package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;
import java.sql.Timestamp;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import uclm.esi.cardroid.data.ITripOffer;
import uclm.esi.cardroid.data.IUser;
import uclm.esi.cardroid.data.IUserActivity;

/**
 * \class UserActivity
 * Domain class implementing an UserActivity for its storage and retrieval 
 * from an Oracle database
 */
public class UserActivity extends UserActivityTyp implements ORAData,
		ORADataFactory, IUserActivity {
	private static final UserActivity _UserActivityFactory = new UserActivity();

	public static ORADataFactory getORADataFactory() {
		return _UserActivityFactory;
	}

	public UserActivity() {
		super();
	}

	/**
	 * Default constructor
	 */
	public UserActivity(User activityUser, TripOffer activityTrip,
			String activityType) {
		setUser(activityUser);
		setTrip(activityTrip);
		setType(ActivityType.valueOf(activityType));
	}

	/* ORAData interface */

	public ORAData create(Datum d, int sqlType) throws SQLException {
		return create(new UserActivity(), d, sqlType);
	}

	/* IUserActivity interface */

	public UserActivity newInstance(IUserActivity userActivityObject) {
		if (userActivityObject == null)
			return null;
		if (userActivityObject instanceof UserActivity)
			return (UserActivity) userActivityObject;
		User user = new User().newInstance(userActivityObject.getUser());
		TripOffer trip = new TripOffer().newInstance(userActivityObject
				.getTrip());
		return new UserActivity(user, trip, userActivityObject.getType().name());
	}

	public void setUser(IUser activityUser) {
		try {
			super.setActivityUser(new User().newInstance(activityUser));
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public User getUser() {
		User user = null;
		try {
			user = super.getActivityUser();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return user;
	}

	public void setTrip(ITripOffer activityTrip) {
		try {
			super.setActivityTrip(new TripOffer().newInstance(activityTrip));
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public TripOffer getTrip() {
		TripOffer trip = null;
		try {
			trip = super.getActivityTrip();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return trip;
	}

	public void setType(ActivityType activityType) {
		try {
			super.setActivityType(activityType.name());
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public ActivityType getType() {
		ActivityType type = null;
		try {
			type = IUserActivity.ActivityType.valueOf(super
					.getActivityTypeName());
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return type;
	}

	@Override
	public Timestamp getTimeStamp() {
		Timestamp timestamp = null;
		try {
			timestamp = super.getTimeStamp();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getMessage());
		}
		return timestamp;
	}

	@Override
	public void setTimeStamp(Timestamp timeStamp) {
		try {
			super.setTimeStamp(timeStamp);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getMessage());
		}
	}

	public UserActivityRef getRef() throws SQLException {
		UserActivityRef ref = new UserActivityRef();
		ref.setValue(this);
		return ref;
	}

	public void setRef(UserActivityRef ref) throws SQLException {
		ref.setValue(this);
	}
}
