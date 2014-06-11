package View;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.SwingUtilities;

import View.MainFrame;

/**
 * This class is a WindowListener that saves the current
 * state of the program when the MainFrame closes.
 * 
 * @author Mitchell Alpert
 * @version 5/24/14
 *
 */
public class MainFrameListener extends WindowAdapter {

	 /**
	  * {@inheritDoc}
	  */
	@Override
	public void windowClosing(WindowEvent e) {
		((MainFrame) e.getSource()).system.serialize();
	}

}
