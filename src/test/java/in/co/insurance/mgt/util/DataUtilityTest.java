package in.co.insurance.mgt.util;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;


class DataUtilityTest {

	  private static final String APP_DATE_FORMAT = "MM/dd/yyyy";
	  private static final SimpleDateFormat formatter = new SimpleDateFormat(APP_DATE_FORMAT);
	
	  @BeforeAll
	  static void beforeAllInit()
	  {
	    System.out.println("Testing Started for DataUtility");
	  }
	  @BeforeEach
	  void initEach(TestInfo testInfo,TestReporter testReporter)
	  {
	    System.out.println("\n");
	    testReporter.publishEntry("Start Test "+ testInfo.getDisplayName());
	  }
	  
	  @AfterEach
	  void cleanup(TestInfo testInfo,TestReporter testReporter)
	  {
	     System.out.println("\n");
	     testReporter.publishEntry("End Test "+ testInfo.getDisplayName());
	  }
	  
	  @AfterAll
	  static  void complete()
	  {
	    System.out.println(" \n Testing Ended for DataUtility\n");
	  }
	  
	  @DisplayName("Testing Triming of trailing and leading spaces of a String")
	  @Test
	 void testGetString() {
		assertAll(
				() -> assertEquals("bhakti",DataUtility.getString("bhakti  ")),
				() -> assertNull(DataUtility.getString(null))
				);
		
	}

	
	@Nested
	class GetStringDataTest {
		
	   @DisplayName("Testing Conversion of an Object to String @validInput")
	   @Test
	    void testGetStringData_Valid() {
	        Object input = "test";
	        String result = DataUtility.getStringData(input);
	        assertEquals("test", result);
	    }
	   
	    @DisplayName("Testing Conversion of an Object to String @nullInput")
	    @Test
	    void testGetStringData_Null() {
	        Object input = null;
	        String result = DataUtility.getStringData(input);
	        assertEquals("", result);
	    }
	}
	
	@DisplayName("Testing Conversion of String to Integer")
	@Test	
    void testGetInt() {

		assertAll(
				() -> assertEquals(1234,DataUtility.getInt("1234")),
				() -> assertEquals(-45,DataUtility.getInt("-45"))
				);
	}

	@DisplayName("Testing Conversion of String to Double")
	@Test
	void testGetDouble() {
		assertAll(
				() -> assertEquals(12.34,DataUtility.getDouble("12.34")),
				() -> assertEquals(0.0,DataUtility.getDouble("abc"))
				);
	}

	@DisplayName("Testing Conversion of String to Long")
	@Test
	void testGetLong() {
		assertAll(
				() -> assertEquals(123456789L,DataUtility.getLong("123456789")),
				() -> assertEquals(0L,DataUtility.getLong("abc"))
				);
	}

	
	@Nested
	class testGetDate
	{
		@DisplayName("Testing Conversion of String to Date @validInput")
	    @Test
	    void testGetDate_Valid() 
	 	{
	        String input = "04/05/2022";
	        Date expectedDate;
	        try {
	            expectedDate = formatter.parse(input);
	        } catch (ParseException e) {
	            fail("Date parsing failed");
	            return;
	        }

	        Date result = DataUtility.getDate(input);
	        assertEquals(expectedDate, result);
	    }
	
		@DisplayName("Testing Conversion of String to Date @invalidInput")
		@Test
	    void testGetDate_Invalid() {
	        Date result = DataUtility.getDate("abc");
	        assertNull(result);
		}
		
  }

	
	@Nested
	class testGetDateString
	{
		 @DisplayName("Testing Conversion of Date to String @validInput")
		 @Test
		    void testGetDateString_Valid() {
		        Date input;
		        try {
		            input = formatter.parse("03/01/2022");
		        } catch (ParseException e) {
		            fail("Date parsing failed");
		            return;
		        }

		        String result = DataUtility.getDateString(input);
		        assertEquals("03/01/2022", result);
		    }
		 @DisplayName("Testing Conversion of Date to String @nullInput")
		 @Test
		 void testGetDateString_Null() {
		     Date input = null;
		     String result = DataUtility.getDateString(input);
		     assertEquals("", result);
		}
	}
	
	@Disabled
	@Test
	void testGetDateInt() {
		fail("Not yet implemented");
	}

	
	@Nested
	class testGetTimestampLong
	{
		
		@DisplayName("Testing Conversion of Timestamp to Long @validInput ")	
		@Test
		void test_TimestampToLongConversion() {
        Timestamp expectedTimestamp = new Timestamp(123456789L);
        Timestamp actual = DataUtility.getTimestamp(123456789L);
        assertEquals(expectedTimestamp, actual);
    	}
	
		@DisplayName("Testing Conversion of Timestamp to Long @invalidInput")
		@Test
		void testGetTimestamp_Invalid() {
	       Timestamp result = DataUtility.getTimestamp("abc");
	       assertNull(result);
	    }

	}
	
	//in original code function is commented
	@Disabled
	@Test
	void testGetTimestampString() {
		fail("Not yet implemented");
	}
	

	@DisplayName("Testing Conversion of Long to Timestamp")
	@Test
	void test_LongToTimestampConversion() {
	     Timestamp expectedTimestamp = new Timestamp(123456789L);
	     long input = 123456789L;

	     long result = DataUtility.getTimestamp(expectedTimestamp);
	     assertEquals(input, result);
	  }

	@DisplayName("Testing Current time")
	@Test
	void testGetCurrentTimestamp() {
		Timestamp currentTimestamp = DataUtility.getCurrentTimestamp();
        assertNotNull(currentTimestamp);
	}

	@DisplayName("Testing Random number")
	@Test
	void testGetRandom() {
		 long randomNumber = DataUtility.getRandom();
	     assertTrue(randomNumber >= 0 && randomNumber <= 999999);
	}

}
