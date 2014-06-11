package View;

import javax.swing.JLabel;

import Model.Paper;

/**
 * This class is a JLabel that has a field that can be
 * used to associate a paper object with this label.
 * Used for all the icons in the panels to save what
 * paper was being used when a list of icons is being
 * generated.
 * 
 * @author Mitchell Alpert
 * @version 5/24/214
 *
 */
@SuppressWarnings("serial")
public class PanelLabel extends JLabel {
	 
	 
	/**
	 * The paper object associated with this label
	 */
	private Paper paper;
	 
	/**
	 * Constructor that takes the normal text for the label 
	 * but also a paper that you want associated with this label
	 * 
	 * @param text the label 
	 * @param the_paper the paper to save
	 */
	 public PanelLabel(String text, Paper the_paper) {
		 super(text);
		 paper = the_paper;
	 }
	 
	 /**
	  * Returns the paper object associated with this label.
	  * 
	  * @return Paper the paper
	  */
	 public Paper getPaper() {
		 return paper;
	 }
}
