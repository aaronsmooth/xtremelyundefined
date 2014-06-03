package View;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class ViewReview extends JFrame{

	JPanel panel;
	List<String> prompts;
	
	public ViewReview(List<Integer> scores, String spc, List<String> comments) {
		super("Completed Review");
		prompts = initializePrompts();
		panel = new JPanel(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.WEST;
		panel.add(new JLabel("Confidential Comment to the SPC:"), c);
		
		++c.gridy;
		c.gridx = 0;
		panel.add(new JLabel("\t" + spc), c);
		
		for (int i = 0; i < scores.size() - 1; i++) {
			c.gridy+=2;
			c.gridx = 0;
			c.anchor = GridBagConstraints.WEST;
			panel.add(new JLabel(scores.get(i).toString() + ": " + prompts.get(i)), c);
			
			c.gridx = 0;
			++c.gridy;
			c.anchor = GridBagConstraints.WEST;
			panel.add(new JLabel("\t" + comments.get(i)), c);
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
		panel.setPreferredSize(new Dimension(1000, 500));
		add(panel);
		pack();
		setAlwaysOnTop(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	private List<String> initializePrompts() {
		List<String> prompts = new ArrayList<String>();
		prompts.add( "Can the content be directly applied by classroom instructors or curriculum designers?");
		prompts.add( "Does the work appeal to a broad readership interested in engineering education or is it"
					+" narrowly specialized?");
		prompts.add("Does the work address a significant problem?");
		prompts.add("Does the author build upon relevant refernces and bodies of knowledge");
		prompts.add("If a teaching invtervention is reported, is it adequately evaluated in terms of its "
				+ "impact on learning in actual use?");

		prompts.add("Does the author use methods appropriate to the goals, both for the instructional"
				+ " intervention and the evaluation of impact on learning?");

		prompts.add("Did the author provide sufficient detail to replicate and evaluate?");

		prompts.add("Is the paper clearly and carefully written");
		prompts.add("Does the paper adhere to standards ofstyle, usage, and composotion?");
		return prompts;
		
	}
}
