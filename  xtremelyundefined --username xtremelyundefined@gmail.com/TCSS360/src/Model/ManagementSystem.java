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
	private List<Conference> conferences;
	
	public ManagementSystem(){
		users = new ArrayList<User>();
		conferences = new ArrayList<Conference>();
	}
}
