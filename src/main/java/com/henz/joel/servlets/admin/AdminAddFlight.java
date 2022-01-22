package com.henz.joel.servlets.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.henz.joel.helper.DatabaseHelper;
import com.henz.joel.helper.UserInputValidationHelper;
import com.henz.joel.model.Flight;

/**
 * Servlet implementation class AdminAddFlight
 */
public class AdminAddFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddFlight() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();	
		String[] requestParams = {"price","availableSeats","departureTime","arrivalTime"};
		
		if(UserInputValidationHelper.checkIfResultPageIsAccessedDirectlyWithoutQueryParam(request, requestParams)) {
			response.sendRedirect(request.getContextPath()+"/start/admin/flights");
		}else {
			if(UserInputValidationHelper.notAllFieldsAreFilledIn(request, requestParams)) {
				response.getWriter().print("<h3 style=\"color:red;\">Please fill in all fields!</h3>");
				RequestDispatcher rd=request.getRequestDispatcher("/start/admin/flights");
		        rd.include(request, response);
			}else {
				//first check if price and avaliable seats are numbers
				String[] numbersAsString = {request.getParameter("price"),request.getParameter("availableSeats")};
				
				if(UserInputValidationHelper.checkIfInputIsNumber(numbersAsString)) {
					//save flight
					int price = Integer.parseInt(request.getParameter("price"));
					int availableSeats = Integer.parseInt(request.getParameter("availableSeats"));
					String departureTimeAsString = request.getParameter("departureTime");
					String arrivalTimeAsString = request.getParameter("arrivalTime");
					
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
					
					try {
						Date departureTime = format.parse(departureTimeAsString);
						Date arrivalTime = format.parse(arrivalTimeAsString);
						format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						
						String convertedDepartureTime = format.format(departureTime);
						String convertedArrivalTime = format.format(arrivalTime);
						
						int routeId = Integer.parseInt(request.getParameter("route"));
						
						DatabaseHelper helper = DatabaseHelper.getInstance();
						helper.saveNewFlight(price, availableSeats, convertedDepartureTime, convertedArrivalTime, routeId);
						
						RequestDispatcher rd = request.getRequestDispatcher("/views/admin-add-flight-confirmation.jsp");
						rd.include(request, response);
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					response.getWriter().print("<h3 style=\"color:red;\">Input for Price and Available Seats must be a number!</h3>");
					RequestDispatcher rd=request.getRequestDispatcher("/start/admin/flights");
			        rd.include(request, response);
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
	
	private void addNewFlight(Flight flight) throws IOException{
		/*String source = request.getParameter("from");
		String destination = request.getParameter("to");
		String dateAsStringNotConverted = request.getParameter("date");
		
		List<Flight> listWithFlights = null;
		
		//convert Date to correct pattern and then to String
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		
		Date date;
		
		try {
			date = format.parse(dateAsStringNotConverted);
			//change format of that date to yyyy-MM-dd
			format = new SimpleDateFormat("yyyy-MM-dd");
			String convertedDateAsString = format.format(date);
			
			//get all flights
			DatabaseHelper helper = DatabaseHelper.getInstance();
			listWithFlights = helper.getListWithFlights(source, destination, convertedDateAsString);
			
		} catch (ParseException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listWithFlights;*/
	}

}
