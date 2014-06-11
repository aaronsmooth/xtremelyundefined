package View;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import Model.Conference;
import Model.ManagementSystem;
import Model.Paper;
import Model.User;

/**
 * This class is used to show the paper submission pop-up box
 * in order to submit the paper to the conference.
 * 
 * @author Andreas Tarmidi
 * @version 5/30/2014
 *
 */
public class SubmitPaper extends JFrame {
	public static final String CANCEL = "Cancel";
	public static final String SUBMIT = "Submit paper";
	public static final String REMOVE = "Remove File";
	public static final String UPLOAD = "Upload";
	public static final int FIELD_WIDTH = 20;
	public static final int FIELD_HIGH = 1;

	/**
	 * Creates text field for author's information and his/her paper details.
	 */
	private JTextField nameField, emailField, paperTopicField, paperTitleField,
	keywordField, fileChooserField;
	/**
	 * Creates text area for the paper's abstract.
	 */
	private JTextArea abstractField;
	/**
	 * Creates button to do action for the paper / close the window.
	 */
	private JButton cancel, submit, chooseFile, removeFile;
	/**
	 * The Action Listener.
	 */
	private MyActionListener listener = new MyActionListener();
	/**
	 * The file chooser.
	 */
	JFileChooser fileChooser = new JFileChooser();
	
	/**
	 * The user (author) using this submission.
	 */
	private User the_author;
	/**
	 * The conference to send the paper to.
	 */
	private Conference the_conf;
	/**
	 * The paper for submission.
	 */
	private Paper old_paper;

	/*public SubmitPaper(Conference the_conf, User the_author) {
		this.the_author = the_author;
		this.the_conf = the_conf;
		buildUI();
	}*/
	
	/**
	 * Construct the SubmitPaper pop-up window.
	 * 
	 * @param the_conf Conference
	 * @param the_author User Author
	 * @param the_paper Paper
	 */
	public SubmitPaper(Conference the_conf, User the_author, Paper the_paper) {
		this.the_author = the_author;
		this.the_conf = the_conf;
		this.old_paper = the_paper;
		buildUI();
	}

	/**
	 * Set the pop-up window size
	 * 
	 * @param width Window Width
	 * @param height Window Height
	 * @param comp Component
	 */
	private void setPreferedSize(int width, int height, Component... comp) {
		for (int i = 0; i < comp.length; i++) {
			comp[i].setPreferredSize(new Dimension(width, height));
		}
	}

	/**
	 * Build the GUI
	 */
	public void buildUI() {
		if (this.old_paper == null) {
			nameField = new JTextField(the_author.getName());
			emailField = new JTextField(the_author.getEmail());
			paperTopicField = new JTextField();
			paperTitleField = new JTextField();
			keywordField = new JTextField();
			fileChooserField = new JTextField();
			abstractField = new JTextArea();
		} else {
			nameField = new JTextField(the_author.getName());
			emailField = new JTextField(the_author.getEmail());
			paperTopicField = new JTextField(old_paper.getTopic());
			paperTitleField = new JTextField(old_paper.getTitle());
			keywordField = new JTextField(old_paper.getKeywords());
			fileChooserField = new JTextField(old_paper.getFilePath());
			abstractField = new JTextArea(old_paper.getAbstract());
		}
		setPreferedSize(250, 25, nameField, paperTopicField, emailField,
				paperTitleField, keywordField);
		setPreferedSize(150, 25, fileChooserField);

		//abstractField = new JTextArea();
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
		this.setVisible(true);
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
				int c = fileChooser.showOpenDialog(SubmitPaper.this);
				if (c == JFileChooser.APPROVE_OPTION) {
					File f = fileChooser.getSelectedFile();
					fileChooserField.setText(f.getAbsolutePath());
				}
			}
			if (cmd.equals(SUBMIT)) {
				if (old_paper != null) {
					the_conf.removePaper(old_paper.getTitle());
				}
				Paper paper = new Paper(the_author, paperTitleField.getText(),
						keywordField.getText(), abstractField.getText(),
						fileChooserField.getText(), paperTopicField.getText());
				the_conf.submitPaper(paper);
				firePropertyChange("paperSubmission", paper, "Author");
				Window frm = SwingUtilities.windowForComponent((Component) e.getSource());
				frm.dispose();
			}
			if (cmd.equals(REMOVE)) {
				fileChooserField.setText("");
			}

		}

	}


}
/*
public static void main(String[] args) {

		FileInputStream in;
		ObjectInputStream obj;
		try {
			in = new FileInputStream("src/supportingFiles/managementsystem.ser");
			obj = new ObjectInputStream(in);
			ManagementSystem system = (ManagementSystem) obj.readObject();
			new SubmitPaper(system.getConference(), system.getUsers().get(0))
			.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}*/
