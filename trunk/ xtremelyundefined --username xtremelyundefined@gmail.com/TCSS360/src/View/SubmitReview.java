package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import Model.Paper;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.TransferHandler;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import Model.User;

@SuppressWarnings("serial")
public class SubmitReview extends JFrame {
	public static final int TEXT_HEIGHT = 30;
	public static final int TEXT_WIDTH = 30;
	public static final int WINDOW_HEIGHT = 300;
	public static final int WINDOW_WIDTH = 500;
	
	public SubmitReview(Paper thepaper, User thereviewer) {
		super();
		//this.setTitle("Recommendation Form: " + thepaper.getTitle()); 
		this.add(createPanel());
		//this.setResizable(false);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}
	
	public JPanel createPanel() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JPanel sPanel = new JPanel();
		JPanel nPanel = new JPanel(new FlowLayout((int) LEFT_ALIGNMENT));
		JPanel cPanel = new JPanel();
		cPanel.setLayout(new BorderLayout());
		
		ButtonGroup btng = new ButtonGroup();
		nPanel.add(new JLabel("Summary Recommendation:"));
		
		for (int i = 5; i >= 1; i--) {
			JRadioButton btn = new JRadioButton("[" + Integer.toString(i) + "]");
			btng.add(btn);
			nPanel.add(btn);
		}
		
		JPanel cP1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel cP2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel msg1 = new JLabel("Rationale for Recommendation:");
		cP1.add(msg1);
		JTextArea txt = new JTextArea(TEXT_HEIGHT, TEXT_WIDTH);
		txt.setEditable(true);
		txt.setMaximumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
		txt.setLineWrap(true);
		txt.setWrapStyleWord(true);
		JScrollPane scroll = new JScrollPane(txt);
		scroll.setBorder(BorderFactory.createEtchedBorder());
		scroll.setPreferredSize(new Dimension(300, 150));
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		cP2.add(scroll);
		cPanel.add(cP1, BorderLayout.NORTH);
		cPanel.add(cP2, BorderLayout.CENTER);
	
		
		JButton submit = new JButton("Submit");
		JButton cancel = new JButton("Cancel");
		sPanel.add(submit);
		sPanel.add(cancel);
		
		panel.add(cPanel, BorderLayout.CENTER);
		panel.add(sPanel, BorderLayout.SOUTH);
		panel.add(nPanel, BorderLayout.NORTH);
		setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		return panel;
	}
}
