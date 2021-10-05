import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class threeValuesupto20 {
	
	static int total = 0;
	static int correct= 0;
	int activity = 4;

	public static void threeValuesupto20(Connection conn, int id, String pwd) {
		int rand = ThreadLocalRandom.current().nextInt(1, 17);
		//System.out.println(rand);
		int temp = rand-1;
		int rand1 = ThreadLocalRandom.current().nextInt(1, 17 - temp);
		//System.out.println(rand1);
		temp =  rand + rand1;
		int temp2 = 20-temp;
		int rand2 = ThreadLocalRandom.current().nextInt(1, temp2);
		threeValuesupto20 re = new threeValuesupto20();
		
			re.initialize(conn, id, pwd, rand, rand1, rand2);
	}
	
	public void initialize(Connection conn, int id, String pwd, int rand, int rand1, int rand2) {
		JFrame frame;
		JTextField textField;
		
		frame = new JFrame();
		frame.setBounds(100, 100, 491, 337);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		JLabel textLabel = new JLabel("Input the sum of these numbers");
		textLabel.setBounds(21, 21, 290, 26);
		textLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		frame.getContentPane().add(textLabel);
		
		JLabel num1Label = new JLabel(rand + " + " + rand1 + " + " + rand2 + " = ");
		num1Label.setHorizontalAlignment(SwingConstants.RIGHT);
		num1Label.setBounds(0, 100, 290, 56);
		num1Label.setFont(new Font("Times New Roman", Font.BOLD, 20));
		frame.getContentPane().add(num1Label);

		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setBounds(290, 113, 32, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton enterButton = new JButton("Enter");//when enter is pressed, proccess input through the counter

		AbstractAction action = new AbstractAction()
		{
			boolean error = false;
			int ans=-40;
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		    	String ansString = textField.getText();
				//System.out.println(ansString);
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
							initialize(conn, id, pwd, rand, rand1, rand2);
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
				
				if (ans!=-40) {
				checkAns(conn, id, pwd, rand, rand1, rand2, ans);//run through cCheckAns
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
	
	public void checkAns(Connection conn, int id, String pwd, int rand, int rand1, int rand2, int ans) {
		total++;
		if (rand + rand1 + rand2 == ans) {
			correct++;
			//System.out.println(correct + "/" + total);
			JFrame frame;
			
			frame = new JFrame();
			frame.setVisible(true);
			frame.setBounds(100, 100, 530, 370);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
			
			JLabel cLabel = new JLabel("Correct!");
			cLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
			cLabel.setHorizontalAlignment(SwingConstants.CENTER);
			frame.getContentPane().add(cLabel);
			
			JLabel textLabel = new JLabel(rand + " + " + rand1 + " + " + rand2 + " = " + ans);
			textLabel.setHorizontalAlignment(SwingConstants.CENTER);
			frame.getContentPane().add(textLabel);
			
			JLabel gradeLabel = new JLabel("Score: " + correct + "/" + total);
			gradeLabel.setHorizontalAlignment(SwingConstants.CENTER);
			frame.getContentPane().add(gradeLabel);
			
			JButton enterButton = new JButton("Do again");
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

					threeValuesupto20(conn, id, pwd);
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
		} else {
			JFrame frame;
			
			frame = new JFrame();
			frame.setVisible(true);
			frame.setBounds(100, 100, 530, 370);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
			
			JLabel cLabel = new JLabel("Incorrect.");
			cLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
			cLabel.setHorizontalAlignment(SwingConstants.CENTER);
			frame.getContentPane().add(cLabel);
			
			JLabel textLabel = new JLabel(rand + " + " + rand1 + " + " + rand2 + " != " + ans + ".");
			textLabel.setHorizontalAlignment(SwingConstants.CENTER);
			frame.getContentPane().add(textLabel);
			
			JLabel gradeLabel = new JLabel("Score: " + correct + "/" + total);
			gradeLabel.setHorizontalAlignment(SwingConstants.CENTER);
			frame.getContentPane().add(gradeLabel);
			
			JButton enterButton = new JButton("Try again?");
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
					
					try {
						threeValuesupto20(conn, id, pwd);} catch (IllegalArgumentException x) {
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
			frame.getContentPane().add(doneButton);
			
			enterButton.addActionListener(action);
			frame.getRootPane().setDefaultButton(enterButton);
			enterButton.requestFocus();
			frame.getContentPane().add(enterButton);	
		}
	}
	
	public void done(Connection conn, int id, String pwd, int total, int correct) {
		//TutorMain.vertGrade(conn, total, correct, id, activity);
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
