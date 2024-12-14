<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shipping and Billing Information</title>
</head>
<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ page import="controller.*" %>
	<%@ page import="java.util.Random;" %>
	
	<%-- Random alg for denying credit card, 50/50 chance --%>
	<% Random random = new Random();
		boolean r = random.nextBoolean();
		if (r) {%>
	<h2> Credit Card Authorization Failed, Please Try Again</h2>
	<% } %>
	<h3>Enter Your Shipping and Billing Information. </h3>
	
	<p> 
	<% if (r) { %>
	<form action="CheckoutView.jsp"> 
	<%} else { %>
		<form action="OrderConfirmation" method="post"> 
	<% } %>
	Enter your Billing: 
	<input type='text' name='billing' value='${sessionScope.def_billing }' /><br>
	Enter your Shipping: 
	<input type='text' name='shipping' value='${sessionScope.def_shipping }' /><br>
	Enter your Shipping: 
	<input type='text' name='card' /><br>
	Enter your Shipping: 
	<input type='text' name='csv' /><br>
	
	<input type='submit' value='Confirm Order' /> 
		
</body>
</html>