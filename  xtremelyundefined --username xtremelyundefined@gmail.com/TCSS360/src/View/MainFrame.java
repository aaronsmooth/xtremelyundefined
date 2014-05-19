package View;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
	
	JPanel display;
	
	public MainFrame(){
		setTitle("MSEE Login");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display = new LoginPanel();
		this.add(display);
		pack();
		setVisible(true);
	}
	
	
}
