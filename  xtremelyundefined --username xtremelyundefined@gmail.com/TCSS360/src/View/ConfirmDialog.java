package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Model.Paper;

/**
 * This class acts as a confirmation that the user wants to 
 * remove this paper from the conference. If the user confirms 
 * the removal, it fires the appropriate property change event.
 * 
 * @author Mitchell Alpert
 * @version 5/21/14
 *
 */

@SuppressWarnings("serial")
public class ConfirmDialog extends JFrame {

	/**
	 * Constructor that takes a frame title, a property name, and a paper. This
	 * information is taken as parameters for extensibility.
	 * 
	 * @param text 	the title of the frame
	 * @param propertyName the string to fire in the property change
	 * @param thepaper the paper that is being acted on
	 */
	public ConfirmDialog(final String text, final String propertyName, final Paper thepaper) {
		super(text);
		
		JPanel panel = new JPanel(new BorderLayout());
		JPanel npanel = new JPanel(new FlowLayout());
		npanel.setPreferredSize(new Dimension(350, 50));
		JPanel spanel = new JPanel();
		npanel.add(new JLabel("Are you sure you want to remove this paper?"));
		JButton ok = new JButton("Ok");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				firePropertyChange(propertyName, null, thepaper);	
				Window frm = SwingUtilities.windowForComponent((Component) e.getSource());
				frm.dispose();
			}
			
		});
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Window frm = SwingUtilities.windowForComponent((Component) e.getSource());
				frm.dispose();
			}
			
		});
		spanel.add(ok);
		spanel.add(cancel);
		panel.add(npanel, BorderLayout.CENTER);
		panel.add(spanel, BorderLayout.SOUTH);
		add(panel);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		pack();
		setVisible(true);
		
	}
}
