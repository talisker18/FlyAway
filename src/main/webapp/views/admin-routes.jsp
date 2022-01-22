<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Routes</title>
</head>
<body>
		<div align="center">
			<h2>Routes</h2>
			<br>
			<table border="1">
				<th>Route Id</th>
				<th>Source</th>
				<th>Destination</th>
				<th>Action</th>
				<c:forEach items="${listWithRoutes}" var="route">
					<tr>
						<td>${route.routeId}</td>
						<td>${route.source}</td>
						<td>${route.destination}</td>
						<td>
							<form action="/FlyAway/start/admin/routes/deleteRoute" method="post">
								<input type="hidden" id="routeId" name="routeId" value="${route.routeId}">
								<input type="submit" value="Delete">
							</form>
						</td>
					</tr>
				</c:forEach>
				
			</table>
			<h3>Add new Route</h3>
			<form action="/FlyAway/start/admin/routes/addRoute" method="post">
				<label for="source">Source: </label>
				<input type="text" id="source" name="source">
				<br>
				<label for="destination">Destination: </label>
				<input type="text" id="destination" name="destination">
				<br>
				<input type="submit" value="Add">
			</form>
			<br>
			<h4>
				Return to Admin UI <a href="/FlyAway/start/admin">here</a>
			</h4>
		</div>
</body>
</html>
