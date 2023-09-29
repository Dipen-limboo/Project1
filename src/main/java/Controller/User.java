package Controller;

public class User {
	private int id;
	private String fname, lname, email, psw;
	private String role;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	//get and set of id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	//get and set of first name
	public String getFname() {
		return fname;
	}
	
	public void setFname(String first_name) {
		this.fname = first_name;
	}
	
	//get and set of last name
	public String getLname() {
		return lname;
	}
	
	public void setLname(String last_name) {
		this.lname = last_name;
	}
	
	//get and set of email
	public String getEmail() {
		return email;
	}
	public void setEmail(String e) {
		this.email = e;
	}
	
	//get and set of password
	public String getPassword() {
		return psw;
	}
	public void setPassword(String pass) {
		this.psw = pass;
	}
}