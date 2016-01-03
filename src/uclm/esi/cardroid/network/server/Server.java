package uclm.esi.cardroid.network.server;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import uclm.esi.cardroid.data.ice.Car;
import uclm.esi.cardroid.data.ice.Date;
import uclm.esi.cardroid.data.ice.DateTime;
import uclm.esi.cardroid.data.ice.DateTimePrefs;
import uclm.esi.cardroid.data.ice.Message;
import uclm.esi.cardroid.data.ice.Passenger;
import uclm.esi.cardroid.data.ice.Place;
import uclm.esi.cardroid.data.ice.TripOffer;
import uclm.esi.cardroid.data.ice.TripRequest;
import uclm.esi.cardroid.data.ice.Trip;
import uclm.esi.cardroid.data.ice.UserActivity;
import uclm.esi.cardroid.data.ice.User;
import uclm.esi.cardroid.data.ice.Waypoint;
import Ice.Application;
import Ice.Logger;
import Ice.ObjectAdapter;
import Ice.Properties;
import IceStorm.TopicManagerPrx;
import IceStorm.TopicManagerPrxHelper;

/**
 * \class Server
 * CarDroid server main class.
 * It is an Ice Application, and initializes all the subsystems and components 
 * of the runtime needed for the server operation. At the time the Communicator
 * for the application is shutdown, it commands the initialized modules to 
 * perform the necessary cleanup before finishing the server execution in a 
 * controlled manner
 */
public class Server extends Application {

	public int run(String[] args) {
		args = communicator().getProperties().parseCommandLineOptions("JDBC",
				args);

		if (args.length > 0) {
			System.err.println(appName() + ": too many arguments");
			return 1;
		}

		Properties properties = communicator().getProperties();

		// Parse the database configuration properties from the config file
		String username = properties.getProperty("JDBC.Username");
		String password = properties.getProperty("JDBC.Password");
		String url = properties.getProperty("JDBC.Url");
		int nConnections = properties.getPropertyAsIntWithDefault(
				"JDBC.NumConnections", 8);
		if (nConnections < 1) {
			nConnections = 1;
		}

		// Create the database connections pool
		ConnectionPool pool = null;
		Logger logger = communicator().getLogger();

		try {
			pool = new ConnectionPool(logger, username, password, url,
					nConnections);
		} catch (SQLException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			pw.flush();
			System.err
					.println("Failed to create connection pool: SQLException:\n"
							+ sw.toString());
			return 1;
		}

		long timeout = properties.getPropertyAsIntWithDefault("SessionTimeout",
				240);

		// Create an object adapter
		ObjectAdapter adapter = communicator().createObjectAdapter(
				"CardroidServer");

		/* Add the servant locator for every object category to the object *
		 * adapter. The calls to the newly created servants are redirected *
		 * via a dispatch interceptor									   */
		adapter.addServantLocator(new PlaceLocatorI(), "place");
		adapter.addServantLocator(new CarLocatorI(), "car");
		adapter.addServantLocator(new UserLocatorI(adapter), "user");
		adapter.addServantLocator(new TripLocatorI(), "trip");
		adapter.addServantLocator(new TripOfferLocatorI(adapter), "trip_offer");
		adapter.addServantLocator(new TripRequestLocatorI(adapter),
				"trip_request");
		adapter.addServantLocator(new UserActivityLocatorI(adapter),
				"user_activity");
		adapter.addServantLocator(new MessageLocatorI(adapter), "message");

		/* Add the object factory for instantiating servants from proxies for *
		 * every datatype used, which distinguishes itself by the category of *
		 * the identity provided											  */
		communicator().addObjectFactory(new Car(), Car.ice_staticId());
		communicator().addObjectFactory(new Date(), Date.ice_staticId());
		communicator()
				.addObjectFactory(new DateTime(), DateTime.ice_staticId());
		communicator().addObjectFactory(new DateTimePrefs(),
				DateTimePrefs.ice_staticId());
		communicator().addObjectFactory(new Message(adapter),
				Message.ice_staticId());
		communicator().addObjectFactory(new Passenger(adapter),
				Passenger.ice_staticId());
		communicator().addObjectFactory(new Place(), Place.ice_staticId());
		communicator().addObjectFactory(new Trip(), Trip.ice_staticId());
		communicator().addObjectFactory(new TripOffer(adapter),
				TripOffer.ice_staticId());
		communicator().addObjectFactory(new TripRequest(adapter),
				TripRequest.ice_staticId());
		communicator().addObjectFactory(new User(adapter), User.ice_staticId());
		communicator().addObjectFactory(new UserActivity(adapter),
				UserActivity.ice_staticId());
		communicator()
				.addObjectFactory(new Waypoint(), Waypoint.ice_staticId());

		/* Retrieve the topic manager used for the publishing and  *
		 * subscription features by the IceStorm service		   */
		TopicManagerPrx topicManager = TopicManagerPrxHelper
				.checkedCast(communicator().propertyToProxy(
						"TopicManager.Proxy"));
		if (topicManager == null) {
			System.err.println("Invalid topic manager proxy");
			return 1;
		}

		SQLRequestContext.initialize(logger, pool, topicManager);

		// Create the thread responsible for destroying inactive sessions
		ReapThread reaper = new ReapThread(logger, topicManager, timeout);
		reaper.start();

		/* Add to the object adapter the servant responsible for creating user *
		 * sessions for those clients running on a platform capable to work    *
		 * altogether with the Glacier2 router. Also add to the object adapter *
		 * the PermissionsVerifier object servant used by Glacier2 for user    *
		 * authentication													   */
		adapter.add(new Glacier2SessionManagerI(topicManager, logger, reaper),
				communicator().stringToIdentity("CardroidSessionManager"));
		adapter.add(new PermissionsVerifierI(pool), communicator()
				.stringToIdentity("CardroidSessionVerifier"));
		
		/* Add to the object adapter the servant responsible for creating user *
		 * sessions for those clients running on a platform unable to work	   *
		 * altogether with Glacier2											   */
		if (properties.getPropertyAsIntWithDefault("SessionFactory", 0) > 0) {
			adapter.add(new SessionFactoryI(topicManager, logger, reaper,
					timeout), communicator().stringToIdentity("CardroidServer"));
		}

		// Everything ok, let's go
		adapter.activate();

		// Create another object adapter for the registration procedure
		ObjectAdapter registrationAdapter = communicator().createObjectAdapter(
				"CardroidRegistration");
		registrationAdapter.add(new RegistrationI(pool), communicator()
				.stringToIdentity("Registration"));
		registrationAdapter.activate();

		/* Wait until any signal interruption, then shutdown the *
		 * Communicator and perform the necessary cleanup		 */
		shutdownOnInterrupt();
		communicator().waitForShutdown();
		defaultInterrupt();

		/* Send the terminate command to the reaper thread and wait for it     * 
		 * to finish the user sessions and IceStorm topics destruction process */
		reaper.terminate();
		try {
			reaper.join();
		} catch (InterruptedException e) {
		}

		/* Send the destroy command to the database connections pool. This   *
		 * operation will return when every transaction in progress has been *
		 * commited or rolled back, and every connection in the pool has been *
		 * released and closed												 */
		pool.destroy();

		return 0;
	}

	public static void main(String[] args) {
		Server app = new Server();
		
		/* Start the server application, loading its configuration * 
		 * from the config file and the command-line arguments	   */
		int status = app.main("uclm.esi.cardroid.network.Server", args,
				"config.server");
		System.exit(status);
	}
}
