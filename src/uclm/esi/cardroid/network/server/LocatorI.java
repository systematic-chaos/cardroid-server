package uclm.esi.cardroid.network.server;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

import uclm.esi.cardroid.data.ice.Car;
import uclm.esi.cardroid.data.ice.Message;
import uclm.esi.cardroid.data.ice.Place;
import uclm.esi.cardroid.data.ice.Trip;
import uclm.esi.cardroid.data.ice.TripOffer;
import uclm.esi.cardroid.data.ice.TripRequest;
import uclm.esi.cardroid.data.ice.User;
import uclm.esi.cardroid.data.ice.UserActivity;
import Ice.Current;
import Ice.LocalObjectHolder;
import Ice.ObjectAdapter;
import Ice.ServantLocator;

/**
 * \class LocatorI
 * A servant locator is a local object that you implement and attach to an 
 * object adapter. Once an adapter has a servant locator, it consults its 
 * active servant map (ASM) to locate a servant for an incoming request as 
 * usual. If a servant for the request can be found in the ASM, the request 
 * is dispatched to that servant. However, if the ASM does not have an entry 
 * for the object identity of the request, the object adapter calls back into 
 * the servant locator to ask it to provide a servant for the request.
 */
public abstract class LocatorI implements ServantLocator {
	/// Data structure for keeping the servants located
	protected ConcurrentHashMap<String, DispatchInterceptorI> _servantMap;
	
	/// The ObjectAdapter this ServantLocator is attached to
	protected ObjectAdapter _adapter;

	/**
	 * The available object categories, for every one of which a 
	 * category-specific ServantLocator implementation is provided 
	 */
	public static String[] CATEGORIES = { "place", "car", "user", "trip",
			"trip_offer", "trip_request", "user_activity", "message", "" };

	/**
	 * Constructor for the allocation of those object categories which do not 
	 * require an ObjectAdapter
	 */
	public LocatorI() {
		_servantMap = new ConcurrentHashMap<String, DispatchInterceptorI>();
	}

	/**
	 * Constructor for the allocation of those object categories which do
	 * require an ObjectAdapter
	 * @param adapter The ObjectAdapter this ServantLocator is attached to. 
	 * 					It is used in the allocation process of categories 
	 * 					whose related objects may hold proxies to objects 
	 * 					that need to be also allocated
	 */
	public LocatorI(ObjectAdapter adapter) {
		this();
		_adapter = adapter;
	}

	/**
	 * Called by the object adapte after a request has been made.
	 * This operation is only called if locate was called prior to the request 
	 * and returned a non-null servant. This operation can be used for cleanup 
	 * purposes after a request.
	 * finished can throw any user exception. If it does, that exception is 
	 * marshaled back to the client. If the slice definition for the 
	 * corresponding operation includes that user exception, the client 
	 * receives that user exception; otherwise, the client receives 
	 * UnknownUserException. If both the operation and finished throw an 
	 * exception, the exception thrown by finished is marshaled back to the 
	 * client.
	 * @param c 		Information about the current operation call for which  
	 * 						a servant was located by locate
	 * @param servant	The servant that was returned by locate
	 * @param cookie	The cookie that was returned by locate
	 */
	public void finished(Current c, Ice.Object servant, Object cookie) {
		assert Arrays.asList(CATEGORIES).contains(c.id.category);
		//_servantMap.remove(c.id.name);
	}

	/**
	 * Called when the object adapter in which this servant locator is 
	 * installed is finished
	 * @param category Indicates for which category the servant locator is 
	 * 					being deactivated 
	 */
	public void deactivate(String category) {
	}
}

/**
 * \class PlaceLocatorI
 * Servant Locator for the "place" category
 */
class PlaceLocatorI extends LocatorI {

	/**
	 * Constructor for the allocation of those object categories which do not 
	 * require an ObjectAdapter
	 */
	public PlaceLocatorI() {
		super();
	}

	/**
	 * Called before a request is dispatched if a servant cannot be found in 
	 * the object adapter's active servant map. Note that the object adapter 
	 * does not automatically insert the returned servant into its active 
	 * servant map. This must be done by the servant locator implementation, 
	 * if this is desired.
	 * locate can throw any user exception. If it does, that exception is 
	 * marshaled back to the client. If the Slice definition for the 
	 * corresponding operation includes that user exception, the client 
	 * receives that user exception; otherwise, the cliente receives 
	 * UnknownUserException. If locate throws any exception, the Ice run time 
	 * does not call finished
	 * @param c 		Information about the current operation for which a 
	 * 						servant is required
	 * @param cookie	A "cookie" that will be passed to finished
	 * @return			The located servant, or null if no suitable servant 
	 * 						has been found
	 */
	public Ice.Object locate(Current c, LocalObjectHolder cookie) {
		assert Arrays.asList(CATEGORIES).contains(c.id.category);
		DispatchInterceptorI servant;
		if ((servant = _servantMap.get(c.id.name)) == null) {
			servant = new DispatchInterceptorI(new Place());
			_servantMap.put(c.id.name, servant);
		}
		return servant;
	}
}

/**
 * \class CarLocatorI
 * Servant Locator for the "car" category
 */
class CarLocatorI extends LocatorI {

	/**
	 * Constructor for the allocation of those object categories which do not 
	 * require an ObjectAdapter
	 */
	public CarLocatorI() {
		super();
	}

	/**
	 * Called before a request is dispatched if a servant cannot be found in 
	 * the object adapter's active servant map. Note that the object adapter 
	 * does not automatically insert the returned servant into its active 
	 * servant map. This must be done by the servant locator implementation, 
	 * if this is desired.
	 * locate can throw any user exception. If it does, that exception is 
	 * marshaled back to the client. If the Slice definition for the 
	 * corresponding operation includes that user exception, the client 
	 * receives that user exception; otherwise, the cliente receives 
	 * UnknownUserException. If locate throws any exception, the Ice run time 
	 * does not call finished
	 * @param c 		Information about the current operation for which a 
	 * 						servant is required
	 * @param cookie	A "cookie" that will be passed to finished
	 * @return			The located servant, or null if no suitable servant 
	 * 						has been found
	 */
	public Ice.Object locate(Current c, LocalObjectHolder cookie) {
		assert Arrays.asList(CATEGORIES).contains(c.id.category);
		DispatchInterceptorI servant;
		if ((servant = _servantMap.get(c.id.name)) == null) {
			servant = new DispatchInterceptorI(new Car());
			_servantMap.put(c.id.name, servant);
		}
		return servant;
	}
}

/**
 * \class UserLocatorI
 * Servant Locator for the "user" category
 */
class UserLocatorI extends LocatorI {

	/**
	 * Constructor for the allocation of those object categories which do
	 * require an ObjectAdapter
	 * @param adapter The ObjectAdapter this ServantLocator is attached to. 
	 * 					It is used in the allocation process of categories 
	 * 					whose related objects may hold proxies to objects 
	 * 					that need to be also allocated
	 */
	public UserLocatorI(ObjectAdapter adapter) {
		super(adapter);
	}

	/**
	 * Called before a request is dispatched if a servant cannot be found in 
	 * the object adapter's active servant map. Note that the object adapter 
	 * does not automatically insert the returned servant into its active 
	 * servant map. This must be done by the servant locator implementation, 
	 * if this is desired.
	 * locate can throw any user exception. If it does, that exception is 
	 * marshaled back to the client. If the Slice definition for the 
	 * corresponding operation includes that user exception, the client 
	 * receives that user exception; otherwise, the cliente receives 
	 * UnknownUserException. If locate throws any exception, the Ice run time 
	 * does not call finished
	 * @param c 		Information about the current operation for which a 
	 * 						servant is required
	 * @param cookie	A "cookie" that will be passed to finished
	 * @return			The located servant, or null if no suitable servant 
	 * 						has been found
	 */
	public Ice.Object locate(Current c, LocalObjectHolder cookie) {
		assert Arrays.asList(CATEGORIES).contains(c.id.category);
		DispatchInterceptorI servant;
		if ((servant = _servantMap.get(c.id.name)) == null) {
			servant = new DispatchInterceptorI(new User(_adapter));
			_servantMap.put(c.id.name, servant);
		}
		return servant;
	}
}

/**
 * \class TripLocatorI
 * Servant Locator for the "trip" category
 */
class TripLocatorI extends LocatorI {

	/**
	 * Constructor for the allocation of those object categories which do not 
	 * require an ObjectAdapter
	 */
	public TripLocatorI() {
		super();
	}

	/**
	 * Called before a request is dispatched if a servant cannot be found in 
	 * the object adapter's active servant map. Note that the object adapter 
	 * does not automatically insert the returned servant into its active 
	 * servant map. This must be done by the servant locator implementation, 
	 * if this is desired.
	 * locate can throw any user exception. If it does, that exception is 
	 * marshaled back to the client. If the Slice definition for the 
	 * corresponding operation includes that user exception, the client 
	 * receives that user exception; otherwise, the cliente receives 
	 * UnknownUserException. If locate throws any exception, the Ice run time 
	 * does not call finished
	 * @param c 		Information about the current operation for which a 
	 * 						servant is required
	 * @param cookie	A "cookie" that will be passed to finished
	 * @return			The located servant, or null if no suitable servant 
	 * 						has been found
	 */
	public Ice.Object locate(Current c, LocalObjectHolder cookie) {
		assert Arrays.asList(CATEGORIES).contains(c.id.category);
		DispatchInterceptorI servant;
		if ((servant = _servantMap.get(c.id.name)) == null) {
			servant = new DispatchInterceptorI(new Trip());
			_servantMap.put(c.id.name, servant);
		}
		return servant;
	}
}

/**
 * \class TripOfferLocatorI
 * Servant Locator for the "trip_offer" category
 */
class TripOfferLocatorI extends LocatorI {

	/**
	 * Constructor for the allocation of those object categories which do
	 * require an ObjectAdapter
	 * @param adapter The ObjectAdapter this ServantLocator is attached to. 
	 * 					It is used in the allocation process of categories 
	 * 					whose related objects may hold proxies to objects 
	 * 					that need to be also allocated
	 */
	public TripOfferLocatorI(ObjectAdapter adapter) {
		super(adapter);
	}

	/**
	 * Called before a request is dispatched if a servant cannot be found in 
	 * the object adapter's active servant map. Note that the object adapter 
	 * does not automatically insert the returned servant into its active 
	 * servant map. This must be done by the servant locator implementation, 
	 * if this is desired.
	 * locate can throw any user exception. If it does, that exception is 
	 * marshaled back to the client. If the Slice definition for the 
	 * corresponding operation includes that user exception, the client 
	 * receives that user exception; otherwise, the cliente receives 
	 * UnknownUserException. If locate throws any exception, the Ice run time 
	 * does not call finished
	 * @param c 		Information about the current operation for which a 
	 * 						servant is required
	 * @param cookie	A "cookie" that will be passed to finished
	 * @return			The located servant, or null if no suitable servant 
	 * 						has been found
	 */
	public Ice.Object locate(Current c, LocalObjectHolder cookie) {
		assert Arrays.asList(CATEGORIES).contains(c.id.category);
		DispatchInterceptorI servant;
		if ((servant = _servantMap.get(c.id.name)) == null) {
			servant = new DispatchInterceptorI(new TripOffer(_adapter));
			_servantMap.put(c.id.name, servant);
		}
		return servant;
	}
}

/**
 * \class TripRequestLocatorI
 * Servant Locator for the "trip_request" category
 */
class TripRequestLocatorI extends LocatorI {

	/**
	 * Constructor for the allocation of those object categories which do
	 * require an ObjectAdapter
	 * @param adapter The ObjectAdapter this ServantLocator is attached to. 
	 * 					It is used in the allocation process of categories 
	 * 					whose related objects may hold proxies to objects 
	 * 					that need to be also allocated
	 */
	public TripRequestLocatorI(ObjectAdapter adapter) {
		super(adapter);
	}

	/**
	 * Called before a request is dispatched if a servant cannot be found in 
	 * the object adapter's active servant map. Note that the object adapter 
	 * does not automatically insert the returned servant into its active 
	 * servant map. This must be done by the servant locator implementation, 
	 * if this is desired.
	 * locate can throw any user exception. If it does, that exception is 
	 * marshaled back to the client. If the Slice definition for the 
	 * corresponding operation includes that user exception, the client 
	 * receives that user exception; otherwise, the cliente receives 
	 * UnknownUserException. If locate throws any exception, the Ice run time 
	 * does not call finished
	 * @param c 		Information about the current operation for which a 
	 * 						servant is required
	 * @param cookie	A "cookie" that will be passed to finished
	 * @return			The located servant, or null if no suitable servant 
	 * 						has been found
	 */
	public Ice.Object locate(Current c, LocalObjectHolder cookie) {
		assert Arrays.asList(CATEGORIES).contains(c.id.category);
		DispatchInterceptorI servant;
		if ((servant = _servantMap.get(c.id.name)) == null) {
			servant = new DispatchInterceptorI(new TripRequest(_adapter));
			_servantMap.put(c.id.name, servant);
		}
		return servant;
	}
}

/**
 * \class UserActivityLocatorI
 * Servant Locator for the "user_activity" category
 */
class UserActivityLocatorI extends LocatorI {

	/**
	 * Constructor for the allocation of those object categories which do
	 * require an ObjectAdapter
	 * @param adapter The ObjectAdapter this ServantLocator is attached to. 
	 * 					It is used in the allocation process of categories 
	 * 					whose related objects may hold proxies to objects 
	 * 					that need to be also allocated
	 */
	public UserActivityLocatorI(ObjectAdapter adapter) {
		super(adapter);
	}

	/**
	 * Called before a request is dispatched if a servant cannot be found in 
	 * the object adapter's active servant map. Note that the object adapter 
	 * does not automatically insert the returned servant into its active 
	 * servant map. This must be done by the servant locator implementation, 
	 * if this is desired.
	 * locate can throw any user exception. If it does, that exception is 
	 * marshaled back to the client. If the Slice definition for the 
	 * corresponding operation includes that user exception, the client 
	 * receives that user exception; otherwise, the cliente receives 
	 * UnknownUserException. If locate throws any exception, the Ice run time 
	 * does not call finished
	 * @param c 		Information about the current operation for which a 
	 * 						servant is required
	 * @param cookie	A "cookie" that will be passed to finished
	 * @return			The located servant, or null if no suitable servant 
	 * 						has been found
	 */
	public Ice.Object locate(Current c, LocalObjectHolder cookie) {
		assert Arrays.asList(CATEGORIES).contains(c.id.category);
		DispatchInterceptorI servant;
		if ((servant = _servantMap.get(c.id.name)) == null) {
			servant = new DispatchInterceptorI(new UserActivity(_adapter));
			_servantMap.put(c.id.name, servant);
		}
		return servant;
	}
}

/**
 * \class MessageLocatorI
 * Servant Locator for the "message" category
 */
class MessageLocatorI extends LocatorI {

	/**
	 * Constructor for the allocation of those object categories which do
	 * require an ObjectAdapter
	 * @param adapter The ObjectAdapter this ServantLocator is attached to. 
	 * 					It is used in the allocation process of categories 
	 * 					whose related objects may hold proxies to objects 
	 * 					that need to be also allocated
	 */
	public MessageLocatorI(ObjectAdapter adapter) {
		super(adapter);
	}

	/**
	 * Called before a request is dispatched if a servant cannot be found in 
	 * the object adapter's active servant map. Note that the object adapter 
	 * does not automatically insert the returned servant into its active 
	 * servant map. This must be done by the servant locator implementation, 
	 * if this is desired.
	 * locate can throw any user exception. If it does, that exception is 
	 * marshaled back to the client. If the Slice definition for the 
	 * corresponding operation includes that user exception, the client 
	 * receives that user exception; otherwise, the cliente receives 
	 * UnknownUserException. If locate throws any exception, the Ice run time 
	 * does not call finished
	 * @param c 		Information about the current operation for which a 
	 * 						servant is required
	 * @param cookie	A "cookie" that will be passed to finished
	 * @return			The located servant, or null if no suitable servant 
	 * 						has been found
	 */
	public Ice.Object locate(Current c, LocalObjectHolder cookie) {
		assert Arrays.asList(CATEGORIES).contains(c.id.category);
		DispatchInterceptorI servant;
		if ((servant = _servantMap.get(c.id.name)) == null) {
			servant = new DispatchInterceptorI(new Message(_adapter));
			_servantMap.put(c.id.name, servant);
		}
		return servant;
	}
}
