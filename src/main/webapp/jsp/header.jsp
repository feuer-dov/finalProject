<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class='header-container'>

	<form action='home' method='get' class='logo-container'>
		<input type='image' src="images/amaze-on-logo.png" alt="Amaze On" />
	</form>
	<div class='search-container'>
		<form action="home" method="get">
			<input type="text" name="searchQuery" placeholder="Search..." /> 
			<input type="submit" value="Search" />
		</form>
	</div>
	<div class='right-buttons'>
		<form action='ShoppingCart' method='get'>
			<input type='submit' value='View Cart' /> <input type='hidden'
				name='action' value='view' />
		</form>
		<c:choose>
			<c:when test="${account != null }">
				<form action='TempLogin' method='get'>
					<input type='submit' value='Account Info' />
				</form>
			</c:when>
			<c:otherwise>
				<form action='TempLogin' method='get'>
					<input type='submit' value='Log In' />
				</form>
			</c:otherwise>
		</c:choose>

	</div>



</div>