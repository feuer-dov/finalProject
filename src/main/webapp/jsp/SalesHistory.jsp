<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sales History</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
</head>
<body>

	<h2>Sales History</h2>
	
	<table>
		<tr>
			<th>Transaction Id</th>
			<th>Customer</th>
			<th>Items</th>
			<th>Total</th>
			<th>Shipping Address</th>
			<th>CreditCard Info</th>
		</tr>
		
		<c:forEach items="${sales }" var="s">
			<tr>
				<td>${s.transactionId }</td>
				<td>${s.custName }</td>
				<td>
					<ul>
					<c:forEach var="i" begin="0" end="${fn:length(s.itemId) - 1}">
						<c:set var="id" value="${s.itemId[i] }" />
						<c:set var="qty" value="${s.quantity[i] }" />
					
						<li>${id } with Qty Ordered: ${qty }</li>
					</c:forEach>
					</ul>
				</td>
				<td>${s.total }</td>
				<td>${s.shippingAddress }</td>
				<td>${s.CCNumber }</td>
			</tr>
		</c:forEach>
	</table>
	<form method='get' action='SalesHistory'>
		<p>Filter by Username:	<input type='text' name='filterName'><input type='submit' value='Filter'></p>
	</form>
	
	<form method='get' action='AdminView'>
		<input type='hidden' name='username' value='${username }'>
		<input type='submit' value='Return to Admin Menu'>
	</form>
</body>
</html>