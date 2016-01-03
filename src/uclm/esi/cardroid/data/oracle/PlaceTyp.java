/*@lineinfo:filename=Place*/ /*@lineinfo:user-code*/ /*@lineinfo:1^1*/package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;

import oracle.jpub.runtime.MutableStruct;

import sqlj.runtime.ref.DefaultContext;
import sqlj.runtime.ConnectionContext;

public class PlaceTyp implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "ANDROID.PLACE_TYP";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    /* connection management */
    protected Connection __onn = null;
    protected javax.sql.DataSource __dataSource = null;

    public void setDataSource(javax.sql.DataSource dataSource) throws SQLException {
        release();
        __dataSource = dataSource;
    }

    public void setDataSourceLocation(String dataSourceLocation) throws SQLException {
        javax.sql.DataSource dataSource;
        try {
            Class cls = Class.forName("javax.naming.InitialContext");
            Object ctx = cls.newInstance();
            java.lang.reflect.Method meth =
                cls.getMethod("lookup", new Class[] { String.class });
            dataSource =
                    (javax.sql.DataSource)meth.invoke(ctx, new Object[] { "java:comp/env/" +
                                                                          dataSourceLocation });
            setDataSource(dataSource);
        } catch (Exception e) {
            throw new java.sql.SQLException("Error initializing DataSource at " +
                                            dataSourceLocation + ": " +
                                            e.getMessage());
        }
    }

    public Connection getConnection() throws SQLException {
        if (__onn != null)
            return __onn;
        else if (__tx != null)
            return __tx.getConnection();
        else if (__dataSource != null)
            __onn = __dataSource.getConnection();
        return __onn;
    }

    public void release() throws SQLException {
        if (__tx != null && __onn != null)
            __tx.close(ConnectionContext.KEEP_CONNECTION);
        __onn = null;
        __tx = null;
        __dataSource = null;
    }

    public void closeConnection() {
        if (__dataSource != null) {
            try {
                if (__onn != null) {
                    __onn.close();
                }
            } catch (java.sql.SQLException e) {
            }
            try {
                if (__tx != null) {
                    __tx.close();
                }
            } catch (java.sql.SQLException e) {
            }
            __onn = null;
            __tx = null;
        }
    }
    protected DefaultContext __tx = null;

    public void setConnectionContext(DefaultContext ctx) throws SQLException {
        release();
        __tx = ctx;
    }

    public DefaultContext getConnectionContext() throws SQLException {
        if (__tx == null) {
            __tx =
(getConnection() == null) ? DefaultContext.getDefaultContext() :
new DefaultContext(getConnection());
        }
        return __tx;
    }
    protected MutableStruct _struct;

    protected static int[] _sqlType = { 12, 2002, 12, 2004 };
    protected static ORADataFactory[] _factory = new ORADataFactory[4];
    static {
        _factory[1] = LatLng.getORADataFactory();
    }
    protected static final PlaceTyp _PlaceTypFactory = new PlaceTyp();

    public static ORADataFactory getORADataFactory() {
        return _PlaceTypFactory;
    }
    /* constructors */

    protected void _init_struct(boolean init) {
        if (init)
            _struct = new MutableStruct(new Object[4], _sqlType, _factory);
    }

    public PlaceTyp() {
        _init_struct(true);
        __tx = DefaultContext.getDefaultContext();
    }

    public PlaceTyp(DefaultContext c) /*throws SQLException*/
    {
        _init_struct(true);
        __tx = c;
    }

    public PlaceTyp(Connection c) /*throws SQLException*/
    {
        _init_struct(true);
        __onn = c;
    }

    public PlaceTyp(String name, LatLng coords, String description,
                    java.sql.Blob snapshot) throws SQLException {
        _init_struct(true);
        setName(name);
        setCoords(coords);
        setDescription(description);
        setSnapshot(snapshot);
    }

    /* ORAData interface */

    public Datum toDatum(Connection c) throws SQLException {
        if (__tx != null && __onn != c)
            release();
        __onn = c;
        return _struct.toDatum(c, _SQL_NAME);
    }


    /* ORADataFactory interface */

    public ORAData create(Datum d, int sqlType) throws SQLException {
        return create(null, d, sqlType);
    }

    public void setFrom(PlaceTyp o) throws SQLException {
        setContextFrom(o);
        setValueFrom(o);
    }

    protected void setContextFrom(PlaceTyp o) throws SQLException {
        release();
        __tx = o.__tx;
        __onn = o.__onn;
    }

    protected void setValueFrom(PlaceTyp o) {
        _struct = o._struct;
    }

    protected ORAData create(PlaceTyp o, Datum d,
                             int sqlType) throws SQLException {
        if (d == null) {
            if (o != null) {
                o.release();
            }
            ;
            return null;
        }
        if (o == null)
            o = new Place();
        o._struct = new MutableStruct((STRUCT)d, _sqlType, _factory);
        o.__onn = ((STRUCT)d).getJavaSqlConnection();
        return o;
    }
    /* accessor methods */

    public String getName() throws SQLException {
        return (String)_struct.getAttribute(0);
    }

    public void setName(String name) throws SQLException {
        _struct.setAttribute(0, name);
    }


    public LatLng getCoords() throws SQLException {
        return (LatLng)_struct.getAttribute(1);
    }

    public void setCoords(LatLng coords) throws SQLException {
        _struct.setAttribute(1, coords);
    }


    public String getDescription() throws SQLException {
        return (String)_struct.getAttribute(2);
    }

    public void setDescription(String description) throws SQLException {
        _struct.setAttribute(2, description);
    }


    public java.sql.Blob getSnapshot() throws SQLException {
        return (java.sql.Blob)_struct.getAttribute(3);
    }

    public void setSnapshot(java.sql.Blob snapshot) throws SQLException {
        _struct.setAttribute(3, snapshot);
    }

    public String toString() {
        try {
            return "ANDROID.PLACE_TYP" + "(" +
                ((getName() == null) ? "null" : "'" + getName() + "'") + "," +
                getCoords() + "," +
                ((getDescription() == null) ? "null" : "'" + getDescription() +
                 "'") + "," + getSnapshot() + ")";
        } catch (Exception e) {
            return e.toString();
        }
    }


    public Place placeTyp(LatLng COORDS, String NAME,
                          Place[] __jPt_out) throws java.sql.SQLException {
        Place __jPt_temp = (Place)this;
        Place __jPt_result;
        try {
            /*@lineinfo:generated-code*/ /*@lineinfo:169^5*/

            //  ************************************************************
            //  #sql [getConnectionContext()] { BEGIN
            //        :__jPt_result := PLACE_TYP(
            //        :COORDS,
            //        :NAME);
            //        END;
            //       };
            //  ************************************************************

            {
                // declare temps
                oracle.jdbc.OracleCallableStatement __sJT_st = null;
                sqlj.runtime.ref.DefaultContext __sJT_cc =
                    getConnectionContext();
                if (__sJT_cc == null)
                    sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
                sqlj.runtime.ExecutionContext.OracleContext __sJT_ec =
                    ((__sJT_cc.getExecutionContext() == null) ?
                     sqlj.runtime.ExecutionContext.raiseNullExecCtx() :
                     __sJT_cc.getExecutionContext().getOracleContext());
                try {
                    String theSqlTS =
                        "BEGIN\n       :1   := PLACE_TYP(\n       :2  ,\n       :3  );\n      END;";
                    __sJT_st =
                            __sJT_ec.prepareOracleCall(__sJT_cc, "0uclm.esi.cardroid.data.oracle.PlaceTyp",
                                                       theSqlTS);
                    if (__sJT_ec.isNew()) {
                        __sJT_st.registerOutParameter(1, 2002,
                                                      "ANDROID.PLACE_TYP");
                    }
                    // set IN parameters
                    if (COORDS == null)
                        __sJT_st.setNull(2, 2002, "ANDROID.LATLNG_TYP");
                    else
                        __sJT_st.setORAData(2, COORDS);
                    __sJT_st.setString(3, NAME);
                    // execute statement
                    __sJT_ec.oracleExecuteUpdate();
                    // retrieve OUT parameters
                    __jPt_result =
                            (uclm.esi.cardroid.data.oracle.Place)__sJT_st.getORAData(1,
                                                                                     uclm.esi.cardroid.data.oracle.Place.getORADataFactory());
                } finally {
                    __sJT_ec.oracleClose();
                }
            }


            //  ************************************************************

            /*@lineinfo:user-code*/ /*@lineinfo:175^5*/
            __jPt_out[0] = __jPt_temp;
        } catch (java.sql.SQLException _err) {
            try {
                getConnectionContext().getExecutionContext().close();
                closeConnection();
                if (__dataSource == null)
                    throw _err;
                /*@lineinfo:generated-code*/ /*@lineinfo:182^5*/

                //  ************************************************************
                //  #sql [getConnectionContext()] { BEGIN
                //        :__jPt_result := PLACE_TYP(
                //        :COORDS,
                //        :NAME);
                //        END;
                //       };
                //  ************************************************************

                {
                    // declare temps
                    oracle.jdbc.OracleCallableStatement __sJT_st = null;
                    sqlj.runtime.ref.DefaultContext __sJT_cc =
                        getConnectionContext();
                    if (__sJT_cc == null)
                        sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
                    sqlj.runtime.ExecutionContext.OracleContext __sJT_ec =
                        ((__sJT_cc.getExecutionContext() == null) ?
                         sqlj.runtime.ExecutionContext.raiseNullExecCtx() :
                         __sJT_cc.getExecutionContext().getOracleContext());
                    try {
                        String theSqlTS =
                            "BEGIN\n       :1   := PLACE_TYP(\n       :2  ,\n       :3  );\n      END;";
                        __sJT_st =
                                __sJT_ec.prepareOracleCall(__sJT_cc, "1uclm.esi.cardroid.data.oracle.PlaceTyp",
                                                           theSqlTS);
                        if (__sJT_ec.isNew()) {
                            __sJT_st.registerOutParameter(1, 2002,
                                                          "ANDROID.PLACE_TYP");
                        }
                        // set IN parameters
                        if (COORDS == null)
                            __sJT_st.setNull(2, 2002, "ANDROID.LATLNG_TYP");
                        else
                            __sJT_st.setORAData(2, COORDS);
                        __sJT_st.setString(3, NAME);
                        // execute statement
                        __sJT_ec.oracleExecuteUpdate();
                        // retrieve OUT parameters
                        __jPt_result =
                                (uclm.esi.cardroid.data.oracle.Place)__sJT_st.getORAData(1,
                                                                                         uclm.esi.cardroid.data.oracle.Place.getORADataFactory());
                    } finally {
                        __sJT_ec.oracleClose();
                    }
                }


                //  ************************************************************

                /*@lineinfo:user-code*/ /*@lineinfo:188^5*/
                __jPt_out[0] = __jPt_temp;
            } catch (java.sql.SQLException _err2) {
                try {
                    getConnectionContext().getExecutionContext().close();
                } catch (java.sql.SQLException _sqle) {
                }
                throw _err;
            }
        }
        return __jPt_result;
    }

    public Place placeTyp(LatLng COORDS, String NAME, String DESCRIPTION,
                          java.sql.Blob SNAPSHOT,
                          Place[] __jPt_out) throws java.sql.SQLException {
        Place __jPt_temp = (Place)this;
        Place __jPt_result;
        try {
            /*@lineinfo:generated-code*/ /*@lineinfo:209^5*/

            //  ************************************************************
            //  #sql [getConnectionContext()] { BEGIN
            //        :__jPt_result := PLACE_TYP(
            //        :COORDS,
            //        :NAME,
            //        :DESCRIPTION,
            //        :SNAPSHOT);
            //        END;
            //       };
            //  ************************************************************

            {
                // declare temps
                oracle.jdbc.OracleCallableStatement __sJT_st = null;
                sqlj.runtime.ref.DefaultContext __sJT_cc =
                    getConnectionContext();
                if (__sJT_cc == null)
                    sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
                sqlj.runtime.ExecutionContext.OracleContext __sJT_ec =
                    ((__sJT_cc.getExecutionContext() == null) ?
                     sqlj.runtime.ExecutionContext.raiseNullExecCtx() :
                     __sJT_cc.getExecutionContext().getOracleContext());
                try {
                    String theSqlTS =
                        "BEGIN\n       :1   := PLACE_TYP(\n       :2  ,\n       :3  ,\n       :4  ,\n       :5  );\n      END;";
                    __sJT_st =
                            __sJT_ec.prepareOracleCall(__sJT_cc, "2uclm.esi.cardroid.data.oracle.PlaceTyp",
                                                       theSqlTS);
                    if (__sJT_ec.isNew()) {
                        __sJT_st.registerOutParameter(1, 2002,
                                                      "ANDROID.PLACE_TYP");
                    }
                    // set IN parameters
                    if (COORDS == null)
                        __sJT_st.setNull(2, 2002, "ANDROID.LATLNG_TYP");
                    else
                        __sJT_st.setORAData(2, COORDS);
                    __sJT_st.setString(3, NAME);
                    __sJT_st.setString(4, DESCRIPTION);
                    __sJT_st.setBlob(5, SNAPSHOT);
                    // execute statement
                    __sJT_ec.oracleExecuteUpdate();
                    // retrieve OUT parameters
                    __jPt_result =
                            (uclm.esi.cardroid.data.oracle.Place)__sJT_st.getORAData(1,
                                                                                     uclm.esi.cardroid.data.oracle.Place.getORADataFactory());
                } finally {
                    __sJT_ec.oracleClose();
                }
            }


            //  ************************************************************

            /*@lineinfo:user-code*/ /*@lineinfo:217^5*/
            __jPt_out[0] = __jPt_temp;
        } catch (java.sql.SQLException _err) {
            try {
                getConnectionContext().getExecutionContext().close();
                closeConnection();
                if (__dataSource == null)
                    throw _err;
                /*@lineinfo:generated-code*/ /*@lineinfo:224^5*/

                //  ************************************************************
                //  #sql [getConnectionContext()] { BEGIN
                //        :__jPt_result := PLACE_TYP(
                //        :COORDS,
                //        :NAME,
                //        :DESCRIPTION,
                //        :SNAPSHOT);
                //        END;
                //       };
                //  ************************************************************

                {
                    // declare temps
                    oracle.jdbc.OracleCallableStatement __sJT_st = null;
                    sqlj.runtime.ref.DefaultContext __sJT_cc =
                        getConnectionContext();
                    if (__sJT_cc == null)
                        sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
                    sqlj.runtime.ExecutionContext.OracleContext __sJT_ec =
                        ((__sJT_cc.getExecutionContext() == null) ?
                         sqlj.runtime.ExecutionContext.raiseNullExecCtx() :
                         __sJT_cc.getExecutionContext().getOracleContext());
                    try {
                        String theSqlTS =
                            "BEGIN\n       :1   := PLACE_TYP(\n       :2  ,\n       :3  ,\n       :4  ,\n       :5  );\n      END;";
                        __sJT_st =
                                __sJT_ec.prepareOracleCall(__sJT_cc, "3uclm.esi.cardroid.data.oracle.PlaceTyp",
                                                           theSqlTS);
                        if (__sJT_ec.isNew()) {
                            __sJT_st.registerOutParameter(1, 2002,
                                                          "ANDROID.PLACE_TYP");
                        }
                        // set IN parameters
                        if (COORDS == null)
                            __sJT_st.setNull(2, 2002, "ANDROID.LATLNG_TYP");
                        else
                            __sJT_st.setORAData(2, COORDS);
                        __sJT_st.setString(3, NAME);
                        __sJT_st.setString(4, DESCRIPTION);
                        __sJT_st.setBlob(5, SNAPSHOT);
                        // execute statement
                        __sJT_ec.oracleExecuteUpdate();
                        // retrieve OUT parameters
                        __jPt_result =
                                (uclm.esi.cardroid.data.oracle.Place)__sJT_st.getORAData(1,
                                                                                         uclm.esi.cardroid.data.oracle.Place.getORADataFactory());
                    } finally {
                        __sJT_ec.oracleClose();
                    }
                }


                //  ************************************************************

                /*@lineinfo:user-code*/ /*@lineinfo:232^5*/
                __jPt_out[0] = __jPt_temp;
            } catch (java.sql.SQLException _err2) {
                try {
                    getConnectionContext().getExecutionContext().close();
                } catch (java.sql.SQLException _sqle) {
                }
                throw _err;
            }
        }
        return __jPt_result;
    }

    public Place placeTyp(LatLng COORDS, String NAME, java.sql.Blob SNAPSHOT,
                          Place[] __jPt_out) throws java.sql.SQLException {
        Place __jPt_temp = (Place)this;
        Place __jPt_result;
        try {
            /*@lineinfo:generated-code*/ /*@lineinfo:252^5*/

            //  ************************************************************
            //  #sql [getConnectionContext()] { BEGIN
            //        :__jPt_result := PLACE_TYP(
            //        :COORDS,
            //        :NAME,
            //        :SNAPSHOT);
            //        END;
            //       };
            //  ************************************************************

            {
                // declare temps
                oracle.jdbc.OracleCallableStatement __sJT_st = null;
                sqlj.runtime.ref.DefaultContext __sJT_cc =
                    getConnectionContext();
                if (__sJT_cc == null)
                    sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
                sqlj.runtime.ExecutionContext.OracleContext __sJT_ec =
                    ((__sJT_cc.getExecutionContext() == null) ?
                     sqlj.runtime.ExecutionContext.raiseNullExecCtx() :
                     __sJT_cc.getExecutionContext().getOracleContext());
                try {
                    String theSqlTS =
                        "BEGIN\n       :1   := PLACE_TYP(\n       :2  ,\n       :3  ,\n       :4  );\n      END;";
                    __sJT_st =
                            __sJT_ec.prepareOracleCall(__sJT_cc, "4uclm.esi.cardroid.data.oracle.PlaceTyp",
                                                       theSqlTS);
                    if (__sJT_ec.isNew()) {
                        __sJT_st.registerOutParameter(1, 2002,
                                                      "ANDROID.PLACE_TYP");
                    }
                    // set IN parameters
                    if (COORDS == null)
                        __sJT_st.setNull(2, 2002, "ANDROID.LATLNG_TYP");
                    else
                        __sJT_st.setORAData(2, COORDS);
                    __sJT_st.setString(3, NAME);
                    __sJT_st.setBlob(4, SNAPSHOT);
                    // execute statement
                    __sJT_ec.oracleExecuteUpdate();
                    // retrieve OUT parameters
                    __jPt_result =
                            (uclm.esi.cardroid.data.oracle.Place)__sJT_st.getORAData(1,
                                                                                     uclm.esi.cardroid.data.oracle.Place.getORADataFactory());
                } finally {
                    __sJT_ec.oracleClose();
                }
            }


            //  ************************************************************

            /*@lineinfo:user-code*/ /*@lineinfo:259^5*/
            __jPt_out[0] = __jPt_temp;
        } catch (java.sql.SQLException _err) {
            try {
                getConnectionContext().getExecutionContext().close();
                closeConnection();
                if (__dataSource == null)
                    throw _err;
                /*@lineinfo:generated-code*/ /*@lineinfo:266^5*/

                //  ************************************************************
                //  #sql [getConnectionContext()] { BEGIN
                //        :__jPt_result := PLACE_TYP(
                //        :COORDS,
                //        :NAME,
                //        :SNAPSHOT);
                //        END;
                //       };
                //  ************************************************************

                {
                    // declare temps
                    oracle.jdbc.OracleCallableStatement __sJT_st = null;
                    sqlj.runtime.ref.DefaultContext __sJT_cc =
                        getConnectionContext();
                    if (__sJT_cc == null)
                        sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
                    sqlj.runtime.ExecutionContext.OracleContext __sJT_ec =
                        ((__sJT_cc.getExecutionContext() == null) ?
                         sqlj.runtime.ExecutionContext.raiseNullExecCtx() :
                         __sJT_cc.getExecutionContext().getOracleContext());
                    try {
                        String theSqlTS =
                            "BEGIN\n       :1   := PLACE_TYP(\n       :2  ,\n       :3  ,\n       :4  );\n      END;";
                        __sJT_st =
                                __sJT_ec.prepareOracleCall(__sJT_cc, "5uclm.esi.cardroid.data.oracle.PlaceTyp",
                                                           theSqlTS);
                        if (__sJT_ec.isNew()) {
                            __sJT_st.registerOutParameter(1, 2002,
                                                          "ANDROID.PLACE_TYP");
                        }
                        // set IN parameters
                        if (COORDS == null)
                            __sJT_st.setNull(2, 2002, "ANDROID.LATLNG_TYP");
                        else
                            __sJT_st.setORAData(2, COORDS);
                        __sJT_st.setString(3, NAME);
                        __sJT_st.setBlob(4, SNAPSHOT);
                        // execute statement
                        __sJT_ec.oracleExecuteUpdate();
                        // retrieve OUT parameters
                        __jPt_result =
                                (uclm.esi.cardroid.data.oracle.Place)__sJT_st.getORAData(1,
                                                                                         uclm.esi.cardroid.data.oracle.Place.getORADataFactory());
                    } finally {
                        __sJT_ec.oracleClose();
                    }
                }


                //  ************************************************************

                /*@lineinfo:user-code*/ /*@lineinfo:273^5*/
                __jPt_out[0] = __jPt_temp;
            } catch (java.sql.SQLException _err2) {
                try {
                    getConnectionContext().getExecutionContext().close();
                } catch (java.sql.SQLException _sqle) {
                }
                throw _err;
            }
        }
        return __jPt_result;
    }

    public Place placeTyp(LatLng COORDS, String NAME, String DESCRIPTION,
                          Place[] __jPt_out) throws java.sql.SQLException {
        Place __jPt_temp = (Place)this;
        Place __jPt_result;
        try {
            /*@lineinfo:generated-code*/ /*@lineinfo:293^5*/

            //  ************************************************************
            //  #sql [getConnectionContext()] { BEGIN
            //        :__jPt_result := PLACE_TYP(
            //        :COORDS,
            //        :NAME,
            //        :DESCRIPTION);
            //        END;
            //       };
            //  ************************************************************

            {
                // declare temps
                oracle.jdbc.OracleCallableStatement __sJT_st = null;
                sqlj.runtime.ref.DefaultContext __sJT_cc =
                    getConnectionContext();
                if (__sJT_cc == null)
                    sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
                sqlj.runtime.ExecutionContext.OracleContext __sJT_ec =
                    ((__sJT_cc.getExecutionContext() == null) ?
                     sqlj.runtime.ExecutionContext.raiseNullExecCtx() :
                     __sJT_cc.getExecutionContext().getOracleContext());
                try {
                    String theSqlTS =
                        "BEGIN\n       :1   := PLACE_TYP(\n       :2  ,\n       :3  ,\n       :4  );\n      END;";
                    __sJT_st =
                            __sJT_ec.prepareOracleCall(__sJT_cc, "6uclm.esi.cardroid.data.oracle.PlaceTyp",
                                                       theSqlTS);
                    if (__sJT_ec.isNew()) {
                        __sJT_st.registerOutParameter(1, 2002,
                                                      "ANDROID.PLACE_TYP");
                    }
                    // set IN parameters
                    if (COORDS == null)
                        __sJT_st.setNull(2, 2002, "ANDROID.LATLNG_TYP");
                    else
                        __sJT_st.setORAData(2, COORDS);
                    __sJT_st.setString(3, NAME);
                    __sJT_st.setString(4, DESCRIPTION);
                    // execute statement
                    __sJT_ec.oracleExecuteUpdate();
                    // retrieve OUT parameters
                    __jPt_result =
                            (uclm.esi.cardroid.data.oracle.Place)__sJT_st.getORAData(1,
                                                                                     uclm.esi.cardroid.data.oracle.Place.getORADataFactory());
                } finally {
                    __sJT_ec.oracleClose();
                }
            }


            //  ************************************************************

            /*@lineinfo:user-code*/ /*@lineinfo:300^5*/
            __jPt_out[0] = __jPt_temp;
        } catch (java.sql.SQLException _err) {
            try {
                getConnectionContext().getExecutionContext().close();
                closeConnection();
                if (__dataSource == null)
                    throw _err;
                /*@lineinfo:generated-code*/ /*@lineinfo:307^5*/

                //  ************************************************************
                //  #sql [getConnectionContext()] { BEGIN
                //        :__jPt_result := PLACE_TYP(
                //        :COORDS,
                //        :NAME,
                //        :DESCRIPTION);
                //        END;
                //       };
                //  ************************************************************

                {
                    // declare temps
                    oracle.jdbc.OracleCallableStatement __sJT_st = null;
                    sqlj.runtime.ref.DefaultContext __sJT_cc =
                        getConnectionContext();
                    if (__sJT_cc == null)
                        sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
                    sqlj.runtime.ExecutionContext.OracleContext __sJT_ec =
                        ((__sJT_cc.getExecutionContext() == null) ?
                         sqlj.runtime.ExecutionContext.raiseNullExecCtx() :
                         __sJT_cc.getExecutionContext().getOracleContext());
                    try {
                        String theSqlTS =
                            "BEGIN\n       :1   := PLACE_TYP(\n       :2  ,\n       :3  ,\n       :4  );\n      END;";
                        __sJT_st =
                                __sJT_ec.prepareOracleCall(__sJT_cc, "7uclm.esi.cardroid.data.oracle.PlaceTyp",
                                                           theSqlTS);
                        if (__sJT_ec.isNew()) {
                            __sJT_st.registerOutParameter(1, 2002,
                                                          "ANDROID.PLACE_TYP");
                        }
                        // set IN parameters
                        if (COORDS == null)
                            __sJT_st.setNull(2, 2002, "ANDROID.LATLNG_TYP");
                        else
                            __sJT_st.setORAData(2, COORDS);
                        __sJT_st.setString(3, NAME);
                        __sJT_st.setString(4, DESCRIPTION);
                        // execute statement
                        __sJT_ec.oracleExecuteUpdate();
                        // retrieve OUT parameters
                        __jPt_result =
                                (uclm.esi.cardroid.data.oracle.Place)__sJT_st.getORAData(1,
                                                                                         uclm.esi.cardroid.data.oracle.Place.getORADataFactory());
                    } finally {
                        __sJT_ec.oracleClose();
                    }
                }


                //  ************************************************************

                /*@lineinfo:user-code*/ /*@lineinfo:314^5*/
                __jPt_out[0] = __jPt_temp;
            } catch (java.sql.SQLException _err2) {
                try {
                    getConnectionContext().getExecutionContext().close();
                } catch (java.sql.SQLException _sqle) {
                }
                throw _err;
            }
        }
        return __jPt_result;
    }
}/*@lineinfo:generated-code*/