<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create an Account</title>
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/login.css">
</head>

<body>
	<div class='outer-container'>
		<jsp:include page="header.jsp" />
		<div class='login-container'>
			<h2>Create an Account</h2>
			<form method="post" action="CreateAccountServlet">
    <div class="input-field">
        <label for="name">Name:</label>
        <input type="text" name="name" id="name" required>
    </div>

    <div class="input-field">
        <label for="username">Username:</label>
        <input type="text" name="username" id="username" required>
    </div>

    <div class="input-field">
        <label for="password">Password:</label>
        <input type="password" name="password" id="password" required>
    </div>

    <div class="input-field">
        <label for="CC">Credit Card Info:</label>
        <input type="text" name="CC" id="CC" required>
    </div>

    <div class="input-field">
        <label for="ship">Shipping Address:</label>
        <input type="text" name="ship" id="ship" required>
    </div>

    <div class="input-field">
        <label for="billing">Billing Address:</label>
        <input type="text" name="billing" id="billing" required>
    </div>

    <div class="login-button">
        <input type="submit" value="Create Account">
    </div>
</form>
		</div>
	</div>
</body>
</html>

