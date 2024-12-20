<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/login.css">
    
</head>
<body>
	<div class="outer-container">
		<jsp:include page="header.jsp" />
	    <div class="login-container">
	        <c:choose>
	            <c:when test="${requestScope.returnValue == 1}">
	                <p class="success-message">Account Created Successfully</p>
	            </c:when>
	            <c:when test="${requestScope.returnValue == 2}">
	                <p class="error-message">Username is already in use. Please Try Again</p>
	            </c:when>
	            <c:when test="${requestScope.returnValue == 3}">
	                <p class="error-message">Error Creating Account. Please Try Again</p>
	            </c:when>
	            <c:when test="${requestScope.returnValue == 4}">
	                <p class="error-message">Login Failed. Please Try Again</p>
	            </c:when>
	            <c:otherwise>
	                <p class="welcome-message">Welcome!</p>
	            </c:otherwise>
	        </c:choose>
	
	        <h2>Login:</h2>
	        <form method="post" action="AttemptLogin">
	            <div class="input-field">
	                <label for="username">Username:</label>
	                <input type="text" name="username" id="username" required>
	            </div>
	
	            <div class="input-field">
	                <label for="password">Password:</label>
	                <input type="password" name="password" id="password" required>
	            </div>
	
	            <div class="login-button">
	                <input type="submit" value="Login">
	            </div>
	            
	        </form>
	
	        <p class="create-account">
	            Don't have an account? 
	            <a href="RegisterUser">Create one</a>
	        </p>
	    </div>
    </div>
</body>
</html>