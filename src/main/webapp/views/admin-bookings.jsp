<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Bookings</title>
</head>
<body>
		<div align="center">
			<h2>Bookings</h2>
			<br>
			<table border="1">
				<th>Booking Id</th>
				<th>User Id</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Flight Id</th>
				<th>Price in $</th>
				<th>Available seats</th>
				<th>Departure time</th>
				<th>Arrival time</th>
				<th>Route Id</th>
				<th>Source</th>
				<th>Destination</th>
				<th>Action</th>
				<c:forEach items="${listWithBookings}" var="booking">
					<tr>
						<td>${booking.bookingId}</td>
						<td>${booking.userId}</td>
						<td>${booking.firstName}</td>
						<td>${booking.lastName}</td>
						<td>${booking.email}</td>
						<td>${booking.flightId}</td>
						<td>${booking.price}</td>
						<td>${booking.availableSeats}</td>
						<td>${booking.departureTime}</td>
						<td>${booking.arrivalTime}</td>
						<td>${booking.routeId}</td>
						<td>${booking.source}</td>
						<td>${booking.destination}</td>
						<td>
							<form action="/FlyAway/start/admin/bookings/deleteBooking" method="post">
								<input type="hidden" id="bookingId" name="bookingId" value="${booking.bookingId}">
								<input type="hidden" id="flightId" name="flightId" value="${booking.flightId}">
								<input type="submit" value="Delete">
							</form>
						</td>
					</tr>
				</c:forEach>
				
			</table>
			<br>
			<h4>
				Return to Admin UI <a href="/FlyAway/start/admin">here</a>
			</h4>
		</div>
</body>
</html>
