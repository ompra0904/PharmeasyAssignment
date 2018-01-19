package pharmeasy.model;

public class Person {
	private int personid;
	private String firstname;
	private String lastname;
	private String email;
	private String gender;
	private int roleid;
	private String username;
	private String password;
	private String prescriptionhistory;
	
	public Person()
	{
		
	}
	
	public Person(String firstname,String lastname,String email,String gender,int roleid,String username,String password)
	{
		this.firstname=firstname;
		this.lastname=lastname;
		this.email=email;
		this.gender=gender;
		this.roleid=roleid;
		this.username=username;
		this.password=password;
	}
	

	public int getPersonid() {
		return personid;
	}


	public void setPersonid(int personid) {
		this.personid = personid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrescriptionhistory() {
		return prescriptionhistory;
	}

	public void setPrescriptionhistory(String prescriptionhistory) {
		this.prescriptionhistory = prescriptionhistory;
	}




	
}
