package uclm.esi.cardroid.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORADataFactory;

import uclm.esi.cardroid.data.ICar.Fuel;
import uclm.esi.cardroid.data.oracle.Car;
import uclm.esi.cardroid.data.oracle.Message;
import uclm.esi.cardroid.data.oracle.Place;
import uclm.esi.cardroid.data.oracle.Trip;
import uclm.esi.cardroid.data.oracle.TripOffer;
import uclm.esi.cardroid.data.oracle.TripRequest;
import uclm.esi.cardroid.data.oracle.User;
import uclm.esi.cardroid.data.oracle.UserActivity;

/**
 * \class CallableStatementManager
 * CarDroid platform's server interface with its Oracle Database.
 * Setups a Connection to the database and provides methods to the execution 
 * the operations ( CallableStatement s) provided by CarDroid's database, 
 * managing every aspect related to datatypes conversion, statements 
 * execution, results retrieval and exceptions handling
 */
public class CallableStatementManager {
	private static CallableStatementManager instance;
	private static Connection conn;

	/**
	 * Private constructor
	 * Only one instance of this class can exist at one time, it must be 
	 * gotten from the getInstance method, which implements a Singleton pattern
	 * @param username	The user name in the database
	 * @param password	The user password in the database
	 * @param dbName	The database instance name
	 */
	private CallableStatementManager(String username, String password,
			String dbName) {
		try {
			conn = JDBCUtil.getConnection(username, password, dbName);
		} catch (SQLException e) {
			// Print stack trace
			JDBCUtil.printException(e);
		} finally {
			// Close the connection in finally clause
			JDBCUtil.close(conn);
		}

		/* Setup the connection's type map for operation with user defined *
		 * complex data types											   */
		setupTypeMap();
	}

	/**
	 * Singleton pattern implementation
	 * @param username	The user name in the database
	 * @param password	The user password in the database
	 * @param dbName	The database instance name
	 * @return			A static instance of this class
	 */
	public CallableStatementManager getInstance(String username,
			String password, String dbName) {
		if (instance == null)
			instance = new CallableStatementManager(username, password, dbName);
		return instance;
	}

	/**
	 * Execute a SQL statement on the connection held by this class
	 * @param sql92Style	SQL statatement, according to the SQL 92 standard
	 * 							specification
	 * @param commit		Whether the transaction should be commited right  
	 * 							after its execution
	 * @param params		Parameters to be binded as arguments in the  
	 * 							statement to be executed
	 * @return				Whether the statement was successfully executed
	 * @throws SQLException
	 */
	public boolean executeStatement(String sql92Style, boolean commit,
			Object... params) throws SQLException {
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			pstmt = conn.prepareStatement(sql92Style);

			for (int n = 0; n < params.length; n++) {
				pstmt.setObject(n + 1, params[n]);
			}

			result = pstmt.execute();
			if (commit)
				conn.commit();
		} catch (SQLException e) {
			// Print stack trace
			JDBCUtil.printException(e);
		} finally {
			// Release JDBC resources in finally clause
			JDBCUtil.close(pstmt);
		}

		return result;
	}

	/**
	 * Execute a SQL statement on the connection held by this class
	 * @param sql92Style	SQL statatement, according to the SQL 92 standard
	 * 							specification
	 * @param commit		Whether the transaction should be commited right  
	 * 							after its execution
	 * @param params		Map of the parameters to be binded as arguments in 
	 * 							the statement to be executed. For every Map 
	 * 							Entry, the key is the parameter itself, as the 
	 * 							value is its SQL type code
	 * @return				Whether the statement was successfully executed
	 * @throws SQLException
	 */
	public boolean executeStatement(String sql92Style, boolean commit,
			Map<Object, Integer> params) throws SQLException {
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			pstmt = conn.prepareStatement(sql92Style);

			Iterator<Entry<Object, Integer>> paramsIter = params.entrySet()
					.iterator();
			int pos = 1;
			while (paramsIter.hasNext()) {
				Entry<Object, Integer> paramEntry = paramsIter.next();
				pstmt.setObject(pos++, paramEntry.getKey(),
						paramEntry.getValue());
			}

			result = pstmt.execute();
			if (commit)
				conn.commit();
		} catch (SQLException e) {
			// Print stack trace
			JDBCUtil.printException(e);
		} finally {
			// Release JDBC resources in finally clause
			JDBCUtil.close(pstmt);
		}

		return result;
	}

	/**
	 * Execute a SQL query on the connection held by this class
	 * @param sql92Style	SQL query statatement, according to the SQL 92 
	 * 							standard specification
	 * @param rowsFetched	The maximum number of rows to be fetched by this 
	 * 							query
	 * @param params		Params to be binded as arguments in the statement 
	 * 							to be executed
	 * @return				The result of executing the commanded query
	 * @throws SQLException
	 */
	public ResultSet executeStatementQuery(String sql92Style, int rowsFetched,
			Object... params) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			pstmt = conn.prepareStatement(sql92Style);
			pstmt.setFetchSize(rowsFetched);

			for (int n = 0; n < params.length; n++) {
				pstmt.setObject(n + 1, params[n]);
			}

			result = pstmt.executeQuery();
		} catch (SQLException e) {
			// Print stack trace
			JDBCUtil.printException(e);
		} finally {
			// Release JDBC resources in finally clause
			JDBCUtil.close(pstmt);
		}

		return result;
	}

	/**
	 * Execute a SQL query on the connection held by this class
	 * @param sql92Style	SQL query statatement, according to the SQL 92 
	 * 							standard specification
	 * @param rowsFetched	The maximum number of rows to be fetched by this 
	 * 							query
	 * @param params		Map of the parameters to be binded as arguments in 
	 * 							the statement to be executed. For every Map 
	 * 							Entry, the key is the parameter itself, as the 
	 * 							value is its SQL type code
	 * @return				The result of executing the commanded query
	 * @throws SQLException
	 */
	public ResultSet executeStatementQuery(String sql92Style, int rowsFetched,
			Map<Object, Integer> params) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			pstmt = conn.prepareStatement(sql92Style);
			pstmt.setFetchSize(rowsFetched);

			Iterator<Entry<Object, Integer>> paramsIter = params.entrySet()
					.iterator();
			int pos = 1;
			while (paramsIter.hasNext()) {
				Entry<Object, Integer> paramEntry = paramsIter.next();
				pstmt.setObject(pos++, paramEntry.getKey(),
						paramEntry.getValue());
			}

			result = pstmt.executeQuery();
		} catch (SQLException e) {
			// Print stack trace
			JDBCUtil.printException(e);
		} finally {
			// Release JDBC resources in finally clause
			JDBCUtil.close(pstmt);
		}

		return result;
	}

	/**
	 * Execute a SQL update statement on the connection held by this class
	 * @param sql92Style	SQL statatement, according to the SQL 92 standard
	 * 							specification
	 * @param commit		Whether the transaction should be commited right  
	 * 							after its execution
	 * @param params		Parameters to be binded as arguments in the  
	 * 							statement to be executed
	 * @return				The number of rows affected by this statement 
	 * 							execution
	 * @throws SQLException
	 */
	public int executeStatementUpdate(String sql92Style, boolean commit,
			Object... params) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql92Style);

			for (int n = 0; n < params.length; n++) {
				pstmt.setObject(n + 1, params[n]);
			}

			result = pstmt.executeUpdate();
			if (commit)
				conn.commit();
		} catch (SQLException e) {
			// Print stack trace
			JDBCUtil.printException(e);
		} finally {
			// Release JDBC resources in finally clause
			JDBCUtil.close(pstmt);
		}

		return result;
	}

	/**
	 * Execute a SQL update statement on the connection held by this class
	 * @param sql92Style	SQL statatement, according to the SQL 92 standard
	 * 							specification
	 * @param commit		Whether the transaction should be commited right  
	 * 							after its execution
	 * @param params		Map of the parameters to be binded as arguments in 
	 * 							the statement to be executed. For every Map 
	 * 							Entry, the key is the parameter itself, as the 
	 * 							value is its SQL type code
	 * @return				The number of rows affected by this statement 
	 * 							execution
	 * @throws SQLException
	 */
	public int executeStatementUpdate(String sql92Style, boolean commit,
			Map<Object, Integer> params) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql92Style);

			Iterator<Entry<Object, Integer>> paramsIter = params.entrySet()
					.iterator();
			int pos = 1;
			while (paramsIter.hasNext()) {
				Entry<Object, Integer> paramEntry = paramsIter.next();
				pstmt.setObject(pos++, paramEntry.getKey(),
						paramEntry.getValue());
			}

			result = pstmt.executeUpdate();
			if (commit)
				conn.commit();
		} catch (SQLException e) {
			// Print stack trace
			JDBCUtil.printException(e);
		} finally {
			// Release JDBC resources in finally clause
			JDBCUtil.close(pstmt);
		}

		return result;
	}

	/**
	 * Execute a stored procedure call on the connection held by this class
	 * @param sql92Style	SQL stored procedure call statement, according to 
	 * 							the SQL 92 standard specification
	 * @param commit		Whether the transaction should be commited right  
	 * 							after its execution
	 * @param params		Parameters to be binded as arguments in the  
	 * 							statement to be executed
	 * @return				Whether the stored procedure commanded was  
	 * 							successfully called and executed
	 * @throws SQLException
	 */
	public boolean executeProcedure(String sql92Style, boolean commit,
			Object... params) throws SQLException {
		CallableStatement cstmt = null;
		boolean result = false;

		try {
			cstmt = conn.prepareCall(sql92Style);

			for (int n = 0; n < params.length; n++) {
				cstmt.setObject(n + 1, params[n]);
			}

			result = cstmt.execute();
			if (commit)
				conn.commit();
		} catch (SQLException e) {
			// Print stack trace
			JDBCUtil.printException(e);
		} finally {
			// Release JDBC resources in finally clause
			JDBCUtil.close(cstmt);
		}
		return result;
	}

	/**
	 * Execute a stored procedure call on the connection held by this class
	 * @param sql92Style	SQL stored procedure call statement, according to 
	 * 							the SQL 92 standard specification
	 * @param commit		Whether the transaction should be commited right  
	 * 							after its execution
	 * @param params		Map of the parameters to be binded as arguments in 
	 * 							the statement to be executed. For every Map 
	 * 							Entry, the key is the parameter itself, as the 
	 * 							value is its SQL type code
	 * @return				Whether the stored procedure commanded was  
	 * 							successfully called and executed
	 * @throws SQLException
	 */
	public boolean executeProcedure(String sql92Style, boolean commit,
			Map<Object, Integer> params) {
		CallableStatement cstmt = null;
		boolean result;

		try {
			cstmt = conn.prepareCall(sql92Style);

			Iterator<Entry<Object, Integer>> paramsIter = params.entrySet()
					.iterator();
			int pos = 1;
			while (paramsIter.hasNext()) {
				Entry<Object, Integer> paramEntry = paramsIter.next();
				cstmt.setObject(pos++, paramEntry.getKey(),
						paramEntry.getValue());
			}
			result = cstmt.execute();
			if (commit)
				conn.commit();
		} catch (SQLException e) {
			// Print stack trace
			JDBCUtil.printException(e);
			result = false;
		} finally {
			// Release JDBC resources in finally clause
			JDBCUtil.close(cstmt);
		}
		return result;
	}

	/**
	 * Execute a stored function call on the connection held by this class
	 * @param sql92Style		SQL stored function call statement, according 
	 * 								to the SQL 92 standard specification
	 * @param resultSqlType		SQL type code of the function's result
	 * @param resultSqlName		SQL type name of the function's result
	 * @param resultTypeClass	The class which the function's result must be 
	 * 								casted to
	 * @param params			Parameters to be binded as arguments in the  
	 * 								statement to be executed
	 * @return					The result of executing the function
	 * @throws SQLException
	 */
	public <C> C executeFunction(String sql92Style, int resultSqlType,
			String resultTypeName, Class<C> resultTypeClass, Object... params)
			throws SQLException {
		CallableStatement cstmt = null;
		C result = null;

		try {
			cstmt = conn.prepareCall(sql92Style);

			cstmt.registerOutParameter(1, resultSqlType, resultTypeName);
			for (int n = 0; n < params.length; n++) {
				cstmt.setObject(n + 2, params[n]);
			}

			cstmt.execute();
			result = cstmt.getObject(1, resultTypeClass);
		} catch (SQLException e) {
			// Print stack trace
			JDBCUtil.printException(e);
		} finally {
			// Release JDBC resources in finally clause
			JDBCUtil.close(cstmt);
		}
		return result;
	}

	/**
	 * Execute a stored function call on the connection held by this class
	 * @param sql92Style		SQL stored function call statement, according 
	 * 								to the SQL 92 standard specification
	 * @param resultSqlType		SQL type code of the function's result
	 * @param resultSqlName		SQL type name of the function's result
	 * @param resultTypeClass	The class which the function's result must be 
	 * 								casted to
	 * @param params			Map of the parameters to be binded as  
	 * 								arguments in the statement to be executed. 
	 * 								For every Map Entry, the key is the 
	 * 								parameter itself, as the value is its SQL 
	 * 								type code
	 * @return					The result of executing the function
	 * @throws SQLException
	 */
	public <C> C executeFunction(String sql92Style, int resultSqlType,
			String resultTypeName, Class<C> resultTypeClass,
			Map<Object, Integer> params) {
		CallableStatement cstmt = null;
		C result = null;

		try {
			cstmt = conn.prepareCall(sql92Style);

			cstmt.registerOutParameter(1, resultSqlType, resultTypeName);
			Iterator<Entry<Object, Integer>> paramsIter = params.entrySet()
					.iterator();
			int pos = 2;
			while (paramsIter.hasNext()) {
				Entry<Object, Integer> paramEntry = paramsIter.next();
				cstmt.setObject(pos++, paramEntry.getKey(),
						paramEntry.getValue());
			}

			cstmt.execute();
			result = cstmt.getObject(1, resultTypeClass);
		} catch (SQLException e) {
			// Print stack trace
			JDBCUtil.printException(e);
		} finally {
			// Release JDBC resources in finally clause
			JDBCUtil.close(cstmt);
		}
		return result;
	}
	
	/**
	 * Execute a stored function call on the connection held by this class
	 * @param sql92Style		SQL stored function call statement, according 
	 * 								to the SQL 92 standard specification
	 * @param resultSqlType		SQL type code of the function's result
	 * @param resultSqlName		SQL type name of the function's result
	 * @param resultTypeFactory	The factory for building an instance of 
	 * 								resultTypeClass from a SQL struct
	 * @param resultTypeClass	The class which the function's result must be 
	 * 								casted to
	 * @param params			Parameters to be binded as arguments in the  
	 * 								statement to be executed
	 * @return					The result of executing the function
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public <C> C executeFunction(String sql92Style, int resultSqlType,
			String resultTypeName, ORADataFactory resultTypeFactory,
			Object... params) throws SQLException {
		OracleCallableStatement cstmt = null;
		C result = null;

		try {
			cstmt = (OracleCallableStatement) conn.prepareCall(sql92Style);

			cstmt.registerOutParameter(1, resultSqlType, resultTypeName);
			for (int n = 0; n < params.length; n++) {
				cstmt.setObject(n + 2, params[n]);
			}

			cstmt.execute();
			result = (C) cstmt.getORAData(1, resultTypeFactory);
		} catch (SQLException e) {
			// Print stack trace
			JDBCUtil.printException(e);
		} finally {
			// Release JDBC resources in finally clause
			JDBCUtil.close(cstmt);
		}
		return result;
	}

	/**
	 * Execute a stored function call on the connection held by this class
	 * @param sql92Style		SQL stored function call statement, according 
	 * 								to the SQL 92 standard specification
	 * @param resultSqlType		SQL type code of the function's result
	 * @param resultSqlName		SQL type name of the function's result
	 * @param resultTypeFactory	The factory for building an instance of 
	 * 								resultTypeClass from a SQL struct
	 * @param resultTypeClass	The class which the function's result must be 
	 * 								casted to
	 * @param params			Map of the parameters to be binded as  
	 * 								arguments in the statement to be executed. 
	 * 								For every Map Entry, the key is the 
	 * 								parameter itself, as the value is its SQL 
	 * 								type code
	 * @return					The result of executing the function
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public <C> C executeFunction(String sql92Style, int resultSqlType,
			String resultTypeName, ORADataFactory resultTypeFactory,
			Class<C> resultTypeClass, Map<Object, Integer> params) {
		OracleCallableStatement cstmt = null;
		C result = null;

		try {
			cstmt = (OracleCallableStatement) conn.prepareCall(sql92Style);

			cstmt.registerOutParameter(1, resultSqlType, resultTypeName);
			Iterator<Entry<Object, Integer>> paramsIter = params.entrySet()
					.iterator();
			int pos = 2;
			while (paramsIter.hasNext()) {
				Entry<Object, Integer> paramEntry = paramsIter.next();
				cstmt.setObject(pos++, paramEntry.getKey(),
						paramEntry.getValue());
			}

			cstmt.execute();
			result = (C) cstmt.getORAData(1, resultTypeFactory);
		} catch (SQLException e) {
			// Print stack trace
			JDBCUtil.printException(e);
		} finally {
			// Release JDBC resources in finally clause
			JDBCUtil.close(cstmt);
		}
		return result;
	}

	/**
	 * Execute the "getUserPlaces" function
	 * @return The query result
	 */
	public ResultSet getUserPlaces(User usr) {
		CallableStatement cstmt = null;
		ResultSet result = null;

		try {
			String sql92Style = "{ ? = call callable_statements.get_user_places(?) }";
			// Create the CallableStatement object
			cstmt = conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(2, usr, User._SQL_TYPECODE);
			// Register output parameters
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = (ResultSet) cstmt.getObject(1);
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}

		return result;
	}

	/**
	 * Execute the "getUserPlaces" function and store its results in a List
	 * @return The query result, stored in a List structure
	 */
	public List<Place> getUserPlacesList(User usr) {
		return resultSetToList(getUserPlaces(usr), Place.class);
	}

	/**
	 * Execute the "searchTrips" function
	 * @return The query result
	 */
	public ResultSet searchTrips(TripRequest tRequest) {
		CallableStatement cstmt = null;
		ResultSet result = null;

		try {
			String sql92Style = "{ ? = call callable_statements.search_trips(?) }";
			// Create the CallableStatement object
			cstmt = conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(2, tRequest,
					uclm.esi.cardroid.data.oracle.TripRequest._SQL_TYPECODE);
			// Register output parameters
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = (ResultSet) cstmt.getObject(1);
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}

		return result;
	}

	/**
	 * Execute the "searchTrips" function and store its results in a List
	 * @return The query result, stored in a List structure
	 */
	public List<Trip> searchTripsList(TripRequest tRequest) {
		return resultSetToList(searchTrips(tRequest), Trip.class);
	}

	/**
	 * Execute the "getTripFromId" function
	 * @return The function result
	 */
	public Trip getTripFromId(int tripId) {
		OracleCallableStatement cstmt = null;
		Trip result = null;

		try {
			String sql92Style = "{ ? = call callable_statements.get_trip_from_id(?) }";
			// Create the CallableStatement object
			cstmt = (OracleCallableStatement) conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setInt(2, tripId);
			// Register output parameters
			cstmt.registerOutParameter(1, Trip._SQL_TYPECODE, Trip._SQL_NAME);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = (Trip) cstmt.getORAData(1, Trip.getORADataFactory());
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}

		return result;
	}

	/**
	 * Execute the "joinTrip" procedure
	 * @return The procedure result, if any
	 */
	public TripOffer joinTrip(TripOffer trip, User passenger, int nSeats) {
		OracleCallableStatement cstmt = null;
		TripOffer result = null;

		try {
			String sql92Style = "{ call callable_statements.join_trip(?, ?, ?) }";
			// Create the CallableStatement object
			cstmt = (OracleCallableStatement) conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(1, trip, TripOffer._SQL_TYPECODE);
			cstmt.setObject(2, passenger, User._SQL_TYPECODE);
			cstmt.setInt(3, nSeats);
			// Register output parameters
			cstmt.registerOutParameter(1, TripOffer._SQL_TYPECODE,
					TripOffer._SQL_NAME);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = (TripOffer) cstmt.getORAData(1,
					TripOffer.getORADataFactory());
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}

		return result;
	}

	/**
	 * Execute the "getUserTrips" function
	 * @return The query result
	 */
	public ResultSet getUserTrips(User usr) {
		CallableStatement cstmt = null;
		ResultSet result = null;

		try {
			String sql92Style = "{ ? = call callable_statements.get_user_trips(?) }";
			// Create the CallableStatement object
			cstmt = conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(2, usr, User._SQL_TYPECODE);
			// Register output parameters
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = (ResultSet) cstmt.getObject(1);
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}

		return result;
	}

	/**
	 * Execute the "getUserTrips" function and store its results in a List
	 * @return The query result, stored in a List structure
	 */
	public List<Trip> getUserTripsList(User usr) {
		return resultSetToList(getUserTrips(usr), Trip.class);
	}

	/**
	 * Execute the "getPassengerTrips" function
	 * @return The query result
	 */
	public ResultSet getPassengerTrips(User usr) {
		CallableStatement cstmt = null;
		ResultSet result = null;

		try {
			String sql92Style = "{ ? = call callable_statements.get_passenger_trips(?) }";
			// Create the CallableStatement object
			cstmt = conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(2, usr, User._SQL_TYPECODE);
			// Register output parameters
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = (ResultSet) cstmt.getObject(1);
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}

		return result;
	}

	/**
	 * Execute the "getPassengerTrips" function and store its results in a List
	 * @return The query result, stored in a List structure
	 */
	public List<TripOffer> getPassengerTripsList(User usr) {
		return resultSetToList(getPassengerTrips(usr), TripOffer.class);
	}

	/**
	 * Execute the "calculatePriceEstimation" function
	 * @return The function result
	 */
	public double calculatePriceEstimation(Fuel fuel, int distance) {
		CallableStatement cstmt = null;
		double result = 0.;

		try {
			String sql92Style = "{ ? = call callable_statements.calculate_price_estimation(?, ?) }";
			// Create the CallableStatement object
			cstmt = conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setString(1, fuel.name());
			cstmt.setInt(3, distance);
			// Register output parameters
			cstmt.registerOutParameter(1, OracleTypes.DOUBLE, 6);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = cstmt.getDouble(1);
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}

		return result;
	}

	/**
	 * Execute the "newTripOffer" procedure
	 * @return The procedure result, if any
	 */
	public void newTripOffer(TripOffer tOffer) {
		CallableStatement cstmt = null;

		try {
			String sql92Style = "{ call callable_statements.new_trip_offer(?) }";
			// Create the CallableStatement object
			cstmt = conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(1, tOffer, TripOffer._SQL_TYPECODE);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}
	}

	/**
	 * Execute the "newTripRequest" procedure
	 * @return The procedure result, if any
	 */
	public void newTripRequest(TripRequest tRequest) {
		CallableStatement cstmt = null;

		try {
			String sql92Style = "{ call callable_statements.new_trip_request(?) }";
			// Create the CallableStatement object
			cstmt = conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(1, tRequest, TripRequest._SQL_TYPECODE);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}
	}

	/**
	 * Execute the "getMessageTalksSpeakers" function
	 * @return The query result
	 */
	public ResultSet getMessageTalksSpeakers(User usr) {
		CallableStatement cstmt = null;
		ResultSet result = null;

		try {
			String sql92Style = "{ ? = call callable_statements.get_message_talks_speakers(?) }";
			// Create the CallableStatement object
			cstmt = conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(2, usr, User._SQL_TYPECODE);
			// Register output parameters
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = (ResultSet) cstmt.getObject(1);
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}

		return result;
	}

	/**
	 * Execute the "getMessageTalksSpeakers" function and store its results in a List
	 * @return The query result, stored in a List structure
	 */
	public List<User> getMessageTalksSpeakersList(User usr) {
		return resultSetToList(getMessageTalksSpeakers(usr), User.class);
	}

	/**
	 * Execute the "getMessageTalks" function
	 * @return The query result
	 */
	public ResultSet getMessageTalks(User usr1, User usr2) {
		CallableStatement cstmt = null;
		ResultSet result = null;

		try {
			String sql92Style = "{ ? = call callable_statements.get_message_talks(?, ?) }";
			// Create the CallableStatement object
			cstmt = conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(2, usr1, User._SQL_TYPECODE);
			cstmt.setObject(3, usr2, User._SQL_TYPECODE);
			// Register output parameters
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = (ResultSet) cstmt.getObject(1);
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}

		return result;
	}

	/**
	 * Execute the "getMessageTalksList" function and store its results in a List
	 * @return The query result, stored in a List structure
	 */
	public List<Message> getMessageTalksList(User usr1, User usr2) {
		return resultSetToList(getMessageTalks(usr1, usr2), Message.class);
	}

	/**
	 * Execute the "newMessage" procedure
	 * @return The procedure result, if any
	 */
	public Message newMessage(User usr1, User usr2, String message) {
		OracleCallableStatement cstmt = null;
		Message result = null;

		try {
			String sql92Style = "{ call callable_statements.new_message(?, ?, ?, ?) }";
			// Create the CallableStatement object
			cstmt = (OracleCallableStatement) conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(1, usr1, User._SQL_TYPECODE);
			cstmt.setObject(2, usr2, User._SQL_TYPECODE);
			cstmt.setString(3, message);
			// Register output parameters
			cstmt.registerOutParameter(4, Message._SQL_TYPECODE,
					Message._SQL_NAME);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = (Message) cstmt.getORAData(4, Message.getORADataFactory());
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}

		return result;
	}

	/**
	 * Execute the "getUserActivity" function
	 * @return The query result
	 */
	public ResultSet getUserActivity(User usr) {
		CallableStatement cstmt = null;
		ResultSet result = null;

		try {
			String sql92Style = "{ ? = call callable_statements.get_user_activity(?) }";
			// Create the CallableStatement object
			cstmt = conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(2, usr, User._SQL_TYPECODE);
			// Register output parameters
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = (ResultSet) cstmt.getObject(1);
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}

		return result;
	}

	/**
	 * Execute the "getUserActivityList" function and store its results in a List
	 * @return The query result, stored in a List structure
	 */
	public List<UserActivity> getUserActivityList(User usr) {
		return resultSetToList(getUserActivity(usr), UserActivity.class);
	}

	/**
	 * Execute the "getUserFromEmail" function
	 * @return The function result
	 */
	public User getUserFromEmail(String email) {
		OracleCallableStatement cstmt = null;
		User result = null;

		try {
			String sql92Style = "{ ? = call callable_statements.get_user_from_email(?) }";
			// Create the CallableStatement object
			cstmt = (OracleCallableStatement) conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setString(2, email);
			// Register output parameters
			cstmt.registerOutParameter(1,
					uclm.esi.cardroid.data.oracle.User._SQL_TYPECODE,
					uclm.esi.cardroid.data.oracle.User._SQL_NAME);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = (User) cstmt.getORAData(1, User.getORADataFactory());
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}

		return result;
	}

	/**
	 * Execute the "getCarFromPlate" function
	 * @return The function result
	 */
	public Car getCarFromPlate(String plate, User owner) {
		OracleCallableStatement cstmt = null;
		Car result = null;

		try {
			String sql92Style = "{ ? = call callable_statements.get_car_from_plate(?, ?) }";
			// Create the CallableStatement object
			cstmt = (OracleCallableStatement) conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setString(2, plate);
			cstmt.setObject(3, owner, User._SQL_TYPECODE);
			// Register output parameters
			cstmt.registerOutParameter(1, Car._SQL_TYPECODE, Car._SQL_NAME);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = (Car) cstmt.getORAData(1, Car.getORADataFactory());
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}

		return result;
	}

	/**
	 * Execute the "getCarFromPlate" function
	 * @return The function result
	 */
	public Car getCarFromPlate(String plate, String ownerEmail) {
		OracleCallableStatement cstmt = null;
		Car result = null;

		try {
			String sql92Style = "{ ? = call callable_statements.get_car_from_plate(?, ?) }";
			// Create the CallableStatement object
			cstmt = (OracleCallableStatement) conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setString(2, plate);
			cstmt.setString(3, ownerEmail);
			// Register output parameters
			cstmt.registerOutParameter(1, Car._SQL_TYPECODE, Car._SQL_NAME);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = (Car) cstmt.getORAData(1, Car.getORADataFactory());
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}

		return result;
	}

	/**
	 * Execute the "updateUserData" procedure
	 * @return The procedure result, if any
	 */
	public void updateUserData(User usr) {
		CallableStatement cstmt = null;
		try {
			String sql92Style = "{ call callable_statements.update_user_data(?) }";
			// Create the CallableStatement object
			cstmt = conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(1, usr, User._SQL_TYPECODE);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}
	}

	/**
	 * Execute the "updateCarData" procedure
	 * @return The procedure result, if any
	 */
	public void updateCarData(Car car, User usr) {
		CallableStatement cstmt = null;

		try {
			String sql92Style = "{ call callable_statements.update_car_data(?, ?) }";
			// Create the CallableStatement object
			cstmt = conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(1, car, Car._SQL_TYPECODE);
			cstmt.setObject(2, usr, User._SQL_TYPECODE);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}
	}

	/**
	 * Execute the "updateCarData" procedure
	 * @return The procedure result, if any
	 */
	public void updateCarData(Car car, String usrEmail) {
		CallableStatement cstmt = null;

		try {
			String sql92Style = "{ call callable_statements.update_car_data(?, ?) }";
			// Create the CallableStatement object
			cstmt = conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(1, car, Car._SQL_TYPECODE);
			cstmt.setString(2, usrEmail);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}
	}

	/**
	 * Execute the "addCar" procedure
	 * @return The procedure result, if any
	 */
	public User addCar(Car car, User usr) {
		OracleCallableStatement cstmt = null;
		User result = null;

		try {
			String sql92Style = "{ call callable_statements.add_car(?, ?) }";
			// Create the CallableStatement object
			cstmt = (OracleCallableStatement) conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(1, car, Car._SQL_TYPECODE);
			cstmt.setObject(2, usr, User._SQL_TYPECODE);
			// Register output parameters
			cstmt.registerOutParameter(2, User._SQL_TYPECODE, User._SQL_NAME);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = (User) cstmt.getORAData(2, User.getORADataFactory());
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}

		return result;
	}

	/**
	 * Execute the "addCar" procedure
	 * @return The procedure result, if any
	 */
	public void addCar(Car car, String usrEmail) {
		CallableStatement cstmt = null;

		try {
			String sql92Style = "{ call callable_statements.add_car(?, ?) }";
			// Create the CallableStatement object
			cstmt = conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(1, car, Car._SQL_TYPECODE);
			cstmt.setString(2, usrEmail);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}
	}

	/**
	 * Execute the "login" function
	 * @return The function result
	 */
	public int login(String email, String password) {
		CallableStatement cstmt = null;
		int result = -1;

		try {
			String sql92Style = "{ ? = call callable_statements.login(?, ?) }";
			// Create the CallableStatement object
			cstmt = conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setString(2, email);
			cstmt.setString(3, password);
			// Register output parameters
			cstmt.registerOutParameter(1, OracleTypes.INTEGER);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = cstmt.getInt(1);
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}

		return result;
	}

	/**
	 * Execute the "register" procedure
	 * @return The procedure result, if any
	 */
	public int register(User usr, String password) {
		CallableStatement cstmt = null;
		int result = -1;

		try {
			String sql92Style = "{ call callable_statements.register(?, ?, ?, ?) }";
			// Create the CallableStatement object
			cstmt = conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(1, usr, User._SQL_TYPECODE);
			cstmt.setString(2, password);
			// Register output parameters
			cstmt.registerOutParameter(1, User._SQL_TYPECODE, User._SQL_NAME);
			cstmt.registerOutParameter(3, OracleTypes.INTEGER);
			cstmt.registerOutParameter(4, OracleTypes.CHAR);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = cstmt.getInt(3);
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}

		return result;
	}

	/**
	 * Extract the results contained in a ResultSet and store them in a List
	 * @param rset		ResultSet containing some query's result data
	 * @param datatype	The class the data contained in rset must be casted to
	 * @return 			List structure containing the data kept by rset
	 */
	public static <C> List<C> resultSetToList(ResultSet rset, Class<C> datatype) {
		List<C> rlist = null;
		try {
			rlist = new ArrayList<C>();
			while (rset.next()) {
				rlist.add(rset.getObject(0, datatype));
			}
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(rset);
		}
		return rlist;
	}

	/**
	 * Setup the type map for the database Connection held by this class, 
	 * so that JDBC can translate the database user defined complex types to 
	 * the corresponding classes implementing them
	 */
	private void setupTypeMap() {
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
