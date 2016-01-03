package uclm.esi.cardroid.network.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import uclm.esi.cardroid._QueryResultDisp;

import Ice.BooleanHolder;
import Ice.Current;
import Ice.ObjectAdapter;
import Ice.ObjectNotExistException;
import Ice.ObjectPrx;

/**
 * \class QueryResultI
 * Class to manage query results
 */
public class QueryResultI extends _QueryResultDisp {
	private Stack<ObjectPrx> _data;
	private boolean _destroyed = false;
	private static final int MAX_ENTRY_QUERY_RESULT = 1000;
	private static final long serialVersionUID = -8433958483272712676L;

	/**
	 * Default constructor
	 * @param context	The context for the query that originated this result 
	 * 						and the result itself
	 * @param rs		Result set containing the query result
	 * @param adapter	Object adapter needed to translate object 
	 * 						implementations and incarnate them in Ice servants
	 * @param queryType	The type of the query that originated this result
	 * @throws SQLException Raised when an error happened in the retrieval of 
	 * 						data from this query result
	 */
	public QueryResultI(SQLRequestContext context, ResultSet rs,
			ObjectAdapter adapter, QueryType queryType) throws SQLException {
		// Cache the first entries of the query result
		_data = new Stack<ObjectPrx>();
		for (int i = 0; i < MAX_ENTRY_QUERY_RESULT && rs.next(); ++i) {
			switch (queryType) {
			case GET_MESSAGE_TALKS:
				uclm.esi.cardroid.data.oracle.Message m = rs.getObject(1,
						uclm.esi.cardroid.data.oracle.Message.class);
				_data.add(uclm.esi.cardroid.data.ice.Message.createProxy(m,
						adapter));
				break;
			case GET_MESSAGE_TALKS_SPEAKERS:
				uclm.esi.cardroid.data.oracle.User u = rs.getObject(1,
						uclm.esi.cardroid.data.oracle.User.class);
				_data.add(uclm.esi.cardroid.data.ice.User.createProxy(u,
						adapter));
				break;
			case GET_PASSENGER_TRIPS:
				uclm.esi.cardroid.data.oracle.TripOffer to = rs.getObject(1,
						uclm.esi.cardroid.data.oracle.TripOffer.class);
				_data.add(uclm.esi.cardroid.data.ice.TripOffer.createProxy(to,
						adapter));
				break;
			case GET_USER_ACTIVITY:
				uclm.esi.cardroid.data.oracle.UserActivity ua = rs.getObject(1,
						uclm.esi.cardroid.data.oracle.UserActivity.class);
				_data.add(uclm.esi.cardroid.data.ice.UserActivity.createProxy(
						ua, adapter));
				break;
			case GET_USER_PLACES:
				uclm.esi.cardroid.data.oracle.Place p = rs.getObject(1,
						uclm.esi.cardroid.data.oracle.Place.class);
				_data.add(uclm.esi.cardroid.data.ice.Place.createProxy(p,
						adapter));
				break;
			case GET_USER_TRIPS:
				uclm.esi.cardroid.data.oracle.Trip gut = rs.getObject(1,
						uclm.esi.cardroid.data.oracle.Trip.class);
				_data.add(uclm.esi.cardroid.data.ice.Trip.createProxy(gut,
						adapter));
				break;
			case SEARCH_TRIPS:
				uclm.esi.cardroid.data.oracle.Trip st = rs.getObject(1,
						uclm.esi.cardroid.data.oracle.Trip.class);
				_data.add(uclm.esi.cardroid.data.ice.Trip.createProxy(st,
						adapter));
				break;
			}
		}
	}

	public synchronized List<ObjectPrx> next(int n, BooleanHolder destroyed,
			Current __current) {
		if (_destroyed) {
			throw new ObjectNotExistException();
		}
		destroyed.value = false;
		List<ObjectPrx> l = new LinkedList<ObjectPrx>();
		if (n <= 0) {
			return l;
		}

		for (int i = 0; i < n && _data.size() > 0; ++i) {
			l.add(_data.pop());
		}

		if (_data.size() <= 0) {
			try {
				destroyed.value = true;
				destroy(__current);
			} catch (Exception e) {
				// Ignore
			}
		}

		return l;
	}

	public synchronized void destroy(Current __current) {
		if (_destroyed) {
			throw new ObjectNotExistException();
		}
		_destroyed = true;

		__current.adapter.remove(__current.id);
	}

	/**
	 * Called on application shutdown by the Cardroid Manager
	 */
	public synchronized void shutdown() {
		if (!_destroyed) {
			_destroyed = true;
		}
	}
}
