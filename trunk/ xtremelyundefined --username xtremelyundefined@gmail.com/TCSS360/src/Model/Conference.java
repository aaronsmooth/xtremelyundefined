package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Observable;

/**
 * This class represents a conference that holds papers whose members
 * that are authors submit, reviewers review, and sub-program chairs 
 * recommend and program chairs approve to the conference.
 * @author Aaron Nelson
 * @version 5/19/2014
 *
 */
public class Conference extends Observable implements Serializable{
	
	/**
	 * ID used for unique serialization
	 * added by Mitchell Alpert
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
	 * Adds an SPC to the conference. This method enforces business rule 6
	 * that a user has to already be a reviewer for the current conference
	 * before being eligible to be a Sup-Program Chair
	 * 
	 * @param newSPC the SPC to add
	 */
	public void addSPC(User newSPC) {
		if (newSPC == null) {
			throw new IllegalArgumentException();
		}
		if (!spc.contains(newSPC) && reviewers.contains(newSPC)) {
			spc.add(Objects.requireNonNull(newSPC));
		}
	}
	
	/**
	 * Sets a new PC for this conference. This method should never be
	 * called by anyone.
	 * 
	 * @param newPC PC to add
	 */
	public void setPC(User newPC) {
		if (newPC == null) {
			throw new IllegalArgumentException();
		}
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
		if (title == null) {
			throw new IllegalArgumentException();
		}
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
		if (title == null) {
			throw new IllegalArgumentException();
		}
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
	 * @return true if the paper is submitted successfully; otherwise false.
	 */
	public boolean submitPaper(Paper a_paper) {
		boolean isSuccess = false;
		if (a_paper == null) {
			throw new IllegalArgumentException();
		}
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
	
	/**
	 * This method finds all papers of this conference authored by a user
	 * and makes a deep copy of them all to return
	 * 
	 * @param a_user The author of papers
	 * @return A list of Papers this user is the author of
	 */
	public List<Paper> getAuthored(User a_user) {
		if (a_user == null) {
			throw new IllegalArgumentException();
		}
		List<Paper> myList = new ArrayList<Paper>();
		for (Iterator<Paper> iter = papers.iterator(); iter.hasNext(); ) {
			Paper current = iter.next();
			if(current.getAuthor().getID() == a_user.getID()) {
				myList.add(current);
			}
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
		if (a_user == null) {
			throw new IllegalArgumentException();
		}
		List<Paper> myList = new ArrayList<Paper>();
		if (reviewers.contains(a_user)) {
			for (Iterator<Paper> iter = papers.iterator(); iter.hasNext();) {
				Paper current = iter.next();
				if (current.isAReviewer(a_user)) {
					myList.add(current);
				}
			}
		}
		return myList;
	}
	
	/**
	 * This method finds all papers assigned to a SPC
	 * 
	 * @param a_user
	 * @return a list of papers assigned to a SPC.
	 */
	public List<Paper> getPapersBySPC(User a_user) {
		if (a_user == null) {
			throw new IllegalArgumentException();
		}
		List<Paper> myList = new ArrayList<Paper>();
		if (spc.contains(a_user)) {
			for (Iterator<Paper> iter = papers.iterator(); iter.hasNext();) {
				Paper current = iter.next();
				if (a_user == current.getSPC()) {
					myList.add(current);
				}
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
		return spc;
	}
	
	/**
	 * Adds a reviewer to the list of reviewers.
	 * 
	 * @param reviewer User to add as reviewer
	 */
	public void addReviewer(User reviewer) {
		if (reviewer == null) {
			throw new IllegalArgumentException();
		}
		if (!reviewers.contains(reviewer)) {
		reviewers.add(Objects.requireNonNull(reviewer));
		}
	}
	
	/**
	 * Returns list of reviewers
	 * added by Mitchell Alpert
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
		return name;
	}
	
	/**
	 * Getter for the Program Chair of the current conference
	 * 
	 * @return a User that is the PC
	 */
	public User getPC() {
		return pc;
	}
	
	/**
	 * This method figures out if a user is part of the current conference
	 * added by Mitchell Alpert
	 * 
	 * @param usr a User to look for
	 * @return True if User is in conference
	 */
	public boolean hasUser(User usr){
		if (usr == null) return false;
		else return usr.equals(this.pc) || getReviewers().contains(usr) || getSPCs().contains(usr);
	}
	
	/**
	 * This method finds all roles of a User
	 * added by Mitchell Alpert
	 * 
	 * @param usr The user to look up
	 * @return a list of that user's roles
	 */
	public List<String> getRoles(User usr) {
		List<String> ret = new ArrayList<String>();
		
		if (usr != null) {
			if (getReviewers().contains(usr)) ret.add("Reviewer");
			if (getSPCs().contains(usr)) ret.add("SubProgram Chair");
			if (usr.equals(this.pc)) ret.add("Program Chair");
		}
		
		return ret;
	}
	
	/**
	 * This method gets all papers of this conference
	 * added by Mitchell Alpert
	 * 
	 * @return a list of papers
	 */
	public List<Paper> getAllPapers() {
		return this.papers;
	}
}
