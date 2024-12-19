<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create an Account</title>
</head>
<body>
	<h2>Create an Account</h2>
	<form method='get' action='/final/CreateAccountServlet'>
		<p>Name: <input type='text' name='name'></p>
		<p>Username: <input type='text' name='username'></p>
		<p>Password: <input type='password' name='password'></p>
		<p>Credit Card Info: <input type='text' name='CC'></p>
		<p>Shipping Address: <input type='text' name='ship'></p>
		<p>Billing Address: <input type='text' name='billing'></p>
		<input type='Submit' value='Create Account'>
	</form>
</body>
</html>

