package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ManagementSystem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3328234716102456578L;
	public List<User> users;
	private List<Conference> conferences;
	
	public ManagementSystem(){
		users = new ArrayList<User>();
		conferences = new ArrayList<Conference>();
	}
	
	public void addUser(User user) {
		users.add(user);
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
	public boolean hasConference(String title) {
		
		for (Conference c : conferences) {
			if (c.getName() == title) return true;
		}		
		
		return false;
	}
}
