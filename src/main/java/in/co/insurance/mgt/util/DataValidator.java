package in.co.insurance.mgt.util;

import java.text.ParseException;
import java.util.Date;

/**
 * This class validates input data
 */

public class DataValidator {
	/**
	 * Checks if value is Null
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isName(String val) {

		String name = "^[A-Za-z ]*$";
		if (val.matches(name)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public static boolean isRollNO(String val) {
		String passregex = "^([0-9]{2}[A-Z]{2}[0-9]{1,})\\S$";

		if (val.matches(passregex)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if value is Password
	 * 
	 * @param val
	 * @return boolean
	 */
	public static boolean isPassword(String val) {
		String passregex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[\\S])[A-Za-z0-9\\S]{6,12}$";

		if (val.matches(passregex)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if value is Phone No
	 * 
	 * @param val
	 * @return boolean
	 */
	public static boolean isPhoneNo(String val) {
		String regex = "^[7-9][0-9]{9}$";
		if (val.matches(regex)) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean isAadhaarNo(String val) {
		String regex = "^\\d{12}$";
		if (val.matches(regex)) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean isPanNo(String val) {
		
		if (val.length()==10) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if value is Null
	 * 
	 * @param val
	 * @return boolean
	 */
	public static boolean isNull(String val) {
		if (val == null || val.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if value is NOT Null
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isNotNull(String val) {
		return !isNull(val);
	}

	/**
	 * Checks if value is an Integer
	 * 
	 * @param val
	 * @return
	 */

	public static boolean isInteger(String val) {

		if (isNotNull(val)) {
			try {
				int i = Integer.parseInt(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value is Long
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isLong(String val) {
		if (isNotNull(val)) {
			try {
				long i = Long.parseLong(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}
	/**
	 * Checks if value is Double
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isDouble(String val) {
		if (isNotNull(val)) {
			try {
				double i = Double.parseDouble(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	

	/**
	 * Checks if value is valid Email ID
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isEmail(String val) {

		String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		if (isNotNull(val)) {
			try {
				return val.matches(emailreg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value is Date
	 * 
	 * @param val
	 * @return
	 * @throws ParseException
	 */
	public static boolean isDate(String val) {

		Date d = null;
		if (isNotNull(val)) {
			d = DataUtility.getDate(val);
		}
		return d != null;
	}

}
