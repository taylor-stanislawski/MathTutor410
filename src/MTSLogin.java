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

public class MTSLogin {

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
					MTSLogin window = new MTSLogin();
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
	public MTSLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(250, 250, 630, 470);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
		JLabel loginLabel = new JLabel("Login"); //label used as header for the login GUI
		loginLabel.setSize(new Dimension(400, 400));
		loginLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(loginLabel);
		
		JLabel idLabel = new JLabel("ID:"); //label for user_ID text field
		idLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.getContentPane().add(idLabel);
		
		idTextField = new JTextField(); //textfield to input user_ID
		idTextField.setHorizontalAlignment(SwingConstants.CENTER);
		idTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		idTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.getContentPane().add(idTextField);
		idTextField.setColumns(10);
		
		JLabel pwdLabel = new JLabel("Password:"); //label for password text field
		pwdLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		pwdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pwdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.getContentPane().add(pwdLabel);
		
		pwdTextField = new JTextField(); //text field to input password
		pwdTextField.setHorizontalAlignment(SwingConstants.CENTER);
		pwdTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.getContentPane().add(pwdTextField);
		pwdTextField.setColumns(10);
		
		JButton loginButton = new JButton("I am a Student"); //Login button for students
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
		frame.getContentPane().add(loginButton);
		
		JButton teacherLoginButton = new JButton("I am a Teacher"); //login button for teachers
		teacherLoginButton.setSize(250,30);
		teacherLoginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Insert method call here (Teacher Login Method)
			}
		});
		teacherLoginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.getContentPane().add(teacherLoginButton);
		
		JButton registerButton = new JButton("Register"); //register button
		registerButton.setSize(250,30);
		registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Insert method call here (Registration Method)
			}
		});
		registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.getContentPane().add(registerButton);
		
	}

}
