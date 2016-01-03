package uclm.esi.cardroid.jdbc;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLWarning;

import oracle.jdbc.pool.OracleDataSource;

/**
 * \class JDBCUtil
 * Provides static functions for the management of database Connection ,  
 * Statement , ResultSet and error handling
 */
public class JDBCUtil {
	
	/**
	 * Establishes a Connection to the database specified by the supplied 
	 * parameters
	 * @param username	The user name of the database
	 * @param password	The password of the user in the database
	 * @param dbName	The database instance name
	 * @return 			A connection to the database, null if it could not be  
	 * 						successfully established
	 * @throws SQLException	If an error was raised in the establishment  
	 * 							of the connection
	 */
	public static Connection getConnection(String username, String password,
			String dbName) throws SQLException {
		OracleDataSource ods = null;
		Connection connection = null;
		ods = new OracleDataSource();

		// Set the properties that define the connection
		ods.setDriverType("thin"); // Type of driver
		ods.setServerName("localhost"); // Database server name
		ods.setNetworkProtocol("tcp"); // tcp is the default anyway
		ods.setDatabaseName(dbName); // Oracle SID
		if ("ora92".equals(dbName)) {
			ods.setPortNumber(1522);
		} else {
			ods.setPortNumber(1521);
		}
		ods.setUser(username); // User name
		ods.setPassword(password); // Password
		System.out.println("URL:" + ods.getURL());
		System.out.flush();

		// Get the connection without JNDI
		try {
			connection = ods.getConnection();
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			System.err.println("ERROR: Could not get the connection!");
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		/*
		 * finally { ods.close(); }
		 */

		return connection;
	}

	/**
	 * Close a ResultSet , the Statement that originated it, and the database 
	 * Connection over which the Statement was executed
	 */
	public static void close(ResultSet resultSet, Statement statement,
			Connection connection) {
		try {
			if (resultSet != null)
				resultSet.close();
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		} catch (SQLException ignored) {
		}

	}

	/**
	 * Close a ResultSet and the Statement that originated it
	 */
	public static void close(ResultSet resultSet, Statement statement) {
		try {
			if (resultSet != null)
				resultSet.close();
			if (statement != null)
				statement.close();
		} catch (SQLException ignored) {
		}
	}

	/**
	 * Close a ResultSet
	 */
	public static void close(ResultSet resultSet) {
		try {
			if (resultSet != null)
				resultSet.close();
		} catch (SQLException ignored) {
		}

	}

	/**
	 * Close a Statement
	 */
	public static void close(Statement statement) {
		try {
			if (statement != null)
				statement.close();
		} catch (SQLException ignored) {
		}

	}

	/**
	 * Close a database Connection
	 */
	public static void close(Connection connection) {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException ignored) {
		}
	}

	/**
	 * Print an exception, providing convenient format
	 */
	public static void printException(Exception e) {
		System.out.println("Exception caught! Exiting ..");
		System.out.println("error message: " + e.getMessage());
		e.printStackTrace();
	}

	/**
	 * Print an exception, providing convenient format, and rollback the 
	 * changes made on the Connection supplied to the last checkpoint
	 * (uncommited transactions will be undone)
	 */
	public static void printExceptionAndRollback(Connection conn, Exception e) {
		printException(e);
		try {
			if (conn != null)
				conn.rollback();
		} catch (SQLException ignore) {
		}
	}

	// PRIVATE METHODS

	/**
	 * Starts SQL trace for a JDBC program. Also sets the timed statistics to
	 * true. The SQL trace is automatically disabled when the program ends
	 */

	public static void startTrace(Connection connection) throws SQLException {
		String setTimedStatisticsStmt = "alter session set timed_statistics=true";
		String setTraceStmt = "alter session set events '10046 trace name context forever, level 12'";
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			stmt.execute(setTimedStatisticsStmt);
			stmt.execute(setTraceStmt);
		} finally {
			stmt.close();
		}
	}

	public static void printRsetTypeAndConcurrencyType(Statement stmt)
			throws SQLException {
		System.out.print("\tResult set category (using Statement API): ");
		int resultSetType = stmt.getResultSetType();
		switch (resultSetType) {
		case ResultSet.TYPE_FORWARD_ONLY:
			System.out.print("Forward only");
			break;
		case ResultSet.TYPE_SCROLL_INSENSITIVE:
			System.out.print("Scroll insensitive");
			break;
		case ResultSet.TYPE_SCROLL_SENSITIVE:
			System.out.print("Scroll sensitive");
			break;
		}
		int resultSetConcurrency = stmt.getResultSetConcurrency();
		switch (resultSetConcurrency) {
		case ResultSet.CONCUR_READ_ONLY:
			System.out.println(", Read only");
			break;
		case ResultSet.CONCUR_UPDATABLE:
			System.out.println(", Updatable");
			break;
		}
	}

	public static void printRsetTypeAndConcurrencyType(ResultSet rset)
			throws SQLException {
		int resultSetType = rset.getType();
		System.out.print("\tResult set category (using ResultSet API): ");

		switch (resultSetType) {
		case ResultSet.TYPE_FORWARD_ONLY:
			System.out.print("Forward only");
			break;
		case ResultSet.TYPE_SCROLL_INSENSITIVE:
			System.out.print("Scroll insensitive");
			break;
		case ResultSet.TYPE_SCROLL_SENSITIVE:
			System.out.print("Scroll sensitive");
			break;
		}
		int resultSetConcurrency = rset.getConcurrency();
		switch (resultSetConcurrency) {
		case ResultSet.CONCUR_READ_ONLY:
			System.out.println(", Read only");
			break;
		case ResultSet.CONCUR_UPDATABLE:
			System.out.println(", Updatable");
			break;
		}
	}

	public static void printWarnings(ResultSet resultSet) throws SQLException {
		System.out.println("Resultset warnings begin:");
		if (resultSet != null) {
			SQLWarning warning = resultSet.getWarnings();
			if (warning != null)
				warning = warning.getNextWarning();
			if (warning != null)
				System.out.println("MessageTyp: " + warning.getMessage());
		}
		System.out.println("Resultset warnings end");
	}

	public static void printWarnings(Statement stmt) throws SQLException {
		System.out.println("Resultset warnings begin:");
		if (stmt != null) {
			SQLWarning warning = stmt.getWarnings();
			if (warning != null)
				warning = warning.getNextWarning();
			if (warning != null)
				System.out.println("MessageTyp: " + warning.getMessage());
		}
		System.out.println("Resultset warnings end");
	}

	/**
	 *  Sets session cached cursor for the connection
	 */
	public static void setSessionCachedCursors(Connection connection,
			int sessionCachedCursors) throws SQLException {
		String stmtStr = "alter session set session_cached_cursors="
				+ sessionCachedCursors;
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			stmt.execute(stmtStr);
		} finally {
			stmt.close();
		}
	}
}
