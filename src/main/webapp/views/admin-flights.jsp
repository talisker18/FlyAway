<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Flights</title>
</head>
<body>
		<div align="center">
			<h2>Flights</h2>
			<br>
			<table border="1">
				<th>Flight Id</th>
				<th>Price in $</th>
				<th>Available seats</th>
				<th>Departure time</th>
				<th>Arrival time</th>
				<th>Route</th>
				<th>Action</th>
				<c:forEach items="${listWithFlights}" var="flight">
					<tr>
						<td>${flight.flightNumber}</td>
						<td>${flight.price}</td>
						<td>${flight.availableSeats}</td>
						<td>${flight.departureTime}</td>
						<td>${flight.arrivalTime}</td>
						<td>${flight.route.source} to ${flight.route.destination}</td>
						<td>
							<form action="/FlyAway/start/admin/flights/deleteFlight" method="post">
								<input type="hidden" id="flightNumber" name="flightNumber" value="${flight.flightNumber}">
								<input type="submit" value="Delete">
							</form>
						</td>
					</tr>
				</c:forEach>
				
			</table>
			<h3>Add new Flight</h3>
			<form action="/FlyAway/start/admin/flights/addFlight" method="post">
				<label for="route">Route: </label>
	        	<select name="route" id="route">
		        	<c:forEach items="${listWithRoutes}" var="route">
		        		<option value="${route.routeId}">${route.source} to ${route.destination}</option>
					</c:forEach>
				</select>
				<br>
				<label for="price">Price: </label>
				<input type="text" name="price" id="price">
				<br>
				<label for="availableSeats">Avaliable seats: </label>
				<input type="text" name="availableSeats" id="availableSeats">
				<br>
				<label for="departureTime">Departure time: </label>
				<input type="datetime-local" id="departureTime" name="departureTime" value="2022-01-20T19:30">
				<br>
				<label for="arrivalTime">Arrival time: </label>
				<input type="datetime-local" id="arrivalTime" name="arrivalTime" value="2022-01-20T19:30">
				<br><br>		
				<input type="submit" value="Submit">
			</form>
			<br>
			<h4>
				Return to Admin UI <a href="/FlyAway/start/admin">here</a>
			</h4>
		</div>
</body>
</html>
