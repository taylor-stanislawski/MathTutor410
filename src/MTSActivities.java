import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;

public class MTSActivities {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MTSActivities window = new MTSActivities();
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
	public MTSActivities() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 513, 297);
		frame.getContentPane().add(panel);
		panel.setLayout(new MigLayout("", "[513px]", "[40px][40px][40px][40px][40px][40px][40px][40px]"));
		
		JLabel activityListLabel = new JLabel("Select an Activity");
		activityListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		activityListLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		activityListLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(activityListLabel, "cell 0 0,grow");
		
		JButton activity1Button = new JButton("Counting");
		activity1Button.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(activity1Button, "cell 0 1,alignx center,aligny center");
		
		JButton activity2Button = new JButton("Activity 2");
		activity2Button.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(activity2Button, "cell 0 2,alignx center,aligny center");
		
		JButton activity3Button = new JButton("Activity 3");
		activity3Button.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(activity3Button, "cell 0 3,alignx center,aligny center");
		
		JButton activity4Button = new JButton("Activity 4");
		activity4Button.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(activity4Button, "cell 0 4,alignx center,aligny center");
		
		JButton activity5Button = new JButton("Activity 5");
		activity5Button.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(activity5Button, "cell 0 5,alignx center,aligny center");
		
		JButton activity7Button = new JButton("Activity 6");
		activity7Button.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(activity7Button, "cell 0 6,alignx center,aligny center");
		
		JButton activity6button = new JButton("Activity 7");
		activity6button.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(activity6button, "cell 0 7,alignx center,aligny center");
		frame.setBounds(100, 100, 529, 336);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
