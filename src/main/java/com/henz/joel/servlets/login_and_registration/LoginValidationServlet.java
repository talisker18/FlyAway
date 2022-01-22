package com.henz.joel.servlets.login_and_registration;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.henz.joel.helper.DatabaseHelper;
import com.henz.joel.model.User;
import com.henz.joel.other.CustomLogger;
import com.henz.joel.other.UserRoles;

/**
 * Servlet implementation class LoginValidationServlet
 */
public class LoginValidationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginValidationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//if user gets /loginValidation directly
		if(request.getParameter("email") == null || request.getParameter("password") == null) {
			response.sendRedirect(request.getContextPath()+"/start/login");
		}else {
			String email = request.getParameter("email");
			String pw = request.getParameter("password");
			
			if(email.equals("") || pw.equals("")) {
				response.getWriter().print("<h3 style=\"color:red;\">Please fill in all data!</h3>");
				RequestDispatcher rd = request.getRequestDispatcher("/start/login");
				rd.include(request, response);
			}else {
				try {
					DatabaseHelper helper = DatabaseHelper.getInstance();
					User user = helper.returnUserIfLoginIsSuccessful(email, pw);
					
					if(user != null) { //successful login
						HttpSession session = request.getSession();
						session.setAttribute("isUserLoggedIn", "true");
						session.setAttribute("userId", user.getUserId());
						session.setAttribute("email", user.getEmail());
						
						CustomLogger.logger.info("user logged in with email = "+user.getEmail()+" and session id = "+session.getId());
						
						if(user.getRole().equals(UserRoles.USER.getRole())) {
							session.setAttribute("role", UserRoles.USER.getRole());
							
							if(request.getParameter("loginFrom").equals("fromSearchForm")) {
								response.sendRedirect(request.getContextPath()+"/start/"); // go to start servlet
							}else if(request.getParameter("loginFrom").equals("fromFlightOverview")) {
								response.sendRedirect(request.getContextPath()+"/start/searchFlightResults");
							}
						}else { //logged in as admin
							session.setAttribute("role", UserRoles.ADMIN.getRole());
							session.setAttribute("isUserLoggedIn", "true");
							response.sendRedirect(request.getContextPath()+"/start/admin");
						}
					}else {
						response.getWriter().print("<h3 style=\"color:red;\">Wrong email or password!</h3>");
						RequestDispatcher rd = request.getRequestDispatcher("/start/login");
						rd.include(request, response);
					}
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
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
