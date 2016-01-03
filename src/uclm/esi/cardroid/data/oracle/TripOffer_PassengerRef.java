package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;

public class TripOffer_PassengerRef implements ORAData, ORADataFactory {
	public static final String _SQL_BASETYPE = "ANDROID.PASSENGER_TYP";
	public static final int _SQL_TYPECODE = OracleTypes.REF;

	REF _ref;

	private static final TripOffer_PassengerRef _PassengerRefFactory = new TripOffer_PassengerRef();

	public static ORADataFactory getORADataFactory() {
		return _PassengerRefFactory;
	}

	/* constructor */

	public TripOffer_PassengerRef() {
	}

	/* ORAData interface */

	public Datum toDatum(Connection c) throws SQLException {
		return _ref;
	}

	/* ORADataFactory interface */

	public ORAData create(Datum d, int sqlType) throws SQLException {
		if (d == null)
			return null;
		TripOffer_PassengerRef r = new TripOffer_PassengerRef();
		r._ref = (REF) d;
		return r;
	}

	public static TripOffer_PassengerRef cast(ORAData o) throws SQLException {
		if (o == null)
			return null;
		try {
			return (TripOffer_PassengerRef) getORADataFactory().create(o.toDatum(null),
					OracleTypes.REF);
		} catch (Exception exn) {
			throw new SQLException("Unable to convert "
					+ o.getClass().getName() + " to TripOffer_PassengerRef: "
					+ exn.toString());
		}
	}

	public TripOffer_Passenger getValue() throws SQLException {
		return (TripOffer_Passenger) TripOffer_Passenger.getORADataFactory().create(
				_ref.getSTRUCT(), OracleTypes.REF);
	}

	public void setValue(TripOffer_Passenger c) throws SQLException {
		_ref.setValue(c.toDatum(_ref.getJavaSqlConnection()));
	}

	public String toString() {
		try {
			return "REF " + _ref.getBaseTypeName() + "(" + _ref + ")";
		} catch (SQLException e) {
			return e.toString();
		}
	}

}
