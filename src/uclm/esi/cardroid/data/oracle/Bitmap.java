package uclm.esi.cardroid.data.oracle;

import java.sql.Blob;
import java.sql.SQLException;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;

import uclm.esi.cardroid.data.IBitmap;

/**
 * \class Bitmap
 * Domain class implementing a Bitmap for its storage and retrieval from an 
 * Oracle database
 */
public class Bitmap extends BitmapTyp implements ORAData, ORADataFactory,
		IBitmap {

	public Bitmap() {
	}

	/**
	 * Default constructor
	 */
	public Bitmap(Blob bitmap, String compressFormatName, String configName,
			int density) {
		setBitmap(bitmap);
		setCompressFormat(IBitmap.CompressFormat.valueOf(compressFormatName));
		setConfig(IBitmap.Config.valueOf(configName));
		setDensity(density);
	}

	/* ORADataFactory interface */

	private static final Bitmap _BitmapFactory = new Bitmap();

	public static ORADataFactory getORADataFactory() {
		return _BitmapFactory;
	}

	/* ORAData interface */

	public ORAData create(Datum d, int sqlType) throws SQLException {
		return create(new Bitmap(), d, sqlType);
	}

	/* IBitmap interface */

	public Bitmap newInstance(IBitmap bitmapObject) {
		if (bitmapObject == null)
			return null;
		if (bitmapObject instanceof Bitmap)
			return (Bitmap) bitmapObject;
		Blob bitmap = bitmapObject.getBitmap();
		String compressFormatName = bitmapObject.getCompressFormat().name();
		String configName = bitmapObject.getConfig().name();
		int density = bitmapObject.getDensity();
		return new Bitmap(bitmap, compressFormatName, configName, density);
	}

	@Override
	public void setBitmap(Blob bitmap) {
		try {
			super.setBitmap(bitmap);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	@Override
	public Blob getBitmap() {
		Blob bitmap = null;
		try {
			bitmap = super.getBitmap();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return bitmap;
	}

	public void setCompressFormat(CompressFormat compressFormat) {
		try {
			super.setCompressFormat(compressFormat.name());
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public CompressFormat getCompressFormat() {
		CompressFormat compressFormat = null;
		try {
			compressFormat = IBitmap.CompressFormat.valueOf(super
					.getCompressFormatName());
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return compressFormat;
	}

	public void setConfig(Config config) {
		try {
			super.setConfig(config.name());
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public Config getConfig() {
		Config config = null;
		try {
			config = IBitmap.Config.valueOf(super.getConfigName());
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return config;
	}

	@Override
	public void setDensity(int density) {
		try {
			super.setDensity(density);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	@Override
	public int getDensity() {
		int density = 0;
		try {
			density = super.getDensity();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return density;
	}

	public BitmapRef getRef() throws SQLException {
		BitmapRef ref = new BitmapRef();
		ref.setValue(this);
		return ref;
	}

	public void setRef(BitmapRef ref) throws SQLException {
		ref.setValue(this);
	}

}
