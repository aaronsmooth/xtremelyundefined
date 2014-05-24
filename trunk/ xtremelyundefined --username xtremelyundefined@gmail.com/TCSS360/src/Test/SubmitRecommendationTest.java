package Test;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Model.Conference;
import Model.Paper;
import Model.User;
import View.SubmitRecommendation;

public class SubmitRecommendationTest {

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
	public void testWindow(){
		SubmitRecommendation window = new SubmitRecommendation(ppr);
	}
}
