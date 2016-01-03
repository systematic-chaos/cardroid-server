package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import oracle.jpub.runtime.MutableArray;

/**
 * \class TripOffer_WaypointCollection
 * Domain class implementing a collection of Waypoint s for its storage and 
 * retrieval from an Oracle database
 */
public class TripOffer_WaypointCollection implements ORAData, ORADataFactory {
	public static final String _SQL_NAME = "ANDROID.WAYPOINT_COLLECTION_TYP";
	public static final int _SQL_TYPECODE = OracleTypes.ARRAY;

	MutableArray _array;

	private static final TripOffer_WaypointCollection _TripOffer_WaypointCollectionFactory = new TripOffer_WaypointCollection();

	public static ORADataFactory getORADataFactory() {
		return _TripOffer_WaypointCollectionFactory;
	}

	/* constructors */

	public TripOffer_WaypointCollection() {
		this(null);
	}

	public TripOffer_WaypointCollection(TripOffer_Waypoint[] a) {
		_array = new MutableArray(2002, a,
				TripOffer_Waypoint.getORADataFactory());
	}

	/* ORAData interface */

	public Datum toDatum(Connection c) throws SQLException {
		return _array.toDatum(c, _SQL_NAME);
	}

	/* ORADataFactory interface */

	public ORAData create(Datum d, int sqlType) throws SQLException {
		if (d == null)
			return null;
		TripOffer_WaypointCollection a = new TripOffer_WaypointCollection();
		a._array = new MutableArray(2002, (ARRAY) d,
				TripOffer_Waypoint.getORADataFactory());
		return a;
	}

	public int length() throws SQLException {
		return _array.length();
	}

	public int getBaseType() throws SQLException {
		return _array.getBaseType();
	}

	public String getBaseTypeName() throws SQLException {
		return _array.getBaseTypeName();
	}

	public ArrayDescriptor getDescriptor() throws SQLException {
		return _array.getDescriptor();
	}

	/* array accessor methods */

	public TripOffer_Waypoint[] getArray() throws SQLException {
		return (TripOffer_Waypoint[]) _array
				.getObjectArray(new TripOffer_Waypoint[_array.length()]);
	}

	public TripOffer_Waypoint[] getArray(long index, int count)
			throws SQLException {
		return (TripOffer_Waypoint[]) _array.getObjectArray(index,
				new TripOffer_Waypoint[_array.sliceLength(index, count)]);
	}

	public void setArray(TripOffer_Waypoint[] a) throws SQLException {
		_array.setObjectArray(a);
	}

	public void setArray(TripOffer_Waypoint[] a, long index)
			throws SQLException {
		_array.setObjectArray(a, index);
	}

	public TripOffer_Waypoint getElement(long index) throws SQLException {
		return (TripOffer_Waypoint) _array.getObjectElement(index);
	}

	public void setElement(TripOffer_Waypoint a, long index)
			throws SQLException {
		_array.setObjectElement(a, index);
	}

	public String toString() {
		try {
			String r = "ANDROID.WAYPOINT_COLLECTION_TYP" + "(";
			TripOffer_Waypoint[] a = getArray();
			for (int i = 0; i < a.length;) {
				r = r + a[i];
				i++;
				if (i < a.length)
					r = r + ",";
			}
			r = r + ")";
			return r;
		} catch (SQLException e) {
			return e.toString();
		}
	}

}
