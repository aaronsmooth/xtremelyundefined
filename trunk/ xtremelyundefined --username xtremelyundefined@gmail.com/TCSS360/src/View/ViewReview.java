package View;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class ViewReview extends JFrame{

	JPanel panel;
	
	public ViewReview(List<Integer> scores, List<String> prompts, List<String> comments) {
		panel = new JPanel();
		for (int i = 0; i < scores.size(); i++) {
			if (prompts != null) {
				panel.add(new JLabel(prompts.get(i)));
			}
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
		add(panel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
