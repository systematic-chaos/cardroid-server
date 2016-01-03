package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class MessageTyp implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "ANDROID.MESSAGE_TYP";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2002,2002,12,93 };
  protected static ORADataFactory[] _factory = new ORADataFactory[4];
  static
  {
    _factory[0] = User.getORADataFactory();
    _factory[1] = User.getORADataFactory();
  }
  protected static final MessageTyp _MessageTypFactory = new MessageTyp();

  public static ORADataFactory getORADataFactory()
  { return _MessageTypFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[4], _sqlType, _factory); }
  public MessageTyp()
  { _init_struct(true); }
  public MessageTyp(User user1, User user2, String message, java.sql.Timestamp timeStamp) throws SQLException
  { _init_struct(true);
    setUser1(user1);
    setUser2(user2);
    setMessageText(message);
    setTimeStamp(timeStamp);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(MessageTyp o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new Message();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public User getUser1() throws SQLException
  { return (User) _struct.getAttribute(0); }

  public void setUser1(User user1) throws SQLException
  { _struct.setAttribute(0, user1); }


  public User getUser2() throws SQLException
  { return (User) _struct.getAttribute(1); }

  public void setUser2(User user2) throws SQLException
  { _struct.setAttribute(1, user2); }


  public String getMessageText() throws SQLException
  { return (String) _struct.getAttribute(2); }

  public void setMessageText(String message) throws SQLException
  { _struct.setAttribute(2, message); }


  public java.sql.Timestamp getTimeStamp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(3); }

  public void setTimeStamp(java.sql.Timestamp timeStamp) throws SQLException
  { _struct.setAttribute(3, timeStamp); }

  public String toString()
  { try {
     return "ANDROID.MESSAGE_TYP" + "(" +
       getUser1() + "," +
       getUser2() + "," +
       ((getMessageText()==null)?"null": "'" + getMessageText()+"'" ) + "," +
       getTimeStamp() +
     ")";
    } catch (Exception e) { return e.toString(); }
  }

}
