package pharmeasy.model;

public class PersonApproveCTO {

	private int personid;
	private String firstname;
	private String rolename;
	private boolean hasrequestedhistory;
	private boolean isapproved;
	
	public PersonApproveCTO(){
		
	}

	public int getPersonid() {
		return personid;
	}

	public void setPersonid(int personid) {
		this.personid = personid;
	}
	
	
	

	public boolean isHasrequestedhistory() {
		return hasrequestedhistory;
	}

	public void setHasrequestedhistory(boolean hasrequestedhistory) {
		this.hasrequestedhistory = hasrequestedhistory;
	}

	public boolean isIsapproved() {
		return isapproved;
	}

	public void setIsapproved(boolean isapproved) {
		this.isapproved = isapproved;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
}
