package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.ScrollPane;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import Model.Paper;
import Model.Review;
import Model.User;

@SuppressWarnings("serial")
public class SubmitReview extends JFrame {
	private static final int TEXT_HEIGHT = 50;
	private static final int TEXT_WIDTH = 500;
	private static final int LAST_QUESTION = 21;
	
	private Paper paper;
	private User currentUser;
	private List<ButtonGroup> btngroups;
	private List<JTextArea> comments;
	
	
	private String instructions = "Instructions to Reviewers: \n "
			+ "Please provide a numeric rating on a 5-point scale for each question,along with a brief \n "
			+ "rationale for each numeric rating. In doing so, please discuss both the strengths and \n"
			+ "the weaknesses of each paper so that the editorsand authors can understand your reasoning. \n"
			+ "Please phrase your reviews politely; even \'bad\' papers represent a lot of work on the part \n"
			+ "of the authors. The review may be the basis for further revisions of the paper or the work \n"
			+ "that the paper reports. We all know how hurtful a needlessly negative review can be, \n"
			+ "and how helpful a positive one can be; please try to bear that in mind when \n"
			+ "you are writing yours.";
	
	private String[] prompts = new String[34]; //number of prompts needed
	
	public SubmitReview(Paper thepaper, User theuser) {
		super();
		paper = thepaper;
		currentUser = theuser;
		btngroups = new ArrayList<ButtonGroup>();
		comments = new ArrayList<JTextArea>();
		JOptionPane.showMessageDialog(this, instructions);
		initializePrompts();
		setTitle("Submit a Review for " + paper.getTitle());
		FlowLayout lyout = new FlowLayout();
		lyout.setHgap(30);
		JPanel pane = new JPanel(lyout);
		pane.add(createCenterPane());
		add(pane);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}
	
	private void initializePrompts() {
		prompts[0] = "Can the content be directly applied by classroom instructors or curriculum designers?";
		prompts[1] = "Directly and obviously applicable";
		prompts[2] = "Not applicable to classroom instruction or curriculum design";
		prompts[3] = "Does the work appeal to a broad readership interested in engineering education or is it"
					+" narrowly specialized?";
		prompts[4] = "Broad";
		prompts[5] = "Narrow";
		prompts[6] = "Does the work address a significant problem?";
		prompts[7] = "Significant";
		prompts[8] = "Insignificant";
		prompts[9] = "Does the author build upon relevant refernces and bodies of knowledge";
		prompts[10] = "Relevant and sufficient references to existing bodies of knowledge";
		prompts[11] = "Few if any relevant references";
		prompts[12] = "If a teaching invtervention is reported, is it adequately evaluated in terms of its "
				+ "impact on learning in actual use?";
		prompts[13] = "Excellent evaluation";
		prompts[14] = "Inadequate evaluation";
		prompts[15] = "Does the author use methods appropriate to the goals, both for the instructional"
				+ " intervention and the evaluation of impact on learning?";
		prompts[16] = "Appropriate methods";
		prompts[17] = "Inappropriate or unclear methods";
		prompts[18] = "Did the author provide sufficient detail to replicate and evaluate?";
		prompts[19] = "Sufficient detail";
		prompts[20] = "Insufficient detail";
		prompts[21] = "Is the paper clearly and carefully written";
		prompts[22] = "Does the paper adhere to standards ofstyle, usage, and composotion?";
		prompts[23] = "Excellent";
		prompts[24] = "Very good";
		prompts[25] = "Acceptable";
		prompts[26] = "Weak";
		prompts[27] = "Unacceptable";
		prompts[28] = "Strong accept";
		prompts[29] = "Accept";
		prompts[30] = "Neutral";
		prompts[31] = "Reject";
		prompts[32] = "Strong reject";
		
	}
	
	private JPanel createSouthPanel() {
		JPanel panel = new JPanel(new FlowLayout());
		JButton submit = new JButton("Submit");
		submit.addActionListener(new SubmitAction());
		JButton cancel = new JButton("Cancel");
		panel.add(submit);
		panel.add(cancel);
		return panel;
	}
	
	private JScrollPane newText(){
		JTextArea txt = new JTextArea();
		txt.setBorder(BorderFactory.createEtchedBorder());
		txt.setWrapStyleWord(true);
		txt.setLineWrap(true);
		txt.setEditable(true);
		txt.setMaximumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
		JScrollPane scroll = new JScrollPane(txt);
		scroll.setBorder(BorderFactory.createEtchedBorder());
		scroll.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		comments.add(txt);
		return scroll;
	}
	
	public ScrollPane createCenterPane() {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 4;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(new JLabel("Confidential Comments to the SubProgramChair:"), c);
		
		++c.gridy;
		c.gridx = 0;
		c.gridwidth = 6;
		c.ipady = 10;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = GridBagConstraints.REMAINDER;
		panel.add(newText(), c);
		
		++c.gridy;
		c.gridwidth = 4;
		c.anchor = GridBagConstraints.WEST;
		panel.add(new JLabel("Comments to the Author:"), c);
		
		++c.gridy;
		c.gridx = 0;
		c.gridwidth = 6;
		c.ipady = 10;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = GridBagConstraints.REMAINDER;
		panel.add(newText(), c);
		
		
		int questionNumber = 1;
		++c.gridy;
		for(int i = 0; i < LAST_QUESTION; i+=3) {
			
			c.gridx = 0;
			c.gridwidth = 1;
			c.ipady = 40;
			c.weightx = .025;
			panel.add(new JLabel(questionNumber++ + ". "), c);

			c.ipady = 0;
			c.gridx = 1;
			c.gridwidth = 10;
			panel.add(new JLabel(prompts[i]), c);

			++c.gridy;
			c.gridx = 0;
			c.anchor = GridBagConstraints.WEST;
			ButtonGroup btng = new ButtonGroup();
			for (int j = 5; j > 0; j--) {
				c.gridwidth = 1;
				c.weightx = .2;
				JRadioButton btn = new JRadioButton(j + "");
				++c.gridx;
				panel.add(btn, c);
				btng.add(btn);	
			}
			btngroups.add(btng);

			++c.gridy;
			c.gridx = 1;
			c.gridwidth = 1;
			c.fill = GridBagConstraints.NONE;
			panel.add(createLabel(prompts[i+1], panel.getBackground()), c);


			c.gridx = 5;
			c.gridwidth = 1;
			c.fill = GridBagConstraints.NONE;
			panel.add(createLabel(prompts[i+2], panel.getBackground()), c);

			c.gridx = 0;
			c.gridwidth = 6;
			c.ipady = 30;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = GridBagConstraints.REMAINDER;
			++c.gridy;
			panel.add(newText(), c);
			++c.gridy;
		}

		++c.gridy;
		c.gridx = 0;
		c.gridwidth = 1;
		c.ipady = 40;
		c.weightx = .025;
		panel.add(new JLabel(questionNumber++ + ". "), c);

		c.gridx = 1;
		c.gridwidth = 10;
		c.anchor = GridBagConstraints.WEST;
		c.weightx = GridBagConstraints.REMAINDER;
		panel.add(new JLabel(prompts[21]), c);


		++c.gridy;
		c.gridx = 0;
		c.anchor = GridBagConstraints.WEST;
		ButtonGroup btng = new ButtonGroup();
		int startingprompt = 23;
		for (int j = 5; j > 0; j--) {
			c.gridwidth = 1;
			c.weightx = .2;
			JRadioButton btn = new JRadioButton(j + " " +prompts[startingprompt++]);
			++c.gridx;
			panel.add(btn, c);
			btng.add(btn);	
		}
		btngroups.add(btng);
		
		c.gridx = 0;
		c.gridwidth = 6;
		c.ipady = 30;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = GridBagConstraints.REMAINDER;
		++c.gridy;
		panel.add(newText(), c);
		
		++c.gridy;
		c.gridx = 0;
		c.gridwidth = 1;
		c.ipady = 40;
		c.weightx = .025;
		panel.add(new JLabel(questionNumber++ + ". "), c);

		c.gridx = 1;
		c.gridwidth = 10;
		c.anchor = GridBagConstraints.WEST;
		c.weightx = GridBagConstraints.REMAINDER;
		panel.add(new JLabel(prompts[22]), c);


		++c.gridy;
		c.gridx = 0;
		c.anchor = GridBagConstraints.WEST;
		btng = new ButtonGroup();
		startingprompt = 23;
		for (int j = 5; j > 0; j--) {
			c.gridwidth = 1;
			c.weightx = .2;
			JRadioButton btn = new JRadioButton(j + " " +prompts[startingprompt++]);
			++c.gridx;
			panel.add(btn, c);
			btng.add(btn);	
		}
		btngroups.add(btng);
		
		c.gridx = 0;
		c.gridwidth = 6;
		c.ipady = 30;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = GridBagConstraints.REMAINDER;
		++c.gridy;
		panel.add(newText(), c);
		
		c.gridx = 0;
		++c.gridy;
		c.gridwidth = 4;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(new JLabel("Summary Rating:"), c);
		
		++c.gridy;
		c.gridx = 0;
		c.anchor = GridBagConstraints.WEST;
		btng = new ButtonGroup();
		startingprompt = 28;
		for (int j = 5; j > 0; j--) {
			c.gridwidth = 1;
			c.weightx = .2;
			JRadioButton btn = new JRadioButton(j + " " +prompts[startingprompt++]);
			++c.gridx;
			panel.add(btn, c);
			btng.add(btn);	
		}
		btngroups.add(btng);
		
		c.gridx = 0;
		++c.gridy;
		c.gridwidth = 4;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(new JLabel("Summary Comments:"), c);
		
		c.gridx = 0;
		c.gridwidth = 6;
		c.ipady = 30;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = GridBagConstraints.REMAINDER;
		++c.gridy;
		panel.add(newText(), c);
		
		c.gridy++;
		panel.add(createSouthPanel(), c);
		
		
		ScrollPane scroll = new ScrollPane();
		scroll.setPreferredSize(new Dimension(1100, 700));
		panel.setVisible(true);
		scroll.add(panel);
		
		scroll.setVisible(true);
		return scroll;
	}
	
	private static JTextArea createLabel(String text, Color bkg) {
		JTextArea txt = new JTextArea(text);
		txt.setWrapStyleWord(true);
		txt.setLineWrap(true);
		txt.setMaximumSize(new Dimension(5,5));
		txt.setEditable(false);
		txt.setBackground(bkg);
		return txt;
		
	}
	
	private class SubmitAction implements ActionListener {
		boolean allChosen = true;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			for (ButtonGroup bg : btngroups) {
				if (bg.getSelection() == null) {
					allChosen = false;
				} 
			}
			for (JTextArea t : comments) {
				if (t.getText().equals("")) {
					allChosen = false;
				}
			}
			if (!allChosen){
				JOptionPane.showMessageDialog((Component) e.getSource(), "You need to select a rating and "
						+ "enter a rationale each one of your ratings.");

			}  else {
				List<Integer> scores = new ArrayList<Integer>();
				List<String> author = new ArrayList<String>();
				
				for (ButtonGroup bg : btngroups) {
					scores.add( Integer.valueOf(btngroups.get(i).getSelection().getActionCommand()));
				}
				String spcOnly = comments.get(0).getText();
				for (int i = 1; i < comments.size(); i++) {
					author.add(comments.get(i).getText());
				}
				paper.review(currentUser, new Review(scores, spcOnly, author));
				firePropertyChange(paper.getTitle(), paper, currentUser);
				Window frm = SwingUtilities.windowForComponent((Component) e.getSource());
				frm.dispose();
			}
			
		}
		
		
		
	}
}
