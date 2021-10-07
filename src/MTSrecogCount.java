import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.concurrent.ThreadLocalRandom;

public class MTSrecogCount {	
	
	static int correct=0;
	static int total=0;
	int activity = 1;
	
	public static void MTSrecogCount(Connection conn, int id, String pwd) {
		int rand = ThreadLocalRandom.current().nextInt(1, 50 + 1);
		MTSrecogCount re = new MTSrecogCount();
			re.initialize(conn, id, pwd, rand, false);	
	}
	
	private void initialize(Connection conn, int id, String pwd, int start, boolean isCorrect) {//this sets up our gui for the problem using the start int
		JFrame frame;
		JTextField textField;
		
		frame = new JFrame();
		frame.setBounds(100, 100, 491, 337);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		if (isCorrect==true) {
			JLabel correctLabel = new JLabel("Correct! Please enter the next number");
			correctLabel.setBounds(21, 21, 290, 26);
			correctLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
			frame.getContentPane().add(correctLabel);
		} else {
			JLabel textLabel = new JLabel("Please enter the number that comes after..");
			textLabel.setBounds(21, 21, 290, 26);
			textLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
			frame.getContentPane().add(textLabel);
		}
		
		JLabel num1Label = new JLabel(start + " ");
		num1Label.setHorizontalAlignment(SwingConstants.RIGHT);
		num1Label.setBounds(-10, 100, 290, 56);
		num1Label.setFont(new Font("Times New Roman", Font.BOLD, 20));
		frame.getContentPane().add(num1Label);

		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setBounds(290, 113, 32, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton enterButton = new JButton("Enter");//when enter is pressed, proccess input through the counter
		
		boolean error = false;

		AbstractAction action = new AbstractAction()
		{
			boolean error = false;
			int ans=-40;
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		    	String ansString = textField.getText();
				System.out.println(ansString);
				try {
				ans = Integer.parseInt(ansString);
				} catch (NumberFormatException n) {//if input was not a number
					ans=-40;//we do this so we skip running through cCheckAns
					JFrame frame;
					
					frame = new JFrame();
					frame.setVisible(true);
					frame.setBounds(100, 100, 530, 370);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
					
					JLabel errorLabel = new JLabel("Error");
					errorLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
					errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
					frame.getContentPane().add(errorLabel);
					JLabel errorText = new JLabel("You have entered an invalid input, please retry");
					errorText.setHorizontalAlignment(SwingConstants.CENTER);
					frame.getContentPane().add(errorText);
					error = true;
					
					JButton retryButton = new JButton("Retry");
					frame.getRootPane().setDefaultButton(retryButton);
					AbstractAction action = new AbstractAction()
					{
					    @Override
					    public void actionPerformed(ActionEvent e)
					    {
					    	frame.removeAll();//or remove(JComponent)
							frame.revalidate();
							frame.repaint();
							frame.dispose();
							initialize(conn, id, pwd, start, false);									
					    }
					};
					
					retryButton.addActionListener(action);
					frame.getRootPane().setDefaultButton(retryButton);
					retryButton.requestFocus();
					frame.getContentPane().add(retryButton);
				
				}
				frame.removeAll();//or remove(JComponent)
				frame.revalidate();
				frame.repaint();
				frame.dispose();

				try {
					if(error==false) {
					checkAns(conn, id, pwd, start, ans);//run through cCheckAns
					}
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		};
		
		 JButton doneButton = new JButton( new AbstractAction("Done") {
		        @Override
		        public void actionPerformed( ActionEvent e ) {
		            // add Action
		        	done(conn, id, pwd, total, correct);
		        	frame.dispose();
		        }
		    });
		doneButton.setBounds(153, 162, 141, 32); 
		frame.getContentPane().add(doneButton);
		
		enterButton.addActionListener(action);
		textField.addActionListener( action );
		frame.getRootPane().setDefaultButton(enterButton);
		enterButton.requestFocus();
		enterButton.setBounds(303, 162, 141, 32);
		frame.getContentPane().add(enterButton);
		
	}
	
	private void checkAns(Connection conn, int id, String pwd, int start, int ans) throws InterruptedException {
		int correctAns = start+1;
		total++;
		if (ans==-40) {//-40 means invalid input, so do nothing
			
		} else {
		if (ans==correctAns) {//if the answer was correct, increase correct number and run again using our ans
			correct++;
			//System.out.println(start + " " + ans);
			initialize(conn, id, pwd, ans, true);
		} else {
		if (ans!=correct&&ans!=0) {//if answer is not correct and we havent inputted the exit command, test is done and results are given. Exit will be a button in finished product
			JFrame frame;
			
			frame = new JFrame();
			frame.setVisible(true);
			frame.setBounds(100, 100, 530, 370);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
			
			JLabel cLabel = new JLabel("Incorrect");
			cLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
			cLabel.setHorizontalAlignment(SwingConstants.CENTER);
			frame.getContentPane().add(cLabel);
			
			JLabel textLabel = new JLabel("The number you entered was incorrect. Your grade was: " + correct + "/" + total);
			textLabel.setHorizontalAlignment(SwingConstants.CENTER);
			frame.getContentPane().add(textLabel);
			
			JButton enterButton = new JButton("Retry");
			frame.getRootPane().setDefaultButton(enterButton);
			AbstractAction action = new AbstractAction()
			{
			    @Override
			    public void actionPerformed(ActionEvent e)//remove everything because we are done
			    {
			    	frame.removeAll();//or remove(JComponent)
					frame.revalidate();
					frame.repaint();
					frame.dispose();
					initialize(conn, id, pwd, start, enabled);
			    }
			};
			
			 JButton doneButton = new JButton( new AbstractAction("Done") {
			        @Override
			        public void actionPerformed( ActionEvent e ) {
			            // add Action
			        	done(conn, id, pwd, total, correct);
			        	frame.dispose();
			        }
			    });
			frame.getContentPane().add(doneButton);
			
			enterButton.addActionListener(action);
			frame.getRootPane().setDefaultButton(enterButton);
			enterButton.requestFocus();
			frame.getContentPane().add(enterButton);
			
		} }}
		}
	public void done(Connection conn, int id, String pwd, int total, int correct) {
		TutorMain.vertGrade(conn, total, correct, id, 1);
		System.out.println("DONE!");
		JFrame frame;
		
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 530, 370);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel cLabel = new JLabel("Finished");
		cLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		cLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(cLabel);
		int newTotal = total-1;
		JLabel textLabel = new JLabel("You have manually finished this activity. Your grade was: " + correct + "/" + total);
		textLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(textLabel);
		
		JButton enterButton = new JButton("Okay");
		frame.getRootPane().setDefaultButton(enterButton);
		AbstractAction action = new AbstractAction()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		    	frame.removeAll();//or remove(JComponent)
				frame.revalidate();
				frame.repaint();
				frame.dispose();
		    }
		};
		
		enterButton.addActionListener(action);
		frame.getRootPane().setDefaultButton(enterButton);
		enterButton.requestFocus();
		frame.getContentPane().add(enterButton);
    	
	}
}
