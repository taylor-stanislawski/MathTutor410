
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
            else {
            	MTSNotification badCredentials = new MTSNotification("Error: Invalid Credentials");
            	badCredentials.setNotificationText("Error: Invalid Credentials");
            	badCredentials.getFrame().setVisible(true);  	
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
            if(rs.next()) {
                System.out.println("Welcome " + rs.getString("firstName") + " " + rs.getString("lastName") );
                MTSProgress progress = new MTSProgress(conn, T_ID);
                progress.getFrame().setVisible(true);
            }
            else {
            	MTSNotification badCredentials = new MTSNotification("Error: Invalid Credentials");
            	badCredentials.setNotificationText("Error: Invalid Credentials");
            	badCredentials.getFrame().setVisible(true);
            	
            }
            
    
        } catch (SQLException e) {
            //print SQL errors
            e.printStackTrace();
        }
    }
	
	public static void studentRegister(String firstName, String lastName, String pass, int grade, int teacherID, Connection conn) {
        int latestID = 0;

        try {
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("SELECT S_ID FROM tutor.student order by S_ID");
            while(rs.next()) {
                latestID=rs.getInt("S_ID");
            }
            
            stmt.execute("insert into tutor.student " + 
                         "values('"  + firstName + "','" + lastName + "','" +  pass + "','" + (latestID+1) + "','" + teacherID + "','" + grade + "')" );
            stmt.execute("insert into tutor.Activities " + "values('" + (latestID+1) + "','" + 0 + "','" + 0 + "','" + 0 + "','" + 0 + "','" + 0 + "','" + 0 + "')");
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
            MTSNotification newID = new MTSNotification(String.valueOf(latestID + 1));
            newID.getFrame().setVisible(true);
        } catch (SQLException e) {
            //print SQL errors
            e.printStackTrace();
        }
    }
	
	public static void vertGrade(Connection conn, int total, int correct, int S_ID, int activity) {
        double currGrade = 0;
        double newGrade = 0;
        double updGrade = 0;
        
        try {
        	stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        switch(activity) {

        case 1:
	        rs = stmt.executeQuery("select Actv_1 from tutor.Activities where S_ID = " + S_ID);
	        while(rs.next()) {
	            currGrade=rs.getInt("Actv_1");
	        }
	        if(currGrade == 0) {
	        	newGrade = (correct / (double) total) * 100;
	        	updGrade = newGrade;
	        }
	        else {
		        newGrade = (correct / (double) total) * 100;
		        updGrade = ((newGrade + currGrade) / 2);
	        }
	        stmt.execute("update tutor.activities " +
                     "SET Actv_1 = " + updGrade +
                     " WHERE S_ID = " + S_ID);
	        break;
        case 2: 
        	rs = stmt.executeQuery("select Actv_2 from tutor.Activities where S_ID = " + S_ID);
	        while(rs.next()) {
	            currGrade=rs.getInt("Actv_2");
	        }
	        if(currGrade == 0) {
	        	newGrade = (correct / (double) total) * 100;
	        	updGrade = newGrade;
	        }
	        else {
		        newGrade = (correct / (double) total) * 100;
		        updGrade = ((newGrade + currGrade) / 2);
	        }
	        stmt.execute("update tutor.activities " +
                         "SET Actv_2 = " + updGrade +
                         " WHERE S_ID = " + S_ID);
        	break;
        case 3:
        	rs = stmt.executeQuery("select Actv_3 from tutor.Activities where S_ID = " + S_ID);
        	while(rs.next()) {
            	currGrade = rs.getInt("Actv_3");
        	}
        	
	        if(currGrade == 0) {
	        	newGrade = (correct / (double) total) * 100;
	        	updGrade = newGrade;
	        }
	        else {
		        newGrade = (correct / (double) total) * 100;
		        updGrade = ((newGrade + currGrade) / 2);
	        }
	        System.out.println(currGrade + " " + newGrade + " " + updGrade);
	        stmt.execute("update tutor.Activities " +
                     "SET Actv_3 = " + updGrade +
                     " WHERE S_ID = " + S_ID);
        	break;
        case 4:
        	rs = stmt.executeQuery("select Actv_4 from tutor.Activities where S_ID = " + S_ID);
	        while(rs.next()) {
	            currGrade=rs.getInt("Actv_4");
	        }
	        if(currGrade == 0) {
	        	newGrade = (correct / (double) total) * 100;
	        	updGrade = newGrade;
	        }
	        else {
		        newGrade = (correct / (double) total) * 100;
		        updGrade = ((newGrade + currGrade) / 2);
	        }
	        System.out.println(currGrade + " " + newGrade + " " + updGrade);
	        stmt.execute("update tutor.activities " +
                     "SET Actv_4 = " + updGrade +
                     " WHERE S_ID = " + S_ID);
        	break;
        case 5:
        	rs = stmt.executeQuery("select Actv_5 from tutor.Activities where S_ID = " + S_ID);
	        while(rs.next()) {
	            currGrade=rs.getInt("Actv_5");
	        }
	        if(currGrade == 0) {
	        	newGrade = (correct / (double) total) * 100;
	        	updGrade = newGrade;
	        }
	        else {
		        newGrade = (correct / (double) total) * 100;
		        updGrade = ((newGrade + currGrade) / 2);
	        }
	        stmt.execute("update tutor.activities " +
                     "SET Actv_5 = " + updGrade +
                     " WHERE S_ID = " + S_ID);
        	break;
        case 6:
        	rs = stmt.executeQuery("select Actv_6 from tutor.Activities where S_ID = " + S_ID);
	        while(rs.next()) {
	            currGrade=rs.getInt("Actv_6");
	        }
	        if(currGrade == 0) {
	        	newGrade = (correct / (double) total) * 100;
	        	updGrade = newGrade;
	        }
	        else {
		        newGrade = (correct / (double) total) * 100;
		        updGrade = ((newGrade + currGrade) / 2);
	        }
	        stmt.execute("update tutor.activities " +
                     "SET Actv_6 = " + updGrade +
                     " WHERE S_ID = " + S_ID);
	        break;

        }//end switch
        
        
        
        } catch (SQLException e) {
            //print SQL errors
            e.printStackTrace();
        }
    }
	
	public static ArrayList<Student> getProgress() {
        ArrayList<Student> studentList = new ArrayList<Student>();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select * from tutor.Activities");
            
            
            while(rs.next()) {
                studentList.add(new Student(rs.getInt("S_ID"),
                                            rs.getInt("Actv_1"),
                                            rs.getInt("Actv_2"),
                                            rs.getInt("Actv_3"),
                                            rs.getInt("Actv_4"),
                                            rs.getInt("Actv_5"),
                                            rs.getInt("Actv_6") ) 
                                );
            }

        } catch (SQLException e) {
            //print SQL errors
            e.printStackTrace();
        }
        
        return studentList;
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
	
	public static int[] VerticalAdd() {
		
		int numArray[] = new int[2]; 
		int i =0;
		
		Random rand = new Random();
		numArray[0] = rand.nextInt(2);
		if(numArray[0] == 0) {
			i = rand.nextInt(9) + 1;
			numArray[0] = 10;
			numArray[1] = 10 - i;
		}
		else {
			i = rand.nextInt(19) + 1;
			numArray[0] = 20;
			numArray[1] = 20 - i;
		}
		
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

