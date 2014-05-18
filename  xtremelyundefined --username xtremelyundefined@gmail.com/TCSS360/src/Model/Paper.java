package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Paper {
	
	/**
	 * The local filepath of the file.
	 */
	private String filePath;
	
	/**
	 * Name of the author.
	 */
	private User author;
	
	/**
	 * Title of paper.
	 */
	private String title;
	
	/**
	 * Keywords indicated for this paper.
	 */
	private String keywords;
	
	/**
	 * Abstract for this paper.
	 */
	private String paperAbstract;
	
	/**
	 * The spc assigned to this paper.
	 */
	private User spc;
	
	/**
	 * The rating given the paper by its SPC.
	 */
	private int spcRating;
	
	/**
	 * The summary recommendation from the SPC
	 */
	private String spcSummary;
	
	/**
	 * If this paper has been accepted by the PC
	 */
	private Approval isAccepted;
	
	/**
	 * Map of reviewers to reviews.
	 */
	private Map<User, Review> reviewerMap;
	
	/**
	 * Public constructor. Initializes all fields. title, keywords, abstract and filepath
	 * are all required.
	 * 
	 * @param title the title of the Paper.
	 * @param keywords keywords for this Paper
	 * @param paperAbstract paper's abstract
	 * @param filePath local filePath.
	 */
	public Paper(User author, String  title, String keywords, String paperAbstract, String filePath) {
		this.author = Objects.requireNonNull(author);
		this.title = Objects.requireNonNull(title);
		this.keywords = Objects.requireNonNull(keywords);
		this.paperAbstract = Objects.requireNonNull(paperAbstract);
		this.filePath = Objects.requireNonNull(filePath);
		isAccepted = Approval.UNDECIDED;
		reviewerMap = new HashMap<User, Review>();	
	}
	
	
	/**
	 * Sets the PCs acceptance of this paper for the conference
	 * 
	 * @param decision PC's decision
	 */
	public void setAccepted(Approval decision) {
		isAccepted = decision;
	}
	
	/**
	 * Returns the PCs decision
	 * 
	 * @return Approval the PCs decision
	 */
	public Approval getDecision() {
		return isAccepted;
	}
	
	/**
	 * Adds a Reviewer, Review pair to the reviewerMap. If the Review is null, 
	 * this indicates that the reviewer has been assigned but there is no
	 * review done yet.
	 * 
	 * @param reviewer Reviewer either assigned to the paper or who did this review
	 * @param newReview Review if it is complete. Null otherwise.
	 */
	public void review(User reviewer, Review newReview) {
		if (reviewer != null) {
			reviewerMap.put(reviewer, newReview);
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Assigns an SPC to this paper
	 * 
	 * @param spc the new SPC for this paper
	 */
	public void setSPC(User spc) {
		if (spc != null) {
			this.spc = spc;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Returns all of the available reviews done 
	 * on this paper. Resulting list will have
	 * all nulls removed.
	 * 
	 * @return List<Review> all completed reviews.
	 */
	public List<Review> getReviews() {
		List<Review>  ret = new ArrayList<Review>();
		
		for (Review rev : reviewerMap.values()) {
			if (rev != null) {
				ret.add(rev);
			}
		}
		
		return ret;
	}
	
	/**
	 * getter for the author of this Paper
	 * 
	 * @return The author as a User
	 */
	public User getAuthor() {
		return this.author;
	}
	
	/**
	 * This method checks whether a user is a reviewer assigned to this paper
	 * 
	 * @param rev The user to be checked
	 * @return True if the user is a reviewer assigned to this paper
	 */
	public boolean isAReviewer(User rev) {
		if (reviewerMap.containsKey(rev)) {
			return true;
		} else {
			return false;
		}
		
	}
	/**
	 * Getter for the Sub-Program Chair of this paper
	 * 
	 * @return The user that is the SPC
	 */
	public User getSPC() {
		return this.spc;
	}
	
	public String getTitle(){
		return title;
	}
}
