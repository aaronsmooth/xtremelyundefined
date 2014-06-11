package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Model.Conference;
import Model.Paper;
import Model.Review;
import Model.User;

/**
 * This class tests method functionality of the Review.java class.
 * 
 * @author Aaron Nelson
 * @version 6/1/2014
 *
 */
public class ReviewTest {

	/**
	 * Users.
	 */
	User usr, usr2, usr3;
	
	/**
	 * Papers.
	 */
	Paper ppr, ppr2;
	
	/**
	 * Conference.
	 */
	Conference conf;
	
	/**
	 * List of ratings.
	 */
	List<Integer> ratings;
	
	/**
	 * List of authors.
	 */
	List<String> author;
	
	/**
	 * Sub-Program Chair.
	 */
	String spc;
	
	/**
	 * Reviewer.
	 */
	Review rev;
	
	/**
	 * Exception.
	 */
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	/**
	 * Initialized all the neccesary fields to be used for testing.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		usr = new User(55, "joe", "schmoe", "schmoe@gmail.com");
		usr2 = new User(56, "jon", "doe", "doe@gmail.com");
		usr3 = new User(77, "be", "the", "best@d.com");
		ppr = new Paper(usr, "thisisthetitle", "thekeywords", "theabstact", "filepath", "topic");
		ppr2 = new Paper(usr, "alsoatitle", "alsokeywords", "alsoanabstact", "alsoafilepath", "topic");
		conf = new Conference("alocation", "adate", "adeadline", null, "aname");
		conf.submitPaper(ppr);
		conf.submitPaper(ppr2);
		ratings = new ArrayList<Integer>();
		ratings.add(3);
		ratings.add(3);
		ratings.add(4);
		ratings.add(5);
		ratings.add(6);
		ratings.add(7);
		ratings.add(8);
		ratings.add(9);
		ratings.add(3);
		 spc = "spc comments only";
		author = new ArrayList<String>();
		author.add("a commentg");
		author.add("a commenth");
		author.add("a comment");
		author.add("a commentj");
		author.add("a commente");
		author.add("a commenth");
		author.add("a commentr");
		author.add("a commentr");
		author.add("a commentw");
		rev = new Review(ratings,spc, author);
	}
	
	/**
	 * Test the constructor of the Review object.
	 */
	@Test
	public void ConstructorTest() {
		rev = new Review(ratings,spc, author);
	}
	
	/**
	 * Test the getRating() functionality.
	 */
	@Test
	public void getRatingTest() {
		assertEquals(ratings, rev.getRating());
		assertNotNull(rev.getRating());
	}
	
	/**
	 * Test the getSPCComment() functionality.
	 */
	@Test
	public void getSPCCommentTest() {
		assertEquals(spc, rev.getSpcComment());
		assertNotNull(rev.getSpcComment());
	}
	
	/**
	 * Test the getAuthorComment() functionality.
	 */
	@Test
	public void getAuthorCommentTest() {
		assertEquals(author, rev.getAuthorComments());
		assertNotNull(rev.getAuthorComments());
	}
}
