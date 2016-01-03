package uclm.esi.cardroid.network.server;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import Ice.Logger;
import Ice.ObjectNotExistException;
import Ice.ObjectPrx;
import IceStorm.NoSuchTopic;
import IceStorm.TopicManagerPrx;
import IceStorm.TopicPrx;

/**
 * \class ReapThread
 * Thread responsible for the destruction of idle user sessions
 * It also keeps a record of the currently active user sessions and manages 
 * the destruction of IceStorm topics when no sessions for the user a topic is 
 * referred to are active
 */
public class ReapThread extends Thread {
	private final long _timeout; // seconds
	private Logger _logger;
	private boolean _terminated = false;
	private TopicManagerPrx _topicManager;
	/// Data structure for keeping track of the currently active user sessions
	private Hashtable<String, List<SessionProxyPair>> _sessions;

	/**
	 * Default constructor
	 * @param logger		The logger used to post event or error messages 
	 * @param topicManager	Proxy to the IceStorm topic manager
	 * @param timeout		The time of inactivity a session needs to be 
	 * 							removed due to an idle state
	 */
	public ReapThread(Logger logger, TopicManagerPrx topicManager, long timeout) {
		_logger = logger;
		_topicManager = topicManager;
		_timeout = timeout;
		_sessions = new Hashtable<String, List<SessionProxyPair>>();
	}

	public synchronized void run() {
		while (!_terminated) {
			// This thread will execute once every 500 ms
			try {
				wait(_timeout * 500);
			} catch (InterruptedException e) {
			}

			if (!_terminated) {
				Iterator<Entry<String, List<SessionProxyPair>>> sessionsIt = _sessions
						.entrySet().iterator();
				while (sessionsIt.hasNext()) {
					Entry<String, List<SessionProxyPair>> userSessions = sessionsIt
							.next();
					Iterator<SessionProxyPair> userSessionsIt = userSessions
							.getValue().iterator();
					while (userSessionsIt.hasNext()) {
						SessionProxyPair s = userSessionsIt.next();
						try {
							/* Session destruction may take time. Therefore the *
							 * currentEntry time is computed for each iteration */
							if ((System.currentTimeMillis() - s.session
									.getSessionTimeout()) > _timeout * 1000) {
								
								// This is a Glacier2 session
								if (s.glacier2proxy != null) {
									logSessionDestruction(s.glacier2proxy);
									s.glacier2proxy.destroy();
								}
								
								// This is a factory's session
								if (s.proxy != null) {
									logSessionDestruction(s.proxy);
									s.proxy.destroy();
								}
								
								userSessionsIt.remove();
							}
						} catch (ObjectNotExistException e) {
							userSessionsIt.remove();
						}
					}
					
					// No sessions for the user are still active
					if (userSessions.getValue().isEmpty()) {
						sessionsIt.remove();
						destroyTopic(userSessions.getKey());
					}
				}
			}
		}
	}

	/**
	 * Destroy each of the sessions, releasing any resources they may hold.
	 * This calls directly on the session, not via the proxy since 
	 * terminate() is called after the communicator is shutdown, which means 
	 * call on collocated objects are not permitted.						 
	 */
	public synchronized void terminate() {
		_terminated = true;
		notify();

		Iterator<Entry<String, List<SessionProxyPair>>> sessionsIt = _sessions
				.entrySet().iterator();
		while (sessionsIt.hasNext()) {
			Entry<String, List<SessionProxyPair>> userSessions = sessionsIt
					.next();
			Iterator<SessionProxyPair> userSessionsIt = userSessions.getValue()
					.iterator();
			while (userSessionsIt.hasNext()) {
				SessionProxyPair s = userSessionsIt.next();
				s.session.shutdown();
			}
			userSessions.getValue().clear();
			destroyTopic(userSessions.getKey());
		}
		_sessions.clear();
	}

	/**
	 * Add a new user session to the reaper's record.
	 * This method is called by a Glacier2 session manager
	 * @param proxy		Proxy to the new user session
	 * @param session	Servant of the new user session
	 * @param userName	New session's owner user id
	 */
	public synchronized void add(Glacier2.SessionPrx proxy, SessionI session,
			String userName) {
		List<SessionProxyPair> userSessions;
		if (_sessions.contains(userName))
			userSessions = _sessions.get(userName);
		else
			_sessions.put(userName,
					userSessions = new LinkedList<SessionProxyPair>());
		userSessions.add(new SessionProxyPair(proxy, session));
	}

	/**
	 * Add a new user session to the reaper's record.
	 * This method is called by a session factory, for compatibility with 
	 * platforms not compatible with Glacier2
	 * @param proxy		Proxy to the new user session
	 * @param session	Servant of the new user session
	 * @param userName	New session's owner user id
	 */
	public synchronized void add(
			uclm.esi.cardroid.network.zerocice.SessionPrx proxy,
			SessionI session, String userName) {
		List<SessionProxyPair> userSessions;
		if (_sessions.contains(userName))
			userSessions = _sessions.get(userName);
		else
			_sessions.put(userName,
					userSessions = new LinkedList<SessionProxyPair>());
		userSessions.add(new SessionProxyPair(proxy, session));
	}

	/**
	 * Destroy a session and remove it from the reaper's record
	 * @param servant	The servant of the session to be destroyed and removed
	 * @param topic		Proxy to the topic this session is subscripted to
	 */
	public synchronized void remove(SessionI servant, TopicPrx topic) {
		List<SessionProxyPair> userSessions = _sessions.get(topic.getName());
		if (userSessions == null)
			return;
		Iterator<SessionProxyPair> userSessionsIt = userSessions.iterator();
		while (userSessionsIt.hasNext()) {
			SessionProxyPair s = userSessionsIt.next();
			try {
				if (s.session == servant) {
					
					// This is a Glacier2 session
					if (s.glacier2proxy != null) {
						logSessionDestruction(s.glacier2proxy);
						s.glacier2proxy.destroy();
					}
					
					// This is a factory session
					if (s.proxy != null) {
						logSessionDestruction(s.proxy);
						s.proxy.destroy();
					}
					
					userSessionsIt.remove();
				}
			} catch (ObjectNotExistException e) {
				userSessionsIt.remove();
			}
		}
		// No sessions for the user are still active
		if (userSessions.isEmpty()) {
			_sessions.remove(topic.getName());
			topic.destroy();
		}
	}

	/**
	 * \class SessionProxyPair
	 * Class containing a proxy to a session (created via a Glacier2 router or 
	 * via a session factory) and the servant's session
	 */
	static class SessionProxyPair {
		Glacier2.SessionPrx glacier2proxy;
		uclm.esi.cardroid.network.zerocice.SessionPrx proxy;
		SessionI session;

		public SessionProxyPair(Glacier2.SessionPrx p, SessionI s) {
			glacier2proxy = p;
			proxy = null;
			session = s;
		}

		public SessionProxyPair(
				uclm.esi.cardroid.network.zerocice.SessionPrx p, SessionI s) {
			glacier2proxy = null;
			proxy = p;
			session = s;
		}
	}

	private void logSessionDestruction(ObjectPrx sessionProxy) {
		_logger.trace(
				"ReapThread",
				"The session "
						+ sessionProxy.ice_getCommunicator().identityToString(
								sessionProxy.ice_getIdentity())
						+ " has timed out.");
	}

	/**
	 * Destroy the specified topic, if exists
	 * @param name The name of the topic to destroy
	 */
	private void destroyTopic(String name) {
		TopicPrx topic = null;
		try {
			topic = _topicManager.retrieve(name);
		} catch (NoSuchTopic nst) {
		}
		if (topic != null)
			topic.destroy();
	}
}
