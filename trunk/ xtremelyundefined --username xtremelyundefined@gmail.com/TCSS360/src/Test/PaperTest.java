package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Model.Approval;
import Model.Conference;
import Model.Paper;
import Model.Review;
import Model.User;

/**
 * This class tests method functionality of the Paper.java class.
 * 
 * @author Aaron Nelson
 * @version 6/1/2014
 *
 */

public class PaperTest {
	/**
	 * Users to be used for testing.
	 */
	User usr, usr2, usr3;
	
	/**
	 * Papers to be used for testing.
	 */
	Paper ppr, ppr2, ppr3;
	
	/**
	 * Conference.
	 */
	Conference conf;
	
	/**
	 * Exception.
	 */
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	/**
	 * Initialized all the neccessary fields to be used for testing.
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
	}
	
	/**
	 * Test the getAcceptanceStatus() funtionality.
	 */
	@Test
	public void acceptanceStatusTest() {
		assertSame(ppr.getAcceptanceStatus(), Approval.UNDECIDED);
		ppr.setAccepted(Approval.YES);
		assertSame(ppr.getAcceptanceStatus(), Approval.YES);
		assertNotSame(ppr.getAcceptanceStatus(), Approval.UNDECIDED);
	}
	
	/**
	 * Test the getAuthor() functionality.
	 */
	@Test
	public void authorTest() {
		assertSame(ppr2.getAuthor(), usr);
		assertNotSame(ppr.getAuthor(), usr2);
	}
	
	/**
	 * Test the getDecision() functionality.
	 */
	@Test
	public void decisionTest() {
		assertSame(ppr.getAcceptanceStatus(), ppr.getDecision());
		ppr.setAccepted(Approval.NO);
		assertSame(ppr.getAcceptanceStatus(), ppr.getDecision());
	}
	
	/**
	 * Test the getRating() functionality.
	 */
	@Test
	public void ratingTest() {
		assertNotNull(ppr.getRating());
		assertSame(ppr.getRating(), 0);
		ppr.setRating(10);
		assertSame(ppr.getRating(), 10);
	}
	
	/**
	 * Test the getRationale() functionality.
	 */
	@Test
	public void rationaleTest() {
		assertNull(ppr.getRationale());
		ppr.setRationale("GOOD!");
		assertSame(ppr.getRationale(),  "GOOD!");
	}
	
	/**
	 * Test the getReviewers(), isAreviwer() and hasReviewerCompletedReview(0 functionality.
	 */
	@Test
	public void reviewerTest() {
		assertSame(ppr.getReviewers().size(), 0);
		assertFalse(ppr.isAReviewer(usr2));
		conf.addReviewer(usr2);
		ppr.review(usr2,  null);
		
		exception.expect(IllegalArgumentException.class);
		ppr.review(null,  null);
		
		assertTrue(ppr.getReviewers().contains(usr2));
		assertFalse(ppr.hasReviewerCompletedReview(usr2));
		assertTrue(ppr.isAReviewer(usr2));
		assertTrue(ppr.getReviews().isEmpty());
		List<Integer> myList = new ArrayList<Integer>();
		myList.add(1);
		myList.add(2);
		List<String> myAuth = new ArrayList<String>();
		myAuth.add(usr.getName());
		myAuth.add(usr2.getName());
		ppr.review(usr2,  new Review(myList, "good", myAuth));
		assertTrue(ppr.hasReviewerCompletedReview(usr2));
	}
	
	/**
	 * Test the getSPC() functionality.
	 */
	@Test
	public void spcTest() {
		assertNull(ppr.getSPC());
		ppr.setSPC(usr3);
		assertSame(ppr.getSPC(), usr3);
		
		exception.expect(IllegalArgumentException.class);
		ppr.setSPC(null);
	}
	
	/**
	 * Test the getTitle(0 functionality.
	 */
	@Test
	public void titleTest() {
		assertSame(ppr.getTitle(), "thisisthetitle");
		assertNotSame(ppr.getTitle(), "hi");
		assertNotNull(ppr.getTitle());
	}
}
