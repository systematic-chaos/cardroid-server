package uclm.esi.cardroid.data.oracle;

import java.sql.Blob;
import java.sql.SQLException;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;

import uclm.esi.cardroid.data.ICar;
import uclm.esi.cardroid.data.IPassenger;
import uclm.esi.cardroid.data.IPlace;
import uclm.esi.cardroid.data.IUser;

/**
 * \class TripOffer_Passenger
 * Domain class implementing a Passenger for its storage and retrieval from an 
 * Oracle database
 */
public class TripOffer_Passenger extends TripOffer_PassengerTyp implements
		ORAData, ORADataFactory, IPassenger {
	private static final TripOffer_Passenger _PassengerFactory = new TripOffer_Passenger();

	public static ORADataFactory getORADataFactory() {
		return _PassengerFactory;
	}

	public TripOffer_Passenger() {
		super();
	}

	/**
	 * Default constructor
	 */
	public TripOffer_Passenger(User usr, int nSeats) {
		setUserPassenger(usr);
		setNSeats(nSeats);
	}

	/* ORAData interface */

	public ORAData create(Datum d, int sqlType) throws SQLException {
		return create(new TripOffer_Passenger(), d, sqlType);
	}

	/* superclass accessors */

	public void setUserPassenger(IUser userPassenger) {
		try {
			super.setUsr(new User().newInstance(userPassenger));
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}

	}

	public User getUserPassenger() {
		User user = null;
		try {
			user = super.getUsr();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return user;
	}

	public void setNSeats(int nSeats) {
		try {
			super.setNSeats(nSeats);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public int getNSeats() {
		int nSeats = 1;
		try {
			nSeats = super.getNSeats();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return nSeats;
	}

	/* IPassenger interface */

	public User newInstance(IUser userObject) {
		return new User().newInstance(userObject);
	}

	public TripOffer_Passenger newInstance(IPassenger passengerObject) {
		if (passengerObject == null)
			return null;
		if (passengerObject instanceof TripOffer_Passenger)
			return (TripOffer_Passenger) passengerObject;

		TripOffer_Passenger passenger = new TripOffer_Passenger(
				newInstance(passengerObject.getUserPassenger()),
				passengerObject.getNSeats());

		return passenger;
	}

	public void setName(String name) {
		getUserPassenger().setName(name);
	}

	public String getName() {
		return getUserPassenger().getName();
	}

	public void setSurname(String surname) {
		getUserPassenger().setSurname(surname);
	}

	public String getSurname() {
		return getUserPassenger().getSurname();
	}

	public void setAvatar(Blob avatar) {
		getUserPassenger().setAvatar(avatar);
	}

	public boolean hasAvatar() {
		return getUserPassenger().hasAvatar();
	}

	public Blob getAvatar() {
		return getUserPassenger().getAvatar();
	}

	public void setHome(IPlace home) {
		getUserPassenger().setHome(home);
	}

	public IPlace getHome() {
		return getUserPassenger().getHome();
	}

	public void setTelephoneNumber(int telephone) {
		getUserPassenger().setTelephoneNumber(telephone);
	}

	public int getTelephoneNumber() {
		return getUserPassenger().getTelephoneNumber();
	}

	public void setEmail(String email) {
		getUserPassenger().setEmail(email);
	}

	public String getEmail() {
		return getUserPassenger().getEmail();
	}

	public void setReputation(int reputation) {
		getUserPassenger().setReputation(reputation);
	}

	public int getReputation() {
		return getUserPassenger().getReputation();
	}

	public boolean hasReputation() {
		return getUserPassenger().hasReputation();
	}

	public void increaseReputation() {
		getUserPassenger().increaseReputation();
	}

	public void increaseReputation(int increase) {
		getUserPassenger().increaseReputation(increase);
	}

	public void decreaseReputation() {
		getUserPassenger().decreaseReputation();
	}

	public void decreaseReputation(int decrease) {
		getUserPassenger().decreaseReputation(decrease);
	}

	public void setCars(ICar[] cars) {
		getUserPassenger().setCars(cars);
	}

	public ICar[] getCars() {
		return getUserPassenger().getCars();
	}

	public boolean addCar(ICar car) {
		return getUserPassenger().addCar(car);
	}

	public boolean removeCar(ICar car) {
		return getUserPassenger().removeCar(car);
	}

	public void clearCars() {
		getUserPassenger().clearCars();
	}

	public int getNCars() {
		return getUserPassenger().getNCars();
	}

	public void setRef(TripOffer_PassengerRef ref) throws SQLException {
		ref.setValue(this);
	}

	public TripOffer_PassengerRef getRef() throws SQLException {
		TripOffer_PassengerRef ref = new TripOffer_PassengerRef();
		ref.setValue(this);
		return ref;
	}
}
