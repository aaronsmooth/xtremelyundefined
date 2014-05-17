package Model;

import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class Conference extends Observable {
	
	/**
	 * The location of the conference
	 */
	String location;
	
	/**
	 * The date the conference takes place using
	 * the format "MM/DD/YYYY"
	 */
	String date;
	
	/**
	 * The last day for papers to be submitted to the conference using
	 * the format "MM/DD/YYYY" effective 11:59pm PST that night
	 */
	String deadline;
	
	/**
	 * The name of this conference
	 */
	String name;
	
	/**
	 * The program chair assigned to this conference
	 */
	User pc;
	
	/**
	 * The list of sub-program chairs for this conference
	 */
	List spc;
	
	/**
	 * The list of reviewers for this conference
	 */
	List reviewers;
	
	/**
	 * A map that contains the papers of this conference 
	 * and the sub-program chair assigned to it.
	 */
	Map<Paper, User> spcMap;
	
	/**
	 * A map that contains the papers of this conference 
	 * and the authors that wrote them.
	 */
	Map<Paper, User> authorMap;
	
	/**
	 * The list of papers submitted to this conference
	 */
	List papers;
	
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
		reviewers = new ArrayList();
		reviewers = new ArrayList();
		spcMap = new HashMap<Paper, User>();
		authorMap = new HashMap<Paper, User>();
		papers = new ArrayList();
		
	}
	
	/**
	 * This method removes a paper from the conference that matches the title.
	 * 
	 * @param title The title of the paper to remove
	 * @return True if paper was removed successfully
	 */
	public boolean removePaper{String title) {
		boolean paperFound = false;
		Paper target = getPaper(title);
		if (target != null) {
			paperFound = true;
			papers.remove(target);
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
		Paper result;
		for (Iterator iter = papers.iterator(); iter.hasNext(); ) {
			Paper current = iter.next();
			if (current.title == title) {
				result = paper;
				break;
			}
		}
		return result;
	}
	
	public boolean submitPaper(Paper a_paper) {
		
	}
}
