package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Model.Conference;
import Model.ManagementSystem;
import Model.User;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements Observer, Runnable{
	
	protected JPanel display;
	protected ManagementSystem system;
	
	public MainFrame(ManagementSystem system){
		this.system = system;
		setTitle("MSEE");
		setSize(1200,800); // setting the window size as permanent.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2 - 1200/2, dim.height/2 - 800/2); // center the window frame.
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.addWindowListener(new MainFrameListener());
		display = new LoginPanel(system);
		display.addPropertyChangeListener(system);
		this.add(display);
		//pack(); //---> needs to be disable because I am not using a default layout manager.
		setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg.equals("Program Chair")){
			display.setVisible(false);
			display = new PCPanel(system);
			add(display);
		} else if (arg.equals("SubProgram Chair")){
			display.setVisible(false);
			display = new SPCPanel();
			add(display);
		} else if (arg.equals("Reviewer")){
			display.setVisible(false);
			display = new ReviewerPanel(system);	
			add(display);
		} else if (arg.equals("Author")) {
			display.setVisible(false);
			display = new AuthorPanel(system);
			add(display);
		}
		
		validate();
		
	}

	@Override
	public void run() {
        system.addObserver(this);   
	}
	
	
}
