
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class RadioQuestion extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	int correctAns;
	Quiz quiz;
	int selected;
	boolean used;
	static int score = 0;
	// questions
	
	JPanel questionPanel = new JPanel();
	// answers
	JPanel answerPanel = new JPanel();
	JRadioButton[] reponse;
	ButtonGroup group = new ButtonGroup();
	// bottom
	JPanel botPanel = new JPanel();
	private JButton next = new JButton("Next");
	JButton finish = new JButton("Finish");
	
	JLabel scorePanel=new JLabel("Votre Score est :"+score);
	

	public RadioQuestion(String question, String[] options, int correctAnswer, Quiz quiz) {
		this.quiz = quiz;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		correctAns = correctAnswer;
		//Score
		score=0;
		
		
		add(scorePanel);
		
		// Question
		questionPanel.add(new JLabel(question));
		add(questionPanel);
		
		// Answer
		reponse = new JRadioButton[options.length];
		
		for (int i = 0; i < options.length; i++) {
			reponse[i] = new JRadioButton(options[i]);
			reponse[i].addActionListener(this);
			group.add(reponse[i]);
			answerPanel.add(reponse[i]);
		}
		add(answerPanel);
		// bottom
		next.addActionListener(this);
		finish.addActionListener(this);
		botPanel.add(next);
		botPanel.add(finish);
		add(botPanel);
	}

	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		// next button
		if (src.equals(next)) {
			//showResult();
			if (selected == correctAns) {
				used = true;
				score+=20;
				quiz.next();
				System.out.println(score);
			}else {
				System.out.println(score);
				quiz.next();
			}
		}
		// finish button
		if (src.equals(finish)) {
			quiz.showSummary();
		}
		// radio buttons
		for (int i = 0; i < reponse.length; i++) {
			if (src == reponse[i]) {
				selected = i;
			}
		}
	}

	public void showResult() {
		String text = reponse[selected].getText();
		quiz.total++;
		if (selected == correctAns) {
			JOptionPane.showMessageDialog(null, text + " is correct!\nGood Job!");
		} else {
			quiz.wrongs++;
			JOptionPane.showMessageDialog(null, text + " is wrong.\nTry again!");
		}
	}
}
