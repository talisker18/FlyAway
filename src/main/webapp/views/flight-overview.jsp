<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Overview of available Flights</title>
</head>
<body>
		<div align="center">
			<h2>Overview of available Flights (ordered by departure time)</h2>
			<br>
			<table border="1">
				<th>Flight No</th>
				<th>Price in $</th>
				<th>Available Seats</th>
				<th>Departure Time</th>
				<th>Arrival Time</th>
				<th>Source</th>
				<th>Destination</th>
				<th>Action</th>
				
				<c:choose>
				    <c:when test="${sessionScope.isUserLoggedIn == 'true'}">
					    <c:forEach items="${sessionScope.listWithFlights}" var="flight">
							<tr>
								<td>${flight.flightNumber}</td>
								<td>${flight.price}</td>
								<td>${flight.availableSeats}</td>
								<td>${flight.departureTime}</td>
								<td>${flight.arrivalTime}</td>
								<td>${flight.source}</td>
								<td>${flight.destination}</td>
								<td>
									<form action="searchFlightResults/book" method="post">
										<input type="hidden" id="flightNumber" name="flightNumber" value="${flight.flightNumber}">
										<input type="submit" value="Book">
									</form>
								</td>
							</tr>
						</c:forEach>
				    </c:when>    
				    <c:otherwise>
				    	<c:forEach items="${sessionScope.listWithFlights}" var="flight">
							<tr>
								<td>${flight.flightNumber}</td>
								<td>${flight.price}</td>
								<td>${flight.availableSeats}</td>
								<td>${flight.departureTime}</td>
								<td>${flight.arrivalTime}</td>
								<td>${flight.source}</td>
								<td>${flight.destination}</td>
								<td>Login for booking!</td>
							</tr>
						</c:forEach>
				    </c:otherwise>
				</c:choose>
				
			</table>
			<h4>
				Return to Search <a href="/FlyAway/start/">here</a>
			</h4>
			
			<c:choose>
			    <c:when test="${sessionScope.isUserLoggedIn == 'true'}">
			    	<form action="logout" method="post">
						<input type="submit" value="Logout" style="background-color: #f44336;">
					</form>
			    </c:when>    
			    <c:otherwise>
				    <h4>
						Register <a href="registration">here</a>
					</h4>
					<br>
					<form action="login" method="post">
						<input type="hidden" id="loginFrom" name="loginFrom" value="fromFlightOverview">
						<input type="submit" value="Login" style="background-color: #4CAF50;">
					</form>
			    </c:otherwise>
			</c:choose>
		</div>
</body>
</html>
