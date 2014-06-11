package View;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import Model.Review;

/**
 * This class is used to View the Reviews made by the Reviewer
 *
 * @author Mithell Alpert
 * @version 5/24/14
 *
 */
@SuppressWarnings("serial")
public class ViewReview extends JFrame{

	/**
	 * The panel displaying all of the review information
	 */
	JPanel panel;
	/**
	 * List of prompts for all of the review questions
	 */
	List<String> prompts;
	
	/**
	 * Boolean to indicate whether the confidential SPC comments
	 * should be shown.
	 */
	boolean spc;

	/**
	 * The height of the comment boxes
	 */
	private static final int TEXT_HEIGHT = 50;
	
	/**
	 * The width of the text boxes
	 */
	private static final int TEXT_WIDTH = 500;
	
	/**
	 * Constructs the ViewReview class
	 * 
	 * @param rev The Reviewer
	 * @param spc true if the user should see the SPC comments
	 */
	public ViewReview(Review rev, boolean spc) {
		super("Completed Review");
		this.spc = spc;
		prompts = initializePrompts();
		panel = new JPanel(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		
		if (spc) {
			c.gridheight = 1;
			c.gridwidth = 1;
			c.anchor = GridBagConstraints.WEST;
			panel.add(new JLabel("Confidential Comment to the SPC:"), c);

			++c.gridy;
			c.gridx = 0;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = GridBagConstraints.REMAINDER;
			panel.add(newText(rev.getSpcComment()), c);
		}
		
		for (int i = 0; i < rev.getRating().size(); i++) {
			++c.gridy;
			c.gridheight = 1;
			c.weighty = GridBagConstraints.REMAINDER;
			c.fill = GridBagConstraints.NONE;
			panel.add(Box.createVerticalStrut(25), c);
			c.ipady = 20;
			c.ipadx = 10;
			++c.gridy;
			c.gridx = 0;
			c.gridwidth = 1;
			c.fill = GridBagConstraints.BOTH;
			c.anchor = GridBagConstraints.WEST;
			panel.add(new JLabel(prompts.get(i)), c);
			
			++c.gridx;
			JLabel score = new JLabel(rev.getRating().get(i).toString());
			score.setFont(new Font("TimesRoman", Font.BOLD, 20));
			panel.add(score, c);
			
			c.gridx = 0;
			++c.gridy;
			c.anchor = GridBagConstraints.WEST;
			c.gridwidth = 2;
			c.gridheight = 2;
			c.fill = GridBagConstraints.BOTH;
			panel.add(newText(rev.getAuthorComments().get(i)), c);
		}
		++c.gridy;
		c.anchor = GridBagConstraints.CENTER;
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				Window frm = SwingUtilities.windowForComponent((Component) e.getSource());
				frm.dispose();			
			}
			
		});
		JPanel spanel = new JPanel(new FlowLayout());
		spanel.add(ok);
		
		++c.gridy;
		panel.add(spanel, c);
		ScrollPane scroll = new ScrollPane();
		scroll.setPreferredSize(new Dimension(1000, 700));
		scroll.add(panel);
		add(scroll);
		pack();
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	/**
	 * This is the collection of question prompted for the Review
	 * 
	 * @return List<String> the questino prompts
	 */
	private List<String> initializePrompts() {
		List<String> prompts = new ArrayList<String>();
		prompts.add( "Can the content be directly applied by classroom instructors or curriculum designers?");
		prompts.add( "Does the work appeal to a broad readership interested in engineering education or is it"
					+" narrowly specialized?");
		prompts.add("Does the work address a significant problem?");
		prompts.add("Does the author build upon relevant refernces and bodies of knowledge?");
		prompts.add("If a teaching invtervention is reported, is it adequately evaluated in terms of its "
				+ "impact on learning in actual use?");

		prompts.add("Does the author use methods appropriate to the goals, both for the instructional"
				+ " intervention and the evaluation of impact on learning?");

		prompts.add("Did the author provide sufficient detail to replicate and evaluate?");

		prompts.add("Is the paper clearly and carefully written?");
		prompts.add("Does the paper adhere to standards ofstyle, usage, and composotion?");
		prompts.add("Summary: ");
		return prompts;
		
	}
	
	/**
	 * Returns a scrollpane that holds the comments that the reviewer provided.
	 * Scrolls incase they wrote longer comments.
	 * 
	 * @param str the comment
	 * @return JScrollPane the scroll pane holding the comments
	 */
	private JScrollPane newText(String str){
		JTextArea txt = new JTextArea(str);
		txt.setBorder(BorderFactory.createEtchedBorder());
		txt.setWrapStyleWord(true);
		txt.setLineWrap(true);
		txt.setEditable(false);
		txt.setMinimumSize(new Dimension(200, 100));
		txt.setBackground(this.getBackground());
		JScrollPane scroll = new JScrollPane(txt);
		scroll.setBorder(BorderFactory.createEtchedBorder());
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		return scroll;
	}
}
