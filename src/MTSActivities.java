import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Connection;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MTSActivities {

	private JFrame frame;
	private int id;
	private String password;

	/**
	 * Launch the application.
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
		lblNewLabel.setBounds(68, 32, 257, 47);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Counting");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MTSrecogCount counting = new MTSrecogCount();
				counting.MTSrecogCount(conn, id, password);
			}
		});
		btnNewButton.setBounds(117, 103, 175, 23);
		frame.getContentPane().add(btnNewButton);
		
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
		
		JButton btnAddNumbers = new JButton("Add 3 Numbers");
		btnAddNumbers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				threeValuesupto20 addThreeValues = new threeValuesupto20();
				addThreeValues.threeValuesupto20(conn, id, password);
			}
		});
		btnAddNumbers.setBounds(117, 171, 175, 23);
		frame.getContentPane().add(btnAddNumbers);
		
		JButton btnVerticalAddition = new JButton("Vertical Addition");
		btnVerticalAddition.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VertAddWindow verticalAddition = new VertAddWindow(conn, id, password);
				verticalAddition.getFrame().setVisible(true);
			}
		});
		btnVerticalAddition.setBounds(117, 205, 175, 23);
		frame.getContentPane().add(btnVerticalAddition);
		
		JButton btnHorizontalAddition = new JButton("Horizontal Addition");
		btnHorizontalAddition.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HorizAddWindow horizontalAddition = new HorizAddWindow(conn, id, password);
				horizontalAddition.getFrame().setVisible(true);
			}
		});
		btnHorizontalAddition.setBounds(117, 239, 175, 23);
		frame.getContentPane().add(btnHorizontalAddition);
		
		JButton btnAdddigitNumbers = new JButton("Add 2-Digit Numbers");
		btnAdddigitNumbers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				twoDigitCarry carry = new twoDigitCarry();
				carry.twoDigitCarry(conn, id, password);
			}
		});
		btnAdddigitNumbers.setBounds(117, 273, 175, 23);
		frame.getContentPane().add(btnAdddigitNumbers);
	}

}
