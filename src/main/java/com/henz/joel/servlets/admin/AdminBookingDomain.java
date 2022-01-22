package com.henz.joel.servlets.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.henz.joel.helper.DatabaseHelper;
import com.henz.joel.model.BookingComplete;
import com.henz.joel.model.Flight;
import com.henz.joel.model.Route;

/**
 * Servlet implementation class AdminBookingDomain
 */
public class AdminBookingDomain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminBookingDomain() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			DatabaseHelper helper = DatabaseHelper.getInstance();
			List<BookingComplete> listWithBookings = helper.getAllBookingsComplete();
			request.setAttribute("listWithBookings", listWithBookings);
			
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin-bookings.jsp");
			rd.include(request, response);
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
