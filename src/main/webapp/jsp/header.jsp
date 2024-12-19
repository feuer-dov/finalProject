<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class='header-container'>

	<form action='home' method='get' class='logo-container'>
		<input type='image' src="images/amaze-on-logo.jpg" alt="Amaze On" />
	</form>
	<form action="home" method="get">
        <input type="text" name="query" placeholder="Search..." />
        <input type="submit" value="Search" />
    </form>
	<form action='ShoppingCart' method='get'>
		<input type='submit' value='View Cart' />
		<input type='hidden' name='action' value='view' />
	</form>
	<form action='jsp/LoginPage.jsp' method='get'>
		<input type='submit' value='Log In' />
	</form>
	

</div>