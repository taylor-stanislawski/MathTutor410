import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Connection;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MTSActivities {

	private JFrame frame;
	private int id;
	private String password;

	/**
	 * Launch the application.
	 */
	
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MTSActivities window = new MTSActivities(conn);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the application.
	 */
	public MTSActivities(Connection conn, int id, String password) {
		this.id = id;
		this.password = password;
		initialize(conn);
	}
	
	public JFrame getFrame() {
		return this.frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Connection conn) {
		frame = new JFrame();
		frame.setBounds(100, 100, 408, 358);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Choose an Activity");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(68, 11, 257, 47);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Counting");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnNewButton.setBounds(117, 69, 175, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnGreaterOrLess = new JButton("Greater or Less Than");
		btnGreaterOrLess.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnGreaterOrLess.setBounds(117, 103, 175, 23);
		frame.getContentPane().add(btnGreaterOrLess);
		
		JButton btnAddUsingUmber = new JButton("Add Using Number Line");
		btnAddUsingUmber.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MTSNumberLineAdd numberLine = new MTSNumberLineAdd(conn, id, password);
				numberLine.getFrame().setVisible(true);
			}
		});
		btnAddUsingUmber.setBounds(117, 137, 175, 23);
		frame.getContentPane().add(btnAddUsingUmber);
		
		JButton btnAddNumbers = new JButton("Add 3 Numbers to Make 20");
		btnAddNumbers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnAddNumbers.setBounds(117, 171, 175, 23);
		frame.getContentPane().add(btnAddNumbers);
		
		JButton btnVerticalAddition = new JButton("Vertical Addition");
		btnVerticalAddition.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnVerticalAddition.setBounds(117, 205, 175, 23);
		frame.getContentPane().add(btnVerticalAddition);
		
		JButton btnHorizontalAddition = new JButton("Horizontal Addition");
		btnHorizontalAddition.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnHorizontalAddition.setBounds(117, 239, 175, 23);
		frame.getContentPane().add(btnHorizontalAddition);
		
		JButton btnAdddigitNumbers = new JButton("Add 2-Digit Numbers");
		btnAdddigitNumbers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnAdddigitNumbers.setBounds(117, 273, 175, 23);
		frame.getContentPane().add(btnAdddigitNumbers);
	}

}
