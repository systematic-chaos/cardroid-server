/*@lineinfo:filename=TripTyp*//*@lineinfo:user-code*//*@lineinfo:1^1*/package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;

import sqlj.runtime.ref.DefaultContext;

import uclm.esi.cardroid.data.IDate;
import uclm.esi.cardroid.data.IPlace;
import uclm.esi.cardroid.data.ITrip;

/**
 * \class Trip
 * Domain class implementing a Trip for its storage and retrieval from an 
 * Oracle database
 */
public class Trip extends TripTyp implements ORAData, ORADataFactory, ITrip {
	private static final Trip _TripFactory = new Trip();

	public static ORADataFactory getORADataFactory() {
		return _TripFactory;
	}

	public Trip() {
		super();
	}

	protected Trip(Connection conn) throws SQLException {
		super(conn);
	}

	protected Trip(DefaultContext ctx) throws SQLException {
		super(ctx);
	}

	/**
	 * Long constructor
	 */
	public Trip(Place fromPlace, Place toPlace, Date datetime, int nSeats,
			Date returnDatetime, Trip_WeekDaysVarray weekDays,
			String periodicity, int distance, String characteristics) {
		setFromPlace(fromPlace);
		setToPlace(toPlace);
		setDateTime(datetime);
		setNSeats(nSeats);
		setReturnDateTime(returnDatetime);
		setWeekDays(weekDays);
		setPeriodicity(periodicity);
		setDistance(distance);
		setCharacteristics(characteristics);
	}

	/**
	 * Short constructor
	 */
	public Trip(Place fromPlace, Place toPlace, Date datetime, int nSeats) {
		setFromPlace(fromPlace);
		setToPlace(toPlace);
		setDateTime(datetime);
		setNSeats(nSeats);
	}

	/**
	 * Another constructor
	 */
	public Trip(Place fromPlace, Place toPlace, Date datetime, int nSeats,
			Date returnDatetime) {
		setFromPlace(fromPlace);
		setToPlace(toPlace);
		setDateTime(datetime);
		setNSeats(nSeats);
		setReturnDateTime(returnDatetime);
	}

	/**
	 * Another constructor
	 */
	public Trip(Place fromPlace, Place toPlace, Date datetime, int nSeats,
			Trip_WeekDaysVarray weekDays, String periodicity) {
		setFromPlace(fromPlace);
		setToPlace(toPlace);
		setDateTime(datetime);
		setNSeats(nSeats);
		setWeekDays(weekDays);
		setPeriodicity(periodicity);
	}

	/**
	 * Another constructor
	 */
	public Trip(Place fromPlace, Place toPlace, Date datetime, int nSeats,
			Date returnDatetime, Trip_WeekDaysVarray weekDays,
			String periodicity) {
		setFromPlace(fromPlace);
		setToPlace(toPlace);
		setDateTime(datetime);
		setNSeats(nSeats);
		setReturnDateTime(returnDatetime);
		setWeekDays(weekDays);
		setPeriodicity(periodicity);
	}

	/* ORAData interface */

	protected ORAData createExact(Datum d, int sqlType) throws SQLException {
		return create(new Trip(), d, sqlType);
	}

	/* ITrip interface */

	public Trip newInstance(ITrip tripObject) {
		if (tripObject == null)
			return null;
		if (tripObject instanceof Trip)
			return (Trip) tripObject;

		Trip trip = null;
		Place from = new Place().newInstance(tripObject.getFromPlace());
		Place to = new Place().newInstance(tripObject.getToPlace());
		Date datetime = new Date().newInstance(tripObject.getDateTime());
		Date returnDatetime = tripObject.hasReturnDateTime() ? new Date()
				.newInstance(tripObject.getReturnDateTime()) : null;
		Trip_WeekDaysVarray weekDaysVarray = null;
		if (tripObject.hasWeekDaysPeriodicity()) {
			char[] weekDays = tripObject.getWeekDays();
			String[] weekDaysStr = new String[weekDays.length];
			for (int n = 0; n < weekDays.length; n++) {
				weekDaysStr[n] = String.valueOf(weekDays[n]);
			}
			weekDaysVarray = new Trip_WeekDaysVarray(weekDaysStr);
		}

		if (tripObject.hasReturnDateTime()
				&& tripObject.hasWeekDaysPeriodicity())
			trip = new Trip(from, to, datetime, tripObject.getNSeats(),
					returnDatetime, weekDaysVarray, tripObject.getPeriodicity()
							.name());
		if (!tripObject.hasReturnDateTime()
				&& !tripObject.hasWeekDaysPeriodicity())
			trip = new Trip(from, to, datetime, tripObject.getNSeats());
		if (tripObject.hasReturnDateTime()
				&& !tripObject.hasWeekDaysPeriodicity())
			trip = new Trip(from, to, datetime, tripObject.getNSeats(),
					returnDatetime);
		if (!tripObject.hasReturnDateTime()
				&& tripObject.hasWeekDaysPeriodicity())
			trip = new Trip(from, to, datetime, tripObject.getNSeats(),
					weekDaysVarray, tripObject.getPeriodicity().name());

		trip.setTripId(tripObject.getTripId());
		if (tripObject.hasDistance())
			trip.setDistance(tripObject.getDistance());
		if (tripObject.hasCharacteristics())
			trip.setCharacteristics(tripObject.getCharacteristics());

		return trip;
	}

	public void setTripId(int tripId) {
		try {
			super.setTripId(tripId);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public int getTripId() {
		int tripId = 0;
		try {
			tripId = super.getTripId();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return tripId;
	}

	public void setFromPlace(IPlace from) {
		try {
			if (from instanceof Place) {
				super.setPlace1((Place) from);
			} else {
				super.setPlace1(new Place().newInstance(from));
			}
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public Place getFromPlace() {
		Place place = null;
		try {
			place = super.getPlace1();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return place;
	}

	public void setToPlace(IPlace to) {
		try {
			if (to instanceof Place) {
				super.setPlace2((Place) to);
			} else {
				super.setPlace2(new Place().newInstance(to));
			}
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public Place getToPlace() {
		Place place = null;
		try {
			place = super.getPlace2();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return place;
	}

	public void setDateTime(IDate datetime) {
		try {
			if (datetime instanceof Date) {
				super.setDatetime((Date) datetime);
			} else {
				super.setDatetime(new Date().newInstance(datetime));
			}
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public Date getDateTime() {
		Date datetime = null;
		try {
			datetime = super.getDatetime();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return datetime;
	}

	@Override
	public void setNSeats(int nSeats) {
		try {
			super.setNSeats(nSeats);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	@Override
	public int getNSeats() {
		int nSeats = 0;
		try {
			nSeats = super.getNSeats();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return nSeats;
	}

	public void setReturnDateTime(IDate returnDatetime) {
		try {
			if (returnDatetime instanceof Date) {
				super.setReturnDatetime((Date) returnDatetime);
			} else {
				super.setReturnDatetime(new Date().newInstance(returnDatetime));
			}
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public Date getReturnDateTime() {
		Date returnDatetime = null;
		try {
			returnDatetime = super.getReturnDatetime();
			if (returnDatetime == null || returnDatetime.getDatetime() == null)
				return null;
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return returnDatetime;
	}

	public boolean hasReturnDateTime() {
		return getReturnDateTime() != null;
	}

	@Override
	public void setWeekDays(Trip_WeekDaysVarray weekDaysVarray) {
		try {
			super.setWeekDays(weekDaysVarray);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public void setWeekDays(char[] weekDays) {
		String[] weekDaysStr = new String[weekDays.length];
		for (int n = 0; n < weekDays.length; n++) {
			weekDaysStr[n] = String.valueOf(weekDays[n]);
		}
		try {
			super.setWeekDays(new Trip_WeekDaysVarray(weekDaysStr));
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public char[] getWeekDays() {
		char[] weekDaysChar = null;
		try {
			if (super.getWeekDaysVarray() == null)
				return null;
			String[] weekDays = super.getWeekDaysVarray().getArray();
			weekDaysChar = new char[weekDays.length];
			for (int n = 0; n < weekDays.length; n++) {
				weekDaysChar[n] = weekDays[n].charAt(0);
			}
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return weekDaysChar;
	}

	public void setPeriodicity(Periodicity periodicity) {
		try {
			super.setPeriodicity(periodicity.name());
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	@Override
	public void setPeriodicity(String periodicityName) {
		try {
			super.setPeriodicity(periodicityName);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public Periodicity getPeriodicity() {
		Periodicity periodicity = null;
		try {
			if (super.getPeriodicityName() == null)
				return null;
			periodicity = ITrip.Periodicity.valueOf(super.getPeriodicityName());
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return periodicity;
	}

	public void setWeekDaysPeriodicity(char[] weekDays, Periodicity periodicity) {
		setWeekDays(weekDays);
		setPeriodicity(periodicity);
	}

	public boolean hasWeekDaysPeriodicity() {
		return getWeekDays() != null && getPeriodicity() != null;
	}

	@Override
	public void setDistance(int distance) {
		try {
			super.setDistance(distance);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	@Override
	public int getDistance() {
		int distance = 0;
		try {
			distance = super.getDistance();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return distance;
	}

	public boolean hasDistance() {
		return getDistance() > 0;
	}

	@Override
	public void setCharacteristics(String characteristics) {
		try {
			super.setCharacteristics(characteristics);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	@Override
	public String getCharacteristics() {
		String characteristics = null;
		try {
			characteristics = super.getCharacteristics();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return characteristics;
	}

	public boolean hasCharacteristics() {
		return getCharacteristics() != null;
	}

	@Override
	public void setTripType(int tripType) {
		try {
			super.setTripType(tripType);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getMessage());
		}
	}

	@Override
	public int getTripType() {
		int tripType = -1;
		try {
			tripType = super.getTripType();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getMessage());
		}
		return tripType;
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

	public TripRef getTripRef() throws SQLException {
		TripRef ref = new TripRef();
		ref.setValue(this);
		return ref;
	}

	public void setTripRef(TripRef ref) throws SQLException {
		ref.setValue(this);
	}
}/* @lineinfo:generated-code */