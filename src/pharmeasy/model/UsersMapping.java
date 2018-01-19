package pharmeasy.model;

public class UsersMapping {
	private int personid;
	private String firstname;
	private  String prescriptionhistory;
	private boolean hasrequestedhistory;
	private boolean isapproved;
	
	public UsersMapping()
	{}
	
	public UsersMapping(int personid,String firstname,String prescriptionhistory,boolean hasrequestedhistory,boolean isapproved)
	{
		this.personid=personid;
		this.firstname=firstname;
		this.prescriptionhistory = prescriptionhistory;
		this.hasrequestedhistory = hasrequestedhistory;
		this.isapproved = isapproved;
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

	public String getPrescriptionhistory() {
		return prescriptionhistory;
	}

	public void setPrescriptionhistory(String prescriptionhistory) {
		this.prescriptionhistory = prescriptionhistory;
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
}
