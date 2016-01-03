package uclm.esi.cardroid.network.server;

import uclm.esi.cardroid.network.zerocice._Glacier2SessionTie;
import Glacier2.SessionControlPrx;
import Glacier2.SessionPrx;
import Glacier2.SessionPrxHelper;
import Glacier2._SessionManagerDisp;
import Ice.Current;
import Ice.Logger;
import IceStorm.NoSuchTopic;
import IceStorm.TopicExists;
import IceStorm.TopicManagerPrx;
import IceStorm.TopicPrx;

/**
 * \class Glacier2SessionManagerI
 * The session manager for username/password authenticated users that is 
 * responsible for managing Glacier2.Session objects. New session objects are 
 * created by the Glacier2.Router object calling on an application-provided 
 * session manager. If no session manager is provided by the application, no 
 * client-visible sessions are passed to the client.
 */
public class Glacier2SessionManagerI extends _SessionManagerDisp implements
		SessionManager {
	private TopicManagerPrx _topicManager;
	private Logger _logger;
	private ReapThread _reaper;

	private static final long serialVersionUID = -8088570405686337161L;

	/**
	 * Default constructor
	 * @param topicManager	Proxy to the IceStorm topic manager
	 * @param logger		Logger to post events and errors related to the 
	 * 							session management, creation and destruction
	 * @param reaper		Reference to the thread responsible for destroying 
	 * 							idle sessions, as well as reaping all the 
	 * 							sessions alive at the time the router is 
	 * 							shutdown
	 */
	public Glacier2SessionManagerI(TopicManagerPrx topicManager, Logger logger,
			ReapThread reaper) {
		_topicManager = topicManager;
		_logger = logger;
		_reaper = reaper;
	}

	/**
	 * Create a new session
	 * @param userId	The user id for the session
	 * @param control	A proxy to the session control object
	 * @return			A proxy to the newly created session
	 */
	public synchronized SessionPrx create(String userId,
			SessionControlPrx control, Current c) {
		TopicPrx topic = retrieveTopic(userId);
		SessionI session = new SessionI(_logger, true, c.adapter, topic,
				userId, this, true);
		_Glacier2SessionTie servant = new _Glacier2SessionTie(session);

		SessionPrx proxy = SessionPrxHelper.uncheckedCast(c.adapter
				.addWithUUID(servant));

		_logger.trace(
				"SessionFactory",
				"Create new session: "
						+ c.adapter.getCommunicator().identityToString(
								proxy.ice_getIdentity()));

		// Add the newly created session to the reaper's record
		_reaper.add(proxy, session, userId);

		return proxy;
	}

	/**
	 * Destroy an existing session
	 * @param session	The user session to be destroyed
	 * @param userName	The user id for the session
	 */
	public synchronized void destroy(SessionI session, String userName) {
		_reaper.remove(session, retrieveTopic(userName));
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
