package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Observable;


public class Conference extends Observable implements Serializable{
	
	/**
	 * ID used for unique serialization
	 */
	private static final long serialVersionUID = -6376784954447084287L;

	/**
	 * The location of the conference
	 */
	private String location;
	
	/**
	 * The date the conference takes place using
	 * the format "MM/DD/YYYY"
	 */
	private String date;
	
	/**
	 * The last day for papers to be submitted to the conference using
	 * the format "MM/DD/YYYY" effective 11:59pm PST that night
	 */
	private String deadline;
	
	/**
	 * The name of this conference
	 */
	private String name;
	
	/**
	 * The program chair assigned to this conference
	 */
	private User pc;
	
	/**
	 * The list of sub-program chairs for this conference
	 */
	private List<User> spc;
	
	/**
	 * The list of reviewers for this conference
	 */
	private List<User> reviewers;
	
	/**
	 * A map that contains the papers of this conference 
	 * and the sub-program chair assigned to it.
	 */
	private Map<Paper, User> spcMap;
	
	/**
	 * A map that contains the papers of this conference 
	 * and the authors that wrote them.
	 */
	private Map<Paper, User> authorMap;
	
	/**
	 * The list of papers submitted to this conference
	 */
	private List<Paper> papers;
	
	/**
	 * This method serves as the constructor for the Conference class.
	 * 
	 * @param alocation A string representing the location of the conference
	 * @param adate A string representing the date the conference takes place
	 * @param adeadline A string representing the deadline for this conference
	 * @param apc A user that is assigned the program chair position
	 * @param aname The name of this conference
	 */
	public Conference(String alocation, String adate, String adeadline, User apc, String aname) {
		this.location = alocation;
		this.date = adate;
		this.deadline = adeadline;
		this.pc = apc;
		this.name = aname;
		spc = new ArrayList<User>();
		reviewers = new ArrayList<User>();
		spcMap = new HashMap<Paper, User>();
		authorMap = new HashMap<Paper, User>();
		papers = new ArrayList<Paper>();
		
	}
	
	/**
	 * Adds an SPC to the conference
	 * 
	 * @param newSPC the SPC to add
	 */
	public void addSPC(User newSPC) {
		if (!spc.contains(newSPC)) {
			reviewers.add(Objects.requireNonNull(newSPC));
		}
	}
	
	/**
	 * Sets a new PC for this conference.
	 * 
	 * @param newPC PC to add
	 */
	public void setPC(User newPC) {
		pc = newPC;
	}
	
	/**
	 * Returns the name of this conference.
	 * 
	 * @return String the name of the conference
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This method removes a paper from the conference that matches the title.
	 * 
	 * @param title The title of the paper to remove
	 * @return True if paper was removed successfully
	 */
	public boolean removePaper(String title) {
		boolean paperFound = false;
		Paper target = getPaper(title);
		if (target != null) {
			paperFound = true;
			papers.remove(target);
			authorMap.remove(target);
			spcMap.remove(target);
		}
		return paperFound;
	}
	
	/**
	 * This method finds the Paper object in this conference matching a title
	 * 
	 * @param title The title of the paper to search for
	 * @return The paper object that matches the title
	 */
	public Paper getPaper(String title) {
		Paper result = null;
		
		for (Iterator<Paper> iter = papers.iterator(); iter.hasNext(); ) {
			Paper current = iter.next();
			if (current.getTitle() == title) {
				result = current;
				break;
			}
		}
		return result;
	}
	
	/**
	 * This method is used to submit a paper to the conference
	 * @param a_paper
	 * @return
	 */
	public boolean submitPaper(Paper a_paper) {
		boolean isSuccess = false;
		for (Iterator<Paper> iter = papers.iterator(); iter.hasNext(); ) {
			Paper current = iter.next();
			if (current.getTitle() == a_paper.getTitle()) {	//duplicate paper title, submission failed
				return isSuccess;
			}
		}
		isSuccess = true;
		papers.add(a_paper);
		spcMap.put(a_paper, null);
		authorMap.put(a_paper, a_paper.getAuthor());
		return isSuccess;
	}
	
	/*
	/**
	 * This method is used to edit a paper
	 * @param a_paper
	 * @param b
	 * @return
	 
	public boolean submitPaper(Paper a_paper, boolean b) {
		
	}
	*/
	
	/**
	 * This method finds all papers of this conference authored by a user
	 * 
	 * @param a_user The author of papers
	 * @return A list of Papers this user is the author of
	 */
	public List<Paper> getAuthored(User a_user) {
		List<Paper> myList = new ArrayList<Paper>();
		for (Iterator<Paper> iter = papers.iterator(); iter.hasNext(); ) {
			Paper current = iter.next();
			if(current.getAuthor().getID() == a_user.getID())
				myList.add(current);
		}
		return myList;
	}
	
	/**
	 * This method finds all papers assigned to a reviewer
	 * 
	 * @param a_user The reviewer to look for
	 * @return A list of papers this reviewer is to review
	 */
	public List<Paper> getPapersToReview(User a_user) {
		List<Paper> myList = new ArrayList<Paper>();
		for (Iterator<Paper> iter = papers.iterator(); iter.hasNext();) {
			Paper current = iter.next();
			if (current.isAReviewer(a_user)) {
				myList.add(current);
			}
		}
		return myList;
	}
	
	/**
	 * This method finds all papers assigned to a SPC
	 * 
	 * @param a_user
	 * @return
	 */
	public List<Paper> getPapersBySPC(User a_user) {
		List<Paper> myList = new ArrayList<Paper>();
		for (Iterator<Paper> iter = papers.iterator(); iter.hasNext();) {
			Paper current = iter.next();
			if (a_user == current.getSPC()) {
				myList.add(current);
			}
		}
		return myList;
	}
	
	/**
	 * This method makes a deep copy of the list of Sub-Program chairs assigned
	 * to this conference
	 * 
	 * @return A list of the SPC's for this conference
	 */
	public List<User> getSPCs() {
		List<User> myList = new ArrayList<User>();
		for (Iterator<User> iter = spc.iterator(); iter.hasNext(); ){
			User current = iter.next();
			myList.add(new User(current.getID(), current.getFirstName(), 
					current.getLastName(), current.getEmail()));
		}
		return myList;
	}
	
	/**
	 * Adds a reviewer to the list of reviewers.
	 * 
	 * @param reviewer User to add as reviewer
	 */
	public void addReviewer(User reviewer) {
		reviewers.add(Objects.requireNonNull(reviewer));
	}
	
	/**
	 * Returns list of reviewers
	 * 
	 * @return List<Users> list of reviewers assigned to this 
	 * 	conference.
	 */
	public List<User> getReviewers() {
		return reviewers;
	}
	
	@Override
	/**
	 * <@inheritDoc>
	 */
	public String toString() {
		return name + ": " + " \n\tPC:" + pc + " \n\tSPCs: " + spc + " \n\tReviewers: " + reviewers;
	}
	
}
