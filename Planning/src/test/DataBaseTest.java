package test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import dao.DataBase;

public class DataBaseTest {

	@Test
	public void testConnectionBase() throws SQLException {

		assertNotNull(DataBase.getConnection());
	}

}
