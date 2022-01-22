package com.henz.joel.servlets.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.henz.joel.helper.DatabaseHelper;
import com.henz.joel.helper.UserInputValidationHelper;

/**
 * Servlet implementation class AdminAddRoute
 */
public class AdminAddRoute extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddRoute() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();	
		String[] requestParams = {"source","destination"};
		
		if(UserInputValidationHelper.checkIfResultPageIsAccessedDirectlyWithoutQueryParam(request, requestParams)) {
			response.sendRedirect("/FlyAway/start/admin/routes");
		}else {
			if(UserInputValidationHelper.notAllFieldsAreFilledIn(request, requestParams)) {
				response.getWriter().print("<h3 style=\"color:red;\">Please fill in all fields!</h3>");
				RequestDispatcher rd=request.getRequestDispatcher("/start/admin/routes");
		        rd.include(request, response);
			}else {
				//save new route
				try {
					DatabaseHelper helper = DatabaseHelper.getInstance();
					helper.addNewRoute(request.getParameter("source"), request.getParameter("destination"));
					
					RequestDispatcher rd = request.getRequestDispatcher("/views/admin-add-route-confirmation.jsp");
					rd.include(request, response);
				} catch (ClassNotFoundException | IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
