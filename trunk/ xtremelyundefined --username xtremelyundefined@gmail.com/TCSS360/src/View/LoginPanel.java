package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class LoginPanel extends JPanel{
    

	/**
	 * Note:  I have create two array of strings here for the JComboBox
	 * I made them a field for this LoginPanel so that the panel
	 * can display accurate and current list of available conference from 
	 * time to time.
	 * The calling method, in this case the system management, will
	 * pass a string arrays of conference to be selected as option 
	 * for the conference dropbox.
	 */
	
	private String [] roles = { "   -------------------------   ", "PC: Program Chair", "SPC: Sub-Prgram Chair",
			             "R: Reviewer", "A: Author"};
	
	private String [] conference = {"   -------------------------   " ,"Conference 1", "Conference 2", "Conference 3"};
	
	public LoginPanel(){
		
		JPanel loginpanel = new JPanel(), buttonPanel = new JPanel(), welcomePanel = new JPanel();	
		
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
		loginpanel.add(new JLabel("First Name"));
		loginpanel.add(new JTextField(16));
		loginpanel.add(new JLabel("Password"));
		loginpanel.add(new JTextField(16));
		loginpanel.add(new JLabel("Role"));
		loginpanel.add(new JComboBox(roles));
		loginpanel.add(new JLabel("Conference"));
		loginpanel.add(new JComboBox(conference));
		
		//Button Panel
		buttonPanel.setLayout(new FlowLayout());
		JButton login = new JButton(), forgot = new JButton();
		login.setIcon(new ImageIcon("src/View/login.png"));
		forgot.setIcon(new ImageIcon("src/View/forgot.png"));
		buttonPanel.add(login);
		buttonPanel.add(forgot);
		//panel.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	
	
}