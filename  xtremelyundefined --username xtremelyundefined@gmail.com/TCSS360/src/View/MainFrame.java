package View;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Model.Conference;
import Model.ManagementSystem;
import Model.User;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements Observer{
	
	JPanel display;
	User currentUser;
	Conference currentConference;
	
	public MainFrame(ManagementSystem system){
		setTitle("MSEE Login");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display = new LoginPanel();
		this.add(display);
		pack();
		setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
	
	
}
