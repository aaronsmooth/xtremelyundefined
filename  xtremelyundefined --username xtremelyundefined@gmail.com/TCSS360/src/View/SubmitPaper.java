package View;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import Model.User;

public class SubmitPaper extends JFrame {
	public static final String CANCEL = "Cancel";
	public static final String SUBMIT = "Submit paper";
	public static final String REMOVE = "Remove File";
	public static final String UPLOAD = "Upload";
	public static final int FIELD_WIDTH = 20;
	public static final int FIELD_HIGH = 1;

	private JTextField nameField, emailField, paperTopicField, paperTitleField,
			keywordField, fileChooserField;
	private JTextArea abstractField;
	private JButton cancel, submit, chooseFile, removeFile;
	private MyActionListener listener = new MyActionListener();
	JFileChooser fileChooser = new JFileChooser();

	public SubmitPaper(User the_author) {
		buildUI();
	}

	private void setPreferedSize(int width, int height, Component... comp) {
		for (int i = 0; i < comp.length; i++) {
			comp[i].setPreferredSize(new Dimension(width, height));
		}
	}

	public void buildUI() {
		nameField = new JTextField();
		emailField = new JTextField();
		paperTopicField = new JTextField();
		paperTitleField = new JTextField();
		keywordField = new JTextField();
		fileChooserField = new JTextField();
		setPreferedSize(250, 25, nameField, paperTopicField, emailField,
				paperTitleField, keywordField);
		setPreferedSize(150, 25, fileChooserField);

		abstractField = new JTextArea();
		setPreferedSize(250, 80, abstractField);
		cancel = new JButton(CANCEL);
		submit = new JButton(SUBMIT);
		chooseFile = new JButton(UPLOAD);
		removeFile = new JButton(REMOVE);

		cancel.addActionListener(listener);
		submit.addActionListener(listener);
		chooseFile.addActionListener(listener);
		removeFile.addActionListener(listener);

		Container c = this.getContentPane();

		// add Header
		JLabel header = new JLabel("Conference Name");
		JPanel pHeader = new JPanel();
		pHeader.add(header);
		c.add(pHeader, BorderLayout.NORTH);

		JPanel pCenter = new JPanel();
		c.add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(new GridBagLayout());
		addComponent(pCenter, new JLabel("Name: "), 0, 0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);
		addComponent(pCenter, new JLabel("Email: "), 0, 1,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);
		addComponent(pCenter, new JLabel("Topic of paper: "), 0, 2,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);
		addComponent(pCenter, new JLabel("Page title: "), 0, 3,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);
		addComponent(pCenter, new JLabel("Keywords: "), 0, 4,
				GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
		addComponent(pCenter, new JLabel("Abstract: "), 0, 5,
				GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
		addComponent(pCenter, new JLabel("File choser: "), 0, 6,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);

		addComponent(pCenter, nameField, 1, 0, GridBagConstraints.WEST,
				GridBagConstraints.BOTH);
		addComponent(pCenter, emailField, 1, 1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH);
		addComponent(pCenter, paperTopicField, 1, 2, GridBagConstraints.WEST,
				GridBagConstraints.BOTH);
		addComponent(pCenter, paperTitleField, 1, 3, GridBagConstraints.WEST,
				GridBagConstraints.BOTH);
		addComponent(pCenter, keywordField, 1, 4, GridBagConstraints.WEST,
				GridBagConstraints.BOTH);
		addComponent(pCenter, new JScrollPane(abstractField), 1, 5,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);
		JPanel pnt = new JPanel();
		pnt.add(fileChooserField);
		pnt.add(chooseFile);
		pnt.add(removeFile);
		addComponent(pCenter, pnt, 1, 6, GridBagConstraints.WEST,
				GridBagConstraints.BOTH);

		addComponent(pCenter, cancel, 0, 7, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL);
		addComponent(pCenter, submit, 1, 7, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL);

		this.pack();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * Function to add component to the container given
	 * 
	 * @param container
	 * @param component
	 * @param gridx
	 * @param gridy
	 * @param anchor
	 * @param fill
	 */
	public static void addComponent(Container container, Component component,
			int gridx, int gridy, int anchor, int fill) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = anchor;
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.ipadx = 4;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = anchor;
		container.add(component, gbc);

	}

	/**
	 * To hand User action
	 *
	 */
	class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd.equals(CANCEL)) {
				setVisible(false);
			}
			if (cmd.equals(UPLOAD)) {
				System.out.println("???");
				int c = fileChooser.showOpenDialog(SubmitPaper.this);
				if (c == JFileChooser.APPROVE_OPTION) {
					File f = fileChooser.getSelectedFile();
					fileChooserField.setText(f.getAbsolutePath());
				}
			}
			if (cmd.equals(REMOVE)) {
				fileChooserField.setText("");
			}
			if (cmd.equals(SUBMIT)) {
				
			}

		}

	}

	public static void main(String[] args) {
		new SubmitPaper(null).setVisible(true);
	}
}
