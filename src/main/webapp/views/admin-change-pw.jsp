<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Change password</title>
</head>
<body>

		<form action="/FlyAway/start/admin/changePassword/checkCurrentPassword" method="post">
			<label for="currentPassword">Enter your current password: </label>
			<input type="password" id="currentPassword" name="currentPassword">
			<br>
			<label for="newPassword">Enter your new password: </label>
			<input type="password" id="newPassword" name="newPassword">
			<br>
			<input type="submit" value="Submit">
		</form>
        <h4>
			Return to Admin UI <a href="/FlyAway/start/admin">here</a>
		</h4>
</body>
</html>
