/*@lineinfo:filename=DateTyp*/ /*@lineinfo:user-code*/ /*@lineinfo:1^1*/package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Timestamp;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;

import sqlj.runtime.ref.DefaultContext;

import uclm.esi.cardroid.data.IDate;

/**
 * \class Date
 * Domain class implementing a Date for its storage and retrieval from an 
 * Oracle database
 */
public class Date extends DateTyp implements ORAData, ORADataFactory, IDate {

    public Date() {
        super();
    }

    public Date(Connection conn) throws SQLException {
        super(conn);
    }

    public Date(DefaultContext ctx) throws SQLException {
        super(ctx);
    }

    /**
	 * Default constructor
	 */
    public Date(java.sql.Timestamp datetime) {
        setDatetime(datetime);
    }
    
    /* ORADataFactory interface */
    
    private static final Date _DateFactory = new Date();

    public static ORADataFactory getORADataFactory() {
        return _DateFactory;
    }
    
    /* ORAData interface */

    protected ORAData createExact(Datum d, int sqlType) throws SQLException {
        return create(new Date(), d, sqlType);
    }

    /* IDate interface */

    public Date newInstance(IDate dateObject) {
        if (dateObject == null)
            return null;
        if (dateObject instanceof Date)
            return (Date)dateObject;
        return new Date(new Timestamp(dateObject.getTimeInMillis()));
    }

    public void setDatetime(Timestamp datetime) {
        try {
            super.setDatetime(datetime);
        } catch (SQLException sqle) {
            System.err.println("SLQException sqle: " + sqle.getSQLState());
        }
    }

    public Timestamp getDatetime() {
        Timestamp datetime = null;
        try {
            datetime = super.getDatetime();
        } catch (SQLException sqle) {
            System.err.println("SLQException sqle: " + sqle.getSQLState());
        }
        return datetime;
    }

    public void setTimeInMillis(long time) {
        try {
            super.setDatetime(new Timestamp(time));
        } catch (SQLException sqle) {
            System.err.println("SQLException: " + sqle.getSQLState());
        }
    }

    public long getTimeInMillis() {
        long time = 0;
        try {
            time = super.getDatetime().getTime();
        } catch (SQLException sqle) {
            System.err.println("SQLException: " + sqle.getSQLState());
        }
        return time;
    }

    /* superclass methods */

    @Override
    public java.sql.Timestamp compareTo() throws java.sql.SQLException {

        java.sql.Timestamp __jRt_0 = null;
        try {
            __jRt_0 = super.compareTo();

        } catch (java.sql.SQLException except) {
            except.printStackTrace();
            throw new java.sql.SQLException(except.getClass().getName() +
                                            ": " + except.getMessage());
        }
        return __jRt_0;
    }

    public DateRef getDateRef() throws SQLException {
        DateRef ref = new DateRef();
        ref.setValue(this);
        return ref;
    }

    public void setDateRef(DateRef ref) throws SQLException {
        ref.setValue(this);
    }
}/*@lineinfo:generated-code*/