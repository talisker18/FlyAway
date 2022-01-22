package com.henz.joel.servlets.login_and_registration;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.henz.joel.helper.DatabaseHelper;

/**
 * Servlet implementation class RegistrationValidationServlet
 */
public class RegistrationValidationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationValidationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(this.areParamForRegistrationNull(request)) {
			response.sendRedirect(request.getContextPath()+"/start/registration");
		}else {
			String firstName = request.getParameter("first_name");
			String lastName = request.getParameter("last_name");
			String email = request.getParameter("email");
			String pw = request.getParameter("password");
			
			try {
				DatabaseHelper helper = DatabaseHelper.getInstance();
				
				//first check if user filled in every field
				if(firstName.equals("") || lastName.equals("") || email.equals("") || pw.equals("")) {
					response.getWriter().print("<h3 style=\"color:red;\">Please fill in all data!</h3>");
					RequestDispatcher rd = request.getRequestDispatcher("/start/registration");
					rd.include(request, response);
				}else {
					//email must be unique. Check if there is already this email in database
					boolean isEmailAlreadyInUse = helper.isEmailAlreadyInUse(email);
					if(!isEmailAlreadyInUse) {
						//save data
						helper.saveNewUser(firstName, lastName, email, pw);
						
						request.setAttribute("email", email);
						RequestDispatcher rd = request.getRequestDispatcher("/views/registration-successful.jsp");
						rd.include(request, response);
					}else {
						response.getWriter().print("<h3 style=\"color:red;\">Sorry, this email "+email+" is already in use</h3>");
						RequestDispatcher rd = request.getRequestDispatcher("/start/registration");
						rd.include(request, response);
					}
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private boolean areParamForRegistrationNull(HttpServletRequest request) {
		
		boolean areNull = false;
		
		if(request.getParameter("first_name") == null || request.getParameter("last_name") == null || request.getParameter("email") == null || request.getParameter("password") == null) {
			areNull = true;
		}
		
		return areNull;
	}

}
