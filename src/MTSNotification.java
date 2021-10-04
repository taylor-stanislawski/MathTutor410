import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

public class MTSNotification {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MTSNotification window = new MTSNotification();
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
	public MTSNotification(String id) {
		initialize(id);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String id) {
		frame = new JFrame();
		frame.setBounds(100, 100, 325, 131);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel idLabel = new JLabel("Your New ID is: " + id);
		idLabel.setBounds(97, 23, 116, 14);
		frame.getContentPane().add(idLabel);
		
		JButton okButton = new JButton("OK");
		okButton.setBounds(111, 53, 89, 23);
		frame.getContentPane().add(okButton);
	}
	
	public JFrame getFrame() {
		return frame;
	}

}
