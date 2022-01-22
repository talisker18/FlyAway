package com.henz.joel.servlets;

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
 * Servlet implementation class SearchFlightsServlet
 */
public class SearchFlightsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchFlightsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();	
		List<Flight> listWithFlights = null;
		String[] requestParams = {"from","to","date"};
		
		
		//first check if user puts /FlyAway/start/searchFlightResults directly as URL (without query param)
		if(UserInputValidationHelper.checkIfResultPageIsAccessedDirectlyWithoutQueryParam(request, requestParams)) {
			RequestDispatcher rd=request.getRequestDispatcher("/views/flight-overview.jsp");
	        rd.include(request, response);
		}else {
			//check if user input of search is correct (all fields have to be filled in)
			if(UserInputValidationHelper.notAllFieldsAreFilledIn(request,requestParams)) {
				response.getWriter().print("<h3 style=\"color:red;\">Please fill in all fields!</h3>");
				RequestDispatcher rd=request.getRequestDispatcher("/start/");
		        rd.include(request, response);
			}else {
				listWithFlights = this.getFlightsBySearch(request);
				session.setAttribute("listWithFlights", listWithFlights);
				RequestDispatcher rd=request.getRequestDispatcher("/views/flight-overview.jsp");
		        rd.include(request, response);
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
	
	private List<Flight> getFlightsBySearch(HttpServletRequest request) throws IOException{
		String source = request.getParameter("from");
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
		
		return listWithFlights;
	}
}
