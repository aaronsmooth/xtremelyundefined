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

public class ReviewTest {

	User usr, usr2, usr3;
	Paper ppr, ppr2;
	Conference conf;
	List<Integer> ratings;
	List<String> author;
	String spc;
	Review rev;
	
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
	
	@Test
	public void ConstructorTest() {
		rev = new Review(ratings,spc, author);
	}
	
	@Test
	public void getRatingTest() {
		assertEquals(ratings, rev.getRating());
		assertNotNull(rev.getRating());
	}
	
	@Test
	public void getSPCCommentTest() {
		assertEquals(spc, rev.getSpcComment());
		assertNotNull(rev.getSpcComment());
	}
	
	@Test
	public void getAuthorCommentTest() {
		assertEquals(author, rev.getAuthorComments());
		assertNotNull(rev.getAuthorComments());
	}
}
