<%@ page language="java" contentType='text/html; charset=UTF-8' pageEncoding="UTF-8"%>
<html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

	<head>
		<title>Order Confirmation Page</title>
		<link rel="stylesheet" href="css/common.css">
		<link rel="stylesheet" href="css/header.css">
		<link rel="stylesheet" href="css/order-confirmation.css">
	</head>
	<body>
	<div class='outer-container' >
		<jsp:include page="header.jsp" />
		<div class='summary-display'>
			<h1>Thank you for ordering. </h1>
			<p>Billing: <b>${param.billingAddress } </b> </p>
			<p>Shipping: <b>${param.shippingAddress } </b>  </p>
			<p>Credit Card: <b>${param.creditCardNumber } </b> </p>
			<p>Total: <b>$<fmt:formatNumber value="${total}" pattern="#0.00" /></b>  </p>
	
			<p><a href='home'>Back to Shopping</a></p>
		</div>
	</div>
		
	</body>
</html>