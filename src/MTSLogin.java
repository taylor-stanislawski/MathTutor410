import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JRadioButton;

public class MTSLogin {

	private JFrame frame;
	private JTextField idTextField;
	private JTextField pwdTextField;
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/";
    
    //open connection to the database
    public static Connection conn = null;
    public static Statement stmt = null;
    public static ResultSet rs = null;
    public static ResultSet rs2 = null;

	/**
	 * Create the application.
	 */
	
	
	public MTSLogin(Connection conn) {
		initialize(conn);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Connection conn) {
		setFrame(new JFrame());
		getFrame().setBounds(250, 250, 630, 470);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		//label used as header for the login GUI
		JLabel loginLabel = new JLabel("Login"); 
		loginLabel.setSize(new Dimension(400, 400));
		loginLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getFrame().getContentPane().add(loginLabel);
		
		//label for user_ID text field
		JLabel idLabel = new JLabel("ID:"); 
		idLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		getFrame().getContentPane().add(idLabel);
		
		//text field to input user_ID
		idTextField = new JTextField(); 
		idTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		idTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
		getFrame().getContentPane().add(idTextField);
		idTextField.setColumns(10);
		
		//label for password text field
		JLabel pwdLabel = new JLabel("Password:"); 
		pwdLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		pwdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pwdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		getFrame().getContentPane().add(pwdLabel);
		
		//text field to input password
		pwdTextField = new JTextField(); 
		pwdTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
		getFrame().getContentPane().add(pwdTextField);
		pwdTextField.setColumns(10);
		
		//Login button for students
		JButton loginButton = new JButton("Login"); 
		loginButton.setSize(250,30);
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idInt = Integer.valueOf(idTextField.getText());
				String pwdString = pwdTextField.getText();
				TutorMain.studentLogin(idInt, pwdString, conn);

			}
		});
		loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		getFrame().getContentPane().add(loginButton);
		
		//register button
		JButton registerButton = new JButton("Register"); 
		registerButton.setSize(250,30);
		registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Insert method call here (Registration Method)
				MTSRegister window = new MTSRegister(conn);
				window.frame.setVisible(true);
			}
		});
		registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		getFrame().getContentPane().add(registerButton);
		
		JRadioButton teacherRadioButton = new JRadioButton("I am a teacher");
		teacherRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
		getFrame().getContentPane().add(teacherRadioButton);
		
		JRadioButton studentRadioButton = new JRadioButton("I am a student");
		studentRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
		studentRadioButton.setSelected(true);
		getFrame().getContentPane().add(studentRadioButton);
		
		ButtonGroup group = new ButtonGroup();
		group.add(teacherRadioButton);
		group.add(studentRadioButton);
		
	}

	public JFrame getFrame() {
		return this.frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
