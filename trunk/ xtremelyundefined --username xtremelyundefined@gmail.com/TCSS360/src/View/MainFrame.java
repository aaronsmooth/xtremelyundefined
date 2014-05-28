package View;

import java.awt.Dimension;
import java.awt.Toolkit;
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
		setTitle("MSEE");
		setSize(1200,800); // setting the window size as permanent.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2 - 1200/2, dim.height/2 - 800/2); // center the window frame.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display = new LoginPanel(system);
		this.add(display);
		//pack(); //---> needs to be disable because I am not using a default layout manager.
		setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
	
	
}
