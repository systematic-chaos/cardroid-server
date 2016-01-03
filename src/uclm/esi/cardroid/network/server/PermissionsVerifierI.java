package uclm.esi.cardroid.network.server;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;
import uclm.esi.cardroid.jdbc.JDBCUtil;


import Glacier2.PermissionDeniedException;
import Glacier2._PermissionsVerifierDisp;
import Ice.Current;
import Ice.StringHolder;

/**
 * \class PermissionsVerifierI
 * The Glacier2 permissions verifier.
 * This is called through the process of establishing a session.
 */
public class PermissionsVerifierI extends _PermissionsVerifierDisp {

	private ConnectionPool _connectionPool;
	private static final long serialVersionUID = -2574060917522212083L;

	/**
	 * Default constructor
	 * @param connectionPool Reference to the database connection pool.
	 * 							The permissions verifier will need to acquire 
	 * 							a database connection to check the user's 
	 * 							access credentials against the database
	 */
	public PermissionsVerifierI(ConnectionPool connectionPool) {
		_connectionPool = connectionPool;
	}

	/**
	 * Check whether a user has permission to access the router
	 * @param userId 	The user id for which to check permission
	 * @param password	The user's password
	 * @param reason	The reason why access was denied
	 * @return			True if access is granted, or false otherwise
	 * @throws PermissionDeniedException	Raised if the user access is denied
	 * 										This can be raised in place of 
	 * 										returning false with a reason set 
	 * 										in the reason out parameter 
	 */
	public boolean checkPermissions(String userId, String password,
			StringHolder reason, Current c) throws PermissionDeniedException {
		boolean accessGranted;
		Connection conn = _connectionPool.acquire();
		assert conn != null;
		CallableStatement cstmt = null;
		int result = -1;

		try {
			String sql92Style = "{ ? = call callable_statements.login(?, ? ) }";
			// Create the CallableStatement object
			cstmt = conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setString(2, userId);
			cstmt.setString(3, password);
			// Register output parameters
			cstmt.registerOutParameter(1, OracleTypes.CHAR);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = cstmt.getInt(1);
		} catch (SQLException sqle) {
			accessGranted = false;
			reason.value = "Access denied. SQLException: " + sqle.getMessage();
		} finally {
			JDBCUtil.close(cstmt);
			_connectionPool.release(conn);
		}

		switch (result) {
		case 0:
			accessGranted = true;
			reason.value = "Access granted.";
			break;
		case 1:
			accessGranted = false;
			reason.value = "Access denied. Incorrect login/password pair.";
			break;
		case 2:
			accessGranted = false;
			reason.value = "Access denied. Supplied user email is not registered in the system.";
			break;
		default:
			accessGranted = false;
			reason.value = "Access denied.";
		}

		return accessGranted;
	}
}
