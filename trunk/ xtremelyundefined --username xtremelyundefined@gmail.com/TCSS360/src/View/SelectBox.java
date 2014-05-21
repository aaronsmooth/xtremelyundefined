package View;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SelectBox<T> extends JFrame {
	
	
	public SelectBox (List<T> objs) {
		super();
		setTitle("Please make a selection:");
		JPanel panel = createPanel();
		panel.add(createSelectOptions(objs), BorderLayout.CENTER);
		for (JButton btn : createButtons()) {
			panel.add(btn, BorderLayout.SOUTH);
		}
		setLocationRelativeTo(null); //put in middle of screen
		pack();
		setVisible(true);
	}

	private JPanel createPanel() {
		JPanel panel = new JPanel();
		return panel;
	}
	
	private JComboBox<T> createSelectOptions(List<T> list){
		JComboBox<T> options = new JComboBox<T>();
		for (Object obj : list) {
			options.addItem((T) obj);
		}
		return new JComboBox<T>();
	}
	
	private JButton[] createButtons() {
		JButton[] buttons = new JButton[2];
		buttons[0] = new JButton("Submit");
		buttons[1] = new JButton("Cancel");
		return buttons;
	}
	

}
