<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Registration</title>
</head>
<body>
        <h2>Registration</h2>
        <br>

		<form action="registrationValidation" method="POST">
			<label for="first_name">First Name:</label>
			<input type="text" id="first_name" name="first_name">
			<br><br>
			<label for="last_name">Last Name:</label>
			<input type="text" id="last_name" name="last_name">
			<br><br>
			<label for="email">Email:</label>
			<input type="text" id="email" name="email">
			<br><br>
			<label for="password">Password:</label>
			<input type="password" id="password" name="password">
			<br><br>
			
			<input type="submit" value="Submit">
        </form>
        <br><br>
        <h4>
			Return to Search <a href="/FlyAway/start/">here</a>
		</h4>
		<h4>
			Login <a href="login">here</a>
		</h4>
</body>
</html>
