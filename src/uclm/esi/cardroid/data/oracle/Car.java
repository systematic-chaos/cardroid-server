package uclm.esi.cardroid.data.oracle;

import java.sql.SQLException;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;

import uclm.esi.cardroid.data.ICar;

/**
 * \class Car
 * Domain class implementing a Car for its storage and retrieval from an 
 * Oracle database
 */
public class Car extends CarTyp implements ORAData, ORADataFactory, ICar {

	public Car() {
	}

	/**
	 * Default constructor
	 */
	public Car(String brand, String model, String fuelName,
			double consumptionPerKm, int nSeats, String color, String plate) {
		setBrand(brand);
		setModel(model);
		setFuel(ICar.Fuel.valueOf(fuelName));
		setConsumptionPerKm(consumptionPerKm);
		setNSeats(nSeats);
		setColor(color);
		setPlate(plate);
	}
	
	/* ORADataFactory interface */
	
	private static final Car _CarFactory = new Car();

	public static ORADataFactory getORADataFactory() {
		return _CarFactory;
	}

	/* ORAData interface */

	public ORAData create(Datum d, int sqlType) throws SQLException {
		return create(new Car(), d, sqlType);
	}

	/* ICar interface */

	public Car newInstance(ICar carObject) {
		if (carObject == null)
			return null;
		if (carObject instanceof Car)
			return (Car) carObject;
		String brand = carObject.getBrand();
		String model = carObject.getModel();
		String fuelName = carObject.getFuel().name();
		double consumptionPerKm = carObject.getConsumptionPerKm();
		int nSeats = carObject.getNSeats();
		String color = carObject.getColor();
		String plate = carObject.getPlate();
		return new Car(brand, model, fuelName, consumptionPerKm, nSeats, color,
				plate);
	}

	@Override
	public void setBrand(String brand) {
		try {
			super.setBrand(brand);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	@Override
	public String getBrand() {
		String brand = null;
		try {
			brand = super.getBrand();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return brand;
	}

	@Override
	public void setModel(String model) {
		try {
			super.setModel(model);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	@Override
	public String getModel() {
		String model = null;
		try {
			model = super.getModel();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return model;
	}

	public void setFuel(Fuel fuel) {
		try {
			super.setFuel(fuel.name());
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	public Fuel getFuel() {
		Fuel fuel = null;
		try {
			fuel = ICar.Fuel.valueOf(super.getFuelName());
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return fuel;
	}

	@Override
	public void setConsumptionPerKm(double consumptionPerKm) {
		try {
			super.setConsumptionPerKm(consumptionPerKm);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	@Override
	public double getConsumptionPerKm() {
		double consumption = 0.;
		try {
			consumption = super.getConsumptionPerKm();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return consumption;
	}

	@Override
	public void setNSeats(int nSeats) {
		try {
			super.setNSeats(nSeats);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	@Override
	public int getNSeats() {
		int nSeats = 0;
		try {
			nSeats = super.getNSeats();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return nSeats;
	}

	@Override
	public void setColor(String color) {
		try {
			super.setColor(color);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	@Override
	public String getColor() {
		String color = null;
		try {
			color = super.getColor();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return color;
	}

	@Override
	public void setPlate(String plate) {
		try {
			super.setPlate(plate);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
	}

	@Override
	public String getPlate() {
		String plate = null;
		try {
			plate = super.getPlate();
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getSQLState());
		}
		return plate;
	}

	public CarRef getRef() throws SQLException {
		CarRef ref = new CarRef();
		ref.setValue(this);
		return ref;
	}

	public void setRef(CarRef ref) throws SQLException {
		ref.setValue(this);
	}
}
