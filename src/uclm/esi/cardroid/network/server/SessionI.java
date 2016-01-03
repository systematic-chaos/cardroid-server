package uclm.esi.cardroid.network.server;

import uclm.esi.cardroid.network.zerocice._Glacier2SessionOperations;
import uclm.esi.cardroid.network.zerocice._SessionOperations;
import uclm.esi.cardroid.zerocice.CardroidManagerPrx;
import uclm.esi.cardroid.zerocice.CardroidManagerPrxHelper;
import Ice.Current;
import Ice.Logger;
import Ice.ObjectAdapter;
import Ice.ObjectNotExistException;
import IceStorm.TopicPrx;

/**
 * \class SessionI
 * The session object.
 * This is used to retrieve a CarDroid session on behalf of the client.
 * If the session is not refreshed on a periodic basis, it will be 
 * automatically destroyed.
 */
public class SessionI implements _SessionOperations, _Glacier2SessionOperations {
	private Logger _logger;
	private TopicPrx _topic;
	/// true if destroy() was called, false otherwise
	private boolean _destroyed = false;
	/// The last time the session was refreshed
	private long _timeout;
	private String _userId;
	private SessionManager _sessionManager;
	private boolean _glacier2;
	private CardroidManagerPrx _cardroidManagerProxy;
	private CardroidManagerI _cardroidManagerServant;

	/**
	 * Default constructor
	 * @param logger			The logger used by this Application 's 
	 * 								Communicator to post event and error 
	 * 								messages
	 * @param trace				Whether a trace of the session activity will be 
	 * 								generated
	 * @param adapter			The object adapter used to create a proxy to 
	 * 								the CardroidManager object created for 
	 * 								this session
	 * @param topic				Proxy to the topic of the user who owns this 
	 * 								session
	 * @param userId			The id of the user who owns this session
	 * @param sessionManager	The SessionManager for this session, used to 
	 * 								ask for the session destruction from the 
	 * 								session itself
	 * @param glacier2			Whether this session was created by means of a 
	 * 								Glacier2 router
	 */
	public SessionI(Logger logger, boolean trace, ObjectAdapter adapter,
			TopicPrx topic, String userId, SessionManager sessionManager,
			boolean glacier2) {
		_logger = logger;
		_timeout = System.currentTimeMillis();
		_topic = topic;
		_userId = userId;
		_sessionManager = sessionManager;
		_glacier2 = glacier2;
		_cardroidManagerServant = new CardroidManagerI();
		_cardroidManagerProxy = CardroidManagerPrxHelper
				.uncheckedCast(adapter.addWithUUID(new DispatchInterceptorI(
						_cardroidManagerServant)));
	}

	public synchronized CardroidManagerPrx getCardroidManager(Current c) {
		if (_destroyed) {
			throw new ObjectNotExistException();
		}
		return _cardroidManagerProxy;
	}

	public TopicPrx getTopic(Current c) {
		return _topic;
	}

	public synchronized void refresh(Current c) {
		if (_destroyed) {
			throw new ObjectNotExistException();
		}
		_timeout = System.currentTimeMillis();
	}

	/**
	 * @return The timeout for this session
	 */
	public synchronized long getSessionTimeout() {
		if (_destroyed)
			throw new ObjectNotExistException();
		
		return _timeout;
	}

	public synchronized void destroy(Current c) {
		if (_destroyed) {
			throw new ObjectNotExistException();
		}

		_destroyed = true;
		_logger.trace("Session", "Session "
				+ c.adapter.getCommunicator().identityToString(c.id)
				+ " is now destroyed.");

		// This method is never called on shutdown of the server
		_cardroidManagerServant.destroy();
		c.adapter.remove(_cardroidManagerProxy.ice_getIdentity());
		c.adapter.remove(c.id);
		if (!_glacier2 && _sessionManager != null)
			_sessionManager.destroy(this, _userId);
	}

	/**
	 *  Called on application shutdown
	 */
	public synchronized void shutdown() {
		if (!_destroyed) {
			_destroyed = true;
			_cardroidManagerServant.shutdown();
		}
	}
}
