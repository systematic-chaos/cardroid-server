package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;

import oracle.jpub.runtime.MutableStruct;

public class TripOffer_PassengerTyp implements ORAData, ORADataFactory {
	public static final String _SQL_NAME = "ANDROID.PASSENGER_TYP";
	public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

	protected MutableStruct _struct;

	protected static int[] _sqlType = { 2002, 4 };
	protected static ORADataFactory[] _factory = new ORADataFactory[2];
	static {
		_factory[0] = User.getORADataFactory();
	}
	protected static final TripOffer_PassengerTyp _PassengerTypFactory = new TripOffer_PassengerTyp();

	public static ORADataFactory getORADataFactory() {
		return _PassengerTypFactory;
	}

	/* constructors */

	protected void _init_struct(boolean init) {
		if (init)
			_struct = new MutableStruct(new Object[2], _sqlType, _factory);
	}

	public TripOffer_PassengerTyp() {
		_init_struct(true);
	}

	public TripOffer_PassengerTyp(User usr, int nSeats) throws SQLException {
		_init_struct(true);
		setUsr(usr);
		setNSeats(nSeats);
	}

	/* ORAData interface */

	public Datum toDatum(Connection c) throws SQLException {
		return _struct.toDatum(c, _SQL_NAME);
	}

	/* ORADataFactory interface */

	public ORAData create(Datum d, int sqlType) throws SQLException {
		return create(null, d, sqlType);
	}

	protected ORAData create(TripOffer_PassengerTyp o, Datum d, int sqlType)
			throws SQLException {
		if (d == null)
			return null;
		if (o == null)
			o = new TripOffer_Passenger();
		o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
		return o;
	}

	/* accessor methods */

	public User getUsr() throws SQLException {
		return (User) _struct.getAttribute(0);
	}

	public void setUsr(User usr) throws SQLException {
		_struct.setAttribute(0, usr);
	}

	public int getNSeats() throws SQLException {
		return ((Integer) _struct.getAttribute(1)).intValue();
	}

	public void setNSeats(int nSeats) throws SQLException {
		_struct.setAttribute(1, new Integer(nSeats));
	}

	public String toString() {
		try {
			return "ANDROID.PASSENGER_TYP" + "(" + getUsr() + "," + getNSeats()
					+ ")";
		} catch (Exception e) {
			return e.toString();
		}
	}

}
