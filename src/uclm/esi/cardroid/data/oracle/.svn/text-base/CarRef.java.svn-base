package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;

public class CarRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "ANDROID.CAR_TYP";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final CarRef _CarRefFactory = new CarRef();

    public static ORADataFactory getORADataFactory() {
        return _CarRefFactory;
    }
    /* constructor */

    public CarRef() {
    }

    /* ORAData interface */

    public Datum toDatum(Connection c) throws SQLException {
        return _ref;
    }

    /* ORADataFactory interface */

    public ORAData create(Datum d, int sqlType) throws SQLException {
        if (d == null)
            return null;
        CarRef r = new CarRef();
        r._ref = (REF)d;
        return r;
    }

    public static CarRef cast(ORAData o) throws SQLException {
        if (o == null)
            return null;
        try {
            return (CarRef)getORADataFactory().create(o.toDatum(null),
                                                      OracleTypes.REF);
        } catch (Exception exn) {
            throw new SQLException("Unable to convert " +
                                   o.getClass().getName() + " to CarRef: " +
                                   exn.toString());
        }
    }

    public Car getValue() throws SQLException {
        return (Car)Car.getORADataFactory().create(_ref.getSTRUCT(),
                                                   OracleTypes.REF);
    }

    public void setValue(Car c) throws SQLException {
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
