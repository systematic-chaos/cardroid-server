package uclm.esi.cardroid.network.server;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import uclm.esi.cardroid.QueryResultPrx;
import uclm.esi.cardroid.QueryResultPrxHolder;
import uclm.esi.cardroid.ResultSeqHolder;
import uclm.esi.cardroid.data.zerocice.CarTyp;
import uclm.esi.cardroid.data.zerocice.CarTypPrx;
import uclm.esi.cardroid.data.zerocice.Fuel;
import uclm.esi.cardroid.data.zerocice.MessageTypPrx;
import uclm.esi.cardroid.data.zerocice.TripOfferTyp;
import uclm.esi.cardroid.data.zerocice.TripOfferTypPrx;
import uclm.esi.cardroid.data.zerocice.TripRequestTyp;
import uclm.esi.cardroid.data.zerocice.TripRequestTypPrx;
import uclm.esi.cardroid.data.zerocice.TripTypPrx;
import uclm.esi.cardroid.data.zerocice.UserTyp;
import uclm.esi.cardroid.data.zerocice.UserTypPrx;
import uclm.esi.cardroid.network.server.SQLRequestContext.QueryResultListener;
import uclm.esi.cardroid.zerocice._CardroidManagerDisp;

import Ice.Current;
import Ice.IntHolder;
import Ice.ObjectNotExistException;

/**
 * \class CardroidManagerI
 * This is a per-session CarDroid manager object servant
 */
public class CardroidManagerI extends _CardroidManagerDisp implements
		QueryResultListener {
	private List<QueryProxyPair> _queries = new LinkedList<QueryProxyPair>();
	private boolean _destroyed = false;

	private static final long serialVersionUID = 1290124593860850175L;

	/**
	 * Default constructor
	 */
	public CardroidManagerI() {
	}

	/**
	 * Execute the "getUserPlaces" query in the database
	 * @param n			The maximum number of rows to be fetched
	 * @param first		Holder for the result of the query to be performed
	 * @param nrows		The number of rows already fetched
	 * @param result	Holder for the rest of the result (not yet fetched)
	 * 						of the query to be performed
	 * @param current	Information about the current method invocation for 
	 * 						servers. Each operation on the server has a 
	 * 						Current as its implicit final parameter. Current 
	 * 						is mostly used for Ice services. Most applications 
	 * 						ignore this parameter.
	 */
	public void getUserPlaces(UserTypPrx usr, int n, ResultSeqHolder first,
			IntHolder nrows, QueryResultPrxHolder result, Current current) {
		SQLRequestContext context = SQLRequestContext.getCurrentContext();
		assert context != null;
		context.setQueryResultListener(this);

		reapQueries();

		context.getUserPlaces(usr, n, first, nrows, result, current);
	}

	/**
	 * Execute the "searchTrips" query in the database
	 * @param n			The maximum number of rows to be fetched
	 * @param first		Holder for the result of the query to be performed
	 * @param nrows		The number of rows already fetched
	 * @param result	Holder for the rest of the result (not yet fetched)
	 * 						of the query to be performed
	 * @param current	Information about the current method invocation for 
	 * 						servers. Each operation on the server has a 
	 * 						Current as its implicit final parameter. Current 
	 * 						is mostly used for Ice services. Most applications 
	 * 						ignore this parameter.
	 */
	public void searchTrips(TripRequestTyp tRequest, int n,
			ResultSeqHolder first, IntHolder nrows,
			QueryResultPrxHolder result, Current current) {
		SQLRequestContext context = SQLRequestContext.getCurrentContext();
		assert context != null;
		context.setQueryResultListener(this);

		reapQueries();

		context.searchTrips(tRequest, n, first, nrows, result, current);
	}

	/**
	 * Execute the "getTripFromId" operation in the database
	 * @return The result of the operation, if any
	 */
	public TripTypPrx getTripFromId(int tripId, Current current) {
		SQLRequestContext context = SQLRequestContext.getCurrentContext();
		assert context != null;
		context.setQueryResultListener(this);

		reapQueries();

		return context.getTripFromId(tripId, current);
	}

	/**
	 * Execute the "getTripOfferFromId" operation in the database
	 * @return The result of the operation, if any
	 */
	public TripOfferTypPrx getTripOfferFromId(int tripId, Current current) {
		SQLRequestContext context = SQLRequestContext.getCurrentContext();
		assert context != null;
		context.setQueryResultListener(this);

		reapQueries();

		return context.getTripOfferFromId(tripId, current);
	}

	/**
	 * Execute the "getTripRequestFromId" operation in the database
	 * @return The result of the operation, if any
	 */
	public TripRequestTypPrx getTripRequestFromId(int tripId, Current current) {
		SQLRequestContext context = SQLRequestContext.getCurrentContext();
		assert context != null;
		context.setQueryResultListener(this);

		reapQueries();

		return context.getTripRequestFromId(tripId, current);
	}

	/**
	 * Execute the "joinTrip" operation in the database
	 * @return The result of the operation, if any
	 */
	public void joinTrip(TripOfferTypPrx trip, UserTypPrx passenger,
			int nSeats, Current current) {
		SQLRequestContext context = SQLRequestContext.getCurrentContext();
		assert context != null;
		context.setQueryResultListener(this);

		reapQueries();
		context.joinTrip(trip, passenger, nSeats, current);
	}

	/**
	 * Execute the "organizeTrip" operation in the database
	 * @return The result of the operation, if any
	 */
	public TripOfferTypPrx organizeTrip(TripRequestTypPrx tripRequest,
			TripOfferTyp tripOffer, Current current) {
		SQLRequestContext context = SQLRequestContext.getCurrentContext();
		assert context != null;
		context.setQueryResultListener(this);

		reapQueries();
		return context.organizeTrip(tripRequest, tripOffer, current);
	}

	/**
	 * Execute the "getUserTrips" query in the database
	 * @param n			The maximum number of rows to be fetched
	 * @param first		Holder for the result of the query to be performed
	 * @param nrows		The number of rows already fetched
	 * @param result	Holder for the rest of the result (not yet fetched)
	 * 						of the query to be performed
	 * @param current	Information about the current method invocation for 
	 * 						servers. Each operation on the server has a 
	 * 						Current as its implicit final parameter. Current 
	 * 						is mostly used for Ice services. Most applications 
	 * 						ignore this parameter.
	 */
	public void getUserTrips(UserTypPrx usr, int n, ResultSeqHolder first,
			IntHolder nrows, QueryResultPrxHolder result, Current current) {
		SQLRequestContext context = SQLRequestContext.getCurrentContext();
		assert context != null;
		context.setQueryResultListener(this);

		reapQueries();

		context.getUserTrips(usr, n, first, nrows, result, current);
	}

	/**
	 * Execute the "getPassengerTrips" query in the database
	 * @param n			The maximum number of rows to be fetched
	 * @param first		Holder for the result of the query to be performed
	 * @param nrows		The number of rows already fetched
	 * @param result	Holder for the rest of the result (not yet fetched)
	 * 						of the query to be performed
	 * @param current	Information about the current method invocation for 
	 * 						servers. Each operation on the server has a 
	 * 						Current as its implicit final parameter. Current 
	 * 						is mostly used for Ice services. Most applications 
	 * 						ignore this parameter.
	 */
	public void getPassengerTrips(UserTypPrx passenger, int n,
			ResultSeqHolder first, IntHolder nrows,
			QueryResultPrxHolder result, Current current) {
		SQLRequestContext context = SQLRequestContext.getCurrentContext();
		assert context != null;
		context.setQueryResultListener(this);

		reapQueries();

		context.getPassengerTrips(passenger, n, first, nrows, result, current);
	}

	/**
	 * Execute the "calculatePriceEstimation" operation in the database
	 * @return The result of the operation, if any
	 */
	public double calculatePriceEstimation(Fuel f, int distance, Current current) {
		SQLRequestContext context = SQLRequestContext.getCurrentContext();
		assert context != null;
		context.setQueryResultListener(this);

		reapQueries();

		return context.calculatePriceEstimation(f, distance, current);
	}

	/**
	 * Execute the "newTripOffer" operation in the database
	 * @return The result of the operation, if any
	 */
	public TripOfferTypPrx newTripOffer(TripOfferTyp tOffer, Current current) {
		SQLRequestContext context = SQLRequestContext.getCurrentContext();
		assert context != null;
		context.setQueryResultListener(this);

		reapQueries();

		return context.newTripOffer(tOffer, current);
	}

	/**
	 * Execute the "newTripRequest" operation in the database
	 * @return The result of the operation, if any
	 */
	public TripRequestTypPrx newTripRequest(TripRequestTyp tRequest,
			Current current) {
		SQLRequestContext context = SQLRequestContext.getCurrentContext();
		assert context != null;
		context.setQueryResultListener(this);

		reapQueries();

		return context.newTripRequest(tRequest, current);
	}

	/**
	 * Execute the "getMessageTalksSpeakers" query in the database
	 * @param n			The maximum number of rows to be fetched
	 * @param first		Holder for the result of the query to be performed
	 * @param nrows		The number of rows already fetched
	 * @param result	Holder for the rest of the result (not yet fetched)
	 * 						of the query to be performed
	 * @param current	Information about the current method invocation for 
	 * 						servers. Each operation on the server has a 
	 * 						Current as its implicit final parameter. Current 
	 * 						is mostly used for Ice services. Most applications 
	 * 						ignore this parameter.
	 */
	public void getMessageTalksSpeakers(UserTypPrx usr, int n,
			ResultSeqHolder first, IntHolder nrows,
			QueryResultPrxHolder result, Current current) {
		SQLRequestContext context = SQLRequestContext.getCurrentContext();
		assert context != null;
		context.setQueryResultListener(this);

		reapQueries();

		context.getMessageTalksSpeakers(usr, n, first, nrows, result, current);
	}

	/**
	 * Execute the "getMessageTalks" query in the database
	 * @param n			The maximum number of rows to be fetched
	 * @param first		Holder for the result of the query to be performed
	 * @param nrows		The number of rows already fetched
	 * @param result	Holder for the rest of the result (not yet fetched)
	 * 						of the query to be performed
	 * @param current	Information about the current method invocation for 
	 * 						servers. Each operation on the server has a 
	 * 						Current as its implicit final parameter. Current 
	 * 						is mostly used for Ice services. Most applications 
	 * 						ignore this parameter.
	 */
	public void getMessageTalks(UserTypPrx usr1, UserTypPrx usr2, int n,
			ResultSeqHolder first, IntHolder nrows,
			QueryResultPrxHolder result, Current current) {
		SQLRequestContext context = SQLRequestContext.getCurrentContext();
		assert context != null;
		context.setQueryResultListener(this);

		reapQueries();

		context.getMessageTalks(usr1, usr2, n, first, nrows, result, current);
	}

	/**
	 * Execute the "newMessage" operation in the database
	 * @return The result of the operation, if any
	 */
	public MessageTypPrx newMessage(UserTypPrx usr1, UserTypPrx usr2,
			String message, Current current) {
		SQLRequestContext context = SQLRequestContext.getCurrentContext();
		assert context != null;
		context.setQueryResultListener(this);

		reapQueries();

		return context.newMessage(usr1, usr2, message, current);
	}

	/**
	 * Execute the "getUserActivity" query in the database
	 * @param n			The maximum number of rows to be fetched
	 * @param first		Holder for the result of the query to be performed
	 * @param nrows		The number of rows already fetched
	 * @param result	Holder for the rest of the result (not yet fetched)
	 * 						of the query to be performed
	 * @param current	Information about the current method invocation for 
	 * 						servers. Each operation on the server has a 
	 * 						Current as its implicit final parameter. Current 
	 * 						is mostly used for Ice services. Most applications 
	 * 						ignore this parameter.
	 */
	public void getUserActivity(UserTypPrx usr, int n, ResultSeqHolder first,
			IntHolder nrows, QueryResultPrxHolder result, Current current) {
		SQLRequestContext context = SQLRequestContext.getCurrentContext();
		assert context != null;
		context.setQueryResultListener(this);

		reapQueries();

		context.getUserActivity(usr, n, first, nrows, result, current);
	}

	/**
	 * Execute the "getUserFromEmail" operation in the database
	 * @return The result of the operation, if any
	 */
	public UserTypPrx getUserFromEmail(String email, Current current) {
		SQLRequestContext context = SQLRequestContext.getCurrentContext();
		assert context != null;
		context.setQueryResultListener(this);

		reapQueries();

		return context.getUserFromEmail(email, current);
	}

	/**
	 * Execute the "getCarFromPlate" operation in the database
	 * @return The result of the operation, if any
	 */
	public CarTypPrx getCarFromPlate(String plate, UserTypPrx owner,
			Current current) {
		SQLRequestContext context = SQLRequestContext.getCurrentContext();
		assert context != null;
		context.setQueryResultListener(this);

		reapQueries();

		return context.getCarFromPlate(plate, owner, current);
	}

	/**
	 * Execute the "getCarFromPlateEmail" operation in the database
	 * @return The result of the operation, if any
	 */
	public CarTypPrx getCarFromPlateEmail(String plate, String ownerEmail,
			Current current) {
		SQLRequestContext context = SQLRequestContext.getCurrentContext();
		assert context != null;
		context.setQueryResultListener(this);

		reapQueries();

		return context.getCarFromPlateEmail(plate, ownerEmail, current);
	}

	/**
	 * Execute the "updateUserData" operation in the database
	 * @return The result of the operation, if any
	 */
	public UserTypPrx updateUserData(UserTyp usr, Current current) {
		SQLRequestContext context = SQLRequestContext.getCurrentContext();
		assert context != null;
		context.setQueryResultListener(this);

		reapQueries();

		return context.updateUserData(usr, current);
	}

	/**
	 * Execute the "updateCarData" operation in the database
	 * @return The result of the operation, if any
	 */
	public CarTypPrx updateCarData(CarTyp car, UserTyp usr, Current current) {
		SQLRequestContext context = SQLRequestContext.getCurrentContext();
		assert context != null;
		context.setQueryResultListener(this);

		reapQueries();

		return context.updateCarData(car, usr, current);
	}

	/**
	 * Execute the "updateCarDataEmail" operation in the database
	 * @return The result of the operation, if any
	 */
	public CarTypPrx updateCarDataEmail(CarTyp car, String usrEmail,
			Current current) {
		SQLRequestContext context = SQLRequestContext.getCurrentContext();
		assert context != null;
		context.setQueryResultListener(this);

		reapQueries();

		return context.updateCarDataEmail(car, usrEmail, current);
	}

	/**
	 * Execute the "addCar" operation in the database
	 * @return The result of the operation, if any
	 */
	public CarTypPrx addCar(CarTyp car, UserTypPrx usr, Current current) {
		SQLRequestContext context = SQLRequestContext.getCurrentContext();
		assert context != null;
		context.setQueryResultListener(this);

		reapQueries();

		return context.addCar(car, usr, current);
	}

	/**
	 * Execute the "addCarEmail" operation in the database
	 * @return The result of the operation, if any
	 */
	public CarTypPrx addCarEmail(CarTyp car, String usrEmail, Current current) {
		SQLRequestContext context = SQLRequestContext.getCurrentContext();
		assert context != null;
		context.setQueryResultListener(this);

		reapQueries();

		return context.addCarEmail(car, usrEmail, current);
	}

	/**
	 * Execute the "removeCar" operation in the database
	 * @return The result of the operation, if any
	 */
	public void removeCar(CarTypPrx car, UserTypPrx usr, Current current) {
		SQLRequestContext context = SQLRequestContext.getCurrentContext();
		assert context != null;
		context.setQueryResultListener(this);

		reapQueries();

		context.removeCar(car, usr, current);
	}

	/**
	 * Execute the "removeCarPlateEmail" operation in the database
	 * @return The result of the operation, if any
	 */
	public void removeCarPlateEmail(String plate, String ownerEmail,
			Current current) {
		SQLRequestContext context = SQLRequestContext.getCurrentContext();
		assert context != null;
		context.setQueryResultListener(this);

		reapQueries();

		context.removeCarPlateEmail(plate, ownerEmail, current);
	}

	/**
	 * Execute the "userTripRegistered" operation in the database
	 * @return The result of the operation, if any
	 */
	public boolean userTripRegistered(UserTypPrx usr, TripTypPrx trip,
			Current current) {
		SQLRequestContext context = SQLRequestContext.getCurrentContext();
		assert context != null;
		context.setQueryResultListener(this);

		reapQueries();

		return context.userTripRegistered(usr, trip, current);
	}

	/**
	 * Destroy this CardroidManager and the query result objects it currently 
	 * holds
	 */
	public synchronized void destroy() {
		if (_destroyed) {
			return;
		}
		_destroyed = true;
		for (QueryProxyPair p : _queries) {
			try {
				p.proxy.destroy();
			} catch (ObjectNotExistException e) {
				// Ignore, it could have already been destroyed
			}
		}
	}

	/**
	 * Shutdown this CardroidManager and the query result objects it currently 
	 * holds
	 */
	public synchronized void shutdown() {
		if (_destroyed) {
			return;
		}
		_destroyed = true;

		// Shutdown each of the associated query objects
		for (QueryProxyPair p : _queries) {
			p.impl.shutdown();
		}
	}

	/**
	 * Add a new query result to the list of query result objects currently 
	 * kept by this CardroidManager
	 * @param proxy		Proxy to the query result to add
	 * @param impl		Servant of the query result to add
	 * @param queryType	The type of the query which originated the result to add
	 */
	public synchronized void add(QueryResultPrx proxy, QueryResultI impl,
			QueryType queryType) {
		/* If the session has been destroyed, then destroy the query result, *
		 * and raise an ObjectNotExistException								 */
		if (_destroyed) {
			proxy.destroy();
			throw new ObjectNotExistException();
		}
		_queries.add(new QueryProxyPair(proxy, impl));
	}

	/**
	 * Remove the context of the queries held by this CardroidManager which 
	 * have already been destroyed
	 */
	private synchronized void reapQueries() {
		if (_destroyed) {
			throw new ObjectNotExistException();
		}

		Iterator<QueryProxyPair> p = _queries.iterator();
		while (p.hasNext()) {
			QueryProxyPair pair = p.next();
			try {
				pair.proxy.ice_ping();
			} catch (ObjectNotExistException e) {
				p.remove();
			}
		}
	}

	/**
	 * \class QueryProxyPair
	 * Class containing a proxy to a query result and the servant it references
	 */
	static class QueryProxyPair {
		public QueryResultPrx proxy;
		public QueryResultI impl;

		public QueryProxyPair(QueryResultPrx p, QueryResultI i) {
			proxy = p;
			impl = i;
		}
	}
}
