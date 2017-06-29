package data.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	private String URL;
	private String USER;
	private String PASS;
	private int DB;
	
	private Connection conn;  
	private Statement statement;
	
	public DBUtil(String uRL, String uSER, String pASS, int dB) {
		URL = uRL;
		USER = uSER;
		PASS = pASS;
		DB = dB;
		this.conn = null;
		this.statement = null;
	}	
	
	public void connect() {
        conn = ConnectionFactory.createConnection(URL, USER, PASS, DB);
		try {
			statement = conn.createStatement();
		} catch (SQLException e) {
			System.err.println("Failed to connect to the DB!");
			e.printStackTrace();
		}
        System.out.println("Connected to the database!");   
	}
	
	public void disconnect() {
		try {
			statement.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("Failed to disconnect!");
			e.printStackTrace();
		}
		
		System.out.println("Connection closed!");
	}

	public Connection getConn() {
		return conn;
	}

	public Statement getStatement() {
		return statement;
	}
	
}
