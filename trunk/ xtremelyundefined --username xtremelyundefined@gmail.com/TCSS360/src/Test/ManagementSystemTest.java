package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Model.Conference;
import Model.ManagementSystem;
import Model.Paper;
import Model.User;

/**
 * This class tests method functionality of the ManagementSystem.java class.
 * 
 * @author Aaron Nelson
 * @version 6/1/2014
 *
 */

public class ManagementSystemTest {
	/**
	 * Users to be used for testing.
	 */
	User usr, usr2, usr3;
	
	/**
	 * Papers to be used for testing.
	 */
	Paper ppr, ppr2;
	
	/**
	 *  Conference to be used for testing.
	 */
	Conference conf;
	
	/**
	 * Management System
	 */
	ManagementSystem system;

	/**
	 * Exception.
	 */
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	/**
	 * Initialized all the necessary fields to be used for testing.
	 * 
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
		system = new ManagementSystem();
	}
	
	/**
	 * Test the getConferences() and hasConference() functionality.
	 */
	@Test
	public void ConferenceTest() {
		system.addConference(conf);
		system.setConference(conf);
		
		assertSame(system.getConferences().get(0), conf);
		assertSame(system.getConference("aname"), conf);
		
		assertSame(system.getConference(), conf);
		assertTrue(system.hasConference("aname"));
		
	}
	
	/**
	 * Test the getUser() functionality.
	 */
	@Test
	public void UserTest() {
		system.addUser(usr);
		system.addUser(usr2);
		system.addUser(usr3);
		system.setUser(usr);
		
		assertSame(system.getCurrentUser(), usr);
		system.setUser(usr2);
		assertSame(system.getCurrentUser(), usr2);
		
		assertSame(system.getUser(77), usr3);
		assertSame(system.getUser("doe@gmail.com"), usr2);
		
		assertSame(system.getUsers().get(0).getFirstName(), usr3.getFirstName());
		assertSame(system.getUsers().get(1).getEmail(), usr.getEmail());
		assertSame(system.getUsers().get(2).getID(), usr2.getID());
	}
	
}
