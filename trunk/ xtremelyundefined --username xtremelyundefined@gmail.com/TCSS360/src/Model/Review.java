package Model;

import java.util.ArrayList;
import java.util.List;

public class Review {
	
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
	 * @param rating int[] of ratings
	 * @param summary text of reviewers summary
	 */
	public Review(List<Integer> rating, String spc, List<String> author) {
		rating = new ArrayList<Integer>();
		author = new ArrayList<String>();
		//defensive copy of parameter
		for (Integer i : rating) { 
			this.rating.add(i);
		}
		spcComments = spc;
		for (String str : author) {
			authorComments.add(str);
		}
		
	}
}
