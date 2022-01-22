package com.henz.joel.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.henz.joel.helper.DatabaseHelper;
import com.henz.joel.model.Booking;

/**
 * Servlet implementation class ShowBookingsServlet
 */
public class ShowBookingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowBookingsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("isUserLoggedIn") == null) {
			response.sendRedirect(request.getContextPath()+"/start/");
		}else {
			
			DatabaseHelper helper;
			try {
				helper = DatabaseHelper.getInstance();
				
				List<Booking> listWithBookings = helper.getBookingsByUserId((int)session.getAttribute("userId"));
				
				request.setAttribute("listWithBookings", listWithBookings);
				
				RequestDispatcher rd = request.getRequestDispatcher("/views/show-bookings.jsp");
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
