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
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;

import Model.Approval;
import Model.Conference;
import Model.ManagementSystem;
import Model.Paper;
import Model.User;


@SuppressWarnings("serial")
public class PCPanel extends JPanel {
	
	private String Author;

	private Paper currentPaper;
	
	private ManagementSystem mySystem;
	
	private List<Paper> myPapers; 
	
	private int start;
	
	private int end;
	
	private int size;
	
	private boolean forward;
	
	private boolean backward;
	
	private JPanel midPanel;
	
	private JPanel bottomPanel;
	
	private JPanel manuscriptPanel;
	
	private JPanel arrowsPanel;
	
	/**
	 * Constructor.
	 */
	public PCPanel(ManagementSystem theSystem){
	   /* User usr = new User(55, "joe", "schmoe", "schmoe@gmail.com");
		User usr2 = new User(56, "jon", "doe", "doe@gmail.com");
		Paper ppr = new Paper(usr, "thisisthetitle", "thekeywords", "theabstact", "filepath");
		Paper ppr2 = new Paper(usr, "alsoatitle", "alsokeywords", "alsoanabstact", "alsoafilepath");
		Conference conf = theSystem.getConference();
		boolean state;
		boolean state1 = theSystem.getConference().submitPaper(ppr2);
		theSystem.addConference(conf);
		
		for(int i = 0; i < 10; i++){
			User usri = new User(5+i, "joe"+i, "schmoe", "schmoe@gmail.com");
			Paper ppri = new Paper(usri, "thisisthetitle" + i, "thekeywords" + i, "theabstact", "filepath", "topic" + i);
			state = theSystem.getConference().submitPaper(ppri);
		} */
		
		mySystem = theSystem;
		myPapers = (this.mySystem.getConference()).getAllPapers();
		start = 1;
		size = myPapers.size();
		if (size > 8){
			end = 8;
			forward = true;
		} else{
			end = size;
			forward = false;
		}
		
		setLayout(new BorderLayout());
		AbstractBorder brdr = new TextBubbleBorder(Color.BLACK,2,6,0);
		
		JPanel topPanel; //, bottomPanel, midPanel;
		topPanel = topPanel(brdr, mySystem.getCurrentUser().getName());
		manuscriptPanel = manuscripts(brdr);
		arrowsPanel = arrowPanel(brdr);
		bottomPanel = bottomPanel(brdr, manuscriptPanel, arrowsPanel);
		midPanel = midPanel(bottomPanel, brdr, mySystem.getConference().getName());
		
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
		
		JLabel logout = new LogOutLabel("Logout");
		logout.addPropertyChangeListener(mySystem);
		logout.setBorder(brdr);
		JPanel logPanel = new JPanel();
		logPanel.setLayout(new GridLayout(1,6));
		for(int i =1; i < 6; i++){
			logPanel.add(new JLabel("      ")); // Dummy JLabel for proper placement of logout button.
		}
		logPanel.add(logout);
		
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
		bottomPanel.setBounds(100, 110, 1000, 580);

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
		arrowPanel.setLayout(new GridLayout(2,1));
		
		JSeparator separator1 = new JSeparator();
	    GridBagConstraints gbc_separator1 = new GridBagConstraints();
	    gbc_separator1.fill = GridBagConstraints.HORIZONTAL;
	    gbc_separator1.gridwidth = 7;
	    gbc_separator1.insets = new Insets(0, 0, 5, 0);
	    gbc_separator1.gridx = 0;
	    gbc_separator1.gridy = 1;
		
	    final JLabel prev = new JLabel(" ");
		prev.setIcon(new ImageIcon("src/supportingFiles/prev.png"));
		prev.setBorder(brdr);
		prev.setHorizontalAlignment(SwingConstants.CENTER);
		prev.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(start == 1){ //if start is at original position, it will not move
					backward = false;
				} else {
					
					remove(midPanel);
					AbstractBorder brdr = new TextBubbleBorder(Color.BLACK,2,6,0);
					
					if(start - 8 > 8){
						start -= 8;
						backward = true;
					
						end = end -8;
						forward = true;
						
						manuscriptPanel = manuscripts(brdr);
						bottomPanel = bottomPanel(brdr, manuscriptPanel, arrowsPanel);
						midPanel = midPanel(bottomPanel, brdr, mySystem.getConference().getName());
						add(midPanel);
						prev.addPropertyChangeListener(mySystem);
						validate();
						
					} else {	
						start = Math.max((start - 8),1);
						backward = false;
					
						end = end - 8;
						forward = true;
						
						manuscriptPanel = manuscripts(brdr);
						bottomPanel = bottomPanel(brdr, manuscriptPanel, arrowsPanel);
						midPanel = midPanel(bottomPanel, brdr, mySystem.getConference().getName());
						add(midPanel);
						prev.addPropertyChangeListener(mySystem);
						validate();
					}
				}
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				AbstractBorder brdr;
				if(backward){
				brdr = new TextBubbleBorder(Color.BLUE,2,6,0);
				}else {
				brdr = new TextBubbleBorder(Color.BLACK,2,6,0);
				}
				prev.setBorder(brdr);		
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				AbstractBorder brdr = new TextBubbleBorder(Color.BLACK,2,6,0);
			    prev.setBorder(brdr);		
				repaint();
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}
			
		});
		
	    final JLabel next = new JLabel(" ");
	    next.setIcon(new ImageIcon("src/supportingFiles/next.png"));
	    next.setBorder(brdr);
		next.setHorizontalAlignment(SwingConstants.CENTER);
		next.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(end == size){
					forward = false;
				} else {
					remove(midPanel);
					AbstractBorder brdr = new TextBubbleBorder(Color.BLACK,2,6,0);
					
					start = end + 1;
					backward = true;
				
					if(size - end > 8){
						forward = true;
						end +=8;
					} else {
						forward = false;
						end += (size - end);
					}
					manuscriptPanel = manuscripts(brdr);
					bottomPanel = bottomPanel(brdr, manuscriptPanel, arrowsPanel);
					midPanel = midPanel(bottomPanel, brdr, mySystem.getConference().getName());
					add(midPanel);
					next.addPropertyChangeListener(mySystem);
					validate();
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				AbstractBorder brdr;
				if(forward){
				brdr = new TextBubbleBorder(Color.BLUE,2,6,0);
				}else {
				brdr = new TextBubbleBorder(Color.BLACK,2,6,0);
				}
				next.setBorder(brdr);		
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				AbstractBorder brdr = new TextBubbleBorder(Color.BLACK,2,6,0);
			    next.setBorder(brdr);		
				repaint();
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub				
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub				
			}
			
		});
		
		arrowPanel.add(separator1, gbc_separator1);
		JPanel insideArrowPanel = new JPanel();
		insideArrowPanel.setLayout(new GridLayout(1,7));
		for(int i=0; i < 13; i++){
			if(i == 3 ) insideArrowPanel.add(prev);
			else if(i == 9) insideArrowPanel.add(next);
			else insideArrowPanel.add(new JLabel(" "));
		}
		arrowPanel.add(insideArrowPanel);
	
		return arrowPanel;
	}
	
	/**
	 * Creates a display panel for all the manuscripts in the conference
	 * @param brdr necessary border style for 
	 * @return
	 */
	public JPanel manuscripts(AbstractBorder brdr){
		
		GridBagLayout gridBagLayout = new GridBagLayout();
	    gridBagLayout.columnWidths = new int[]{150, 150, 150, 150, 150, 150};
	    gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
	    gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
	    gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	    
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
	    
	    JLabel Review = new JLabel("Assigned SPC");
		Review.setHorizontalAlignment(SwingConstants.CENTER);
		Review.setFont(new Font("Tahoma", Font.BOLD, 14));
	    GridBagConstraints gridReview = new GridBagConstraints();
	    gridReview.gridx = 1;
	    gridReview.insets = new Insets(0, 0, 5, 5);
	    gridReview.gridy = 0;
	    panelManuscript.add(Review);
		
		JLabel  Edit = new JLabel("Reviewer");
		Edit.setHorizontalAlignment(SwingConstants.CENTER);
		Edit.setFont(new Font("Tahoma", Font.BOLD, 14));
	    GridBagConstraints gridEdit = new GridBagConstraints();
	    gridEdit.gridx = 1;
	    gridEdit.insets = new Insets(0, 0, 5, 5);
	    gridEdit.gridy = 0;
	    panelManuscript.add(Edit);
	    
	    JLabel  Recommend = new JLabel(" View Recommendation");
		Recommend.setHorizontalAlignment(SwingConstants.CENTER);
		Recommend.setFont(new Font("Tahoma", Font.BOLD, 14));
	    GridBagConstraints gridRecommend = new GridBagConstraints();
	    gridRecommend.gridx = 1;
	    gridRecommend.insets = new Insets(0, 0, 5, 5);
	    gridRecommend.gridy = 0;
	    panelManuscript.add(Recommend);
		
		JLabel Remove = new JLabel("Acceptance Status");
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
	    
	    attachManuscripts(brdr, panelManuscript);
	    
	    return panelManuscript;
	}
	
	/**
	 *  Attaches the list of manuscript in a panel.
	 * @param brdr necessary border styles for JLabels and JPanels.
	 * @param p a panel where the manuscript will be attached.
	 */
	 public void attachManuscripts(AbstractBorder brdr, JPanel p){
		 //Iterator<Paper> myIt = myPapers.iterator(); 
		 for(int i = start-1 ; i < end; i++){
			 	currentPaper = myPapers.get(i);
			    JLabel Topic1 = new JLabel(currentPaper.getTitle());
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
			    
			    PanelLabel SPC1;
			    if(currentPaper.getSPC() != null) {
			    	SPC1= new PanelLabel(currentPaper.getSPC().getName(), currentPaper);
			    } else {
				    SPC1 = new PanelLabel(" ", currentPaper);
				    SPC1.setIcon(new ImageIcon("src/supportingFiles/spc.png"));
				    SPC1.setBorder(brdr);
				    SPC1.setHorizontalAlignment(SwingConstants.CENTER);
				    SPC1.addMouseListener(new PCPick());
			    }
			    GridBagConstraints gridSPC1 = new GridBagConstraints();
			    gridSPC1.insets = new Insets(0, 0, 5, 5);
			    gridSPC1.gridx = 2;
			    gridSPC1.gridy = i+2;
			    p.add(SPC1, gridSPC1);
			    
			    JLabel Reviewer1;
			    Set<User> rev = currentPaper.getReviewers();
			    if (rev.size() == 0) {
				    Reviewer1 = new JLabel(" ---------- ");
				    
			    } else {
			    	Iterator<User> myRevIt = rev.iterator();
			    	String reviewerNames = myRevIt.next().getName();
			    	while (myRevIt.hasNext()) {
			    		reviewerNames += ", ";
			    		reviewerNames += myRevIt.next().getName();
			    	}
				    Reviewer1 = new JLabel(reviewerNames);
				}
			    GridBagConstraints gridReviewer1 = new GridBagConstraints();
			    gridReviewer1.insets = new Insets(0, 0, 5, 5);
			    gridReviewer1.gridx = 3;
			    gridReviewer1.gridy = i+2;
			    p.add(Reviewer1, gridReviewer1);
			    
			    
			    PanelLabel Recommend1;
			    if(currentPaper.getRationale() != null){
			       Recommend1 = new PanelLabel(currentPaper.getRating() + ": " 
			    		   + currentPaper.getRationale(), currentPaper);
			    } else {
			    	Recommend1 = new PanelLabel(" ", currentPaper);
			    	Recommend1.setIcon(new ImageIcon("src/supportingFiles/recd.png"));
			    	Recommend1.setBorder(brdr);
			    	Recommend1.setHorizontalAlignment(SwingConstants.CENTER);
			    }
			    
			    GridBagConstraints gridRecommend1 = new GridBagConstraints();
			    gridRecommend1.insets = new Insets(0, 0, 5, 5);
			    gridRecommend1.gridx = 4;
			    gridRecommend1.gridy = i+2;
			    p.add(Recommend1, gridRecommend1);
			    
			    PanelLabel Status1 = new PanelLabel(" ", currentPaper);
			    Approval status = currentPaper.getAcceptanceStatus();
			    Status1.addMouseListener(new AcceptAction());
			    switch(status) {
			    case YES:
			    	Status1.setIcon(new ImageIcon("src/supportingFiles/approve.png"));
			    	break;
			    case NO:
			    	Status1.setIcon(new ImageIcon("src/supportingFiles/reject.png"));
			    	break;
			    default:
			    	Status1.setIcon(new ImageIcon("src/supportingFiles/decide.png"));
			    	Status1.setBorder(brdr);
				    Status1.setHorizontalAlignment(SwingConstants.CENTER);
			    }
		    	Status1.setBorder(brdr);
		    	Status1.setHorizontalAlignment(SwingConstants.CENTER);

			    GridBagConstraints gridRemove1 = new GridBagConstraints();
			    gridRemove1.insets = new Insets(0, 0, 5, 5);
			    gridRemove1.gridx = 5;
			    gridRemove1.gridy = i+2;
			    p.add(Status1, gridRemove1);
			    }
	 }
	 private class PCPick extends MouseAdapter {

		 @Override
		 public void mouseClicked(MouseEvent arg0) {

			 User selected = new User(0, "", "", "");
			 SelectBox myBox = new SelectBox(mySystem.getConference().getSPCs(), 
					 "SPC", ((PanelLabel) arg0.getSource()).getPaper());
			 myBox.addPropertyChangeListener(mySystem);
			 currentPaper.setSPC(selected);
		 }
	 }
	 
	 private class AcceptAction extends MouseAdapter {
		 @Override
		 public void mouseClicked(MouseEvent arg0) {
			PanelLabel src = (PanelLabel) arg0.getSource();
			 Approval status = src.getPaper().getAcceptanceStatus();
			 switch (status) {
			 	case YES:
			 		src.getPaper().setAccepted(Approval.NO);
			    	src.setIcon(new ImageIcon("src/supportingFiles/reject.png"));
			 		break;
			 	case NO:
			 		src.getPaper().setAccepted(Approval.UNDECIDED);
			    	src.setIcon(new ImageIcon("src/supportingFiles/decide.png"));
			 		break;
			 	case UNDECIDED:
			 		src.getPaper().setAccepted(Approval.YES);
			    	src.setIcon(new ImageIcon("src/supportingFiles/approve.png"));
			 		break;
			 	default:
			 		break;
			 }
		 }

	 }
}


