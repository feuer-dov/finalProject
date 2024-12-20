<%@ page language="java" contentType='text/html; charset=UTF-8' pageEncoding="UTF-8"%>
<html>
	<head>
		<title>Order Confirmation Page</title>
		<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
	</head>
	<body>
		<h1>Thank you for Orderings. </h1>
		
		<p>Billing: <b>${param.billing } </b> </p>
		<p>Shipping: <b>${param.shipping } </b>  </p>
		<p>Credit Card: <b>${param.card } </b> </p>
		<p>Total: <b>${param.total }</b>  </p>

		<p><a href='home'>Back to Shopping</a></p>
	</body>
</html>