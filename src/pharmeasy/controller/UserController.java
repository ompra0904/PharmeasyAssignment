package pharmeasy.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.daniel.controller.String;
//import com.daniel.model.User;

import pharmeasy.dao.UserDao;

import pharmeasy.model.Person;
import pharmeasy.model.PersonApproveCTO;
import pharmeasy.model.PersonDTO;
import pharmeasy.model.UsersMapping;
import pharmeasy.util.DbUtil;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        dao = new UserDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
//		RequestDispatcher dispatcher = request.getServletContext()
//                .getRequestDispatcher("/home.jsp");
//        dispatcher.forward(request, response);
		
		String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("addPharmacistOrDoctor")){
            int patientid = Integer.parseInt(request.getParameter("patientid"));
            int doctorid = Integer.parseInt(request.getParameter("doctorid"));
            dao.addMapping(patientid,doctorid);
            
            List<PersonDTO> lstDoctors = new ArrayList<PersonDTO>();
    		lstDoctors=dao.getAllDTOUsers(1,patientid);                
    		request.setAttribute("lstDoctors", lstDoctors);
    		
    		List<PersonDTO> lstPharmacists = new ArrayList<PersonDTO>();
    		lstPharmacists=dao.getAllDTOUsers(3,patientid);                
    		request.setAttribute("lstPharmacists", lstPharmacists);
    		
    		List<PersonApproveCTO> lstPersonApproveCTO = new ArrayList<PersonApproveCTO>();
    		lstPersonApproveCTO=dao.getPersonApproveCTOList(patientid);               
    		request.setAttribute("lstPersonApproveCTO", lstPersonApproveCTO);
    		
    		request.setAttribute("loginpersonid", patientid);
    		
    		RequestDispatcher view = request.getRequestDispatcher("/patientdashboard.jsp");
    		view.forward(request, response);
           
        } 
        else if(action.equalsIgnoreCase("updateDoctor"))
        {
        	
        	int patientid = Integer.parseInt(request.getParameter("patientid"));
            int doctorid = Integer.parseInt(request.getParameter("doctorid"));
            dao.updatestatus(patientid,doctorid);
            
            
            List<UsersMapping> lstUsersMapping = new ArrayList<UsersMapping>();
    		lstUsersMapping=dao.getMappedUsers(doctorid);                
    		request.setAttribute("lstUsersMapping", lstUsersMapping);
    		
    		request.setAttribute("loginpersonid", patientid);
    		RequestDispatcher view = request.getRequestDispatcher("/doctordashboard.jsp");
    		view.forward(request, response);
        }//
        else if(action.equalsIgnoreCase("updatePharmacist"))
        {
        	
        	int patientid = Integer.parseInt(request.getParameter("patientid"));
            int pharmacistid = Integer.parseInt(request.getParameter("pharmacistid"));
            dao.updatestatus(patientid,pharmacistid);
            
            
            List<UsersMapping> lstUsersMapping = new ArrayList<UsersMapping>();
    		lstUsersMapping=dao.getMappedUsers(pharmacistid);                
    		request.setAttribute("lstUsersMapping", lstUsersMapping);
    		
    		request.setAttribute("loginpersonid", patientid);
    		RequestDispatcher view = request.getRequestDispatcher("/pharmacistdashboard.jsp");
    		view.forward(request, response);
        }
        //updatePharmacistOrDoctor
       
        else if (action.equalsIgnoreCase("updatePharmacistOrDoctor")){
            int patientid = Integer.parseInt(request.getParameter("patientid"));
            int requesterid = Integer.parseInt(request.getParameter("requesterid"));
            dao.updatePatientDoctorPharmacistMapping(patientid,requesterid);
            
            List<PersonDTO> lstDoctors = new ArrayList<PersonDTO>();
    		lstDoctors=dao.getAllDTOUsers(1,patientid);                
    		request.setAttribute("lstDoctors", lstDoctors);
    		
    		List<PersonDTO> lstPharmacists = new ArrayList<PersonDTO>();
    		lstPharmacists=dao.getAllDTOUsers(3,patientid);                
    		request.setAttribute("lstPharmacists", lstPharmacists);
    		
    		List<PersonApproveCTO> lstPersonApproveCTO = new ArrayList<PersonApproveCTO>();
    		lstPersonApproveCTO=dao.getPersonApproveCTOList(patientid);               
    		request.setAttribute("lstPersonApproveCTO", lstPersonApproveCTO);
    		
    		request.setAttribute("loginpersonid", patientid);
    		
    		RequestDispatcher view = request.getRequestDispatcher("/patientdashboard.jsp");
    		view.forward(request, response);
           
        } 
        /*else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int userId = Integer.parseInt(request.getParameter("userId"));
            User user = dao.getUserById(userId);
            request.setAttribute("user", user);
        } else if (action.equalsIgnoreCase("listUser")){
            forward = LIST_USER;
            request.setAttribute("users", dao.getAllUsers());
        } else {
            forward = INSERT_OR_EDIT;
        }*/
        
        
		//((ServletRequest) response).setAttribute("lstPharmacists", lstPharmacists);
		
		

//        RequestDispatcher view = request.getRequestDispatcher("patientdashboard.jsp");
//        view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String firstname = (String) request.getParameter("firstname");
        String lastname = (String) request.getParameter("lastname");
        String email = (String) request.getParameter("email");
        String gender = (String) request.getParameter("gender");
        int roleid = Integer.parseInt((String) request.getParameter("roleid"));
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        
        Person person = new Person(firstname, lastname, email,gender,roleid,username,password);
        
        dao.addUser(person);
       
        // Store infomation to request attribute, before forward to views.
        RequestDispatcher view = request.getRequestDispatcher("/home.jsp");        
        view.forward(request, response);
        
//        request.setAttribute("person", person);
//        response.sendRedirect(request.getContextPath() + "/login");
	}

}
