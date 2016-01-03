package uclm.esi.cardroid.network.server;


/**
 * \interface SessionManager
 * Specification of operations for a SessionManager,
 * in addition to those of Glacier2.SessionManager
 */
public interface SessionManager {
	
	/**
	 * Destroy an existing session
	 * @param session	The user session to be destroyed
	 * @param userName	The user id for the session
	 */
	void destroy(SessionI session, String userName);
}
