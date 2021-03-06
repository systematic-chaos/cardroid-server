package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;

import oracle.jpub.runtime.MutableStruct;

public class TripOffer_WaypointTyp implements ORAData, ORADataFactory {
	public static final String _SQL_NAME = "ANDROID.WAYPOINT_TYP";
	public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

	protected MutableStruct _struct;

	protected static int[] _sqlType = { 4, 2002 };
	protected static ORADataFactory[] _factory = new ORADataFactory[2];
	static {
		_factory[1] = Place.getORADataFactory();
	}
	protected static final TripOffer_WaypointTyp _WaypointTypFactory = new TripOffer_WaypointTyp();

	public static ORADataFactory getORADataFactory() {
		return _WaypointTypFactory;
	}

	/* constructors */

	protected void _init_struct(boolean init) {
		if (init)
			_struct = new MutableStruct(new Object[2], _sqlType, _factory);
	}

	public TripOffer_WaypointTyp() {
		_init_struct(true);
	}

	public TripOffer_WaypointTyp(int nOrder, Place place) throws SQLException {
		_init_struct(true);
		setNOrder(nOrder);
		setPlace(place);
	}

	/* ORAData interface */

	public Datum toDatum(Connection c) throws SQLException {
		return _struct.toDatum(c, _SQL_NAME);
	}

	/* ORADataFactory interface */

	public ORAData create(Datum d, int sqlType) throws SQLException {
		return create(null, d, sqlType);
	}

	protected ORAData create(TripOffer_WaypointTyp o, Datum d, int sqlType)
			throws SQLException {
		if (d == null)
			return null;
		if (o == null)
			o = new TripOffer_Waypoint();
		o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
		return o;
	}

	/* accessor methods */

	public int getNOrder() throws SQLException {
		return ((Integer) _struct.getAttribute(0)).intValue();
	}

	public void setNOrder(int nOrder) throws SQLException {
		_struct.setAttribute(0, new Integer(nOrder));
	}

	public Place getPlace() throws SQLException {
		return (Place) _struct.getAttribute(1);
	}

	public void setPlace(Place place) throws SQLException {
		_struct.setAttribute(1, place);
	}

	public String toString() {
		try {
			return "ANDROID.WAYPOINT_TYP" + "(" + getNOrder() + ","
					+ getPlace() + ")";
		} catch (Exception e) {
			return e.toString();
		}
	}

}
