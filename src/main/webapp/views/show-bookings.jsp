<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Your bookings</title>
</head>
<body>
		<div align="center">
			<h2>Your bookings</h2>
			<br>
			<table border="1">
				<th>Booking No</th>
				<th>Source</th>
				<th>Destination</th>
				<th>Price in $</th>
				<th>Available seats</th>
				<th>Departure time</th>
				<th>Arrival time</th>
				
				<c:forEach items="${listWithBookings}" var="booking">
					<tr>
						<td>${booking.bookingId}</td>
						<td>${booking.flight.source}</td>
						<td>${booking.flight.destination}</td>
						<td>${booking.flight.price}</td>
						<td>${booking.flight.availableSeats}</td>
						<td>${booking.flight.departureTime}</td>
						<td>${booking.flight.arrivalTime}</td>
					</tr>
				</c:forEach>
				
			</table>
			<h4>
				Return to Search <a href="/FlyAway/start/">here</a>
			</h4>
		</div>
</body>
</html>
