package Test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Model.Conference;
import Model.Paper;
import Model.User;

/**
 * This class tests method functionality of the Conference.java class.
 * 
 * @author TeamUndefined
 * @version 6/1/2014
 *
 */

public class ConferenceTest  {

	private Conference conf;
	private Paper ppr;
	private Paper ppr2;
	private User usr;
	private User usr2;
	private User usr3;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		usr = new User(55, "joe", "schmoe", "schmoe@gmail.com");
		usr2 = new User(56, "jon", "doe", "doe@gmail.com");
		usr3 = new User(77, "be", "the", "best@d.com");
		ppr = new Paper(usr, "thisisthetitle", "thekeywords", "theabstact", "filepath", "topic");
		ppr2 = new Paper(usr, "alsoatitle", "alsokeywords", "alsoanabstact", "alsoafilepath", "topic");
		conf = new Conference("alocation", "adate", "adeadline", null, "aname");	
	}

	@Test
	public void addReviewerTest() {
		conf.addReviewer(usr);
		conf.addReviewer(usr2);
		
		assertSame(conf.getReviewers().get(0), usr);
		assertSame(conf.getReviewers().get(1), usr2);
		
		assertTrue(conf.getReviewers().size() == 2);
		
		conf.addReviewer(usr);
		assertTrue(conf.getReviewers().size() == 2);
		
		exception.expect(IllegalArgumentException.class);
		conf.addReviewer(null);
	}
	
	@Test
	public void addSPCTest() {
		conf.addReviewer(usr);
		conf.addSPC(usr);
		conf.addSPC(usr2);
		
		assertSame(conf.getSPCs().get(0).getID(), usr.getID());
		assertTrue(conf.getSPCs().size() == 1);
		//assertSame(conf.getSPCs().get(1).getID(), usr2.getID());
		
		conf.addSPC(usr);
		assertTrue(conf.getSPCs().size() == 1);
		
		exception.expect(IllegalArgumentException.class);
		conf.addSPC(null);
	}
	
	@Test
	public void PaperTest() {
		assertTrue(conf.submitPaper(ppr));
		assertTrue(conf.submitPaper(ppr2));
		
		assertSame(conf.getAllPapers().size(), 2);
		assertSame(conf.getAllPapers().get(0), ppr);
		assertSame(conf.getAllPapers().get(1), ppr2);
		
		assertSame(ppr, conf.getPaper("thisisthetitle"));
		assertSame(ppr2, conf.getPaper("alsoatitle"));
		
		assertFalse(conf.submitPaper(ppr));
		
		exception.expect(IllegalArgumentException.class);
		conf.submitPaper(null);
		
	}
	
	@Test
	public void getAuthoredTest() {
		assertTrue(conf.submitPaper(ppr));
		assertTrue(conf.submitPaper(ppr2));
		
		assertSame(conf.getAuthored(usr).get(0), ppr);
		assertSame(conf.getAuthored(usr).get(1), ppr2);
	}
	
	@Test
	public void getNameTest() {
		assertTrue(conf.getName() == "aname");
	}
	
	@Test
	public void getPapersBySPCTest() {
		conf.addReviewer(usr2);
		conf.addSPC(usr2);
		conf.submitPaper(ppr);
		ppr.setSPC(usr2);
		assertSame(conf.getPapersBySPC(usr2).get(0), ppr);
	}
	
	@Test
	public void getPapersToReview() {
		conf.addReviewer(usr2);
		conf.submitPaper(ppr);
		ppr.review(usr2, null);
		assertSame(conf.getPapersToReview(usr2).get(0), ppr);
		
	}
	
	@Test
	public void getPCTest() {
		conf.setPC(usr3);
		assertSame(conf.getPC(), usr3);
	}
	
	@Test
	public void getReviewersTest() {
		conf.addReviewer(usr2);
		assertSame(conf.getReviewers().get(0), usr2);
	}
	
	@Test
	public void getRolesTest() {
		List<String> myList = new ArrayList<String>();
		myList.add("Reviewer");
		myList.add("SubProgram Chair");
		myList.add("Program Chair");

		conf.addReviewer(usr);
		conf.addSPC(usr);
		conf.setPC(usr);
		
		assertSame(conf.getRoles(usr).get(0), myList.get(0));
		assertSame(conf.getRoles(usr).get(1), myList.get(1));
		assertSame(conf.getRoles(usr).get(2), myList.get(2));
	}
	
	@Test
	public void getSPCTest() {
		conf.addReviewer(usr);
		conf.addSPC(usr);
		assertSame(conf.getSPCs().get(0), usr);
	}
	
	@Test
	public void hasUserTest() {
		conf.setPC(usr);
		
		assertTrue(conf.hasUser(usr));
		assertFalse(conf.hasUser(usr2));
	}
	
	@Test
	public void removePaperTest() {
		conf.submitPaper(ppr);
		
		assertFalse(conf.removePaper(ppr2.getTitle()));
		assertTrue(conf.removePaper(ppr.getTitle()));
		
		exception.expect(IllegalArgumentException.class);
		conf.removePaper(null);
	}
	
	@Test
	public void setPCTest() {
		exception.expect(IllegalArgumentException.class);
		conf.setPC(null);
	}

	@Test
	public void toStringTest() {
		assertSame(conf.getName(), "aname");
	}



}
