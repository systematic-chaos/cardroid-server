/*@lineinfo:filename=Trip*//*@lineinfo:user-code*//*@lineinfo:1^1*/package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;

import oracle.jpub.runtime.MutableStruct;

import sqlj.runtime.ref.DefaultContext;
import sqlj.runtime.ConnectionContext;

public class TripTyp implements ORAData, ORADataFactory {
	public static final String _SQL_NAME = "ANDROID.TRIP_TYP";
	public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

	/* connection management */
	protected Connection __onn = null;
	protected javax.sql.DataSource __dataSource = null;

	public void setDataSource(javax.sql.DataSource dataSource)
			throws SQLException {
		release();
		__dataSource = dataSource;
	}

	public void setDataSourceLocation(String dataSourceLocation)
			throws SQLException {
		javax.sql.DataSource dataSource;
		try {
			Class cls = Class.forName("javax.naming.InitialContext");
			Object ctx = cls.newInstance();
			java.lang.reflect.Method meth = cls.getMethod("lookup",
					new Class[] { String.class });
			dataSource = (javax.sql.DataSource) meth.invoke(ctx,
					new Object[] { "java:comp/env/" + dataSourceLocation });
			setDataSource(dataSource);
		} catch (Exception e) {
			throw new java.sql.SQLException("Error initializing DataSource at "
					+ dataSourceLocation + ": " + e.getMessage());
		}
	}

	public Connection getConnection() throws SQLException {
		if (__onn != null)
			return __onn;
		else if (__tx != null)
			return __tx.getConnection();
		else if (__dataSource != null)
			__onn = __dataSource.getConnection();
		return __onn;
	}

	public void release() throws SQLException {
		if (__tx != null && __onn != null)
			__tx.close(ConnectionContext.KEEP_CONNECTION);
		__onn = null;
		__tx = null;
		__dataSource = null;
	}

	public void closeConnection() {
		if (__dataSource != null) {
			try {
				if (__onn != null) {
					__onn.close();
				}
			} catch (java.sql.SQLException e) {
			}
			try {
				if (__tx != null) {
					__tx.close();
				}
			} catch (java.sql.SQLException e) {
			}
			__onn = null;
			__tx = null;
		}
	}

	protected DefaultContext __tx = null;

	public void setConnectionContext(DefaultContext ctx) throws SQLException {
		release();
		__tx = ctx;
	}

	public DefaultContext getConnectionContext() throws SQLException {
		if (__tx == null) {
			__tx = (getConnection() == null) ? DefaultContext
					.getDefaultContext() : new DefaultContext(getConnection());
		}
		return __tx;
	}

	protected MutableStruct _struct;

	protected static int[] _sqlType = { 4, 2002, 2002, 2002, 4, 2002, 2003, 12,
			4, 12, 4 };
	protected static ORADataFactory[] _factory = new ORADataFactory[11];
	static {
		_factory[1] = Place.getORADataFactory();
		_factory[2] = Place.getORADataFactory();
		_factory[3] = Date.getORADataFactory();
		_factory[5] = Date.getORADataFactory();
		_factory[6] = Trip_WeekDaysVarray.getORADataFactory();
	}
	protected static final TripTyp _TripTypFactory = new TripTyp();

	public static ORADataFactory getORADataFactory() {
		return _TripTypFactory;
	}

	protected static java.util.Hashtable _map = new java.util.Hashtable();
	protected static boolean _initialized = false;

	protected static synchronized void init() {
		if (!_initialized) {
			_initialized = true;
			_map.put("ANDROID.TRIP_TYP",
					uclm.esi.cardroid.data.oracle.Trip.getORADataFactory());
			_map.put("ANDROID.TRIP_OFFER_TYP",
					uclm.esi.cardroid.data.oracle.TripOffer.getORADataFactory());
			_map.put("ANDROID.TRIP_REQUEST_TYP",
					uclm.esi.cardroid.data.oracle.TripRequest
							.getORADataFactory());
		}
	}

	/* constructors */

	protected void _init_struct(boolean init) {
		if (init)
			_struct = new MutableStruct(new Object[11], _sqlType, _factory);
	}

	protected TripTyp() {
		_init_struct(true);
		__tx = DefaultContext.getDefaultContext();
	}

	protected TripTyp(DefaultContext c) /* throws SQLException */
	{
		_init_struct(true);
		__tx = c;
	}

	protected TripTyp(Connection c) /* throws SQLException */
	{
		_init_struct(true);
		__onn = c;
	}

	protected TripTyp(int tripId, Place fromPlace, Place toPlace,
			Date datetime, int nSeats, Date returnDatetime,
			Trip_WeekDaysVarray weekDays, String periodicity, int distance,
			String characteristics, int tripType) throws SQLException {
		_init_struct(true);
		setTripId(tripId);
		setPlace1(fromPlace);
		setPlace2(toPlace);
		setDatetime(datetime);
		setNSeats(nSeats);
		setReturnDatetime(returnDatetime);
		setWeekDays(weekDays);
		setPeriodicity(periodicity);
		setDistance(distance);
		setCharacteristics(characteristics);
		setTripType(tripType);
	}

	/* ORAData interface */

	public Datum toDatum(Connection c) throws SQLException {
		if (__tx != null && __onn != c)
			release();
		__onn = c;
		return _struct.toDatum(c, _SQL_NAME);
	}

	/* ORADataFactory interface */

	public ORAData create(Datum d, int sqlType) throws SQLException {
		return create(null, d, sqlType);
	}

	public void setFrom(TripTyp o) throws SQLException {
		setContextFrom(o);
		setValueFrom(o);
	}

	protected void setContextFrom(TripTyp o) throws SQLException {
		release();
		__tx = o.__tx;
		__onn = o.__onn;
	}

	protected void setValueFrom(TripTyp o) {
		_struct = o._struct;
	}

	protected ORAData create(TripTyp o, Datum d, int sqlType)
			throws SQLException {
		if (d == null) {
			if (o != null) {
				o.release();
			}
			;
			return null;
		}
		if (o == null)
			return createFromFactory("Trip", d, sqlType);
		o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
		o.__onn = ((STRUCT) d).getJavaSqlConnection();
		return o;
	}

	protected ORAData createExact(Datum d, int sqlType) throws SQLException {
		TripTyp o = new TripTyp();
		o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
		o.__onn = ((STRUCT) d).getJavaSqlConnection();
		return o;
	}

	protected ORAData createFromFactory(String s, Datum d, int sqlType)
			throws SQLException {
		String sql = ((STRUCT) d).getSQLTypeName();
		init();
		TripTyp factory = (TripTyp) _map.get(sql);
		if (factory == null) {
			int p;
			if ((p = sql.indexOf(".")) >= 0) {
				factory = (TripTyp) _map.get(sql.substring(p + 1));
				if (factory != null)
					_map.put(sql, factory);
			}
			if (factory == null)
				throw new SQLException("Unable to convert a " + sql + " to a "
						+ s + " or a subclass of " + s);
		}
		return factory.createExact(d, sqlType);
	}

	/* accessor methods */

	public int getTripId() throws SQLException {
		return ((Integer) _struct.getAttribute(0)).intValue();
	}

	public void setTripId(int tripId) throws SQLException {
		_struct.setAttribute(0, new Integer(tripId));
	}

	public Place getPlace1() throws SQLException {
		return (Place) _struct.getAttribute(1);
	}

	public void setPlace1(Place fromPlace) throws SQLException {
		_struct.setAttribute(1, fromPlace);
	}

	public Place getPlace2() throws SQLException {
		return (Place) _struct.getAttribute(2);
	}

	public void setPlace2(Place toPlace) throws SQLException {
		_struct.setAttribute(2, toPlace);
	}

	public Date getDatetime() throws SQLException {
		return (Date) _struct.getAttribute(3);
	}

	public void setDatetime(Date datetime) throws SQLException {
		_struct.setAttribute(3, datetime);
	}

	public int getNSeats() throws SQLException {
		return ((Integer) _struct.getAttribute(4)).intValue();
	}

	public void setNSeats(int nSeats) throws SQLException {
		_struct.setAttribute(4, new Integer(nSeats));
	}

	public Date getReturnDatetime() throws SQLException {
		return (Date) _struct.getAttribute(5);
	}

	public void setReturnDatetime(Date returnDatetime) throws SQLException {
		_struct.setAttribute(5, returnDatetime);
	}

	public Trip_WeekDaysVarray getWeekDaysVarray() throws SQLException {
		return (Trip_WeekDaysVarray) _struct.getAttribute(6);
	}

	public void setWeekDays(Trip_WeekDaysVarray weekDays) throws SQLException {
		_struct.setAttribute(6, weekDays);
	}

	public String getPeriodicityName() throws SQLException {
		return (String) _struct.getAttribute(7);
	}

	public void setPeriodicity(String periodicity) throws SQLException {
		_struct.setAttribute(7, periodicity);
	}

	public int getDistance() throws SQLException {
		return ((Integer) _struct.getAttribute(8)).intValue();
	}

	public void setDistance(int distance) throws SQLException {
		_struct.setAttribute(8, new Integer(distance));
	}

	public String getCharacteristics() throws SQLException {
		return (String) _struct.getAttribute(9);
	}

	public void setCharacteristics(String characteristics) throws SQLException {
		_struct.setAttribute(9, characteristics);
	}

	public int getTripType() throws SQLException {
		return ((Integer) _struct.getAttribute(10)).intValue();
	}

	public void setTripType(int tripType) throws SQLException {
		_struct.setAttribute(10, new Integer(tripType));
	}

	public String toString() {
		try {
			return "ANDROID.TRIP_TYP"
					+ "("
					+ getTripId()
					+ ","
					+ getPlace1()
					+ ","
					+ getPlace2()
					+ ","
					+ getDatetime()
					+ ","
					+ getNSeats()
					+ ","
					+ getReturnDatetime()
					+ ","
					+ getWeekDaysVarray()
					+ ","
					+ ((getPeriodicityName() == null) ? "null" : "'"
							+ getPeriodicityName() + "'")
					+ ","
					+ getDistance()
					+ ","
					+ ((getCharacteristics() == null) ? "null" : "'"
							+ getCharacteristics() + "," + getTripType() + "'")
					+ ")";
		} catch (Exception e) {
			return e.toString();
		}
	}

	public java.sql.Timestamp compareTo() throws java.sql.SQLException {
		TripTyp __jPt_temp = this;
		java.sql.Timestamp __jPt_result;
		try {
			/* @lineinfo:generated-code *//* @lineinfo:258^5 */

			// ************************************************************
			// #sql [getConnectionContext()] { BEGIN
			// :__jPt_result := :__jPt_temp.COMPARE_TO();
			// END;
			// };
			// ************************************************************

			{
				// declare temps
				oracle.jdbc.OracleCallableStatement __sJT_st = null;
				sqlj.runtime.ref.DefaultContext __sJT_cc = getConnectionContext();
				if (__sJT_cc == null)
					sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
				sqlj.runtime.ExecutionContext.OracleContext __sJT_ec = ((__sJT_cc
						.getExecutionContext() == null) ? sqlj.runtime.ExecutionContext
						.raiseNullExecCtx() : __sJT_cc.getExecutionContext()
						.getOracleContext());
				try {
					String theSqlTS = "BEGIN\n       :1   :=  :2  .COMPARE_TO();\n      END;";
					__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
							"0uclm.esi.cardroid.data.oracle.TripTyp", theSqlTS);
					if (__sJT_ec.isNew()) {
						__sJT_st.registerOutParameter(1,
								oracle.jdbc.OracleTypes.TIMESTAMP);
					}
					// set IN parameters
					if (__jPt_temp == null)
						__sJT_st.setNull(2, 2002, "ANDROID.TRIP_TYP");
					else
						__sJT_st.setORAData(2, __jPt_temp);
					// execute statement
					__sJT_ec.oracleExecuteUpdate();
					// retrieve OUT parameters
					__jPt_result = __sJT_st.getTimestamp(1);
				} finally {
					__sJT_ec.oracleClose();
				}
			}

			// ************************************************************

			/* @lineinfo:user-code *//* @lineinfo:262^5 */
		} catch (java.sql.SQLException _err) {
			try {
				getConnectionContext().getExecutionContext().close();
				closeConnection();
				if (__dataSource == null)
					throw _err;
				/* @lineinfo:generated-code *//* @lineinfo:268^5 */

				// ************************************************************
				// #sql [getConnectionContext()] { BEGIN
				// :__jPt_result := :__jPt_temp.COMPARE_TO();
				// END;
				// };
				// ************************************************************

				{
					// declare temps
					oracle.jdbc.OracleCallableStatement __sJT_st = null;
					sqlj.runtime.ref.DefaultContext __sJT_cc = getConnectionContext();
					if (__sJT_cc == null)
						sqlj.runtime.error.RuntimeRefErrors
								.raise_NULL_CONN_CTX();
					sqlj.runtime.ExecutionContext.OracleContext __sJT_ec = ((__sJT_cc
							.getExecutionContext() == null) ? sqlj.runtime.ExecutionContext
							.raiseNullExecCtx() : __sJT_cc
							.getExecutionContext().getOracleContext());
					try {
						String theSqlTS = "BEGIN\n       :1   :=  :2  .COMPARE_TO();\n      END;";
						__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
								"1uclm.esi.cardroid.data.oracle.TripTyp",
								theSqlTS);
						if (__sJT_ec.isNew()) {
							__sJT_st.registerOutParameter(1,
									oracle.jdbc.OracleTypes.TIMESTAMP);
						}
						// set IN parameters
						if (__jPt_temp == null)
							__sJT_st.setNull(2, 2002, "ANDROID.TRIP_TYP");
						else
							__sJT_st.setORAData(2, __jPt_temp);
						// execute statement
						__sJT_ec.oracleExecuteUpdate();
						// retrieve OUT parameters
						__jPt_result = __sJT_st.getTimestamp(1);
					} finally {
						__sJT_ec.oracleClose();
					}
				}

				// ************************************************************

				/* @lineinfo:user-code *//* @lineinfo:272^5 */
			} catch (java.sql.SQLException _err2) {
				try {
					getConnectionContext().getExecutionContext().close();
				} catch (java.sql.SQLException _sqle) {
				}
				throw _err;
			}
		}
		return __jPt_result;
	}

	public Trip tripTyp(Place FROM_PLACE, Place TO_PLACE, Date DATETIME,
			Date RETURN_DATETIME, int N_SEATS, Trip_WeekDaysVarray WEEK_DAYS,
			String PERIODICITY, Trip[] __jPt_out) throws java.sql.SQLException {
		Trip __jPt_temp = (Trip) this;
		Trip __jPt_result;
		try {
			/* @lineinfo:generated-code *//* @lineinfo:295^5 */

			// ************************************************************
			// #sql [getConnectionContext()] { BEGIN
			// :__jPt_result := TRIP_TYP(
			// :FROM_PLACE,
			// :TO_PLACE,
			// :DATETIME,
			// :RETURN_DATETIME,
			// :N_SEATS,
			// :WEEK_DAYS,
			// :PERIODICITY);
			// END;
			// };
			// ************************************************************

			{
				// declare temps
				oracle.jdbc.OracleCallableStatement __sJT_st = null;
				sqlj.runtime.ref.DefaultContext __sJT_cc = getConnectionContext();
				if (__sJT_cc == null)
					sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
				sqlj.runtime.ExecutionContext.OracleContext __sJT_ec = ((__sJT_cc
						.getExecutionContext() == null) ? sqlj.runtime.ExecutionContext
						.raiseNullExecCtx() : __sJT_cc.getExecutionContext()
						.getOracleContext());
				try {
					String theSqlTS = "BEGIN\n       :1   := TRIP_TYP(\n       :2  ,\n       :3  ,\n       :4  ,\n       :5  ,\n       :6  ,\n       :7  ,\n       :8  );\n      END;";
					__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
							"2uclm.esi.cardroid.data.oracle.TripTyp", theSqlTS);
					if (__sJT_ec.isNew()) {
						__sJT_st.registerOutParameter(1, 2002,
								"ANDROID.TRIP_TYP");
					}
					// set IN parameters
					if (FROM_PLACE == null)
						__sJT_st.setNull(2, 2002, "ANDROID.PLACE_TYP");
					else
						__sJT_st.setORAData(2, FROM_PLACE);
					if (TO_PLACE == null)
						__sJT_st.setNull(3, 2002, "ANDROID.PLACE_TYP");
					else
						__sJT_st.setORAData(3, TO_PLACE);
					if (DATETIME == null)
						__sJT_st.setNull(4, 2002, "ANDROID.DATE_TYP");
					else
						__sJT_st.setORAData(4, DATETIME);
					if (RETURN_DATETIME == null)
						__sJT_st.setNull(5, 2002, "ANDROID.DATE_TYP");
					else
						__sJT_st.setORAData(5, RETURN_DATETIME);
					__sJT_st.setInt(6, N_SEATS);
					if (WEEK_DAYS == null)
						__sJT_st.setNull(7, 2003, "ANDROID.WEEKDAYS_TYP");
					else
						__sJT_st.setORAData(7, WEEK_DAYS);
					__sJT_st.setString(8, PERIODICITY);
					// execute statement
					__sJT_ec.oracleExecuteUpdate();
					// retrieve OUT parameters
					__jPt_result = (uclm.esi.cardroid.data.oracle.Trip) __sJT_st
							.getORAData(1, uclm.esi.cardroid.data.oracle.Trip
									.getORADataFactory());
				} finally {
					__sJT_ec.oracleClose();
				}
			}

			// ************************************************************

			/* @lineinfo:user-code *//* @lineinfo:306^5 */
			__jPt_out[0] = __jPt_temp;
		} catch (java.sql.SQLException _err) {
			try {
				getConnectionContext().getExecutionContext().close();
				closeConnection();
				if (__dataSource == null)
					throw _err;
				/* @lineinfo:generated-code *//* @lineinfo:313^5 */

				// ************************************************************
				// #sql [getConnectionContext()] { BEGIN
				// :__jPt_result := TRIP_TYP(
				// :FROM_PLACE,
				// :TO_PLACE,
				// :DATETIME,
				// :RETURN_DATETIME,
				// :N_SEATS,
				// :WEEK_DAYS,
				// :PERIODICITY);
				// END;
				// };
				// ************************************************************

				{
					// declare temps
					oracle.jdbc.OracleCallableStatement __sJT_st = null;
					sqlj.runtime.ref.DefaultContext __sJT_cc = getConnectionContext();
					if (__sJT_cc == null)
						sqlj.runtime.error.RuntimeRefErrors
								.raise_NULL_CONN_CTX();
					sqlj.runtime.ExecutionContext.OracleContext __sJT_ec = ((__sJT_cc
							.getExecutionContext() == null) ? sqlj.runtime.ExecutionContext
							.raiseNullExecCtx() : __sJT_cc
							.getExecutionContext().getOracleContext());
					try {
						String theSqlTS = "BEGIN\n       :1   := TRIP_TYP(\n       :2  ,\n       :3  ,\n       :4  ,\n       :5  ,\n       :6  ,\n       :7  ,\n       :8  );\n      END;";
						__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
								"3uclm.esi.cardroid.data.oracle.TripTyp",
								theSqlTS);
						if (__sJT_ec.isNew()) {
							__sJT_st.registerOutParameter(1, 2002,
									"ANDROID.TRIP_TYP");
						}
						// set IN parameters
						if (FROM_PLACE == null)
							__sJT_st.setNull(2, 2002, "ANDROID.PLACE_TYP");
						else
							__sJT_st.setORAData(2, FROM_PLACE);
						if (TO_PLACE == null)
							__sJT_st.setNull(3, 2002, "ANDROID.PLACE_TYP");
						else
							__sJT_st.setORAData(3, TO_PLACE);
						if (DATETIME == null)
							__sJT_st.setNull(4, 2002, "ANDROID.DATE_TYP");
						else
							__sJT_st.setORAData(4, DATETIME);
						if (RETURN_DATETIME == null)
							__sJT_st.setNull(5, 2002, "ANDROID.DATE_TYP");
						else
							__sJT_st.setORAData(5, RETURN_DATETIME);
						__sJT_st.setInt(6, N_SEATS);
						if (WEEK_DAYS == null)
							__sJT_st.setNull(7, 2003, "ANDROID.WEEKDAYS_TYP");
						else
							__sJT_st.setORAData(7, WEEK_DAYS);
						__sJT_st.setString(8, PERIODICITY);
						// execute statement
						__sJT_ec.oracleExecuteUpdate();
						// retrieve OUT parameters
						__jPt_result = (uclm.esi.cardroid.data.oracle.Trip) __sJT_st
								.getORAData(1,
										uclm.esi.cardroid.data.oracle.Trip
												.getORADataFactory());
					} finally {
						__sJT_ec.oracleClose();
					}
				}

				// ************************************************************

				/* @lineinfo:user-code *//* @lineinfo:324^5 */
				__jPt_out[0] = __jPt_temp;
			} catch (java.sql.SQLException _err2) {
				try {
					getConnectionContext().getExecutionContext().close();
				} catch (java.sql.SQLException _sqle) {
				}
				throw _err;
			}
		}
		return __jPt_result;
	}

	public Trip tripTyp(Place FROM_PLACE, Place TO_PLACE, Date DATETIME,
			int N_SEATS, Trip_WeekDaysVarray WEEK_DAYS, String PERIODICITY,
			Trip[] __jPt_out) throws java.sql.SQLException {
		Trip __jPt_temp = (Trip) this;
		Trip __jPt_result;
		try {
			/* @lineinfo:generated-code *//* @lineinfo:347^5 */

			// ************************************************************
			// #sql [getConnectionContext()] { BEGIN
			// :__jPt_result := TRIP_TYP(
			// :FROM_PLACE,
			// :TO_PLACE,
			// :DATETIME,
			// :N_SEATS,
			// :WEEK_DAYS,
			// :PERIODICITY);
			// END;
			// };
			// ************************************************************

			{
				// declare temps
				oracle.jdbc.OracleCallableStatement __sJT_st = null;
				sqlj.runtime.ref.DefaultContext __sJT_cc = getConnectionContext();
				if (__sJT_cc == null)
					sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
				sqlj.runtime.ExecutionContext.OracleContext __sJT_ec = ((__sJT_cc
						.getExecutionContext() == null) ? sqlj.runtime.ExecutionContext
						.raiseNullExecCtx() : __sJT_cc.getExecutionContext()
						.getOracleContext());
				try {
					String theSqlTS = "BEGIN\n       :1   := TRIP_TYP(\n       :2  ,\n       :3  ,\n       :4  ,\n       :5  ,\n       :6  ,\n       :7  );\n      END;";
					__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
							"4uclm.esi.cardroid.data.oracle.TripTyp", theSqlTS);
					if (__sJT_ec.isNew()) {
						__sJT_st.registerOutParameter(1, 2002,
								"ANDROID.TRIP_TYP");
					}
					// set IN parameters
					if (FROM_PLACE == null)
						__sJT_st.setNull(2, 2002, "ANDROID.PLACE_TYP");
					else
						__sJT_st.setORAData(2, FROM_PLACE);
					if (TO_PLACE == null)
						__sJT_st.setNull(3, 2002, "ANDROID.PLACE_TYP");
					else
						__sJT_st.setORAData(3, TO_PLACE);
					if (DATETIME == null)
						__sJT_st.setNull(4, 2002, "ANDROID.DATE_TYP");
					else
						__sJT_st.setORAData(4, DATETIME);
					__sJT_st.setInt(5, N_SEATS);
					if (WEEK_DAYS == null)
						__sJT_st.setNull(6, 2003, "ANDROID.WEEKDAYS_TYP");
					else
						__sJT_st.setORAData(6, WEEK_DAYS);
					__sJT_st.setString(7, PERIODICITY);
					// execute statement
					__sJT_ec.oracleExecuteUpdate();
					// retrieve OUT parameters
					__jPt_result = (uclm.esi.cardroid.data.oracle.Trip) __sJT_st
							.getORAData(1, uclm.esi.cardroid.data.oracle.Trip
									.getORADataFactory());
				} finally {
					__sJT_ec.oracleClose();
				}
			}

			// ************************************************************

			/* @lineinfo:user-code *//* @lineinfo:357^5 */
			__jPt_out[0] = __jPt_temp;
		} catch (java.sql.SQLException _err) {
			try {
				getConnectionContext().getExecutionContext().close();
				closeConnection();
				if (__dataSource == null)
					throw _err;
				/* @lineinfo:generated-code *//* @lineinfo:364^5 */

				// ************************************************************
				// #sql [getConnectionContext()] { BEGIN
				// :__jPt_result := TRIP_TYP(
				// :FROM_PLACE,
				// :TO_PLACE,
				// :DATETIME,
				// :N_SEATS,
				// :WEEK_DAYS,
				// :PERIODICITY);
				// END;
				// };
				// ************************************************************

				{
					// declare temps
					oracle.jdbc.OracleCallableStatement __sJT_st = null;
					sqlj.runtime.ref.DefaultContext __sJT_cc = getConnectionContext();
					if (__sJT_cc == null)
						sqlj.runtime.error.RuntimeRefErrors
								.raise_NULL_CONN_CTX();
					sqlj.runtime.ExecutionContext.OracleContext __sJT_ec = ((__sJT_cc
							.getExecutionContext() == null) ? sqlj.runtime.ExecutionContext
							.raiseNullExecCtx() : __sJT_cc
							.getExecutionContext().getOracleContext());
					try {
						String theSqlTS = "BEGIN\n       :1   := TRIP_TYP(\n       :2  ,\n       :3  ,\n       :4  ,\n       :5  ,\n       :6  ,\n       :7  );\n      END;";
						__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
								"5uclm.esi.cardroid.data.oracle.TripTyp",
								theSqlTS);
						if (__sJT_ec.isNew()) {
							__sJT_st.registerOutParameter(1, 2002,
									"ANDROID.TRIP_TYP");
						}
						// set IN parameters
						if (FROM_PLACE == null)
							__sJT_st.setNull(2, 2002, "ANDROID.PLACE_TYP");
						else
							__sJT_st.setORAData(2, FROM_PLACE);
						if (TO_PLACE == null)
							__sJT_st.setNull(3, 2002, "ANDROID.PLACE_TYP");
						else
							__sJT_st.setORAData(3, TO_PLACE);
						if (DATETIME == null)
							__sJT_st.setNull(4, 2002, "ANDROID.DATE_TYP");
						else
							__sJT_st.setORAData(4, DATETIME);
						__sJT_st.setInt(5, N_SEATS);
						if (WEEK_DAYS == null)
							__sJT_st.setNull(6, 2003, "ANDROID.WEEKDAYS_TYP");
						else
							__sJT_st.setORAData(6, WEEK_DAYS);
						__sJT_st.setString(7, PERIODICITY);
						// execute statement
						__sJT_ec.oracleExecuteUpdate();
						// retrieve OUT parameters
						__jPt_result = (uclm.esi.cardroid.data.oracle.Trip) __sJT_st
								.getORAData(1,
										uclm.esi.cardroid.data.oracle.Trip
												.getORADataFactory());
					} finally {
						__sJT_ec.oracleClose();
					}
				}

				// ************************************************************

				/* @lineinfo:user-code *//* @lineinfo:374^5 */
				__jPt_out[0] = __jPt_temp;
			} catch (java.sql.SQLException _err2) {
				try {
					getConnectionContext().getExecutionContext().close();
				} catch (java.sql.SQLException _sqle) {
				}
				throw _err;
			}
		}
		return __jPt_result;
	}

	public Trip tripTyp(Place FROM_PLACE, Place TO_PLACE, Date DATETIME,
			int N_SEATS, Trip[] __jPt_out) throws java.sql.SQLException {
		Trip __jPt_temp = (Trip) this;
		Trip __jPt_result;
		try {
			/* @lineinfo:generated-code *//* @lineinfo:395^5 */

			// ************************************************************
			// #sql [getConnectionContext()] { BEGIN
			// :__jPt_result := TRIP_TYP(
			// :FROM_PLACE,
			// :TO_PLACE,
			// :DATETIME,
			// :N_SEATS);
			// END;
			// };
			// ************************************************************

			{
				// declare temps
				oracle.jdbc.OracleCallableStatement __sJT_st = null;
				sqlj.runtime.ref.DefaultContext __sJT_cc = getConnectionContext();
				if (__sJT_cc == null)
					sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
				sqlj.runtime.ExecutionContext.OracleContext __sJT_ec = ((__sJT_cc
						.getExecutionContext() == null) ? sqlj.runtime.ExecutionContext
						.raiseNullExecCtx() : __sJT_cc.getExecutionContext()
						.getOracleContext());
				try {
					String theSqlTS = "BEGIN\n       :1   := TRIP_TYP(\n       :2  ,\n       :3  ,\n       :4  ,\n       :5  );\n      END;";
					__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
							"6uclm.esi.cardroid.data.oracle.TripTyp", theSqlTS);
					if (__sJT_ec.isNew()) {
						__sJT_st.registerOutParameter(1, 2002,
								"ANDROID.TRIP_TYP");
					}
					// set IN parameters
					if (FROM_PLACE == null)
						__sJT_st.setNull(2, 2002, "ANDROID.PLACE_TYP");
					else
						__sJT_st.setORAData(2, FROM_PLACE);
					if (TO_PLACE == null)
						__sJT_st.setNull(3, 2002, "ANDROID.PLACE_TYP");
					else
						__sJT_st.setORAData(3, TO_PLACE);
					if (DATETIME == null)
						__sJT_st.setNull(4, 2002, "ANDROID.DATE_TYP");
					else
						__sJT_st.setORAData(4, DATETIME);
					__sJT_st.setInt(5, N_SEATS);
					// execute statement
					__sJT_ec.oracleExecuteUpdate();
					// retrieve OUT parameters
					__jPt_result = (uclm.esi.cardroid.data.oracle.Trip) __sJT_st
							.getORAData(1, uclm.esi.cardroid.data.oracle.Trip
									.getORADataFactory());
				} finally {
					__sJT_ec.oracleClose();
				}
			}

			// ************************************************************

			/* @lineinfo:user-code *//* @lineinfo:403^5 */
			__jPt_out[0] = __jPt_temp;
		} catch (java.sql.SQLException _err) {
			try {
				getConnectionContext().getExecutionContext().close();
				closeConnection();
				if (__dataSource == null)
					throw _err;
				/* @lineinfo:generated-code *//* @lineinfo:410^5 */

				// ************************************************************
				// #sql [getConnectionContext()] { BEGIN
				// :__jPt_result := TRIP_TYP(
				// :FROM_PLACE,
				// :TO_PLACE,
				// :DATETIME,
				// :N_SEATS);
				// END;
				// };
				// ************************************************************

				{
					// declare temps
					oracle.jdbc.OracleCallableStatement __sJT_st = null;
					sqlj.runtime.ref.DefaultContext __sJT_cc = getConnectionContext();
					if (__sJT_cc == null)
						sqlj.runtime.error.RuntimeRefErrors
								.raise_NULL_CONN_CTX();
					sqlj.runtime.ExecutionContext.OracleContext __sJT_ec = ((__sJT_cc
							.getExecutionContext() == null) ? sqlj.runtime.ExecutionContext
							.raiseNullExecCtx() : __sJT_cc
							.getExecutionContext().getOracleContext());
					try {
						String theSqlTS = "BEGIN\n       :1   := TRIP_TYP(\n       :2  ,\n       :3  ,\n       :4  ,\n       :5  );\n      END;";
						__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
								"7uclm.esi.cardroid.data.oracle.TripTyp",
								theSqlTS);
						if (__sJT_ec.isNew()) {
							__sJT_st.registerOutParameter(1, 2002,
									"ANDROID.TRIP_TYP");
						}
						// set IN parameters
						if (FROM_PLACE == null)
							__sJT_st.setNull(2, 2002, "ANDROID.PLACE_TYP");
						else
							__sJT_st.setORAData(2, FROM_PLACE);
						if (TO_PLACE == null)
							__sJT_st.setNull(3, 2002, "ANDROID.PLACE_TYP");
						else
							__sJT_st.setORAData(3, TO_PLACE);
						if (DATETIME == null)
							__sJT_st.setNull(4, 2002, "ANDROID.DATE_TYP");
						else
							__sJT_st.setORAData(4, DATETIME);
						__sJT_st.setInt(5, N_SEATS);
						// execute statement
						__sJT_ec.oracleExecuteUpdate();
						// retrieve OUT parameters
						__jPt_result = (uclm.esi.cardroid.data.oracle.Trip) __sJT_st
								.getORAData(1,
										uclm.esi.cardroid.data.oracle.Trip
												.getORADataFactory());
					} finally {
						__sJT_ec.oracleClose();
					}
				}

				// ************************************************************

				/* @lineinfo:user-code *//* @lineinfo:418^5 */
				__jPt_out[0] = __jPt_temp;
			} catch (java.sql.SQLException _err2) {
				try {
					getConnectionContext().getExecutionContext().close();
				} catch (java.sql.SQLException _sqle) {
				}
				throw _err;
			}
		}
		return __jPt_result;
	}

	public Trip tripTyp(Place FROM_PLACE, Place TO_PLACE, Date DATETIME,
			Date RETURN_DATETIME, int N_SEATS, Trip[] __jPt_out)
			throws java.sql.SQLException {
		Trip __jPt_temp = (Trip) this;
		Trip __jPt_result;
		try {
			/* @lineinfo:generated-code *//* @lineinfo:440^5 */

			// ************************************************************
			// #sql [getConnectionContext()] { BEGIN
			// :__jPt_result := TRIP_TYP(
			// :FROM_PLACE,
			// :TO_PLACE,
			// :DATETIME,
			// :RETURN_DATETIME,
			// :N_SEATS);
			// END;
			// };
			// ************************************************************

			{
				// declare temps
				oracle.jdbc.OracleCallableStatement __sJT_st = null;
				sqlj.runtime.ref.DefaultContext __sJT_cc = getConnectionContext();
				if (__sJT_cc == null)
					sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
				sqlj.runtime.ExecutionContext.OracleContext __sJT_ec = ((__sJT_cc
						.getExecutionContext() == null) ? sqlj.runtime.ExecutionContext
						.raiseNullExecCtx() : __sJT_cc.getExecutionContext()
						.getOracleContext());
				try {
					String theSqlTS = "BEGIN\n       :1   := TRIP_TYP(\n       :2  ,\n       :3  ,\n       :4  ,\n       :5  ,\n       :6  );\n      END;";
					__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
							"8uclm.esi.cardroid.data.oracle.TripTyp", theSqlTS);
					if (__sJT_ec.isNew()) {
						__sJT_st.registerOutParameter(1, 2002,
								"ANDROID.TRIP_TYP");
					}
					// set IN parameters
					if (FROM_PLACE == null)
						__sJT_st.setNull(2, 2002, "ANDROID.PLACE_TYP");
					else
						__sJT_st.setORAData(2, FROM_PLACE);
					if (TO_PLACE == null)
						__sJT_st.setNull(3, 2002, "ANDROID.PLACE_TYP");
					else
						__sJT_st.setORAData(3, TO_PLACE);
					if (DATETIME == null)
						__sJT_st.setNull(4, 2002, "ANDROID.DATE_TYP");
					else
						__sJT_st.setORAData(4, DATETIME);
					if (RETURN_DATETIME == null)
						__sJT_st.setNull(5, 2002, "ANDROID.DATE_TYP");
					else
						__sJT_st.setORAData(5, RETURN_DATETIME);
					__sJT_st.setInt(6, N_SEATS);
					// execute statement
					__sJT_ec.oracleExecuteUpdate();
					// retrieve OUT parameters
					__jPt_result = (uclm.esi.cardroid.data.oracle.Trip) __sJT_st
							.getORAData(1, uclm.esi.cardroid.data.oracle.Trip
									.getORADataFactory());
				} finally {
					__sJT_ec.oracleClose();
				}
			}

			// ************************************************************

			/* @lineinfo:user-code *//* @lineinfo:449^5 */
			__jPt_out[0] = __jPt_temp;
		} catch (java.sql.SQLException _err) {
			try {
				getConnectionContext().getExecutionContext().close();
				closeConnection();
				if (__dataSource == null)
					throw _err;
				/* @lineinfo:generated-code *//* @lineinfo:456^5 */

				// ************************************************************
				// #sql [getConnectionContext()] { BEGIN
				// :__jPt_result := TRIP_TYP(
				// :FROM_PLACE,
				// :TO_PLACE,
				// :DATETIME,
				// :RETURN_DATETIME,
				// :N_SEATS);
				// END;
				// };
				// ************************************************************

				{
					// declare temps
					oracle.jdbc.OracleCallableStatement __sJT_st = null;
					sqlj.runtime.ref.DefaultContext __sJT_cc = getConnectionContext();
					if (__sJT_cc == null)
						sqlj.runtime.error.RuntimeRefErrors
								.raise_NULL_CONN_CTX();
					sqlj.runtime.ExecutionContext.OracleContext __sJT_ec = ((__sJT_cc
							.getExecutionContext() == null) ? sqlj.runtime.ExecutionContext
							.raiseNullExecCtx() : __sJT_cc
							.getExecutionContext().getOracleContext());
					try {
						String theSqlTS = "BEGIN\n       :1   := TRIP_TYP(\n       :2  ,\n       :3  ,\n       :4  ,\n       :5  ,\n       :6  );\n      END;";
						__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
								"9uclm.esi.cardroid.data.oracle.TripTyp",
								theSqlTS);
						if (__sJT_ec.isNew()) {
							__sJT_st.registerOutParameter(1, 2002,
									"ANDROID.TRIP_TYP");
						}
						// set IN parameters
						if (FROM_PLACE == null)
							__sJT_st.setNull(2, 2002, "ANDROID.PLACE_TYP");
						else
							__sJT_st.setORAData(2, FROM_PLACE);
						if (TO_PLACE == null)
							__sJT_st.setNull(3, 2002, "ANDROID.PLACE_TYP");
						else
							__sJT_st.setORAData(3, TO_PLACE);
						if (DATETIME == null)
							__sJT_st.setNull(4, 2002, "ANDROID.DATE_TYP");
						else
							__sJT_st.setORAData(4, DATETIME);
						if (RETURN_DATETIME == null)
							__sJT_st.setNull(5, 2002, "ANDROID.DATE_TYP");
						else
							__sJT_st.setORAData(5, RETURN_DATETIME);
						__sJT_st.setInt(6, N_SEATS);
						// execute statement
						__sJT_ec.oracleExecuteUpdate();
						// retrieve OUT parameters
						__jPt_result = (uclm.esi.cardroid.data.oracle.Trip) __sJT_st
								.getORAData(1,
										uclm.esi.cardroid.data.oracle.Trip
												.getORADataFactory());
					} finally {
						__sJT_ec.oracleClose();
					}
				}

				// ************************************************************

				/* @lineinfo:user-code *//* @lineinfo:465^5 */
				__jPt_out[0] = __jPt_temp;
			} catch (java.sql.SQLException _err2) {
				try {
					getConnectionContext().getExecutionContext().close();
				} catch (java.sql.SQLException _sqle) {
				}
				throw _err;
			}
		}
		return __jPt_result;
	}
}/* @lineinfo:generated-code */