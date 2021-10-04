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

public class MTSNumberLineAdd {

	private JFrame frame;
	private JTextField answerText;
	private int numCorrect = 0;
	private int numIncorrect = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MTSNumberLineAdd window = new MTSNumberLineAdd();
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
	public MTSNumberLineAdd() {
		initialize();
	}
	
	public void doCorrectAnswer() {
		numCorrect++;
		System.out.println("Correct!\nYou have correctly answered " + numCorrect + " times!");
	}
	
	public void doIncorrectAnswer() {
		numIncorrect++;
		System.out.println("Incorrect!\nYou have incorrectly answered " + numIncorrect + " times!");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 692, 430);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 678, 393);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel sliderLabel = new JLabel("5");
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
		submitButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		submitButton.setBounds(224, 296, 230, 46);
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
		slider.setPaintLabels(true);
		slider.setSnapToTicks(true);
		slider.setBounds(109, 219, 459, 51);
		panel.add(slider);
	}
	/*
	public void checkAnswer() {
		if(Integer.parseInt(answerText.getText()) == Integer.parseInt(sliderLabel.getText())) { //wtf
			doCorrectAnswer();
		}
		else {
			doIncorrectAnswer();
		}
	}
	*/
}
