import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MTSDriver {
	
	public static void printLogin(String str1, String str2) {
		System.out.println("ID: " + str1 + "\nPWD: " + str2);
		
	}
	/*
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/MTSDataBase";
		String username = "root";
		String password = "2018Gr33n3B34n";
		
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connected to the database");
		} catch (SQLException e) {
			System.out.println("Could not connect to the database");
			e.printStackTrace();
		}
	}
	*/
}
