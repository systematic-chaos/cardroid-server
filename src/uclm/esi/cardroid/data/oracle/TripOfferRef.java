package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;

public class TripOfferRef implements ORAData, ORADataFactory {
	public static final String _SQL_BASETYPE = "ANDROID.TRIP_OFFER_TYP";
	public static final int _SQL_TYPECODE = OracleTypes.REF;

	REF _ref;

	private static final TripOfferRef _TripOfferRefFactory = new TripOfferRef();

	public static ORADataFactory getORADataFactory() {
		return _TripOfferRefFactory;
	}

	/* constructor */

	public TripOfferRef() {
	}

	/* ORAData interface */

	public Datum toDatum(Connection c) throws SQLException {
		return _ref;
	}

	/* ORADataFactory interface */

	public ORAData create(Datum d, int sqlType) throws SQLException {
		if (d == null)
			return null;
		TripOfferRef r = new TripOfferRef();
		r._ref = (REF) d;
		return r;
	}

	public static TripOfferRef cast(ORAData o) throws SQLException {
		if (o == null)
			return null;
		try {
			return (TripOfferRef) getORADataFactory().create(o.toDatum(null),
					OracleTypes.REF);
		} catch (Exception exn) {
			throw new SQLException("Unable to convert "
					+ o.getClass().getName() + " to TripOfferRef: "
					+ exn.toString());
		}
	}

	public TripOffer getValue() throws SQLException {
		return (TripOffer) TripOffer.getORADataFactory().create(
				_ref.getSTRUCT(), OracleTypes.REF);
	}

	public void setValue(TripOffer c) throws SQLException {
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
