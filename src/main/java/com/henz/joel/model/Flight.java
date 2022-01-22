package com.henz.joel.model;

public class Flight {
	
	private int flightNumber;
	private int price;
	private int availableSeats;
	private String departureTime;
	private String arrivalTime;
	private String source;
	private String destination;
	private Route route;
	
	private Flight(int flightNumber, int price, int availableSeats, String departureTime, String arrivalTime,
			String source, String destination) {

		this.flightNumber = flightNumber;
		this.price = price;
		this.availableSeats = availableSeats;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.source = source;
		this.destination = destination;
	}
	
	//with Route object
	private Flight(int flightNumber, int price, int availableSeats, String departureTime, String arrivalTime,
			Route route) {

		this.flightNumber = flightNumber;
		this.price = price;
		this.availableSeats = availableSeats;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.route = route;
	}
	
	public static class Builder{
		
		private int flightNumber;
		private int price;
		private int availableSeats;
		private String departureTime;
		private String arrivalTime;
		private String source;
		private String destination;
		private Route route;
		
		public Builder withFlightNumber(int flightNumber) {
			this.flightNumber = flightNumber;
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
		
		public Builder withSource(String source) {
			this.source = source;
			return this;
		}
		
		public Builder withDestination(String destination) {
			this.destination = destination;
			return this;
		}
		
		public Builder withRoute(Route route) {
			this.route = route;
			return this;
		}
		
		public Flight build() {
			return new Flight(
					this.flightNumber,
					this.price,
					this.availableSeats,
					this.departureTime,
					this.arrivalTime,
					this.source,
					this.destination
			);
		}
		
		public Flight buildWithRoute() {
			return new Flight(
					this.flightNumber,
					this.price,
					this.availableSeats,
					this.departureTime,
					this.arrivalTime,
					this.route
			);
		}
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
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

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	@Override
	public String toString() {
		return "Flight [flightNumber=" + flightNumber + ", price=" + price + ", availableSeats=" + availableSeats
				+ ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", source=" + source
				+ ", destination=" + destination + "]";
	}
}
