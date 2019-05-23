package atcodingblocks.july10;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

public class TypingTutor extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel labtimer;
	JLabel labscore;
	JLabel labword;
	JTextField textword;
	JButton btnstart;
	JButton btnstop;
	boolean running;
	int timeRemaining;
	int score;
	Timer timer;
	String[] data;

	public TypingTutor(String[] args) {
		this.data = args;

		super.setTitle("Typing Tutor");

		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setExtendedState(MAXIMIZED_BOTH);

		Font font = new Font("comic Sans MS", 1, 100);
		Color color = new Color(0, 200, 200);

		GridLayout layout = new GridLayout(3, 2);
		super.setLayout(layout);

		labtimer = new JLabel();
		super.add(labtimer);
		labtimer.setFont(font);
		labtimer.setOpaque(true);
		// labtimer.setForeground(color);
		labtimer.setBackground(color);

		labscore = new JLabel();
		super.add(labscore);
		labscore.setFont(font);
		

		labword = new JLabel();
		super.add(labword);
		labword.setFont(font);

		textword = new JTextField();
		super.add(textword);
		textword.setFont(font);

		btnstart = new JButton();
		super.add(btnstart);
		btnstart.setFont(font);
		btnstart.addActionListener(this);

		btnstop = new JButton();
		super.add(btnstop);
		btnstop.setFont(font);
		btnstop.addActionListener(this);

		setgameup();

		super.setVisible(true);

	}

	public void setgameup() {
		
		running = false;
		timeRemaining = 50;
		score = 0;
		timer = new Timer(4000, this);

		labtimer.setText("Timer: " + timeRemaining);
		labscore.setText("Score: " + score);
		labword.setText("Word: ");

		textword.setEnabled(false);

		btnstart.setText("Start");
		btnstop.setText("Stop");
		btnstop.setEnabled(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnstart) {
			handlestart();
		} else if (e.getSource() == btnstop) {
			handlestop();
		} else if (e.getSource() == timer) {
			handletimer();
		}
	}

	private void handlestop() {

		timer.stop();

		int ans = JOptionPane.showConfirmDialog(this, "REplay?");
		if (ans == JOptionPane.YES_OPTION) {
			setgameup();
		} else {
			super.dispose();
		}
	}

	private void handletimer() {

		if (timeRemaining > 0) {
			timeRemaining--;
			String exp = labword.getText();
			String actual = textword.getText();
			if (exp.equals(actual) && actual.length() > 0) {
				score++;
			}
			labtimer.setText("Timer: " + timeRemaining);
			labscore.setText("Score: " + score);
			textword.setText("");
			textword.setFocusable(true);
			labword.setText(data[(int) (Math.random() * this.data.length)]);

		} else {
			handlestop();
		}

	}

	private void handlestart() {
		if (!running) {
			running = true;
			timer.start();
			textword.setEnabled(true);
			btnstart.setText("pause");
			btnstop.setEnabled(true);

		} else {
			running = false;
			timer.stop();
			textword.setEnabled(false);
			btnstart.setText("resume");

		}
	}
}
