package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;

import uclm.esi.cardroid.data.ILatLng;

/**
 * \class LatLng
 * Domain class implementing a LatLng for its storage and retrieval from an 
 * Oracle database
 */
public class LatLng extends LatLngTyp implements ORAData, ORADataFactory,
		ILatLng {

	public LatLng() {
		super();
	}

	/**
	 * Default constructor
	 */
	public LatLng(double latitude, double longitude) {
		setLatitude(latitude);
		setLongitude(longitude);
	}

	/* ORADataFactory interface */

	private static final LatLng _LatLngFactory = new LatLng();

	public static ORADataFactory getORADataFactory() {
		return _LatLngFactory;
	}

	/* ORAData interface */

	public ORAData create(Datum d, int sqlType) throws SQLException {
		return create(new LatLng(), d, sqlType);
	}

	/* ILatLng interface */

	public LatLng newInstance(ILatLng latLngObject) {
		if (latLngObject == null)
			return null;
		if (latLngObject instanceof LatLng)
			return (LatLng) latLngObject;
		return new LatLng(latLngObject.getLatitude(),
				latLngObject.getLongitude());
	}

	@Override
	public void setLatitude(double latitude) {
		try {
			super.setLatitude(latitude);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	@Override
	public double getLatitude() {
		double latitude = 0.;
		try {
			latitude = super.getLatitude();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return latitude;
	}

	@Override
	public void setLongitude(double longitude) {
		try {
			super.setLongitude(longitude);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	@Override
	public double getLongitude() {
		double longitude = 0.;
		try {
			longitude = super.getLongitude();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return longitude;
	}

	public LatLngRef getRef() throws SQLException {
		LatLngRef ref = new LatLngRef();

		ref.setValue(this);
		return ref;
	}

	public void setRef(LatLngRef ref) throws SQLException {
		ref.setValue(this);
	}
}
