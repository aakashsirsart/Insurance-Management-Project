package in.co.insurance.mgt.exception;

/**
 * DatabaseException is propagated by DAO classes when an unhandled Database
 * exception occurred
 */

public class DatabaseException  extends Exception
{
	/**
    * @param msg
    *            : Error message
    */
   public DatabaseException(String msg) {
       super(msg);
   }
}

