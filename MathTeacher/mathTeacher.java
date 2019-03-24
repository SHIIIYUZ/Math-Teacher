
import java.awt.event.*;



import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import java.awt.*;

/**
 * Title      : mathTeacher.java
 * Description: It can generate random math questions and judge the user's answer.
 * @author      Shiyu Zhang
 * @version     1.0
 */

public class mathTeacher extends JFrame{

	static JButton myButton,nextQues; //myButton shows answers. nextQues shows next questions.
	static JButton zero,one,two,three,four,five,six,seven,eight,nine;//Nine buttons composite keyboard in GUI.
	static JTextField text;//Input answers.
	static JLabel label1,label2,labelResult;//label1 shows questions. label2 shows answers and correctness. label3 shows accuracy.
	static int textCount=0;//Count the times of text input. If the first time is right, then user can get a score.
	static int questionCount=1;//Count the number of questions.
	static int firstAnswer;//The answer to the first question.
	static int nextAnswer;//The answer to the following question.
	static boolean hasAnswered;//User has input or not.
	static int score;//User's score.
	buttonListener b=new buttonListener();
	textListener t=new textListener();
	nextQuesListener q=new nextQuesListener();
	keyBoardListener k=new keyBoardListener();
	
	public static void main(String args[]) {
		new mathTeacher();
	}
	
	/**Constructor for the class. It sets the layout of the application and assign function to each element.
	 * 
	 */

	public mathTeacher() {
		myButton = new JButton("Press for answer");
		nextQues = new JButton("Next question");
		text=new JTextField();
		label1=new JLabel();
		label2=new JLabel();
		labelResult=new JLabel();
		zero=new JButton("0");
		one=new JButton("1");
		two=new JButton("2");
		three=new JButton("3");
		four=new JButton("4");
		five=new JButton("5");
		six=new JButton("6");
		seven=new JButton("7");
		eight=new JButton("8");
		nine=new JButton("9");
		
		myButton.addActionListener(b);
		text.addActionListener(t);
		
		// To constrain the input. Only numbers and a minus symbol allowed.
		text.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				if((c>=KeyEvent.VK_0&&c<=KeyEvent.VK_9)||c==KeyEvent.VK_MINUS) {
					
				}
				else {
					e.consume();
				}
			}
			public void keyPressed(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
				
			}
		});
		
		nextQues.addActionListener(q);
		Container conPane = getContentPane();
		JPanel p=new JPanel();
		p.setLayout(null);
		p.add(label1);
		label1.setBounds(50, 40, 150, 40);
		p.add(text);
		text.setBounds(200, 40, 150, 40);;
		p.add(label2);
		label2.setBounds(50, 80, 300, 40);;
		p.add(myButton);
		myButton.setBounds(50, 120, 300, 30);
		p.add(nextQues);
		nextQues.setBounds(50, 160, 300, 30);
		p.add(labelResult);
		labelResult.setBounds(50,200,300,30);
		
		JPanel p1=new JPanel();
		p1.setLayout(new GridLayout(3,4));
		p1.add(zero);
		zero.addActionListener(k);
		p1.add(one);
		one.addActionListener(k);
		p1.add(two);
		two.addActionListener(k);
		p1.add(three);
		three.addActionListener(k);
		p1.add(four);
		four.addActionListener(k);
		p1.add(five);
		five.addActionListener(k);
		p1.add(six);
		six.addActionListener(k);
		p1.add(seven);
		seven.addActionListener(k);
		p1.add(eight);
		eight.addActionListener(k);
		p1.add(nine);
		nine.addActionListener(k);
		
		JPanel p2=new JPanel();
		p2.setLayout(null);
		p2.add(p);
		p.setBounds(0,0,400,300);
		p2.add(p1);
		p1.setBounds(50,300,300,100);
		
		conPane.add(p2);
		

		this.setTitle("Math Teacher");
		this.setVisible(true);
		this.setSize(400,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		firstAnswer=mathTeacher.question();
		
		//To constrain the length of input.
		text.setDocument(new PlainDocument() {  
		    int MAX_LENGTH = 3;  
		    public void insertString(int offset, String s,  
		            AttributeSet attributeSet) throws BadLocationException {  
		        if (s == null || offset < 0) {  
		            return;  
		        }  
		        for (int i = 0; i < s.length(); i++) {  
		            if (getLength() > MAX_LENGTH - 1) {  
		                break;  
		            }  
		            super.insertString(offset + i, s.substring(i, i + 1),  
		                    attributeSet);  
		        }  
		        return;  
		    }  
		});  
	}

	/**This method generate random math questions.
	 * @return the answer of the question.
	 */
	public static int question() {
		int a,b,c;
		double answer;
		answer=0;
		a=0;
		b=0;
		c=0;
		
		do{
			a=(int)(1+Math.random()*10);
			b=(int)(1+Math.random()*10);
			c=(int)(1+Math.random()*4);
			
			switch(c) {
				case 1:
					answer=a+b;
					break;
				case 2:
					answer=a-b;
					break;
				case 3:
					answer=a*b;
					break;
				case 4:
					answer=(float)a*b/(float)b;
					break;
			    }
			
			
			if(((int)answer-answer)==0) {
				switch(c){
				case 1:
					label1.setText("Question "+a+"+"+b);
					break;
				case 2:
					label1.setText("Question "+a+"-"+b);
					break;
				case 3:
					label1.setText("Question "+a+"*"+b);
					break;
				case 4:
					label1.setText("Question "+a*b+"/"+b);
					break;
				}
				
				break;	
			}
		}while(((int)answer-answer)!=0);
	
		return (int)answer;	
	
	}
	
}

//When pressed the answer button, answer will be shown.
class buttonListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		if(!mathTeacher.text.getText().equals("")) {
			if(mathTeacher.questionCount==1) {
				mathTeacher.label2.setText("answer is "+mathTeacher.firstAnswer);
			}
			else {
				mathTeacher.label2.setText("answer is "+mathTeacher.nextAnswer);
			}
		}

	}
}

//Text shown is to judge and respond whether the user has answered correctly.
class textListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		mathTeacher.textCount++;
		if(!mathTeacher.text.getText().equals("")) {
			mathTeacher.hasAnswered=true;
			int a=Integer.parseInt(mathTeacher.text.getText());
			if(mathTeacher.questionCount==1) {
				if(a==mathTeacher.firstAnswer) {
					if(mathTeacher.textCount==1) {
						mathTeacher.label2.setText("You are right! Get 1 mark!");
						mathTeacher.score++;
					}
					else {
						mathTeacher.label2.setText("You are right!");
					}
				}
				else {
					mathTeacher.label2.setText("You are wrong");
				}
			}
			else {
				if(a==mathTeacher.nextAnswer) {
					if(mathTeacher.textCount==1) {
						mathTeacher.label2.setText("You are right! Get 1 mark!");
						mathTeacher.score++;
					}
					else {
						mathTeacher.label2.setText("You are right!");
					}
				}
				else {
					mathTeacher.label2.setText("You are wrong");
				}
				
			}

		}

	}
}


//The next question button is to change to the next question and clear the work space for the last question.
class nextQuesListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		if(mathTeacher.hasAnswered==true) {
			mathTeacher.nextAnswer=mathTeacher.question();
			mathTeacher.text.setText(""); 
			mathTeacher.label2.setText("");
			mathTeacher.hasAnswered=false;
			mathTeacher.textCount=0;
			mathTeacher.questionCount++;
			mathTeacher.labelResult.setText(""+mathTeacher.score+" out of "+(mathTeacher.questionCount-1));
		}

	}
}

//keyBoard is to make every button can make input in the text field.
class keyBoardListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		String s=mathTeacher.text.getText();
		if(e.getSource()==mathTeacher.zero) {
			mathTeacher.text.setText(""+s+0);
		}
		else if(e.getSource()==mathTeacher.one) {
			mathTeacher.text.setText(""+s+1);
		}
		else if(e.getSource()==mathTeacher.two) {
			mathTeacher.text.setText(""+s+2);
		}
		else if(e.getSource()==mathTeacher.three) {
			mathTeacher.text.setText(""+s+3);
		}
		else if(e.getSource()==mathTeacher.four) {
			mathTeacher.text.setText(""+s+4);
		}
		else if(e.getSource()==mathTeacher.five) {
			mathTeacher.text.setText(""+s+5);
		}
		else if(e.getSource()==mathTeacher.six) {
			mathTeacher.text.setText(""+s+6);
		}
		else if(e.getSource()==mathTeacher.seven) {
			mathTeacher.text.setText(""+s+7);
		}
		else if(e.getSource()==mathTeacher.eight) {
			mathTeacher.text.setText(""+s+8);
		}
		else if(e.getSource()==mathTeacher.nine) {
			mathTeacher.text.setText(""+s+9);
		}
	}
}


