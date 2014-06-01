package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Model.User;

/**
 * This class tests method functionality of the User.java class.
 * 
 * @author TeamUndefined
 * @version 6/1/2014
 *
 */

public class UserTest {
	User usr, usr2, usr3, usr4, usr5;

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		usr = new User(55, "joe", "schmoe", "schmoe@gmail.com");
		usr2 = new User(56, "jon", "doe", "doe@gmail.com");
		usr3 = new User(77, "be", "the", "best@d.com");
		usr4 = new User(89, "be", "the", "o@d.com");
		usr5 = new User(90, "jo", "doe", "o@d.com");
	}
	
	@Test
	public void compareToTest() {
		assertTrue(usr.compareTo(usr2) < 0);
		assertTrue(usr2.compareTo(usr) > 0);
		assertTrue(usr3.compareTo(usr) < 0);
		assertTrue(usr3.compareTo(usr4) == 0);
		assertTrue(usr5.compareTo(usr2) > 0);
	}
	
	@Test
	public void equalsTest() {
		assertTrue(usr4.equals(usr4));
		assertFalse(usr4.equals(usr3));
		assertTrue(usr4.equals(usr5));
	}
	
	@Test
	public void getEmailTest() {
		assertSame(usr.getEmail(), "schmoe@gmail.com");
		assertNotSame(usr.getEmail(), usr2.getEmail());
	}
	
	@Test
	public void getFirstNameTest() {
		assertSame(usr.getFirstName(), "joe");
		assertNotSame(usr.getFirstName(), usr2.getFirstName());
	}
	
	@Test
	public void getIDTest() {
		assertSame(usr.getID(), 55);
		assertNotSame(usr.getID(), usr2.getID());
	}
	
	@Test
	public void getLastNameTest() {
		assertSame(usr4.getLastName(), usr3.getLastName());
		assertNotSame(usr3.getLastName(), usr.getLastName());
	}
	
	@Test
	public void getNameTest() {
		String name = "schmoe, joe";
		assertTrue(usr.getName().equals(name));
		assertFalse(usr.getName() == name);
	}
	
	@Test
	public void toStringTest() {
		assertTrue(usr.toString().equals(usr.getName()));
		assertFalse(usr.toString() == usr.getName());
	}
}
