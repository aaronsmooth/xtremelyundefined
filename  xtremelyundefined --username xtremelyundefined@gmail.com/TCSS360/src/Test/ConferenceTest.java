package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Model.Conference;
import Model.Paper;
import Model.User;

public class ConferenceTest  {

	private Conference conf;
	private Paper ppr;
	private Paper ppr2;
	private User usr;
	private User usr2;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		usr = new User(55, "joe", "schmoe", "schmoe@gmail.com");
		usr2 = new User(56, "jon", "doe", "doe@gmail.com");
		ppr = new Paper(usr, "thisisthetitle", "thekeywords", "theabstact", "filepath");
		ppr2 = new Paper(usr, "alsoatitle", "alsokeywords", "alsoanabstact", "alsoafilepath");
		conf = new Conference("alocation", "adate", "adeadline", null, "aname");	
	}

	@Test
	public void submitPaperTest() {
		assertTrue(conf.submitPaper(ppr));
		assertTrue(conf.submitPaper(ppr2));
		
		assertSame(ppr, conf.getPaper("thisisthetitle"));
		assertSame(ppr2, conf.getPaper("alsoatitle"));
		
		assertFalse(conf.submitPaper(ppr));
		
		exception.expect(IllegalArgumentException.class);
		conf.submitPaper(null);
		
	}
	
	public void addReviewerTest() {
		conf.addReviewer(usr);
		conf.addReviewer(usr2);
		
		assertSame(conf.getReviewers().get(0), usr);
		assertSame(conf.getReviewers().get(1), usr2);
		
		assertTrue(conf.getReviewers().size() == 2);
		
		conf.addReviewer(usr);
		assertTrue(conf.getReviewers().size() == 2);	
	}
	
	
	
	

}
