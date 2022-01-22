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
import com.henz.joel.model.Flight;
import com.henz.joel.model.Route;

/**
 * Servlet implementation class AdminFlightDomain
 */
public class AdminFlightDomain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminFlightDomain() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			DatabaseHelper helper = DatabaseHelper.getInstance();
			List<Flight> listWithFlights = helper.getAllFlights();
			request.setAttribute("listWithFlights", listWithFlights);
			
			List<Route> listWithRoutes = helper.getAllRoutes();
			request.setAttribute("listWithRoutes", listWithRoutes);
			
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin-flights.jsp");
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
