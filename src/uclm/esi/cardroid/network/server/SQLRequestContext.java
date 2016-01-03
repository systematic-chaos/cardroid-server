package uclm.esi.cardroid.network.server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORADataFactory;
import uclm.esi.cardroid.QueryResultPrx;
import uclm.esi.cardroid.QueryResultPrxHelper;
import uclm.esi.cardroid.QueryResultPrxHolder;
import uclm.esi.cardroid.ResultSeqHolder;
import uclm.esi.cardroid.data.zerocice.CarTypPrx;
import uclm.esi.cardroid.data.zerocice.MessageTypPrx;
import uclm.esi.cardroid.data.zerocice.TripOfferTypPrx;
import uclm.esi.cardroid.data.zerocice.TripRequestTypPrx;
import uclm.esi.cardroid.data.zerocice.TripTypPrx;
import uclm.esi.cardroid.data.zerocice.UserActivityTypPrx;
import uclm.esi.cardroid.data.zerocice.UserActivityTypPrxHolder;
import uclm.esi.cardroid.data.zerocice.UserTypPrx;
import uclm.esi.cardroid.jdbc.JDBCUtil;
import uclm.esi.cardroid.network.zerocice.CardroidEventStormPrx;
import uclm.esi.cardroid.network.zerocice.CardroidEventStormPrxHelper;
import uclm.esi.cardroid.zerocice.JDBCException;
import uclm.esi.cardroid.zerocice._CardroidManagerOperations;
import Ice.Current;
import Ice.IntHolder;
import Ice.Logger;
import Ice.ObjectPrx;
import IceStorm.NoSuchTopic;
import IceStorm.TopicManagerPrx;
import IceStorm.TopicPrx;

/**
 * \class SQLRequestContext
 * 
 * A SQL request context encapsulates SQL resources allocated in the
 * process of executing a request, such as the database connection,
 * and associated SQL statements.
 * 
 * The request context is automatically destroyed at the end of a
 * request.
 * 
 * When the request context is destroyed, the transaction is either
 * automatically commited or rolled back, depending whether the
 * request executed successfully.
 */
public class SQLRequestContext implements _CardroidManagerOperations {
	// A map of threads to request contexts
	private static Map<Thread, SQLRequestContext> _contextMap = Collections
			.synchronizedMap(new HashMap<Thread, SQLRequestContext>());

	private static Logger _logger = null;
	private static ConnectionPool _pool = null;
	private static TopicManagerPrx _topicManager = null;

	private boolean _trace = true;
	private List<Statement> _statements = new LinkedList<Statement>();
	private Connection _conn;
	private QueryResultListener _queryResultListener;

	/**
	 * Default constructor.
	 * Every instance of SQLRequestContext will hold a single database 
	 * connection. Initialization of SQLRequestContext must take place
	 * in the static method initialize, since every instance of 
	 * SQLRequestContext will share static resources
	 */
	public SQLRequestContext() {
		_conn = _pool.acquire();

		if (_trace) {
			_logger.trace("SQLRequestContext", "create new context: " + this
					+ "thread: " + Thread.currentThread() + ": connection: "
					+ _conn);
		}
		_contextMap.put(Thread.currentThread(), this);
	}

	/**
	 * Set the object whose implementation will be called upon the completion 
	 * of a query
	 * @param listener The new listener for the processing of queries results
	 */
	public void setQueryResultListener(QueryResultListener listener) {
		_queryResultListener = listener;
	}

	/**
	 * Called only during the dispatch process.
	 * Commits or rolls back the transaction performed and destroys this 
	 * SQLRequestContext, previously releasing all the resources it keeps
	 * @param commit Whether the transaction performed will be commited or 
	 * 					rolled back
	 */
	public void destroyFromDispatch(boolean commit) {
		// Remove the currentEntry context from the thread -> context map
		SQLRequestContext context = _contextMap.remove(Thread.currentThread());
		assert context != null;
		destroyInternal(commit);
	}

	/**
	 * Commit or roll back the transaction performed.
	 * Release all the resources kept by this SQLRequestContext
	 * @param commit Whether the transaction performed will be commited or 
	 * 					rolled back
	 */
	private void destroyInternal(boolean commit) {
		// Release all resources
		try {
			if (commit) {
				_conn.commit();
				if (_trace) {
					_logger.trace("SQLRequestContext", "commit context: "
							+ this);
				}
			} else {
				_conn.rollback();
				if (_trace) {
					_logger.trace("SQLRequestContext", "rollback context: "
							+ this);
				}
			}

			for (Statement p : _statements) {
				p.close();
			}
		} catch (SQLException e) {
			error("SQLRequestContext", e);
		}

		_pool.release(_conn);

		_statements.clear();
		_conn = null;
	}

	/**
	 * @return The instance of SQLRequestContext corresponding to the current
	 * 			execution thread
	 */
	public static SQLRequestContext getCurrentContext() {
		SQLRequestContext currentContext = _contextMap.get(Thread
				.currentThread());
		return currentContext;
	}

	/**
	 * Initialize the static resources shared by every instance of 
	 * SQLRequestContext
	 * @param logger		The logger used to post event or error messages
	 * @param pool			Reference to the pool of database connections
	 * @param topicManager	Proxy to the IceStorm topic manager
	 */
	public static void initialize(Logger logger, ConnectionPool pool,
			TopicManagerPrx topicManager) {
		assert _logger == null;
		assert _pool == null;
		assert _topicManager == null;

		_logger = logger;
		_pool = pool;
		_topicManager = topicManager;
	}

	public PreparedStatement prepareStatement(String sql) throws SQLException {
		PreparedStatement stmt = _conn.prepareStatement(sql);
		_statements.add(stmt);
		return stmt;
	}

	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
			throws SQLException {
		PreparedStatement stmt = _conn.prepareStatement(sql, autoGeneratedKeys);
		_statements.add(stmt);
		return stmt;
	}

	/**
	 * Called only during the dispatch process.
	 * Commits or rolls back the transaction performed and destroys this 
	 * SQLRequestContext, previously releasing all the resources it keeps
	 * @param commit Whether the transaction performed will be commited or 
	 * 					rolled back
	 */
	public void destroy(boolean commit) {
		destroyInternal(commit);
	}

	/**
	 * Log an exception
	 * @param prefix	The prefix to be printed before the exception
	 * @param ex		The exception message
	 */
	public void error(String prefix, Exception ex) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		pw.flush();
		_logger.error(prefix + ": error:\n" + sw.toString());
	}

	/**
	 * Execute a SQL statement on the connection held by this class
	 * @param sql92Style	SQL statatement, according to the SQL 92 standard
	 * 							specification
	 * @param params		Parameters to be binded as arguments in the  
	 * 							statement to be executed
	 * @return				Whether the statement was successfully executed
	 * @throws SQLException
	 */
	public boolean executeStatement(String sql92Style, Object... params)
			throws SQLException {
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			pstmt = _conn.prepareStatement(sql92Style);

			for (int n = 0; n < params.length; n++) {
				pstmt.setObject(n + 1, params[n]);
			}

			result = pstmt.execute();
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
	 * @param params		Map of the parameters to be binded as arguments in 
	 * 							the statement to be executed. For every Map 
	 * 							Entry, the key is the parameter itself, as the 
	 * 							value is its SQL type code
	 * @return				Whether the statement was successfully executed
	 * @throws SQLException
	 */
	public boolean executeStatement(String sql92Style,
			Map<Object, Integer> params) throws SQLException {
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			pstmt = _conn.prepareStatement(sql92Style);

			Iterator<Entry<Object, Integer>> paramsIter = params.entrySet()
					.iterator();
			int pos = 1;
			while (paramsIter.hasNext()) {
				Entry<Object, Integer> paramEntry = paramsIter.next();
				pstmt.setObject(pos++, paramEntry.getKey(),
						paramEntry.getValue());
			}

			result = pstmt.execute();
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
			pstmt = _conn.prepareStatement(sql92Style);
			pstmt.setFetchSize(rowsFetched);

			for (int n = 0; n < params.length; n++) {
				pstmt.setObject(n + 1, params[n]);
			}

			result = pstmt.executeQuery();
		} catch (SQLException e) {
			// Print stack trace
			JDBCUtil.printException(e);
			// Release JDBC resources
			JDBCUtil.close(result, pstmt);
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
			pstmt = _conn.prepareStatement(sql92Style);
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
			// Release JDBC resources
			JDBCUtil.close(result, pstmt);
		}

		return result;
	}

	/**
	 * Execute a SQL update statement on the connection held by this class
	 * @param sql92Style	SQL statatement, according to the SQL 92 standard
	 * 							specification
	 * @param params		Parameters to be binded as arguments in the  
	 * 							statement to be executed
	 * @return				The number of rows affected by this statement 
	 * 							execution
	 * @throws SQLException
	 */
	public int executeStatementUpdate(String sql92Style, Object... params)
			throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			pstmt = _conn.prepareStatement(sql92Style);

			for (int n = 0; n < params.length; n++) {
				pstmt.setObject(n + 1, params[n]);
			}

			result = pstmt.executeUpdate();
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
	 * @param params		Map of the parameters to be binded as arguments in 
	 * 							the statement to be executed. For every Map 
	 * 							Entry, the key is the parameter itself, as the 
	 * 							value is its SQL type code
	 * @return				The number of rows affected by this statement 
	 * 							execution
	 * @throws SQLException
	 */
	public int executeStatementUpdate(String sql92Style,
			Map<Object, Integer> params) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			pstmt = _conn.prepareStatement(sql92Style);

			Iterator<Entry<Object, Integer>> paramsIter = params.entrySet()
					.iterator();
			int pos = 1;
			while (paramsIter.hasNext()) {
				Entry<Object, Integer> paramEntry = paramsIter.next();
				pstmt.setObject(pos++, paramEntry.getKey(),
						paramEntry.getValue());
			}

			result = pstmt.executeUpdate();
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
	 * @param params		Parameters to be binded as arguments in the  
	 * 							statement to be executed
	 * @return				Whether the stored procedure commanded was  
	 * 							successfully called and executed
	 * @throws SQLException
	 */
	public boolean executeProcedure(String sql92Style, Object... params)
			throws SQLException {
		CallableStatement cstmt = null;
		boolean result = false;

		try {
			cstmt = _conn.prepareCall(sql92Style);

			for (int n = 0; n < params.length; n++) {
				cstmt.setObject(n + 1, params[n]);
			}

			result = cstmt.execute();
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
	 * @param params		Map of the parameters to be binded as arguments in 
	 * 							the statement to be executed. For every Map 
	 * 							Entry, the key is the parameter itself, as the 
	 * 							value is its SQL type code
	 * @return				Whether the stored procedure commanded was  
	 * 							successfully called and executed
	 * @throws SQLException
	 */
	public boolean executeProcedure(String sql92Style,
			Map<Object, Integer> params) {
		CallableStatement cstmt = null;
		boolean result;

		try {
			cstmt = _conn.prepareCall(sql92Style);

			Iterator<Entry<Object, Integer>> paramsIter = params.entrySet()
					.iterator();
			int pos = 1;
			while (paramsIter.hasNext()) {
				Entry<Object, Integer> paramEntry = paramsIter.next();
				cstmt.setObject(pos++, paramEntry.getKey(),
						paramEntry.getValue());
			}
			result = cstmt.execute();
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
			cstmt = _conn.prepareCall(sql92Style);

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
			cstmt = _conn.prepareCall(sql92Style);

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
	
	@SuppressWarnings("unchecked")
	public <C> C executeFunction(String sql92Style, int resultSqlType,
			String resultTypeName, ORADataFactory resultTypeFactory,
			Object... params) {
		OracleCallableStatement cstmt = null;
		C result = null;

		try {
			cstmt = (OracleCallableStatement) _conn.prepareCall(sql92Style);

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
			cstmt = (OracleCallableStatement) _conn.prepareCall(sql92Style);

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
	public void getUserPlaces(UserTypPrx usr, int n, ResultSeqHolder first,
			IntHolder nrows, QueryResultPrxHolder result, Current current) {
		ResultSet rs = getUserPlaces(
				new uclm.esi.cardroid.data.oracle.User().newInstance(uclm.esi.cardroid.data.ice.User
						.extractObject(usr)), current);
		Statement stmt = null;

		try {
			boolean next = rs.next();
			assert next;
			stmt = rs.getStatement();

			first.value = new LinkedList<ObjectPrx>();
			for (int i = 0; i < n && next; ++i) {
				first.value.add(uclm.esi.cardroid.data.ice.Place.createProxy(
						rs.getObject(1,
								uclm.esi.cardroid.data.oracle.Place.class),
						current.adapter));
				next = rs.next();
			}
			if (next) {
				QueryResultI impl = new QueryResultI(this, rs, current.adapter,
						QueryType.GET_USER_PLACES);
				result.value = QueryResultPrxHelper
						.uncheckedCast(current.adapter.addWithUUID(impl));
				if (_queryResultListener != null)
					_queryResultListener.add(result.value, impl,
							QueryType.GET_USER_PLACES);
			}
		} catch (SQLException e) {
			JDBCException ex = new JDBCException();
			ex.initCause(e);
			throw ex;
		} finally {
			// Release JDBC resources in finally clause
			JDBCUtil.close(rs, stmt);
		}
	}

	/**
	 * Execute the "getUserPlaces" function
	 * @return The query result
	 */
	public ResultSet getUserPlaces(uclm.esi.cardroid.data.oracle.User usr,
			Current current) {
		CallableStatement cstmt = null;
		ResultSet result = null;

		try {
			String sql92Style = "{ ? = call callable_statements.get_user_places(?) }";
			// Create the CallableStatement object
			cstmt = _conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(2, usr,
					uclm.esi.cardroid.data.oracle.User._SQL_TYPECODE);
			// Register output parameters
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = (ResultSet) cstmt.getObject(1);
		} catch (SQLException e) {
			JDBCUtil.printException(e);
			JDBCUtil.close(result, cstmt);
		}

		return result;
	}

	/**
	 * Execute the "getUserPlaces" function and store its results in a List
	 * @return The query result, stored in a List structure
	 */
	public List<uclm.esi.cardroid.data.oracle.Place> getUserPlacesList(
			uclm.esi.cardroid.data.oracle.User usr, Current current) {
		return resultSetToList(getUserPlaces(usr, current),
				uclm.esi.cardroid.data.oracle.Place.class);
	}

	/**
	 * Execute the "searchTrips" function
	 * @return The query result
	 */
	public void searchTrips(
			uclm.esi.cardroid.data.zerocice.TripRequestTyp tRequest, int n,
			ResultSeqHolder first, IntHolder nrows,
			QueryResultPrxHolder result, Current current) {
		ResultSet rs = searchTrips(
				new uclm.esi.cardroid.data.oracle.TripRequest().newInstance(new uclm.esi.cardroid.data.ice.TripRequest(
						tRequest)), current);
		Statement stmt = null;

		try {
			boolean next = rs.next();
			assert next;
			stmt = rs.getStatement();

			first.value = new LinkedList<ObjectPrx>();
			for (int i = 0; i < n && next; ++i) {
				first.value.add(uclm.esi.cardroid.data.ice.Trip.createProxy(
						rs.getObject(1,
								uclm.esi.cardroid.data.oracle.Trip.class),
						current.adapter));
				next = rs.next();
			}
			if (next) {
				QueryResultI impl = new QueryResultI(this, rs, current.adapter,
						QueryType.SEARCH_TRIPS);
				result.value = QueryResultPrxHelper
						.uncheckedCast(current.adapter.addWithUUID(impl));
				if (_queryResultListener != null)
					_queryResultListener.add(result.value, impl,
							QueryType.SEARCH_TRIPS);
			}
		} catch (SQLException e) {
			JDBCException ex = new JDBCException();
			ex.initCause(e);
			throw ex;
		} finally {
			// Release JDBC resources in finally clause
			JDBCUtil.close(rs, stmt);
		}
	}

	/**
	 * Execute the "searchTrips" function
	 * @return The query result
	 */
	public ResultSet searchTrips(
			uclm.esi.cardroid.data.oracle.TripRequest tRequest, Current current) {
		CallableStatement cstmt = null;
		ResultSet result = null;

		try {
			String sql92Style = "{ ? = call callable_statements.search_trips(?) }";
			// Create the CallableStatement object
			cstmt = _conn.prepareCall(sql92Style);
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
			JDBCUtil.close(result, cstmt);
		}

		return result;
	}

	/**
	 * Execute the "searchTrips" function and store its results in a List
	 * @return The query result, stored in a List structure
	 */
	public List<uclm.esi.cardroid.data.oracle.Trip> searchTripsList(
			uclm.esi.cardroid.data.oracle.TripRequest tRequest, Current current) {
		return resultSetToList(searchTrips(tRequest, current),
				uclm.esi.cardroid.data.oracle.Trip.class);
	}

	/**
	 * Execute the "getTripFromId" function
	 * @return The function result
	 */
	public TripTypPrx getTripFromId(int tripId, Current current) {
		OracleCallableStatement cstmt = null;
		TripTypPrx result = null;

		try {
			String sql92Style = "{ ? = call callable_statements.get_trip_from_id(?) }";
			// Create the CallableStatement object
			cstmt = (OracleCallableStatement) _conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setInt(2, tripId);
			// Register output parameters
			cstmt.registerOutParameter(1,
					uclm.esi.cardroid.data.oracle.Trip._SQL_TYPECODE,
					uclm.esi.cardroid.data.oracle.Trip._SQL_NAME);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = uclm.esi.cardroid.data.ice.Trip.createProxy(
					((uclm.esi.cardroid.data.oracle.Trip) cstmt.getORAData(1,
							uclm.esi.cardroid.data.oracle.Trip
									.getORADataFactory())), current.adapter);
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}

		return result;
	}

	/**
	 * Execute the "getTripOfferFromId" function
	 * @return The function result
	 */
	public TripOfferTypPrx getTripOfferFromId(int tripId, Current current) {
		OracleCallableStatement cstmt = null;
		TripOfferTypPrx result = null;

		try {
			String sql92Style = "{ ? = call callable_statements.get_trip_offer_from_id(?) }";
			// Create the CallableStatement object
			cstmt = (OracleCallableStatement) _conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setInt(2, tripId);
			// Register output parameters
			cstmt.registerOutParameter(1,
					uclm.esi.cardroid.data.oracle.TripOffer._SQL_TYPECODE,
					uclm.esi.cardroid.data.oracle.TripOffer._SQL_NAME);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = uclm.esi.cardroid.data.ice.TripOffer.createProxy(
					((uclm.esi.cardroid.data.oracle.TripOffer) cstmt
							.getORAData(1,
									uclm.esi.cardroid.data.oracle.TripOffer
											.getORADataFactory())),
					current.adapter);
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}

		return result;
	}

	/**
	 * Execute the "getTripRequestFromId" function
	 * @return The function result
	 */
	public TripRequestTypPrx getTripRequestFromId(int tripId, Current current) {
		OracleCallableStatement cstmt = null;
		TripRequestTypPrx result = null;

		try {
			String sql92Style = "{ ? = call callable_statements.get_trip_request_from_id(?) }";
			// Create the CallableStatement object
			cstmt = (OracleCallableStatement) _conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setInt(2, tripId);
			// Register output parameteres
			cstmt.registerOutParameter(1,
					uclm.esi.cardroid.data.oracle.TripRequest._SQL_TYPECODE,
					uclm.esi.cardroid.data.oracle.TripRequest._SQL_NAME);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = uclm.esi.cardroid.data.ice.TripRequest.createProxy(
					((uclm.esi.cardroid.data.oracle.TripRequest) cstmt
							.getORAData(1,
									uclm.esi.cardroid.data.oracle.TripRequest
											.getORADataFactory())),
					current.adapter);
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
	public void joinTrip(TripOfferTypPrx trip, UserTypPrx passenger,
			int nSeats, Current current) {
		UserActivityTypPrxHolder activity = new UserActivityTypPrxHolder();
		joinTrip(
				new uclm.esi.cardroid.data.oracle.TripOffer().newInstance(uclm.esi.cardroid.data.ice.TripOffer
						.extractObject(trip)),
				new uclm.esi.cardroid.data.oracle.User()
						.newInstance(uclm.esi.cardroid.data.ice.User
								.extractObject(passenger)), nSeats, activity,
				current);
		notifyUserActivity(activity.value);
	}

	/**
	 * Execute the "joinTrip" procedure
	 * @return The procedure result, if any
	 */
	public uclm.esi.cardroid.data.oracle.TripOffer joinTrip(
			uclm.esi.cardroid.data.oracle.TripOffer trip,
			uclm.esi.cardroid.data.oracle.User passenger, int nSeats,
			UserActivityTypPrxHolder activity, Current current) {
		OracleCallableStatement cstmt = null;
		uclm.esi.cardroid.data.oracle.TripOffer result = null;

		try {
			String sql92Style = "{ call callable_statements.join_trip(?, ?, ?, ?) }";
			// Create the CallableStatement object
			cstmt = (OracleCallableStatement) _conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(1, trip,
					uclm.esi.cardroid.data.oracle.TripOffer._SQL_TYPECODE);
			cstmt.setObject(2, passenger,
					uclm.esi.cardroid.data.oracle.User._SQL_TYPECODE);
			cstmt.setInt(3, nSeats);
			// Register output parameters
			cstmt.registerOutParameter(1,
					uclm.esi.cardroid.data.oracle.TripOffer._SQL_TYPECODE,
					uclm.esi.cardroid.data.oracle.TripOffer._SQL_NAME);
			cstmt.registerOutParameter(4,
					uclm.esi.cardroid.data.oracle.UserActivity._SQL_TYPECODE,
					uclm.esi.cardroid.data.oracle.UserActivity._SQL_NAME);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = (uclm.esi.cardroid.data.oracle.TripOffer) cstmt
					.getORAData(1, uclm.esi.cardroid.data.oracle.TripOffer
							.getORADataFactory());
			activity.value = uclm.esi.cardroid.data.ice.UserActivity
					.createProxy(
							(uclm.esi.cardroid.data.oracle.UserActivity) cstmt
									.getORAData(
											4,
											uclm.esi.cardroid.data.oracle.UserActivity
													.getORADataFactory()),
							current.adapter);
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}

		return result;
	}

	/**
	 * Execute the "organize" procedure
	 * @return The procedure result, if any
	 */
	public TripOfferTypPrx organizeTrip(TripRequestTypPrx tripRequest,
			uclm.esi.cardroid.data.zerocice.TripOfferTyp tripOffer,
			Current current) {
		UserActivityTypPrxHolder activity = new UserActivityTypPrxHolder();
		TripOfferTypPrx result = uclm.esi.cardroid.data.ice.TripOffer
				.createProxy(
						organizeTrip(
								new uclm.esi.cardroid.data.oracle.TripRequest()
										.newInstance(uclm.esi.cardroid.data.ice.TripRequest
												.extractObject(tripRequest)),
								new uclm.esi.cardroid.data.oracle.TripOffer()
										.newInstance(new uclm.esi.cardroid.data.ice.TripOffer(
												tripOffer)), activity, current),
						current.adapter);
		notifyUserActivity(activity.value);
		return result;
	}

	/**
	 * Execute the "organize" procedure
	 * @return The procedure result, if any
	 */
	public uclm.esi.cardroid.data.oracle.TripOffer organizeTrip(
			uclm.esi.cardroid.data.oracle.TripRequest tripRequest,
			uclm.esi.cardroid.data.oracle.TripOffer tripOffer,
			UserActivityTypPrxHolder activity, Current current) {
		OracleCallableStatement cstmt = null;
		uclm.esi.cardroid.data.oracle.TripOffer result = null;

		try {
			String sql92Style = "{ call callable_statements.organize_trip(?, ?, ?) }";
			// Create the CallableStatement object
			cstmt = (OracleCallableStatement) _conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(1, tripRequest,
					uclm.esi.cardroid.data.oracle.TripRequest._SQL_TYPECODE);
			cstmt.setObject(2, tripOffer,
					uclm.esi.cardroid.data.oracle.TripOffer._SQL_TYPECODE);
			// Register output parameters
			cstmt.registerOutParameter(2,
					uclm.esi.cardroid.data.oracle.TripOffer._SQL_TYPECODE,
					uclm.esi.cardroid.data.oracle.TripOffer._SQL_NAME);
			cstmt.registerOutParameter(3,
					uclm.esi.cardroid.data.oracle.UserActivity._SQL_TYPECODE,
					uclm.esi.cardroid.data.oracle.UserActivity._SQL_NAME);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = (uclm.esi.cardroid.data.oracle.TripOffer) cstmt
					.getORAData(2, uclm.esi.cardroid.data.oracle.TripOffer
							.getORADataFactory());
			activity.value = uclm.esi.cardroid.data.ice.UserActivity
					.createProxy(
							(uclm.esi.cardroid.data.oracle.UserActivity) cstmt
									.getORAData(
											3,
											uclm.esi.cardroid.data.oracle.UserActivity
													.getORADataFactory()),
							current.adapter);
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
	public void getUserTrips(UserTypPrx usr, int n, ResultSeqHolder first,
			IntHolder nrows, QueryResultPrxHolder result, Current current) {
		ResultSet rs = getUserTrips(
				new uclm.esi.cardroid.data.oracle.User().newInstance(uclm.esi.cardroid.data.ice.User
						.extractObject(usr)), current);
		Statement stmt = null;

		try {
			boolean next = rs.next();
			assert next;
			stmt = rs.getStatement();

			first.value = new LinkedList<ObjectPrx>();
			for (int i = 0; i < n && next; ++i) {
				first.value.add(uclm.esi.cardroid.data.ice.Trip.createProxy(
						rs.getObject(1,
								uclm.esi.cardroid.data.oracle.Trip.class),
						current.adapter));
				next = rs.next();
			}
			for (ObjectPrx oprx : first.value)
				System.out.println(((TripTypPrx) oprx)._toString());
			if (next) {
				QueryResultI impl = new QueryResultI(this, rs, current.adapter,
						QueryType.GET_USER_TRIPS);
				result.value = QueryResultPrxHelper
						.uncheckedCast(current.adapter.addWithUUID(impl));
				if (_queryResultListener != null)
					_queryResultListener.add(result.value, impl,
							QueryType.GET_USER_TRIPS);
			}
		} catch (SQLException e) {
			JDBCException ex = new JDBCException();
			ex.initCause(e);
			throw ex;
		} finally {
			// Release JDBC resources in finally clause
			JDBCUtil.close(rs, stmt);
		}
	}

	/**
	 * Execute the "getUserTrips" function
	 * @return The query result
	 */
	public ResultSet getUserTrips(uclm.esi.cardroid.data.oracle.User usr,
			Current current) {
		CallableStatement cstmt = null;
		ResultSet result = null;

		try {
			String sql92Style = "{ ? = call callable_statements.get_user_trips(?) }";
			// Create the CallableStatement object
			cstmt = _conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(2, usr,
					uclm.esi.cardroid.data.oracle.User._SQL_TYPECODE);
			// Register output parameters
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = (ResultSet) cstmt.getObject(1);
		} catch (SQLException e) {
			JDBCUtil.printException(e);
			JDBCUtil.close(result, cstmt);
		}

		return result;
	}

	/**
	 * Execute the "getUserTrips" function and store its results in a List
	 * @return The query result, stored in a List structure
	 */
	public List<uclm.esi.cardroid.data.oracle.Trip> getUserTripsList(
			uclm.esi.cardroid.data.oracle.User usr, Current current) {
		return resultSetToList(getUserTrips(usr, current),
				uclm.esi.cardroid.data.oracle.Trip.class);
	}

	/**
	 * Execute the "getPassengerTrips" function
	 * @return The query result
	 */
	public void getPassengerTrips(UserTypPrx usr, int n, ResultSeqHolder first,
			IntHolder nrows, QueryResultPrxHolder result, Current current) {
		ResultSet rs = getPassengerTrips(
				new uclm.esi.cardroid.data.oracle.User().newInstance(uclm.esi.cardroid.data.ice.User
						.extractObject(usr)), current);
		Statement stmt = null;

		try {
			boolean next = rs.next();
			assert next;
			stmt = rs.getStatement();

			first.value = new LinkedList<ObjectPrx>();
			for (int i = 0; i < n && next; ++i) {
				first.value.add(uclm.esi.cardroid.data.ice.TripOffer
						.createProxy(rs.getObject(1,
								uclm.esi.cardroid.data.oracle.TripOffer.class),
								current.adapter));
				next = rs.next();
			}
			if (next) {
				QueryResultI impl = new QueryResultI(this, rs, current.adapter,
						QueryType.GET_PASSENGER_TRIPS);
				result.value = QueryResultPrxHelper
						.uncheckedCast(current.adapter.addWithUUID(impl));
				if (_queryResultListener != null)
					_queryResultListener.add(result.value, impl,
							QueryType.GET_PASSENGER_TRIPS);
			}
		} catch (SQLException e) {
			JDBCException ex = new JDBCException();
			ex.initCause(e);
			throw ex;
		} finally {
			// Release JDBC resources in finally clause
			JDBCUtil.close(rs, stmt);
		}
	}

	/**
	 * Execute the "getPassengerTrips" function
	 * @return The query result
	 */
	public ResultSet getPassengerTrips(uclm.esi.cardroid.data.oracle.User usr,
			Current current) {
		CallableStatement cstmt = null;
		ResultSet result = null;

		try {
			String sql92Style = "{ ? = call callable_statements.get_passenger_trips(?) }";
			// Create the CallableStatement object
			cstmt = _conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(2, usr,
					uclm.esi.cardroid.data.oracle.User._SQL_TYPECODE);
			// Register output parameters
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = (ResultSet) cstmt.getObject(1);
		} catch (SQLException e) {
			JDBCUtil.printException(e);
			JDBCUtil.close(result, cstmt);
		}

		return result;
	}

	/**
	 * Execute the "getPassengerTrips" function and store its results in a List
	 * @return The query result, stored in a List structure
	 */
	public List<uclm.esi.cardroid.data.oracle.TripOffer> getPassengerTripsList(
			uclm.esi.cardroid.data.oracle.User usr, Current current) {
		return resultSetToList(getPassengerTrips(usr, current),
				uclm.esi.cardroid.data.oracle.TripOffer.class);
	}

	/**
	 * Execute the "calculatePriceEstimation" function
	 * @return The function result
	 */
	public double calculatePriceEstimation(
			uclm.esi.cardroid.data.zerocice.Fuel fuel, int distance,
			Current current) {
		CallableStatement cstmt = null;
		double result = 0.;

		try {
			String sql92Style = "{ ? = call callable_statements.calculate_price_estimation(?, ?) }";
			// Create the CallableStatement object
			cstmt = _conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setString(2, fuel.name());
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
	public TripOfferTypPrx newTripOffer(
			uclm.esi.cardroid.data.zerocice.TripOfferTyp tOffer, Current current) {
		OracleCallableStatement cstmt = null;
		TripOfferTypPrx result = null;

		try {
			String sql92Style = "{ call callable_statements.new_trip_offer(?) }";
			// Create the CallableStatement object
			cstmt = (OracleCallableStatement) _conn.prepareCall(sql92Style);
			// Bind input parameters
			uclm.esi.cardroid.data.oracle.TripOffer toffer = new uclm.esi.cardroid.data.oracle.TripOffer()
					.newInstance((uclm.esi.cardroid.data.ice.TripOffer) tOffer);
			cstmt.setObject(1, toffer,
					uclm.esi.cardroid.data.oracle.TripOffer._SQL_TYPECODE);
			// Register output parameters
			cstmt.registerOutParameter(1,
					uclm.esi.cardroid.data.oracle.TripOffer._SQL_TYPECODE,
					uclm.esi.cardroid.data.oracle.TripOffer._SQL_NAME);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = uclm.esi.cardroid.data.ice.TripOffer.createProxy(
					(uclm.esi.cardroid.data.oracle.TripOffer) cstmt.getORAData(
							1, uclm.esi.cardroid.data.oracle.TripOffer
									.getORADataFactory()), current.adapter);
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}

		return result;
	}

	/**
	 * Execute the "newTripRequest" procedure
	 * @return The procedure result, if any
	 */
	public TripRequestTypPrx newTripRequest(
			uclm.esi.cardroid.data.zerocice.TripRequestTyp tRequest,
			Current current) {
		OracleCallableStatement cstmt = null;
		TripRequestTypPrx result = null;

		try {
			String sql92Style = "{ call callable_statements.new_trip_request(?) }";
			// Create the CallableStatement object
			cstmt = (OracleCallableStatement) _conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(
					1,
					new uclm.esi.cardroid.data.oracle.TripRequest()
							.newInstance((uclm.esi.cardroid.data.ice.TripRequest) tRequest),
					uclm.esi.cardroid.data.oracle.TripRequest._SQL_TYPECODE);
			// Register output parameters
			cstmt.registerOutParameter(1,
					uclm.esi.cardroid.data.oracle.TripRequest._SQL_TYPECODE,
					uclm.esi.cardroid.data.oracle.TripRequest._SQL_NAME);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = uclm.esi.cardroid.data.ice.TripRequest.createProxy(
					(uclm.esi.cardroid.data.oracle.TripRequest) cstmt
							.getORAData(1,
									uclm.esi.cardroid.data.oracle.TripRequest
											.getORADataFactory()),
					current.adapter);
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}

		return result;
	}

	/**
	 * Execute the "getMessageTalksSpeakers" function
	 * @return The query result
	 */
	public void getMessageTalksSpeakers(UserTypPrx usr, int n,
			ResultSeqHolder first, IntHolder nrows,
			QueryResultPrxHolder result, Current current) {
		ResultSet rs = getMessageTalksSpeakers(
				new uclm.esi.cardroid.data.oracle.User().newInstance(uclm.esi.cardroid.data.ice.User
						.extractObject(usr)), current);
		Statement stmt = null;

		try {
			boolean next = rs.next();
			assert next;
			stmt = rs.getStatement();

			first.value = new LinkedList<ObjectPrx>();
			for (int i = 0; i < n && next; ++i) {
				first.value.add(uclm.esi.cardroid.data.ice.User.createProxy(
						rs.getObject(1,
								uclm.esi.cardroid.data.oracle.User.class),
						current.adapter));
				next = rs.next();
			}
			if (next) {
				QueryResultI impl = new QueryResultI(this, rs, current.adapter,
						QueryType.GET_MESSAGE_TALKS_SPEAKERS);
				result.value = QueryResultPrxHelper
						.uncheckedCast(current.adapter.addWithUUID(impl));
				if (_queryResultListener != null)
					_queryResultListener.add(result.value, impl,
							QueryType.GET_MESSAGE_TALKS_SPEAKERS);
			}
		} catch (SQLException e) {
			JDBCException ex = new JDBCException();
			ex.initCause(e);
			throw ex;
		} finally {
			// Release JDBC resources in finally clause
			JDBCUtil.close(rs, stmt);
		}
	}

	/**
	 * Execute the "getMessageTalksSpeakers" function
	 * @return The query result
	 */
	public ResultSet getMessageTalksSpeakers(
			uclm.esi.cardroid.data.oracle.User usr, Current current) {
		CallableStatement cstmt = null;
		ResultSet result = null;

		try {
			String sql92Style = "{ ? = call callable_statements.get_message_talks_speakers(?) }";
			// Create the CallableStatement object
			cstmt = _conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(2, usr,
					uclm.esi.cardroid.data.oracle.User._SQL_TYPECODE);
			// Register output parameters
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = (ResultSet) cstmt.getObject(1);
		} catch (SQLException e) {
			JDBCUtil.printException(e);
			JDBCUtil.close(result, cstmt);
		}

		return result;
	}

	/**
	 * Execute the "getMessageTalksSpeakers" function and store its results in a List
	 * @return The query result, stored in a List structure
	 */
	public List<uclm.esi.cardroid.data.oracle.User> getMessageTalksSpeakersList(
			uclm.esi.cardroid.data.oracle.User usr, Current current) {
		return resultSetToList(getMessageTalksSpeakers(usr, current),
				uclm.esi.cardroid.data.oracle.User.class);
	}

	/**
	 * Execute the "getMessageTalks" function
	 * @return The query result
	 */
	public void getMessageTalks(UserTypPrx usr1, UserTypPrx usr2, int n,
			ResultSeqHolder first, IntHolder nrows,
			QueryResultPrxHolder result, Current current) {
		ResultSet rs = getMessageTalks(
				new uclm.esi.cardroid.data.oracle.User().newInstance(uclm.esi.cardroid.data.ice.User
						.extractObject(usr1)),
				new uclm.esi.cardroid.data.oracle.User()
						.newInstance(uclm.esi.cardroid.data.ice.User
								.extractObject(usr2)), current);
		Statement stmt = null;

		try {
			boolean next = rs.next();
			assert next;
			stmt = rs.getStatement();

			first.value = new LinkedList<ObjectPrx>();
			for (int i = 0; i < n && next; ++i) {
				first.value.add(uclm.esi.cardroid.data.ice.Message.createProxy(
						rs.getObject(1,
								uclm.esi.cardroid.data.oracle.Message.class),
						current.adapter));
				next = rs.next();
			}
			if (next) {
				QueryResultI impl = new QueryResultI(this, rs, current.adapter,
						QueryType.GET_MESSAGE_TALKS);
				result.value = QueryResultPrxHelper
						.uncheckedCast(current.adapter.addWithUUID(impl));
				if (_queryResultListener != null)
					_queryResultListener.add(result.value, impl,
							QueryType.GET_MESSAGE_TALKS);
			}
		} catch (SQLException e) {
			JDBCException ex = new JDBCException();
			ex.initCause(e);
			throw ex;
		} finally {
			// Release JDBC resources in finally clause
			JDBCUtil.close(rs, stmt);
		}
	}

	/**
	 * Execute the "getMessageTalks" function
	 * @return The query result
	 */
	public ResultSet getMessageTalks(uclm.esi.cardroid.data.oracle.User usr1,
			uclm.esi.cardroid.data.oracle.User usr2, Current current) {
		CallableStatement cstmt = null;
		ResultSet result = null;

		try {
			String sql92Style = "{ ? = call callable_statements.get_message_talks(?, ?) }";
			// Create the CallableStatement object
			cstmt = _conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(2, usr1,
					uclm.esi.cardroid.data.oracle.User._SQL_TYPECODE);
			cstmt.setObject(3, usr2,
					uclm.esi.cardroid.data.oracle.User._SQL_TYPECODE);
			// Register output parameters
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = (ResultSet) cstmt.getObject(1);
		} catch (SQLException e) {
			JDBCUtil.printException(e);
			JDBCUtil.close(result, cstmt);
		}

		return result;
	}

	/**
	 * Execute the "getMessageTalksList" function and store its results in a List
	 * @return The query result, stored in a List structure
	 */
	public List<uclm.esi.cardroid.data.oracle.Message> getMessageTalksList(
			uclm.esi.cardroid.data.oracle.User usr1,
			uclm.esi.cardroid.data.oracle.User usr2, Current current) {
		return resultSetToList(getMessageTalks(usr1, usr2, current),
				uclm.esi.cardroid.data.oracle.Message.class);
	}

	/**
	 * Execute the "newMessage" procedure
	 * @return The procedure result, if any
	 */
	public MessageTypPrx newMessage(UserTypPrx usr1, UserTypPrx usr2,
			String message, Current current) {
		uclm.esi.cardroid.data.oracle.Message result = newMessage(
				new uclm.esi.cardroid.data.oracle.User().newInstance(uclm.esi.cardroid.data.ice.User
						.extractObject(usr1)),
				new uclm.esi.cardroid.data.oracle.User()
						.newInstance(uclm.esi.cardroid.data.ice.User
								.extractObject(usr2)), message, current);
		MessageTypPrx resultPrx = uclm.esi.cardroid.data.ice.Message
				.createProxy(result, current.adapter);
		sendMessage(resultPrx);
		return resultPrx;
	}

	/**
	 * Execute the "newMessage" procedure
	 * @return The procedure result, if any
	 */
	public uclm.esi.cardroid.data.oracle.Message newMessage(
			uclm.esi.cardroid.data.oracle.User usr1,
			uclm.esi.cardroid.data.oracle.User usr2, String message,
			Current current) {
		OracleCallableStatement cstmt = null;
		uclm.esi.cardroid.data.oracle.Message result = null;

		try {
			String sql92Style = "{ call callable_statements.new_message(?, ?, ?, ?) }";
			// Create the CallableStatement object
			cstmt = (OracleCallableStatement) _conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(1, usr1,
					uclm.esi.cardroid.data.oracle.User._SQL_TYPECODE);
			cstmt.setObject(2, usr2,
					uclm.esi.cardroid.data.oracle.User._SQL_TYPECODE);
			cstmt.setString(3, message);
			// Register output parameters
			cstmt.registerOutParameter(4,
					uclm.esi.cardroid.data.oracle.Message._SQL_TYPECODE,
					uclm.esi.cardroid.data.oracle.Message._SQL_NAME);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = (uclm.esi.cardroid.data.oracle.Message) cstmt.getORAData(
					4,
					uclm.esi.cardroid.data.oracle.Message.getORADataFactory());
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
	public ResultSet getUserActivity(uclm.esi.cardroid.data.oracle.User usr,
			Current current) {
		CallableStatement cstmt = null;
		ResultSet result = null;

		try {
			String sql92Style = "{ ? = call callable_statements.get_user_activity(?) }";
			// Create the CallableStatement object
			cstmt = _conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(2, usr,
					uclm.esi.cardroid.data.oracle.User._SQL_TYPECODE);
			// Register output parameters
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = (ResultSet) cstmt.getObject(1);
		} catch (SQLException e) {
			JDBCUtil.printException(e);
			JDBCUtil.close(result, cstmt);
		}

		return result;
	}

	/**
	 * Execute the "getUserActivity" function
	 * @return The query result
	 */
	public void getUserActivity(UserTypPrx usr, int n, ResultSeqHolder first,
			IntHolder nrows, QueryResultPrxHolder result, Current current) {
		ResultSet rs = getUserActivity(
				new uclm.esi.cardroid.data.oracle.User().newInstance(uclm.esi.cardroid.data.ice.User
						.extractObject(usr)), current);
		Statement stmt = null;

		try {
			boolean next = rs.next();
			assert next;
			stmt = rs.getStatement();

			first.value = new LinkedList<ObjectPrx>();
			for (int i = 0; i < n && next; ++i) {
				first.value
						.add(uclm.esi.cardroid.data.ice.UserActivity.createProxy(
								rs.getObject(
										1,
										uclm.esi.cardroid.data.oracle.UserActivity.class),
								current.adapter));
				next = rs.next();
			}
			if (next) {
				QueryResultI impl = new QueryResultI(this, rs, current.adapter,
						QueryType.GET_USER_ACTIVITY);
				result.value = QueryResultPrxHelper
						.uncheckedCast(current.adapter.addWithUUID(impl));
				if (_queryResultListener != null)
					_queryResultListener.add(result.value, impl,
							QueryType.GET_USER_ACTIVITY);
			}
		} catch (SQLException e) {
			JDBCException ex = new JDBCException();
			ex.initCause(e);
			throw ex;
		} finally {
			// Release JDBC resources in finally clause
			JDBCUtil.close(rs, stmt);
		}
	}

	/**
	 * Execute the "getUserActivityList" function and store its results in a List
	 * @return The query result, stored in a List structure
	 */
	public List<uclm.esi.cardroid.data.oracle.UserActivity> getUserActivityList(
			uclm.esi.cardroid.data.oracle.User usr, Current current) {
		return resultSetToList(getUserActivity(usr, current),
				uclm.esi.cardroid.data.oracle.UserActivity.class);
	}

	/**
	 * Execute the "getUserFromEmail" function
	 * @return The function result
	 */
	public UserTypPrx getUserFromEmail(String email, Current current) {
		OracleCallableStatement cstmt = null;
		UserTypPrx result = null;

		try {
			String sql92Style = "{ ? = call callable_statements.get_user_from_email(?) }";
			// Create the CallableStatement object
			cstmt = (OracleCallableStatement) _conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setString(2, email);
			// Register output parameters
			cstmt.registerOutParameter(1,
					uclm.esi.cardroid.data.oracle.User._SQL_TYPECODE,
					uclm.esi.cardroid.data.oracle.User._SQL_NAME);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = uclm.esi.cardroid.data.ice.User.createProxy(
					((uclm.esi.cardroid.data.oracle.User) cstmt.getORAData(1,
							uclm.esi.cardroid.data.oracle.User
									.getORADataFactory())), current.adapter);
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
	public CarTypPrx getCarFromPlate(String plate, UserTypPrx owner,
			Current current) {
		OracleCallableStatement cstmt = null;
		CarTypPrx result = null;

		try {
			String sql92Style = "{ ? = call callable_statements.get_car_from_plate(?, ?) }";
			// Create the CallableStatement object
			cstmt = (OracleCallableStatement) _conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setString(2, plate);
			cstmt.setObject(3, new uclm.esi.cardroid.data.oracle.User()
					.newInstance(uclm.esi.cardroid.data.ice.User
							.extractObject(owner)),
					uclm.esi.cardroid.data.oracle.User._SQL_TYPECODE);
			// Register output parameters
			cstmt.registerOutParameter(1,
					uclm.esi.cardroid.data.oracle.Car._SQL_TYPECODE,
					uclm.esi.cardroid.data.oracle.Car._SQL_NAME);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = uclm.esi.cardroid.data.ice.Car.createProxy(owner
					.getEmail(), ((uclm.esi.cardroid.data.oracle.Car) cstmt
					.getORAData(1, uclm.esi.cardroid.data.oracle.Car
							.getORADataFactory())), current.adapter);
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
	public uclm.esi.cardroid.data.oracle.Car getCarFromPlate(String plate,
			uclm.esi.cardroid.data.oracle.User owner, Current current) {
		OracleCallableStatement cstmt = null;
		uclm.esi.cardroid.data.oracle.Car result = null;

		try {
			String sql92Style = "{ ? = call callable_statements.get_car_from_plate(?, ?) }";
			// Create the CallableStatement object
			cstmt = (OracleCallableStatement) _conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setString(2, plate);
			cstmt.setObject(3, owner,
					uclm.esi.cardroid.data.oracle.User._SQL_TYPECODE);
			// Register output parameters
			cstmt.registerOutParameter(1,
					uclm.esi.cardroid.data.oracle.Car._SQL_TYPECODE,
					uclm.esi.cardroid.data.oracle.Car._SQL_NAME);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = (uclm.esi.cardroid.data.oracle.Car) cstmt.getORAData(1,
					uclm.esi.cardroid.data.oracle.Car.getORADataFactory());
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}

		return result;
	}

	/**
	 * Execute the "getCarFromPlateEmail" function
	 * @return The function result
	 */
	public CarTypPrx getCarFromPlateEmail(String plate, String ownerEmail,
			Current current) {
		return uclm.esi.cardroid.data.ice.Car.createProxy(ownerEmail,
				getCarFromPlate(plate, ownerEmail, current), current.adapter);
	}

	/**
	 * Execute the "getCarFromPlate" function
	 * @return The function result
	 */
	public uclm.esi.cardroid.data.oracle.Car getCarFromPlate(String plate,
			String ownerEmail, Current current) {
		OracleCallableStatement cstmt = null;
		uclm.esi.cardroid.data.oracle.Car result = null;

		try {
			String sql92Style = "{ ? = call callable_statements.get_car_from_plate(?, ?) }";
			// Create the CallableStatement object
			cstmt = (OracleCallableStatement) _conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setString(2, plate);
			cstmt.setString(3, ownerEmail);
			// Register output parameters
			cstmt.registerOutParameter(1,
					uclm.esi.cardroid.data.oracle.Car._SQL_TYPECODE,
					uclm.esi.cardroid.data.oracle.Car._SQL_NAME);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = (uclm.esi.cardroid.data.oracle.Car) cstmt.getORAData(1,
					uclm.esi.cardroid.data.oracle.Car.getORADataFactory());
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
	public UserTypPrx updateUserData(
			uclm.esi.cardroid.data.zerocice.UserTyp usr, Current current) {
		String sql92Style;

		OracleCallableStatement cstmt = null;
		UserTypPrx result = null;

		if (usr.hasAvatar()) {
			ResultSet rset = null;
			OutputStream ostream = null;

			byte[] avatarBytes = usr.getAvatarBytes();
			usr.clearUserAvatarBytes();

			try {
				sql92Style = "SELECT avatar FROM users_tbl WHERE email = ? FOR UPDATE";
				Map<Object, Integer> paramsMap = new LinkedHashMap<Object, Integer>(
						1);
				paramsMap.put(usr.getEmail(), OracleTypes.VARCHAR);
				rset = executeStatementQuery(sql92Style, 1, paramsMap);
				if (rset.next()) {
					Blob blob = rset.getBlob(1);
					ostream = blob.setBinaryStream(1L);
					ostream.write(avatarBytes);
					ostream.close();
					blob.truncate(avatarBytes.length);
				}
			} catch (SQLException sqle) {
				JDBCUtil.printException(sqle);
			} catch (IOException ioe) {
				JDBCUtil.printException(ioe);
			} finally {
				JDBCUtil.close(rset);
			}
		}

		try {
			sql92Style = "{ call callable_statements.update_user_data(?) }";
			// Create the CallableStatement object
			cstmt = (OracleCallableStatement) _conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(1, new uclm.esi.cardroid.data.oracle.User()
					.newInstance(new uclm.esi.cardroid.data.ice.User(usr)),
					uclm.esi.cardroid.data.oracle.User._SQL_TYPECODE);
			// Register output parameters
			cstmt.registerOutParameter(1,
					uclm.esi.cardroid.data.oracle.User._SQL_TYPECODE,
					uclm.esi.cardroid.data.oracle.User._SQL_NAME);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = uclm.esi.cardroid.data.ice.User.createProxy(
					(uclm.esi.cardroid.data.oracle.User) cstmt.getORAData(1,
							uclm.esi.cardroid.data.oracle.User
									.getORADataFactory()), current.adapter);
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}

		return result;
	}

	/**
	 * Execute the "updateCarData" procedure
	 * @return The procedure result, if any
	 */
	public CarTypPrx updateCarData(uclm.esi.cardroid.data.zerocice.CarTyp car,
			uclm.esi.cardroid.data.zerocice.UserTyp usr, Current current) {
		CallableStatement cstmt = null;
		CarTypPrx result = null;

		try {
			String sql92Style = "{ call callable_statements.update_car_data(?, ?) }";
			// Create the CallableStatement object
			cstmt = _conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(1, new uclm.esi.cardroid.data.oracle.Car()
					.newInstance((uclm.esi.cardroid.data.ice.Car) car),
					uclm.esi.cardroid.data.oracle.Car._SQL_TYPECODE);
			cstmt.setObject(2, new uclm.esi.cardroid.data.oracle.User()
					.newInstance((uclm.esi.cardroid.data.ice.User) usr),
					uclm.esi.cardroid.data.oracle.User._SQL_TYPECODE);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = uclm.esi.cardroid.data.ice.Car.createProxy(usr.getEmail(),
					car, current.adapter);
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}

		return result;
	}

	/**
	 * Execute the "updateCarDataEmail" procedure
	 * @return The procedure result, if any
	 */
	public CarTypPrx updateCarDataEmail(
			uclm.esi.cardroid.data.zerocice.CarTyp car, String usrEmail,
			Current current) {
		CallableStatement cstmt = null;
		CarTypPrx result = null;

		try {
			String sql92Style = "{ call callable_statements.update_car_data(?, ?) }";
			// Create the CallableStatement object
			cstmt = _conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(1, new uclm.esi.cardroid.data.oracle.Car()
					.newInstance((uclm.esi.cardroid.data.ice.Car) car),
					uclm.esi.cardroid.data.oracle.Car._SQL_TYPECODE);
			cstmt.setString(2, usrEmail);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = uclm.esi.cardroid.data.ice.Car.createProxy(usrEmail, car,
					current.adapter);
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
	public CarTypPrx addCar(uclm.esi.cardroid.data.zerocice.CarTyp car,
			UserTypPrx usr, Current current) {
		CallableStatement cstmt = null;
		CarTypPrx result = null;

		try {
			String sql92Style = "{ call callable_statements.add_car(?, ?) }";
			// Create the CallableStatement object
			cstmt = _conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(1, new uclm.esi.cardroid.data.oracle.Car()
					.newInstance((uclm.esi.cardroid.data.ice.Car) car),
					uclm.esi.cardroid.data.oracle.Car._SQL_TYPECODE);
			cstmt.setObject(2, new uclm.esi.cardroid.data.oracle.User()
					.newInstance(uclm.esi.cardroid.data.ice.User
							.extractObject(usr)),
					uclm.esi.cardroid.data.oracle.User._SQL_TYPECODE);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = uclm.esi.cardroid.data.ice.Car.createProxy(usr.getEmail(),
					car, current.adapter);
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
	public CarTypPrx addCarEmail(uclm.esi.cardroid.data.zerocice.CarTyp car,
			String usrEmail, Current current) {
		CallableStatement cstmt = null;
		CarTypPrx result = null;

		try {
			String sql92Style = "{ call callable_statements.add_car(?, ?) }";
			// Create the CallableStatement object
			cstmt = _conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(1, new uclm.esi.cardroid.data.oracle.Car()
					.newInstance((uclm.esi.cardroid.data.ice.Car) car),
					uclm.esi.cardroid.data.oracle.Car._SQL_TYPECODE);
			cstmt.setString(2, usrEmail);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
			result = uclm.esi.cardroid.data.ice.Car.createProxy(usrEmail, car,
					current.adapter);
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}

		return result;
	}

	/**
	 * Execute the "removeCar" procedure
	 * @return The procedure result, if any
	 */
	public void removeCar(CarTypPrx car, UserTypPrx usr, Current current) {
		removeCar(
				new uclm.esi.cardroid.data.oracle.Car().newInstance(uclm.esi.cardroid.data.ice.Car
						.extractObject(car)),
				new uclm.esi.cardroid.data.oracle.User()
						.newInstance(uclm.esi.cardroid.data.ice.User
								.extractObject(usr)), current);
	}

	/**
	 * Execute the "removeCar" procedure
	 * @return The procedure result, if any
	 */
	public void removeCar(uclm.esi.cardroid.data.oracle.Car car,
			uclm.esi.cardroid.data.oracle.User usr, Current current) {
		CallableStatement cstmt = null;

		try {
			String sql92Style = "{ call callable_statements.remove_car(?, ?) }";
			// Create the CallableStatement object
			cstmt = _conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(1, car,
					uclm.esi.cardroid.data.oracle.Car._SQL_TYPECODE);
			cstmt.setObject(2, usr,
					uclm.esi.cardroid.data.oracle.User._SQL_TYPECODE);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}
	}

	/**
	 * Execute the "removeCarPlateEmail" procedure
	 * @return The procedure result, if any
	 */
	public void removeCarPlateEmail(String plate, String ownerEmail,
			Current current) {
		CallableStatement cstmt = null;

		try {
			String sql92Style = "{ call callable_statements.remove_car(?, ?) }";
			// Create the CallableStatement object
			cstmt = _conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setString(1, plate);
			cstmt.setString(2, ownerEmail);
			// Execute CallableStatement and retrieve results
			cstmt.execute();
		} catch (SQLException e) {
			JDBCUtil.printException(e);
		} finally {
			JDBCUtil.close(cstmt);
		}
	}

	/**
	 * Execute the "userTripRegistered" function
	 * @return The function result
	 */
	public boolean userTripRegistered(UserTypPrx usr, TripTypPrx trip,
			Current current) {
		return userTripRegistered(
				new uclm.esi.cardroid.data.oracle.User().newInstance(uclm.esi.cardroid.data.ice.User
						.extractObject(usr)),
				new uclm.esi.cardroid.data.oracle.Trip()
						.newInstance(uclm.esi.cardroid.data.ice.Trip
								.extractObject(trip)), current);
	}

	/**
	 * Execute the "userTripRegistered" function
	 * @return The function result
	 */
	public boolean userTripRegistered(uclm.esi.cardroid.data.oracle.User usr,
			uclm.esi.cardroid.data.oracle.Trip trip, Current current) {
		CallableStatement cstmt = null;
		boolean result;

		try {
			String sql92Style = "{ ? = call callable_statements.user_trip_registered(?, ?) }";
			// Create the CallableStatement object
			cstmt = _conn.prepareCall(sql92Style);
			// Bind input parameters
			cstmt.setObject(2, usr,
					uclm.esi.cardroid.data.oracle.User._SQL_TYPECODE);
			cstmt.setObject(3, trip,
					uclm.esi.cardroid.data.oracle.Trip._SQL_TYPECODE);
			// Register output parameters
			cstmt.registerOutParameter(1, OracleTypes.INTEGER);
			// Execute Callable Statement and retrieve results
			cstmt.execute();
			result = cstmt.getInt(1) > 0;
		} catch (SQLException e) {
			JDBCUtil.printException(e);
			result = true;
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

	public interface QueryResultListener {
		public void add(QueryResultPrx proxy, QueryResultI impl,
				QueryType queryType);
	}

	/**
	 * Publish an user activity in the receipt user's topic
	 * @param activity The object to be published
	 */
	private void notifyUserActivity(UserActivityTypPrx activity) {
		// Retrieve the topic's publisher for the receipt user
		CardroidEventStormPrx publisher = retrieveTopicPublisher(activity
				.getActivityUser().getEmail());

		/* Publish the new user activity notification to its subscribers * 
		 * via the IceStorm service										 */
		if (publisher != null)
			publisher._notify(activity);
	}

	/**
	 * Publish a private message in the receipt's topic
	 * @param msg The object to be published
	 */
	private void sendMessage(MessageTypPrx msg) {
		// Retrieve the topic's publisher for the receipt user
		CardroidEventStormPrx publisher = retrieveTopicPublisher(msg.getUser2()
				.getEmail());

		/* Publish the new message notification to its subscribers *
		 * via the IceStorm service								   */
		if (publisher != null)
			publisher.message(msg);

		// retrieveTopicPublisher(msg.getUser1().getEmail()).message(msg);
	}

	/**
	 * Retrieve the publisher object for the IceStorm topic identified by name
	 * @param name	The id of the topic
	 * @return		A proxy to the publisher object for this topic,
	 * 					null if no topic with that name exists
	 */
	private CardroidEventStormPrx retrieveTopicPublisher(String name) {
		TopicPrx topic = null;
		try {
			topic = _topicManager.retrieve(name);
		} catch (NoSuchTopic nst) {
			// No sessions for the topic's user are currently active
			return null;
		}

		/* The _fwd context key, when encountered by Glacier2, *
		 * causes the router to forward the request            *
		 * using compressed batch oneway messages			   */
		Map<String, String> ctx = new HashMap<String, String>();
		ctx.put("_fwd", "Oz");

		/* Obtain a proxy for the publisher object and narrow it to the *
		 * CardroidEventStorm interface									*/
		return CardroidEventStormPrxHelper.uncheckedCast(topic.getPublisher()
				.ice_oneway().ice_context(ctx));
	}
}
