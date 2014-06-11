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

/**
 * This class serves as a generic selection window. It takes a 
 * list of users and sends a property change with the user
 * that was picked.
 * 
 * @author Mitchell Alpert
 * @version 5/22/14
 *
 */
@SuppressWarnings("serial")
public class SelectBox extends JFrame {
	
	/**
	 * The combo box used to make the selection
	 */
	private JComboBox<User> choice;
	
	/**
	 * Text representing what is being selected
	 */
	private String type;
	
	/**
	 * The current paper that is being effected
	 */
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

	/**
	 * Creates the panel holding the combobox and the the 
	 * submit, cancel buttons
	 * 
	 * @param objs list of users to be put in the combobox
	 * @param name the string to use to indiciate what is being
	 * 				selected
	 * @return JPanel panel with the dropdown
	 */
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

	/**
	 * Confers a List of Uses to an array of Users so
	 * it can be used in the combobox
	 * 
	 * @param list List of Users
	 * @return User[] array of Users
	 */
	private User[] createSelectOptions(List<User> list){
		User[] options = new User[list.size()];
		for (int i = 0; i < options.length; i++) {
			options[i] = list.get(i);
		}
		return options ;
	}

	/**
	 * Defines the action for the Submit button. If the user
	 * clicks submit, it fires the appropriate property change
	 * 
	 * @author Mitchell Alpert
	 *
	 */
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			firePropertyChange(type, currentPaper, choice.getSelectedItem());
			Window frm = SwingUtilities.windowForComponent((Component) e.getSource());
			frm.dispose();
		}
		
	}
	
	/**
	 * Defines the action for the Cancel button. If the 
	 * user clicks cancel, this window closes.
	 * 
	 * @author Mitchell Alpert
	 *
	 */
	private class CancelListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			Window frm = SwingUtilities.windowForComponent((Component) e.getSource());
			frm.dispose();
		}
	}
	

}



