<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Purchase History</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/user-history.css">

</head>
<body>
	<div class='outer-container'>
		<jsp:include page='header.jsp' />
		<div class='sale-container'>
			<table>
				<tr>
					<th>Transaction ID</th>
					<th>Total</th>
					<th>Shipping Address</th>
					<th>Credit Card</th>
				</tr>
				<c:forEach items='${sales }' var='s'>
					<tr>
						<td>${s.transactionId }</td>
						<td>${s.total }</td>
						<td>${s.shippingAddress }</td>
						<td>${s.CCNumber }</td>
					</tr>
				</c:forEach>
			</table>
		
		</div>
		<form class='back' action='AccountInfo' >
			<input type='hidden' name='action' value='view'/>
			<input type='submit' value='Back' />
		</form>
		
	</div>
	

</body>
</html>