/*@lineinfo:filename=TripTypOffer*//*@lineinfo:user-code*//*@lineinfo:1^1*/package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;

import sqlj.runtime.ref.DefaultContext;

import uclm.esi.cardroid.data.ICar;
import uclm.esi.cardroid.data.IDateTime;
import uclm.esi.cardroid.data.IPassenger;
import uclm.esi.cardroid.data.IPlace;
import uclm.esi.cardroid.data.ITripOffer;
import uclm.esi.cardroid.data.IUser;
import uclm.esi.cardroid.data.IWaypoint;

/**
 * \class TripOffer
 * Domain class implementing a TripOffer for its storage and retrieval from an 
 * Oracle database
 */
public class TripOffer extends TripOfferTyp implements ORAData, ORADataFactory,
		ITripOffer {
	private static final TripOffer _TripOfferFactory = new TripOffer();

	public static ORADataFactory getORADataFactory() {
		return _TripOfferFactory;
	}

	public TripOffer() {
		super();
	}

	public TripOffer(Connection conn) throws SQLException {
		super(conn);
	}

	public TripOffer(DefaultContext ctx) throws SQLException {
		super(ctx);
	}

	/**
	 * Short constructor
	 */
	public TripOffer(Place from, Place to, DateTime datetime, User driver,
			Car car, int availableSeats, double price,
			TripOffer_AllowedVarray allow) {
		setFromPlace(from);
		setToPlace(to);
		setDateTime(datetime);
		setDriver(driver);
		setCar(car);
		setNSeats(availableSeats);
		setPrice(price);
		setAllowed(allow);
	}

	/**
	 * Another constructor
	 */
	public TripOffer(Place from, Place to, DateTime datetime,
			DateTime returnDatetime, User driver, Car car, int availableSeats,
			double price, TripOffer_AllowedVarray allow) {
		setFromPlace(from);
		setToPlace(to);
		setDateTime(datetime);
		setReturnDateTime(returnDatetime);
		setDriver(driver);
		setCar(car);
		setNSeats(availableSeats);
		setPrice(price);
		setAllowed(allow);
	}

	/**
	 * Another constructor
	 */
	public TripOffer(Place from, Place to, DateTime datetime, User driver,
			Car car, int availableSeats, double price,
			TripOffer_AllowedVarray allow, Trip_WeekDaysVarray weekDays,
			String periodicity) {
		setFromPlace(from);
		setToPlace(to);
		setDateTime(datetime);
		setDriver(driver);
		setCar(car);
		setNSeats(availableSeats);
		setPrice(price);
		setAllowed(allow);
		setWeekDays(weekDays);
		setPeriodicity(periodicity);
	}

	/**
	 * Another constructor
	 */
	public TripOffer(Place from, Place to, DateTime datetime,
			DateTime returnDatetime, User driver, Car car, int availableSeats,
			double price, TripOffer_AllowedVarray allow,
			Trip_WeekDaysVarray weekDays, String periodicity) {
		setFromPlace(from);
		setToPlace(to);
		setDateTime(datetime);
		setReturnDateTime(returnDatetime);
		setDriver(driver);
		setCar(car);
		setNSeats(availableSeats);
		setPrice(price);
		setAllowed(allow);
		setWeekDays(weekDays);
		setPeriodicity(periodicity);
	}

	/**
	 * Long constructor
	 */
	public TripOffer(Place fromPlace, Place toPlace, DateTime datetime,
			int nSeats, DateTime returnDatetime, Trip_WeekDaysVarray weekDays,
			String periodicity, int distance, String characteristics,
			User driver, TripOffer_WaypointCollection waypoints, Car car,
			TripOffer_PassengerCollection passengers, double price,
			TripOffer_AllowedVarray allowed) {
		setFromPlace(fromPlace);
		setToPlace(toPlace);
		setDateTime(datetime);
		setNSeats(nSeats);
		setReturnDateTime(returnDatetime);
		setWeekDays(weekDays);
		setPeriodicity(periodicity);
		setDistance(distance);
		setCharacteristics(characteristics);
		setDriver(driver);
		setWaypointsCollection(waypoints);
		setCar(car);
		setPassengersCollection(passengers);
		setPrice(price);
		setAllowed(allowed);
	}

	/* ORAData interface */

	protected ORAData createExact(Datum d, int sqlType) throws SQLException {
		return create(new TripOffer(), d, sqlType);
	}

	/* TripOfferInterface interface */

	public TripOffer newInstance(ITripOffer tripOfferObject) {
		if (tripOfferObject == null)
			return null;
		if (tripOfferObject instanceof TripRequest)
			return (TripOffer) tripOfferObject;

		TripOffer tOffer = null;
		Place from = new Place().newInstance(tripOfferObject.getFromPlace());
		Place to = new Place().newInstance(tripOfferObject.getToPlace());
		DateTime datetime = new DateTime().newInstance(tripOfferObject
				.getDateTime());
		Trip_WeekDaysVarray weekDaysVarray = null;
		if (tripOfferObject.hasWeekDaysPeriodicity()) {
			char[] weekDays = tripOfferObject.getWeekDays();
			String[] weekDaysStr = new String[weekDays.length];
			for (int n = 0; n < weekDays.length; n++) {
				weekDaysStr[n] = String.valueOf(weekDays[n]);
			}
			weekDaysVarray = new Trip_WeekDaysVarray(weekDaysStr);
		}
		User driver = new User().newInstance(tripOfferObject.getDriver());
		IWaypoint[] waypoints = tripOfferObject.getWaypoints();
		TripOffer_Waypoint[] wp = new TripOffer_Waypoint[waypoints.length];
		for (int n = 0; n < waypoints.length; n++) {
			wp[n] = new TripOffer_Waypoint().newInstance(waypoints[n]);
		}
		TripOffer_WaypointCollection waypointsCollection = new TripOffer_WaypointCollection(
				wp);
		Car car = new Car().newInstance(tripOfferObject.getCar());
		IPassenger[] passengers = tripOfferObject.getPassengers();
		TripOffer_Passenger[] p = new TripOffer_Passenger[passengers.length];
		for (int n = 0; n < passengers.length; n++) {
			p[n] = new TripOffer_Passenger().newInstance(passengers[n]);
		}
		TripOffer_PassengerCollection passengersCollection = new TripOffer_PassengerCollection(
				p);
		boolean[] allowed = tripOfferObject.getAllowed();
		String[] allowedStr = new String[allowed.length];
		for (int n = 0; n < allowed.length; n++) {
			allowedStr[n] = String.valueOf(allowed[n]).substring(0, 1);
		}
		TripOffer_AllowedVarray allowedVarray = new TripOffer_AllowedVarray(
				allowedStr);
		DateTime returnDatetime = tripOfferObject.hasReturnDateTime() ? new DateTime()
				.newInstance(tripOfferObject.getReturnDateTime()) : null;
		String[] weekDays = null;
		uclm.esi.cardroid.data.zerocice.Periodicity periodicity = null;
		if (tripOfferObject.hasWeekDaysPeriodicity()) {
			char[] wd = tripOfferObject.getWeekDays();
			weekDays = new String[wd.length];
			for (int n = 0; n < wd.length; n++)
				weekDays[n] = String.valueOf(wd[n]).substring(0, 1);
			periodicity = uclm.esi.cardroid.data.zerocice.Periodicity
					.valueOf(tripOfferObject.getPeriodicity().name());
		}

		if (tripOfferObject.hasReturnDateTime()
				&& tripOfferObject.hasWeekDaysPeriodicity())
			tOffer = new TripOffer(from, to, datetime, returnDatetime, driver,
					car, tripOfferObject.getNSeats(),
					tripOfferObject.getPrice(), allowedVarray, weekDaysVarray,
					tripOfferObject.getPeriodicity().name());
		if (!tripOfferObject.hasReturnDateTime()
				&& !tripOfferObject.hasWeekDaysPeriodicity())
			tOffer = new TripOffer(from, to, datetime, driver, car,
					tripOfferObject.getNSeats(), tripOfferObject.getPrice(),
					allowedVarray);
		if (tripOfferObject.hasReturnDateTime()
				&& !tripOfferObject.hasWeekDaysPeriodicity())
			tOffer = new TripOffer(from, to, datetime, returnDatetime, driver,
					car, tripOfferObject.getNSeats(),
					tripOfferObject.getPrice(), allowedVarray);
		if (!tripOfferObject.hasReturnDateTime()
				&& tripOfferObject.hasWeekDaysPeriodicity())
			tOffer = new TripOffer(from, to, datetime, returnDatetime, driver,
					car, tripOfferObject.getNSeats(),
					tripOfferObject.getPrice(), allowedVarray, weekDaysVarray,
					periodicity.name());

		tOffer.setTripId(tripOfferObject.getTripId());
		tOffer.setWaypointsCollection(waypointsCollection);
		tOffer.setPassengersCollection(passengersCollection);
		if (tripOfferObject.hasDistance())
			tOffer.setDistance(tripOfferObject.getDistance());
		if (tripOfferObject.hasCharacteristics())
			tOffer.setCharacteristics(tripOfferObject.getCharacteristics());

		return tOffer;
	}

	public void setDateTime(IDateTime datetime) {
		try {
			super.setDatetime(new DateTime().newInstance(datetime));
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	@Override
	public DateTime getDateTime() {
		DateTime datetime = null;
		try {
			datetime = (DateTime) super.getDatetime();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return datetime;
	}

	public void setReturnDateTime(IDateTime returnDatetime) {
		try {
			super.setReturnDatetime(new DateTime().newInstance(returnDatetime));
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	@Override
	public DateTime getReturnDateTime() {
		DateTime returnDatetime = null;
		try {
			returnDatetime = (DateTime) super.getReturnDatetime();
			if (returnDatetime == null || returnDatetime.getDatetime() == null)
				return null;
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return returnDatetime;
	}

	public void setDriver(IUser driver) {
		try {
			super.setTripDriver(new User().newInstance(driver));
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public User getDriver() {
		User driver = null;
		try {
			driver = super.getDriver();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return driver;
	}

	public TripOffer_Waypoint[] getWaypoints() {
		TripOffer_Waypoint[] waypoints = null;
		try {
			waypoints = super.getWaypointsCollection().getArray();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return waypoints;
	}

	public void setWaypointsCollection(TripOffer_WaypointCollection waypoints) {
		try {
			super.setWaypointsCollection(waypoints);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public void setWaypoints(IWaypoint[] waypoints) {
		TripOffer_Waypoint[] wp = new TripOffer_Waypoint[waypoints.length];
		for (int n = 0; n < waypoints.length; n++) {
			wp[n] = new TripOffer_Waypoint().newInstance(waypoints[n]);
		}
		try {
			super.setWaypointsCollection(new TripOffer_WaypointCollection(wp));
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public boolean addWaypoint(IPlace waypoint) {
		try {
			super.addTripWaypoint(new Place().newInstance(waypoint));
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return true;
	}

	public void setPassengersCollection(TripOffer_PassengerCollection passengers) {
		try {
			super.setPassengersCollection(passengers);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public void setPassengers(IPassenger[] passengers) {
		TripOffer_Passenger[] p = new TripOffer_Passenger[passengers.length];
		try {
			for (int n = 0; n < passengers.length; n++) {
				p[n] = new TripOffer_Passenger().newInstance(passengers[n]);
			}
			super.setPassengersCollection(new TripOffer_PassengerCollection(p));
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public TripOffer_Passenger[] getPassengers() {
		TripOffer_Passenger[] passengers = null;
		try {
			TripOffer_Passenger[] p = (TripOffer_Passenger[]) super
					.getPassengersCollection().getArray();
			passengers = new TripOffer_Passenger[p.length];
			for (int n = 0; n < passengers.length; n++) {
				passengers[n] = p[n];
			}
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return passengers;
	}

	public boolean addPassenger(IPassenger passenger) {
		try {
			super.addTripPassenger(
					new User().newInstance(passenger.getUserPassenger()),
					passenger.getNSeats());
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return true;
	}

	public boolean removePassenger(IUser passenger) {
		boolean has = true;
		try {
			super.removeTripPassenger(new User().newInstance(passenger));
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
			has = false;
		}
		return has;
	}

	public void setCar(ICar car) {
		try {
			super.setTripCar(new Car().newInstance(car));
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public Car getCar() {
		Car car = null;
		try {
			car = super.getCar();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return car;
	}

	public void setPrice(double price) {
		try {
			super.setPrice(price);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public double getPrice() {
		double price = 0.;
		try {
			price = super.getPrice();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return price;
	}

	@Override
	public void setAllowed(TripOffer_AllowedVarray allowedVarray) {
		try {
			super.setAllowed(allowedVarray);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public void setAllowed(boolean[] allowed) {
		String[] allowedStr = new String[allowed.length];
		for (int n = 0; n < allowed.length; n++) {
			allowedStr[n] = String.valueOf(allowed[n]).substring(0, 1);
		}
		try {
			super.setAllowed(new TripOffer_AllowedVarray(allowedStr));
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public boolean[] getAllowed() {
		boolean[] allowed = null;
		try {
			String[] allowedStr = super.getAllowedVarray().getArray();
			for (int n = 0; n < allowedStr.length; n++) {
				switch (allowedStr[n].charAt(0)) {
				case 't':
					allowedStr[n] = "true";
					break;
				case 'f':
					allowedStr[n] = "false";
					break;
				}
			}
			allowed = new boolean[allowedStr.length];
			for (int n = 0; n < allowed.length; n++) {
				allowed[n] = Boolean.valueOf(allowedStr[n]);
			}
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return allowed;
	}

	/* superclass methods */

	public boolean addPassenger(User passenger, int nSeats) {

		try {
			User __jRt_622 = passenger;
			Integer __jRt_623 = nSeats;
			super.addTripPassenger(__jRt_622, __jRt_623);

		} catch (java.sql.SQLException except) {
			except.printStackTrace();
			System.err.println(except.getClass().getName() + ": "
					+ except.getMessage());
		}
		return true;
	}

	public boolean addWaypoint(Place waypoint) {

		try {
			Place __jRt_624 = waypoint;
			super.addTripWaypoint(__jRt_624);

		} catch (java.sql.SQLException except) {
			except.printStackTrace();
			System.err.println(except.getClass().getName() + ": "
					+ except.getMessage());
		}
		return true;
	}

	public void clearPassengers() {

		try {
			super.clearPassengersCollection();

		} catch (java.sql.SQLException except) {
			except.printStackTrace();
			System.err.println(except.getClass().getName() + ": "
					+ except.getMessage());
		}
	}

	public void clearWaypoints() {

		try {
			super.clearWaypointsCollection();

		} catch (java.sql.SQLException except) {
			except.printStackTrace();
			System.err.println(except.getClass().getName() + ": "
					+ except.getMessage());
		}
	}

	public java.sql.Timestamp compareTo() throws SQLException {

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

	public int getNPassengers() {

		int __jRt_0 = 0;
		try {
			__jRt_0 = super.getNPassengers();

		} catch (java.sql.SQLException except) {
			except.printStackTrace();
			System.err.println(except.getClass().getName() + ": "
					+ except.getMessage());
		}
		return __jRt_0;
	}

	public int getNWaypoints() {

		int __jRt_0 = 0;
		try {
			__jRt_0 = super.getNWaypoints();

		} catch (java.sql.SQLException except) {
			except.printStackTrace();
			System.err.println(except.getClass().getName() + ": "
					+ except.getMessage());
		}
		return __jRt_0;
	}

	public boolean removePassenger(User passenger) {

		boolean __jRt_0 = true;
		try {
			User __jRt_626 = passenger;
			super.removeTripPassenger(__jRt_626);

		} catch (java.sql.SQLException except) {
			__jRt_0 = false;
			except.printStackTrace();
			System.err.println(except.getClass().getName() + ": "
					+ except.getMessage());
		}
		return __jRt_0;
	}

	public boolean removeWaypoint(int pos) {

		boolean __jRt_0 = true;
		try {
			int __jRt_628 = pos;
			super.removeTripWaypoint(__jRt_628);

		} catch (java.sql.SQLException except) {
			__jRt_0 = false;
			except.printStackTrace();
			System.err.println(except.getClass().getName() + ": "
					+ except.getMessage());
		}
		return __jRt_0;
	}

	public TripOfferRef getTripOfferRef() {
		TripOfferRef ref = new TripOfferRef();
		try {
			ref.setValue(this);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return ref;
	}

	public void setTripOfferRef(TripOfferRef ref) {
		try {
			ref.setValue(this);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}
}/* @lineinfo:generated-code */