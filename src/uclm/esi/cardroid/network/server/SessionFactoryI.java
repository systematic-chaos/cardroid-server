package uclm.esi.cardroid.network.server;

import uclm.esi.cardroid.network.zerocice.SessionPrx;
import uclm.esi.cardroid.network.zerocice.SessionPrxHelper;
import uclm.esi.cardroid.network.zerocice._SessionFactoryDisp;
import Glacier2._SessionTie;
import Ice.Current;
import Ice.Logger;
import IceStorm.NoSuchTopic;
import IceStorm.TopicExists;
import IceStorm.TopicManagerPrx;
import IceStorm.TopicPrx;

/**
 * \class SessionFactoryI
 * The session manager for users responsible for creating session objects.
 * This class purpose is the creation of sessions for clients running on 
 * platforms no compatible with Glacier2. Since these users sessions cannot
 * be created via a Glacier2 router, they are programmatically created here
 */
public class SessionFactoryI extends _SessionFactoryDisp implements
		SessionManager {
	private TopicManagerPrx _topicManager;
	private Logger _logger;
	private ReapThread _reaper;
	private long _timeout;

	private static final long serialVersionUID = -3509017929914135668L;

	/**
	 * Default constructor
	 * @param topicManager	Proxy to the IceStorm topic manager
	 * @param logger		Logger to post events and errors related to the 
	 * 							session management, creation and destruction
	 * @param reaper		Reference to the thread responsible for destroying 
	 * 							idle sessions, as well as reaping all the 
	 * 							sessions alive at the time the router is 
	 * 							shutdown
	 * @param timeout		The time of inactivity a session needs to be 
	 * 							considered to reach an idle state
	 */
	public SessionFactoryI(TopicManagerPrx topicManager, Logger logger,
			ReapThread reaper, long timeout) {
		_topicManager = topicManager;
		_logger = logger;
		_reaper = reaper;
		_timeout = timeout;
	}

	public synchronized SessionPrx create(Current c) {
		TopicPrx topic = retrieveTopic(c.id.name);
		SessionI session = new SessionI(_logger, false, c.adapter, topic,
				c.id.name, this, false);
		_SessionTie servant = new _SessionTie(session);

		SessionPrx proxy = SessionPrxHelper.uncheckedCast(c.adapter
				.addWithUUID(servant));

		_logger.trace(
				"SessionFactory",
				"Create new session: "
						+ c.adapter.getCommunicator().identityToString(
								proxy.ice_getIdentity()));

		// Add the newly created session to the reaper's record
		_reaper.add(proxy, session, c.id.name);

		return proxy;
	}

	public synchronized void destroy(SessionI session, String userName) {
		_reaper.remove(session, retrieveTopic(userName));
	}

	public long getSessionTimeout(Current c) {
		return _timeout;
	}

	/**
	 * Retrieve the IceStorm topic corresponding to the user creating the new 
	 * session. Create the topic if it doesn't exist. Such a topic will be 
	 * used for publishing event notifications destined to the clients on
	 * which active sessions for the user are running
	 * @param name	The user id, which will act as the name for the topic
	 * @return		A proxy to the user's topic
	 */
	private TopicPrx retrieveTopic(String name) {
		TopicPrx topic = null;
		while (topic == null) {
			try {
				topic = _topicManager.retrieve(name);
			} catch (NoSuchTopic nst) {
				try {
					topic = _topicManager.create(name);
				} catch (TopicExists te) {
					/*
					 * Another session for the user is active and created the
					 * topic
					 */
				}
			}
		}
		return topic;
	}
}
