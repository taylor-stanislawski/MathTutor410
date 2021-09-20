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
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MTSApp {

	private JFrame frame;
	private JTextField idTextField;
	private JTextField pwdTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MTSApp window = new MTSApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MTSApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(250, 250, 630, 470);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel loginLabel = new JLabel("Login"); //label used as header for the login GUI
		loginLabel.setSize(new Dimension(400, 400));
		loginLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.add(loginLabel);
		
		JLabel idLabel = new JLabel("ID:"); //label for user_ID text field
		idLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.add(idLabel);
		
		idTextField = new JTextField(); //textfield to input user_ID
		idTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		idTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.add(idTextField);
		idTextField.setColumns(10);
		
		JLabel pwdLabel = new JLabel("Password:"); //label for password text field
		pwdLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		pwdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pwdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.add(pwdLabel);
		
		pwdTextField = new JTextField(); //text field to input password
		pwdTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.add(pwdTextField);
		pwdTextField.setColumns(10);
		
		JButton loginButton = new JButton("Login"); //Login button for students
		loginButton.setSize(250,30);
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String idString = idTextField.getText();
				String pwdString = pwdTextField.getText();
				MTSDriver.printLogin(idString, pwdString); //Make sure to replace this with a proper student login method
			}
		});
		loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.add(loginButton);
		
		JButton teacherLoginButton = new JButton("Teacher Login"); //login button for teachers
		teacherLoginButton.setSize(250,30);
		teacherLoginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Insert method call here (Teacher Login Method)
			}
		});
		teacherLoginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.add(teacherLoginButton);
		
		JButton registerButton = new JButton("Register"); //register button
		registerButton.setSize(250,30);
		registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Insert method call here (Registration Method)
			}
		});
		registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.add(registerButton);
		
	}

}
