<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout</title>
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/shopping-cart.css">
<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="css/checkout.css">
</head>
<body>
	<div class="outer-container">
		<jsp:include page="header.jsp" />
		<div class='login-container'>
		<c:choose>
			<c:when test="${requestScope.cardFailed == 1}">
				<p class="error-message">Credit Card Authorization Failed, Please Try Again</p>
			</c:when>
		</c:choose>
		<p>Checkout</p>
			<p>
				Total Price: $<fmt:formatNumber value="${cart.total}" pattern="#0.00" />
			</p>
			
			<form method="get" action="OrderConfirmation">
	            <div class="input-field">
	                <label for="shippingAddress">Shipping Address:</label>
	                <input type="text" name="shippingAddress" id="shippingAddress" value="${sessionScope.def_shipping }" required>
	            </div>
	
	            <div class="input-field">
	                <label for="billingAddress">Billing Address:</label>
	                <input type="text" name="billingAddress" id="billingAddress" value="${sessionScope.def_billing }" required>
	            </div>
	            
	            <div class="input-field">
	                <label for="creditCardNumber">Credit Card Number</label>
	                <input type="number" name="creditCardNumber" id="creditCardNumber" value="${sessionScope.def_creditCard }" required>
	            </div>
	            
	            <div class="input-field">
	                <label for="cvv">CVV</label>
	                <input type="number" name="cvv" id="cvv" required>
	            </div>
	
	            <div class="login-button">
	                <input type="submit" value="Make Payment">
	            </div>
	            
	        </form>

		</div>
		<br> <a href="/final/home">Keep Shopping</a>

	</div>

</body>
</html>