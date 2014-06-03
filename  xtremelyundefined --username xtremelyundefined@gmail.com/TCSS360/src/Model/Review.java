package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Review implements Serializable{
	
	/**
	 * 
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

	public List<Integer> getRating() {
		return rating;
	}
	
	public String getSpcComment() {
		return spcComments;
	}

	public List<String> getAuthorComments() {
		return authorComments;
	}
}
