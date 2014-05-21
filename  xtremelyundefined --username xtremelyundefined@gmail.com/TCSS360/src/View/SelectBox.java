package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class SelectBox<T> extends JFrame {
	
	private JComboBox<String> choice;
	private String type;
	
	/**
	 * Creates a window that includes a dropdown box made from 
	 * the toString() of all the objects in objs
	 * 
	 * @param objs List of objects for the combo box
	 */
	public SelectBox (List<T> objs) {
		super();
		type = objs.get(0).getClass().toString();
		setTitle("Please make a selection:");
		add(createPanel(objs));
		setLocationRelativeTo(null); //put in middle of screen
		pack();
		setVisible(true);
	}

	private JPanel createPanel(List<T> objs) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel cPanel = new JPanel();
		JPanel sPanel = new JPanel();
		sPanel.setLayout(new FlowLayout());
		choice = (createSelectOptions(objs));
		panel.add(choice);
		JButton btn = new JButton("Submit");
		btn.addActionListener(new ButtonListener());
		sPanel.add(btn);
		btn = new JButton("Cancel");
		btn.addActionListener(new ButtonListener());
		sPanel.add(btn);;
		add(cPanel, BorderLayout.CENTER);
		add(sPanel, BorderLayout.SOUTH);
		panel.setPreferredSize(new Dimension(300,100));
		return panel;
	}

	private JComboBox<String> createSelectOptions(List<T> list){
		JComboBox<String> options = new JComboBox<String>();
		for (int i = list.size() - 1; i >= 0 ; i--) { //for alphabetical order
			options.addItem(list.get(i).toString());
		}
		return options ;
	}

	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JButton ) {
				JButton src = (JButton) e.getSource();
				Window frm = SwingUtilities.windowForComponent(src);
				if (src.getText().equals("Submit")) {
					firePropertyChange(type, null, choice.getSelectedItem());
				}			
				frm.dispose();
			}
		}
	}
}


