package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;


public class SPCPanel extends JPanel {
	
	private String Author = "JOE SCHMOE";
	
	/**
	 * Constructor.
	 */
	public SPCPanel(){
	setLayout(new BorderLayout());
	AbstractBorder brdr = new TextBubbleBorder(Color.BLACK,2,6,0);
	
	JPanel topPanel, bottomPanel, midPanel;
	
	topPanel = topPanel(brdr, Author);
	bottomPanel = bottomPanel(brdr, manuscripts(brdr), arrowPanel(brdr));
	midPanel = midPanel(bottomPanel, brdr, "Conference Name");
	
	add(topPanel, BorderLayout.NORTH);
	add(midPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Creates the top panel of the window where the author and logout option is displayed.
	 * 
	 * @param brdr necessary border for the label(s) and panel(s).
	 * @return a panel of a JPanel type.
	 */
	public JPanel topPanel(AbstractBorder brdr, String aName){
		JPanel topPanel = new JPanel(), topInside = new JPanel();
		JLabel author = new JLabel(aName);
		author.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel logout = new JLabel("Logout");
		logout.setBorder(brdr);
		JPanel logPanel = new JPanel();
		logPanel.setLayout(new GridLayout(1,4));
		for(int i =0; i < 4; i++){
			if(i == 3)logPanel.add(logout);
			logPanel.add(new JLabel("      ")); // Dummy JLabel for proper placement of logout button.
		}
		
		topInside.setLayout(new GridLayout(2,1));
		topInside.add(new JLabel("MSEE Conference"));
		topInside.add(new JLabel("Tracking System"));
		
		topPanel.setLayout(new GridLayout(1,3));
		topPanel.add(topInside);
		topPanel.add(author);
		topPanel.add(logPanel);
		topPanel.setBorder(brdr);
		topPanel.setOpaque(false);
		
		return topPanel;
	}
	
	/**
	 *  Creates the bottom panel where the list of Manuscript and arrow navigation will be displayed.
	 *   
	 * @param brdr  necessary border styles for labels and JPanel.
	 * @param panelManuscript a panel that display the manuscripts.
	 * @param arrowPanel a panel that display the navigation arrow
	 * @return a JPanel to be added to the midPanel.
	 */
	public JPanel bottomPanel(AbstractBorder brdr, JPanel panelManuscript, JPanel arrowPanel ){
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(brdr);
		bottomPanel.setOpaque(false);
		bottomPanel.setSize(400, 400);
		bottomPanel.setBounds(100, 110, 1000, 550);
		
		JPanel insideBottomPanel = new JPanel();
		insideBottomPanel.setLayout(new BorderLayout());
		JLabel mypaper = new JLabel("Manuscript(s)");
		mypaper.setFont(new Font("TimesRoman", Font.BOLD, 18));
		mypaper.setForeground(Color.BLACK);
		mypaper.setHorizontalAlignment(SwingConstants.CENTER);
		JPanel paperPanel = new JPanel();
		paperPanel.setLayout(new GridLayout(2,1));
		paperPanel.add(mypaper);
		paperPanel.add(new JLabel(" "));
		
		insideBottomPanel.add(paperPanel, BorderLayout.NORTH);
		insideBottomPanel.add(panelManuscript, BorderLayout.CENTER);
		insideBottomPanel.add(arrowPanel, BorderLayout.SOUTH);
		
		bottomPanel.add(insideBottomPanel);
		return bottomPanel;
	}
	/**
	 * Creates a midPanel that would display the Name of the conference
	 * @param brdr necessary border for the label(s) and panel(s).
	 * @return a panel of a JPanel type.
	 */
	public JPanel midPanel(JPanel bottomPanel, AbstractBorder brdr, String cName){
		JPanel midPanel = new JPanel();
		midPanel.setSize(1200,800);
		midPanel.setLayout(null);
		
		JLabel conference = new JLabel(cName);
		conference.setFont(new Font("TimesRoman", Font.BOLD, 25));
		conference.setForeground(Color.BLACK);
		conference.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel midTop = new JPanel();
		midTop.setBorder(brdr);
		midTop.setBounds(100,10,1000,70);
		midTop.add(conference);
		
		midPanel.add(midTop);
		midPanel.add(bottomPanel);
		
		return midPanel;
	}
	
	/**
	 * Creates a panel displays the navigation arrow: forward and backward arrows.
	 * 
	 * @param brdr necessary border style for the JLabels and JPanels.
	 * @return a panel displaying an arrow navigation.
	 */
	public JPanel arrowPanel(AbstractBorder brdr){
		
		JPanel arrowPanel = new JPanel();
		arrowPanel.setLayout(new GridLayout(1,1));
		
		JSeparator separator1 = new JSeparator();
	    GridBagConstraints gbc_separator1 = new GridBagConstraints();
	    gbc_separator1.fill = GridBagConstraints.HORIZONTAL;
	    gbc_separator1.gridwidth = 7;
	    gbc_separator1.insets = new Insets(0, 0, 5, 0);
	    gbc_separator1.gridx = 0;
	    gbc_separator1.gridy = 1;

		arrowPanel.add(separator1, gbc_separator1);
	
		return arrowPanel;
	}
	
	/**
	 * Creates a display panel for all the manuscripst in the conference
	 * @param brdr necessary border style for 
	 * @return
	 */
	public JPanel manuscripts(AbstractBorder brdr){
		
		GridBagLayout gridBagLayout = new GridBagLayout();
	    gridBagLayout.columnWidths = new int[]{150, 150, 150, 150, 150};
	    gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
	    gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
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
	    
	    JLabel Review = new JLabel("Assigned Reviewer");
		Review.setHorizontalAlignment(SwingConstants.CENTER);
		Review.setFont(new Font("Tahoma", Font.BOLD, 14));
	    GridBagConstraints gridReview = new GridBagConstraints();
	    gridReview.gridx = 1;
	    gridReview.insets = new Insets(0, 0, 5, 5);
	    gridReview.gridy = 0;
	    panelManuscript.add(Review);
		
		JLabel  Edit = new JLabel("View Review(s)");
		Edit.setHorizontalAlignment(SwingConstants.CENTER);
		Edit.setFont(new Font("Tahoma", Font.BOLD, 14));
	    GridBagConstraints gridEdit = new GridBagConstraints();
	    gridEdit.gridx = 1;
	    gridEdit.insets = new Insets(0, 0, 5, 5);
	    gridEdit.gridy = 0;
	    panelManuscript.add(Edit);
	    
	    JLabel  Recommend = new JLabel(" Send Recommendation");
		Recommend.setHorizontalAlignment(SwingConstants.CENTER);
		Recommend.setFont(new Font("Tahoma", Font.BOLD, 14));
	    GridBagConstraints gridRecommend = new GridBagConstraints();
	    gridRecommend.gridx = 1;
	    gridRecommend.insets = new Insets(0, 0, 5, 5);
	    gridRecommend.gridy = 0;
	    panelManuscript.add(Recommend);
		/*
		JLabel Remove = new JLabel("Acceptance Status");
		Remove.setHorizontalAlignment(SwingConstants.CENTER);
		Remove.setFont(new Font("Tahoma", Font.BOLD, 14));
	    GridBagConstraints gridRemove = new GridBagConstraints();
	    gridRemove.gridx = 1;
	    gridRemove.insets = new Insets(0, 0, 5, 5);
	    gridRemove.gridy = 0;
	    panelManuscript.add(Remove);
		*/
	    JSeparator separator = new JSeparator();
	    GridBagConstraints gbc_separator = new GridBagConstraints();
	    gbc_separator.fill = GridBagConstraints.HORIZONTAL;
	    gbc_separator.gridwidth = 7;
	    gbc_separator.insets = new Insets(0, 0, 5, 0);
	    gbc_separator.gridx = 0;
	    gbc_separator.gridy = 1;
	    panelManuscript.add(separator, gbc_separator);
	    
	    attachManuscripts(brdr, panelManuscript);
	    
	    return panelManuscript;
	}
	
	/**
	 *  Attaches the list of manuscript in a panel.
	 * @param brdr necessary border styles for JLabels and JPanels.
	 * @param p a panel where the manuscript will be attached.
	 */
	 public void attachManuscripts(AbstractBorder brdr, JPanel p){
		 for(int i = 0; i < 4; i++){
			    JLabel Topic1 = new JLabel("New Scientific Frontier" + i);
			    GridBagConstraints gridTopic1 = new GridBagConstraints();
			    gridTopic1.insets = new Insets(0, 0, 5, 5);
			    gridTopic1.gridx = 0;
			    gridTopic1.gridy = i+2;
			    p.add(Topic1, gridTopic1);
			    
			    JLabel Date1 = new JLabel("12/15/201"+i);
			    GridBagConstraints gridDate1 = new GridBagConstraints();
			    gridDate1.insets = new Insets(0, 0, 5, 5);
			    gridDate1.gridx = 1;
			    gridDate1.gridy = i+2;
			    p.add(Date1, gridDate1);
			    
			    JLabel SPC1;
			    if(i % 2 == 0) {
			     SPC1= new JLabel("Michelle Yang" + i);
			     } else{
			    SPC1 = new JLabel(" ");
			    SPC1.setIcon(new ImageIcon("src/supportingFiles/spc.png"));
			    SPC1.setBorder(brdr);
			    SPC1.setHorizontalAlignment(SwingConstants.CENTER);
			    //topPanel.setOpaque(false);
			    }
			    GridBagConstraints gridSPC1 = new GridBagConstraints();
			    gridSPC1.insets = new Insets(0, 0, 5, 5);
			    gridSPC1.gridx = 2;
			    gridSPC1.gridy = i+2;
			    p.add(SPC1, gridSPC1);
			    
			    JLabel Reviewer1;
			    if(i %2 != 0){
			       Reviewer1 = new JLabel(" ---------- ");
			    } else {
			    	Reviewer1 = new JLabel(" ");
			    	Reviewer1.setIcon(new ImageIcon("src/supportingFiles/review.png"));
				    Reviewer1.setBorder(brdr);
				    Reviewer1.setHorizontalAlignment(SwingConstants.CENTER);
			    }
			    
			    GridBagConstraints gridReviewer1 = new GridBagConstraints();
			    gridReviewer1.insets = new Insets(0, 0, 5, 5);
			    gridReviewer1.gridx = 3;
			    gridReviewer1.gridy = i+2;
			    p.add(Reviewer1, gridReviewer1);
			    
			    JLabel Recommend1;
			    if(i %2 != 0){
			       Recommend1 = new JLabel(" ---------- ");
			    } else {
			    	Recommend1 = new JLabel(" ");
			    	Recommend1.setIcon(new ImageIcon("src/supportingFiles/recd.png"));
			    	Recommend1.setBorder(brdr);
			    	Recommend1.setHorizontalAlignment(SwingConstants.CENTER);
			    }

			    GridBagConstraints gridRecommend1 = new GridBagConstraints();
			    gridRecommend1.insets = new Insets(0, 0, 5, 5);
			    gridRecommend1.gridx = 4;
			    gridRecommend1.gridy = i+2;
			    p.add(Recommend1, gridRecommend1);
			    
			    }
	 }
}

