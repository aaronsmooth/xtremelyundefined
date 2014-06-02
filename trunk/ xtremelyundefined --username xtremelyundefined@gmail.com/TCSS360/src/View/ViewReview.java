package View;

import java.awt.Component;
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
		prompts = initializePrompts();
		panel = new JPanel();
		panel.add(new JLabel("Confidential Comment to the SPC"));
		panel.add(new JLabel(spc));
		for (int i = 0; i < scores.size(); i++) {
			panel.add(new JLabel(prompts.get(i)));
			panel.add(new JLabel(scores.get(i).toString() + ": "));
			panel.add(new JLabel(comments.get(i)));
		}
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				Window frm = SwingUtilities.windowForComponent((Component) e.getSource());
				frm.dispose();			
			}
			
		});
		panel.add(ok);
		add(panel);
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
