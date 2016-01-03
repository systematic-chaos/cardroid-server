package uclm.esi.cardroid.data.oracle;

import java.sql.Blob;
import java.sql.SQLException;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;

import uclm.esi.cardroid.data.ILatLng;
import uclm.esi.cardroid.data.IPlace;
import uclm.esi.cardroid.data.IWaypoint;

/**
 * \class TripOffer_Waypoint
 * Domain class implementing a Waypoint for its storage and retrieval  
 * from an Oracle database
 */
public class TripOffer_Waypoint extends TripOffer_WaypointTyp implements
		ORAData, ORADataFactory, IWaypoint {
	private static final TripOffer_Waypoint _WaypointFactory = new TripOffer_Waypoint();

	public static ORADataFactory getORADataFactory() {
		return _WaypointFactory;
	}

	public TripOffer_Waypoint() {
		super();
	}

	/**
	 * Default constructor
	 */
	public TripOffer_Waypoint(int nOrder, Place place) {
		setNOrder(nOrder);
		setPlaceWaypoint(place);
	}

	/* ORAData interface */

	public ORAData create(Datum d, int sqlType) throws SQLException {
		return create(new TripOffer_Waypoint(), d, sqlType);
	}

	/* IPlace interface */

	public Place newInstance(IPlace placeObject) {
		return new Place().newInstance(placeObject);
	}

	public TripOffer_Waypoint newInstance(IWaypoint waypointObject) {
		if (waypointObject == null)
			return null;
		if (waypointObject instanceof TripOffer_Waypoint)
			return (TripOffer_Waypoint) waypointObject;

		TripOffer_Waypoint waypoint = new TripOffer_Waypoint(
				waypointObject.getNOrder(),
				newInstance(waypointObject.getPlaceWaypoint()));

		return waypoint;
	}

	public void setNOrder(int nOrder) {
		try {
			super.setNOrder(nOrder);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public int getNOrder() {
		int nOrder = 0;
		try {
			nOrder = super.getNOrder();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return nOrder;
	}

	public void setPlaceWaypoint(IPlace placeWaypoint) {
		try {
			super.setPlace(newInstance(placeWaypoint));
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public Place getPlaceWaypoint() {
		Place place = null;
		try {
			place = super.getPlace();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return place;
	}

	public void setName(String name) {
		getPlaceWaypoint().setName(name);
	}

	public String getName() {
		return getPlaceWaypoint().getName();
	}

	public void setCoordinates(ILatLng coords) {
		getPlaceWaypoint().setCoordinates(coords);
	}

	public ILatLng getCoordinates() {
		return getPlaceWaypoint().getCoordinates();
	}

	public void setDescription(String description) {
		getPlaceWaypoint().setDescription(description);
	}

	public String getDescription() {
		return getPlaceWaypoint().getDescription();
	}

	public boolean hasDescription() {
		return getPlaceWaypoint().hasDescription();
	}

	public void setSnapshot(Blob bitmapObject) {
		getPlaceWaypoint().setSnapshot(bitmapObject);
	}

	public Blob getSnapshot() {
		return getPlaceWaypoint().getSnapshot();
	}

	public boolean hasSnapshot() {
		return getPlaceWaypoint().hasSnapshot();
	}

	public TripOffer_WaypointRef getRef() throws SQLException {
		TripOffer_WaypointRef ref = new TripOffer_WaypointRef();
		ref.setValue(this);
		return ref;
	}

	public void setRef(TripOffer_WaypointRef ref) throws SQLException {
		ref.setValue(this);
	}
}
