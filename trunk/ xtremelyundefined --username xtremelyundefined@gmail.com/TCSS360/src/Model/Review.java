package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represent the review (and its components) associated to a paper.
 * 
 * @author Mitchell Alpert
 * @version 5/19/2014
 *
 */
public class Review implements Serializable{
	
	/**
	 * Serial version identification
	 */
	private static final long serialVersionUID = 7055683803564457286L;

	/**
	 * Array of integers representing the scores given 
	 * by the reviewer
	 */
	private List<Integer> rating;
	
	/**
	 * Summary of the review
	 */
	private String summary;
	
	/**
	 * Comments for the spc only
	 */
	private String spcComments;
	
	/**
	 * Comments for the author
	 */
	private List<String> authorComments;

	/**
	 * Public constructor that initializes fields
	 * 
	 * @param rating List  of ratings
	 * @param summary text of reviewers summary
	 */
	public Review(List<Integer> rating, String spc, List<String> author) {
		this.rating = new ArrayList<Integer>(rating);
		this.authorComments = new ArrayList<String>(author);	
		spcComments = spc;

	}

	/**
	 * Get the rating of the review.
	 * 
	 * @return the rating of the review.
	 */
	public List<Integer> getRating() {
		return rating;
	}
	
	/**
	 * Get the Sub-Program Chair (SPC) comment(s).
	 * 
	 * @return the comment(s) made by the SPC.
	 */
	public String getSpcComment() {
		return spcComments;
	}

	/**
	 * Get the comment(s) made by the author.
	 * 
	 * @return the comment(s) made by the author.
	 */
	public List<String> getAuthorComments() {
		return authorComments;
	}
}
