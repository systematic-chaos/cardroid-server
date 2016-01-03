/*@lineinfo:filename=TripRequestTyp*//*@lineinfo:user-code*//*@lineinfo:1^1*/package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;

import sqlj.runtime.ref.DefaultContext;

import uclm.esi.cardroid.data.IDateTimePrefs;
import uclm.esi.cardroid.data.ITripRequest;
import uclm.esi.cardroid.data.IUser;

/**
 * \class TripRequest
 * Domain class implementing a TripRequest for its storage and retrieval from an 
 * Oracle database
 */
public class TripRequest extends TripRequestTyp implements ORAData,
		ORADataFactory, ITripRequest {
	private static final TripRequest _TripRequestFactory = new TripRequest();

	public static ORADataFactory getORADataFactory() {
		return _TripRequestFactory;
	}

	public TripRequest() {
		super();
	}

	public TripRequest(Connection conn) throws SQLException {
		super(conn);
	}

	public TripRequest(DefaultContext ctx) throws SQLException {
		super(ctx);
	}

	/**
	 * Short constructor
	 */
	public TripRequest(Place from, Place to, DateTimePrefs datetime,
			User requester, int requestedSeats) {
		setFromPlace(from);
		setToPlace(to);
		setDateTime(datetime);
		setRequester(requester);
		setNSeats(requestedSeats);
	}

	/**
	 * Another constructor
	 */
	public TripRequest(Place from, Place to, DateTimePrefs datetime,
			DateTimePrefs returnDateTime, User requester, int requestedSeats) {
		setFromPlace(from);
		setToPlace(to);
		setDateTime(datetime);
		setReturnDateTime(returnDateTime);
		setRequester(requester);
		setNSeats(requestedSeats);
	}

	/**
	 * Another constructor
	 */
	public TripRequest(Place from, Place to, DateTimePrefs datetime,
			User requester, int requestedSeats, Trip_WeekDaysVarray weekDays,
			String periodicity) {
		setFromPlace(from);
		setToPlace(to);
		setDateTime(datetime);
		setRequester(requester);
		setNSeats(requestedSeats);
		setWeekDays(weekDays);
		setPeriodicity(periodicity);
	}

	/**
	 * Another constructor
	 */
	public TripRequest(Place from, Place to, DateTimePrefs datetime,
			DateTimePrefs returnDatetime, User requester, int requestedSeats,
			Trip_WeekDaysVarray weekDays, String periodicity) {
		setFromPlace(from);
		setToPlace(to);
		setDateTime(datetime);
		setReturnDateTime(returnDatetime);
		setRequester(requester);
		setNSeats(requestedSeats);
		setWeekDays(weekDays);
		setPeriodicity(periodicity);
	}

	/**
	 * Long constructor
	 */
	public TripRequest(Place fromPlace, Place toPlace, DateTimePrefs datetime,
			int nSeats, DateTimePrefs returnDatetime,
			Trip_WeekDaysVarray weekDays, String periodicity, int distance,
			String characteristics, User requester) {
		setFromPlace(fromPlace);
		setToPlace(toPlace);
		setDateTime(datetime);
		setNSeats(nSeats);
		setReturnDateTime(returnDatetime);
		setWeekDays(weekDays);
		setPeriodicity(periodicity);
		setDistance(distance);
		setCharacteristics(characteristics);
		setRequester(requester);
	}

	/* ORAData interface */

	protected ORAData createExact(Datum d, int sqlType) throws SQLException {
		return create(new TripRequest(), d, sqlType);
	}

	/* TripRequestInterface interface */

	public TripRequest newInstance(ITripRequest tripRequestObject) {
		if (tripRequestObject == null)
			return null;
		if (tripRequestObject instanceof TripRequest)
			return (TripRequest) tripRequestObject;

		TripRequest tRequest = null;
		Place from = new Place().newInstance(tripRequestObject.getFromPlace());
		Place to = new Place().newInstance(tripRequestObject.getToPlace());
		DateTimePrefs datetime = new DateTimePrefs()
				.newInstance(tripRequestObject.getDateTime());
		DateTimePrefs returnDatetime = tripRequestObject.hasReturnDateTime() ? new DateTimePrefs()
				.newInstance(tripRequestObject.getReturnDateTime()) : null;
		Trip_WeekDaysVarray weekDaysVarray = null;
		if (tripRequestObject.hasWeekDaysPeriodicity()) {
			char[] weekDays = tripRequestObject.getWeekDays();
			String[] weekDaysStr = new String[weekDays.length];
			for (int n = 0; n < weekDays.length; n++) {
				weekDaysStr[n] = String.valueOf(weekDays[n]);
			}
			weekDaysVarray = new Trip_WeekDaysVarray(weekDaysStr);
		}
		User requester = new User().newInstance(tripRequestObject
				.getRequester());

		if (tripRequestObject.hasReturnDateTime()
				&& tripRequestObject.hasWeekDaysPeriodicity())
			tRequest = new TripRequest(from, to, datetime, returnDatetime,
					requester, tripRequestObject.getNSeats(), weekDaysVarray,
					tripRequestObject.getPeriodicity().name());
		if (!tripRequestObject.hasReturnDateTime()
				&& !tripRequestObject.hasWeekDaysPeriodicity())
			tRequest = new TripRequest(from, to, datetime, requester,
					tripRequestObject.getNSeats());
		if (tripRequestObject.hasReturnDateTime()
				&& !tripRequestObject.hasWeekDaysPeriodicity())
			tRequest = new TripRequest(from, to, datetime, returnDatetime,
					requester, tripRequestObject.getNSeats());
		if (!tripRequestObject.hasReturnDateTime()
				&& tripRequestObject.hasWeekDaysPeriodicity())
			tRequest = new TripRequest(from, to, datetime, requester,
					tripRequestObject.getNSeats(), weekDaysVarray,
					tripRequestObject.getPeriodicity().name());

		tRequest.setTripId(tripRequestObject.getTripId());
		if (tripRequestObject.hasDistance())
			tRequest.setDistance(tripRequestObject.getDistance());
		if (tripRequestObject.hasCharacteristics())
			tRequest.setCharacteristics(tripRequestObject.getCharacteristics());

		return tRequest;
	}

	public void setDateTime(IDateTimePrefs dateTimePrefsObject) {
		try {
			super.setDatetime(new DateTimePrefs()
					.newInstance(dateTimePrefsObject));
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	@Override
	public DateTimePrefs getDateTime() {
		DateTimePrefs datetime = null;
		try {
			datetime = (DateTimePrefs) super.getDatetime();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return datetime;
	}

	public void setReturnDateTime(IDateTimePrefs dateTimePrefsObject) {
		try {
			super.setReturnDatetime(new DateTimePrefs()
					.newInstance(dateTimePrefsObject));
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	@Override
	public DateTimePrefs getReturnDateTime() {
		DateTimePrefs returnDatetime = null;
		try {
			returnDatetime = (DateTimePrefs) super.getReturnDatetime();
			if (returnDatetime == null || returnDatetime.getDatetime() == null)
				return null;
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return returnDatetime;
	}

	public void setRequester(IUser requester) {
		try {
			super.setTripRequester(new User().newInstance(requester));
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public User getRequester() {
		User user = null;
		try {
			user = super.getRequester();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return user;
	}

	/* superclass methods */

	public java.sql.Timestamp compareTo() throws java.sql.SQLException {

		java.sql.Timestamp __jRt_0 = null;
		try {
			__jRt_0 = super.compareTo();

		} catch (java.sql.SQLException except) {
			except.printStackTrace();
			throw new java.sql.SQLException(except.getClass().getName() + ": "
					+ except.getMessage());
		}
		return __jRt_0;
	}

	public TripRequestRef getTripRequestRef() {
		TripRequestRef ref = new TripRequestRef();
		try {
			ref.setValue(this);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return ref;
	}

	public void setTripRequestRef(TripRequestRef ref) {
		try {
			ref.setValue(this);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}
}/* @lineinfo:generated-code */