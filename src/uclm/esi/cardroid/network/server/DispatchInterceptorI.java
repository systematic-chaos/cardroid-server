package uclm.esi.cardroid.network.server;

import uclm.esi.cardroid.zerocice.JDBCException;
import Ice.Current;
import Ice.DispatchInterceptor;
import Ice.DispatchStatus;
import Ice.Request;
import Ice.UnknownException;

/**
 * \class DispatchInterceptorI
 * Requests interceptor. Is used to automatically commit or rollback requests 
 * forwarded to a servant, depending on the success of their completion
 */
public class DispatchInterceptorI extends DispatchInterceptor {
	private Ice.Object _servant;

	private static final long serialVersionUID = -3418216384163689271L;

	/**
	 * Default constructor
	 * @param servant The servant the intercepted requests will be forwarded to
	 */
	public DispatchInterceptorI(Ice.Object servant) {
		_servant = servant;
	}

	/**
	 * Called by the Ice run time to dispatch an incoming request. 
	 * Dispatches the request to the actual servant.
	 */
	public DispatchStatus dispatch(Request request) {
		// Allocate a new SQLRequestContext associated with this request thread
		SQLRequestContext context = new SQLRequestContext();
		try {
			DispatchStatus status = _servant.ice_dispatch(request);

			// An exception causes the currentEntry transaction to rollback
			context.destroyFromDispatch(status == DispatchStatus.DispatchOK);

			return status;
		} catch (JDBCException ex) {
			// Log the error
			Current c = request.getCurrent();
			context.error("Call of " + c.operation + " on id " + c.id.category
					+ "/" + c.id.name + " failed", ex);

			// A JDBCException causes the currentEntry transaction to rollback
			context.destroyFromDispatch(false);

			// Translate the exception to UnknownException
			UnknownException e = new UnknownException();
			e.initCause(ex);
			throw e;
		} catch (RuntimeException ex) {
			// Any other exception causes the transaction to rollback
			context.destroyFromDispatch(false);
			throw ex;
		}
	}
}
