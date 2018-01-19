package pharmeasy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pharmeasy.dao.UserDao;
import pharmeasy.model.Person;
import pharmeasy.model.PersonApproveCTO;
import pharmeasy.model.PersonDTO;
import pharmeasy.model.UsersMapping;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
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
		
		RequestDispatcher view = request.getRequestDispatcher("/home.jsp");
        view.forward(request, response);
		
//		String action = request.getParameter("action");
//		
//		if (action.equalsIgnoreCase("adddoctor")){
//            int patientid = Integer.parseInt(request.getParameter("patientid"));
//            int doctorid = Integer.parseInt(request.getParameter("doctorid"));
//            dao.addMapping(patientid,doctorid);
//           /* forward = LIST_USER;
//            request.setAttribute("users", dao.getAllUsers());*/  
//            
//            List<Person> lstDoctors = new ArrayList<Person>();
//    		lstDoctors=dao.getAllUsers(1);                
//    		request.setAttribute("lstDoctors", lstDoctors);
//    		
//    		List<Person> lstPharmacists = new ArrayList<Person>();
//    		lstPharmacists=dao.getAllUsers(3);                
//    		request.setAttribute("lstPharmacists", lstPharmacists);
//    		
//    		RequestDispatcher view = request.getRequestDispatcher("/patientdashboard.jsp");
//    		view.forward(request, response);
//        } 
        
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        
        Person person = new Person();
        person=dao.loginUser(username,password);
        
        if(person == null)
        {
        	response.addHeader("status", "Username password invalid");
        	RequestDispatcher view = request.getRequestDispatcher("/home.jsp");
        	view.forward(request, response);
        }
        else
        {
        	int loggedInUserID=person.getPersonid();
        	request.setAttribute("loginpersonid", loggedInUserID);
        	if(person.getRoleid() == 1)
        	{
        		List<UsersMapping> lstUsersMapping = new ArrayList<UsersMapping>();
        		lstUsersMapping=dao.getMappedUsers(loggedInUserID);                
        		request.setAttribute("lstUsersMapping", lstUsersMapping);
    		      		
        		// Store info to the request attribute before forwarding.
        		RequestDispatcher view = request.getRequestDispatcher("/doctordashboard.jsp");
        		view.forward(request, response);
        		
        		
        	}
        	else if(person.getRoleid() == 2)
        	{
        		List<PersonDTO> lstDoctors = new ArrayList<PersonDTO>();
        		lstDoctors=dao.getAllDTOUsers(1,loggedInUserID);                
        		request.setAttribute("lstDoctors", lstDoctors);
        		
        		List<PersonDTO> lstPharmacists = new ArrayList<PersonDTO>();
        		lstPharmacists=dao.getAllDTOUsers(3,loggedInUserID);                
        		request.setAttribute("lstPharmacists", lstPharmacists);
        		
        		List<PersonApproveCTO> lstPersonApproveCTO = new ArrayList<PersonApproveCTO>();
        		lstPersonApproveCTO=dao.getPersonApproveCTOList(person.getPersonid());               
        		request.setAttribute("lstPersonApproveCTO", lstPersonApproveCTO);
        		
        		RequestDispatcher view = request.getRequestDispatcher("/patientdashboard.jsp");
        		view.forward(request, response);
        		//response.sendRedirect("/patientdashboard.jsp");
        	}
        	else if(person.getRoleid() == 3)
        	{
        		List<UsersMapping> lstUsersMapping = new ArrayList<UsersMapping>();
        		lstUsersMapping=dao.getMappedUsers(loggedInUserID);                
        		request.setAttribute("lstUsersMapping", lstUsersMapping);
    		      		
        		// Store info to the request attribute before forwarding.
        		RequestDispatcher view = request.getRequestDispatcher("/pharmacistdashboard.jsp");
        		view.forward(request, response);        		
        	}
        }
       
        // Store infomation to request attribute, before forward to views.
           
	}

}
