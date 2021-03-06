
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;

public class HorizAddWindow {

	
	public static int total;
	public static int correct;
	private JFrame frame;
	static volatile int numArray[] = TutorMain.HorizontalAdd();
	static volatile int result = numArray[3];

	public HorizAddWindow(Connection conn, int id, String pwd) {
		initialize(conn, id);
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
	private void initialize(Connection conn, int id) {
		frame = new JFrame();
		frame.setBounds(100, 100, 473, 314);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);	
		
		JLabel sumLabel = new JLabel("Enter sum of: ");
		sumLabel.setBounds(0, 31, 159, 26);
		frame.getContentPane().add(sumLabel);
		
		JLabel numLabel = new JLabel(String.valueOf(numArray[0]) + " + " +
				                          String.valueOf(numArray[1]) + " + " +
				                          String.valueOf(numArray[2]));
		numLabel.setHorizontalAlignment(SwingConstants.CENTER);
		numLabel.setBounds(173, 31, 101, 26);
		frame.getContentPane().add(numLabel);
		
		JLabel resultLabel = new JLabel();
		resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		resultLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		resultLabel.setBounds(104, 223, 250, 26);
		frame.getContentPane().add(resultLabel);
		
		JTextField numField = new JTextField();
		numField.setBounds(130, 89, 186, 32);
		frame.getContentPane().add(numField);
		numField.setColumns(10);
		
		JButton enterButton = new JButton("Enter");
		enterButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if(numField.getText() != null && numField.getText() != "") {
						if(Integer.parseInt(numField.getText()) == result) {
						resultLabel.setText("Correct");
						correct++;
						total++;
						} else {
						resultLabel.setText("Incorrect");
						total++;
						}
					}
					else {
						resultLabel.setText("Please Enter a Number.");
					}
					numField.setText(null);
					numArray = TutorMain.HorizontalAdd();
					result = numArray[3];
					numLabel.setText(String.valueOf(numArray[0]) + " + " +
									 String.valueOf(numArray[1]) + " + " +
									 String.valueOf(numArray[2]));
				}
				catch(java.lang.NumberFormatException error) {
					resultLabel.setText("Please enter a Number.");  
				}

			}
		});
		enterButton.setBounds(153, 133, 141, 32);
		frame.getContentPane().add(enterButton);
		
		JButton skipButton = new JButton("Skip");
		skipButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				numArray = TutorMain.HorizontalAdd();
				result = numArray[3];
				numLabel.setText(String.valueOf(numArray[0]) + " + " +
						 String.valueOf(numArray[1]) + " + " +
						 String.valueOf(numArray[2]));
				
				
			}
		});
		skipButton.setBounds(325, 27, 101, 35);
		frame.getContentPane().add(skipButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TutorMain.vertGrade(conn, total, correct, id, 5);
				frame.dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnExit.setBounds(153, 176, 141, 36);
		frame.getContentPane().add(btnExit);
		
	}

}
