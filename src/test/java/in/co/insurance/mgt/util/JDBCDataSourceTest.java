package in.co.insurance.mgt.util;

import in.co.insurance.mgt.exception.ApplicationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class JDBCDataSourceTest {	
	
	@BeforeAll
    static void setUp() {
		ResourceBundle rb = mock(ResourceBundle.class);
   }
	

	@Test
	void testGetInstance() {
		JDBCDataSource dataSource = JDBCDataSource.getInstance();
        assertNotNull(dataSource);
	}

	@Test
	void testGetConnection() {
		try {
            Connection connection = JDBCDataSource.getConnection();
            assertNotNull(connection);
        } catch (Exception e) {
            fail("Failed to get connection: " + e.getMessage());
        }
	}

	@Test
	void testCloseConnection() {
		Connection connection = mock(Connection.class);
        JDBCDataSource.closeConnection(connection);
        try {
            connection.close();
        } catch (SQLException e) {
            fail("Failed to close connection: " + e.getMessage());
        }
	}

	@Disabled
	@Test
	void testTrnRollback() {
		 Connection connection = mock(Connection.class);
	        ApplicationException exception = assertThrows(ApplicationException.class, () -> JDBCDataSource.trnRollback(connection));
	        assertEquals("SQLException", exception.toString());
	}

}
