package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.border.AbstractBorder;

/**
 * This class creates a JLabel that changes color to blue when you
 * mouse over it and when it is clicked, it tells the main panel 
 * to load the login screen. This can be used in any of the display
 * panels.
 * 
 * @author Mitchell Alpert
 * @version 5/22/14
 *
 */
@SuppressWarnings("serial")
public class LogOutLabel extends JLabel {
	
	/**
	 * Creates the logout label with the supplied text
	 * allowing for use with different labels
	 * 
	 * @param text text of the label
	 */
	LogOutLabel(String text) {
		super(text);
		
		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				firePropertyChange("login", null, null);		
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				JLabel label = (JLabel) e.getSource();
				label.setForeground(Color.BLUE);
				AbstractBorder brdr = new TextBubbleBorder(Color.BLUE,2,6,0);
				label.setBorder(brdr);		
				label.getParent().repaint();
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				JLabel label = (JLabel) e.getSource();
				label.setForeground(Color.BLACK);
				AbstractBorder brdr = new TextBubbleBorder(Color.BLACK,2,6,0);
				label.setBorder(brdr);		
				label.getParent().repaint();
				
			}
			
		});
	}

}
