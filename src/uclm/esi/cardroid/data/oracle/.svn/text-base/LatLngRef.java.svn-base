package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;

public class LatLngRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "ANDROID.LATLNG_TYP";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final LatLngRef _LatLngRefFactory = new LatLngRef();

    public static ORADataFactory getORADataFactory() {
        return _LatLngRefFactory;
    }
    /* constructor */

    public LatLngRef() {
    }

    /* ORAData interface */

    public Datum toDatum(Connection c) throws SQLException {
        return _ref;
    }

    /* ORADataFactory interface */

    public ORAData create(Datum d, int sqlType) throws SQLException {
        if (d == null)
            return null;
        LatLngRef r = new LatLngRef();
        r._ref = (REF)d;
        return r;
    }

    public static LatLngRef cast(ORAData o) throws SQLException {
        if (o == null)
            return null;
        try {
            return (LatLngRef)getORADataFactory().create(o.toDatum(null),
                                                         OracleTypes.REF);
        } catch (Exception exn) {
            throw new SQLException("Unable to convert " +
                                   o.getClass().getName() + " to LatLngRef: " +
                                   exn.toString());
        }
    }

    public LatLng getValue() throws SQLException {
        return (LatLng)LatLng.getORADataFactory().create(_ref.getSTRUCT(),
                                                         OracleTypes.REF);
    }

    public void setValue(LatLng c) throws SQLException {
        _ref.setValue(c.toDatum(_ref.getJavaSqlConnection()));
    }

    public String toString() {
        try {
            return "REF " + _ref.getBaseTypeName() + "(" + _ref + ")";
        } catch (SQLException e) {
            return e.toString();
        }
    }

}
