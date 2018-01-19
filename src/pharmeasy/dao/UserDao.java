package pharmeasy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pharmeasy.model.Person;
import pharmeasy.model.PersonApproveCTO;
import pharmeasy.model.PersonDTO;
import pharmeasy.model.UsersMapping;
import pharmeasy.util.DbUtil;

public class UserDao {

	private Connection connection;

	public UserDao() {
		connection = DbUtil.getConnection();
	}

	public void addUser(Person person) {
		try {
			
			PreparedStatement pstm = connection
					.prepareStatement("insert into Person(firstname,lastname,email,gender,roleid,username,password)values (?,?,?,?,?,?,?)");
			// Parameters start with 1
			pstm.setString(1, person.getFirstname());
	        pstm.setString(2, person.getLastname());
	        pstm.setString(3, person.getEmail());
	        pstm.setString(4, person.getGender());
	        pstm.setInt(5, person.getRoleid());
	        pstm.setString(6, person.getUsername());
	        pstm.setString(7, person.getPassword());
	        pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Person loginUser(String username , String password) {	
		
	try {
		Person user = new Person();
		PreparedStatement pstm = connection
				.prepareStatement("select personid,firstname,roleid from Person where username= ? and [password] = ?");
	 
	    
	    pstm.setString(1, username);
	    pstm.setString(2, password);
	    ResultSet rs = pstm.executeQuery();

	    if (rs.next()) {
        int roleid = rs.getInt("roleid");        
        user.setFirstname(rs.getString("firstname"));
        user.setPersonid(rs.getInt("personid"));
        user.setRoleid(roleid);
        
          return user;
	    }
	      return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//for getting list of doctors or pharmacists to be shown on patients dashboard
//	public List<Person> getAllUsers(int roleid) {
//	List<Person> users = new ArrayList<Person>();
//	try {
//		Statement statement = connection.createStatement();
//		ResultSet rs = statement.executeQuery("select personid,firstname,prescriptionhistory from Person where roleid="+roleid);
//		while (rs.next()) {
//			Person user = new Person();
//			user.setPersonid(rs.getInt("personid"));
//			user.setFirstname(rs.getString("firstname"));
//			
//			
//			users.add(user);
//		}
//	} catch (SQLException e) {
//		e.printStackTrace();
//	}
//
//	return users;
//}
	
	// for getting list of doctors or pharmacists to be shown on patients dashboard
	public List<PersonDTO> getAllDTOUsers(int roleid, int patientid) {
		List<PersonDTO> users = new ArrayList<PersonDTO>();
		try {
			Statement statement = connection.createStatement();
			//ResultSet rs = statement.executeQuery("select personid,firstname,prescriptionhistory from Person where roleid="+roleid);
			
			String query="select tab1.personid,tab1.firstname ,case when tab2.requesterid is null then CAST(0 as bit) else CAST(1 as bit) end 'HasAdded'"+
			  " from (select * from person p where roleid="+roleid+") tab1 "+
			  " left join "+
			  " (select * from PatientDoctorPharmacistMapping where patientid="+patientid+") tab2 "+
			  " on tab1.personid = tab2.requesterid";
			
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) {
				PersonDTO user = new PersonDTO();
				user.setPersonid(rs.getInt("personid"));
				user.setFirstname(rs.getString("firstname"));
				//user.setRoleid(rs.getInt("roleid"));
				user.setHasadded(rs.getBoolean("hasadded"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}
	
	public void addMapping(int patientid,int requesterid) {
		try {
			
			PreparedStatement pstm = connection
					.prepareStatement("insert into PatientDoctorPharmacistMapping (patientid,requesterid) values (?,?)");
			// Parameters start with 1
			pstm.setInt(1, patientid);
	        pstm.setInt(2, requesterid);
	        
	        pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//for getting patients that are to be shown on doctor dashboard or pharmacist dashboard
	public List<UsersMapping> getMappedUsers(int requesterid) {  
		List<UsersMapping> lstUsersMapping = new ArrayList<UsersMapping>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select p.personid,p.firstname,p.prescriptionhistory,pdpm.hasrequestedhistory,pdpm.isapproved from person p join PatientDoctorPharmacistMapping pdpm on p.personid = pdpm.PatientID where pdpm.RequesterID = "+requesterid);
			while (rs.next()) {
				UsersMapping user = new UsersMapping();
				user.setPersonid(rs.getInt("personid"));
				user.setFirstname(rs.getString("firstname"));
				user.setPrescriptionhistory(rs.getString("prescriptionhistory"));
				user.setHasrequestedhistory(rs.getBoolean("hasrequestedhistory"));
				user.setIsapproved(rs.getBoolean("isapproved"));
				lstUsersMapping.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lstUsersMapping;
	}	
	
	//updateing status of request made by doctor or pharmacist
	public void updatestatus(int patientid,int requesterid) {
	try {
		PreparedStatement preparedStatement = connection.prepareStatement("update PatientDoctorPharmacistMapping SET HasRequestedHistory=1 where PatientID=? and RequesterID=?");
		// Parameters start with 1
		preparedStatement.setInt(1, patientid);
		preparedStatement.setInt(2, requesterid);		
		preparedStatement.executeUpdate();

	} catch (SQLException e) {
		e.printStackTrace();
	}
    }
	
	public List<PersonApproveCTO> getPersonApproveCTOList(int patientid){
		List<PersonApproveCTO> lstPersonApproveCTO = new ArrayList<PersonApproveCTO>();
		try {
			Statement statement = connection.createStatement();
			//ResultSet rs = statement.executeQuery("select p.personid,p.firstname,p.prescriptionhistory,pdpm.hasrequestedhistory,pdpm.isapproved from person p join PatientDoctorPharmacistMapping pdpm on p.personid = pdpm.PatientID where pdpm.RequesterID = "+requesterid);
			
	          
            String query = "select tab1.personid,tab1.firstname,r.rolename,hasrequestedhistory,isapproved from "+
            "(select * from person where roleid in (1,3)) tab1 " +
            " join " +
            "(select * from PatientDoctorPharmacistMapping  where patientid= "+ patientid +" and HasRequestedHistory=1) tab2 "+
            "on tab1.personid =tab2.requesterid " +
            "join Roles r "+
            "on tab1.roleid = r.roleid";
			
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) {
				PersonApproveCTO user = new PersonApproveCTO();
				user.setPersonid(rs.getInt("personid"));
				user.setFirstname(rs.getString("firstname"));		
				user.setRolename(rs.getString("rolename"));
				user.setHasrequestedhistory(rs.getBoolean("hasrequestedhistory"));
				user.setIsapproved(rs.getBoolean("isapproved"));
				lstPersonApproveCTO.add(user);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lstPersonApproveCTO;

	}
	
	//update PatientDoctorPharmacistMapping SET IsApproved=1 where PatientID=7 and RequesterID=2 
	
	   //approval by patient for request made by doctor or pharmacist
		public void updatePatientDoctorPharmacistMapping(int patientid,int requesterid) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("update PatientDoctorPharmacistMapping SET IsApproved=1 where PatientID=? and RequesterID=?");
			// Parameters start with 1
			preparedStatement.setInt(1, patientid);
			preparedStatement.setInt(2, requesterid);		
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
//	public void deleteUser(int userId) {
//		try {
//			PreparedStatement preparedStatement = connection
//					.prepareStatement("delete from users where userid=?");
//			// Parameters start with 1
//			preparedStatement.setInt(1, userId);
//			preparedStatement.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
//	public void updateUser(Person user) {
//		try {
//			PreparedStatement preparedStatement = connection
//					.prepareStatement("update users set firstname=?, lastname=?, dob=?, email=?" +
//							"where userid=?");
//			// Parameters start with 1
//			preparedStatement.setString(1, user.getFirstName());
//			preparedStatement.setString(2, user.getLastName());
//			preparedStatement.setDate(3, new java.sql.Date(user.getDob().getTime()));
//			preparedStatement.setString(4, user.getEmail());
//			preparedStatement.setInt(5, user.getUserid());
//			preparedStatement.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public List<User> getAllUsers() {
//		List<User> users = new ArrayList<User>();
//		try {
//			Statement statement = connection.createStatement();
//			ResultSet rs = statement.executeQuery("select * from users");
//			while (rs.next()) {
//				User user = new User();
//				user.setUserid(rs.getInt("userid"));
//				user.setFirstName(rs.getString("firstname"));
//				user.setLastName(rs.getString("lastname"));
//				user.setDob(rs.getDate("dob"));
//				user.setEmail(rs.getString("email"));
//				users.add(user);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return users;
//	}
//	
//	public User getUserById(int userId) {
//		User user = new User();
//		try {
//			PreparedStatement preparedStatement = connection.
//					prepareStatement("select * from users where userid=?");
//			preparedStatement.setInt(1, userId);
//			ResultSet rs = preparedStatement.executeQuery();
//			
//			if (rs.next()) {
//				user.setUserid(rs.getInt("userid"));
//				user.setFirstName(rs.getString("firstname"));
//				user.setLastName(rs.getString("lastname"));
//				user.setDob(rs.getDate("dob"));
//				user.setEmail(rs.getString("email"));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return user;
//	}
}

