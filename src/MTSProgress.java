import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Connection;
import java.util.ArrayList;

public class MTSProgress {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public MTSProgress(Connection conn, int id) {
		initialize(conn);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Connection conn) {
		frame = new JFrame();
		frame.setBounds(100, 100, 656, 501);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student Grades");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(111, 11, 245, 50);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		JTextPane textPane = new JTextPane();
        scrollPane.setViewportView(textPane);
		scrollPane.setBounds(10, 72, 622, 381);
		frame.getContentPane().add(scrollPane);
		ArrayList<Student> students = TutorMain.getProgress();
		String result = "";
		for(int i = 0; i < students.size(); i++) {
			result += students.get(i).getId() + "   		  " 
					+ students.get(i).getActv1() + "	     " 
					+ students.get(i).getActv2() + "   	    " 
					+ students.get(i).getActv3() + "	   " 
					+ students.get(i).getActv4() + "      	 " 
					+ students.get(i).getActv5()+ "     	  " 
					+ students.get(i).getActv6() + "  	   " + "\n";
			
		}
		textPane.setText(result);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(22, 53, 30, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Activity 1");
		lblNewLabel_1_1.setBounds(136, 53, 58, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Acitivity 2");
		lblNewLabel_1_2.setBounds(224, 53, 58, 14);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Acitivity 3");
		lblNewLabel_1_3.setBounds(307, 53, 67, 14);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Acitivity 4");
		lblNewLabel_1_4.setBounds(384, 53, 58, 14);
		frame.getContentPane().add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Acitivity 5");
		lblNewLabel_1_5.setBounds(458, 53, 58, 14);
		frame.getContentPane().add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel("Acitivity 6");
		lblNewLabel_1_6.setBounds(536, 53, 58, 14);
		frame.getContentPane().add(lblNewLabel_1_6);
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
