package helpdesk.ticket;

/**
 * User.java
 * This is a model class represents a User entity
 * @author Ramesh Fadatare
 *
 */
public class user {
	private int ID;
	private String Name;
	private String Email;
	private String address;
	
	
	public user(int ID, String Name, String Email, String address) {
		super();
		this.ID = ID;
		this.Name = Name;
		this.Email = Email;
		this.address = address;
	}
	
	public user(String Name, String Email, String address) {
		super();
		this.Name = Name;
		this.Email = Email;
		this.address = address;
		
	}

	public int getID() {
		return ID;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String Name) {
		this.Name = Name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String Email) {
		this.Email = Email;
	}
	public String getaddress() {
		return address;
	}
	public void setaddress(String address) {
		this.address = address;
	}
	
	
}

