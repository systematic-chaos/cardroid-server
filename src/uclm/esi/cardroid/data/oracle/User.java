/*@lineinfo:filename=UserTyp*//*@lineinfo:user-code*//*@lineinfo:1^1*/package uclm.esi.cardroid.data.oracle;

import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Connection;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;

import sqlj.runtime.ref.DefaultContext;

import uclm.esi.cardroid.data.ICar;
import uclm.esi.cardroid.data.IPlace;
import uclm.esi.cardroid.data.IUser;

/**
 * \class User
 * Domain class implementing an User for its storage and retrieval from an 
 * Oracle database
 */
public class User extends UserTyp implements ORAData, ORADataFactory, IUser {
	private static final User _UserFactory = new User();

	public static ORADataFactory getORADataFactory() {
		return _UserFactory;
	}

	public User() {
		super();
	}

	public User(Connection conn) throws SQLException {
		super(conn);
	}

	public User(DefaultContext ctx) throws SQLException {
		super(ctx);
	}

	/**
	 * Long constructor
	 */
	public User(String name, String surname, java.sql.Blob avatar, Place home,
			int telephone, String email, int reputation, User_CarCollection cars) {
		setName(name);
		setSurname(surname);
		// setAvatar(avatar);
		setHome(home);
		setTelephoneNumber(telephone);
		setEmail(email);
		setReputation(reputation);
		setCars(cars);
	}

	/**
	 * Short constructor
	 */
	public User(String name, String surname, Place home, int telephone,
			String email) {
		setName(name);
		setSurname(surname);
		setHome(home);
		setTelephoneNumber(telephone);
		setEmail(email);
	}

	/* ORAData interface */

	public ORAData create(Datum d, int sqlType) throws SQLException {
		return create(new User(), d, sqlType);
	}

	/* UserInterface interface */

	public User newInstance(IUser userObject) {
		if (userObject == null)
			return null;
		if (userObject instanceof User)
			return (User) userObject;
		Place home = new Place().newInstance(userObject.getHome());
		ICar[] cars = userObject.getCars();
		Car[] c = new Car[cars.length];
		for (int n = 0; n < cars.length; n++) {
			c[n] = new Car().newInstance(cars[n]);
		}
		/*
		 * return new User(userObject.getName(), userObject.getSurname(),
		 * userObject.getAvatar(), home, userObject.getTelephoneNumber(),
		 * userObject.getEmail(), userObject.getReputation(), new
		 * User_CarCollection(c));
		 */
		return new User(userObject.getName(), userObject.getSurname(), null,
				home, userObject.getTelephoneNumber(), userObject.getEmail(),
				userObject.getReputation(), new User_CarCollection(c));
	}

	@Override
	public void setName(String name) {
		try {
			super.setName(name);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	@Override
	public String getName() {
		String name = null;
		try {
			name = super.getName();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return name;
	}

	@Override
	public void setSurname(String surname) {
		try {
			super.setSurname(surname);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	@Override
	public String getSurname() {
		String surname = null;
		try {
			surname = super.getSurname();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return surname;
	}

	@Override
	public void setAvatar(Blob avatar) {
		try {
			super.setAvatar(avatar);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	@Override
	public Blob getAvatar() {
		Blob avatar = null;
		try {
			avatar = super.getAvatar();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return avatar;
	}

	public boolean hasAvatar() {
		return getAvatar() != null;
	}

	@Override
	public void setHome(IPlace home) {
		try {
			super.setHomePlace(new Place().newInstance(home));
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	@Override
	public Place getHome() {
		Place home = null;
		try {
			home = super.getHome();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return home;
	}

	@Override
	public void setTelephoneNumber(int telephone) {
		try {
			super.setTelephoneNumber(telephone);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	@Override
	public int getTelephoneNumber() {
		int telephone = 0;
		try {
			telephone = super.getTelephoneNumber();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return telephone;
	}

	@Override
	public void setEmail(String email) {
		try {
			super.setEmail(email);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	@Override
	public String getEmail() {
		String email = null;
		try {
			email = super.getEmail();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return email;
	}

	@Override
	public void setReputation(int reputation) {
		try {
			super.setReputation(reputation);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	@Override
	public int getReputation() {
		int reputation = 0;
		try {
			reputation = super.getReputation();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return reputation;
	}

	public boolean hasReputation() {
		return getReputation() > 0;
	}

	public void setCars(User_CarCollection cars) {
		try {
			super.setCars(cars);
		} catch (SQLException sqle) {
			System.err.println("SLQException: " + sqle.getSQLState());
		}
	}

	public void setCars(ICar[] cars) {
		Car[] c = new Car[cars.length];
		try {
			for (int n = 0; n < cars.length; n++) {
				c[n] = new Car().newInstance(cars[n]);
			}
			super.setCars(new User_CarCollection(c));
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public Car[] getCars() {
		Car[] cars = null;
		try {
			cars = super.getCarsCollection().getArray();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return cars;
	}

	/* superclass methods */

	public boolean addCar(ICar carObject) {

		Car __jRt_838 = new Car().newInstance(carObject);
		try {
			super.addCar(__jRt_838);

		} catch (java.sql.SQLException except) {
			except.printStackTrace();
			System.err.println(except.getClass().getName() + ": "
					+ except.getMessage());
		}
		return true;
	}

	public void clearCars() {

		try {
			super.clearCarsCollection();

		} catch (java.sql.SQLException except) {
			except.printStackTrace();
			System.err.println(except.getClass().getName() + ": "
					+ except.getMessage());
		}
	}

	public void decreaseReputation() {

		try {
			super.decreaseUserReputation();

		} catch (java.sql.SQLException except) {
			except.printStackTrace();
			System.err.println(except.getClass().getName() + ": "
					+ except.getMessage());
		}
	}

	public void decreaseReputation(int decrease) {

		try {
			int __jRt_840 = decrease;
			super.decreaseUserReputation(__jRt_840);

		} catch (java.sql.SQLException except) {
			except.printStackTrace();
			System.err.println(except.getClass().getName() + ": "
					+ except.getMessage());
		}
	}

	public int getNCars() {

		int __jRt_0 = 0;
		try {
			__jRt_0 = super.getNCars();

		} catch (java.sql.SQLException except) {
			except.printStackTrace();
			System.err.println(except.getClass().getName() + ": "
					+ except.getMessage());
		}
		return __jRt_0;
	}

	public void increaseReputation(int increase) {

		try {
			int __jRt_842 = increase;
			super.increaseUserReputation(__jRt_842);

		} catch (java.sql.SQLException except) {
			except.printStackTrace();
			System.err.println(except.getClass().getName() + ": "
					+ except.getMessage());
		}
	}

	public void increaseReputation() {

		try {
			super.increaseUserReputation();

		} catch (java.sql.SQLException except) {
			except.printStackTrace();
			System.err.println(except.getClass().getName() + ": "
					+ except.getMessage());
		}
	}

	public boolean removeCar(ICar carObject) {

		boolean __jRt_0 = true;
		Car __jRt_844 = new Car().newInstance(carObject);
		try {
			super.removeCar(__jRt_844);

		} catch (java.sql.SQLException except) {
			__jRt_0 = false;
			except.printStackTrace();
			System.err.println(except.getClass().getName() + ": "
					+ except.getMessage());
		}
		return __jRt_0;
	}

	public UserRef getRef() throws SQLException {
		UserRef ref = new UserRef();
		ref.setValue(this);
		return ref;
	}

	public void setRef(UserRef ref) throws SQLException {
		ref.setValue(this);
	}
}/* @lineinfo:generated-code */