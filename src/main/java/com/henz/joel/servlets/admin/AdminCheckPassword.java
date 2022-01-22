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
 * Servlet implementation class AdminCheckPassword
 */
public class AdminCheckPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCheckPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] params = {"currentPassword","newPassword"};
		
		if(UserInputValidationHelper.checkIfResultPageIsAccessedDirectlyWithoutQueryParam(request, params)) {
			response.sendRedirect(request.getContextPath()+"/start/admin/changePassword");
		}else {
			DatabaseHelper helper;
			try {
				HttpSession session = request.getSession();
				helper = DatabaseHelper.getInstance();
				String currentPassword = request.getParameter("currentPassword");
				int userId = (int)session.getAttribute("userId");
				
				if(helper.checkIfPasswordIsCorrectByUserId(userId, currentPassword)) {
					String newPassword = request.getParameter("newPassword");
					
					//save new password
					helper.changePassword(userId, newPassword);
					RequestDispatcher rd = request.getRequestDispatcher("/views/admin-change-pw-confirmation.jsp");
					rd.include(request, response);
				}else {
					response.getWriter().print("<h3 style=\"color:red;\">Current password was wrong</h3>");
					RequestDispatcher rd = request.getRequestDispatcher("/views/admin-change-pw.jsp");
					rd.include(request, response);
				}
			} catch (ClassNotFoundException | IOException | SQLException e) {
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

}
