import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class MTSNotification {

	private JFrame frame;
	private JLabel idLabel;

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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		idLabel = new JLabel("Your New ID is: " + id);
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setBounds(9, 23, 293, 14);
		frame.getContentPane().add(idLabel);
		
		JButton okButton = new JButton("OK");
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		okButton.setBounds(111, 53, 89, 23);
		frame.getContentPane().add(okButton);
	}
	
	public void setNotificationText(String input) {
		idLabel.setText(input);
	}
	
	public JFrame getFrame() {
		return frame;
	}

}
