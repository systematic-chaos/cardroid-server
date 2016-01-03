package uclm.esi.cardroid.network.server;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import uclm.esi.cardroid.data.oracle.User;
import uclm.esi.cardroid.data.zerocice.UserTyp;
import uclm.esi.cardroid.jdbc.JDBCUtil;
import uclm.esi.cardroid.network.zerocice.RegistrationDeniedException;
import uclm.esi.cardroid.network.zerocice._RegistrationDisp;

import Ice.Current;

/**
 * \class RegistrationI
 * Servant for the registration of new users in the CarDroid platform
 */
public class RegistrationI extends _RegistrationDisp {

	private ConnectionPool _connectionPool;
	private static final long serialVersionUID = 3502112289481155093L;

	/**
	 * Default constructor
	 * @param connectionPool Reference to the database connection pool.
	 * 							The registration feature will need to acquire 
	 * 							a database connection for carrying out the 
	 * 							user's registration process in the platform
	 */
	public RegistrationI(ConnectionPool connectionPool) {
		_connectionPool = connectionPool;
	}

	public boolean registerNewUser(UserTyp user, String password, Current c)
			throws RegistrationDeniedException {
		boolean successfulRegistration;
		Connection conn = _connectionPool.acquire();
		assert conn != null;
		OracleCallableStatement cstmt = null;
		int result = -1;
		String reason;

		User u = new User().newInstance((uclm.esi.cardroid.data.ice.User) user);
		try {
			String sql92Style = "{ call callable_statements.register(?, ?, ?, ?) }";
			// Create the CallableStatement object
			cstmt = (OracleCallableStatement) conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(1, u, User._SQL_TYPECODE);
			cstmt.setString(2, password);
			// Register output parameters
			cstmt.registerOutParameter(1, User._SQL_TYPECODE, User._SQL_NAME);
			cstmt.registerOutParameter(3, OracleTypes.INTEGER);
			cstmt.registerOutParameter(4, OracleTypes.CHAR);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			u = (User) cstmt.getORAData(1, User.getORADataFactory());
			result = cstmt.getInt(3);
			reason = cstmt.getString(4);

			if (successfulRegistration = result == 0)
				conn.commit();
			else {
				conn.rollback();
				throw new RegistrationDeniedException(reason);
			}
		} catch (SQLException sqle) {
			successfulRegistration = false;
			throw new RegistrationDeniedException(
					"Registration failed. SQLException: " + sqle.getMessage(),
					sqle);
		} finally {
			JDBCUtil.close(cstmt);
			_connectionPool.release(conn);
		}

		return successfulRegistration;
	}
}