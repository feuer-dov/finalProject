<%@ page language="java" contentType='text/html; charset=UTF-8' pageEncoding="UTF-8"%>
<html>
	<head>
		<title>Checkout</title>
		<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
	</head>
	<body>
		<c:choose>
			<c:when test="${requestScope.cardFailed == 1}">
				<p>Credit Card Authorization Failed, Please Try Again</p>
			</c:when>
			<c:otherwise>
				<p>Checkout</p>
			</c:otherwise>
		</c:choose>
		<h2>Enter Your Shipping and Billing Information. </h2>
		<form method='post' action='/final/OrderConfirmation'>
			<%-- Enter Purchase Info --%>
			Enter your Billing: 
			<input type='text' name='billing' value='${sessionScope.def_billing }' /><br>
			Enter your Shipping: 
			<input type='text' name='shipping' value='${sessionScope.def_shipping }' /><br>
			Enter your Credit Card Number: 
			<input type='text' name='card' value='${sessionScope.def_creditCard }'/><br>
			Enter your CVV: 
			<input type='text' name='cvv' /><br>
			<input type='submit' value='Confirm'>
		</form>
		<a href='/final/ShoppingCart'>Back To Cart</a>
	</body>
</html>