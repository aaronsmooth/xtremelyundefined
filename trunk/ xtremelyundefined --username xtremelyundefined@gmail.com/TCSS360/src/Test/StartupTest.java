package Test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Model.Conference;
import Model.ManagementSystem;

/**
 * Test class for Startup.java. Tests to make sure that the 
 * csv file is being parsed correctly.
 * 
 * @author Mitchell Alpert
 * @version 6/11/2014
 *
 */
public class StartupTest {
	
	/**
	 * System for testing
	 */
	private static ManagementSystem mySystem;
	
	/**
	 * Path for source file
	 */
	 public static final String FILE = "src/supportingFiles/managementsystem.ser";

	 /**
	  * Deserializes the file created by Startup
	  * @throws Exception IOException | ClassNotFoundException file not found or 
	  * 	class does not exist
	  */
	 @BeforeClass
	 public static void setUpBeforeClass() throws Exception {

		 try{
			 FileInputStream  in = new FileInputStream(FILE);
			 ObjectInputStream obj = new ObjectInputStream(in);
			 mySystem = (ManagementSystem) obj.readObject();
			 obj.close();
		 } catch (IOException | ClassNotFoundException e) {
			 System.out.println(e);
		 }
	 }

	 /**
	  * Tests the conferences read in 
	  */
	@Test
	public void ConferenceReadInTest() {
		assertEquals(mySystem.getConferences().get(0).toString(), "LAS");
		assertEquals(mySystem.getConferences().get(1).toString(), "ICMEE");
		assertEquals(mySystem.getConferences().get(2).toString(), "PNWCSE");
		assertEquals(mySystem.getConferences().get(3).toString(), "OOPSLA");
	}
	
	/**
	 * Tests that the reviewers are assigned
	 */
	@Test
	public void ReadReviewersTest() {
		Conference conf = mySystem.getConferences().get(0);
		assertEquals(conf.getReviewers().get(0), mySystem.getUser(89));
		assertEquals(conf.getReviewers().get(1), mySystem.getUser(17));
	}
	
	/**
	 * Tests that the SPCs are assigned
	 */
	@Test
	public void ReadSPCsTest() {
		Conference conf = mySystem.getConferences().get(1);
		assertEquals(conf.getSPCs().get(0), mySystem.getUser(112));
		assertEquals(conf.getSPCs().get(1), mySystem.getUser(34));
		
	}
	
	/**
	 * Tests that the PCs are assigned
	 */
	@Test
	public void ReadPCsTest() {
		Conference conf = mySystem.getConferences().get(2);
		assertEquals(conf.getPC(), mySystem.getUser(62));
		
		conf = mySystem.getConferences().get(3);
		assertEquals(conf.getPC(), mySystem.getUser(152));
		
	}
	
	/**
	 * Tests that the users without roles are read in
	 */
	@Test
	public void UserReadTest() {
		assertEquals(mySystem.getUser(63), mySystem.getUser("Randy_Deritis@kla-tencor.com"));
		assertEquals(mySystem.getUser(249), mySystem.getUser("SebastianZizza@microsoft.com"));
	}

}
