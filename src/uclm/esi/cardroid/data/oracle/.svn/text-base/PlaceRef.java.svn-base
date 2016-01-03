package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;

public class PlaceRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "ANDROID.PLACE_TYP";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final PlaceRef _PlaceRefFactory = new PlaceRef();

    public static ORADataFactory getORADataFactory() {
        return _PlaceRefFactory;
    }
    /* constructor */

    public PlaceRef() {
    }

    /* ORAData interface */

    public Datum toDatum(Connection c) throws SQLException {
        return _ref;
    }

    /* ORADataFactory interface */

    public ORAData create(Datum d, int sqlType) throws SQLException {
        if (d == null)
            return null;
        PlaceRef r = new PlaceRef();
        r._ref = (REF)d;
        return r;
    }

    public static PlaceRef cast(ORAData o) throws SQLException {
        if (o == null)
            return null;
        try {
            return (PlaceRef)getORADataFactory().create(o.toDatum(null),
                                                        OracleTypes.REF);
        } catch (Exception exn) {
            throw new SQLException("Unable to convert " +
                                   o.getClass().getName() + " to PlaceRef: " +
                                   exn.toString());
        }
    }

    public Place getValue() throws SQLException {
        return (Place)Place.getORADataFactory().create(_ref.getSTRUCT(),
                                                       OracleTypes.REF);
    }

    public void setValue(Place c) throws SQLException {
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
