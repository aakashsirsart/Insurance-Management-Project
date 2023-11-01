package in.co.insurance.mgt.exception;

/**
 * RecordNotFoundException thrown when a record not found occurred
 */

public class RecordNotFoundException extends Exception
{

	/**
	 * @param msg
	 * error message
	 */
	public RecordNotFoundException(String msg) {
		super(msg);

	}
}
