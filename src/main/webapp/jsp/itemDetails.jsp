<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Item Details</title>
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/sidebar.css">
<link rel="stylesheet" href="css/item-details.css">

</head>
<body>
	<div class="container">
		<jsp:include page='sidebar.jsp' />
		<div class='item-details'>
			<h2>${item.name }</h2>

			<p>${item.description }</p>
			<p>${item.categoryName}</p>
			<p>${item.brand}</p>
			<[]>$<fmt:formatNumber value="${item.price}" pattern="#0.00" /></p>

			<form action='ShoppingCart' method='get'>
				<select name="qty" id="quantity">
					<c:forEach var="i" begin="1" end="${item.stock}">
						<option value="${i}">${i}</option>
					</c:forEach>
				</select> <input type='hidden' name='action' value='add' /> <input
					type='hidden' name='itemId' value=${item.id } /> <input
					type='submit' value='Add to Cart' />

			</form>
		</div>
	</div>

</body>
</html>