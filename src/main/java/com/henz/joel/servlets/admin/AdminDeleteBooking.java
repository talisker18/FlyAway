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
 * Servlet implementation class AdminDeleteBooking
 */
public class AdminDeleteBooking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteBooking() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("bookingId") == null || request.getParameter("flightId") == null) {
			response.sendRedirect(request.getContextPath()+"/start/admin");
		}else {
			int bookingId = Integer.parseInt(request.getParameter("bookingId"));
			int flightId = Integer.parseInt(request.getParameter("flightId"));
			
			try {
				DatabaseHelper helper = DatabaseHelper.getInstance();
				helper.deleteBookingById(bookingId,flightId);
				
				RequestDispatcher rd = request.getRequestDispatcher("/views/admin-delete-booking-confirmation.jsp");
				request.setAttribute("bookingId", bookingId);
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
