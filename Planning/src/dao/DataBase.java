package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe de connexion à la base de données. Fournit une connexion à la base de
 * données lagarenne2015
 * 
 * @author Jerome
 */
public class DataBase {

	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	static {
		try {
			Class.forName(DRIVER_NAME).newInstance();
			System.out.println("*** Driver loaded.");
		} catch (ClassNotFoundException e) {
			System.err.println("*** ERROR: Driver " + DRIVER_NAME + " not found");
		} catch (InstantiationException e) {
			System.err.println("*** ERROR: Impossible de se connecter à " + DRIVER_NAME);
			System.err.println(e.getMessage());
		} catch (IllegalAccessException e) {
			System.err.println("*** ERROR: Impossible de se connecter à " + DRIVER_NAME);
			System.err.println(e.getMessage());
		}
	}

	private static final String URL = "jdbc:mysql://localhost/lagarenne2015";
	private static final String USER = "lagarenne2015";
	private static final String PASSWORD = "lagarenne2015";

	/**
	 * Methode qui permet la connexion à la base de données
	 * @return la connexion
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
