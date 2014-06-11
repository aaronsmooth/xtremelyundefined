package View;

/**
 * This class is an ActionListner that opens a confirmation dialog and 
 * if the user selects OK, it disposes whatever frame contains the 
 * source of the action. This can be used by cancel buttons in other
 * classes.
 * 
 * @author Mitchell Alpert
 * @version 5/25/14
 */

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class CloseAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel?", 
				"Confirm Cancel", JOptionPane.OK_CANCEL_OPTION);
		if (confirm == 0) {
			//gets the frame containing the source of the event
			Window frm = SwingUtilities.windowForComponent((Component) e.getSource());
			frm.dispose();
		}
	}

}
