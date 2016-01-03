/*@lineinfo:filename=DateTimePrefs*/ /*@lineinfo:user-code*/ /*@lineinfo:1^1*/package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;

import oracle.jpub.runtime.MutableStruct;

import sqlj.runtime.ref.DefaultContext;

public class DateTimePrefsTyp extends Date implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "ANDROID.DATE_TIME_PREFS_TYP";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    protected static int[] _sqlType = { 91, 4, 12 };
    protected static ORADataFactory[] _factory = new ORADataFactory[3];
    protected static final DateTimePrefsTyp _DateTimePrefsTypFactory =
        new DateTimePrefsTyp();

    public static ORADataFactory getORADataFactory() {
        return _DateTimePrefsTypFactory;
    }
    static {
        _map.put("ANDROID.DATE_TIME_PREFS_TYP", _DateTimePrefsTypFactory);
    }

    /* constructors */

    protected void _init_struct(boolean init) {
        if (init)
            _struct = new MutableStruct(new Object[3], _sqlType, _factory);
    }

    public DateTimePrefsTyp() {
        _init_struct(true);
        __tx = DefaultContext.getDefaultContext();
    }

    public DateTimePrefsTyp(DefaultContext c) /*throws SQLException*/
    {
        _init_struct(true);
        __tx = c;
    }

    public DateTimePrefsTyp(Connection c) /*throws SQLException*/
    {
        _init_struct(true);
        __onn = c;
    }

    public DateTimePrefsTyp(java.sql.Timestamp datetime, int toleranceDays,
                            String timePreferences) throws SQLException {
        _init_struct(true);
        setDatetime(datetime);
        setToleranceDays(toleranceDays);
        setTimePreferences(timePreferences);
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

    public void setFrom(DateTimePrefsTyp o) throws SQLException {
        setContextFrom(o);
        setValueFrom(o);
    }

    protected void setContextFrom(DateTimePrefsTyp o) throws SQLException {
        release();
        __tx = o.__tx;
        __onn = o.__onn;
    }

    protected void setValueFrom(DateTimePrefsTyp o) {
        _struct = o._struct;
    }

    protected ORAData create(DateTimePrefsTyp o, Datum d,
                             int sqlType) throws SQLException {
        if (d == null) {
            if (o != null) {
                o.release();
            }
            ;
            return null;
        }
        if (o == null)
            o = new DateTimePrefs();
        o._struct = new MutableStruct((STRUCT)d, _sqlType, _factory);
        o.__onn = ((STRUCT)d).getJavaSqlConnection();
        return o;
    }

    protected ORAData createExact(Datum d, int sqlType) throws SQLException {
        return create(null, d, sqlType);
    }

    /* accessor methods */

    public int getToleranceDays() throws SQLException {
        return ((Integer)_struct.getAttribute(1)).intValue();
    }

    public void setToleranceDays(int toleranceDays) throws SQLException {
        _struct.setAttribute(1, new Integer(toleranceDays));
    }


    public String getTimePreferencesName() throws SQLException {
        return (String)_struct.getAttribute(2);
    }

    public void setTimePreferences(String timePreferences) throws SQLException {
        _struct.setAttribute(2, timePreferences);
    }

    public String toString() {
        try {
            return "ANDROID.DATE_TIME_PREFS_TYP" + "(" + getDatetime() + "," +
                getToleranceDays() + "," +
                ((getTimePreferencesName() == null) ? "null" :
                 "'" + getTimePreferencesName() + "'") + ")";
        } catch (Exception e) {
            return e.toString();
        }
    }


    public java.sql.Timestamp compareTo() throws java.sql.SQLException {
        DateTyp __jPt_temp = this;
        java.sql.Timestamp __jPt_result;
        try {
            /*@lineinfo:generated-code*/ /*@lineinfo:104^5*/

            //  ************************************************************
            //  #sql [getConnectionContext()] { BEGIN
            //        :__jPt_result := :__jPt_temp.COMPARE_TO();
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
                        "BEGIN\n       :1   :=  :2  .COMPARE_TO();\n      END;";
                    __sJT_st =
                            __sJT_ec.prepareOracleCall(__sJT_cc, "0uclm.esi.cardroid.data.oracle.DateTimePrefsTyp",
                                                       theSqlTS);
                    if (__sJT_ec.isNew()) {
                        __sJT_st.registerOutParameter(1,
                                                      oracle.jdbc.OracleTypes.TIMESTAMP);
                    }
                    // set IN parameters
                    if (__jPt_temp == null)
                        __sJT_st.setNull(2, 2002, "ANDROID.DATE_TYP");
                    else
                        __sJT_st.setORAData(2, __jPt_temp);
                    // execute statement
                    __sJT_ec.oracleExecuteUpdate();
                    // retrieve OUT parameters
                    __jPt_result = __sJT_st.getTimestamp(1);
                } finally {
                    __sJT_ec.oracleClose();
                }
            }


            //  ************************************************************

            /*@lineinfo:user-code*/ /*@lineinfo:108^5*/
        } catch (java.sql.SQLException _err) {
            try {
                getConnectionContext().getExecutionContext().close();
                closeConnection();
                if (__dataSource == null)
                    throw _err;
                /*@lineinfo:generated-code*/ /*@lineinfo:114^5*/

                //  ************************************************************
                //  #sql [getConnectionContext()] { BEGIN
                //        :__jPt_result := :__jPt_temp.COMPARE_TO();
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
                            "BEGIN\n       :1   :=  :2  .COMPARE_TO();\n      END;";
                        __sJT_st =
                                __sJT_ec.prepareOracleCall(__sJT_cc, "1uclm.esi.cardroid.data.oracle.DateTimePrefsTyp",
                                                           theSqlTS);
                        if (__sJT_ec.isNew()) {
                            __sJT_st.registerOutParameter(1,
                                                          oracle.jdbc.OracleTypes.TIMESTAMP);
                        }
                        // set IN parameters
                        if (__jPt_temp == null)
                            __sJT_st.setNull(2, 2002, "ANDROID.DATE_TYP");
                        else
                            __sJT_st.setORAData(2, __jPt_temp);
                        // execute statement
                        __sJT_ec.oracleExecuteUpdate();
                        // retrieve OUT parameters
                        __jPt_result = __sJT_st.getTimestamp(1);
                    } finally {
                        __sJT_ec.oracleClose();
                    }
                }


                //  ************************************************************

                /*@lineinfo:user-code*/ /*@lineinfo:118^5*/
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

    public DateTimePrefs dateTimePrefsTyp(java.sql.Timestamp DATETIME,
                                          String TIME_PREFERENCES,
                                          DateTimePrefs[] __jPt_out) throws java.sql.SQLException {
        DateTimePrefs __jPt_temp = (DateTimePrefs)this;
        DateTimePrefs __jPt_result;
        try {
            /*@lineinfo:generated-code*/ /*@lineinfo:136^5*/

            //  ************************************************************
            //  #sql [getConnectionContext()] { BEGIN
            //        :__jPt_result := DATE_TIME_PREFS_TYP(
            //        :DATETIME,
            //        :TIME_PREFERENCES);
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
                        "BEGIN\n       :1   := DATE_TIME_PREFS_TYP(\n       :2  ,\n       :3  );\n      END;";
                    __sJT_st =
                            __sJT_ec.prepareOracleCall(__sJT_cc, "2uclm.esi.cardroid.data.oracle.DateTimePrefsTyp",
                                                       theSqlTS);
                    if (__sJT_ec.isNew()) {
                        __sJT_st.registerOutParameter(1, 2002,
                                                      "ANDROID.DATE_TIME_PREFS_TYP");
                    }
                    // set IN parameters
                    __sJT_st.setTimestamp(2, DATETIME);
                    __sJT_st.setString(3, TIME_PREFERENCES);
                    // execute statement
                    __sJT_ec.oracleExecuteUpdate();
                    // retrieve OUT parameters
                    __jPt_result =
                            (uclm.esi.cardroid.data.oracle.DateTimePrefs)__sJT_st.getORAData(1,
                                                                                             uclm.esi.cardroid.data.oracle.DateTimePrefs.getORADataFactory());
                } finally {
                    __sJT_ec.oracleClose();
                }
            }


            //  ************************************************************

            /*@lineinfo:user-code*/ /*@lineinfo:142^5*/
            __jPt_out[0] = __jPt_temp;
        } catch (java.sql.SQLException _err) {
            try {
                getConnectionContext().getExecutionContext().close();
                closeConnection();
                if (__dataSource == null)
                    throw _err;
                /*@lineinfo:generated-code*/ /*@lineinfo:149^5*/

                //  ************************************************************
                //  #sql [getConnectionContext()] { BEGIN
                //        :__jPt_result := DATE_TIME_PREFS_TYP(
                //        :DATETIME,
                //        :TIME_PREFERENCES);
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
                            "BEGIN\n       :1   := DATE_TIME_PREFS_TYP(\n       :2  ,\n       :3  );\n      END;";
                        __sJT_st =
                                __sJT_ec.prepareOracleCall(__sJT_cc, "3uclm.esi.cardroid.data.oracle.DateTimePrefsTyp",
                                                           theSqlTS);
                        if (__sJT_ec.isNew()) {
                            __sJT_st.registerOutParameter(1, 2002,
                                                          "ANDROID.DATE_TIME_PREFS_TYP");
                        }
                        // set IN parameters
                        __sJT_st.setTimestamp(2, DATETIME);
                        __sJT_st.setString(3, TIME_PREFERENCES);
                        // execute statement
                        __sJT_ec.oracleExecuteUpdate();
                        // retrieve OUT parameters
                        __jPt_result =
                                (uclm.esi.cardroid.data.oracle.DateTimePrefs)__sJT_st.getORAData(1,
                                                                                                 uclm.esi.cardroid.data.oracle.DateTimePrefs.getORADataFactory());
                    } finally {
                        __sJT_ec.oracleClose();
                    }
                }


                //  ************************************************************

                /*@lineinfo:user-code*/ /*@lineinfo:155^5*/
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

    public DateTimePrefs dateTimePrefsTyp(java.sql.Timestamp DATETIME,
                                          int TOLERANCE_DAYS,
                                          DateTimePrefs[] __jPt_out) throws java.sql.SQLException {
        DateTimePrefs __jPt_temp = (DateTimePrefs)this;
        DateTimePrefs __jPt_result;
        try {
            /*@lineinfo:generated-code*/ /*@lineinfo:174^5*/

            //  ************************************************************
            //  #sql [getConnectionContext()] { BEGIN
            //        :__jPt_result := DATE_TIME_PREFS_TYP(
            //        :DATETIME,
            //        :TOLERANCE_DAYS);
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
                        "BEGIN\n       :1   := DATE_TIME_PREFS_TYP(\n       :2  ,\n       :3  );\n      END;";
                    __sJT_st =
                            __sJT_ec.prepareOracleCall(__sJT_cc, "4uclm.esi.cardroid.data.oracle.DateTimePrefsTyp",
                                                       theSqlTS);
                    if (__sJT_ec.isNew()) {
                        __sJT_st.registerOutParameter(1, 2002,
                                                      "ANDROID.DATE_TIME_PREFS_TYP");
                    }
                    // set IN parameters
                    __sJT_st.setTimestamp(2, DATETIME);
                    __sJT_st.setInt(3, TOLERANCE_DAYS);
                    // execute statement
                    __sJT_ec.oracleExecuteUpdate();
                    // retrieve OUT parameters
                    __jPt_result =
                            (uclm.esi.cardroid.data.oracle.DateTimePrefs)__sJT_st.getORAData(1,
                                                                                             uclm.esi.cardroid.data.oracle.DateTimePrefs.getORADataFactory());
                } finally {
                    __sJT_ec.oracleClose();
                }
            }


            //  ************************************************************

            /*@lineinfo:user-code*/ /*@lineinfo:180^5*/
            __jPt_out[0] = __jPt_temp;
        } catch (java.sql.SQLException _err) {
            try {
                getConnectionContext().getExecutionContext().close();
                closeConnection();
                if (__dataSource == null)
                    throw _err;
                /*@lineinfo:generated-code*/ /*@lineinfo:187^5*/

                //  ************************************************************
                //  #sql [getConnectionContext()] { BEGIN
                //        :__jPt_result := DATE_TIME_PREFS_TYP(
                //        :DATETIME,
                //        :TOLERANCE_DAYS);
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
                            "BEGIN\n       :1   := DATE_TIME_PREFS_TYP(\n       :2  ,\n       :3  );\n      END;";
                        __sJT_st =
                                __sJT_ec.prepareOracleCall(__sJT_cc, "5uclm.esi.cardroid.data.oracle.DateTimePrefsTyp",
                                                           theSqlTS);
                        if (__sJT_ec.isNew()) {
                            __sJT_st.registerOutParameter(1, 2002,
                                                          "ANDROID.DATE_TIME_PREFS_TYP");
                        }
                        // set IN parameters
                        __sJT_st.setTimestamp(2, DATETIME);
                        __sJT_st.setInt(3, TOLERANCE_DAYS);
                        // execute statement
                        __sJT_ec.oracleExecuteUpdate();
                        // retrieve OUT parameters
                        __jPt_result =
                                (uclm.esi.cardroid.data.oracle.DateTimePrefs)__sJT_st.getORAData(1,
                                                                                                 uclm.esi.cardroid.data.oracle.DateTimePrefs.getORADataFactory());
                    } finally {
                        __sJT_ec.oracleClose();
                    }
                }


                //  ************************************************************

                /*@lineinfo:user-code*/ /*@lineinfo:193^5*/
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