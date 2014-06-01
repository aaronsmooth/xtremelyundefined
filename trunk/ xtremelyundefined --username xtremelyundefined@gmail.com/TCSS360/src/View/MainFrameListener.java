package View;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.SwingUtilities;

import View.MainFrame;

public class MainFrameListener extends WindowAdapter {

	 
	@Override
	public void windowClosing(WindowEvent e) {
		((MainFrame) e.getSource()).system.serialize();
	}

}
