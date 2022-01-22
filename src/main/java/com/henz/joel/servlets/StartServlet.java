package com.henz.joel.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.henz.joel.helper.DatabaseHelper;
import com.henz.joel.other.CustomLogger;
import com.henz.joel.other.UserRoles;

/**
 * Servlet implementation class StartServlet
 */
public class StartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseHelper helper;
		HttpSession session = request.getSession();

		try {
			helper = DatabaseHelper.getInstance();
			
			List<String> sourceList = helper.getRoutesSourcesOrdered();
			//eliminate duplicates
			sourceList = new ArrayList<String>(new HashSet<String>(sourceList));
			request.setAttribute("sourceList", sourceList);
			
			List<String> destinationList = helper.getRoutesDestinationsOrdered();
			//eliminate duplicates
			destinationList = new ArrayList<String>(new HashSet<String>(destinationList));
			request.setAttribute("destinationList", destinationList);
			
			//redirect admin to admin servlet if he accesses this servlet
			if(session.getAttribute("isUserLoggedIn") != null) {
				if(session.getAttribute("role").equals(UserRoles.ADMIN.getRole())) {
					response.sendRedirect(request.getContextPath()+"/start/admin");
				}
			}
			
			RequestDispatcher rd=request.getRequestDispatcher("/views/start.jsp");  
	        rd.include(request, response); //include is needed because we set sourceList and destinationList on jsp file
		} catch (ClassNotFoundException | IOException | SQLException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		doGet(request, response);
	}

}
