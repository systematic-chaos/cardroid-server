/*@lineinfo:filename=User*/ /*@lineinfo:user-code*/ /*@lineinfo:1^1*/package uclm.esi.cardroid.data.oracle;

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

public class UserTyp implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "ANDROID.USER_TYP";
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

    protected static int[] _sqlType = { 12, 12, 2004, 2002, 4, 12, 4, 2003 };
    protected static ORADataFactory[] _factory = new ORADataFactory[8];
    static {
        _factory[3] = Place.getORADataFactory();
        _factory[7] = User_CarCollection.getORADataFactory();
    }
    protected static final UserTyp _UserTypFactory = new UserTyp();

    public static ORADataFactory getORADataFactory() {
        return _UserTypFactory;
    }
    /* constructors */

    protected void _init_struct(boolean init) {
        if (init)
            _struct = new MutableStruct(new Object[8], _sqlType, _factory);
    }

    public UserTyp() {
        _init_struct(true);
        __tx = DefaultContext.getDefaultContext();
    }

    public UserTyp(DefaultContext c) /*throws SQLException*/
    {
        _init_struct(true);
        __tx = c;
    }

    public UserTyp(Connection c) /*throws SQLException*/
    {
        _init_struct(true);
        __onn = c;
    }

    public UserTyp(String name, String surname, java.sql.Blob avatar,
                   Place home, int telephone, String email, int reputation,
                   User_CarCollection cars) throws SQLException {
        _init_struct(true);
        setName(name);
        setSurname(surname);
        setAvatar(avatar);
        setHomePlace(home);
        setTelephoneNumber(telephone);
        setEmail(email);
        setReputation(reputation);
        setCars(cars);
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

    public void setFrom(UserTyp o) throws SQLException {
        setContextFrom(o);
        setValueFrom(o);
    }

    protected void setContextFrom(UserTyp o) throws SQLException {
        release();
        __tx = o.__tx;
        __onn = o.__onn;
    }

    protected void setValueFrom(UserTyp o) {
        _struct = o._struct;
    }

    protected ORAData create(UserTyp o, Datum d,
                             int sqlType) throws SQLException {
        if (d == null) {
            if (o != null) {
                o.release();
            }
            ;
            return null;
        }
        if (o == null)
            o = new User();
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


    public String getSurname() throws SQLException {
        return (String)_struct.getAttribute(1);
    }

    public void setSurname(String surname) throws SQLException {
        _struct.setAttribute(1, surname);
    }


    public java.sql.Blob getAvatar() throws SQLException {
        return (java.sql.Blob)_struct.getAttribute(2);
    }

    public void setAvatar(java.sql.Blob avatar) throws SQLException {
        _struct.setAttribute(2, avatar);
    }


    public Place getHome() throws SQLException {
        return (Place)_struct.getAttribute(3);
    }

    public void setHomePlace(Place home) throws SQLException {
        _struct.setAttribute(3, home);
    }


    public int getTelephoneNumber() throws SQLException {
        return ((Integer)_struct.getAttribute(4)).intValue();
    }

    public void setTelephoneNumber(int telephone) throws SQLException {
        _struct.setAttribute(4, new Integer(telephone));
    }


    public String getEmail() throws SQLException {
        return (String)_struct.getAttribute(5);
    }

    public void setEmail(String email) throws SQLException {
        _struct.setAttribute(5, email);
    }


    public int getReputation() throws SQLException {
        return ((Integer)_struct.getAttribute(6)).intValue();
    }

    public void setReputation(int reputation) throws SQLException {
        _struct.setAttribute(6, new Integer(reputation));
    }


    public User_CarCollection getCarsCollection() throws SQLException {
        return (User_CarCollection)_struct.getAttribute(7);
    }

    public void setCars(User_CarCollection cars) throws SQLException {
        _struct.setAttribute(7, cars);
    }

    public String toString() {
        try {
            return "ANDROID.USER_TYP" + "(" +
                ((getName() == null) ? "null" : "'" + getName() + "'") + "," +
                ((getSurname() == null) ? "null" : "'" + getSurname() + "'") +
                "," + getAvatar() + "," + getHome() + "," + getTelephoneNumber() +
                "," +
                ((getEmail() == null) ? "null" : "'" + getEmail() + "'") +
                "," + getReputation() + "," + getCarsCollection() + ")";
        } catch (Exception e) {
            return e.toString();
        }
    }


    public User addCar(Car CAR) throws java.sql.SQLException {
        try {
            User __jPt_temp = (User)this;
            /*@lineinfo:generated-code*/ /*@lineinfo:203^5*/

            //  ************************************************************
            //  #sql [getConnectionContext()] { BEGIN
            //        :__jPt_temp.ADD_CAR(
            //        :CAR);
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
                        "BEGIN\n       :1  .ADD_CAR(\n       :2  );\n      END;";
                    __sJT_st =
                            __sJT_ec.prepareOracleCall(__sJT_cc, "0uclm.esi.cardroid.data.oracle.UserTyp",
                                                       theSqlTS);
                    if (__sJT_ec.isNew()) {
                        __sJT_st.registerOutParameter(1, 2002,
                                                      "ANDROID.USER_TYP");
                    }
                    // set IN parameters
                    if (__jPt_temp == null)
                        __sJT_st.setNull(1, 2002, "ANDROID.USER_TYP");
                    else
                        __sJT_st.setORAData(1, __jPt_temp);
                    if (CAR == null)
                        __sJT_st.setNull(2, 2002, "ANDROID.CAR_TYP");
                    else
                        __sJT_st.setORAData(2, CAR);
                    // execute statement
                    __sJT_ec.oracleExecuteUpdate();
                    // retrieve OUT parameters
                    __jPt_temp =
                            (uclm.esi.cardroid.data.oracle.User)__sJT_st.getORAData(1,
                                                                                    uclm.esi.cardroid.data.oracle.User.getORADataFactory());
                } finally {
                    __sJT_ec.oracleClose();
                }
            }


            //  ************************************************************

            /*@lineinfo:user-code*/ /*@lineinfo:208^5*/
            return __jPt_temp;
        } catch (java.sql.SQLException _err) {
            try {
                getConnectionContext().getExecutionContext().close();
                closeConnection();
                if (__dataSource == null)
                    throw _err;
                User __jPt_temp = (User)this;
                /*@lineinfo:generated-code*/ /*@lineinfo:216^5*/

                //  ************************************************************
                //  #sql [getConnectionContext()] { BEGIN
                //        :__jPt_temp.ADD_CAR(
                //        :CAR);
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
                            "BEGIN\n       :1  .ADD_CAR(\n       :2  );\n      END;";
                        __sJT_st =
                                __sJT_ec.prepareOracleCall(__sJT_cc, "1uclm.esi.cardroid.data.oracle.UserTyp",
                                                           theSqlTS);
                        if (__sJT_ec.isNew()) {
                            __sJT_st.registerOutParameter(1, 2002,
                                                          "ANDROID.USER_TYP");
                        }
                        // set IN parameters
                        if (__jPt_temp == null)
                            __sJT_st.setNull(1, 2002, "ANDROID.USER_TYP");
                        else
                            __sJT_st.setORAData(1, __jPt_temp);
                        if (CAR == null)
                            __sJT_st.setNull(2, 2002, "ANDROID.CAR_TYP");
                        else
                            __sJT_st.setORAData(2, CAR);
                        // execute statement
                        __sJT_ec.oracleExecuteUpdate();
                        // retrieve OUT parameters
                        __jPt_temp =
                                (uclm.esi.cardroid.data.oracle.User)__sJT_st.getORAData(1,
                                                                                        uclm.esi.cardroid.data.oracle.User.getORADataFactory());
                    } finally {
                        __sJT_ec.oracleClose();
                    }
                }


                //  ************************************************************

                /*@lineinfo:user-code*/ /*@lineinfo:221^5*/
                return __jPt_temp;
            } catch (java.sql.SQLException _err2) {
                try {
                    getConnectionContext().getExecutionContext().close();
                } catch (java.sql.SQLException _sqle) {
                }
                throw _err;
            }
        }
    }

    public User clearCarsCollection() throws java.sql.SQLException {
        try {
            User __jPt_temp = (User)this;
            /*@lineinfo:generated-code*/ /*@lineinfo:235^5*/

            //  ************************************************************
            //  #sql [getConnectionContext()] { BEGIN
            //        :__jPt_temp.CLEAR_CARS();
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
                        "BEGIN\n       :1  .CLEAR_CARS();\n      END;";
                    __sJT_st =
                            __sJT_ec.prepareOracleCall(__sJT_cc, "2uclm.esi.cardroid.data.oracle.UserTyp",
                                                       theSqlTS);
                    if (__sJT_ec.isNew()) {
                        __sJT_st.registerOutParameter(1, 2002,
                                                      "ANDROID.USER_TYP");
                    }
                    // set IN parameters
                    if (__jPt_temp == null)
                        __sJT_st.setNull(1, 2002, "ANDROID.USER_TYP");
                    else
                        __sJT_st.setORAData(1, __jPt_temp);
                    // execute statement
                    __sJT_ec.oracleExecuteUpdate();
                    // retrieve OUT parameters
                    __jPt_temp =
                            (uclm.esi.cardroid.data.oracle.User)__sJT_st.getORAData(1,
                                                                                    uclm.esi.cardroid.data.oracle.User.getORADataFactory());
                } finally {
                    __sJT_ec.oracleClose();
                }
            }


            //  ************************************************************

            /*@lineinfo:user-code*/ /*@lineinfo:239^5*/
            return __jPt_temp;
        } catch (java.sql.SQLException _err) {
            try {
                getConnectionContext().getExecutionContext().close();
                closeConnection();
                if (__dataSource == null)
                    throw _err;
                User __jPt_temp = (User)this;
                /*@lineinfo:generated-code*/ /*@lineinfo:247^5*/

                //  ************************************************************
                //  #sql [getConnectionContext()] { BEGIN
                //        :__jPt_temp.CLEAR_CARS();
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
                            "BEGIN\n       :1  .CLEAR_CARS();\n      END;";
                        __sJT_st =
                                __sJT_ec.prepareOracleCall(__sJT_cc, "3uclm.esi.cardroid.data.oracle.UserTyp",
                                                           theSqlTS);
                        if (__sJT_ec.isNew()) {
                            __sJT_st.registerOutParameter(1, 2002,
                                                          "ANDROID.USER_TYP");
                        }
                        // set IN parameters
                        if (__jPt_temp == null)
                            __sJT_st.setNull(1, 2002, "ANDROID.USER_TYP");
                        else
                            __sJT_st.setORAData(1, __jPt_temp);
                        // execute statement
                        __sJT_ec.oracleExecuteUpdate();
                        // retrieve OUT parameters
                        __jPt_temp =
                                (uclm.esi.cardroid.data.oracle.User)__sJT_st.getORAData(1,
                                                                                        uclm.esi.cardroid.data.oracle.User.getORADataFactory());
                    } finally {
                        __sJT_ec.oracleClose();
                    }
                }


                //  ************************************************************

                /*@lineinfo:user-code*/ /*@lineinfo:251^5*/
                return __jPt_temp;
            } catch (java.sql.SQLException _err2) {
                try {
                    getConnectionContext().getExecutionContext().close();
                } catch (java.sql.SQLException _sqle) {
                }
                throw _err;
            }
        }
    }

    public User decreaseUserReputation() throws java.sql.SQLException {
        try {
            User __jPt_temp = (User)this;
            /*@lineinfo:generated-code*/ /*@lineinfo:265^5*/

            //  ************************************************************
            //  #sql [getConnectionContext()] { BEGIN
            //        :__jPt_temp.DECREASE_REPUTATION();
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
                        "BEGIN\n       :1  .DECREASE_REPUTATION();\n      END;";
                    __sJT_st =
                            __sJT_ec.prepareOracleCall(__sJT_cc, "4uclm.esi.cardroid.data.oracle.UserTyp",
                                                       theSqlTS);
                    if (__sJT_ec.isNew()) {
                        __sJT_st.registerOutParameter(1, 2002,
                                                      "ANDROID.USER_TYP");
                    }
                    // set IN parameters
                    if (__jPt_temp == null)
                        __sJT_st.setNull(1, 2002, "ANDROID.USER_TYP");
                    else
                        __sJT_st.setORAData(1, __jPt_temp);
                    // execute statement
                    __sJT_ec.oracleExecuteUpdate();
                    // retrieve OUT parameters
                    __jPt_temp =
                            (uclm.esi.cardroid.data.oracle.User)__sJT_st.getORAData(1,
                                                                                    uclm.esi.cardroid.data.oracle.User.getORADataFactory());
                } finally {
                    __sJT_ec.oracleClose();
                }
            }


            //  ************************************************************

            /*@lineinfo:user-code*/ /*@lineinfo:269^5*/
            return __jPt_temp;
        } catch (java.sql.SQLException _err) {
            try {
                getConnectionContext().getExecutionContext().close();
                closeConnection();
                if (__dataSource == null)
                    throw _err;
                User __jPt_temp = (User)this;
                /*@lineinfo:generated-code*/ /*@lineinfo:277^5*/

                //  ************************************************************
                //  #sql [getConnectionContext()] { BEGIN
                //        :__jPt_temp.DECREASE_REPUTATION();
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
                            "BEGIN\n       :1  .DECREASE_REPUTATION();\n      END;";
                        __sJT_st =
                                __sJT_ec.prepareOracleCall(__sJT_cc, "5uclm.esi.cardroid.data.oracle.UserTyp",
                                                           theSqlTS);
                        if (__sJT_ec.isNew()) {
                            __sJT_st.registerOutParameter(1, 2002,
                                                          "ANDROID.USER_TYP");
                        }
                        // set IN parameters
                        if (__jPt_temp == null)
                            __sJT_st.setNull(1, 2002, "ANDROID.USER_TYP");
                        else
                            __sJT_st.setORAData(1, __jPt_temp);
                        // execute statement
                        __sJT_ec.oracleExecuteUpdate();
                        // retrieve OUT parameters
                        __jPt_temp =
                                (uclm.esi.cardroid.data.oracle.User)__sJT_st.getORAData(1,
                                                                                        uclm.esi.cardroid.data.oracle.User.getORADataFactory());
                    } finally {
                        __sJT_ec.oracleClose();
                    }
                }


                //  ************************************************************

                /*@lineinfo:user-code*/ /*@lineinfo:281^5*/
                return __jPt_temp;
            } catch (java.sql.SQLException _err2) {
                try {
                    getConnectionContext().getExecutionContext().close();
                } catch (java.sql.SQLException _sqle) {
                }
                throw _err;
            }
        }
    }

    public User decreaseUserReputation(int DECREASE) throws java.sql.SQLException {
        try {
            User __jPt_temp = (User)this;
            /*@lineinfo:generated-code*/ /*@lineinfo:296^5*/

            //  ************************************************************
            //  #sql [getConnectionContext()] { BEGIN
            //        :__jPt_temp.DECREASE_REPUTATION(
            //        :DECREASE);
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
                        "BEGIN\n       :1  .DECREASE_REPUTATION(\n       :2  );\n      END;";
                    __sJT_st =
                            __sJT_ec.prepareOracleCall(__sJT_cc, "6uclm.esi.cardroid.data.oracle.UserTyp",
                                                       theSqlTS);
                    if (__sJT_ec.isNew()) {
                        __sJT_st.registerOutParameter(1, 2002,
                                                      "ANDROID.USER_TYP");
                    }
                    // set IN parameters
                    if (__jPt_temp == null)
                        __sJT_st.setNull(1, 2002, "ANDROID.USER_TYP");
                    else
                        __sJT_st.setORAData(1, __jPt_temp);
                    __sJT_st.setInt(2, DECREASE);
                    // execute statement
                    __sJT_ec.oracleExecuteUpdate();
                    // retrieve OUT parameters
                    __jPt_temp =
                            (uclm.esi.cardroid.data.oracle.User)__sJT_st.getORAData(1,
                                                                                    uclm.esi.cardroid.data.oracle.User.getORADataFactory());
                } finally {
                    __sJT_ec.oracleClose();
                }
            }


            //  ************************************************************

            /*@lineinfo:user-code*/ /*@lineinfo:301^5*/
            return __jPt_temp;
        } catch (java.sql.SQLException _err) {
            try {
                getConnectionContext().getExecutionContext().close();
                closeConnection();
                if (__dataSource == null)
                    throw _err;
                User __jPt_temp = (User)this;
                /*@lineinfo:generated-code*/ /*@lineinfo:309^5*/

                //  ************************************************************
                //  #sql [getConnectionContext()] { BEGIN
                //        :__jPt_temp.DECREASE_REPUTATION(
                //        :DECREASE);
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
                            "BEGIN\n       :1  .DECREASE_REPUTATION(\n       :2  );\n      END;";
                        __sJT_st =
                                __sJT_ec.prepareOracleCall(__sJT_cc, "7uclm.esi.cardroid.data.oracle.UserTyp",
                                                           theSqlTS);
                        if (__sJT_ec.isNew()) {
                            __sJT_st.registerOutParameter(1, 2002,
                                                          "ANDROID.USER_TYP");
                        }
                        // set IN parameters
                        if (__jPt_temp == null)
                            __sJT_st.setNull(1, 2002, "ANDROID.USER_TYP");
                        else
                            __sJT_st.setORAData(1, __jPt_temp);
                        __sJT_st.setInt(2, DECREASE);
                        // execute statement
                        __sJT_ec.oracleExecuteUpdate();
                        // retrieve OUT parameters
                        __jPt_temp =
                                (uclm.esi.cardroid.data.oracle.User)__sJT_st.getORAData(1,
                                                                                        uclm.esi.cardroid.data.oracle.User.getORADataFactory());
                    } finally {
                        __sJT_ec.oracleClose();
                    }
                }


                //  ************************************************************

                /*@lineinfo:user-code*/ /*@lineinfo:314^5*/
                return __jPt_temp;
            } catch (java.sql.SQLException _err2) {
                try {
                    getConnectionContext().getExecutionContext().close();
                } catch (java.sql.SQLException _sqle) {
                }
                throw _err;
            }
        }
    }

    public int getNCars() throws java.sql.SQLException {
        UserTyp __jPt_temp = this;
        int __jPt_result;
        try {
            /*@lineinfo:generated-code*/ /*@lineinfo:329^5*/

            //  ************************************************************
            //  #sql [getConnectionContext()] { BEGIN
            //        :__jPt_result := :__jPt_temp.GET_N_CARS();
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
                        "BEGIN\n       :1   :=  :2  .GET_N_CARS();\n      END;";
                    __sJT_st =
                            __sJT_ec.prepareOracleCall(__sJT_cc, "8uclm.esi.cardroid.data.oracle.UserTyp",
                                                       theSqlTS);
                    if (__sJT_ec.isNew()) {
                        __sJT_st.registerOutParameter(1,
                                                      oracle.jdbc.OracleTypes.INTEGER);
                    }
                    // set IN parameters
                    if (__jPt_temp == null)
                        __sJT_st.setNull(2, 2002, "ANDROID.USER_TYP");
                    else
                        __sJT_st.setORAData(2, __jPt_temp);
                    // execute statement
                    __sJT_ec.oracleExecuteUpdate();
                    // retrieve OUT parameters
                    __jPt_result = __sJT_st.getInt(1);
                    if (__sJT_st.wasNull())
                        throw new sqlj.runtime.SQLNullException();
                } finally {
                    __sJT_ec.oracleClose();
                }
            }


            //  ************************************************************

            /*@lineinfo:user-code*/ /*@lineinfo:333^5*/
        } catch (java.sql.SQLException _err) {
            try {
                getConnectionContext().getExecutionContext().close();
                closeConnection();
                if (__dataSource == null)
                    throw _err;
                /*@lineinfo:generated-code*/ /*@lineinfo:339^5*/

                //  ************************************************************
                //  #sql [getConnectionContext()] { BEGIN
                //        :__jPt_result := :__jPt_temp.GET_N_CARS();
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
                            "BEGIN\n       :1   :=  :2  .GET_N_CARS();\n      END;";
                        __sJT_st =
                                __sJT_ec.prepareOracleCall(__sJT_cc, "9uclm.esi.cardroid.data.oracle.UserTyp",
                                                           theSqlTS);
                        if (__sJT_ec.isNew()) {
                            __sJT_st.registerOutParameter(1,
                                                          oracle.jdbc.OracleTypes.INTEGER);
                        }
                        // set IN parameters
                        if (__jPt_temp == null)
                            __sJT_st.setNull(2, 2002, "ANDROID.USER_TYP");
                        else
                            __sJT_st.setORAData(2, __jPt_temp);
                        // execute statement
                        __sJT_ec.oracleExecuteUpdate();
                        // retrieve OUT parameters
                        __jPt_result = __sJT_st.getInt(1);
                        if (__sJT_st.wasNull())
                            throw new sqlj.runtime.SQLNullException();
                    } finally {
                        __sJT_ec.oracleClose();
                    }
                }


                //  ************************************************************

                /*@lineinfo:user-code*/ /*@lineinfo:343^5*/
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

    public User increaseUserReputation(int INCREASE) throws java.sql.SQLException {
        try {
            User __jPt_temp = (User)this;
            /*@lineinfo:generated-code*/ /*@lineinfo:358^5*/

            //  ************************************************************
            //  #sql [getConnectionContext()] { BEGIN
            //        :__jPt_temp.INCREASE_REPUTATION(
            //        :INCREASE);
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
                        "BEGIN\n       :1  .INCREASE_REPUTATION(\n       :2  );\n      END;";
                    __sJT_st =
                            __sJT_ec.prepareOracleCall(__sJT_cc, "10uclm.esi.cardroid.data.oracle.UserTyp",
                                                       theSqlTS);
                    if (__sJT_ec.isNew()) {
                        __sJT_st.registerOutParameter(1, 2002,
                                                      "ANDROID.USER_TYP");
                    }
                    // set IN parameters
                    if (__jPt_temp == null)
                        __sJT_st.setNull(1, 2002, "ANDROID.USER_TYP");
                    else
                        __sJT_st.setORAData(1, __jPt_temp);
                    __sJT_st.setInt(2, INCREASE);
                    // execute statement
                    __sJT_ec.oracleExecuteUpdate();
                    // retrieve OUT parameters
                    __jPt_temp =
                            (uclm.esi.cardroid.data.oracle.User)__sJT_st.getORAData(1,
                                                                                    uclm.esi.cardroid.data.oracle.User.getORADataFactory());
                } finally {
                    __sJT_ec.oracleClose();
                }
            }


            //  ************************************************************

            /*@lineinfo:user-code*/ /*@lineinfo:363^5*/
            return __jPt_temp;
        } catch (java.sql.SQLException _err) {
            try {
                getConnectionContext().getExecutionContext().close();
                closeConnection();
                if (__dataSource == null)
                    throw _err;
                User __jPt_temp = (User)this;
                /*@lineinfo:generated-code*/ /*@lineinfo:371^5*/

                //  ************************************************************
                //  #sql [getConnectionContext()] { BEGIN
                //        :__jPt_temp.INCREASE_REPUTATION(
                //        :INCREASE);
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
                            "BEGIN\n       :1  .INCREASE_REPUTATION(\n       :2  );\n      END;";
                        __sJT_st =
                                __sJT_ec.prepareOracleCall(__sJT_cc, "11uclm.esi.cardroid.data.oracle.UserTyp",
                                                           theSqlTS);
                        if (__sJT_ec.isNew()) {
                            __sJT_st.registerOutParameter(1, 2002,
                                                          "ANDROID.USER_TYP");
                        }
                        // set IN parameters
                        if (__jPt_temp == null)
                            __sJT_st.setNull(1, 2002, "ANDROID.USER_TYP");
                        else
                            __sJT_st.setORAData(1, __jPt_temp);
                        __sJT_st.setInt(2, INCREASE);
                        // execute statement
                        __sJT_ec.oracleExecuteUpdate();
                        // retrieve OUT parameters
                        __jPt_temp =
                                (uclm.esi.cardroid.data.oracle.User)__sJT_st.getORAData(1,
                                                                                        uclm.esi.cardroid.data.oracle.User.getORADataFactory());
                    } finally {
                        __sJT_ec.oracleClose();
                    }
                }


                //  ************************************************************

                /*@lineinfo:user-code*/ /*@lineinfo:376^5*/
                return __jPt_temp;
            } catch (java.sql.SQLException _err2) {
                try {
                    getConnectionContext().getExecutionContext().close();
                } catch (java.sql.SQLException _sqle) {
                }
                throw _err;
            }
        }
    }

    public User increaseUserReputation() throws java.sql.SQLException {
        try {
            User __jPt_temp = (User)this;
            /*@lineinfo:generated-code*/ /*@lineinfo:390^5*/

            //  ************************************************************
            //  #sql [getConnectionContext()] { BEGIN
            //        :__jPt_temp.INCREASE_REPUTATION();
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
                        "BEGIN\n       :1  .INCREASE_REPUTATION();\n      END;";
                    __sJT_st =
                            __sJT_ec.prepareOracleCall(__sJT_cc, "12uclm.esi.cardroid.data.oracle.UserTyp",
                                                       theSqlTS);
                    if (__sJT_ec.isNew()) {
                        __sJT_st.registerOutParameter(1, 2002,
                                                      "ANDROID.USER_TYP");
                    }
                    // set IN parameters
                    if (__jPt_temp == null)
                        __sJT_st.setNull(1, 2002, "ANDROID.USER_TYP");
                    else
                        __sJT_st.setORAData(1, __jPt_temp);
                    // execute statement
                    __sJT_ec.oracleExecuteUpdate();
                    // retrieve OUT parameters
                    __jPt_temp =
                            (uclm.esi.cardroid.data.oracle.User)__sJT_st.getORAData(1,
                                                                                    uclm.esi.cardroid.data.oracle.User.getORADataFactory());
                } finally {
                    __sJT_ec.oracleClose();
                }
            }


            //  ************************************************************

            /*@lineinfo:user-code*/ /*@lineinfo:394^5*/
            return __jPt_temp;
        } catch (java.sql.SQLException _err) {
            try {
                getConnectionContext().getExecutionContext().close();
                closeConnection();
                if (__dataSource == null)
                    throw _err;
                User __jPt_temp = (User)this;
                /*@lineinfo:generated-code*/ /*@lineinfo:402^5*/

                //  ************************************************************
                //  #sql [getConnectionContext()] { BEGIN
                //        :__jPt_temp.INCREASE_REPUTATION();
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
                            "BEGIN\n       :1  .INCREASE_REPUTATION();\n      END;";
                        __sJT_st =
                                __sJT_ec.prepareOracleCall(__sJT_cc, "13uclm.esi.cardroid.data.oracle.UserTyp",
                                                           theSqlTS);
                        if (__sJT_ec.isNew()) {
                            __sJT_st.registerOutParameter(1, 2002,
                                                          "ANDROID.USER_TYP");
                        }
                        // set IN parameters
                        if (__jPt_temp == null)
                            __sJT_st.setNull(1, 2002, "ANDROID.USER_TYP");
                        else
                            __sJT_st.setORAData(1, __jPt_temp);
                        // execute statement
                        __sJT_ec.oracleExecuteUpdate();
                        // retrieve OUT parameters
                        __jPt_temp =
                                (uclm.esi.cardroid.data.oracle.User)__sJT_st.getORAData(1,
                                                                                        uclm.esi.cardroid.data.oracle.User.getORADataFactory());
                    } finally {
                        __sJT_ec.oracleClose();
                    }
                }


                //  ************************************************************

                /*@lineinfo:user-code*/ /*@lineinfo:406^5*/
                return __jPt_temp;
            } catch (java.sql.SQLException _err2) {
                try {
                    getConnectionContext().getExecutionContext().close();
                } catch (java.sql.SQLException _sqle) {
                }
                throw _err;
            }
        }
    }

    public User removeCar(Car CAR) throws java.sql.SQLException {
        try {
            User __jPt_temp = (User)this;
            /*@lineinfo:generated-code*/ /*@lineinfo:421^5*/

            //  ************************************************************
            //  #sql [getConnectionContext()] { BEGIN
            //        :__jPt_temp.REMOVE_CAR(
            //        :CAR);
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
                        "BEGIN\n       :1  .REMOVE_CAR(\n       :2  );\n      END;";
                    __sJT_st =
                            __sJT_ec.prepareOracleCall(__sJT_cc, "14uclm.esi.cardroid.data.oracle.UserTyp",
                                                       theSqlTS);
                    if (__sJT_ec.isNew()) {
                        __sJT_st.registerOutParameter(1, 2002,
                                                      "ANDROID.USER_TYP");
                    }
                    // set IN parameters
                    if (__jPt_temp == null)
                        __sJT_st.setNull(1, 2002, "ANDROID.USER_TYP");
                    else
                        __sJT_st.setORAData(1, __jPt_temp);
                    if (CAR == null)
                        __sJT_st.setNull(2, 2002, "ANDROID.CAR_TYP");
                    else
                        __sJT_st.setORAData(2, CAR);
                    // execute statement
                    __sJT_ec.oracleExecuteUpdate();
                    // retrieve OUT parameters
                    __jPt_temp =
                            (uclm.esi.cardroid.data.oracle.User)__sJT_st.getORAData(1,
                                                                                    uclm.esi.cardroid.data.oracle.User.getORADataFactory());
                } finally {
                    __sJT_ec.oracleClose();
                }
            }


            //  ************************************************************

            /*@lineinfo:user-code*/ /*@lineinfo:426^5*/
            return __jPt_temp;
        } catch (java.sql.SQLException _err) {
            try {
                getConnectionContext().getExecutionContext().close();
                closeConnection();
                if (__dataSource == null)
                    throw _err;
                User __jPt_temp = (User)this;
                /*@lineinfo:generated-code*/ /*@lineinfo:434^5*/

                //  ************************************************************
                //  #sql [getConnectionContext()] { BEGIN
                //        :__jPt_temp.REMOVE_CAR(
                //        :CAR);
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
                            "BEGIN\n       :1  .REMOVE_CAR(\n       :2  );\n      END;";
                        __sJT_st =
                                __sJT_ec.prepareOracleCall(__sJT_cc, "15uclm.esi.cardroid.data.oracle.UserTyp",
                                                           theSqlTS);
                        if (__sJT_ec.isNew()) {
                            __sJT_st.registerOutParameter(1, 2002,
                                                          "ANDROID.USER_TYP");
                        }
                        // set IN parameters
                        if (__jPt_temp == null)
                            __sJT_st.setNull(1, 2002, "ANDROID.USER_TYP");
                        else
                            __sJT_st.setORAData(1, __jPt_temp);
                        if (CAR == null)
                            __sJT_st.setNull(2, 2002, "ANDROID.CAR_TYP");
                        else
                            __sJT_st.setORAData(2, CAR);
                        // execute statement
                        __sJT_ec.oracleExecuteUpdate();
                        // retrieve OUT parameters
                        __jPt_temp =
                                (uclm.esi.cardroid.data.oracle.User)__sJT_st.getORAData(1,
                                                                                        uclm.esi.cardroid.data.oracle.User.getORADataFactory());
                    } finally {
                        __sJT_ec.oracleClose();
                    }
                }


                //  ************************************************************

                /*@lineinfo:user-code*/ /*@lineinfo:439^5*/
                return __jPt_temp;
            } catch (java.sql.SQLException _err2) {
                try {
                    getConnectionContext().getExecutionContext().close();
                } catch (java.sql.SQLException _sqle) {
                }
                throw _err;
            }
        }
    }

    public User userTyp(String NAME, String SURNAME, int TELEPHONE,
                        String EMAIL,
                        User[] __jPt_out) throws java.sql.SQLException {
        User __jPt_temp = (User)this;
        User __jPt_result;
        try {
            /*@lineinfo:generated-code*/ /*@lineinfo:459^5*/

            //  ************************************************************
            //  #sql [getConnectionContext()] { BEGIN
            //        :__jPt_result := USER_TYP(
            //        :NAME,
            //        :SURNAME,
            //        :TELEPHONE,
            //        :EMAIL);
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
                        "BEGIN\n       :1   := USER_TYP(\n       :2  ,\n       :3  ,\n       :4  ,\n       :5  );\n      END;";
                    __sJT_st =
                            __sJT_ec.prepareOracleCall(__sJT_cc, "16uclm.esi.cardroid.data.oracle.UserTyp",
                                                       theSqlTS);
                    if (__sJT_ec.isNew()) {
                        __sJT_st.registerOutParameter(1, 2002,
                                                      "ANDROID.USER_TYP");
                    }
                    // set IN parameters
                    __sJT_st.setString(2, NAME);
                    __sJT_st.setString(3, SURNAME);
                    __sJT_st.setInt(4, TELEPHONE);
                    __sJT_st.setString(5, EMAIL);
                    // execute statement
                    __sJT_ec.oracleExecuteUpdate();
                    // retrieve OUT parameters
                    __jPt_result =
                            (uclm.esi.cardroid.data.oracle.User)__sJT_st.getORAData(1,
                                                                                    uclm.esi.cardroid.data.oracle.User.getORADataFactory());
                } finally {
                    __sJT_ec.oracleClose();
                }
            }


            //  ************************************************************

            /*@lineinfo:user-code*/ /*@lineinfo:467^5*/
            __jPt_out[0] = __jPt_temp;
        } catch (java.sql.SQLException _err) {
            try {
                getConnectionContext().getExecutionContext().close();
                closeConnection();
                if (__dataSource == null)
                    throw _err;
                /*@lineinfo:generated-code*/ /*@lineinfo:474^5*/

                //  ************************************************************
                //  #sql [getConnectionContext()] { BEGIN
                //        :__jPt_result := USER_TYP(
                //        :NAME,
                //        :SURNAME,
                //        :TELEPHONE,
                //        :EMAIL);
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
                            "BEGIN\n       :1   := USER_TYP(\n       :2  ,\n       :3  ,\n       :4  ,\n       :5  );\n      END;";
                        __sJT_st =
                                __sJT_ec.prepareOracleCall(__sJT_cc, "17uclm.esi.cardroid.data.oracle.UserTyp",
                                                           theSqlTS);
                        if (__sJT_ec.isNew()) {
                            __sJT_st.registerOutParameter(1, 2002,
                                                          "ANDROID.USER_TYP");
                        }
                        // set IN parameters
                        __sJT_st.setString(2, NAME);
                        __sJT_st.setString(3, SURNAME);
                        __sJT_st.setInt(4, TELEPHONE);
                        __sJT_st.setString(5, EMAIL);
                        // execute statement
                        __sJT_ec.oracleExecuteUpdate();
                        // retrieve OUT parameters
                        __jPt_result =
                                (uclm.esi.cardroid.data.oracle.User)__sJT_st.getORAData(1,
                                                                                        uclm.esi.cardroid.data.oracle.User.getORADataFactory());
                    } finally {
                        __sJT_ec.oracleClose();
                    }
                }


                //  ************************************************************

                /*@lineinfo:user-code*/ /*@lineinfo:482^5*/
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