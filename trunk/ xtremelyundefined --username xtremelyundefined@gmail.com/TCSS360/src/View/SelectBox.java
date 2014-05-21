package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SelectBox<T> extends JFrame {
	
	
	public SelectBox (List<T> objs) {
		super();
		setTitle("Please make a selection:");
		add(createPanel(objs));
		setLocationRelativeTo(null); //put in middle of screen
		pack();
		setVisible(true);
	}

	private JPanel createPanel(List<T> objs) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel cPanel = new JPanel();
		JPanel sPanel = new JPanel();
		sPanel.setLayout(new FlowLayout());
		panel.add(createSelectOptions(objs));	
		sPanel.add(new JButton("Submit"));
		sPanel.add(new JButton("Cancel"));
		add(cPanel, BorderLayout.CENTER);
		add(sPanel, BorderLayout.SOUTH);
		panel.setPreferredSize(new Dimension(300,100));
		return panel;
	}
	
	private JComboBox<T> createSelectOptions(List<T> list){
		JComboBox<T> options = new JComboBox<T>();
		for (int i = 0; i < list.size(); i++) {
			options.addItem(list.get(i));
		}
		return options;
	}
	

}
