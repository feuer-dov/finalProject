<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Account Info</title>
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/account-info.css">

</head>
<body>
	<div class="outer-container">
		<jsp:include page="header.jsp" />
		<form action="AccountInfo" method="get">
			
			<div class="account-container">
				<p>Username: ${account.username }<p>
				<p>Name: <input type='text' name='name'value='${account.name }'/><p>
				<p>Billing Address<input type='text' name='billingAddress' value='${account.billingAddress }'/></p>
				<p>Shipping Address<input type='text' name='shipAddress' value='${account.shipAddress }'/></p>
				<p>Credit Card Number: <input type='number' name='creditCard' value='${account.creditCard }'/></p>
			</div>
			<div class='update-button'>
					<input type='submit' value='Update Information' />
					<input type='hidden' name='action' value='updateInfo' />
					<input type='hidden' name='username' value='${account.username }' />
			</div>
		
		</form>
		<div class='logout-button'>
			<form action="AccountInfo" method="post">
				<input type='submit' value='Log Out' />
				<input type='hidden' name='action' value='logOut' />
			</form>
		</div>
	</div>
		
		

</body>
</html>