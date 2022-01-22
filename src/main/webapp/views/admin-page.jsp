<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Admin UI</title>
</head>
<body>
        <h2>Admin UI</h2>
        
        <c:choose>
		    <c:when test="${sessionScope.isUserLoggedIn == 'true'}">
		    	<h4>
					Go to routes <a href="/FlyAway/start/admin/routes">here</a>
				</h4>
				<h4>
					Go to flights <a href="/FlyAway/start/admin/flights">here</a>
				</h4>
				<h4>
					Go to bookings <a href="/FlyAway/start/admin/bookings">here</a>
				</h4>
				<br>
				<h4>
					Change Admin password <a href="/FlyAway/start/admin/changePassword">here</a>
				</h4>
				<br>
		    	<form action="logout" method="post">
					<input type="submit" value="Logout" style="background-color: #f44336;">
				</form>
		    </c:when>    
		    <c:otherwise>
				<form action="login" method="post">
					<input type="hidden" id="loginFrom" name="loginFrom" value="fromSearchForm">
					<input type="submit" value="Login" style="background-color: #4CAF50;">
				</form>
		    </c:otherwise>
		</c:choose>
</body>
</html>
