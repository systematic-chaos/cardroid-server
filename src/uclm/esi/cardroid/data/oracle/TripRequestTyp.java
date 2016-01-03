/*@lineinfo:filename=TripRequest*//*@lineinfo:user-code*//*@lineinfo:1^1*/package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;

import oracle.jpub.runtime.MutableStruct;

import sqlj.runtime.ref.DefaultContext;

public class TripRequestTyp extends Trip implements ORAData, ORADataFactory {
	public static final String _SQL_NAME = "ANDROID.TRIP_REQUEST_TYP";
	public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

	protected static int[] _sqlType = { 4, 2002, 2002, 2002, 4, 2002, 2003, 12,
			4, 12, 4, 2002 };
	protected static ORADataFactory[] _factory = new ORADataFactory[12];
	static {
		_factory[1] = Place.getORADataFactory();
		_factory[2] = Place.getORADataFactory();
		_factory[3] = Date.getORADataFactory();
		_factory[5] = Date.getORADataFactory();
		_factory[6] = Trip_WeekDaysVarray.getORADataFactory();
		_factory[11] = User.getORADataFactory();
	}
	protected static final TripRequestTyp _TripRequestTypFactory = new TripRequestTyp();

	public static ORADataFactory getORADataFactory() {
		return _TripRequestTypFactory;
	}

	static {
		_map.put("ANDROID.TRIP_REQUEST_TYP", _TripRequestTypFactory);
	}

	/* constructors */

	protected void _init_struct(boolean init) {
		if (init)
			_struct = new MutableStruct(new Object[12], _sqlType, _factory);
	}

	public TripRequestTyp() {
		_init_struct(true);
		__tx = DefaultContext.getDefaultContext();
	}

	public TripRequestTyp(DefaultContext c) /* throws SQLException */
	{
		_init_struct(true);
		__tx = c;
	}

	public TripRequestTyp(Connection c) /* throws SQLException */
	{
		_init_struct(true);
		__onn = c;
	}

	public TripRequestTyp(int tripId, Place fromPlace, Place toPlace,
			Date datetime, int nSeats, Date returnDatetime,
			Trip_WeekDaysVarray weekDays, String periodicity, int distance,
			String characteristics, int tripType, User requester)
			throws SQLException {
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
		setTripRequester(requester);
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

	public void setFrom(TripRequestTyp o) throws SQLException {
		setContextFrom(o);
		setValueFrom(o);
	}

	protected void setContextFrom(TripRequestTyp o) throws SQLException {
		release();
		__tx = o.__tx;
		__onn = o.__onn;
	}

	protected void setValueFrom(TripRequestTyp o) {
		_struct = o._struct;
	}

	protected ORAData create(TripRequestTyp o, Datum d, int sqlType)
			throws SQLException {
		if (d == null) {
			if (o != null) {
				o.release();
			}
			;
			return null;
		}
		if (o == null)
			o = new TripRequest();
		o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
		o.__onn = ((STRUCT) d).getJavaSqlConnection();
		return o;
	}

	protected ORAData createExact(Datum d, int sqlType) throws SQLException {
		return create(null, d, sqlType);
	}

	/* accessor methods */

	public User getRequester() throws SQLException {
		return (User) _struct.getAttribute(11);
	}

	public void setTripRequester(User requester) throws SQLException {
		_struct.setAttribute(11, requester);
	}

	public String toString() {
		try {
			return "ANDROID.TRIP_REQUEST_TYP" + "("
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
							+ getCharacteristics() + "'") + "," + getTripType()
					+ "," + getRequester() + ")";
		} catch (Exception e) {
			return e.toString();
		}
	}

	public java.sql.Timestamp compareTo() throws java.sql.SQLException {
		TripTyp __jPt_temp = this;
		java.sql.Timestamp __jPt_result;
		try {
			/* @lineinfo:generated-code *//* @lineinfo:122^5 */

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
							"0uclm.esi.cardroid.data.oracle.TripRequestTyp",
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

			/* @lineinfo:user-code *//* @lineinfo:126^5 */
		} catch (java.sql.SQLException _err) {
			try {
				getConnectionContext().getExecutionContext().close();
				closeConnection();
				if (__dataSource == null)
					throw _err;
				/* @lineinfo:generated-code *//* @lineinfo:132^5 */

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
						__sJT_st = __sJT_ec
								.prepareOracleCall(
										__sJT_cc,
										"1uclm.esi.cardroid.data.oracle.TripRequestTyp",
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

				/* @lineinfo:user-code *//* @lineinfo:136^5 */
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

	public TripRequest tripRequestTyp(Place FROM_PLACE, Place TO_PLACE,
			DateTimePrefs DATETIME, DateTimePrefs RETURN_DATETIME,
			User REQUESTER, int REQUESTED_SEATS, Trip_WeekDaysVarray WEEK_DAYS,
			String PERIODICITY, TripRequest[] __jPt_out)
			throws java.sql.SQLException {
		TripRequest __jPt_temp = (TripRequest) this;
		TripRequest __jPt_result;
		try {
			/* @lineinfo:generated-code *//* @lineinfo:160^5 */

			// ************************************************************
			// #sql [getConnectionContext()] { BEGIN
			// :__jPt_result := TRIP_REQUEST_TYP(
			// :FROM_PLACE,
			// :TO_PLACE,
			// :DATETIME,
			// :RETURN_DATETIME,
			// :REQUESTER,
			// :REQUESTED_SEATS,
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
					String theSqlTS = "BEGIN\n       :1   := TRIP_REQUEST_TYP(\n       :2  ,\n       :3  ,\n       :4  ,\n       :5  ,\n       :6  ,\n       :7  ,\n       :8  ,\n       :9  );\n      END;";
					__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
							"2uclm.esi.cardroid.data.oracle.TripRequestTyp",
							theSqlTS);
					if (__sJT_ec.isNew()) {
						__sJT_st.registerOutParameter(1, 2002,
								"ANDROID.TRIP_REQUEST_TYP");
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
						__sJT_st.setNull(4, 2002, "ANDROID.DATE_TIME_PREFS_TYP");
					else
						__sJT_st.setORAData(4, DATETIME);
					if (RETURN_DATETIME == null)
						__sJT_st.setNull(5, 2002, "ANDROID.DATE_TIME_PREFS_TYP");
					else
						__sJT_st.setORAData(5, RETURN_DATETIME);
					if (REQUESTER == null)
						__sJT_st.setNull(6, 2002, "ANDROID.USER_TYP");
					else
						__sJT_st.setORAData(6, REQUESTER);
					__sJT_st.setInt(7, REQUESTED_SEATS);
					if (WEEK_DAYS == null)
						__sJT_st.setNull(8, 2003, "ANDROID.WEEKDAYS_TYP");
					else
						__sJT_st.setORAData(8, WEEK_DAYS);
					__sJT_st.setString(9, PERIODICITY);
					// execute statement
					__sJT_ec.oracleExecuteUpdate();
					// retrieve OUT parameters
					__jPt_result = (uclm.esi.cardroid.data.oracle.TripRequest) __sJT_st
							.getORAData(1,
									uclm.esi.cardroid.data.oracle.TripRequest
											.getORADataFactory());
				} finally {
					__sJT_ec.oracleClose();
				}
			}

			// ************************************************************

			/* @lineinfo:user-code *//* @lineinfo:172^5 */
			__jPt_out[0] = __jPt_temp;
		} catch (java.sql.SQLException _err) {
			try {
				getConnectionContext().getExecutionContext().close();
				closeConnection();
				if (__dataSource == null)
					throw _err;
				/* @lineinfo:generated-code *//* @lineinfo:179^5 */

				// ************************************************************
				// #sql [getConnectionContext()] { BEGIN
				// :__jPt_result := TRIP_REQUEST_TYP(
				// :FROM_PLACE,
				// :TO_PLACE,
				// :DATETIME,
				// :RETURN_DATETIME,
				// :REQUESTER,
				// :REQUESTED_SEATS,
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
						String theSqlTS = "BEGIN\n       :1   := TRIP_REQUEST_TYP(\n       :2  ,\n       :3  ,\n       :4  ,\n       :5  ,\n       :6  ,\n       :7  ,\n       :8  ,\n       :9  );\n      END;";
						__sJT_st = __sJT_ec
								.prepareOracleCall(
										__sJT_cc,
										"3uclm.esi.cardroid.data.oracle.TripRequestTyp",
										theSqlTS);
						if (__sJT_ec.isNew()) {
							__sJT_st.registerOutParameter(1, 2002,
									"ANDROID.TRIP_REQUEST_TYP");
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
							__sJT_st.setNull(4, 2002,
									"ANDROID.DATE_TIME_PREFS_TYP");
						else
							__sJT_st.setORAData(4, DATETIME);
						if (RETURN_DATETIME == null)
							__sJT_st.setNull(5, 2002,
									"ANDROID.DATE_TIME_PREFS_TYP");
						else
							__sJT_st.setORAData(5, RETURN_DATETIME);
						if (REQUESTER == null)
							__sJT_st.setNull(6, 2002, "ANDROID.USER_TYP");
						else
							__sJT_st.setORAData(6, REQUESTER);
						__sJT_st.setInt(7, REQUESTED_SEATS);
						if (WEEK_DAYS == null)
							__sJT_st.setNull(8, 2003, "ANDROID.WEEKDAYS_TYP");
						else
							__sJT_st.setORAData(8, WEEK_DAYS);
						__sJT_st.setString(9, PERIODICITY);
						// execute statement
						__sJT_ec.oracleExecuteUpdate();
						// retrieve OUT parameters
						__jPt_result = (uclm.esi.cardroid.data.oracle.TripRequest) __sJT_st
								.getORAData(
										1,
										uclm.esi.cardroid.data.oracle.TripRequest
												.getORADataFactory());
					} finally {
						__sJT_ec.oracleClose();
					}
				}

				// ************************************************************

				/* @lineinfo:user-code *//* @lineinfo:191^5 */
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

	public TripRequest tripRequestTyp(Place FROM_PLACE, Place TO_PLACE,
			DateTimePrefs DATETIME, User REQUESTER, int REQUESTED_SEATS,
			Trip_WeekDaysVarray WEEK_DAYS, String PERIODICITY,
			TripRequest[] __jPt_out) throws java.sql.SQLException {
		TripRequest __jPt_temp = (TripRequest) this;
		TripRequest __jPt_result;
		try {
			/* @lineinfo:generated-code *//* @lineinfo:215^5 */

			// ************************************************************
			// #sql [getConnectionContext()] { BEGIN
			// :__jPt_result := TRIP_REQUEST_TYP(
			// :FROM_PLACE,
			// :TO_PLACE,
			// :DATETIME,
			// :REQUESTER,
			// :REQUESTED_SEATS,
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
					String theSqlTS = "BEGIN\n       :1   := TRIP_REQUEST_TYP(\n       :2  ,\n       :3  ,\n       :4  ,\n       :5  ,\n       :6  ,\n       :7  ,\n       :8  );\n      END;";
					__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
							"4uclm.esi.cardroid.data.oracle.TripRequestTyp",
							theSqlTS);
					if (__sJT_ec.isNew()) {
						__sJT_st.registerOutParameter(1, 2002,
								"ANDROID.TRIP_REQUEST_TYP");
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
						__sJT_st.setNull(4, 2002, "ANDROID.DATE_TIME_PREFS_TYP");
					else
						__sJT_st.setORAData(4, DATETIME);
					if (REQUESTER == null)
						__sJT_st.setNull(5, 2002, "ANDROID.USER_TYP");
					else
						__sJT_st.setORAData(5, REQUESTER);
					__sJT_st.setInt(6, REQUESTED_SEATS);
					if (WEEK_DAYS == null)
						__sJT_st.setNull(7, 2003, "ANDROID.WEEKDAYS_TYP");
					else
						__sJT_st.setORAData(7, WEEK_DAYS);
					__sJT_st.setString(8, PERIODICITY);
					// execute statement
					__sJT_ec.oracleExecuteUpdate();
					// retrieve OUT parameters
					__jPt_result = (uclm.esi.cardroid.data.oracle.TripRequest) __sJT_st
							.getORAData(1,
									uclm.esi.cardroid.data.oracle.TripRequest
											.getORADataFactory());
				} finally {
					__sJT_ec.oracleClose();
				}
			}

			// ************************************************************

			/* @lineinfo:user-code *//* @lineinfo:226^5 */
			__jPt_out[0] = __jPt_temp;
		} catch (java.sql.SQLException _err) {
			try {
				getConnectionContext().getExecutionContext().close();
				closeConnection();
				if (__dataSource == null)
					throw _err;
				/* @lineinfo:generated-code *//* @lineinfo:233^5 */

				// ************************************************************
				// #sql [getConnectionContext()] { BEGIN
				// :__jPt_result := TRIP_REQUEST_TYP(
				// :FROM_PLACE,
				// :TO_PLACE,
				// :DATETIME,
				// :REQUESTER,
				// :REQUESTED_SEATS,
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
						String theSqlTS = "BEGIN\n       :1   := TRIP_REQUEST_TYP(\n       :2  ,\n       :3  ,\n       :4  ,\n       :5  ,\n       :6  ,\n       :7  ,\n       :8  );\n      END;";
						__sJT_st = __sJT_ec
								.prepareOracleCall(
										__sJT_cc,
										"5uclm.esi.cardroid.data.oracle.TripRequestTyp",
										theSqlTS);
						if (__sJT_ec.isNew()) {
							__sJT_st.registerOutParameter(1, 2002,
									"ANDROID.TRIP_REQUEST_TYP");
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
							__sJT_st.setNull(4, 2002,
									"ANDROID.DATE_TIME_PREFS_TYP");
						else
							__sJT_st.setORAData(4, DATETIME);
						if (REQUESTER == null)
							__sJT_st.setNull(5, 2002, "ANDROID.USER_TYP");
						else
							__sJT_st.setORAData(5, REQUESTER);
						__sJT_st.setInt(6, REQUESTED_SEATS);
						if (WEEK_DAYS == null)
							__sJT_st.setNull(7, 2003, "ANDROID.WEEKDAYS_TYP");
						else
							__sJT_st.setORAData(7, WEEK_DAYS);
						__sJT_st.setString(8, PERIODICITY);
						// execute statement
						__sJT_ec.oracleExecuteUpdate();
						// retrieve OUT parameters
						__jPt_result = (uclm.esi.cardroid.data.oracle.TripRequest) __sJT_st
								.getORAData(
										1,
										uclm.esi.cardroid.data.oracle.TripRequest
												.getORADataFactory());
					} finally {
						__sJT_ec.oracleClose();
					}
				}

				// ************************************************************

				/* @lineinfo:user-code *//* @lineinfo:244^5 */
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

	public TripRequest tripRequestTyp(Place FROM_PLACE, Place TO_PLACE,
			DateTimePrefs DATETIME, User REQUESTER, int REQUESTED_SEATS,
			TripRequest[] __jPt_out) throws java.sql.SQLException {
		TripRequest __jPt_temp = (TripRequest) this;
		TripRequest __jPt_result;
		try {
			/* @lineinfo:generated-code *//* @lineinfo:266^5 */

			// ************************************************************
			// #sql [getConnectionContext()] { BEGIN
			// :__jPt_result := TRIP_REQUEST_TYP(
			// :FROM_PLACE,
			// :TO_PLACE,
			// :DATETIME,
			// :REQUESTER,
			// :REQUESTED_SEATS);
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
					String theSqlTS = "BEGIN\n       :1   := TRIP_REQUEST_TYP(\n       :2  ,\n       :3  ,\n       :4  ,\n       :5  ,\n       :6  );\n      END;";
					__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
							"6uclm.esi.cardroid.data.oracle.TripRequestTyp",
							theSqlTS);
					if (__sJT_ec.isNew()) {
						__sJT_st.registerOutParameter(1, 2002,
								"ANDROID.TRIP_REQUEST_TYP");
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
						__sJT_st.setNull(4, 2002, "ANDROID.DATE_TIME_PREFS_TYP");
					else
						__sJT_st.setORAData(4, DATETIME);
					if (REQUESTER == null)
						__sJT_st.setNull(5, 2002, "ANDROID.USER_TYP");
					else
						__sJT_st.setORAData(5, REQUESTER);
					__sJT_st.setInt(6, REQUESTED_SEATS);
					// execute statement
					__sJT_ec.oracleExecuteUpdate();
					// retrieve OUT parameters
					__jPt_result = (uclm.esi.cardroid.data.oracle.TripRequest) __sJT_st
							.getORAData(1,
									uclm.esi.cardroid.data.oracle.TripRequest
											.getORADataFactory());
				} finally {
					__sJT_ec.oracleClose();
				}
			}

			// ************************************************************

			/* @lineinfo:user-code *//* @lineinfo:275^5 */
			__jPt_out[0] = __jPt_temp;
		} catch (java.sql.SQLException _err) {
			try {
				getConnectionContext().getExecutionContext().close();
				closeConnection();
				if (__dataSource == null)
					throw _err;
				/* @lineinfo:generated-code *//* @lineinfo:282^5 */

				// ************************************************************
				// #sql [getConnectionContext()] { BEGIN
				// :__jPt_result := TRIP_REQUEST_TYP(
				// :FROM_PLACE,
				// :TO_PLACE,
				// :DATETIME,
				// :REQUESTER,
				// :REQUESTED_SEATS);
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
						String theSqlTS = "BEGIN\n       :1   := TRIP_REQUEST_TYP(\n       :2  ,\n       :3  ,\n       :4  ,\n       :5  ,\n       :6  );\n      END;";
						__sJT_st = __sJT_ec
								.prepareOracleCall(
										__sJT_cc,
										"7uclm.esi.cardroid.data.oracle.TripRequestTyp",
										theSqlTS);
						if (__sJT_ec.isNew()) {
							__sJT_st.registerOutParameter(1, 2002,
									"ANDROID.TRIP_REQUEST_TYP");
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
							__sJT_st.setNull(4, 2002,
									"ANDROID.DATE_TIME_PREFS_TYP");
						else
							__sJT_st.setORAData(4, DATETIME);
						if (REQUESTER == null)
							__sJT_st.setNull(5, 2002, "ANDROID.USER_TYP");
						else
							__sJT_st.setORAData(5, REQUESTER);
						__sJT_st.setInt(6, REQUESTED_SEATS);
						// execute statement
						__sJT_ec.oracleExecuteUpdate();
						// retrieve OUT parameters
						__jPt_result = (uclm.esi.cardroid.data.oracle.TripRequest) __sJT_st
								.getORAData(
										1,
										uclm.esi.cardroid.data.oracle.TripRequest
												.getORADataFactory());
					} finally {
						__sJT_ec.oracleClose();
					}
				}

				// ************************************************************

				/* @lineinfo:user-code *//* @lineinfo:291^5 */
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

	public TripRequest tripRequestTyp(Place FROM_PLACE, Place TO_PLACE,
			DateTimePrefs DATETIME, DateTimePrefs RETURN_DATETIME,
			User REQUESTER, int REQUESTED_SEATS, TripRequest[] __jPt_out)
			throws java.sql.SQLException {
		TripRequest __jPt_temp = (TripRequest) this;
		TripRequest __jPt_result;
		try {
			/* @lineinfo:generated-code *//* @lineinfo:314^5 */

			// ************************************************************
			// #sql [getConnectionContext()] { BEGIN
			// :__jPt_result := TRIP_REQUEST_TYP(
			// :FROM_PLACE,
			// :TO_PLACE,
			// :DATETIME,
			// :RETURN_DATETIME,
			// :REQUESTER,
			// :REQUESTED_SEATS);
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
					String theSqlTS = "BEGIN\n       :1   := TRIP_REQUEST_TYP(\n       :2  ,\n       :3  ,\n       :4  ,\n       :5  ,\n       :6  ,\n       :7  );\n      END;";
					__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
							"8uclm.esi.cardroid.data.oracle.TripRequestTyp",
							theSqlTS);
					if (__sJT_ec.isNew()) {
						__sJT_st.registerOutParameter(1, 2002,
								"ANDROID.TRIP_REQUEST_TYP");
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
						__sJT_st.setNull(4, 2002, "ANDROID.DATE_TIME_PREFS_TYP");
					else
						__sJT_st.setORAData(4, DATETIME);
					if (RETURN_DATETIME == null)
						__sJT_st.setNull(5, 2002, "ANDROID.DATE_TIME_PREFS_TYP");
					else
						__sJT_st.setORAData(5, RETURN_DATETIME);
					if (REQUESTER == null)
						__sJT_st.setNull(6, 2002, "ANDROID.USER_TYP");
					else
						__sJT_st.setORAData(6, REQUESTER);
					__sJT_st.setInt(7, REQUESTED_SEATS);
					// execute statement
					__sJT_ec.oracleExecuteUpdate();
					// retrieve OUT parameters
					__jPt_result = (uclm.esi.cardroid.data.oracle.TripRequest) __sJT_st
							.getORAData(1,
									uclm.esi.cardroid.data.oracle.TripRequest
											.getORADataFactory());
				} finally {
					__sJT_ec.oracleClose();
				}
			}

			// ************************************************************

			/* @lineinfo:user-code *//* @lineinfo:324^5 */
			__jPt_out[0] = __jPt_temp;
		} catch (java.sql.SQLException _err) {
			try {
				getConnectionContext().getExecutionContext().close();
				closeConnection();
				if (__dataSource == null)
					throw _err;
				/* @lineinfo:generated-code *//* @lineinfo:331^5 */

				// ************************************************************
				// #sql [getConnectionContext()] { BEGIN
				// :__jPt_result := TRIP_REQUEST_TYP(
				// :FROM_PLACE,
				// :TO_PLACE,
				// :DATETIME,
				// :RETURN_DATETIME,
				// :REQUESTER,
				// :REQUESTED_SEATS);
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
						String theSqlTS = "BEGIN\n       :1   := TRIP_REQUEST_TYP(\n       :2  ,\n       :3  ,\n       :4  ,\n       :5  ,\n       :6  ,\n       :7  );\n      END;";
						__sJT_st = __sJT_ec
								.prepareOracleCall(
										__sJT_cc,
										"9uclm.esi.cardroid.data.oracle.TripRequestTyp",
										theSqlTS);
						if (__sJT_ec.isNew()) {
							__sJT_st.registerOutParameter(1, 2002,
									"ANDROID.TRIP_REQUEST_TYP");
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
							__sJT_st.setNull(4, 2002,
									"ANDROID.DATE_TIME_PREFS_TYP");
						else
							__sJT_st.setORAData(4, DATETIME);
						if (RETURN_DATETIME == null)
							__sJT_st.setNull(5, 2002,
									"ANDROID.DATE_TIME_PREFS_TYP");
						else
							__sJT_st.setORAData(5, RETURN_DATETIME);
						if (REQUESTER == null)
							__sJT_st.setNull(6, 2002, "ANDROID.USER_TYP");
						else
							__sJT_st.setORAData(6, REQUESTER);
						__sJT_st.setInt(7, REQUESTED_SEATS);
						// execute statement
						__sJT_ec.oracleExecuteUpdate();
						// retrieve OUT parameters
						__jPt_result = (uclm.esi.cardroid.data.oracle.TripRequest) __sJT_st
								.getORAData(
										1,
										uclm.esi.cardroid.data.oracle.TripRequest
												.getORADataFactory());
					} finally {
						__sJT_ec.oracleClose();
					}
				}

				// ************************************************************

				/* @lineinfo:user-code *//* @lineinfo:341^5 */
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