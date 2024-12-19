<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/sidebar.css">
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/home-page.css">

</head>
<body>
	<div class='outer-container'>
		<jsp:include page='header.jsp' />

		<div class='horiz-container'>
			<jsp:include page='sidebar.jsp' />

			<div class='item-grid'>

				<c:forEach var="item" items="${itemsToDisplay}">
					<div class='item-view'>
						<h2>${item.name}</h2>
						<p>${item.description}</p>
						<p>${item.brand}</p>
						<p>
							$<fmt:formatNumber value="${item.price}" pattern="#0.00" />
						</p>
						<p>Stock: ${item.stock}</p>
						<form action="itemDetails" method="post">
							<input type='hidden' name='itemId' value=${item.id } /> <input
								type='submit' value="Select Item" />
						</form>
					</div>
				</c:forEach>

			</div>

		</div>
	</div>

</body>
</html>