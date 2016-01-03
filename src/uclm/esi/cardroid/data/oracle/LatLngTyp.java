package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;

import oracle.jpub.runtime.MutableStruct;

public class LatLngTyp implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "ANDROID.LATLNG_TYP";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    protected MutableStruct _struct;

    protected static int[] _sqlType = { 8, 8 };
    protected static ORADataFactory[] _factory = new ORADataFactory[2];
    protected static final LatLngTyp _LatLngTypFactory = new LatLngTyp();

    public static ORADataFactory getORADataFactory() {
        return _LatLngTypFactory;
    }
    /* constructors */

    protected void _init_struct(boolean init) {
        if (init)
            _struct = new MutableStruct(new Object[2], _sqlType, _factory);
    }

    public LatLngTyp() {
        _init_struct(true);
    }

    public LatLngTyp(double latitude, double longitude) throws SQLException {
        _init_struct(true);
        setLatitude(latitude);
        setLongitude(longitude);
    }

    /* ORAData interface */

    public Datum toDatum(Connection c) throws SQLException {
        return _struct.toDatum(c, _SQL_NAME);
    }


    /* ORADataFactory interface */

    public ORAData create(Datum d, int sqlType) throws SQLException {
        return create(null, d, sqlType);
    }

    protected ORAData create(LatLngTyp o, Datum d,
                             int sqlType) throws SQLException {
        if (d == null)
            return null;
        if (o == null)
            o = new LatLng();
        o._struct = new MutableStruct((STRUCT)d, _sqlType, _factory);
        return o;
    }
    /* accessor methods */

    public double getLatitude() throws SQLException {
        return ((Double)_struct.getAttribute(0)).doubleValue();
    }

    public void setLatitude(double latitude) throws SQLException {
        _struct.setAttribute(0, new Double(latitude));
    }


    public double getLongitude() throws SQLException {
        return ((Double)_struct.getAttribute(1)).doubleValue();
    }

    public void setLongitude(double longitude) throws SQLException {
        _struct.setAttribute(1, new Double(longitude));
    }

    public String toString() {
        try {
            return "ANDROID.LATLNG_TYP" + "(" + getLatitude() + "," +
                getLongitude() + ")";
        } catch (Exception e) {
            return e.toString();
        }
    }

}
