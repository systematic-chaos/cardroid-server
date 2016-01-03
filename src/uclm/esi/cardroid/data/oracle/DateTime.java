/*@lineinfo:filename=DateTypTime*//*@lineinfo:user-code*//*@lineinfo:1^1*/package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Timestamp;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;

import sqlj.runtime.ref.DefaultContext;

import uclm.esi.cardroid.data.IDateTime;

/**
 * \class DateTime
 * Domain class implementing a DateTime for its storage and retrieval from an 
 * Oracle database
 */
public class DateTime extends DateTimeTyp implements ORAData, ORADataFactory,
		IDateTime {

	public DateTime() {
		super();
	}

	public DateTime(Connection conn) throws SQLException {
		super(conn);
	}

	public DateTime(DefaultContext ctx) throws SQLException {
		super(ctx);
	}

	/**
	 * Default constructor
	 */
	public DateTime(java.sql.Timestamp datetime) {
		setDatetime(datetime);
	}

	/* ORADataFactory interface */

	private static final DateTime _DateTimeFactory = new DateTime();

	public static ORADataFactory getORADataFactory() {
		return _DateTimeFactory;
	}

	/* ORAData interface */

	protected ORAData createExact(Datum d, int sqlType) throws SQLException {
		return create(new DateTime(), d, sqlType);
	}

	/* IDateTime interface */

	public DateTime newInstance(IDateTime dateTimeObject) {
		if (dateTimeObject == null)
			return null;
		if (dateTimeObject instanceof DateTime)
			return (DateTime) dateTimeObject;
		return new DateTime(new Timestamp(dateTimeObject.getTimeInMillis()));
	}

	/* superclass methods */

	@Override
	public java.sql.Timestamp compareTo() throws java.sql.SQLException {

		java.sql.Timestamp __jRt_0 = null;
		try {
			__jRt_0 = super.compareTo();

		} catch (java.sql.SQLException except) {
			except.printStackTrace();
			throw new java.sql.SQLException(except.getClass().getName() + ": "
					+ except.getMessage());
		}
		return __jRt_0;
	}

	public DateTimeRef getDateTimeRef() throws SQLException {
		DateTimeRef ref = new DateTimeRef();
		ref.setValue(this);
		return ref;
	}

	public void setDateTimeRef(DateTimeRef ref) throws SQLException {
		ref.setValue(this);
	}
}/* @lineinfo:generated-code */