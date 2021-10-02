import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class twoDigitCarry {
	
	public static int total = 0;
	public static int correct= 0;

	public static void twoDigitCarry() {
		int rand = ThreadLocalRandom.current().nextInt(1, 6);
		System.out.println(rand);
		rand = rand * 10;
		int rand1 = ThreadLocalRandom.current().nextInt(5, 10);
		
		rand = rand + rand1;
		System.out.println(rand);
		
		int rand2 = ThreadLocalRandom.current().nextInt(1, 5);
		System.out.println(rand2);
		rand2 = rand2 * 10;
		int rand3 = ThreadLocalRandom.current().nextInt(4, 10);
		
		rand2 = rand2 + rand3;
		System.out.println(rand2);
		twoDigitCarry re = new twoDigitCarry();
		
			re.counter(rand, rand2);
	}
	
	public void cCheckAns(int rand, int rand1, int ans) {
		total++;
		if (rand + rand1 == ans) {
			correct++;
			System.out.println(correct + "/" + total);
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
			
			JLabel textLabel = new JLabel(rand + " + " + rand1 + " = " + ans);
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

					twoDigitCarry();
			    }
			};
			
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
			
			JLabel textLabel = new JLabel(rand + " + " + rand1 + " + " + " != 20.");
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
						twoDigitCarry();} catch (IllegalArgumentException x) {
						}
			    }
			};
			
			enterButton.addActionListener(action);
			frame.getRootPane().setDefaultButton(enterButton);
			enterButton.requestFocus();
			frame.getContentPane().add(enterButton);	
		}
	}
	
	public void counter(int rand, int rand1) {
		JFrame frame;
		JTextField textField;
		
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 530, 370);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel cLabel = new JLabel("Add two digits (with carry)");
		cLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		cLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(cLabel);
		
		JLabel textLabel = new JLabel(rand + " + " + rand1 + " = _____");
		textLabel.setHorizontalAlignment(SwingConstants.CENTER);
		textLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(textLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
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
							counter(rand, rand1);									
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

				cCheckAns(rand, rand1, ans);//run through cCheckAns
			
		    }
		};
		enterButton.addActionListener(action);
		textField.addActionListener( action );
		frame.getRootPane().setDefaultButton(enterButton);
		enterButton.requestFocus();
		frame.getContentPane().add(enterButton);
	}
	}

