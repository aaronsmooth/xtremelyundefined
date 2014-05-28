package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
public class LoginPanel extends JPanel implements PropertyChangeListener{
    

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
	private JComboBox<String> confName;
	private JComboBox<String> role;
	
	public LoginPanel(ManagementSystem system){
		
		JPanel loginpanel = new JPanel(), buttonPanel = new JPanel(), welcomePanel = new JPanel();	
		this.system = system;
		users = system.getUsers();
		conferences = system.getConferences();
		currentUser = null;
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
		email.addPropertyChangeListener(this);
		email.addFocusListener( new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				confName.removeAllItems();
				role.removeAllItems();
				role.addItem("Author");
			}

			@Override
			public void focusLost(FocusEvent e) {
				for (User usr : users) {
					if ( ((JTextField) e.getSource()).getText().equalsIgnoreCase(usr.getEmail())){
						currentUser = usr;
						firePropertyChange("userdropdown", null, currentUser);
					}
				}
			}
			
		});
		loginpanel.add(email);
		loginpanel.add(new JLabel("Conference"));
		confName = new JComboBox<String>();
		loginpanel.add(confName);
		loginpanel.add(new JLabel("Role"));
		role = new JComboBox<String>();
		role.addItem("Author");
		loginpanel.add(role);
		
		
		//Button Panel
		buttonPanel.setLayout(new FlowLayout());
		JButton login = new JButton();
		login.setIcon(new ImageIcon("src/View/login.png"));
		buttonPanel.add(login);
		//panel.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	private String[] getAttendedConferences(){
		ArrayList<String> names = new ArrayList<String>();
		
		for (Conference cnf : conferences) {
			ArrayList<User> users = new ArrayList<User>();
			users.addAll(cnf.getSPCs());
			users.add(cnf.getPC());
			users.addAll(cnf.getReviewers());
			if (users.contains(currentUser)){
				names.add(cnf.getName());
			}
		}
		
		String[] ret = new String[names.size()];
		for (int i = 0; i < names.size(); i++) {
			ret[i] = names.get(i);
		}
		return ret;
	}
	
	private String[] getRoles(String confName) {
		User currentuser = null;
		Conference conference = null;
		List<String> roles = new ArrayList<String>();
		
		for (User usr : users) {
			if (email.equals(usr.getEmail())){
				currentuser = usr;
				break;
			}
		}
		
		for (Conference cnf : conferences) {
			if (confName.equals(cnf.getName())){
				conference = cnf;
				break;
			}
		}
		
		if (currentuser == conference.getPC()) {
			roles.add("Program Chair");
		}
		for (User spc : conference.getSPCs()) {
			if (currentuser == spc) roles.add("Subprogram Chair");
		}
		for (User rev : conference.getReviewers()){
			if (currentuser == rev) roles.add("Reviewer");
		}
		
		String[] ret = new String[roles.size()];
		for (int i = 0; i < roles.size(); i++) {
			ret[i] = roles.get(i);
		}
		return ret;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		if (evt.getNewValue() instanceof User){
			for (String name : getAttendedConferences()) {
				confName.addItem(name);
			}
		}
		
	}
	
	
}