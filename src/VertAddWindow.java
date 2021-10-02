import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class VertAddWindow {

	private JFrame frame;
	static volatile int numArray[] = TutorMain.VerticalAdd();
	static volatile int result = numArray[0];


	/**
	 * Create the application.
	 */
	public VertAddWindow(Connection conn) {
		initialize(conn);
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Connection conn) {
		frame = new JFrame();
		frame.setBounds(100, 100, 491, 337);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel textLabel = new JLabel("Find the number to add up to");
		textLabel.setBounds(21, 21, 290, 26);
		frame.getContentPane().add(textLabel);
		
		JLabel num1Label = new JLabel(String.valueOf(numArray[1]));
		num1Label.setHorizontalAlignment(SwingConstants.RIGHT);
		num1Label.setBounds(236, 84, 32, 26);
		frame.getContentPane().add(num1Label);
		
		JLabel totalLabel = new JLabel(String.valueOf(numArray[0]));
		totalLabel.setBounds(186, 21, 92, 26);
		frame.getContentPane().add(totalLabel);
		
		JTextField numField = new JTextField();
		numField.setHorizontalAlignment(SwingConstants.RIGHT);
		numField.setBounds(236, 118, 32, 32);
		frame.getContentPane().add(numField);
		numField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("+");
		lblNewLabel.setBounds(201, 121, 32, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("__________");
		lblNewLabel_1.setBounds(197, 132, 100, 32);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel totalLabel2 = new JLabel(String.valueOf(numArray[0]));
		totalLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
		totalLabel2.setBounds(236, 172, 32, 26);
		frame.getContentPane().add(totalLabel2);
		
		JLabel resultLabel = new JLabel("New label");
		resultLabel.setBounds(177, 219, 164, 26);
		frame.getContentPane().add(resultLabel);
		
		JButton enterButton = new JButton("Enter");
		enterButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if(numField.getText() != null && numField.getText() != "") {
						if((Integer.parseInt(numField.getText()) + numArray[1]) == result) {
						resultLabel.setText("Correct");
						} else {
						resultLabel.setText("Incorrect");
						}
					}
					else {
						resultLabel.setText("Please Enter a Number.");
					}
					numField.setText(null);
					numArray = TutorMain.VerticalAdd();
					result = numArray[0];
					totalLabel.setText(String.valueOf(numArray[0]));
					totalLabel2.setText(String.valueOf(numArray[0]));
					num1Label.setText(String.valueOf(numArray[1]));
					
				}
				catch(java.lang.NumberFormatException error) {
					resultLabel.setText("Please enter a Number.");  
				}

			}
		});
		enterButton.setBounds(303, 132, 141, 32);
		frame.getContentPane().add(enterButton);
	}
}
