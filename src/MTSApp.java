import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.GridLayout;
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
		frame.setBounds(100, 100, 630, 470);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel loginLabel = new JLabel("Student Login");
		loginLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(loginLabel);
		
		JLabel idLabel = new JLabel("ID:");
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(idLabel);
		
		idTextField = new JTextField();
		idTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(idTextField);
		idTextField.setColumns(10);
		
		JLabel pwdLabel = new JLabel("Password:");
		pwdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(pwdLabel);
		
		pwdTextField = new JTextField();
		frame.getContentPane().add(pwdTextField);
		pwdTextField.setColumns(10);
		
		JButton loginButton = new JButton("Login");
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String idString = idTextField.getText();
				String pwdString = pwdTextField.getText();
				MTSDriver.printLogin(idString, pwdString);
			}
		});
		frame.getContentPane().add(loginButton);
		
		JButton teacherLoginButton = new JButton("Teacher Login");
		teacherLoginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		frame.getContentPane().add(teacherLoginButton);
		
		JButton registerButton = new JButton("Register");
		registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		frame.getContentPane().add(registerButton);
	}

}
