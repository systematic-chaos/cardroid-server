package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;

public class TripOffer_WaypointRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "ANDROID.WAYPOINT_TYP";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final TripOffer_WaypointRef _WaypointRefFactory = new TripOffer_WaypointRef();

    public static ORADataFactory getORADataFactory() {
        return _WaypointRefFactory;
    }
    /* constructor */

    public TripOffer_WaypointRef() {
    }

    /* ORAData interface */

    public Datum toDatum(Connection c) throws SQLException {
        return _ref;
    }

    /* ORADataFactory interface */

    public ORAData create(Datum d, int sqlType) throws SQLException {
        if (d == null)
            return null;
        TripOffer_WaypointRef r = new TripOffer_WaypointRef();
        r._ref = (REF)d;
        return r;
    }

    public static TripOffer_WaypointRef cast(ORAData o) throws SQLException {
        if (o == null)
            return null;
        try {
            return (TripOffer_WaypointRef)getORADataFactory().create(o.toDatum(null),
                                                           OracleTypes.REF);
        } catch (Exception exn) {
            throw new SQLException("Unable to convert " +
                                   o.getClass().getName() +
                                   " to TripOffer_WaypointRef: " + exn.toString());
        }
    }

    public TripOffer_Waypoint getValue() throws SQLException {
        return (TripOffer_Waypoint)TripOffer_Waypoint.getORADataFactory().create(_ref.getSTRUCT(),
                                                             OracleTypes.REF);
    }

    public void setValue(TripOffer_Waypoint c) throws SQLException {
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
