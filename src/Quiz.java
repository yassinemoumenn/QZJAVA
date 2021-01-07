
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.util.Random;
import javax.swing.JOptionPane;

public class Quiz extends JFrame {
	JPanel panel = new JPanel();
	CardLayout cards = new CardLayout();
	int nombreQuestion;
	int wrongs = 0;
	int total = 0;

	String[][] answers = { { "Compilé", "Interprété", "Compilé et interpreté" }, { "Main", "Object", "AWT" },
			{ "est en minuscule", "commence par une majuscule", "est en majuscule" }, { "True", "False" },
			{ "Interface", "Classe parent", "Package" },

	};

	RadioQuestion questions[] = {

			new RadioQuestion("JAVA est un langage?", answers[0], 2, this),
			new RadioQuestion("Toutes les classes héritent de la classe?", answers[1], 1, this),
			new RadioQuestion("Par convention une classe?", answers[2], 1, this),
			new RadioQuestion("Est-ce que on peut avoir plusieurs constructeurs pour la même classe?", answers[3], 0,
					this),

			new RadioQuestion(
					"<html>Après la compilation, un programme écrit en JAVA, il se transforme en programme  en bytecodeQuelle est l’extension du programme en bytecode?</html>",
					answers[4], 0, this),
			new RadioQuestion("Dans la ligne \"public class A implements B\", B est :?", answers[4], 0, this),
			new RadioQuestion("Dans la ligne \"public class A implements B\", B est :?", answers[4], 0, this),
			new RadioQuestion("Dans la ligne \"public class A implements B\", B est :?", answers[4], 0, this),

	};

	public static void main(String args[]) {
		new Quiz("Quiz en Java");
	}

	public Quiz(String title) throws HeadlessException {
		super(title);

		setSize(800, 800);
		panel.setLayout(cards);
		nombreQuestion = questions.length;
		for (int i = 0; i < nombreQuestion; i++) {
			panel.add(questions[i], "q" + i);
		}
		Random r = new Random();
		int i = r.nextInt(nombreQuestion);
		cards.show(panel, "q" + i);
		setBackground(Color.BLACK);
		setResizable(true);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(panel);
		setVisible(true);
	}

	public void next() {
		if (total == nombreQuestion) {
			// showSummary();
		} else {
			Random r = new Random();
			boolean found = false;
			int i = 0;
			while (!found) {
				i = r.nextInt(nombreQuestion);
				if (!questions[i].used) {
					found = true;
				}
			}
			cards.show(panel, "q" + i);
		}
	}

	public void showSummary() {
		JOptionPane.showMessageDialog(null,
				"That's it! Here is your summary:" + "\nYou answered " + wrongs + " questions wrong" + "\nYou answered "
						+ (total - wrongs) + " right" + "\nGiving a correct answer chance: \t\t"
						+ (int) (((float) (total - wrongs) / total) * 100) + "%");
		System.exit(0);
	}
}
