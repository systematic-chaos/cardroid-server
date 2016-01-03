/*@lineinfo:filename=TripOffer*//*@lineinfo:user-code*//*@lineinfo:1^1*/package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;

import oracle.jpub.runtime.MutableStruct;

import sqlj.runtime.ref.DefaultContext;

public class TripOfferTyp extends Trip implements ORAData, ORADataFactory {
	public static final String _SQL_NAME = "ANDROID.TRIP_OFFER_TYP";
	public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

	protected static int[] _sqlType = { 4, 2002, 2002, 2002, 4, 2002, 2003, 12,
			4, 12, 4, 2002, 2003, 2002, 2003, 8, 2003 };
	protected static ORADataFactory[] _factory = new ORADataFactory[17];
	static {
		_factory[1] = Place.getORADataFactory();
		_factory[2] = Place.getORADataFactory();
		_factory[3] = Date.getORADataFactory();
		_factory[5] = Date.getORADataFactory();
		_factory[6] = Trip_WeekDaysVarray.getORADataFactory();
		_factory[11] = User.getORADataFactory();
		_factory[12] = TripOffer_WaypointCollection.getORADataFactory();
		_factory[13] = Car.getORADataFactory();
		_factory[14] = TripOffer_PassengerCollection.getORADataFactory();
		_factory[16] = TripOffer_AllowedVarray.getORADataFactory();
	}
	protected static final TripOfferTyp _TripOfferTypFactory = new TripOfferTyp();

	public static ORADataFactory getORADataFactory() {
		return _TripOfferTypFactory;
	}

	static {
		_map.put("ANDROID.TRIP_OFFER_TYP", _TripOfferTypFactory);
	}

	/* constructors */

	protected void _init_struct(boolean init) {
		if (init)
			_struct = new MutableStruct(new Object[17], _sqlType, _factory);
	}

	public TripOfferTyp() {
		_init_struct(true);
		__tx = DefaultContext.getDefaultContext();
	}

	public TripOfferTyp(DefaultContext c) /* throws SQLException */
	{
		_init_struct(true);
		__tx = c;
	}

	public TripOfferTyp(Connection c) /* throws SQLException */
	{
		_init_struct(true);
		__onn = c;
	}

	public TripOfferTyp(int tripId, Place fromPlace, Place toPlace,
			Date datetime, int nSeats, Date returnDatetime,
			Trip_WeekDaysVarray weekDays, String periodicity, int distance,
			String characteristics, int tripType, User driver,
			TripOffer_WaypointCollection waypoints, Car car,
			TripOffer_PassengerCollection passengers, double price,
			TripOffer_AllowedVarray allowed) throws SQLException {
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
		setTripDriver(driver);
		setWaypointsCollection(waypoints);
		setTripCar(car);
		setPassengersCollection(passengers);
		setPrice(price);
		setAllowed(allowed);
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

	public void setFrom(TripOfferTyp o) throws SQLException {
		setContextFrom(o);
		setValueFrom(o);
	}

	protected void setContextFrom(TripOfferTyp o) throws SQLException {
		release();
		__tx = o.__tx;
		__onn = o.__onn;
	}

	protected void setValueFrom(TripOfferTyp o) {
		_struct = o._struct;
	}

	protected ORAData create(TripOfferTyp o, Datum d, int sqlType)
			throws SQLException {
		if (d == null) {
			if (o != null) {
				o.release();
			}
			;
			return null;
		}
		if (o == null)
			o = new TripOffer();
		o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
		o.__onn = ((STRUCT) d).getJavaSqlConnection();
		return o;
	}

	protected ORAData createExact(Datum d, int sqlType) throws SQLException {
		return create(null, d, sqlType);
	}

	/* accessor methods */

	public User getDriver() throws SQLException {
		return (User) _struct.getAttribute(11);
	}

	public void setTripDriver(User driver) throws SQLException {
		_struct.setAttribute(11, driver);
	}

	public TripOffer_WaypointCollection getWaypointsCollection()
			throws SQLException {
		return (TripOffer_WaypointCollection) _struct.getAttribute(12);
	}

	public void setWaypointsCollection(TripOffer_WaypointCollection waypoints)
			throws SQLException {
		_struct.setAttribute(12, waypoints);
	}

	public Car getCar() throws SQLException {
		return (Car) _struct.getAttribute(13);
	}

	public void setTripCar(Car car) throws SQLException {
		_struct.setAttribute(13, car);
	}

	public TripOffer_PassengerCollection getPassengersCollection()
			throws SQLException {
		return (TripOffer_PassengerCollection) _struct.getAttribute(14);
	}

	public void setPassengersCollection(TripOffer_PassengerCollection passengers)
			throws SQLException {
		_struct.setAttribute(14, passengers);
	}

	public double getPrice() throws SQLException {
		return ((Double) _struct.getAttribute(15)).doubleValue();
	}

	public void setPrice(double price) throws SQLException {
		_struct.setAttribute(15, new Double(price));
	}

	public TripOffer_AllowedVarray getAllowedVarray() throws SQLException {
		return (TripOffer_AllowedVarray) _struct.getAttribute(16);
	}

	public void setAllowed(TripOffer_AllowedVarray allowed) throws SQLException {
		_struct.setAttribute(16, allowed);
	}

	public String toString() {
		try {
			return "ANDROID.TRIP_OFFER_TYP" + "("
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
					+ "," + getDriver() + "," + getWaypointsCollection() + ","
					+ getCar() + "," + getPassengersCollection() + ","
					+ getPrice() + "," + getAllowedVarray() + ")";
		} catch (Exception e) {
			return e.toString();
		}
	}

	public TripOffer addTripPassenger(User PASSENGER, int SEATS)
			throws java.sql.SQLException {
		try {
			TripOffer __jPt_temp = (TripOffer) this;
			/* @lineinfo:generated-code *//* @lineinfo:171^5 */

			// ************************************************************
			// #sql [getConnectionContext()] { BEGIN
			// :__jPt_temp.ADD_PASSENGER(
			// :PASSENGER);
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
					String theSqlTS = "BEGIN\n       :1  .ADD_PASSENGER(\n       :2  ,\n       :3  );\n      END;";
					__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
							"0uclm.esi.cardroid.data.oracle.TripOfferTyp",
							theSqlTS);
					if (__sJT_ec.isNew()) {
						__sJT_st.registerOutParameter(1, 2002,
								"ANDROID.TRIP_OFFER_TYP");
					}
					// set IN parameters
					if (__jPt_temp == null)
						__sJT_st.setNull(1, 2002, "ANDROID.TRIP_OFFER_TYP");
					else
						__sJT_st.setORAData(1, __jPt_temp);
					if (PASSENGER == null)
						__sJT_st.setNull(2, 2002, "ANDROID.USER_TYP");
					else
						__sJT_st.setORAData(2, PASSENGER);
					__sJT_st.setInt(3, SEATS);
					// execute statement
					__sJT_ec.oracleExecuteUpdate();
					// retrieve OUT parameters
					__jPt_temp = (uclm.esi.cardroid.data.oracle.TripOffer) __sJT_st
							.getORAData(1,
									uclm.esi.cardroid.data.oracle.TripOffer
											.getORADataFactory());
				} finally {
					__sJT_ec.oracleClose();
				}
			}

			// ************************************************************

			/* @lineinfo:user-code *//* @lineinfo:176^5 */
			return __jPt_temp;
		} catch (java.sql.SQLException _err) {
			try {
				getConnectionContext().getExecutionContext().close();
				closeConnection();
				if (__dataSource == null)
					throw _err;
				TripOffer __jPt_temp = (TripOffer) this;
				/* @lineinfo:generated-code *//* @lineinfo:184^5 */

				// ************************************************************
				// #sql [getConnectionContext()] { BEGIN
				// :__jPt_temp.ADD_PASSENGER(
				// :PASSENGER);
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
						String theSqlTS = "BEGIN\n       :1  .ADD_PASSENGER(\n       :2  ,\n       :3  );\n      END;";
						__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
								"1uclm.esi.cardroid.data.oracle.TripOfferTyp",
								theSqlTS);
						if (__sJT_ec.isNew()) {
							__sJT_st.registerOutParameter(1, 2002,
									"ANDROID.TRIP_OFFER_TYP");
						}
						// set IN parameters
						if (__jPt_temp == null)
							__sJT_st.setNull(1, 2002, "ANDROID.TRIP_OFFER_TYP");
						else
							__sJT_st.setORAData(1, __jPt_temp);
						if (PASSENGER == null)
							__sJT_st.setNull(2, 2002, "ANDROID.USER_TYP");
						else
							__sJT_st.setORAData(2, PASSENGER);
						__sJT_st.setInt(3, SEATS);
						// execute statement
						__sJT_ec.oracleExecuteUpdate();
						// retrieve OUT parameters
						__jPt_temp = (uclm.esi.cardroid.data.oracle.TripOffer) __sJT_st
								.getORAData(1,
										uclm.esi.cardroid.data.oracle.TripOffer
												.getORADataFactory());
					} finally {
						__sJT_ec.oracleClose();
					}
				}

				// ************************************************************

				/* @lineinfo:user-code *//* @lineinfo:189^5 */
				return __jPt_temp;
			} catch (java.sql.SQLException _err2) {
				try {
					getConnectionContext().getExecutionContext().close();
				} catch (java.sql.SQLException _sqle) {
				}
				throw _err;
			}
		}
	}

	public TripOffer addTripWaypoint(Place WAYPOINT)
			throws java.sql.SQLException {
		try {
			TripOffer __jPt_temp = (TripOffer) this;
			/* @lineinfo:generated-code *//* @lineinfo:204^5 */

			// ************************************************************
			// #sql [getConnectionContext()] { BEGIN
			// :__jPt_temp.ADD_WAYPOINT(
			// :WAYPOINT);
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
					String theSqlTS = "BEGIN\n       :1  .ADD_WAYPOINT(\n       :2  );\n      END;";
					__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
							"2uclm.esi.cardroid.data.oracle.TripOfferTyp",
							theSqlTS);
					if (__sJT_ec.isNew()) {
						__sJT_st.registerOutParameter(1, 2002,
								"ANDROID.TRIP_OFFER_TYP");
					}
					// set IN parameters
					if (__jPt_temp == null)
						__sJT_st.setNull(1, 2002, "ANDROID.TRIP_OFFER_TYP");
					else
						__sJT_st.setORAData(1, __jPt_temp);
					if (WAYPOINT == null)
						__sJT_st.setNull(2, 2002, "ANDROID.PLACE_TYP");
					else
						__sJT_st.setORAData(2, WAYPOINT);
					// execute statement
					__sJT_ec.oracleExecuteUpdate();
					// retrieve OUT parameters
					__jPt_temp = (uclm.esi.cardroid.data.oracle.TripOffer) __sJT_st
							.getORAData(1,
									uclm.esi.cardroid.data.oracle.TripOffer
											.getORADataFactory());
				} finally {
					__sJT_ec.oracleClose();
				}
			}

			// ************************************************************

			/* @lineinfo:user-code *//* @lineinfo:209^5 */
			return __jPt_temp;
		} catch (java.sql.SQLException _err) {
			try {
				getConnectionContext().getExecutionContext().close();
				closeConnection();
				if (__dataSource == null)
					throw _err;
				TripOffer __jPt_temp = (TripOffer) this;
				/* @lineinfo:generated-code *//* @lineinfo:217^5 */

				// ************************************************************
				// #sql [getConnectionContext()] { BEGIN
				// :__jPt_temp.ADD_WAYPOINT(
				// :WAYPOINT);
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
						String theSqlTS = "BEGIN\n       :1  .ADD_WAYPOINT(\n       :2  );\n      END;";
						__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
								"3uclm.esi.cardroid.data.oracle.TripOfferTyp",
								theSqlTS);
						if (__sJT_ec.isNew()) {
							__sJT_st.registerOutParameter(1, 2002,
									"ANDROID.TRIP_OFFER_TYP");
						}
						// set IN parameters
						if (__jPt_temp == null)
							__sJT_st.setNull(1, 2002, "ANDROID.TRIP_OFFER_TYP");
						else
							__sJT_st.setORAData(1, __jPt_temp);
						if (WAYPOINT == null)
							__sJT_st.setNull(2, 2002, "ANDROID.PLACE_TYP");
						else
							__sJT_st.setORAData(2, WAYPOINT);
						// execute statement
						__sJT_ec.oracleExecuteUpdate();
						// retrieve OUT parameters
						__jPt_temp = (uclm.esi.cardroid.data.oracle.TripOffer) __sJT_st
								.getORAData(1,
										uclm.esi.cardroid.data.oracle.TripOffer
												.getORADataFactory());
					} finally {
						__sJT_ec.oracleClose();
					}
				}

				// ************************************************************

				/* @lineinfo:user-code *//* @lineinfo:222^5 */
				return __jPt_temp;
			} catch (java.sql.SQLException _err2) {
				try {
					getConnectionContext().getExecutionContext().close();
				} catch (java.sql.SQLException _sqle) {
				}
				throw _err;
			}
		}
	}

	public TripOffer clearPassengersCollection() throws java.sql.SQLException {
		try {
			TripOffer __jPt_temp = (TripOffer) this;
			/* @lineinfo:generated-code *//* @lineinfo:236^5 */

			// ************************************************************
			// #sql [getConnectionContext()] { BEGIN
			// :__jPt_temp.CLEAR_PASSENGERS();
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
					String theSqlTS = "BEGIN\n       :1  .CLEAR_PASSENGERS();\n      END;";
					__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
							"4uclm.esi.cardroid.data.oracle.TripOfferTyp",
							theSqlTS);
					if (__sJT_ec.isNew()) {
						__sJT_st.registerOutParameter(1, 2002,
								"ANDROID.TRIP_OFFER_TYP");
					}
					// set IN parameters
					if (__jPt_temp == null)
						__sJT_st.setNull(1, 2002, "ANDROID.TRIP_OFFER_TYP");
					else
						__sJT_st.setORAData(1, __jPt_temp);
					// execute statement
					__sJT_ec.oracleExecuteUpdate();
					// retrieve OUT parameters
					__jPt_temp = (uclm.esi.cardroid.data.oracle.TripOffer) __sJT_st
							.getORAData(1,
									uclm.esi.cardroid.data.oracle.TripOffer
											.getORADataFactory());
				} finally {
					__sJT_ec.oracleClose();
				}
			}

			// ************************************************************

			/* @lineinfo:user-code *//* @lineinfo:240^5 */
			return __jPt_temp;
		} catch (java.sql.SQLException _err) {
			try {
				getConnectionContext().getExecutionContext().close();
				closeConnection();
				if (__dataSource == null)
					throw _err;
				TripOffer __jPt_temp = (TripOffer) this;
				/* @lineinfo:generated-code *//* @lineinfo:248^5 */

				// ************************************************************
				// #sql [getConnectionContext()] { BEGIN
				// :__jPt_temp.CLEAR_PASSENGERS();
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
						String theSqlTS = "BEGIN\n       :1  .CLEAR_PASSENGERS();\n      END;";
						__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
								"5uclm.esi.cardroid.data.oracle.TripOfferTyp",
								theSqlTS);
						if (__sJT_ec.isNew()) {
							__sJT_st.registerOutParameter(1, 2002,
									"ANDROID.TRIP_OFFER_TYP");
						}
						// set IN parameters
						if (__jPt_temp == null)
							__sJT_st.setNull(1, 2002, "ANDROID.TRIP_OFFER_TYP");
						else
							__sJT_st.setORAData(1, __jPt_temp);
						// execute statement
						__sJT_ec.oracleExecuteUpdate();
						// retrieve OUT parameters
						__jPt_temp = (uclm.esi.cardroid.data.oracle.TripOffer) __sJT_st
								.getORAData(1,
										uclm.esi.cardroid.data.oracle.TripOffer
												.getORADataFactory());
					} finally {
						__sJT_ec.oracleClose();
					}
				}

				// ************************************************************

				/* @lineinfo:user-code *//* @lineinfo:252^5 */
				return __jPt_temp;
			} catch (java.sql.SQLException _err2) {
				try {
					getConnectionContext().getExecutionContext().close();
				} catch (java.sql.SQLException _sqle) {
				}
				throw _err;
			}
		}
	}

	public TripOffer clearWaypointsCollection() throws java.sql.SQLException {
		try {
			TripOffer __jPt_temp = (TripOffer) this;
			/* @lineinfo:generated-code *//* @lineinfo:266^5 */

			// ************************************************************
			// #sql [getConnectionContext()] { BEGIN
			// :__jPt_temp.CLEAR_WAYPOINTS();
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
					String theSqlTS = "BEGIN\n       :1  .CLEAR_WAYPOINTS();\n      END;";
					__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
							"6uclm.esi.cardroid.data.oracle.TripOfferTyp",
							theSqlTS);
					if (__sJT_ec.isNew()) {
						__sJT_st.registerOutParameter(1, 2002,
								"ANDROID.TRIP_OFFER_TYP");
					}
					// set IN parameters
					if (__jPt_temp == null)
						__sJT_st.setNull(1, 2002, "ANDROID.TRIP_OFFER_TYP");
					else
						__sJT_st.setORAData(1, __jPt_temp);
					// execute statement
					__sJT_ec.oracleExecuteUpdate();
					// retrieve OUT parameters
					__jPt_temp = (uclm.esi.cardroid.data.oracle.TripOffer) __sJT_st
							.getORAData(1,
									uclm.esi.cardroid.data.oracle.TripOffer
											.getORADataFactory());
				} finally {
					__sJT_ec.oracleClose();
				}
			}

			// ************************************************************

			/* @lineinfo:user-code *//* @lineinfo:270^5 */
			return __jPt_temp;
		} catch (java.sql.SQLException _err) {
			try {
				getConnectionContext().getExecutionContext().close();
				closeConnection();
				if (__dataSource == null)
					throw _err;
				TripOffer __jPt_temp = (TripOffer) this;
				/* @lineinfo:generated-code *//* @lineinfo:278^5 */

				// ************************************************************
				// #sql [getConnectionContext()] { BEGIN
				// :__jPt_temp.CLEAR_WAYPOINTS();
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
						String theSqlTS = "BEGIN\n       :1  .CLEAR_WAYPOINTS();\n      END;";
						__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
								"7uclm.esi.cardroid.data.oracle.TripOfferTyp",
								theSqlTS);
						if (__sJT_ec.isNew()) {
							__sJT_st.registerOutParameter(1, 2002,
									"ANDROID.TRIP_OFFER_TYP");
						}
						// set IN parameters
						if (__jPt_temp == null)
							__sJT_st.setNull(1, 2002, "ANDROID.TRIP_OFFER_TYP");
						else
							__sJT_st.setORAData(1, __jPt_temp);
						// execute statement
						__sJT_ec.oracleExecuteUpdate();
						// retrieve OUT parameters
						__jPt_temp = (uclm.esi.cardroid.data.oracle.TripOffer) __sJT_st
								.getORAData(1,
										uclm.esi.cardroid.data.oracle.TripOffer
												.getORADataFactory());
					} finally {
						__sJT_ec.oracleClose();
					}
				}

				// ************************************************************

				/* @lineinfo:user-code *//* @lineinfo:282^5 */
				return __jPt_temp;
			} catch (java.sql.SQLException _err2) {
				try {
					getConnectionContext().getExecutionContext().close();
				} catch (java.sql.SQLException _sqle) {
				}
				throw _err;
			}
		}
	}

	public java.sql.Timestamp compareTo() throws java.sql.SQLException {
		TripTyp __jPt_temp = this;
		java.sql.Timestamp __jPt_result;
		try {
			/* @lineinfo:generated-code *//* @lineinfo:297^5 */

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
							"8uclm.esi.cardroid.data.oracle.TripOfferTyp",
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

			/* @lineinfo:user-code *//* @lineinfo:301^5 */
		} catch (java.sql.SQLException _err) {
			try {
				getConnectionContext().getExecutionContext().close();
				closeConnection();
				if (__dataSource == null)
					throw _err;
				/* @lineinfo:generated-code *//* @lineinfo:307^5 */

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
								"9uclm.esi.cardroid.data.oracle.TripOfferTyp",
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

				/* @lineinfo:user-code *//* @lineinfo:311^5 */
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

	public int getNPassengers() throws java.sql.SQLException {
		TripOfferTyp __jPt_temp = this;
		int __jPt_result;
		try {
			/* @lineinfo:generated-code *//* @lineinfo:326^5 */

			// ************************************************************
			// #sql [getConnectionContext()] { BEGIN
			// :__jPt_result := :__jPt_temp.GET_N_PASSENGERS();
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
					String theSqlTS = "BEGIN\n       :1   :=  :2  .GET_N_PASSENGERS();\n      END;";
					__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
							"10uclm.esi.cardroid.data.oracle.TripOfferTyp",
							theSqlTS);
					if (__sJT_ec.isNew()) {
						__sJT_st.registerOutParameter(1,
								oracle.jdbc.OracleTypes.INTEGER);
					}
					// set IN parameters
					if (__jPt_temp == null)
						__sJT_st.setNull(2, 2002, "ANDROID.TRIP_OFFER_TYP");
					else
						__sJT_st.setORAData(2, __jPt_temp);
					// execute statement
					__sJT_ec.oracleExecuteUpdate();
					// retrieve OUT parameters
					__jPt_result = __sJT_st.getInt(1);
					if (__sJT_st.wasNull())
						throw new sqlj.runtime.SQLNullException();
				} finally {
					__sJT_ec.oracleClose();
				}
			}

			// ************************************************************

			/* @lineinfo:user-code *//* @lineinfo:330^5 */
		} catch (java.sql.SQLException _err) {
			try {
				getConnectionContext().getExecutionContext().close();
				closeConnection();
				if (__dataSource == null)
					throw _err;
				/* @lineinfo:generated-code *//* @lineinfo:336^5 */

				// ************************************************************
				// #sql [getConnectionContext()] { BEGIN
				// :__jPt_result := :__jPt_temp.GET_N_PASSENGERS();
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
						String theSqlTS = "BEGIN\n       :1   :=  :2  .GET_N_PASSENGERS();\n      END;";
						__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
								"11uclm.esi.cardroid.data.oracle.TripOfferTyp",
								theSqlTS);
						if (__sJT_ec.isNew()) {
							__sJT_st.registerOutParameter(1,
									oracle.jdbc.OracleTypes.INTEGER);
						}
						// set IN parameters
						if (__jPt_temp == null)
							__sJT_st.setNull(2, 2002, "ANDROID.TRIP_OFFER_TYP");
						else
							__sJT_st.setORAData(2, __jPt_temp);
						// execute statement
						__sJT_ec.oracleExecuteUpdate();
						// retrieve OUT parameters
						__jPt_result = __sJT_st.getInt(1);
						if (__sJT_st.wasNull())
							throw new sqlj.runtime.SQLNullException();
					} finally {
						__sJT_ec.oracleClose();
					}
				}

				// ************************************************************

				/* @lineinfo:user-code *//* @lineinfo:340^5 */
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

	public int getNWaypoints() throws java.sql.SQLException {
		TripOfferTyp __jPt_temp = this;
		int __jPt_result;
		try {
			/* @lineinfo:generated-code *//* @lineinfo:355^5 */

			// ************************************************************
			// #sql [getConnectionContext()] { BEGIN
			// :__jPt_result := :__jPt_temp.GET_N_WAYPOINTS();
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
					String theSqlTS = "BEGIN\n       :1   :=  :2  .GET_N_WAYPOINTS();\n      END;";
					__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
							"12uclm.esi.cardroid.data.oracle.TripOfferTyp",
							theSqlTS);
					if (__sJT_ec.isNew()) {
						__sJT_st.registerOutParameter(1,
								oracle.jdbc.OracleTypes.INTEGER);
					}
					// set IN parameters
					if (__jPt_temp == null)
						__sJT_st.setNull(2, 2002, "ANDROID.TRIP_OFFER_TYP");
					else
						__sJT_st.setORAData(2, __jPt_temp);
					// execute statement
					__sJT_ec.oracleExecuteUpdate();
					// retrieve OUT parameters
					__jPt_result = __sJT_st.getInt(1);
					if (__sJT_st.wasNull())
						throw new sqlj.runtime.SQLNullException();
				} finally {
					__sJT_ec.oracleClose();
				}
			}

			// ************************************************************

			/* @lineinfo:user-code *//* @lineinfo:359^5 */
		} catch (java.sql.SQLException _err) {
			try {
				getConnectionContext().getExecutionContext().close();
				closeConnection();
				if (__dataSource == null)
					throw _err;
				/* @lineinfo:generated-code *//* @lineinfo:365^5 */

				// ************************************************************
				// #sql [getConnectionContext()] { BEGIN
				// :__jPt_result := :__jPt_temp.GET_N_WAYPOINTS();
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
						String theSqlTS = "BEGIN\n       :1   :=  :2  .GET_N_WAYPOINTS();\n      END;";
						__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
								"13uclm.esi.cardroid.data.oracle.TripOfferTyp",
								theSqlTS);
						if (__sJT_ec.isNew()) {
							__sJT_st.registerOutParameter(1,
									oracle.jdbc.OracleTypes.INTEGER);
						}
						// set IN parameters
						if (__jPt_temp == null)
							__sJT_st.setNull(2, 2002, "ANDROID.TRIP_OFFER_TYP");
						else
							__sJT_st.setORAData(2, __jPt_temp);
						// execute statement
						__sJT_ec.oracleExecuteUpdate();
						// retrieve OUT parameters
						__jPt_result = __sJT_st.getInt(1);
						if (__sJT_st.wasNull())
							throw new sqlj.runtime.SQLNullException();
					} finally {
						__sJT_ec.oracleClose();
					}
				}

				// ************************************************************

				/* @lineinfo:user-code *//* @lineinfo:369^5 */
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

	public TripOffer removeTripPassenger(User PASSENGER)
			throws java.sql.SQLException {
		try {
			TripOffer __jPt_temp = (TripOffer) this;
			/* @lineinfo:generated-code *//* @lineinfo:384^5 */

			// ************************************************************
			// #sql [getConnectionContext()] { BEGIN
			// :__jPt_temp.REMOVE_PASSENGER(
			// :PASSENGER);
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
					String theSqlTS = "BEGIN\n       :1  .REMOVE_PASSENGER(\n       :2  );\n      END;";
					__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
							"14uclm.esi.cardroid.data.oracle.TripOfferTyp",
							theSqlTS);
					if (__sJT_ec.isNew()) {
						__sJT_st.registerOutParameter(1, 2002,
								"ANDROID.TRIP_OFFER_TYP");
					}
					// set IN parameters
					if (__jPt_temp == null)
						__sJT_st.setNull(1, 2002, "ANDROID.TRIP_OFFER_TYP");
					else
						__sJT_st.setORAData(1, __jPt_temp);
					if (PASSENGER == null)
						__sJT_st.setNull(2, 2002, "ANDROID.USER_TYP");
					else
						__sJT_st.setORAData(2, PASSENGER);
					// execute statement
					__sJT_ec.oracleExecuteUpdate();
					// retrieve OUT parameters
					__jPt_temp = (uclm.esi.cardroid.data.oracle.TripOffer) __sJT_st
							.getORAData(1,
									uclm.esi.cardroid.data.oracle.TripOffer
											.getORADataFactory());
				} finally {
					__sJT_ec.oracleClose();
				}
			}

			// ************************************************************

			/* @lineinfo:user-code *//* @lineinfo:389^5 */
			return __jPt_temp;
		} catch (java.sql.SQLException _err) {
			try {
				getConnectionContext().getExecutionContext().close();
				closeConnection();
				if (__dataSource == null)
					throw _err;
				TripOffer __jPt_temp = (TripOffer) this;
				/* @lineinfo:generated-code *//* @lineinfo:397^5 */

				// ************************************************************
				// #sql [getConnectionContext()] { BEGIN
				// :__jPt_temp.REMOVE_PASSENGER(
				// :PASSENGER);
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
						String theSqlTS = "BEGIN\n       :1  .REMOVE_PASSENGER(\n       :2  );\n      END;";
						__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
								"15uclm.esi.cardroid.data.oracle.TripOfferTyp",
								theSqlTS);
						if (__sJT_ec.isNew()) {
							__sJT_st.registerOutParameter(1, 2002,
									"ANDROID.TRIP_OFFER_TYP");
						}
						// set IN parameters
						if (__jPt_temp == null)
							__sJT_st.setNull(1, 2002, "ANDROID.TRIP_OFFER_TYP");
						else
							__sJT_st.setORAData(1, __jPt_temp);
						if (PASSENGER == null)
							__sJT_st.setNull(2, 2002, "ANDROID.USER_TYP");
						else
							__sJT_st.setORAData(2, PASSENGER);
						// execute statement
						__sJT_ec.oracleExecuteUpdate();
						// retrieve OUT parameters
						__jPt_temp = (uclm.esi.cardroid.data.oracle.TripOffer) __sJT_st
								.getORAData(1,
										uclm.esi.cardroid.data.oracle.TripOffer
												.getORADataFactory());
					} finally {
						__sJT_ec.oracleClose();
					}
				}

				// ************************************************************

				/* @lineinfo:user-code *//* @lineinfo:402^5 */
				return __jPt_temp;
			} catch (java.sql.SQLException _err2) {
				try {
					getConnectionContext().getExecutionContext().close();
				} catch (java.sql.SQLException _sqle) {
				}
				throw _err;
			}
		}
	}

	public TripOffer removeTripWaypoint(int POS) throws java.sql.SQLException {
		try {
			TripOffer __jPt_temp = (TripOffer) this;
			/* @lineinfo:generated-code *//* @lineinfo:417^5 */

			// ************************************************************
			// #sql [getConnectionContext()] { BEGIN
			// :__jPt_temp.REMOVE_WAYPOINT(
			// :POS);
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
					String theSqlTS = "BEGIN\n       :1  .REMOVE_WAYPOINT(\n       :2  );\n      END;";
					__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
							"16uclm.esi.cardroid.data.oracle.TripOfferTyp",
							theSqlTS);
					if (__sJT_ec.isNew()) {
						__sJT_st.registerOutParameter(1, 2002,
								"ANDROID.TRIP_OFFER_TYP");
					}
					// set IN parameters
					if (__jPt_temp == null)
						__sJT_st.setNull(1, 2002, "ANDROID.TRIP_OFFER_TYP");
					else
						__sJT_st.setORAData(1, __jPt_temp);
					__sJT_st.setInt(2, POS);
					// execute statement
					__sJT_ec.oracleExecuteUpdate();
					// retrieve OUT parameters
					__jPt_temp = (uclm.esi.cardroid.data.oracle.TripOffer) __sJT_st
							.getORAData(1,
									uclm.esi.cardroid.data.oracle.TripOffer
											.getORADataFactory());
				} finally {
					__sJT_ec.oracleClose();
				}
			}

			// ************************************************************

			/* @lineinfo:user-code *//* @lineinfo:422^5 */
			return __jPt_temp;
		} catch (java.sql.SQLException _err) {
			try {
				getConnectionContext().getExecutionContext().close();
				closeConnection();
				if (__dataSource == null)
					throw _err;
				TripOffer __jPt_temp = (TripOffer) this;
				/* @lineinfo:generated-code *//* @lineinfo:430^5 */

				// ************************************************************
				// #sql [getConnectionContext()] { BEGIN
				// :__jPt_temp.REMOVE_WAYPOINT(
				// :POS);
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
						String theSqlTS = "BEGIN\n       :1  .REMOVE_WAYPOINT(\n       :2  );\n      END;";
						__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
								"17uclm.esi.cardroid.data.oracle.TripOfferTyp",
								theSqlTS);
						if (__sJT_ec.isNew()) {
							__sJT_st.registerOutParameter(1, 2002,
									"ANDROID.TRIP_OFFER_TYP");
						}
						// set IN parameters
						if (__jPt_temp == null)
							__sJT_st.setNull(1, 2002, "ANDROID.TRIP_OFFER_TYP");
						else
							__sJT_st.setORAData(1, __jPt_temp);
						__sJT_st.setInt(2, POS);
						// execute statement
						__sJT_ec.oracleExecuteUpdate();
						// retrieve OUT parameters
						__jPt_temp = (uclm.esi.cardroid.data.oracle.TripOffer) __sJT_st
								.getORAData(1,
										uclm.esi.cardroid.data.oracle.TripOffer
												.getORADataFactory());
					} finally {
						__sJT_ec.oracleClose();
					}
				}

				// ************************************************************

				/* @lineinfo:user-code *//* @lineinfo:435^5 */
				return __jPt_temp;
			} catch (java.sql.SQLException _err2) {
				try {
					getConnectionContext().getExecutionContext().close();
				} catch (java.sql.SQLException _sqle) {
				}
				throw _err;
			}
		}
	}

	public TripOfferTyp tripOfferTyp(Place FROM_PLACE, Place TO_PLACE,
			DateTime DATETIME, User DRIVER, Car CAR, int AVAILABLE_SEATS,
			double PRICE, TripOffer_AllowedVarray ALLOWED,
			TripOfferTyp[] __jPt_out) throws java.sql.SQLException {
		TripOffer __jPt_temp = (TripOffer) this;
		TripOffer __jPt_result;
		try {
			/* @lineinfo:generated-code *//* @lineinfo:459^5 */

			// ************************************************************
			// #sql [getConnectionContext()] { BEGIN
			// :__jPt_result := TRIP_OFFER_TYP(
			// :FROM_PLACE,
			// :TO_PLACE,
			// :DATETIME,
			// :DRIVER,
			// :CAR,
			// :AVAILABLE_SEATS,
			// :PRICE,
			// :ALLOWED);
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
					String theSqlTS = "BEGIN\n       :1   := TRIP_OFFER_TYP(\n       :2  ,\n       :3  ,\n       :4  ,\n       :5  ,\n       :6  ,\n       :7  ,\n       :8  ,\n       :9  );\n      END;";
					__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
							"18uclm.esi.cardroid.data.oracle.TripOfferTyp",
							theSqlTS);
					if (__sJT_ec.isNew()) {
						__sJT_st.registerOutParameter(1, 2002,
								"ANDROID.TRIP_OFFER_TYP");
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
						__sJT_st.setNull(4, 2002, "ANDROID.DATE_TIME_TYP");
					else
						__sJT_st.setORAData(4, DATETIME);
					if (DRIVER == null)
						__sJT_st.setNull(5, 2002, "ANDROID.USER_TYP");
					else
						__sJT_st.setORAData(5, DRIVER);
					if (CAR == null)
						__sJT_st.setNull(6, 2002, "ANDROID.CAR_TYP");
					else
						__sJT_st.setORAData(6, CAR);
					__sJT_st.setInt(7, AVAILABLE_SEATS);
					__sJT_st.setDouble(8, PRICE);
					if (ALLOWED == null)
						__sJT_st.setNull(9, 2003, "ANDROID.ALLOWED_V_TYP");
					else
						__sJT_st.setORAData(9, ALLOWED);
					// execute statement
					__sJT_ec.oracleExecuteUpdate();
					// retrieve OUT parameters
					__jPt_result = (uclm.esi.cardroid.data.oracle.TripOffer) __sJT_st
							.getORAData(1,
									uclm.esi.cardroid.data.oracle.TripOffer
											.getORADataFactory());
				} finally {
					__sJT_ec.oracleClose();
				}
			}

			// ************************************************************

			/* @lineinfo:user-code *//* @lineinfo:471^5 */
			__jPt_out[0] = __jPt_temp;
		} catch (java.sql.SQLException _err) {
			try {
				getConnectionContext().getExecutionContext().close();
				closeConnection();
				if (__dataSource == null)
					throw _err;
				/* @lineinfo:generated-code *//* @lineinfo:478^5 */

				// ************************************************************
				// #sql [getConnectionContext()] { BEGIN
				// :__jPt_result := TRIP_OFFER_TYP(
				// :FROM_PLACE,
				// :TO_PLACE,
				// :DATETIME,
				// :DRIVER,
				// :CAR,
				// :AVAILABLE_SEATS,
				// :PRICE,
				// :ALLOWED);
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
						String theSqlTS = "BEGIN\n       :1   := TRIP_OFFER_TYP(\n       :2  ,\n       :3  ,\n       :4  ,\n       :5  ,\n       :6  ,\n       :7  ,\n       :8  ,\n       :9  );\n      END;";
						__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
								"19uclm.esi.cardroid.data.oracle.TripOfferTyp",
								theSqlTS);
						if (__sJT_ec.isNew()) {
							__sJT_st.registerOutParameter(1, 2002,
									"ANDROID.TRIP_OFFER_TYP");
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
							__sJT_st.setNull(4, 2002, "ANDROID.DATE_TIME_TYP");
						else
							__sJT_st.setORAData(4, DATETIME);
						if (DRIVER == null)
							__sJT_st.setNull(5, 2002, "ANDROID.USER_TYP");
						else
							__sJT_st.setORAData(5, DRIVER);
						if (CAR == null)
							__sJT_st.setNull(6, 2002, "ANDROID.CAR_TYP");
						else
							__sJT_st.setORAData(6, CAR);
						__sJT_st.setInt(7, AVAILABLE_SEATS);
						__sJT_st.setDouble(8, PRICE);
						if (ALLOWED == null)
							__sJT_st.setNull(9, 2003, "ANDROID.ALLOWED_V_TYP");
						else
							__sJT_st.setORAData(9, ALLOWED);
						// execute statement
						__sJT_ec.oracleExecuteUpdate();
						// retrieve OUT parameters
						__jPt_result = (uclm.esi.cardroid.data.oracle.TripOffer) __sJT_st
								.getORAData(1,
										uclm.esi.cardroid.data.oracle.TripOffer
												.getORADataFactory());
					} finally {
						__sJT_ec.oracleClose();
					}
				}

				// ************************************************************

				/* @lineinfo:user-code *//* @lineinfo:490^5 */
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

	public TripOfferTyp tripOfferTyp(Place FROM_PLACE, Place TO_PLACE,
			DateTime DATETIME, DateTime RETURN_DATETIME, User DRIVER, Car CAR,
			int AVAILABLE_SEATS, double PRICE, TripOffer_AllowedVarray ALLOWED,
			TripOfferTyp[] __jPt_out) throws java.sql.SQLException {
		TripOffer __jPt_temp = (TripOffer) this;
		TripOffer __jPt_result;
		try {
			/* @lineinfo:generated-code *//* @lineinfo:516^5 */

			// ************************************************************
			// #sql [getConnectionContext()] { BEGIN
			// :__jPt_result := TRIP_OFFER_TYP(
			// :FROM_PLACE,
			// :TO_PLACE,
			// :DATETIME,
			// :RETURN_DATETIME,
			// :DRIVER,
			// :CAR,
			// :AVAILABLE_SEATS,
			// :PRICE,
			// :ALLOWED);
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
					String theSqlTS = "BEGIN\n       :1   := TRIP_OFFER_TYP(\n       :2  ,\n       :3  ,\n       :4  ,\n       :5  ,\n       :6  ,\n       :7  ,\n       :8  ,\n       :9  ,\n       :10  );\n      END;";
					__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
							"20uclm.esi.cardroid.data.oracle.TripOfferTyp",
							theSqlTS);
					if (__sJT_ec.isNew()) {
						__sJT_st.registerOutParameter(1, 2002,
								"ANDROID.TRIP_OFFER_TYP");
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
						__sJT_st.setNull(4, 2002, "ANDROID.DATE_TIME_TYP");
					else
						__sJT_st.setORAData(4, DATETIME);
					if (RETURN_DATETIME == null)
						__sJT_st.setNull(5, 2002, "ANDROID.DATE_TIME_TYP");
					else
						__sJT_st.setORAData(5, RETURN_DATETIME);
					if (DRIVER == null)
						__sJT_st.setNull(6, 2002, "ANDROID.USER_TYP");
					else
						__sJT_st.setORAData(6, DRIVER);
					if (CAR == null)
						__sJT_st.setNull(7, 2002, "ANDROID.CAR_TYP");
					else
						__sJT_st.setORAData(7, CAR);
					__sJT_st.setInt(8, AVAILABLE_SEATS);
					__sJT_st.setDouble(9, PRICE);
					if (ALLOWED == null)
						__sJT_st.setNull(10, 2003, "ANDROID.ALLOWED_V_TYP");
					else
						__sJT_st.setORAData(10, ALLOWED);
					// execute statement
					__sJT_ec.oracleExecuteUpdate();
					// retrieve OUT parameters
					__jPt_result = (uclm.esi.cardroid.data.oracle.TripOffer) __sJT_st
							.getORAData(1,
									uclm.esi.cardroid.data.oracle.TripOffer
											.getORADataFactory());
				} finally {
					__sJT_ec.oracleClose();
				}
			}

			// ************************************************************

			/* @lineinfo:user-code *//* @lineinfo:529^5 */
			__jPt_out[0] = __jPt_temp;
		} catch (java.sql.SQLException _err) {
			try {
				getConnectionContext().getExecutionContext().close();
				closeConnection();
				if (__dataSource == null)
					throw _err;
				/* @lineinfo:generated-code *//* @lineinfo:536^5 */

				// ************************************************************
				// #sql [getConnectionContext()] { BEGIN
				// :__jPt_result := TRIP_OFFER_TYP(
				// :FROM_PLACE,
				// :TO_PLACE,
				// :DATETIME,
				// :RETURN_DATETIME,
				// :DRIVER,
				// :CAR,
				// :AVAILABLE_SEATS,
				// :PRICE,
				// :ALLOWED);
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
						String theSqlTS = "BEGIN\n       :1   := TRIP_OFFER_TYP(\n       :2  ,\n       :3  ,\n       :4  ,\n       :5  ,\n       :6  ,\n       :7  ,\n       :8  ,\n       :9  ,\n       :10  );\n      END;";
						__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
								"21uclm.esi.cardroid.data.oracle.TripOfferTyp",
								theSqlTS);
						if (__sJT_ec.isNew()) {
							__sJT_st.registerOutParameter(1, 2002,
									"ANDROID.TRIP_OFFER_TYP");
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
							__sJT_st.setNull(4, 2002, "ANDROID.DATE_TIME_TYP");
						else
							__sJT_st.setORAData(4, DATETIME);
						if (RETURN_DATETIME == null)
							__sJT_st.setNull(5, 2002, "ANDROID.DATE_TIME_TYP");
						else
							__sJT_st.setORAData(5, RETURN_DATETIME);
						if (DRIVER == null)
							__sJT_st.setNull(6, 2002, "ANDROID.USER_TYP");
						else
							__sJT_st.setORAData(6, DRIVER);
						if (CAR == null)
							__sJT_st.setNull(7, 2002, "ANDROID.CAR_TYP");
						else
							__sJT_st.setORAData(7, CAR);
						__sJT_st.setInt(8, AVAILABLE_SEATS);
						__sJT_st.setDouble(9, PRICE);
						if (ALLOWED == null)
							__sJT_st.setNull(10, 2003, "ANDROID.ALLOWED_V_TYP");
						else
							__sJT_st.setORAData(10, ALLOWED);
						// execute statement
						__sJT_ec.oracleExecuteUpdate();
						// retrieve OUT parameters
						__jPt_result = (uclm.esi.cardroid.data.oracle.TripOffer) __sJT_st
								.getORAData(1,
										uclm.esi.cardroid.data.oracle.TripOffer
												.getORADataFactory());
					} finally {
						__sJT_ec.oracleClose();
					}
				}

				// ************************************************************

				/* @lineinfo:user-code *//* @lineinfo:549^5 */
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

	public TripOffer tripOfferTyp(Place FROM_PLACE, Place TO_PLACE,
			DateTime DATETIME, User DRIVER, Car CAR, int AVAILABLE_SEATS,
			double PRICE, TripOffer_AllowedVarray ALLOWED,
			Trip_WeekDaysVarray WEEK_DAYS, String PERIODICITY,
			TripOfferTyp[] __jPt_out) throws java.sql.SQLException {
		TripOffer __jPt_temp = (TripOffer) this;
		TripOffer __jPt_result;
		try {
			/* @lineinfo:generated-code *//* @lineinfo:576^5 */

			// ************************************************************
			// #sql [getConnectionContext()] { BEGIN
			// :__jPt_result := TRIP_OFFER_TYP(
			// :FROM_PLACE,
			// :TO_PLACE,
			// :DATETIME,
			// :DRIVER,
			// :CAR,
			// :AVAILABLE_SEATS,
			// :PRICE,
			// :ALLOWED,
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
					String theSqlTS = "BEGIN\n       :1   := TRIP_OFFER_TYP(\n       :2  ,\n       :3  ,\n       :4  ,\n       :5  ,\n       :6  ,\n       :7  ,\n       :8  ,\n       :9  ,\n       :10  ,\n       :11  );\n      END;";
					__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
							"22uclm.esi.cardroid.data.oracle.TripOfferTyp",
							theSqlTS);
					if (__sJT_ec.isNew()) {
						__sJT_st.registerOutParameter(1, 2002,
								"ANDROID.TRIP_OFFER_TYP");
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
						__sJT_st.setNull(4, 2002, "ANDROID.DATE_TIME_TYP");
					else
						__sJT_st.setORAData(4, DATETIME);
					if (DRIVER == null)
						__sJT_st.setNull(5, 2002, "ANDROID.USER_TYP");
					else
						__sJT_st.setORAData(5, DRIVER);
					if (CAR == null)
						__sJT_st.setNull(6, 2002, "ANDROID.CAR_TYP");
					else
						__sJT_st.setORAData(6, CAR);
					__sJT_st.setInt(7, AVAILABLE_SEATS);
					__sJT_st.setDouble(8, PRICE);
					if (ALLOWED == null)
						__sJT_st.setNull(9, 2003, "ANDROID.ALLOWED_V_TYP");
					else
						__sJT_st.setORAData(9, ALLOWED);
					if (WEEK_DAYS == null)
						__sJT_st.setNull(10, 2003, "ANDROID.WEEKDAYS_TYP");
					else
						__sJT_st.setORAData(10, WEEK_DAYS);
					__sJT_st.setString(11, PERIODICITY);
					// execute statement
					__sJT_ec.oracleExecuteUpdate();
					// retrieve OUT parameters
					__jPt_result = (uclm.esi.cardroid.data.oracle.TripOffer) __sJT_st
							.getORAData(1,
									uclm.esi.cardroid.data.oracle.TripOffer
											.getORADataFactory());
				} finally {
					__sJT_ec.oracleClose();
				}
			}

			// ************************************************************

			/* @lineinfo:user-code *//* @lineinfo:590^5 */
			__jPt_out[0] = __jPt_temp;
		} catch (java.sql.SQLException _err) {
			try {
				getConnectionContext().getExecutionContext().close();
				closeConnection();
				if (__dataSource == null)
					throw _err;
				/* @lineinfo:generated-code *//* @lineinfo:597^5 */

				// ************************************************************
				// #sql [getConnectionContext()] { BEGIN
				// :__jPt_result := TRIP_OFFER_TYP(
				// :FROM_PLACE,
				// :TO_PLACE,
				// :DATETIME,
				// :DRIVER,
				// :CAR,
				// :AVAILABLE_SEATS,
				// :PRICE,
				// :ALLOWED,
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
						String theSqlTS = "BEGIN\n       :1   := TRIP_OFFER_TYP(\n       :2  ,\n       :3  ,\n       :4  ,\n       :5  ,\n       :6  ,\n       :7  ,\n       :8  ,\n       :9  ,\n       :10  ,\n       :11  );\n      END;";
						__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
								"23uclm.esi.cardroid.data.oracle.TripOfferTyp",
								theSqlTS);
						if (__sJT_ec.isNew()) {
							__sJT_st.registerOutParameter(1, 2002,
									"ANDROID.TRIP_OFFER_TYP");
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
							__sJT_st.setNull(4, 2002, "ANDROID.DATE_TIME_TYP");
						else
							__sJT_st.setORAData(4, DATETIME);
						if (DRIVER == null)
							__sJT_st.setNull(5, 2002, "ANDROID.USER_TYP");
						else
							__sJT_st.setORAData(5, DRIVER);
						if (CAR == null)
							__sJT_st.setNull(6, 2002, "ANDROID.CAR_TYP");
						else
							__sJT_st.setORAData(6, CAR);
						__sJT_st.setInt(7, AVAILABLE_SEATS);
						__sJT_st.setDouble(8, PRICE);
						if (ALLOWED == null)
							__sJT_st.setNull(9, 2003, "ANDROID.ALLOWED_V_TYP");
						else
							__sJT_st.setORAData(9, ALLOWED);
						if (WEEK_DAYS == null)
							__sJT_st.setNull(10, 2003, "ANDROID.WEEKDAYS_TYP");
						else
							__sJT_st.setORAData(10, WEEK_DAYS);
						__sJT_st.setString(11, PERIODICITY);
						// execute statement
						__sJT_ec.oracleExecuteUpdate();
						// retrieve OUT parameters
						__jPt_result = (uclm.esi.cardroid.data.oracle.TripOffer) __sJT_st
								.getORAData(1,
										uclm.esi.cardroid.data.oracle.TripOffer
												.getORADataFactory());
					} finally {
						__sJT_ec.oracleClose();
					}
				}

				// ************************************************************

				/* @lineinfo:user-code *//* @lineinfo:611^5 */
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

	public TripOffer tripOfferTyp(Place FROM_PLACE, Place TO_PLACE,
			DateTime DATETIME, DateTime RETURN_DATETIME, User DRIVER, Car CAR,
			int AVAILABLE_SEATS, double PRICE, TripOffer_AllowedVarray ALLOWED,
			Trip_WeekDaysVarray WEEK_DAYS, String PERIODICITY,
			TripOfferTyp[] __jPt_out) throws java.sql.SQLException {
		TripOffer __jPt_temp = (TripOffer) this;
		TripOffer __jPt_result;
		try {
			/* @lineinfo:generated-code *//* @lineinfo:639^5 */

			// ************************************************************
			// #sql [getConnectionContext()] { BEGIN
			// :__jPt_result := TRIP_OFFER_TYP(
			// :FROM_PLACE,
			// :TO_PLACE,
			// :DATETIME,
			// :RETURN_DATETIME,
			// :DRIVER,
			// :CAR,
			// :AVAILABLE_SEATS,
			// :PRICE,
			// :ALLOWED,
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
					String theSqlTS = "BEGIN\n       :1   := TRIP_OFFER_TYP(\n       :2  ,\n       :3  ,\n       :4  ,\n       :5  ,\n       :6  ,\n       :7  ,\n       :8  ,\n       :9  ,\n       :10  ,\n       :11  ,\n       :12  );\n      END;";
					__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
							"24uclm.esi.cardroid.data.oracle.TripOfferTyp",
							theSqlTS);
					if (__sJT_ec.isNew()) {
						__sJT_st.registerOutParameter(1, 2002,
								"ANDROID.TRIP_OFFER_TYP");
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
						__sJT_st.setNull(4, 2002, "ANDROID.DATE_TIME_TYP");
					else
						__sJT_st.setORAData(4, DATETIME);
					if (RETURN_DATETIME == null)
						__sJT_st.setNull(5, 2002, "ANDROID.DATE_TIME_TYP");
					else
						__sJT_st.setORAData(5, RETURN_DATETIME);
					if (DRIVER == null)
						__sJT_st.setNull(6, 2002, "ANDROID.USER_TYP");
					else
						__sJT_st.setORAData(6, DRIVER);
					if (CAR == null)
						__sJT_st.setNull(7, 2002, "ANDROID.CAR_TYP");
					else
						__sJT_st.setORAData(7, CAR);
					__sJT_st.setInt(8, AVAILABLE_SEATS);
					__sJT_st.setDouble(9, PRICE);
					if (ALLOWED == null)
						__sJT_st.setNull(10, 2003, "ANDROID.ALLOWED_V_TYP");
					else
						__sJT_st.setORAData(10, ALLOWED);
					if (WEEK_DAYS == null)
						__sJT_st.setNull(11, 2003, "ANDROID.WEEKDAYS_TYP");
					else
						__sJT_st.setORAData(11, WEEK_DAYS);
					__sJT_st.setString(12, PERIODICITY);
					// execute statement
					__sJT_ec.oracleExecuteUpdate();
					// retrieve OUT parameters
					__jPt_result = (uclm.esi.cardroid.data.oracle.TripOffer) __sJT_st
							.getORAData(1,
									uclm.esi.cardroid.data.oracle.TripOffer
											.getORADataFactory());
				} finally {
					__sJT_ec.oracleClose();
				}
			}

			// ************************************************************

			/* @lineinfo:user-code *//* @lineinfo:654^5 */
			__jPt_out[0] = __jPt_temp;
		} catch (java.sql.SQLException _err) {
			try {
				getConnectionContext().getExecutionContext().close();
				closeConnection();
				if (__dataSource == null)
					throw _err;
				/* @lineinfo:generated-code *//* @lineinfo:661^5 */

				// ************************************************************
				// #sql [getConnectionContext()] { BEGIN
				// :__jPt_result := TRIP_OFFER_TYP(
				// :FROM_PLACE,
				// :TO_PLACE,
				// :DATETIME,
				// :RETURN_DATETIME,
				// :DRIVER,
				// :CAR,
				// :AVAILABLE_SEATS,
				// :PRICE,
				// :ALLOWED,
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
						String theSqlTS = "BEGIN\n       :1   := TRIP_OFFER_TYP(\n       :2  ,\n       :3  ,\n       :4  ,\n       :5  ,\n       :6  ,\n       :7  ,\n       :8  ,\n       :9  ,\n       :10  ,\n       :11  ,\n       :12  );\n      END;";
						__sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
								"25uclm.esi.cardroid.data.oracle.TripOfferTyp",
								theSqlTS);
						if (__sJT_ec.isNew()) {
							__sJT_st.registerOutParameter(1, 2002,
									"ANDROID.TRIP_OFFER_TYP");
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
							__sJT_st.setNull(4, 2002, "ANDROID.DATE_TIME_TYP");
						else
							__sJT_st.setORAData(4, DATETIME);
						if (RETURN_DATETIME == null)
							__sJT_st.setNull(5, 2002, "ANDROID.DATE_TIME_TYP");
						else
							__sJT_st.setORAData(5, RETURN_DATETIME);
						if (DRIVER == null)
							__sJT_st.setNull(6, 2002, "ANDROID.USER_TYP");
						else
							__sJT_st.setORAData(6, DRIVER);
						if (CAR == null)
							__sJT_st.setNull(7, 2002, "ANDROID.CAR_TYP");
						else
							__sJT_st.setORAData(7, CAR);
						__sJT_st.setInt(8, AVAILABLE_SEATS);
						__sJT_st.setDouble(9, PRICE);
						if (ALLOWED == null)
							__sJT_st.setNull(10, 2003, "ANDROID.ALLOWED_V_TYP");
						else
							__sJT_st.setORAData(10, ALLOWED);
						if (WEEK_DAYS == null)
							__sJT_st.setNull(11, 2003, "ANDROID.WEEKDAYS_TYP");
						else
							__sJT_st.setORAData(11, WEEK_DAYS);
						__sJT_st.setString(12, PERIODICITY);
						// execute statement
						__sJT_ec.oracleExecuteUpdate();
						// retrieve OUT parameters
						__jPt_result = (uclm.esi.cardroid.data.oracle.TripOffer) __sJT_st
								.getORAData(1,
										uclm.esi.cardroid.data.oracle.TripOffer
												.getORADataFactory());
					} finally {
						__sJT_ec.oracleClose();
					}
				}

				// ************************************************************

				/* @lineinfo:user-code *//* @lineinfo:676^5 */
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