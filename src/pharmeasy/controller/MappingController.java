package pharmeasy.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pharmeasy.dao.UserDao;

/**
 * Servlet implementation class MappingController
 */
@WebServlet("/MappingController")
public class MappingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MappingController() {
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
		String action = request.getParameter("action");

        if (action.equalsIgnoreCase("adddoctor")){
            int patientid = Integer.parseInt(request.getParameter("patientid"));
            int doctorid = Integer.parseInt(request.getParameter("doctorid"));
            dao.addMapping(patientid,doctorid);
           /* forward = LIST_USER;
            request.setAttribute("users", dao.getAllUsers());*/    
        }
        RequestDispatcher view = request.getRequestDispatcher("patientdashboard.jsp");
        view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
