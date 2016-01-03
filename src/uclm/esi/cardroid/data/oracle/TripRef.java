package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;

public class TripRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "ANDROID.TRIP_TYP";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final TripRef _TripRefFactory = new TripRef();

    public static ORADataFactory getORADataFactory() {
        return _TripRefFactory;
    }
    /* constructor */

    public TripRef() {
    }

    /* ORAData interface */

    public Datum toDatum(Connection c) throws SQLException {
        return _ref;
    }

    /* ORADataFactory interface */

    public ORAData create(Datum d, int sqlType) throws SQLException {
        if (d == null)
            return null;
        TripRef r = new TripRef();
        r._ref = (REF)d;
        return r;
    }

    public static TripRef cast(ORAData o) throws SQLException {
        if (o == null)
            return null;
        try {
            return (TripRef)getORADataFactory().create(o.toDatum(null),
                                                       OracleTypes.REF);
        } catch (Exception exn) {
            throw new SQLException("Unable to convert " +
                                   o.getClass().getName() + " to TripRef: " +
                                   exn.toString());
        }
    }

    public Trip getValue() throws SQLException {
        return (Trip)Trip.getORADataFactory().create(_ref.getSTRUCT(),
                                                     OracleTypes.REF);
    }

    public void setValue(Trip c) throws SQLException {
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
