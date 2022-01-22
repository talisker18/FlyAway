package com.henz.joel.helper;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import com.henz.joel.model.Booking;
import com.henz.joel.model.BookingComplete;
import com.henz.joel.model.Flight;
import com.henz.joel.model.Route;
import com.henz.joel.model.User;
import com.henz.joel.other.FlightComparatorByDepartureTime;

public class DatabaseHelper {
	
	private Connection con;
	private String url;
	private String username;
	private String password;
	private String driver;
	private final String user = "user";
	private final String admin = "admin";
	
	private static DatabaseHelper helperInstance = null;
	
	private DatabaseHelper() throws IOException, ClassNotFoundException, SQLException {
		Properties props = new Properties();
		InputStream  in = this.getClass().getClassLoader().getResourceAsStream("application.properties"); //get compiled file application.properties via CLASSPATH
		props.load(in);
		in.close();

		this.driver = props.getProperty("database.driver");

		if (this.driver != null) {
		    Class.forName(this.driver) ;
		}

		this.url = props.getProperty("database.url");
		this.username = props.getProperty("database.user");
		this.password = props.getProperty("database.password");
	}
	
	public static DatabaseHelper getInstance() throws ClassNotFoundException, IOException, SQLException {
		if(helperInstance == null) {
			helperInstance = new DatabaseHelper();
		}
		
		return helperInstance;
	}
	
	public List<String> getRoutesSourcesOrdered() throws SQLException, ClassNotFoundException, IOException{
		this.establishConnection();
		
		PreparedStatement ps = this.con.prepareStatement("SELECT source FROM routes");
        ResultSet rs = ps.executeQuery();
        
        List<String> sources = new ArrayList<String>();
        
        while (rs.next()) {
        	sources.add(rs.getString(1));
        }
        
        Collections.sort(sources);
            
        ps.close();
        this.con.close();
        
        return sources;
	}
	
	public List<String> getRoutesDestinationsOrdered() throws SQLException, ClassNotFoundException, IOException{
		this.establishConnection();
		
		PreparedStatement ps = this.con.prepareStatement("SELECT destination FROM routes");
        //ps.setString(1, "Willi");
        //ps.setString(2, "Meier");
        ResultSet rs = ps.executeQuery();
        
        List<String> sources = new ArrayList<String>();
        
        while (rs.next()) {
        	sources.add(rs.getString(1));
        }
        
        Collections.sort(sources);
            
        ps.close();
        this.con.close();
        
        return sources;
	}
	
	public List<Flight> getListWithFlights(String source, String destination, String date) throws SQLException, ClassNotFoundException, IOException{
		this.establishConnection();
		
		PreparedStatement ps = this.con.prepareStatement("SELECT flights.flight_id, flights.price_in_dollar, flights.available_seats, flights.departure_time, flights.arrival_time, routes.source, routes.destination\r\n"
				+ "\r\n"
				+ "FROM flights\r\n"
				+ "\r\n"
				+ "INNER JOIN routes on flights.fk_route_id = routes.route_id\r\n"
				+ "\r\n"
				+ "WHERE (source =? AND destination =?) "
				+ "AND (DATE(departure_time) =? OR DATE(arrival_time) =?);");
		
        ps.setString(1,source);
        ps.setString(2,destination);
        ps.setString(3,date);
        ps.setString(4,date);

        ResultSet rs = ps.executeQuery();
        
        List<Flight> listOfFlights = new ArrayList<Flight>();
        
        while (rs.next()) {
        	
        	Flight flight = new Flight.Builder()
        			.withFlightNumber(rs.getInt(1))
        			.withPrice(rs.getInt(2))
        			.withAvailableSeats(rs.getInt(3))
        			.withDepartureTime(rs.getString(4))
        			.withArrivalTime(rs.getString(5))
        			.withSource(rs.getString(6))
        			.withDestination(rs.getString(7))
        			.build();
        	
        	//System.out.println(flight);
        	
        	listOfFlights.add(flight);
        }
        
        FlightComparatorByDepartureTime comparator = new FlightComparatorByDepartureTime();
        
        Collections.sort(listOfFlights,comparator);

        /*for(Flight f: listOfFlights) {
        	System.out.println(f);
        }*/
            
        ps.close();
        this.con.close();
		return listOfFlights;
	}
	
	public List<Flight> getAllFlights() throws SQLException, ClassNotFoundException, IOException{
		this.establishConnection();
		
		PreparedStatement ps = this.con.prepareStatement("SELECT flights.flight_id, flights.price_in_dollar, flights.available_seats, flights.departure_time, flights.arrival_time, routes.route_id, routes.source, routes.destination\r\n"
				+ "\r\n"
				+ "FROM flights\r\n"
				+ "\r\n"
				+ "INNER JOIN routes on flights.fk_route_id = routes.route_id;");

        ResultSet rs = ps.executeQuery();
        
        List<Flight> listOfFlights = new ArrayList<Flight>();
        
        while (rs.next()) {
        	
        	Route route = new Route(rs.getInt(6),rs.getString(7),rs.getString(8));
        	
        	Flight flight = new Flight.Builder()
        			.withFlightNumber(rs.getInt(1))
        			.withPrice(rs.getInt(2))
        			.withAvailableSeats(rs.getInt(3))
        			.withDepartureTime(rs.getString(4))
        			.withArrivalTime(rs.getString(5))
        			.withRoute(route)
        			.buildWithRoute();
        	
        	//System.out.println(flight);
        	
        	listOfFlights.add(flight);
        }
        
        FlightComparatorByDepartureTime comparator = new FlightComparatorByDepartureTime();
        
        Collections.sort(listOfFlights,comparator);

        /*for(Flight f: listOfFlights) {
        	System.out.println(f);
        }*/
            
        ps.close();
        this.con.close();
		return listOfFlights;
	}
	
	public boolean isEmailAlreadyInUse(String email) throws SQLException {
		this.establishConnection();
		
		PreparedStatement ps = this.con.prepareStatement("SELECT * FROM users WHERE email=?;");
		
        ps.setString(1,email);
        
        ResultSet rs = ps.executeQuery();
        
        boolean isEmailAlreadyInUse = rs.next();
            
        ps.close();
        this.con.close();
        
        return isEmailAlreadyInUse;
	}
	
	public void saveNewUser(String firstName, String lastName, String email, String password) throws SQLException {
		this.establishConnection();
		
		PreparedStatement ps = this.con.prepareStatement("INSERT INTO users (first_name,last_name,email,password,role) VALUES"
				+ "(?,?,?,?,?);");
		
        ps.setString(1,firstName);
        ps.setString(2,lastName);
        ps.setString(3,email);
        ps.setString(4,password);
        ps.setString(5, this.user);
        
        int row = ps.executeUpdate();
            
        ps.close();
        this.con.close();
	}
	
	public User returnUserIfLoginIsSuccessful(String email, String password) throws SQLException {
		this.establishConnection();
		
		PreparedStatement ps = this.con.prepareStatement("SELECT * FROM users where email=? AND password=?");
		
        ps.setString(1,email);
        ps.setString(2,password);
        
        ResultSet rs = ps.executeQuery();
        int rowCounter=0;
        User user = null;
        
        //since email is unique, there should be only 1 record. In case there are multiple records (manual inserts) found for a email, the method will return null
        while(rs.next()) {
        	rowCounter++;
        	user = new User.Builder()
        			.withUserId(rs.getInt(1))
        			.withFirstName(null)
        			.withLastName(null)
        			.withEmail(rs.getString(4))
        			.withPassword(null)
        			.withRole(rs.getString(6))
        			.build();
        }
        
        ps.close();
        this.con.close();
        
        if(rowCounter == 1) {
        	return user;
        }else { //if rowCounter is 0 or more than 1
        	return null;
        }
	}
	
	public boolean checkIfPasswordIsCorrectByUserId(int userId, String password) throws SQLException {
		this.establishConnection();
		
		PreparedStatement ps = this.con.prepareStatement("SELECT password FROM users where user_id=?");
        ps.setInt(1,userId);
        ResultSet rs = ps.executeQuery();
        
        String passwordInDb = null;
        
        while(rs.next()) {
        	passwordInDb = rs.getString(1);
        }
        
        ps.close();
        this.con.close();
        
       return password.equals(passwordInDb);
	}
	
	public void changePassword(int userId, String newPassword) throws SQLException {
		this.establishConnection();
		
		PreparedStatement ps = this.con.prepareStatement("UPDATE users SET password=? WHERE user_id=?");
		
        ps.setString(1,newPassword);
        ps.setInt(2,userId);
        
        int row = ps.executeUpdate();
            
        ps.close();
        this.con.close();
	}
	
	public Flight getFlightById(int id) throws SQLException {
		this.establishConnection();
		
		PreparedStatement ps = this.con.prepareStatement("SELECT flights.flight_id, flights.price_in_dollar, flights.available_seats, flights.departure_time, flights.arrival_time, routes.source, routes.destination\r\n"
				+ "FROM flights\r\n"
				+ "INNER JOIN routes ON flights.fk_route_id = routes.route_id\r\n"
				+ "WHERE flight_id=?;");
		
        ps.setInt(1,id);

        ResultSet rs = ps.executeQuery();
        int rowCounter = 0;
        Flight flight = null;
        
        while (rs.next()) {
        	rowCounter++;
        	flight = new Flight.Builder()
        			.withFlightNumber(rs.getInt(1))
        			.withPrice(rs.getInt(2))
        			.withAvailableSeats(rs.getInt(3))
        			.withDepartureTime(rs.getString(4))
        			.withArrivalTime(rs.getString(5))
        			.withSource(rs.getString(6))
        			.withDestination(rs.getString(7))
        			.build();
        }
            
        ps.close();
        this.con.close();
        
        if(rowCounter == 1) {
        	return flight;
        }else {
        	return null;
        }
	}
	
	public int getBookingIdByUserIdAndFlightnumber(int userId, int flightNumber) throws SQLException {
		this.establishConnection();
		
		int bookingId = -1;
		
		PreparedStatement ps = this.con.prepareStatement("SELECT booking_id from bookings WHERE fk_user_id =? AND fk_flight_id =?");
		
        ps.setInt(1,userId);
        ps.setInt(2, flightNumber);
        

        ResultSet rs = ps.executeQuery();
        
        int rowCounter = 0;
        
        while(rs.next()) {
        	bookingId = rs.getInt(1);
        	rowCounter++;
        }
            
        ps.close();
        this.con.close();
        
        if(rowCounter == 1) {
        	return bookingId;
        }else {
        	return -1;
        }
	}
	
	public void insertNewBooking(int userId, int flightNumber) throws SQLException {
		this.establishConnection();
		
		PreparedStatement ps = this.con.prepareStatement("INSERT INTO bookings (fk_user_id,fk_flight_id) VALUES"
				+ "(?,?);");
		
        ps.setInt(1,userId);
        ps.setInt(2,flightNumber);
        
        int row = ps.executeUpdate();
        
        //also we have to reduce the number of available seats in flights table
        ps = this.con.prepareStatement("UPDATE flights SET available_seats = available_seats-1 WHERE flight_id =?;");  
        ps.setInt(1,flightNumber);
        row = ps.executeUpdate();
         
        ps.close();
        this.con.close();
	}
	
	public List<Booking> getBookingsByUserId(int userId) throws SQLException{
		this.establishConnection();
		
		PreparedStatement ps = this.con.prepareStatement("SELECT bookings.booking_id, routes.source, routes.destination, flights.price_in_dollar, flights.available_seats, flights.departure_time, flights.arrival_time FROM flights\r\n"
				+ "INNER JOIN bookings ON flights.flight_id = bookings.fk_flight_id\r\n"
				+ "INNER JOIN routes ON flights.fk_route_id = routes.route_id\r\n"
				+ "WHERE bookings.fk_user_id =?;");
		
        ps.setInt(1,userId);

        ResultSet rs = ps.executeQuery();
        
        List<Booking> listWithBookings = new ArrayList<Booking>();
        
        while (rs.next()) {
        	int bookingId = rs.getInt(1);
        	
        	Flight flight = new Flight.Builder()
        			.withFlightNumber(-1)
        			.withPrice(rs.getInt(4))
        			.withAvailableSeats(rs.getInt(5))
        			.withDepartureTime(rs.getString(6))
        			.withArrivalTime(rs.getString(7))
        			.withSource(rs.getString(2))
        			.withDestination(rs.getString(3))
        			.build();
        	
        	listWithBookings.add(new Booking(bookingId,flight));
        }
            
        ps.close();
        this.con.close();
        
        return listWithBookings;
	}
	
	public List<BookingComplete> getAllBookingsComplete() throws SQLException{
		this.establishConnection();
		
		PreparedStatement ps = this.con.prepareStatement("select bookings.booking_id, users.user_id, users.first_name, users.last_name, users.email, flights.flight_id, flights.price_in_dollar, flights.available_seats, flights.departure_time, flights.arrival_time, routes.route_id, routes.source, routes.destination\r\n"
				+ "FROM bookings\r\n"
				+ "INNER JOIN users ON users.user_id = bookings.fk_user_id\r\n"
				+ "INNER JOIN flights ON bookings.fk_flight_id = flights.flight_id\r\n"
				+ "INNER JOIN routes ON routes.route_id = flights.fk_route_id;");

        ResultSet rs = ps.executeQuery();
        
        List<BookingComplete> listWithBookings = new ArrayList<BookingComplete>();
        
        while (rs.next()) {
        	BookingComplete booking = new BookingComplete.Builder()
        			.withBookingId(rs.getInt(1))
        			.withUserId(rs.getInt(2))
        			.withFirstName(rs.getString(3))
        			.withLastName(rs.getString(4))
        			.withEmail(rs.getString(5))
        			.withFlightId(rs.getInt(6))
        			.withPrice(rs.getInt(7))
        			.withAvailableSeats(rs.getInt(8))
        			.withDepartureTime(rs.getString(9))
        			.withArrivalTime(rs.getString(10))
        			.withRouteId(rs.getInt(11))
        			.withSource(rs.getString(12))
        			.withDestination(rs.getString(13))
        			.build();
        	
        	listWithBookings.add(booking);
        }
            
        ps.close();
        this.con.close();
        
        return listWithBookings;
	}
	
	public List<Route> getAllRoutes() throws SQLException{
		this.establishConnection();
		
		PreparedStatement ps = this.con.prepareStatement("SELECT * FROM routes;");

        ResultSet rs = ps.executeQuery();
        
        List<Route> listWithRoutes = new ArrayList<Route>();
        
        while (rs.next()) {      	
        	listWithRoutes.add(new Route(rs.getInt(1),rs.getString(2),rs.getString(3)));
        }
            
        ps.close();
        this.con.close();
        
        return listWithRoutes;
	}
	
	public void addNewRoute(String source, String destination) throws SQLException {
		this.establishConnection();
		
		PreparedStatement ps = this.con.prepareStatement("INSERT INTO routes (source,destination) VALUES"
				+ "(?,?);");
		
        ps.setString(1,source);
        ps.setString(2,destination);
        
        int row = ps.executeUpdate();
            
        ps.close();
        this.con.close();
	}
	
	public void deleteRouteById(int routeId) throws SQLException {
		this.establishConnection();
		
		PreparedStatement ps = this.con.prepareStatement("DELETE FROM routes WHERE route_id=?");
		
        ps.setInt(1,routeId);
        
        int row = ps.executeUpdate();
         
        ps.close();
        this.con.close();
	}
	
	public void deleteFlightById(int flightNumber) throws SQLException {
		this.establishConnection();
		
		PreparedStatement ps = this.con.prepareStatement("DELETE FROM flights WHERE flight_id=?");
		
        ps.setInt(1,flightNumber);
        
        int row = ps.executeUpdate();
         
        ps.close();
        this.con.close();
	}
	
	public void deleteBookingById(int bookingId, int flightId) throws SQLException {
		this.establishConnection();
		
		PreparedStatement ps = this.con.prepareStatement("DELETE FROM bookings WHERE booking_id=?");
        ps.setInt(1,bookingId);
        int row = ps.executeUpdate();
        
        //increase available on that flight
        ps = this.con.prepareStatement("UPDATE flights SET available_seats = available_seats+1 WHERE flight_id =?;");
        ps.setInt(1,flightId);
        row = ps.executeUpdate();
         
        ps.close();
        this.con.close();
	}
	
	public void saveNewFlight(int price, int availableSeats, String departureTime, String arrivalTime, int routeId) throws SQLException {
		this.establishConnection();
		
		PreparedStatement ps = this.con.prepareStatement("INSERT INTO flights (price_in_dollar,available_seats,departure_time,arrival_time,fk_route_id) VALUES"
				+ "(?,?,?,?,?);");
		
        ps.setInt(1,price);
        ps.setInt(2,availableSeats);
        ps.setString(3,departureTime);
        ps.setString(4,arrivalTime);
        ps.setInt(5,routeId);
        
        int row = ps.executeUpdate();
            
        ps.close();
        this.con.close();
	}
	
	private void establishConnection() throws SQLException {
		this.con = DriverManager.getConnection(url, username, password);
	}

}
