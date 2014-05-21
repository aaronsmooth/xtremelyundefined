package View;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SelectBox extends JFrame {
	
	public SelectBox() {
		this.add(createPanel());
		setVisible(true);
	}

	private JPanel createPanel() {
		return new JPanel();
	}

}
