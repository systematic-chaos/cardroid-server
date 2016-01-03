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
 * \class Trip_WeekDaysVarray
 * Domain class implementing an array of week days (characters) 
 * for its storage and retrieval from an Oracle database
 */
public class Trip_WeekDaysVarray implements ORAData, ORADataFactory {
	public static final String _SQL_NAME = "ANDROID.WEEKDAYS_V_TYP";
	public static final int _SQL_TYPECODE = OracleTypes.ARRAY;

	MutableArray _array;

	private static final Trip_WeekDaysVarray _Trip_WeekDaysVarrayFactory = new Trip_WeekDaysVarray();

	public static ORADataFactory getORADataFactory() {
		return _Trip_WeekDaysVarrayFactory;
	}

	/* constructors */

	public Trip_WeekDaysVarray() {
		this(null);
	}

	public Trip_WeekDaysVarray(String[] a) {
		_array = new MutableArray(1, a, null);
	}

	/* ORAData interface */

	public Datum toDatum(Connection c) throws SQLException {
		return _array.toDatum(c, _SQL_NAME);
	}

	/* ORADataFactory interface */

	public ORAData create(Datum d, int sqlType) throws SQLException {
		if (d == null)
			return null;
		Trip_WeekDaysVarray a = new Trip_WeekDaysVarray();
		a._array = new MutableArray(1, (ARRAY) d, null);
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

	public String[] getArray() throws SQLException {
		return (String[]) _array.getObjectArray();
	}

	public String[] getArray(long index, int count) throws SQLException {
		return (String[]) _array.getObjectArray(index, count);
	}

	public void setArray(String[] a) throws SQLException {
		_array.setObjectArray(a);
	}

	public void setArray(String[] a, long index) throws SQLException {
		_array.setObjectArray(a, index);
	}

	public String getElement(long index) throws SQLException {
		return (String) _array.getObjectElement(index);
	}

	public void setElement(String a, long index) throws SQLException {
		_array.setObjectElement(a, index);
	}

	public String toString() {
		try {
			String r = "ANDROID.WEEKDAYS_TYP" + "(";
			String[] a = getArray();
			for (int i = 0; i < a.length;) {
				r = r + ((a[i] == null) ? "null" : "'" + a[i] + "'");
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
