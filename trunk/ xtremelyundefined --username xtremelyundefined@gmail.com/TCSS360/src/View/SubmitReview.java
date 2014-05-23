package View;

import java.awt.Dimension;

import Model.Paper;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import Model.User;

@SuppressWarnings("serial")
public class SubmitReview extends JFrame {
	public static final int TEXT_HEIGHT = 10;
	public static final int TEXT_WIDTH = 30;
	public static final int WINDOW_HEIGHT = 500;
	public static final int WINDOW_WIDTH = 500;
	
	public SubmitReview(Paper thepaper, User thereviewer) {
		super();
		//this.setTitle("Recommendation Form: " + thepaper.getTitle()); 
		this.add(createPanel());
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public JPanel createPanel() {
		
		JPanel panel = new JPanel();
		ButtonGroup btng = new ButtonGroup();
		panel.add(new JLabel("Summary Recommendation:"));
		for (int i = 5; i >= 1; i--) {
			JRadioButton btn = new JRadioButton("[" + Integer.toString(i) + "]");
			btng.add(btn);
			panel.add(btn);
		}
		
		panel.add(new JLabel("Rationale for Recommendation:"));
		panel.add(new JTextArea(TEXT_HEIGHT, TEXT_WIDTH));
		setPreferredSize(new Dimension(WINDOW_HEIGHT, WINDOW_WIDTH));
		return panel;
	}
}

