package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;

import oracle.jpub.runtime.MutableStruct;

public class CarTyp implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "ANDROID.CAR_TYP";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    protected MutableStruct _struct;

    protected static int[] _sqlType = { 12, 12, 12, 8, 4, 12, 12 };
    protected static ORADataFactory[] _factory = new ORADataFactory[7];
    protected static final CarTyp _CarTypFactory = new CarTyp();

    public static ORADataFactory getORADataFactory() {
        return _CarTypFactory;
    }
    /* constructors */

    protected void _init_struct(boolean init) {
        if (init)
            _struct = new MutableStruct(new Object[7], _sqlType, _factory);
    }

    public CarTyp() {
        _init_struct(true);
    }

    public CarTyp(String brand, String model, String fuel,
                  double consumptionPerKm, int nSeats, String color,
                  String plate) throws SQLException {
        _init_struct(true);
        setBrand(brand);
        setModel(model);
        setFuel(fuel);
        setConsumptionPerKm(consumptionPerKm);
        setNSeats(nSeats);
        setColor(color);
        setPlate(plate);
    }

    /* ORAData interface */

    public Datum toDatum(Connection c) throws SQLException {
        return _struct.toDatum(c, _SQL_NAME);
    }


    /* ORADataFactory interface */

    public ORAData create(Datum d, int sqlType) throws SQLException {
        return create(null, d, sqlType);
    }

    protected ORAData create(CarTyp o, Datum d,
                             int sqlType) throws SQLException {
        if (d == null)
            return null;
        if (o == null)
            o = new Car();
        o._struct = new MutableStruct((STRUCT)d, _sqlType, _factory);
        return o;
    }
    /* accessor methods */

    public String getBrand() throws SQLException {
        return (String)_struct.getAttribute(0);
    }

    public void setBrand(String brand) throws SQLException {
        _struct.setAttribute(0, brand);
    }


    public String getModel() throws SQLException {
        return (String)_struct.getAttribute(1);
    }

    public void setModel(String model) throws SQLException {
        _struct.setAttribute(1, model);
    }


    public String getFuelName() throws SQLException {
        return (String)_struct.getAttribute(2);
    }

    public void setFuel(String fuel) throws SQLException {
        _struct.setAttribute(2, fuel);
    }


    public double getConsumptionPerKm() throws SQLException {
        return ((Double)_struct.getAttribute(3)).doubleValue();
    }

    public void setConsumptionPerKm(double consumptionPerKm) throws SQLException {
        _struct.setAttribute(3, new Double(consumptionPerKm));
    }


    public int getNSeats() throws SQLException {
        return ((Integer)_struct.getAttribute(4)).intValue();
    }

    public void setNSeats(int nSeats) throws SQLException {
        _struct.setAttribute(4, new Integer(nSeats));
    }


    public String getColor() throws SQLException {
        return (String)_struct.getAttribute(5);
    }

    public void setColor(String color) throws SQLException {
        _struct.setAttribute(5, color);
    }


    public String getPlate() throws SQLException {
        return (String)_struct.getAttribute(6);
    }

    public void setPlate(String plate) throws SQLException {
        _struct.setAttribute(6, plate);
    }

    public String toString() {
        try {
            return "ANDROID.CAR_TYP" + "(" +
                ((getBrand() == null) ? "null" : "'" + getBrand() + "'") +
                "," +
                ((getModel() == null) ? "null" : "'" + getModel() + "'") +
                "," +
                ((getFuelName() == null) ? "null" : "'" + getFuelName() + "'") +
                "," + getConsumptionPerKm() + "," + getNSeats() + "," +
                ((getColor() == null) ? "null" : "'" + getColor() + "'") +
                "," +
                ((getPlate() == null) ? "null" : "'" + getPlate() + "'") + ")";
        } catch (Exception e) {
            return e.toString();
        }
    }

}
