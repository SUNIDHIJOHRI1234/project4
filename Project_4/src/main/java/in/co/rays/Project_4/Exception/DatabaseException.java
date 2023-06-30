package in.co.rays.Project_4.Exception;


/**
 * DatabaseException is propogated by DAO classes when an unhandled Database
 * exception occurred.
 * @author Darshan
 *
 */
public class DatabaseException extends Exception {

	public DatabaseException(String msg) {
		super(msg);
	}

}
