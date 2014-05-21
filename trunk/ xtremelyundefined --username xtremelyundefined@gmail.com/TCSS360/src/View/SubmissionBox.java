package View;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class SubmissionBox extends JOptionPane {
	Object[] options = {"Submit", "Cancel"};
	public SubmissionBox() {
		showOptionDialog(getRootFrame(), MESSAGE_PROPERTY, "SubmissionBox", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
		this.add(makeCombo());
	}
	
	public JComboBox<Object> makeCombo(){
		return new JComboBox<Object>();
	}

	
}
