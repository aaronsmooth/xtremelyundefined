package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * This class represent the paper and its component that will be submitted to a conference.
 * 
 * @author Mitchell Alpert
 * @modified Aaron Nelson
 * @version 5/19/2014
 */
public class Paper implements Serializable{
	
	/**
	 *  Serial version identification.
	 */
	private static final long serialVersionUID = -7053416395295120578L;

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
	 * Topic of the paper
	 */
	private String topic;
	
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
	private String spcRationale;
	
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
	public Paper(User author, String  title, String keywords, String paperAbstract, String filePath, String topic) {
		this.author = Objects.requireNonNull(author);
		this.title = Objects.requireNonNull(title);
		this.keywords = Objects.requireNonNull(keywords);
		this.paperAbstract = Objects.requireNonNull(paperAbstract);
		this.filePath = Objects.requireNonNull(filePath);
		this.topic = topic;
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
	 * review done yet. This 
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
	 * Get the review made by a specified reviewer
	 * @param reviewer who made the review(s)
	 * @return a review made by the reviewer.
	 */
	public Review getReview(User reviewer) {
		return reviewerMap.get(reviewer);
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
	 * Sets the rating given to the paper by its SPC.
	 * 
	 * @param rating the rating by the SPC
	 */
	public void setRating(int the_rating) {
		spcRating = the_rating;
	}
	
	/**
	 * Sets the recommendation rationale given by the SPC
	 * 
	 * @param rationale the rationale given by the SPC
	 */
	public void setRationale(String the_rationale) {
		spcRationale = the_rationale;
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
	
	/**
	 * Get the title of the paper.
	 * @return the title of the paper.
	 */
	public String getTitle(){
		return title;
	}
	
	/**
	 *  Get the assigned reviewer(s) of this paper.
	 * @return a set of reviewers reviewing this paper.
	 */
	public Set<User> getReviewers() {
		return this.reviewerMap.keySet();
	}
	
	/**
	 *  Get the rating assigned to the paper.
	 * @return the rating of the paper.
	 */
	public int getRating() {
		return this.spcRating;
	}
	
	/**
	 * Get the rationale.
	 * 
	 * @return the rationale.
	 */
	public String getRationale() {
		return this.spcRationale;
	}
	
	/**
	 *  Get the acceptance status of the paper.
	 * @return yes if approve, no if not approve or undecided.
	 */
	public Approval getAcceptanceStatus() {
		return this.isAccepted;
	}
	
	/**
	 *  Check if the Reviewer has completed the review.
	 *  
	 * @param reviewer reviewer of the paper.
	 * @return true if the reviewer has reviewed the paper; otherwise, false.
	 */
	public boolean hasReviewerCompletedReview(User reviewer) {
		return this.reviewerMap.get(reviewer) != null;
	}
	
	/**
	 * Get the filepath of the paper.
	 * 
	 * @return the filepath.
	 */
	public String getFilePath() {
		return this.filePath;
	}
	
	/**
	 * Get the topic of the paper.
	 * 
	 * @return the topic of the paper.
	 */
	public String getTopic() {
		return this.topic;
	}
	
	/**
	 * Get the keywords associated the paper.
	 * 
	 * @return the keywords of the paper.
	 */
	public String getKeywords() {
		return this.keywords;
	}
	
	/**
	 * Get the abstract about the paper.
	 * 
	 * @return the abstract about the paper.
	 */
	public String getAbstract() {
		return this.paperAbstract;
	}
}
