<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Booking details</title>
</head>
<body>
		<div align="center">
			<h2>Booking details</h2>
			
			<p>Flight No: ${flight.flightNumber}</p>
			<p>Price: ${flight.price}</p>
			<p>Departure Time: ${flight.departureTime}</p>
			<p>Arrival Time: ${flight.arrivalTime}</p>
			<p>Source: ${flight.source}</p>
			<p>Destination: ${flight.destination}</p>
			
			<form action="/FlyAway/start/searchFlightResults/book/bookingConfirmation" method="post">
				<input type="hidden" id="flightNumber" name="flightNumber" value="${flight.flightNumber}">
				<input type="submit" value="Confirm booking!">
			</form>
			
			<h4>
				Return to Search <a href="/FlyAway/start/">here</a>
			</h4>
			
			<form action="/FlyAway/start/logout" method="post">
				<input type="submit" value="Logout" style="background-color: #f44336;">
			</form>
		</div>
</body>
</html>
