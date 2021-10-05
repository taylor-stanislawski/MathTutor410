import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MTSNumberLineAdd {

	private JFrame frame;
	private JTextField answerText;
	private JLabel sliderLabel;
	public static int total;
	public static int correct;


	/**
	 * Create the application.
	 */
	public MTSNumberLineAdd(Connection conn, int id, String password) {
		initialize(conn, id);
	}
	
	public void doCorrectAnswer() {
		total++;
		correct++;
		System.out.println("Correct!\nYou have correctly answered " + correct + " times!");
		
	}
	
	public void doIncorrectAnswer() {
		total++;
		System.out.println("Incorrect!\nYou have incorrectly answered " + (total - correct) + " times!");
	}
	
	public JFrame getFrame() {
		return this.frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Connection conn, int id) {
		frame = new JFrame();
		frame.setBounds(100, 100, 692, 430);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 678, 393);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		sliderLabel = new JLabel("5");
		sliderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sliderLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		sliderLabel.setBounds(156, 120, 55, 33);
		panel.add(sliderLabel);
		
		JLabel equalsLabel = new JLabel("=      10");
		equalsLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		equalsLabel.setBounds(422, 120, 80, 33);
		panel.add(equalsLabel);
		
		answerText = new JTextField();
		answerText.setBounds(302, 114, 74, 51);
		panel.add(answerText);
		answerText.setColumns(10);
		
		JLabel add10Label = new JLabel("Add up to 10");
		add10Label.setHorizontalAlignment(SwingConstants.CENTER);
		add10Label.setFont(new Font("Times New Roman", Font.BOLD, 30));
		add10Label.setBounds(249, 11, 179, 57);
		panel.add(add10Label);
		
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		submitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checkAnswer();
			}
		});
		submitButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		submitButton.setBounds(224, 279, 230, 46);
		panel.add(submitButton);
		
		JLabel plusLabel = new JLabel("+");
		plusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		plusLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		plusLabel.setBounds(227, 120, 40, 33);
		panel.add(plusLabel);
		
		JSlider slider = new JSlider();
		slider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				sliderLabel.setText(String.valueOf(slider.getValue()));
			}
		});
		slider.setMaximum(10);
		slider.setValue(0);
		slider.setMajorTickSpacing(1);
		slider.setPaintLabels(true);
		slider.setSnapToTicks(true);
		slider.setBounds(109, 219, 459, 51);
		panel.add(slider);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TutorMain.vertGrade(conn, total, correct, id, 2);
				frame.dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnExit.setBounds(224, 336, 230, 46);
		panel.add(btnExit);
	}
	
	public void checkAnswer() {
		if(Integer.parseInt(answerText.getText()) == 10 - Integer.parseInt(sliderLabel.getText())) {
			doCorrectAnswer();
		}
		else {
			doIncorrectAnswer();
		}
		answerText.setText("");
	}
	
}
