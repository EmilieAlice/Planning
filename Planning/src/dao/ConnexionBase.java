package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBase {

	/**
	 * Classe de connexion à la base de données. Cela fournit une connexion à la
	 * base de données traducteur
	 */
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	static {
		try {
			Class.forName(DRIVER_NAME).newInstance();
			System.out.println("*** Driver loaded.");
		} catch (ClassNotFoundException e) {
			System.err.println("*** ERROR: Driver " + DRIVER_NAME
					+ " not found");
		} catch (InstantiationException e) {
			System.err
					.println("*** ERROR: Impossible to create an instance of "
							+ DRIVER_NAME);
			System.err.println(e.getMessage());
		} catch (IllegalAccessException e) {
			System.err
					.println("*** ERROR: Impossible to create an instance of "
							+ DRIVER_NAME);
			System.err.println(e.getMessage());
		}
	}

	private static final String URL = "jdbc:mysql://localhost/lagarenne2015";
	private static final String USER = "root";
	private static final String PASSWORD = "1234";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
