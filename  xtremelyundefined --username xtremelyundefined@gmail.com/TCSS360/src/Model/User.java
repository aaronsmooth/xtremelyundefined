package Model;

public class User implements Comparable<User>{
	
	/**
	 * Unique ID number given to user.
	 */
	private int ID;
	
	/**
	 * Users first name.
	 */
	private String firstName;
	
	/**
	 * Users last name.
	 */
	private String lastName;
	
	/**
	 * Users email address. This is 
	 * considered unique and can be used
	 * as an identifier
	 */
	private String email;
	
	
	/**
	 * Constructor that creates a User. Initializes all fields and
	 * all fields are necessary at time of instantiation.
	 * 
	 * @param id 		int ID number given to this user
	 * @param first		String users first name
	 * @param last		String users last name
	 * @param email		String users email. 
	 */
	public User(int id, String first, String last, String email) {
		if (first != null && last != null && email != null) {
			firstName = first;
			lastName = last;
			this.email = email;
		}
		ID = id;	
	}
	
	/**
	 * Returns this users formatted name.
	 * @return String full name
	 */
	public String getName() {
		return lastName + ", " + firstName;
	}
	
	/**
	 * Returns this users email address.
	 * @return String email address
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Returns this users ID number.
	 * @return int ID number
	 */
	public int getID() {
		return ID;
	}
	
	@Override
	/**
	 * <@inheritDoc>
	 */
	public String toString(){
		return getID() + ":" + getName() + ", " + getEmail();
		
	}
	
	@Override
	/**
	 * <@inheritDoc>
	 * 
	 * Two users are considered equal if they have the same
	 * email addresses.
	 */
	public boolean equals(Object other) {
		
		if (other == this) {
			return true;
		}
		if (other instanceof User) {
			User otherUser = (User) other;
			if (otherUser.email.equals(this.email)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Compares Users by their last name and then by first name
	 * 
	 * @param other other User to compareTo
	 * @return int comparison
	 */
	public int compareTo(User other) {
		if (other.lastName != this.lastName) {
			return other.lastName.compareTo(this.lastName);
		} else {
			return other.firstName.compareTo(this.firstName);
		}
	}
	
}
