package Test;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Model.Conference;
import Model.Paper;
import Model.User;
import View.SubmitRecommendation;

/**
 * This class tests method functionality of the SubmitRecommendation.java class.
 * 
 * @author Aaron Nelson
 * @version 6/1/2014
 *
 */
public class SubmitRecommendationTest {

	/**
	 * Conference
	 */
	private Conference conf;
	
	/**
	 * Paper to be used for testing.
	 */
	private Paper ppr;
	
	/**
	 * paper #2 to be used for testing.
	 */
	private Paper ppr2;
	
	/**
	 * User to be used for testing.
	 */
	private User usr;
	
	/**
	 * User #2 to be used for testing.
	 */
	private User usr2;
	
	/**
	 * Exception.
	 */
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	/**
	 * Initialized all the necesary fields to be used for testing.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		usr = new User(55, "joe", "schmoe", "schmoe@gmail.com");
		usr2 = new User(56, "jon", "doe", "doe@gmail.com");
		ppr = new Paper(usr, "thisisthetitle", "thekeywords", "theabstact", "filepath", "topic");
		ppr2 = new Paper(usr, "alsoatitle", "alsokeywords", "alsoanabstact", "alsoafilepath", "topic");
		conf = new Conference("alocation", "adate", "adeadline", null, "aname");	
	}
	
	/**
	 * Test the constructor functionality of the SubmitRecommendation.
	 */
	@Test
	public void testWindow(){
		SubmitRecommendation window = new SubmitRecommendation(ppr);
	}
}
