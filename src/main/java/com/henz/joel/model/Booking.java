package com.henz.joel.model;

public class Booking {
	
	private int bookingId;
	private Flight flight;
	
	public Booking(final int bookingId, final Flight flight) {
		this.bookingId = bookingId;
		this.flight = flight;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", flight=" + flight + "]";
	}
}
