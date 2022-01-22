package com.henz.joel.model;

/*
 * this class is used to show further information of a booking like source, destination etc
 * 
 * */

public class BookingComplete {
	private int bookingId;
	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private int flightId;
	private int price;
	private int availableSeats;
	private String departureTime;
	private String arrivalTime;
	private int routeId;
	private String source;
	private String destination;
	
	private BookingComplete(int bookingId, int userId, String firstName, String lastName, String email, int flightId,
			int price, int availableSeats, String departureString, String arrivalTime, int routeId, String source,
			String destination) {
		super();
		this.bookingId = bookingId;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.flightId = flightId;
		this.price = price;
		this.availableSeats = availableSeats;
		this.departureTime = departureString;
		this.arrivalTime = arrivalTime;
		this.routeId = routeId;
		this.source = source;
		this.destination = destination;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public static class Builder{
		private int bookingId;
		private int userId;
		private String firstName;
		private String lastName;
		private String email;
		private int flightId;
		private int price;
		private int availableSeats;
		private String departureTime;
		private String arrivalTime;
		private int routeId;
		private String source;
		private String destination;
		
		public Builder withBookingId(int bookingId) {
			this.bookingId = bookingId;
			return this;
		}
		
		public Builder withUserId(int userId) {
			this.userId = userId;
			return this;
		}
		
		public Builder withFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public Builder withLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		public Builder withEmail(String email) {
			this.email = email;
			return this;
		}
		
		public Builder withFlightId(int flightId) {
			this.flightId = flightId;
			return this;
		}
		
		public Builder withPrice(int price) {
			this.price = price;
			return this;
		}
		
		public Builder withAvailableSeats(int seats) {
			this.availableSeats = seats;
			return this;
		}
		
		public Builder withDepartureTime(String departureTime) {
			this.departureTime = departureTime;
			return this;
		}
		
		public Builder withArrivalTime(String arrivalTime) {
			this.arrivalTime = arrivalTime;
			return this;
		}
		
		public Builder withRouteId(int routeId) {
			this.routeId = routeId;
			return this;
		}
		
		public Builder withSource(String source) {
			this.source = source;
			return this;
		}
		
		public Builder withDestination(String destination) {
			this.destination = destination;
			return this;
		}
		
		public BookingComplete build() {
			return new BookingComplete(
					bookingId, 
					userId, 
					firstName, 
					lastName, 
					email, 
					flightId, 
					price, 
					availableSeats, 
					departureTime, 
					arrivalTime, 
					routeId, 
					source, 
					destination
			);
		}
	}
}
