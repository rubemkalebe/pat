package data.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	// Constant identifier
	public static final int MySQL = 0;
	
	// JDBC Driver for MySQL
	private static final String MySQLDriver = "com.mysql.jdbc.Driver";

	public static Connection createConnection(String url, String user, String pass, int db) {
		switch (db) {
		case MySQL:
			try {
				Class.forName(MySQLDriver);
			} catch (ClassNotFoundException e) {
				System.err.println("Could not find MySQL JDBC Driver!");
				e.printStackTrace();
			}
			break;
		}
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			System.err.println("Could not create database connection!");
			e.printStackTrace();
		}
		return conn;		
	}
}
