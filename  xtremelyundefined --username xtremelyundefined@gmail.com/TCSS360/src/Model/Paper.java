package Model;

import java.util.HashMap;
import java.util.Map;

public class Paper {
	
	/**
	 * The local filepath of the file.
	 */
	private String filePath;
	
	/**
	 * Name of the author.
	 */
	private String author;
	
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
	public Paper(String title, String keywords, String paperAbstract, String filePath) {
		this.title = title;
		this.keywords = keywords;
		this.paperAbstract = paperAbstract;
		this.filePath = filePath;
		isAccepted = UNDECIDED;
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
}
