package in.co.insurance.mgt.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;

class DataValidatorTest {

	
	@BeforeAll
	static void beforeAllInit()
	{
		System.out.println("....Start Testing DataValidator ....\n");
	}
	@BeforeEach
	void initEach(TestInfo testInfo,TestReporter testReporter)
	{
		System.out.println("");
		testReporter.publishEntry("Start Test "+ testInfo.getDisplayName());
	}
	
	@AfterEach
	void cleanup(TestInfo testInfo,TestReporter testReporter)
	{
		 System.out.println("");
		 testReporter.publishEntry("End Test "+ testInfo.getDisplayName());
	}
	
	@AfterAll
	static void complete()
	{
		System.out.println(" .....End Testing DataValidator ....\n");
	}
	
	
//	@Test
//	void testIsName() {
//		assertEquals(true, DataValidator.isName("Bhagavati"),"Name Test");
//		
//	}
	
	@Nested
    @Tag("NameTest")
    class IsNameTests {

        @Test
        @DisplayName("should return true for a valid name")
        public void testIsName_ValidName() {
            
            assertTrue(DataValidator.isName("Bhagavati Tiwari"));
        }

        @Test
        @DisplayName("should return false for a name containing digits")
        public void testIsName_InvalidNameWithDigits() {
            
            assertFalse(DataValidator.isName("bhagavati123"));
        }

        @Test
        @DisplayName("should return false for a name containing special characters")
        public void testIsName_InvalidNameWithSpecialCharacters() {
            
            assertFalse(DataValidator.isName("bhagavati@Tiwari"));
        }
    }




	@Test
	@Tag("RollNoTest")
	@DisplayName(" Rollno TestCases")
	void testIsRollNo()
	{
		/*the regular expression is expecting the following pattern 
		 * for a valid roll number: two digits, two uppercase letters,
		 *  and one or more digits, followed by a non-whitespace character.
		 */
		assertAll("isRollNO",
                () -> assertTrue(DataValidator.isRollNO("19AB123"),"Valid Roll number"),
                () -> assertFalse(DataValidator.isRollNO("AB123"),"Roll number start with alphabe"),
                () -> assertFalse(DataValidator.isRollNO(""),"Blank String error"),
                () -> assertFalse(DataValidator.isRollNO(""),"Null String error")
        );
	}
	
	
	
	    @Test
	    @DisplayName("isPassword method test")
	    public void testIsPassword() {
	        assertAll("isPassword",
	            () -> assertTrue(DataValidator.isPassword("Pass123!"), "Valid password"),
	            () -> assertFalse(DataValidator.isPassword("password"), "Invalid password without an uppercase letter")
	        );
	    }

	    @Test
	    @DisplayName("isPhoneNo method test")
	    public void testIsPhoneNo() {
	        assertAll("isPhoneNo",
	            () -> assertTrue(DataValidator.isPhoneNo("9876543210"), "Valid phone number"),
	            () -> assertFalse(DataValidator.isPhoneNo("12345"), "Invalid phone number with an invalid length")
	        );
	    }
	    
	    @Disabled
	    @Test
	    @DisplayName("isNull method test")
	    public void testIsNull() {
	        assertAll("isNull",
	            () -> assertTrue(DataValidator.isNull(null), "Null value"),
	            () -> assertTrue(DataValidator.isNull(""), "Empty string"),
	            () -> assertFalse(DataValidator.isNull("Hello"), "Non-null value")
	        );
	    }

	    @Test
	    @DisplayName("isNotNull method test")
	    public void testIsNotNull() {
	        assertAll("isNotNull",
	            () -> assertFalse(DataValidator.isNotNull(null), "Null value"),
	            () -> assertFalse(DataValidator.isNotNull(""), "Empty string"),
	            () -> assertTrue(DataValidator.isNotNull("Hello"), "Non-null value"),
	            () -> assertTrue(DataValidator.isNotNull("     Hello      "), "having White Space value")
	        );
	    }

	    @Test
	    @DisplayName("isInteger method test")
	    public void testIsInteger() {
	        assertAll("isInteger",
	            () -> assertTrue(DataValidator.isInteger("123"), "Valid integer"),
	            () -> assertFalse(DataValidator.isInteger("12A"), "Invalid integer")
	        );
	    }

	    @Test
	    @DisplayName("isLong method test")
	    public void testIsLong() {
	        assertAll("isLong",
	            () -> assertTrue(DataValidator.isLong("123456789"), "Valid long value"),
	            () -> assertFalse(DataValidator.isLong("12A"), "Invalid long value")
	        );
	    }

	    @Test
	    @DisplayName("isDouble method test")
	    public void testIsDouble() {
	        assertAll("isDouble",
	            () -> assertTrue(DataValidator.isDouble("123.45"), "Valid double value"),
	            () -> assertFalse(DataValidator.isDouble("12A"), "Invalid double value")
	        );
	    }

	    @Test
	    @DisplayName("isEmail method test")
	    public void testIsEmail() {
	        assertAll("isEmail",
	            () -> assertTrue(DataValidator.isEmail("test@example.com"), "Valid email"),
	            () -> assertFalse(DataValidator.isEmail("invalid"), "Invalid email")
	        );
	    }

	    @Test
	    @DisplayName("isDate method test")
	    public void testIsDate() {
	        assertAll("isDate",
	            () -> assertTrue(DataValidator.isDate("2023/07/10"), "Valid date"),
	            () -> assertFalse(DataValidator.isDate("2023-07-10"), "Invalid date")
	            
	        );
	    }

}
