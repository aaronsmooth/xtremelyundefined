package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ManagementSystem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3328234716102456578L;
	private List<User> users;
	public List<Conference> conferences;
	
	public ManagementSystem(){
		users = new ArrayList<User>();
		conferences = new ArrayList<Conference>();
	}
	
	public void addUser(User user) {
		users.add(user);
	}
	
	public List<User> getUsers(){
		List<User> ret = new ArrayList<User>();
		
		//copy list to return
		for (User u : users) {
			ret.add(new User(u.getID(), u.getFirstName(), u.getLastName(), u.getEmail()));
		}
		
		return ret;
	}
	
	public void addConference(Conference conf) {
		conferences.add(conf);
	}
	
	public Conference getConference(String title) {
		
		for (Conference c : conferences ) {
			if (c.getName() == title) return c;
		}
		
		return null;
	}
	
	public List<Conference> getConferences() {
		List<Conference> ret = new ArrayList<Conference>();
		
		//copy list to return
		for (Conference c : conferences) {
			ret.add(c.clone());
		}
		
		return ret;
		
	}
	public boolean hasConference(String title) {
		
		for (Conference c : conferences) {
			if (c.getName() == title) return true;
		}		
		
		return false;
	}
}
