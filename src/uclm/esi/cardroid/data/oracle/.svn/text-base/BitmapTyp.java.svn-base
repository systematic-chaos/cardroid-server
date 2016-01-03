package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;

import oracle.jpub.runtime.MutableStruct;

public class BitmapTyp implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "ANDROID.BITMAP_TYP";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    protected MutableStruct _struct;

    protected static int[] _sqlType = { 2004, 12, 12, 4 };
    protected static ORADataFactory[] _factory = new ORADataFactory[4];
    protected static final BitmapTyp _BitmapTypFactory = new BitmapTyp();

    public static ORADataFactory getORADataFactory() {
        return _BitmapTypFactory;
    }
    /* constructors */

    protected void _init_struct(boolean init) {
        if (init)
            _struct = new MutableStruct(new Object[4], _sqlType, _factory);
    }

    public BitmapTyp() {
        _init_struct(true);
    }

    public BitmapTyp(java.sql.Blob bitmap, String compressFormat,
                     String config, int density) throws SQLException {
        _init_struct(true);
        setBitmap(bitmap);
        setCompressFormat(compressFormat);
        setConfig(config);
        setDensity(density);
    }

    /* ORAData interface */

    public Datum toDatum(Connection c) throws SQLException {
        return _struct.toDatum(c, _SQL_NAME);
    }


    /* ORADataFactory interface */

    public ORAData create(Datum d, int sqlType) throws SQLException {
        return create(null, d, sqlType);
    }

    protected ORAData create(BitmapTyp o, Datum d,
                             int sqlType) throws SQLException {
        if (d == null)
            return null;
        if (o == null)
            o = new Bitmap();
        o._struct = new MutableStruct((STRUCT)d, _sqlType, _factory);
        return o;
    }
    /* accessor methods */

    public java.sql.Blob getBitmap() throws SQLException {
        return (java.sql.Blob)_struct.getAttribute(0);
    }

    public void setBitmap(java.sql.Blob bitmap) throws SQLException {
        _struct.setAttribute(0, bitmap);
    }


    public String getCompressFormatName() throws SQLException {
        return (String)_struct.getAttribute(1);
    }

    public void setCompressFormat(String compressFormat) throws SQLException {
        _struct.setAttribute(1, compressFormat);
    }


    public String getConfigName() throws SQLException {
        return (String)_struct.getAttribute(2);
    }

    public void setConfig(String config) throws SQLException {
        _struct.setAttribute(2, config);
    }


    public int getDensity() throws SQLException {
        return ((Integer)_struct.getAttribute(3)).intValue();
    }

    public void setDensity(int density) throws SQLException {
        _struct.setAttribute(3, new Integer(density));
    }

    public String toString() {
        try {
            return "ANDROID.BITMAP_TYP" + "(" + getBitmap() + "," +
                ((getCompressFormatName() == null) ? "null" :
                 "'" + getCompressFormatName() + "'") + "," +
                ((getConfigName() == null) ? "null" :
                 "'" + getConfigName() + "'") + "," + getDensity() + ")";
        } catch (Exception e) {
            return e.toString();
        }
    }

}
