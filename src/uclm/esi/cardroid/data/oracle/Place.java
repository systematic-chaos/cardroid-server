/*@lineinfo:filename=PlaceTyp*//*@lineinfo:user-code*//*@lineinfo:1^1*/package uclm.esi.cardroid.data.oracle;

import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Connection;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;

import sqlj.runtime.ref.DefaultContext;

import uclm.esi.cardroid.data.ILatLng;
import uclm.esi.cardroid.data.IPlace;

/**
 * \class Place
 * Domain class implementing a Place for its storage and retrieval from an 
 * Oracle database
 */
public class Place extends PlaceTyp implements ORAData, ORADataFactory, IPlace {
	private static final Place _PlaceFactory = new Place();

	public static ORADataFactory getORADataFactory() {
		return _PlaceFactory;
	}

	public Place() {
		super();
	}

	public Place(Connection conn) throws SQLException {
		super(conn);
	}

	public Place(DefaultContext ctx) throws SQLException {
		super(ctx);
	}

	/**
	 * Long constructor
	 */
	public Place(String name, LatLng coords, String description,
			java.sql.Blob snapshot) {
		setName(name);
		setCoordinates(coords);
		setDescription(description);
		setSnapshot(snapshot);
	}

	/**
	 * Short constructor
	 */
	public Place(String name, LatLng coords) {
		setName(name);
		setCoordinates(coords);
	}

	/**
	 * Another constructor
	 */
	public Place(String name, LatLng coords, java.sql.Blob snapshot) {
		setName(name);
		setCoordinates(coords);
		setSnapshot(snapshot);
	}

	/**
	 * Another constructor
	 */
	public Place(String name, LatLng coords, String description) {
		setName(name);
		setCoordinates(coords);
		setDescription(description);
	}

	/* ORAData interface */

	public ORAData create(Datum d, int sqlType) throws SQLException {
		return create(new Place(), d, sqlType);
	}

	/* IPlace interface */

	public Place newInstance(IPlace placeObject) {
		if (placeObject == null)
			return null;
		if (placeObject instanceof Place)
			return (Place) placeObject;

		Place place = null;
		LatLng coords = new LatLng().newInstance(placeObject.getCoordinates());

		if (!placeObject.hasDescription() && !placeObject.hasSnapshot())
			return place = new Place(placeObject.getName(), coords);
		if (placeObject.hasDescription() && !placeObject.hasSnapshot())
			return place = new Place(placeObject.getName(), coords,
					placeObject.getDescription());
		if (!placeObject.hasDescription() && placeObject.hasSnapshot())
			return place = new Place(placeObject.getName(), coords,
					placeObject.getSnapshot());
		if (placeObject.hasDescription() && placeObject.hasSnapshot())
			return place = new Place(placeObject.getName(), coords,
					placeObject.getDescription(), placeObject.getSnapshot());
		return place;
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

	public void setCoordinates(ILatLng coords) {
		try {
			if (coords instanceof LatLng) {
				super.setCoords((LatLng) coords);
			} else {
				super.setCoords(new LatLng().newInstance(coords));
			}
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public LatLng getCoordinates() {
		LatLng coords = null;
		try {
			coords = super.getCoords();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return coords;
	}

	@Override
	public void setDescription(String description) {
		try {
			super.setDescription(description);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	@Override
	public String getDescription() {
		String description = null;
		try {
			description = super.getDescription();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return description;
	}

	public boolean hasDescription() {
		return getDescription() != null;
	}

	@Override
	public void setSnapshot(Blob snapshot) {
		try {
			super.setSnapshot(snapshot);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	@Override
	public Blob getSnapshot() {
		Blob snapshot = null;
		try {
			snapshot = super.getSnapshot();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return snapshot;
	}

	public boolean hasSnapshot() {
		return getSnapshot() != null;
	}

	/* superclass methods */

	public PlaceRef getRef() throws SQLException {
		PlaceRef ref = new PlaceRef();
		ref.setValue(this);
		return ref;
	}

	public void setRef(PlaceRef ref) throws SQLException {
		ref.setValue(this);
	}
}/* @lineinfo:generated-code */