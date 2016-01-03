package uclm.esi.cardroid.network.server;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;

import uclm.esi.cardroid.jdbc.JDBCUtil;


import Ice.Logger;

/**
 * \class ConnectionPool
 * Database Connection pool, allowing the acquisition and release of the  
 * individual connections and enabling multiple threads or processes to 
 * operate concurrently on the database 
 */
public class ConnectionPool {
	private Logger _logger;
	private boolean _trace = true;
	private String _dbName;
	private String _username;
	private String _password;
	private LinkedList<Connection> _connections = new LinkedList<Connection>();
	private boolean _destroyed = false;
	private int _nconnections;

	/**
	 * Default constructor
	 * @param logger In the case this class works in the context of an Ice 
	 * application (typically on the server side), make use of this Logger to  
	 * print messages related to events or errors in the ConnectionPool
	 * @param username			The user name in the database
	 * @param password			The user password in the database
	 * @param databaseName		The database instance name
	 * @param numConnections	The number of connections the ConnectionPool 
	 * 								must keep
	 * @throws SQLException 	If an error was raised in the establishment of   
	 * 								the connections
	 */
	public ConnectionPool(Logger logger, String username, String password,
			String databaseName, int numConnections) throws SQLException {
		_logger = logger;
		_dbName = databaseName;
		_username = username;
		_password = password;

		// Establish so many connections to the database as specified 
		_nconnections = numConnections;
		if (_trace) {
			_logger.trace("ConnectionPool", "establishing " + numConnections
					+ " connections to " + databaseName);
		}
		while (numConnections-- > 0) {
			Connection connection = JDBCUtil.getConnection(username, password,
					databaseName);
			setupTypeMap(connection);
			_connections.add(connection);
		}
	}

	/**
	 * Destroy this ConnectionPool and close all the database connections it 
	 * keeps
	 */
	public synchronized void destroy() {
		_destroyed = true;
		
		/* Don't allow the acquisition of database connections by any new * 
		 * request, wait until all the connections currently acquired are *
		 * released														  */
		while (_connections.size() != _nconnections) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}

		// Close all the database connections and remove them from the pool
		while (!_connections.isEmpty()) {
			Connection conn = _connections.removeFirst();
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

	/**
	 * Acquire a database Connection. This method will block its caller until 
	 * any connection in the pool is free and the request can be satisfied
	 * @return The database Connection granted to the caller
	 */
	public synchronized Connection acquire() {
		// Wait until there is any free connection in the pool
		while (_connections.isEmpty() && !_destroyed) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		if (_destroyed) {
			return null;
		}
		Connection conn = _connections.removeFirst();

		try {
			boolean closed = conn.isClosed();
			if (closed) {
				_logger.warning("ConnectionPool: lost connection to database");
				conn = null;
			} else {
				// Probe the connection with the database
				PreparedStatement stmt = conn
						.prepareStatement("SELECT 1 FROM DUAL");
				stmt.executeQuery();
				stmt.close();
			}
		} catch (SQLException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			pw.flush();
			_logger.warning("ConnectionPool: lost connection to database:\n"
					+ sw.toString());

			conn = null;
		}

		/* If the connection has been closed, or it otherwise invalid, *
		 * we need to re-establish the connection					   */
		while (conn == null) {
			if (_trace) {
				_logger.trace("ConnectionPool",
						"establishing new database connection");
			}
			try {
				conn = JDBCUtil.getConnection(_username, _password, _dbName);
			} catch (SQLException e) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				pw.flush();
				_logger.warning("ConnectionPool: database connection failed:\n"
						+ sw.toString());
			}
		}
		if (_trace) {
			_logger.trace("ConnectionPool", "returning connection: " + conn
					+ " " + _connections.size() + "/" + _nconnections
					+ " remaining");
		}
		return conn;
	}

	/**
	 * Release a previously acquired database Connection
	 * @param connection The released connection
	 */
	public synchronized void release(Connection connection) {
		if (connection != null) {
			// Add the connection to the pool and notify any waiting thread
			_connections.add(connection);
			notifyAll();
		}
	}

	/**
	 * Setup the type map for the database Connection held by this class, 
	 * so that JDBC can translate the database user defined complex types to 
	 * the corresponding classes implementing them
	 */
	private void setupTypeMap(Connection conn) {
		assert conn != null;
		Map<String, Class<?>> typeMap = null;

		try {
			typeMap = conn.getTypeMap();

			typeMap.put(
					uclm.esi.cardroid.data.oracle.TripOffer_AllowedVarray._SQL_NAME,
					Class.forName(uclm.esi.cardroid.data.oracle.TripOffer_AllowedVarray.class
							.getName()));
			typeMap.put(uclm.esi.cardroid.data.oracle.Bitmap._SQL_NAME, Class
					.forName(uclm.esi.cardroid.data.oracle.Bitmap.class
							.getName()));
			typeMap.put(
					uclm.esi.cardroid.data.oracle.User_CarCollection._SQL_NAME,
					Class.forName(uclm.esi.cardroid.data.oracle.User_CarCollection.class
							.getName()));
			typeMap.put(uclm.esi.cardroid.data.oracle.Car._SQL_NAME, Class
					.forName(uclm.esi.cardroid.data.oracle.Car.class.getName()));
			typeMap.put(
					uclm.esi.cardroid.data.oracle.DateTimePrefs._SQL_NAME,
					Class.forName(uclm.esi.cardroid.data.oracle.DateTimePrefs.class
							.getName()));
			typeMap.put(uclm.esi.cardroid.data.oracle.DateTime._SQL_NAME, Class
					.forName(uclm.esi.cardroid.data.oracle.DateTime.class
							.getName()));
			typeMap.put(uclm.esi.cardroid.data.oracle.Date._SQL_NAME,
					Class.forName(uclm.esi.cardroid.data.oracle.Date.class
							.getName()));
			typeMap.put(uclm.esi.cardroid.data.oracle.LatLng._SQL_NAME, Class
					.forName(uclm.esi.cardroid.data.oracle.LatLng.class
							.getName()));
			typeMap.put(uclm.esi.cardroid.data.oracle.Message._SQL_NAME, Class
					.forName(uclm.esi.cardroid.data.oracle.Message.class
							.getName()));
			typeMap.put(
					uclm.esi.cardroid.data.oracle.TripOffer_PassengerCollection._SQL_NAME,
					Class.forName(uclm.esi.cardroid.data.oracle.TripOffer_PassengerCollection.class
							.getName()));
			typeMap.put(
					uclm.esi.cardroid.data.oracle.TripOffer_Passenger._SQL_NAME,
					Class.forName(uclm.esi.cardroid.data.oracle.TripOffer_Passenger.class
							.getName()));
			typeMap.put(uclm.esi.cardroid.data.oracle.Place._SQL_NAME, Class
					.forName(uclm.esi.cardroid.data.oracle.Place.class
							.getName()));
			typeMap.put(uclm.esi.cardroid.data.oracle.TripOffer._SQL_NAME,
					Class.forName(uclm.esi.cardroid.data.oracle.TripOffer.class
							.getName()));
			typeMap.put(
					uclm.esi.cardroid.data.oracle.TripRequest._SQL_NAME,
					Class.forName(uclm.esi.cardroid.data.oracle.TripRequest.class
							.getName()));
			typeMap.put(uclm.esi.cardroid.data.oracle.Trip._SQL_NAME,
					Class.forName(uclm.esi.cardroid.data.oracle.Trip.class
							.getName()));
			typeMap.put(
					uclm.esi.cardroid.data.oracle.UserActivity._SQL_NAME,
					Class.forName(uclm.esi.cardroid.data.oracle.UserActivity.class
							.getName()));
			typeMap.put(uclm.esi.cardroid.data.oracle.User._SQL_NAME,
					Class.forName(uclm.esi.cardroid.data.oracle.User.class
							.getName()));
			typeMap.put(
					uclm.esi.cardroid.data.oracle.TripOffer_WaypointCollection._SQL_NAME,
					Class.forName(uclm.esi.cardroid.data.oracle.TripOffer_WaypointCollection.class
							.getName()));
			typeMap.put(
					uclm.esi.cardroid.data.oracle.TripOffer_Waypoint._SQL_NAME,
					Class.forName(uclm.esi.cardroid.data.oracle.TripOffer_Waypoint.class
							.getName()));
			typeMap.put(
					uclm.esi.cardroid.data.oracle.Trip_WeekDaysVarray._SQL_NAME,
					Class.forName(uclm.esi.cardroid.data.oracle.Trip_WeekDaysVarray.class
							.getName()));

			conn.setTypeMap(typeMap);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.err.println("ClassNotFoundException: " + cnfe.getMessage());
		}
	}
}
