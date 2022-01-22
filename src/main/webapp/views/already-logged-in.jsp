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
		<h3 style="color:green;">You are already logged in with email ${sessionScope.email}</h3>
        <br><br>
        
        <c:choose>
		    <c:when test="${sessionScope.role == 'admin'}">
		    	<h4>
					Return to Admin UI <a href="/FlyAway/start/admin">here</a>
				</h4>
		    </c:when>    
		    <c:otherwise>
				<h4>
					Return to Search <a href="/FlyAway/start/">here</a>
				</h4>
		    </c:otherwise>
		</c:choose>

		<form action="logout" method="post">
			<input type="submit" value="Logout" style="background-color: #f44336;">
		</form>
</body>
</html>
