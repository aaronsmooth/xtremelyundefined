package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;

import Model.ManagementSystem;
import Model.Paper;
import Model.Review;
import Model.User;

public class ReviewerPanel extends JPanel {
	private String Author;// = "JOE SCHMOE";
	
	private ManagementSystem mySystem;
	
	private Paper currentPaper;
	
	/**
	 * Constructor.
	 */
	public ReviewerPanel(ManagementSystem theSystem){
		
		User usr = new User(55, "joe", "schmoe", "schmoe@gmail.com");
		User usr2 = new User(56, "jon", "doe", "doe@gmail.com");
		Paper ppr = new Paper(usr, "thisisthetitle", "thekeywords", "theabstact", "filepath");
		Paper ppr2 = new Paper(usr, "alsoatitle", "alsokeywords", "alsoanabstact", "alsoafilepath");
		
		//Conference conf = theSystem.getConference();
		theSystem.getConference().submitPaper(ppr);
		theSystem.getConference().submitPaper(ppr2);
		theSystem.getConference().addReviewer(theSystem.getCurrentUser());
		ppr2.review(theSystem.getCurrentUser(), null);
		
		//theSystem.addConference(conf);
		Author = theSystem.getCurrentUser().getName();
		mySystem = theSystem;
		setLayout(new BorderLayout());
		AbstractBorder brdr = new TextBubbleBorder(Color.BLACK,2,6,0);
		
		JPanel topPanel, bottomPanel, midPanel;
		
		topPanel = topPanel(brdr, Author);
	    bottomPanel = bottomPanel(brdr, manuscript(brdr));
	    midPanel = midPanel(brdr, theSystem.getConference().getName(), bottomPanel);
		
		add(topPanel, BorderLayout.NORTH);
		add(midPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Creates the top Panel to display the name of the user and the logout options.
	 * 
	 * @param brdr necessary border styles for JLabel and JPanel.
	 * 
	 * @param aName name of the user.
	 * @return a panel.
	 */
	public JPanel topPanel(AbstractBorder brdr, String aName){
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,3));
		
		JLabel author = new JLabel(aName);
		author.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel logout = new JLabel("Logout");
		logout.setBorder(brdr);

		JPanel logPanel = new JPanel();
		logPanel.setLayout(new GridLayout(1,4));
		logPanel.add(new JLabel("      ")); // Dummy JLabel for proper placement of logout button.
		logPanel.add(new JLabel("      "));
		logPanel.add(new JLabel("      "));
		logPanel.add(logout);
		
		JPanel topInside = new JPanel();
		topInside.setLayout(new GridLayout(2,1));
		topInside.add(new JLabel("MSEE Conference"));
		topInside.add(new JLabel("Tracking System"));
		
		topPanel.setBorder(brdr);
		topPanel.setOpaque(false);
		topPanel.add(topInside);
		topPanel.add(author);
		topPanel.add(logPanel);
		
		return topPanel;
	}
	
	/**
	 * Creates a middle Panel where the conference name and manuscripts submitted will be displayed.
	 * 
	 * @param brdr necessary border styles for JLabel and JPanel.
	 * @param cName  name of the conference.
	 * @return  a panel.
	 */
	public JPanel midPanel(AbstractBorder brdr, String cName, JPanel bottomPanel ){
		JPanel midPanel = new JPanel();
		midPanel.setLayout(null);
		//midPanel.setSize(1200,800);
		
		JLabel conference = new JLabel(cName);
		conference.setFont(new Font("TimesRoman", Font.BOLD, 25));
		conference.setForeground(Color.BLACK);
		conference.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel midTop = new JPanel();
		midTop.setBorder(brdr);
		midTop.setBounds(100,50,1000,70);
		midTop.add(conference);
		
		midPanel.add(midTop);
		midPanel.add(bottomPanel);
		
		return midPanel;
	}
	
	/**
	 * Creates a panel to display the manuscript to be reviewed.
	 * 
	 * @param brdr necessary border styles for JLabel and JPanel.
	 * @param p panel that contains all the manuscript that is assigned to the user.
	 * @return a panel.
	 */
	public JPanel bottomPanel(AbstractBorder brdr, JPanel p){
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(brdr);
		bottomPanel.setOpaque(false);
		bottomPanel.setSize(400, 400);
		bottomPanel.setBounds(100, 150, 1000, 500);
		
		JPanel insideBottomPanel = new JPanel();
		insideBottomPanel.setLayout(new BorderLayout());
		
		JLabel mypaper = new JLabel("My Assigned Papers to Review");
		mypaper.setFont(new Font("TimesRoman", Font.BOLD, 18));
		mypaper.setForeground(Color.BLACK);
		mypaper.setHorizontalAlignment(SwingConstants.CENTER);
		JPanel paperPanel = new JPanel();
		paperPanel.setLayout(new GridLayout(3,1));
		paperPanel.add(new JLabel(" "));
		paperPanel.add(mypaper);
		paperPanel.add(new JLabel(" "));
		
		JSeparator separator1 = new JSeparator();
	    GridBagConstraints gbc_separator1 = new GridBagConstraints();
	    gbc_separator1.fill = GridBagConstraints.HORIZONTAL;
	    gbc_separator1.gridwidth = 7;
	    gbc_separator1.insets = new Insets(0, 0, 5, 0);
	    gbc_separator1.gridx = 0;
	    gbc_separator1.gridy = 1;
		
		JPanel submitPanel = new JPanel();
		submitPanel.setLayout(new GridLayout(1,1));
		submitPanel.add(separator1, gbc_separator1);
		
		insideBottomPanel.add(paperPanel, BorderLayout.NORTH);
		insideBottomPanel.add(p, BorderLayout.CENTER);
		insideBottomPanel.add(submitPanel, BorderLayout.SOUTH);
		bottomPanel.add(insideBottomPanel);
		
		return bottomPanel;
	}
	
	/**
	 * Creates a panel where all the manuscript assigned to the user will be displayed.
	 * 
	 * @param brdr necesary border styles for JLabel and JPanels.
	 * @return a panel.
	 */
	public JPanel manuscript(AbstractBorder brdr){
		
		GridBagLayout gridBagLayout = new GridBagLayout();
	    gridBagLayout.columnWidths = new int[]{150, 150, 150, 150, 150};
	    gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
	    gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
	    gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	    
	    JPanel panelManuscript = new JPanel();   
	    panelManuscript.setLayout(gridBagLayout);
	    panelManuscript.setSize(500,300);
	    
	    
	    JLabel Topic = new JLabel ("Topic");
	    Topic.setHorizontalAlignment(SwingConstants.CENTER);
	    Topic.setFont(new Font("Tahoma", Font.BOLD, 14));
	    GridBagConstraints gridTopic = new GridBagConstraints();
	    gridTopic.gridx = 1;
	    gridTopic.insets = new Insets(0, 0, 5, 5);
	    gridTopic.gridy = 0;
	    panelManuscript.add(Topic);
	    
		JLabel Date = new JLabel ("Date");
		Date.setHorizontalAlignment(SwingConstants.CENTER);
		Date.setFont(new Font("Tahoma", Font.BOLD, 14));
	    GridBagConstraints gridDate = new GridBagConstraints();
	    gridDate.gridx = 1;
	    gridDate.insets = new Insets(0, 0, 5, 5);
	    gridDate.gridy = 0;
	    panelManuscript.add(Date);
	    
	    JLabel Review = new JLabel("Review");
		Review.setHorizontalAlignment(SwingConstants.CENTER);
		Review.setFont(new Font("Tahoma", Font.BOLD, 14));
	    GridBagConstraints gridReview = new GridBagConstraints();
	    gridReview.gridx = 1;
	    gridReview.insets = new Insets(0, 0, 5, 5);
	    gridReview.gridy = 0;
	    panelManuscript.add(Review);
		
		JLabel  Edit = new JLabel("Edit");
		Edit.setHorizontalAlignment(SwingConstants.CENTER);
		Edit.setFont(new Font("Tahoma", Font.BOLD, 14));
	    GridBagConstraints gridEdit = new GridBagConstraints();
	    gridEdit.gridx = 1;
	    gridEdit.insets = new Insets(0, 0, 5, 5);
	    gridEdit.gridy = 0;
	    panelManuscript.add(Edit);
		
		JLabel Remove = new JLabel("Date Reviewed");
		Remove.setHorizontalAlignment(SwingConstants.CENTER);
		Remove.setFont(new Font("Tahoma", Font.BOLD, 14));
	    GridBagConstraints gridRemove = new GridBagConstraints();
	    gridRemove.gridx = 1;
	    gridRemove.insets = new Insets(0, 0, 5, 5);
	    gridRemove.gridy = 0;
	    panelManuscript.add(Remove);
		
	    JSeparator separator = new JSeparator();
	    GridBagConstraints gbc_separator = new GridBagConstraints();
	    gbc_separator.fill = GridBagConstraints.HORIZONTAL;
	    gbc_separator.gridwidth = 7;
	    gbc_separator.insets = new Insets(0, 0, 5, 0);
	    gbc_separator.gridx = 0;
	    gbc_separator.gridy = 1;
	    panelManuscript.add(separator, gbc_separator);
		
	    // List of Papers
	    List<Paper> myPapers = (this.mySystem.getConference()).getPapersToReview(mySystem.getCurrentUser());
		Iterator<Paper> myIt = myPapers.iterator();
	    for(int i = 0; myIt.hasNext(); i++){
	    	currentPaper = myIt.next();
		    JLabel Topic1 = new JLabel(currentPaper.getTitle());
		    GridBagConstraints gridTopic1 = new GridBagConstraints();
		    gridTopic1.insets = new Insets(0, 0, 5, 5);
		    gridTopic1.gridx = 0;
		    gridTopic1.gridy = i+2;
		    panelManuscript.add(Topic1, gridTopic1);
		    
		    JLabel Date1 = new JLabel("12/15/201"+i);
		    GridBagConstraints gridDate1 = new GridBagConstraints();
		    gridDate1.insets = new Insets(0, 0, 5, 5);
		    gridDate1.gridx = 1;
		    gridDate1.gridy = i+2;
		    panelManuscript.add(Date1, gridDate1);
		    
		    JLabel Review1 = new JLabel(" ");
		    Review1.setIcon(new ImageIcon("src/supportingFiles/review.png"));
		    Review1.setBorder(brdr);
		    GridBagConstraints gridReview1 = new GridBagConstraints();
		    gridReview1.insets = new Insets(0, 0, 5, 5);
		    gridReview1.gridx = 2;
		    gridReview1.gridy = i+2;
		    Review1.addMouseListener(new SubmitListen());
		    panelManuscript.add(Review1, gridReview1);
		    
		    JLabel Edit1 = new JLabel(" ");
		    Edit1.setIcon(new ImageIcon("src/supportingFiles/edit.png"));
		    Edit1.setBorder(brdr);
		    GridBagConstraints gridEdit1 = new GridBagConstraints();
		    gridEdit1.insets = new Insets(0, 0, 5, 5);
		    gridEdit1.gridx = 3;
		    gridEdit1.gridy = i+2;
		    panelManuscript.add(Edit1, gridEdit1);
		    
		    JLabel Remove1 = new JLabel("12/15/201"+i);
		    GridBagConstraints gridRemove1 = new GridBagConstraints();
		    gridRemove1.insets = new Insets(0, 0, 5, 5);
		    gridRemove1.gridx = 4;
		    gridRemove1.gridy = i+2;
		    panelManuscript.add(Remove1, gridRemove1);
	    }
	    
	    return panelManuscript;
	}
	private class SubmitListen extends MouseAdapter {
		public void mouseClicked(MouseEvent arg0) {
			new SubmitReview(currentPaper, mySystem.getCurrentUser());
		}
	}
}

