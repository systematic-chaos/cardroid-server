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
 * \class User_CarCollection
 * Domain class implementing a collection of Car s for its storage and 
 * retrieval from an Oracle database
 */
public class User_CarCollection implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "ANDROID.CAR_COLLECTION_TYP";
    public static final int _SQL_TYPECODE = OracleTypes.ARRAY;

    MutableArray _array;

    private static final User_CarCollection _User_CarCollectionFactory =
        new User_CarCollection();

    public static ORADataFactory getORADataFactory() {
        return _User_CarCollectionFactory;
    }
    /* constructors */

    public User_CarCollection() {
        this(null);
    }

    public User_CarCollection(Car[] a) {
        _array = new MutableArray(2002, a, Car.getORADataFactory());
    }

    /* ORAData interface */

    public Datum toDatum(Connection c) throws SQLException {
        return _array.toDatum(c, _SQL_NAME);
    }

    /* ORADataFactory interface */

    public ORAData create(Datum d, int sqlType) throws SQLException {
        if (d == null)
            return null;
        User_CarCollection a = new User_CarCollection();
        a._array = new MutableArray(2002, (ARRAY)d, Car.getORADataFactory());
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

    public Car[] getArray() throws SQLException {
        return (Car[])_array.getObjectArray(new Car[_array.length()]);
    }

    public Car[] getArray(long index, int count) throws SQLException {
        return (Car[])_array.getObjectArray(index,
                                            new Car[_array.sliceLength(index,
                                                                       count)]);
    }

    public void setArray(Car[] a) throws SQLException {
        _array.setObjectArray(a);
    }

    public void setArray(Car[] a, long index) throws SQLException {
        _array.setObjectArray(a, index);
    }

    public Car getElement(long index) throws SQLException {
        return (Car)_array.getObjectElement(index);
    }

    public void setElement(Car a, long index) throws SQLException {
        _array.setObjectElement(a, index);
    }

    public String toString() {
        try {
            String r = "ANDROID.CAR_COLLECTION_TYP" + "(";
            Car[] a = getArray();
            for (int i = 0; i < a.length; ) {
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
