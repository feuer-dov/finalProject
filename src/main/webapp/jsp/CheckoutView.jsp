<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shipping and Billing Information</title>
</head>
<body>
	<%@ page import="controller.*" %>
	<%@ page import="java.util.Random;" %>
	
	<%-- Random alg for denying credit card, 50/50 chance --%>
	<%	boolean check = (boolean) session.getAttribute("displayCreditFail");
		
		if (check) {%>
	<h2> Credit Card Authorization Failed, Please Try Again</h2>
	<% } %>
	<h3>Enter Your Shipping and Billing Information. </h3>
	
	<p> 
	<%-- <% 	Random random = new Random();
		check = random.nextBoolean();
	    session.setAttribute("displayCreditFail", check);
		if (check) { %>
	<form action="CheckoutView.jsp"> 
	<%} else { %>
		<form action="OrderConfirmation" method="post"> 
	<% } % --%>
	
	<form action="OrderConfirmation" method="post"> 
	
	<%-- Enter Purchase Info --%>
	Enter your Billing: 
	<input type='text' name='billing' value='${sessionScope.def_billing }' /><br>
	Enter your Shipping: 
	<input type='text' name='shipping' value='${sessionScope.def_shipping }' /><br>
	Enter your Credit Card Number: 
	<input type='text' name='card' value='${sessionScope.def_creditCard }'/><br>
	Enter your CVV: 
	<input type='text' name='cvv' /><br>
	
	<input type='submit' value='Confirm Order' /> 
	
	</form>
	</p>
</body>
</html>