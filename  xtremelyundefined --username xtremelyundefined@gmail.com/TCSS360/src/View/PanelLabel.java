package View;

import javax.swing.JLabel;

import Model.Paper;

@SuppressWarnings("serial")
public class PanelLabel extends JLabel {
	 
	 private Paper paper;
	 
	 public PanelLabel(String text, Paper the_paper) {
		 super(text);
		 paper = the_paper;
	 }
	 
	 public Paper getPaper() {
		 return paper;
	 }
}
