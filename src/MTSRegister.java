import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

public class MTSRegister {

	JFrame frame;
	private JTextField lastNameTextfield;
	private JTextField passwordTextfield;
	private JTextField firstNameTextfield;
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/";
    
    //open connection to the database
    public static Connection conn = null;
    public static Statement stmt = null;
    public static ResultSet rs = null;
    public static ResultSet rs2 = null;

	/**
	 * Launch the application.
	 */
    /*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MTSRegister window = new MTSRegister();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		String dbuser = args[0];
		String dbpass = args[1];
		
		try {
            //establish connection to database
            System.out.println("Connecting to database");
            conn = DriverManager.getConnection(DB_URL, dbuser, dbpass);
            System.out.println("Connection established: " + conn.isValid(2));

        //handle JDBC errors
        } catch (SQLException se) { 
            System.out.println("SQL Exception: " + se.getMessage());
            System.out.println("SQLState Code: " + se.getSQLState());
            System.out.println("Error Code: " + se.getErrorCode());


        } //end try block
	}
	*/

	/**
	 * Create the application.
	 */
	public MTSRegister(Connection conn) {
		initialize(conn);
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Connection conn) {
		
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 536, 472);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel registerLabel = new JLabel("Register");
		registerLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		registerLabel.setBounds(204, 0, 112, 35);
		frame.getContentPane().add(registerLabel);
		
		JLabel lastNameLabel = new JLabel("Last Name:");
		lastNameLabel.setBounds(182, 134, 86, 14);
		frame.getContentPane().add(lastNameLabel);
		
		lastNameTextfield = new JTextField();
		lastNameTextfield.setBounds(182, 149, 155, 20);
		frame.getContentPane().add(lastNameTextfield);
		lastNameTextfield.setColumns(10);
		
		passwordTextfield = new JTextField();
		passwordTextfield.setBounds(182, 195, 155, 20);
		frame.getContentPane().add(passwordTextfield);
		passwordTextfield.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(182, 180, 86, 14);
		frame.getContentPane().add(passwordLabel);
		
		JLabel gradeLabel = new JLabel("Grade:");
		gradeLabel.setBounds(182, 227, 86, 14);
		frame.getContentPane().add(gradeLabel);
		
		ButtonGroup group = new ButtonGroup();
		
		JRadioButton teacherRadioButton = new JRadioButton("I am a teacher");
		teacherRadioButton.setBounds(205, 294, 109, 23);
		group.add(teacherRadioButton);
		frame.getContentPane().add(teacherRadioButton);
		
		JRadioButton studentRadioButton = new JRadioButton("I am a student");
		studentRadioButton.setBounds(205, 320, 109, 23);
		group.add(studentRadioButton);
		studentRadioButton.setSelected(true);
		frame.getContentPane().add(studentRadioButton);
		
		firstNameTextfield = new JTextField();
		firstNameTextfield.setBounds(182, 103, 155, 20);
		frame.getContentPane().add(firstNameTextfield);
		firstNameTextfield.setColumns(10);
		
		JLabel firstNameLabel = new JLabel("First Name:");
		firstNameLabel.setBounds(182, 87, 86, 14);
		frame.getContentPane().add(firstNameLabel);
		
		JComboBox gradeComboBox = new JComboBox();
		gradeComboBox.setMaximumRowCount(6);
		gradeComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6"}));
		gradeComboBox.setSelectedIndex(0);
		gradeComboBox.setBounds(182, 252, 155, 22);
		frame.getContentPane().add(gradeComboBox);
		
		JLabel errorLabel = new JLabel("");
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setBounds(58, 46, 404, 30);
		frame.getContentPane().add(errorLabel);
		
		JButton registerButton = new JButton("Register");
		registerButton.setBounds(190, 366, 139, 35);
		registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(firstNameTextfield.getText().equals("") || lastNameTextfield.getText().equals("") || passwordTextfield.getText().equals("")) {
					errorLabel.setText("Error: One or more mising fields");
				}
				else {
					if(studentRadioButton.isSelected()) {
						String grade = gradeComboBox.getSelectedItem().toString();
						int gradeInt = Integer.parseInt(grade);
						TutorMain.studentRegister(firstNameTextfield.getText(), lastNameTextfield.getText(), passwordTextfield.getText(), gradeInt, conn);
					}
					else {
						String grade = gradeComboBox.getSelectedItem().toString();
						int gradeInt = Integer.parseInt(grade);
						TutorMain.teacherRegister(firstNameTextfield.getText(), lastNameTextfield.getText(), passwordTextfield.getText(), gradeInt, conn);
					}
				}
				
			}
		});
		frame.getContentPane().add(registerButton);
		
	}
}
