package Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

/**
 * This class contains a list of conference(s) and user(s) and a serialize method
 * to save the state of the conference and the user's activity.
 * 
 * @author Mitchell Alpert
 * @version 5/19/2014
 */
public class ManagementSystem extends Observable implements Serializable, PropertyChangeListener{

	/**
	 * serialVersionID for unique serialization
	 */
	private static final long serialVersionUID = 3328234716102456578L;
	
	/**
	 * The location of the serializable file
	 */
	public static final String FILE = "src/supportingFiles/managementsystem.ser";
	
	/**
	 * List of all users available.
	 */
	private List<User> users;
	
	/**
	 * List of all conferences available.
	 */
	private List<Conference> conferences;
	
	/**
	 * Current user logged in.
	 */
	private User currentUser;
	
	/**
	 * Current conference.
	 */
	private Conference currentConference;
	
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
	 * This method gets a user based on an email address
	 * 
	 * @param email A unique email address
	 * @return The user that the email address belongs to
	 */
	public User getUser(String email) {
		for (User u : users) {
			if (u.getEmail().equalsIgnoreCase(email)) {
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
		Collections.sort(ret);
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
	
	/**
	 * This method gets the current conference of the
	 * current log in session
	 * 
	 * @return The current conference
	 */
	public Conference getConference() {
		return currentConference;
	}
	
	/**
	 * This method gets all conferences in the system
	 * 
	 * @return The list of all the conferences
	 */
	public List<Conference> getConferences() {
		return conferences;
	}

	/**
	 * Listens for a property change and then notifies the GUI 
	 * of the appropriate panels to show
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("role")) {
			setChanged();
			notifyObservers(evt.getNewValue());
		}
		if (evt.getPropertyName().equals("SPC")) {
			if (evt.getNewValue() != null && ((User) evt.getNewValue()).equals(currentUser) ) {
				JOptionPane.showMessageDialog(null, "Sorry, you can not assign yourself to that role.");
			} else {
				((Paper) evt.getOldValue()).setSPC((User) evt.getNewValue());
			}
			setChanged();
			notifyObservers("Program Chair");
		}
		if (evt.getPropertyName().equals("login")) {
			setChanged();
			notifyObservers("login");
		}
		if (evt.getPropertyName().equals("paperSubmission")) {
			setChanged();
			notifyObservers(evt.getNewValue());
		}
		if (evt.getPropertyName().equals("Reviewer")){
			if (evt.getOldValue() != null && evt.getNewValue() != null) {
				if (((User) evt.getNewValue()).equals(currentUser) ) {
					JOptionPane.showMessageDialog(null, "Sorry, you can not assign yourself to that role.");
				} else {
					((Paper) evt.getOldValue()).review((User) evt.getNewValue(), null);
				}
			}
			setChanged();
			notifyObservers("SubProgram Chair");
		}
		if (evt.getPropertyName().equals("Author")) {
			if (evt.getNewValue() != null) {
				currentConference.removePaper(((Paper) evt.getNewValue()).getTitle());
			}
			setChanged();
			notifyObservers("Author");
		}
	
		
	}
	/**
	 *  Set the current user from a specified user.
	 * @param usr to be set as the current user.
	 */
	public void setUser(User usr) {
		currentUser = usr;
	}
	
	/**
	 *  Set the current conference from a specified conference.
	 * @param cnf to be set as the current conference.
	 */
	public void setConference(Conference cnf) {
		currentConference = cnf;
	}

	/**
	 * Get the current user of this conference.
	 * @return the current user of the conference.
	 */
	public User getCurrentUser() {
		return currentUser;
	}
	
	/**
	 * This method serializes this conference and its accompanying components.
	 */
    public void serialize() {
    	try{
    		FileOutputStream  out = new FileOutputStream(FILE);
    		ObjectOutputStream obj = new ObjectOutputStream(out);
    		obj.writeObject(this);
    		obj.close();
    	} catch (IOException e) {
    		System.out.println(e);
    	}
    }
}
