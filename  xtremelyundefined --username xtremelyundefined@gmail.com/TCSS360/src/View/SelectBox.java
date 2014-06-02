package View;

import java.awt.BorderLayout;
import java.awt.Component;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Model.Paper;
import Model.User;

@SuppressWarnings("serial")
public class SelectBox extends JFrame {
	
	private JComboBox<User> choice;
	private String type;
	private Paper currentPaper;
	
	/**
	 * Creates a window that includes a dropdown box made from 
	 * the toString() of all the objects in objs
	 * 
	 * @param objs List of objects for the combo box
	 * @param name name of thing being chosen 
	 */
	public SelectBox (List<User> objs, String name, Paper currentPaper) {
		super();
		this.currentPaper = currentPaper;
		type = name;
		setTitle("Make a Selection");
		add(createPanel(objs, name));
		setLocationRelativeTo(null); //put in middle of screen
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		pack();		
		setVisible(true);
	}

	private JPanel createPanel(List<User> objs, String name) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JPanel nPanel = new JPanel();
		nPanel.add(new JLabel("Please choose from the following list of " + name + ": "));
		panel.add(nPanel, BorderLayout.NORTH);
		
		JPanel cPanel = new JPanel(new FlowLayout());	
		choice = new JComboBox<User>(createSelectOptions(objs));
		cPanel.add(choice);
		panel.add(cPanel);
		
		JPanel sPanel = new JPanel();
		sPanel.setLayout(new FlowLayout());
		JButton btn = new JButton("Select");
		btn.addActionListener(new ButtonListener());
		sPanel.add(btn);
		btn = new JButton("Cancel");
		btn.addActionListener(new CancelListener());
		sPanel.add(btn);;
		panel.add(sPanel, BorderLayout.SOUTH);
		
		panel.setPreferredSize(new Dimension(300,100));
		return panel;
	}

	private User[] createSelectOptions(List<User> list){
		User[] options = new User[list.size()];
		for (int i = 0; i < options.length; i++) {
			options[i] = list.get(i);
		}
		return options ;
	}

	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			firePropertyChange(type, currentPaper, choice.getSelectedItem());
			Window frm = SwingUtilities.windowForComponent((Component) e.getSource());
			frm.dispose();
		}
		
	}
	
	private class CancelListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			Window frm = SwingUtilities.windowForComponent((Component) e.getSource());
			frm.dispose();
		}
	}
	

}



