
import java.awt.EventQueue;
import java.io.*;
import java.util.*; 
import java.sql.*;

public class TutorMain {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/";
	
	//open connection to the database
	public static Connection conn = null;
	public static Statement stmt = null;
	public static ResultSet rs = null;
	public static ResultSet rs2 = null;
	
	public static void main(String[] args) {
		
		//retrieve username and password for mysql server
				String dbuser = args[0];
				String dbpass = args[1];
				
		//open connection to the database
		
		try {
			//establish connection to database
			System.out.println("Connecting to database");
			conn = DriverManager.getConnection(DB_URL, dbuser, dbpass);
			System.out.println("Connection established: " + conn.isValid(2));
		
			try {
				HorizAddWindow window = new HorizAddWindow();
				window.getFrame().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		//Update student file with results	
			
		//handle JDBC errors
			
		} catch (SQLException se) { 
			System.out.println("SQL Exception: " + se.getMessage());
			System.out.println("SQLState Code: " + se.getSQLState());
			System.out.println("Error Code: " + se.getErrorCode());
		
			
		} //end try block
		
	}
	
	public static int[] HorizontalAdd() {
		
		int numArray[] = new int[4]; 
		
		Random rand = new Random();
		numArray[0] = rand.nextInt(9) + 1;
		numArray[1] = rand.nextInt(9) + 1;
		numArray[2] = rand.nextInt(9) + 1;
		numArray[3] = (numArray[0] + numArray[1] + numArray[2]);
		
		return numArray;
	}
	
	public static void endDBConn() {
		try {
			if (conn != null) conn.close();
			if (rs != null) conn.close();
			if (rs2 != null) conn.close();
			if (stmt != null) conn.close();
			System.out.println("Connection closed: " + conn.isClosed());
			
		}
		catch (SQLException se2) {}
	}
}

