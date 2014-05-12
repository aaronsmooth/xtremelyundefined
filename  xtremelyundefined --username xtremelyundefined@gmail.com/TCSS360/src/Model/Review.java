package Model;

public class Review {
	
	/**
	 * Array of integers representing the scores given 
	 * by the reviewer
	 */
	private int[] rating = new int[10];
	
	/**
	 * Summary of the review
	 */
	private String summary;
		
	/**
	 * Public constructor that initializes fields
	 * 
	 * @param rating int[] of ratings
	 * @param summary text of reviewers summary
	 */
	public Review(int[] rating, String summary) {
		
		//defensive copy of parameter
		for (int i : rating) { 
			this.rating[i] = rating[i];
		}
		if (summary != null) {
			this.summary = summary;
		}
	}
}
