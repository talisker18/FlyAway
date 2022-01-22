package com.henz.joel.servlets.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.henz.joel.helper.DatabaseHelper;

/**
 * Servlet implementation class AdminDeleteFlight
 */
public class AdminDeleteFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteFlight() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("flightNumber") == null) {
			response.sendRedirect(request.getContextPath()+"/start/admin");
		}else {
			int flightNumber = Integer.parseInt(request.getParameter("flightNumber"));
			
			try {
				DatabaseHelper helper = DatabaseHelper.getInstance();
				helper.deleteFlightById(flightNumber);
				
				RequestDispatcher rd = request.getRequestDispatcher("/views/admin-delete-flight-confirmation.jsp");
				request.setAttribute("flightNumber", flightNumber);
				rd.include(request, response);
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
