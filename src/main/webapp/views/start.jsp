<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  </script>
<title>Welcome to Fly Away</title>
</head>
<body>

        <h2>Search a flight</h2>
        <br>

		<form action="searchFlightResults" method="get">
        	<label for="from">From:</label>
        	<select name="from" id="from">
	        	<c:forEach items="${sourceList}" var="source">
	        		<option value="${source}">${source}</option>
				</c:forEach>
			</select>
			<br><br>
			
			<label for="to">To:</label>
			<select name="to" id="to">
    			<c:forEach items="${destinationList}" var="destination">
	        		<option value="${destination}">${destination}</option>
				</c:forEach>
			</select>
			<br>
			
			<br>
			<label for="date">Date:</label>
			<input type="text" id="datepicker" name="date">
			
			<br><br>
			
			<input type="submit" value="Submit">
        </form>
        <br><br>
		
		<c:choose>
		    <c:when test="${sessionScope.isUserLoggedIn == 'true'}">
		    	<form action="logout" method="post">
					<input type="submit" value="Logout" style="background-color: #f44336;">
				</form>
				<h4>
					See your bookings <a href="showBookings">here</a>
				</h4>
		    </c:when>    
		    <c:otherwise>
			    <h4>
					Register <a href="registration">here</a>
				</h4>
				<br>
				<form action="login" method="post">
					<input type="hidden" id="loginFrom" name="loginFrom" value="fromSearchForm">
					<input type="submit" value="Login" style="background-color: #4CAF50;">
				</form>
		    </c:otherwise>
		</c:choose>
</body>
</html>
