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
 * \class TripOffer_PassengerCollection
 * Domain class implementing a collection of Passenger s for its storage and  
 * retrieval from an Oracle database
 */
public class TripOffer_PassengerCollection implements ORAData, ORADataFactory {
	public static final String _SQL_NAME = "ANDROID.PASSENGER_COLLECTION_TYP";
	public static final int _SQL_TYPECODE = OracleTypes.ARRAY;

	MutableArray _array;

	private static final TripOffer_PassengerCollection _TripOffer_PassengerCollectionFactory = new TripOffer_PassengerCollection();

	public static ORADataFactory getORADataFactory() {
		return _TripOffer_PassengerCollectionFactory;
	}

	/* constructors */

	public TripOffer_PassengerCollection() {
		this(null);
	}

	public TripOffer_PassengerCollection(TripOffer_PassengerTyp[] a) {
		_array = new MutableArray(2002, a,
				TripOffer_Passenger.getORADataFactory());
	}

	/* ORAData interface */

	public Datum toDatum(Connection c) throws SQLException {
		return _array.toDatum(c, _SQL_NAME);
	}

	/* ORADataFactory interface */

	public ORAData create(Datum d, int sqlType) throws SQLException {
		if (d == null)
			return null;
		TripOffer_PassengerCollection a = new TripOffer_PassengerCollection();
		a._array = new MutableArray(2002, (ARRAY) d, TripOffer_Passenger.getORADataFactory());
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

	public TripOffer_Passenger[] getArray() throws SQLException {
		return (TripOffer_Passenger[]) _array.getObjectArray(new TripOffer_Passenger[_array
				.length()]);
	}

	public TripOffer_Passenger[] getArray(long index, int count)
			throws SQLException {
		return (TripOffer_Passenger[]) _array.getObjectArray(index,
				new TripOffer_Passenger[_array.sliceLength(index, count)]);
	}

	public void setArray(TripOffer_Passenger[] a) throws SQLException {
		_array.setObjectArray(a);
	}

	public void setArray(TripOffer_Passenger[] a, long index)
			throws SQLException {
		_array.setObjectArray(a, index);
	}

	public TripOffer_Passenger getElement(long index) throws SQLException {
		return (TripOffer_Passenger) _array.getObjectElement(index);
	}

	public void setElement(TripOffer_Passenger a, long index)
			throws SQLException {
		_array.setObjectElement(a, index);
	}

	public String toString() {
		try {
			String r = "ANDROID.PASSENGER_COLLECTION_TYP" + "(";
			TripOffer_Passenger[] a = getArray();
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
