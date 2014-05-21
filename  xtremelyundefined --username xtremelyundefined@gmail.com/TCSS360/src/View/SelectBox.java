package View;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SelectBox extends JFrame {
	
	public SelectBox() {
		super();
		add(createPanel());
		pack();
		setVisible(true);
	}

	private JPanel createPanel() {
		JPanel panel = new JPanel();
		panel.add(createSelectOptions());
		return panel;
	}
	
	private JComboBox<Object> createSelectOptions(){
		return new JComboBox<Object>();
	}

}
