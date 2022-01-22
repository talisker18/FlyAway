<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Login</title>
</head>
<body>
        <h2>Login</h2>
        <br>

		<form action="loginValidation" method="POST">
			<label for="email">Email:</label>
			<input type="text" id="email" name="email">
			<br><br>
			<label for="password">Password:</label>
			<input type="password" id="password" name="password">
			<br><br>
			
			<input type="hidden" id="loginFrom" name="loginFrom" value="${loginFrom}">
			<input type="submit" value="Submit" style="background-color: #4CAF50;">
        </form>
        <br><br>
        <h4>
			Return to Search <a href="/FlyAway/start/">here</a>
		</h4>
		<h4>
			Register <a href="registration">here</a>
		</h4>
</body>
</html>
