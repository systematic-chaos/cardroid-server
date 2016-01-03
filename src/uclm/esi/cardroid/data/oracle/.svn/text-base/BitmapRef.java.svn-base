package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;

public class BitmapRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "ANDROID.BITMAP_TYP";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final BitmapRef _BitmapRefFactory = new BitmapRef();

    public static ORADataFactory getORADataFactory() {
        return _BitmapRefFactory;
    }
    /* constructor */

    public BitmapRef() {
    }

    /* ORAData interface */

    public Datum toDatum(Connection c) throws SQLException {
        return _ref;
    }

    /* ORADataFactory interface */

    public ORAData create(Datum d, int sqlType) throws SQLException {
        if (d == null)
            return null;
        BitmapRef r = new BitmapRef();
        r._ref = (REF)d;
        return r;
    }

    public static BitmapRef cast(ORAData o) throws SQLException {
        if (o == null)
            return null;
        try {
            return (BitmapRef)getORADataFactory().create(o.toDatum(null),
                                                         OracleTypes.REF);
        } catch (Exception exn) {
            throw new SQLException("Unable to convert " +
                                   o.getClass().getName() + " to BitmapRef: " +
                                   exn.toString());
        }
    }

    public Bitmap getValue() throws SQLException {
        return (Bitmap)Bitmap.getORADataFactory().create(_ref.getSTRUCT(),
                                                         OracleTypes.REF);
    }

    public void setValue(Bitmap c) throws SQLException {
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
