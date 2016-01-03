/*@lineinfo:filename=DateTimeTypPrefs*//*@lineinfo:user-code*//*@lineinfo:1^1*/package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Timestamp;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;

import sqlj.runtime.ref.DefaultContext;

import uclm.esi.cardroid.data.IDateTimePrefs;

/**
 * \class DateTimePrefs
 * Domain class implementing a DateTimePrefs for its storage and retrieval 
 * from an Oracle database
 */
public class DateTimePrefs extends DateTimePrefsTyp implements ORAData,
		ORADataFactory, IDateTimePrefs {

	public DateTimePrefs() {
		super();
	}

	public DateTimePrefs(Connection conn) throws SQLException {
		super(conn);
	}

	public DateTimePrefs(DefaultContext ctx) throws SQLException {
		super(ctx);
	}

	/**
	 * Default constructor
	 */
	public DateTimePrefs(java.sql.Timestamp datetime, int toleranceDays,
			String timePreferences) {
		setDatetime(datetime);
		setToleranceDays(toleranceDays);
		setTimePreferences(TimePreferences.valueOf(timePreferences));
	}

	/* ORADataFactory interface */

	private static final DateTimePrefs _DateTimePrefsFactory = new DateTimePrefs();

	public static ORADataFactory getORADataFactory() {
		return _DateTimePrefsFactory;
	}

	/* ORAData interface */

	protected ORAData createExact(Datum d, int sqlType) throws SQLException {
		return create(new DateTimePrefs(), d, sqlType);
	}

	/* IDateTimePrefs interface */

	public DateTimePrefs newInstance(IDateTimePrefs dateTimePrefsObject) {
		if (dateTimePrefsObject == null)
			return null;
		if (dateTimePrefsObject instanceof DateTimePrefs)
			return (DateTimePrefs) dateTimePrefsObject;
		Timestamp datetime = new Timestamp(
				dateTimePrefsObject.getTimeInMillis());
		int toleranceDays = dateTimePrefsObject.getToleranceDays();
		String timePreferencesName = dateTimePrefsObject.getTimePreferences()
				.name();
		return new DateTimePrefs(datetime, toleranceDays, timePreferencesName);
	}

	@Override
	public void setToleranceDays(int toleranceDays) {
		try {
			super.setToleranceDays(toleranceDays);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	@Override
	public int getToleranceDays() {
		int toleranceDays = 0;
		try {
			toleranceDays = super.getToleranceDays();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return toleranceDays;
	}

	public void setTimePreferences(TimePreferences timePreferences) {
		try {
			super.setTimePreferences(timePreferences.name());
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public TimePreferences getTimePreferences() {
		TimePreferences timePreferences = null;
		try {
			timePreferences = IDateTimePrefs.TimePreferences.valueOf(super
					.getTimePreferencesName());
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return timePreferences;
	}

	/* superclass methods */

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
	
	public DateTimePrefsRef getDateTimePrefsRef() throws SQLException {
		DateTimePrefsRef ref = new DateTimePrefsRef();
		ref.setValue(this);
		return ref;
	}

	public void setDateTimePrefsRef(DateTimePrefsRef ref) throws SQLException {
		ref.setValue(this);
	}
}/* @lineinfo:generated-code */