package uclm.esi.cardroid.data.ice;

import java.sql.Timestamp;

import Ice.Current;
import Ice.Identity;
import Ice.ObjectAdapter;
import Ice.ObjectFactory;
import uclm.esi.cardroid.data.ITripOffer;
import uclm.esi.cardroid.data.IUser;
import uclm.esi.cardroid.data.IUserActivity;
import uclm.esi.cardroid.data.zerocice.TripOfferTypPrx;
import uclm.esi.cardroid.data.zerocice.UserActivityTyp;
import uclm.esi.cardroid.data.zerocice.UserActivityTypPrx;
import uclm.esi.cardroid.data.zerocice.UserActivityTypPrxHelper;
import uclm.esi.cardroid.data.zerocice.UserTypPrx;

/**
 * \class UserActivity
 * Domain class implementing an User Activity for its transmission between systems
 * communicating across an Ice network infrastructure
 */
public class UserActivity extends UserActivityTyp implements IUserActivity,
		ObjectFactory {
	private static ObjectAdapter _adapter;
	private static final long serialVersionUID = 4842929861773747077L;

	public UserActivity(ObjectAdapter adapter) {
		_adapter = adapter;
	}

	/**
	 * Default constructor
	 */
	public UserActivity(UserTypPrx activityUser, TripOfferTypPrx activityTrip,
			uclm.esi.cardroid.data.zerocice.ActivityType userActivityType,
			long timeStamp) {
		super(activityUser, activityTrip, userActivityType, timeStamp);
	}

	public UserActivity(UserActivityTyp userActivity) {
		this(userActivity.activityUser, userActivity.activityTrip,
				userActivity.userActivityType, userActivity.timeStamp);
	}

	/**
	 *  @return An Ice Identity for this datatype category and the data provided
	 */
	public static Identity createIdentity(String userEmail, int tripId,
			uclm.esi.cardroid.data.zerocice.ActivityType activityType,
			long timeStamp) {
		Identity id = new Identity();
		id.category = "user_activity";
		id.name = userEmail + "@" + tripId + "[" + activityType.name() + " "
				+ timeStamp + "]";
		return id;
	}

	/**
	 * @return A proxy to an Ice Object incarnating the provided data, whose 
	 * 			servant is added to adapter 's active servant map
	 */
	public static UserActivityTypPrx createProxy(
			UserActivityTyp userActivityObject, ObjectAdapter adapter) {
		UserActivityTypPrx userActivityPrx = UserActivityTypPrxHelper
				.uncheckedCast(adapter.createProxy(createIdentity(
						userActivityObject.getActivityUser().getEmail(),
						userActivityObject.getActivityTrip().getTripId(),
						userActivityObject.getUserActivityType(),
						userActivityObject.getTimeStampInMillis())));
		fillProxyData(userActivityObject, userActivityPrx, adapter);
		return userActivityPrx;
	}

	/**
	 * @return A proxy to an Ice Object incarnating the provided data, whose 
	 * 			servant is added to adapter 's active servant map
	 */
	public static UserActivityTypPrx createProxy(
			IUserActivity userActivityObject, ObjectAdapter adapter) {
		UserActivityTypPrx userActivityPrx = UserActivityTypPrxHelper
				.uncheckedCast(adapter.createProxy(createIdentity(
						userActivityObject.getUser().getEmail(),
						userActivityObject.getTrip().getTripId(),
						uclm.esi.cardroid.data.zerocice.ActivityType
								.valueOf(userActivityObject.getType().name()),
						userActivityObject.getTimeStamp().getTime())));
		fillProxyData(userActivityObject, userActivityPrx, adapter);
		return userActivityPrx;
	}

	/**
	 * Fill the servant referenced by the given proxy with the data contained 
	 * in the given object
	 */
	public static void fillProxyData(UserActivityTyp object,
			UserActivityTypPrx proxy, ObjectAdapter adapter) {
		proxy.setActivityUser(object.getActivityUser());
		proxy.setActivityTrip(object.getActivityTrip());
		proxy.setUserActivityType(object.getUserActivityType());
		proxy.setTimeStampInMillis(object.getTimeStampInMillis());
	}

	/**
	 * Fill the servant referenced by the given proxy with the data contained 
	 * in the given object
	 */
	public static void fillProxyData(IUserActivity object,
			UserActivityTypPrx proxy, ObjectAdapter adapter) {
		proxy.setActivityUser(User.createProxy(object.getUser(), adapter));
		proxy.setActivityTrip(TripOffer.createProxy(object.getTrip(), adapter));
		proxy.setUserActivityType(uclm.esi.cardroid.data.zerocice.ActivityType
				.valueOf(object.getType().name()));
		proxy.setTimeStampInMillis(object.getTimeStamp().getTime());
	}

	/* IUserActivity interface */

	public UserActivity newInstance(IUserActivity userActivityObject) {
		if (userActivityObject == null)
			return null;
		if (userActivityObject instanceof UserActivity)
			return (UserActivity) userActivityObject;

		UserTypPrx activityUsr = null;
		TripOfferTypPrx activityTrip = null;
		uclm.esi.cardroid.data.zerocice.ActivityType userActivityType = uclm.esi.cardroid.data.zerocice.ActivityType
				.valueOf(userActivityObject.getType().name());
		long timeStamp = userActivityObject.getTimeStamp().getTime();

		return new UserActivity(activityUsr, activityTrip, userActivityType,
				timeStamp);
	}

	public void setUser(IUser activityUser) {
		setActivityUser(User.createProxy(activityUser, _adapter));
	}

	public User getUser() {
		return User.extractObject(getActivityUser());
	}

	public void setTrip(ITripOffer activityTrip) {
		setActivityTrip(TripOffer.createProxy(activityTrip, _adapter));
	}

	public TripOffer getTrip() {
		return TripOffer.extractObject(getActivityTrip());
	}

	public void setType(ActivityType activityType) {
		setUserActivityType(uclm.esi.cardroid.data.zerocice.ActivityType
				.valueOf(activityType.name()));
	}

	public ActivityType getType() {
		return ActivityType.valueOf(getUserActivityType().name());
	}

	public Timestamp getTimeStamp() {
		return new Timestamp(getTimeStampInMillis());
	}

	public void setTimeStamp(Timestamp timeStamp) {
		setTimeStampInMillis(timeStamp.getTime());
	}

	/* Overriding superclass */

	@Override
	public UserTypPrx getActivityUser(Current __current) {
		return activityUser;
	}

	@Override
	public void setActivityUser(UserTypPrx activityUser, Current __current) {
		this.activityUser = activityUser;
	}

	@Override
	public TripOfferTypPrx getActivityTrip(Current __current) {
		return activityTrip;
	}

	@Override
	public void setActivityTrip(TripOfferTypPrx activityTrip, Current __current) {
		this.activityTrip = activityTrip;
	}

	@Override
	public uclm.esi.cardroid.data.zerocice.ActivityType getUserActivityType(
			Current __current) {
		return userActivityType;
	}

	@Override
	public void setUserActivityType(
			uclm.esi.cardroid.data.zerocice.ActivityType userActivityType,
			Current __current) {
		this.userActivityType = userActivityType;
	}

	@Override
	public long getTimeStampInMillis(Current __current) {
		return timeStamp;
	}

	@Override
	public void setTimeStampInMillis(long timeStampMillis, Current __current) {
		this.timeStamp = timeStampMillis;
	}

	@Override
	public String _toString(Current __current) {
		StringBuilder builder = new StringBuilder();
		builder.append(activityUser._toString());
		switch (userActivityType) {
		case TRIPACCEPT:
			builder.append(" ha aceptado tu inclusión en el viaje ");
			break;
		case TRIPJOIN:
			builder.append(" ha solicitado unirse a tu viaje ");
			break;
		case TRIPREFUSE:
			builder.append(" ha rechazado tu inclusión en el viaje ");
			break;
		case TRIPREQUESTANSWERED:
			builder.append(" ha respondido a tu solicitud del viaje ");
			break;
		}
		builder.append(activityTrip._toString());
		return builder.toString();
	}

	/* ObjectFactory interface */

	@Override
	public UserActivity create(String type) {
		if (type.equals(ice_staticId())) {
			return new UserActivity(_adapter);
		}

		return null;
	}

	@Override
	public void destroy() {
	}
}
