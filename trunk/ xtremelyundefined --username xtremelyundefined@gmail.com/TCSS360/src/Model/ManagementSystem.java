package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ManagementSystem implements Serializable{

	/**
	 * serialVersionID for unique serialization
	 */
	private static final long serialVersionUID = 3328234716102456578L;
	
	/**
	 * List of all users available.
	 */
	private List<User> users;
	
	/**
	 * List of all conferences available.
	 */
	private List<Conference> conferences;
	
	/**
	 * Public constructor that initializes fields.
	 */
	public ManagementSystem(){
		users = new ArrayList<User>();
		conferences = new ArrayList<Conference>();
	}
	
	/**
	 * Adds a user to the system.
	 * @param user the user to add
	 */
	public void addUser(User user) {
		users.add(user);
	}
	
	/**
	 * Returns a user that is in the system based 
	 * on their ID.
	 * 
	 * @param id the ID to look for
	 * @return User the user with the ID
	 */
	public User getUser(int id) {
		for (User u : users) {
			if (u.getID() == id) {
				return u;
			}
		}
		return null;
	}
	
	/**
	 * Determines if a given ID belongs to a user in the system
	 * 
	 * @param id ID to check
	 * @return boolean true if user is in the list. false if not.
	 */
	public boolean hasUser(int id) {
		return users.contains(getUser(id));
	}
	
	/**
	 * Returns a deep copy of the users list.
	 * 
	 * @return List<User> copied list of users
	 */
	public List<User> getUsers(){
		List<User> ret = new ArrayList<User>();
		
		//copy list to return
		for (User u : users) {
			ret.add(new User(u.getID(), u.getFirstName(), u.getLastName(), u.getEmail()));
		}
		
		return ret;
	}
	
	/**
	 * Adds a conference to the system.
	 * 
	 * @param conf the conference to add.
	 */
	public void addConference(Conference conf) {
		conferences.add(conf);
	}
	
	/**
	 * Returns a conference based on title.
	 * 
	 * @param title the title to search for.
	 * 
	 * @return Conference the conference with that title.
	 */
	public Conference getConference(String title) {
		
		for (Conference c : conferences ) {
			if (c.getName().equals(title)) return c;
		}
		
		return null;
	}
	
	/**
	 * Checks if a conference already exists in the list 
	 * based on title.
	 * 
	 * @param title the title to search for
	 * @return boolean true if the conference is in the list
	 */
	public boolean hasConference(String title) {
		
		for (Conference c : conferences) {
			if (c.getName().equals(title)) return true;
		}		
		
		return false;
	}
}
