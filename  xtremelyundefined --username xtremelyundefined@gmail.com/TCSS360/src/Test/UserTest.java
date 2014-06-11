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
 * @author Aaron Nelson
 * @version 6/1/2014
 *
 */

public class UserTest {
	/**
	 * Users to be used for testing.
	 */
	User usr, usr2, usr3, usr4, usr5;
     
	/**
	 * Exception.
	 */
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	/**
	 * Initialize all the necessary fields to be used for testing.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		usr = new User(55, "joe", "schmoe", "schmoe@gmail.com");
		usr2 = new User(56, "jon", "doe", "doe@gmail.com");
		usr3 = new User(77, "be", "the", "best@d.com");
		usr4 = new User(89, "be", "the", "o@d.com");
		usr5 = new User(90, "jo", "doe", "o@d.com");
	}
	
	/**
	 * Test comparison functionality of the User object.
	 */
	@Test
	public void compareToTest() {
		assertTrue(usr.compareTo(usr2) < 0);
		assertTrue(usr2.compareTo(usr) > 0);
		assertTrue(usr3.compareTo(usr) < 0);
		assertTrue(usr3.compareTo(usr4) == 0);
		assertTrue(usr5.compareTo(usr2) > 0);
	}
	
	/**
	 * Test the equality of the User object.
	 */
	@Test
	public void equalsTest() {
		assertTrue(usr4.equals(usr4));
		assertFalse(usr4.equals(usr3));
		assertTrue(usr4.equals(usr5));
	}
	
	/**
	 * Test the getEmail() functionality of the User object.
	 */
	@Test
	public void getEmailTest() {
		assertSame(usr.getEmail(), "schmoe@gmail.com");
		assertNotSame(usr.getEmail(), usr2.getEmail());
	}
	
	/**
	 * Test the getFirstName() functionality of the User object.
	 */
	@Test
	public void getFirstNameTest() {
		assertSame(usr.getFirstName(), "joe");
		assertNotSame(usr.getFirstName(), usr2.getFirstName());
	}
	
	/**
	 * Test the getID() functionality of the User object.
	 */
	@Test
	public void getIDTest() {
		assertSame(usr.getID(), 55);
		assertNotSame(usr.getID(), usr2.getID());
	}
	
	/**
	 * Test the getLastName() functionality of the User object.
	 */
	@Test
	public void getLastNameTest() {
		assertSame(usr4.getLastName(), usr3.getLastName());
		assertNotSame(usr3.getLastName(), usr.getLastName());
	}
	
	/**
	 * Test the getName() functionality of the User object.
	 */
	@Test
	public void getNameTest() {
		String name = "schmoe, joe";
		assertTrue(usr.getName().equals(name));
		assertFalse(usr.getName() == name);
	}
	
	/**
	 * Test toString() functionality.
	 */
	@Test
	public void toStringTest() {
		assertTrue(usr.toString().equals(usr.getName()));
		assertFalse(usr.toString() == usr.getName());
	}
}
