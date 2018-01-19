package pharmeasy.model;

public class PersonDTO {
	private int personid;
	private String firstname;
	private boolean hasadded;
	
	public PersonDTO()
	{
		
	}
	
	public PersonDTO(int personid,String firstname,int roleid,boolean hasadded)
	{
		this.firstname=firstname;		
		this.personid=personid;
		this.setHasadded(hasadded);
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


	public boolean getHasadded() {
		return hasadded;
	}

	public void setHasadded(boolean hasadded) {
		this.hasadded = hasadded;
	}

	
	
}
