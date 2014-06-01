package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Conference;
import Model.ManagementSystem;
import Model.User;


@SuppressWarnings("serial")
public class LoginPanel extends JPanel {
    

	/**
	 * Note:  I have create two array of strings here for the JComboBox
	 * I made them a field for this LoginPanel so that the panel
	 * can display accurate and current list of available conference from 
	 * time to time.
	 * The calling method, in this case the system management, will
	 * pass a string arrays of conference to be selected as option 
	 * for the conference dropbox.
	 */
	
//	private String [] roles = { "   -------------------------   ", "PC: Program Chair", "SPC: Sub-Prgram Chair",
//			             "R: Reviewer", "A: Author"};
//	
//	private String [] conference = {"   -------------------------   " ,"Conference 1", "Conference 2", "Conference 3"};
	private ManagementSystem system;
	private List<User> users;
	private List<Conference> conferences;
	private JTextField email;
	private User currentUser;
	private JComboBox<Conference> currentConference;
	private JComboBox<String> role;
	
	public LoginPanel(final ManagementSystem system){
		
		JPanel loginpanel = new JPanel(), buttonPanel = new JPanel(), welcomePanel = new JPanel();	
		this.system = system;
		users = system.getUsers();
		conferences = system.getConferences();
		currentUser = null;
		currentConference = new JComboBox<Conference>();
		//setLayout(new BorderLayout());
		setLayout(null);
		setSize(500,350);
		add(welcomePanel);
		add(loginpanel);
		add(buttonPanel);
		welcomePanel.setBounds(50, 30, 400, 50);
		loginpanel.setBounds(100, 100, 300, 75);
		buttonPanel.setBounds(100,190,300,50);
		
		//Greetings Panel
		JLabel greetings = new JLabel("MSEE Conference Wizard");
		greetings.setFont(new Font("TimesRoman", Font.BOLD, 30));
		greetings.setForeground(Color.BLACK);
		welcomePanel.add(greetings);
		
		//Login Panel
		loginpanel.setLayout(new GridLayout(4,2, 1, 1));
		loginpanel.add(new JLabel("Email Address"));
		email = new JTextField(16);
		email.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {

			}

			@Override
			public void focusLost(FocusEvent e) {
				for (User usr : users){
					if (usr.getEmail().equalsIgnoreCase(email.getText())){
						//System.out.println( ((JTextField) e.getSource()).getText().equalsIgnoreCase(usr.getEmail()));
						currentUser = usr;
					}
				}
				for (Conference cnf : conferences) {
					if (cnf.hasUser(currentUser)) {
						currentConference.addItem(cnf);
					}
				}
			}					
		});
		loginpanel.add(email);
		loginpanel.add(new JLabel("Conference"));
		currentConference = new JComboBox<Conference>();
		currentConference.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (String rle : ((Conference)currentConference.getSelectedItem()).getRoles(currentUser)) {
					role.addItem(rle);
				}

			}

		});
		loginpanel.add(currentConference);
		loginpanel.add(new JLabel("Role"));
		role = new JComboBox<String>();
		role.addItem("Author");
		loginpanel.add(role);
		
		
		//Button Panel
		buttonPanel.setLayout(new FlowLayout());
		JButton login = new JButton();
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				system.setConference((Conference) currentConference.getSelectedItem());
				system.setUser(currentUser);
				firePropertyChange("user", null, role.getSelectedItem());
			}
			
		});
		login.setIcon(new ImageIcon("src/View/login.png"));
		buttonPanel.add(login);
		//panel.setBorder(BorderFactory.createLineBorder(Color.black));
	}


}
