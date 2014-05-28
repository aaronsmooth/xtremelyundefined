package View;

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
		System.out.println(confirm);
		if (confirm == 0) {
			Window frm = SwingUtilities.windowForComponent((Component) e.getSource());
			frm.dispose();
		}
	}

}
