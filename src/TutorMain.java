
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
	
	public static void register(String firstName, String lastName, int grade ) {
        int latestID = 0;

        try {
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("SELECT S_ID FROM tutor.student order by S_ID");
            while(rs.next()) {
                latestID=rs.getInt("S_ID");
            }
            
            stmt.execute("insert into tutor.student " + 
                         "values('"  + firstName + "','" + lastName + "','" + (latestID+1) + "','" +   grade + "')" );
        } catch (SQLException e) {
            //print SQL errors
            e.printStackTrace();
        }
    }
	
	public static void studentLogin(int S_ID, String pass, Connection conn) {
        
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("SELECT * FROM tutor.student WHERE " +
                                   "S_ID = " + S_ID + " AND S_PWD = '" + pass + "'");
            if(rs.next()) {
                System.out.println("Welcome " + rs.getString("firstName") + " " + rs.getString("lastName") );
                MTSActivities activityList = new MTSActivities(conn, rs.getInt("S_ID"), rs.getString("S_PWD"));
                activityList.getFrame().setVisible(true);
            }
            
    
        } catch (SQLException e) {
            //print SQL errors
            e.printStackTrace();
        }
    }
	
	public static void teacherLogin(int T_ID, String pass, Connection conn) {
        
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("SELECT * FROM tutor.teacher WHERE " +
                                   "T_ID = " + T_ID + " AND T_PWD = '" + pass + "'");
            while(rs.next()) {
                System.out.println("Welcome " + rs.getString("firstName") + " " + rs.getString("lastName") );
            }
            
    
        } catch (SQLException e) {
            //print SQL errors
            e.printStackTrace();
        }
    }
	
	public static void studentRegister(String firstName, String lastName, String pass, int grade, Connection conn) {
        int latestID = 0;

        try {
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("SELECT S_ID FROM tutor.student order by S_ID");
            while(rs.next()) {
                latestID=rs.getInt("S_ID");
            }
            
            stmt.execute("insert into tutor.student " + 
                         "values('"  + firstName + "','" + lastName + "','" +  pass + "','" + (latestID+1) + "','" + grade + "')" );
            MTSNotification newID = new MTSNotification(String.valueOf(latestID + 1));
            newID.getFrame().setVisible(true);
        } catch (SQLException e) {
            //print SQL errors
            e.printStackTrace();
        }
    }
	
	public static void teacherRegister(String firstName, String lastName, String pass, int grade, Connection conn) {
        int latestID = 0;

        try {
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("SELECT T_ID FROM tutor.teacher order by T_ID");
            while(rs.next()) {
                latestID=rs.getInt("T_ID");
            }
            
            stmt.execute("insert into tutor.teacher " + 
                         "values('"  + firstName + "','" + lastName + "','" +  pass + "','" + (latestID+1) + "','" + grade + "')" );
        } catch (SQLException e) {
            //print SQL errors
            e.printStackTrace();
        }
    }
	
	

	
	public static void main(String[] args) {
		
		String dbuser = args[0];
		String dbpass = args[1];
		
		try {
            //establish connection to database
            System.out.println("Connecting to database");
            conn = DriverManager.getConnection(DB_URL, dbuser, dbpass);
            System.out.println("Connection established: " + conn.isValid(2));
            
            try {
    			MTSLogin window = new MTSLogin(conn);
    			window.getFrame().setVisible(true);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}

        //handle JDBC errors
        } catch (SQLException se) { 
            System.out.println("SQL Exception: " + se.getMessage());
            System.out.println("SQLState Code: " + se.getSQLState());
            System.out.println("Error Code: " + se.getErrorCode());


        } //end try block
		
		
		
		
		
		//Update student file with results	
			
		//handle JDBC errors
		/*	
		} catch (SQLException se) { 
			System.out.println("SQL Exception: " + se.getMessage());
			System.out.println("SQLState Code: " + se.getSQLState());
			System.out.println("Error Code: " + se.getErrorCode());
		
			
		} //end try block
		*/
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

