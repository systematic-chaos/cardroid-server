/*@lineinfo:filename=DateTime*//*@lineinfo:user-code*//*@lineinfo:1^1*/package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;
import sqlj.runtime.ref.DefaultContext;

public class DateTimeTyp extends Date implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "ANDROID.DATE_TIME_TYP";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected static int[] _sqlType =  { 91 };
  protected static ORADataFactory[] _factory = new ORADataFactory[1];
  protected static final DateTimeTyp _DateTimeTypFactory = new DateTimeTyp();

  public static ORADataFactory getORADataFactory()
  { return _DateTimeTypFactory; }
  static
  { _map.put("ANDROID.DATE_TIME_TYP", _DateTimeTypFactory); }

  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[1], _sqlType, _factory); }
  public DateTimeTyp()
  { _init_struct(true); __tx = DefaultContext.getDefaultContext(); }
  public DateTimeTyp(DefaultContext c) /*throws SQLException*/
  { _init_struct(true); __tx = c; }
  public DateTimeTyp(Connection c) /*throws SQLException*/
  { _init_struct(true); __onn = c; }
  public DateTimeTyp(java.sql.Timestamp datetime) throws SQLException
  {
    _init_struct(true);
    setDatetime(datetime);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    if (__tx!=null && __onn!=c) release();
    __onn = c;
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  public void setFrom(DateTimeTyp o) throws SQLException
  { setContextFrom(o); setValueFrom(o); }
  protected void setContextFrom(DateTimeTyp o) throws SQLException
  { release(); __tx = o.__tx; __onn = o.__onn; }
  protected void setValueFrom(DateTimeTyp o) { _struct = o._struct; }
  protected ORAData create(DateTimeTyp o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) { if (o!=null) { o.release(); }; return null; }
    if (o == null) o = new DateTime();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    o.__onn = ((STRUCT) d).getJavaSqlConnection();
    return o;
  }
  protected ORAData createExact(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }

  /* accessor methods */  public String toString()
  { try {
     return "ANDROID.DATE_TIME_TYP" + "(" +
       getDatetime() +
     ")";
    } catch (Exception e) { return e.toString(); }
  }


  public java.sql.Timestamp compareTo ()
  throws java.sql.SQLException
  {
    DateTyp __jPt_temp = (DateTyp) this;
    java.sql.Timestamp __jPt_result;
 try {
    /*@lineinfo:generated-code*//*@lineinfo:86^5*/

//  ************************************************************
//  #sql [getConnectionContext()] { BEGIN
//        :__jPt_result := :__jPt_temp.COMPARE_TO();
//        END;
//       };
//  ************************************************************

{
  // declare temps
  oracle.jdbc.OracleCallableStatement __sJT_st = null;
  sqlj.runtime.ref.DefaultContext __sJT_cc = getConnectionContext(); if (__sJT_cc==null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext.OracleContext __sJT_ec = ((__sJT_cc.getExecutionContext()==null) ? sqlj.runtime.ExecutionContext.raiseNullExecCtx() : __sJT_cc.getExecutionContext().getOracleContext());
  try {
   String theSqlTS = "BEGIN\n       :1   :=  :2  .COMPARE_TO();\n      END;";
   __sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,"0uclm.esi.cardroid.data.oracle.DateTimeTyp",theSqlTS);
   if (__sJT_ec.isNew())
   {
      __sJT_st.registerOutParameter(1,oracle.jdbc.OracleTypes.TIMESTAMP);
   }
   // set IN parameters
   if (__jPt_temp==null) __sJT_st.setNull(2,2002,"ANDROID.DATE_TYP"); else __sJT_st.setORAData(2,__jPt_temp);
  // execute statement
   __sJT_ec.oracleExecuteUpdate();
   // retrieve OUT parameters
   __jPt_result = (java.sql.Timestamp)__sJT_st.getTimestamp(1);
  } finally { __sJT_ec.oracleClose(); }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:90^5*/
 } catch(java.sql.SQLException _err) {
   try {
      getConnectionContext().getExecutionContext().close();
      closeConnection();
      if (__dataSource==null) throw _err;
    /*@lineinfo:generated-code*//*@lineinfo:96^5*/

//  ************************************************************
//  #sql [getConnectionContext()] { BEGIN
//        :__jPt_result := :__jPt_temp.COMPARE_TO();
//        END;
//       };
//  ************************************************************

{
  // declare temps
  oracle.jdbc.OracleCallableStatement __sJT_st = null;
  sqlj.runtime.ref.DefaultContext __sJT_cc = getConnectionContext(); if (__sJT_cc==null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext.OracleContext __sJT_ec = ((__sJT_cc.getExecutionContext()==null) ? sqlj.runtime.ExecutionContext.raiseNullExecCtx() : __sJT_cc.getExecutionContext().getOracleContext());
  try {
   String theSqlTS = "BEGIN\n       :1   :=  :2  .COMPARE_TO();\n      END;";
   __sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,"1uclm.esi.cardroid.data.oracle.DateTimeTyp",theSqlTS);
   if (__sJT_ec.isNew())
   {
      __sJT_st.registerOutParameter(1,oracle.jdbc.OracleTypes.TIMESTAMP);
   }
   // set IN parameters
   if (__jPt_temp==null) __sJT_st.setNull(2,2002,"ANDROID.DATE_TYP"); else __sJT_st.setORAData(2,__jPt_temp);
  // execute statement
   __sJT_ec.oracleExecuteUpdate();
   // retrieve OUT parameters
   __jPt_result = (java.sql.Timestamp)__sJT_st.getTimestamp(1);
  } finally { __sJT_ec.oracleClose(); }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:100^5*/
   } catch (java.sql.SQLException _err2) { 
     try { getConnectionContext().getExecutionContext().close(); } catch (java.sql.SQLException _sqle) {}
     throw _err; 
  }
 }
    return __jPt_result;
  }
}/*@lineinfo:generated-code*/