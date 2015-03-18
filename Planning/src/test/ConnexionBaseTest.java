package test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import dao.ConnexionBase;

public class ConnexionBaseTest {

	@Test
	public void testConnectionBase() throws SQLException {
		ConnexionBase connection = new ConnexionBase();
		assertNotNull(connection.getConnection());
	}

}
