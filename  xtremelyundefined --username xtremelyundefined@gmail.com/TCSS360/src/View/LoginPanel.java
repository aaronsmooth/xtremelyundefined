package View;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Conference;
import Model.ManagementSystem;
import Model.User;


@SuppressWarnings("serial")
public class LoginPanel extends JPanel {

	private ManagementSystem system;
	private JTextField email;
	private User currentUser;
	private JComboBox<Conference> currentConference;
	private JComboBox<String> role;
	
	public LoginPanel(final ManagementSystem system){
		
		JPanel loginpanel = new JPanel(), buttonPanel = new JPanel(), welcomePanel = new JPanel();	
		this.system = system;
		currentUser = null;
		currentConference = new JComboBox<Conference>();
		currentConference.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (role != null) {
					role.removeAllItems();
					role.addItem("Author");
				}
				for (String rle : ((Conference)currentConference.getSelectedItem()).getRoles(currentUser)) {
					role.addItem(rle);
				}

			}

		});
		for (Conference cnf : system.getConferences()) {
			currentConference.addItem(cnf);
		}
		setLayout(null);
		
		JPanel framePanel = new JPanel();
		framePanel.setBorder(new TextBubbleBorder(Color.BLACK,2,6,0));
		framePanel.setLayout(null);
		framePanel.setBounds(300,200,600,300);
		framePanel.add(welcomePanel);
		framePanel.add(loginpanel);
		framePanel.add(buttonPanel);
		welcomePanel.setBounds(100, 30, 400, 50);
		loginpanel.setBounds(100, 100, 300, 75);
		buttonPanel.setBounds(140,190,300,50);
		
		add(framePanel);
		
		//Greetings Panel
		JLabel greetings = new JLabel("MSEE Conference Wizard");
		greetings.setFont(new Font("TimesRoman", Font.BOLD, 30));
		greetings.setForeground(Color.BLACK);
		welcomePanel.add(greetings);
		
		//Login Panel
		loginpanel.setLayout(new GridLayout(4,2, 1, 1));
		loginpanel.add(new JLabel("Email Address"));
		email = new JTextField(16);
		role = new JComboBox<String>();
		final JButton login = new JButton();
		login.setEnabled(false);
		email.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (system.getUser(email.getText()) != null) {
					currentUser = system.getUser(email.getText());
					login.setEnabled(true);
				}
			}					
		});
		loginpanel.add(email);
		loginpanel.add(new JLabel("Conference"));
		loginpanel.add(currentConference);
		loginpanel.add(new JLabel("Role"));
		loginpanel.add(role);
		
		
		//Button Panel
		buttonPanel.setLayout(new FlowLayout());
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				system.setConference((Conference) currentConference.getSelectedItem());
				system.setUser(currentUser);
				firePropertyChange("role", currentUser, role.getSelectedItem());
			}
			
		});
		login.setIcon(new ImageIcon("src/View/login.png"));
		buttonPanel.add(login);
		//panel.setBorder(BorderFactory.createLineBorder(Color.black));
	}


}
