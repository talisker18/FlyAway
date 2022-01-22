package com.henz.joel.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.henz.joel.helper.DatabaseHelper;
import com.henz.joel.model.Flight;

/**
 * Servlet implementation class BookingConfirmationServlet
 */
public class BookingConfirmationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingConfirmationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("isUserLoggedIn") == null || request.getParameter("flightNumber") == null) {
			response.sendRedirect(request.getContextPath()+"/start/");
		}else {
			//in case the user returns to booking details and confirms the booking again, a message is shown that he already has booked this flight
			
			String flightNumberAsString = request.getParameter("flightNumber");
			int flightNumber = Integer.parseInt(flightNumberAsString);
			int userId = (int) session.getAttribute("userId");

			DatabaseHelper helper = null;
			int bookingId = -1;
			
			try {
				helper = DatabaseHelper.getInstance();
				
				Flight flight = helper.getFlightById(flightNumber);
				
				if(flight.getAvailableSeats() > 0) {
					//check if this booking was already done by this user and for that flight
					bookingId = helper.getBookingIdByUserIdAndFlightnumber(userId, flightNumber);
					
					if(bookingId != -1 ) {
						response.getWriter().print("<h2>Booking Confirmation</h2>\r\n"
								+ "        <br>\r\n"
								+ "\r\n"
								+ "		<h3 style=\"color:red;\">You have already booked this flight with booking id: "+bookingId+"</h3>");
						RequestDispatcher rd = request.getRequestDispatcher("/views/booking-confirmation.jsp");
						rd.include(request, response);
					}else {
						helper.insertNewBooking(userId, flightNumber);
						bookingId = helper.getBookingIdByUserIdAndFlightnumber(userId, flightNumber);
						
						response.getWriter().print("<h2>Booking Confirmation</h2>\r\n"
								+ "        <br>\r\n"
								+ "\r\n"
								+ "		<h3 style=\"color:green;\">Your booking was successful. Your booking id is: "+bookingId+"</h3>");
						RequestDispatcher rd = request.getRequestDispatcher("/views/booking-confirmation.jsp");
						rd.include(request, response);
					}
				}else {
					response.getWriter().print("<h2>Booking Confirmation</h2>\r\n"
							+ "        <br>\r\n"
							+ "\r\n"
							+ "		<h3 style=\"color:red;\">You can't book this flight because there are no seats left</h3>");
					RequestDispatcher rd = request.getRequestDispatcher("/views/booking-confirmation.jsp");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
