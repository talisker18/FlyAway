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

		<form action="SearchFlightsServlet" method="post">
        	<label for="from">From:</label>
        	<select name="from" id="from">
	        	<c:forEach items="${list}" var="item">
	        		<option value="${item}">${item}</option>
				</c:forEach>
			</select>
			<br><br>
			
			<label for="to">To:</label>
			<select name="to" id="to">
    			<c:forEach items="${list}" var="item">
	        		<option value="${item}">${item}</option>
				</c:forEach>
			</select>
			<br>
			
			<br>
			<label for="date">Date:</label>
			<input type="text" id="datepicker" name="date">
			
			<br><br>
			
			<input type="submit" value="Submit">
        </form>
</body>
</html>
