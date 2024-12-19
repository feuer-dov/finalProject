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
			<div class="item-image">
                <img src="https://via.placeholder.com/300" alt="${item.name}" />
            </div>
            <div class="item-info">
            	<h2>${item.name }</h2>
            	<p class="price">$<fmt:formatNumber value="${item.price}" pattern="#0.00" /></p>
                <p><strong>Brand:</strong> ${item.brand}</p>
                <p><strong>Category:</strong> ${item.categoryName}</p>
                <p><strong>About this item:</strong> ${item.description}</p>
                

                <!-- Add to Cart Form -->
                <form action="ShoppingCart" method="get">
                    <label for="quantity">Quantity:</label>
                    <select name="qty" id="quantity">
                        <c:forEach var="i" begin="1" end="${item.stock}">
                            <option value="${i}">${i}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="action" value="add" />
                    <input type="hidden" name="itemId" value="${item.id}" />
                    <input type="submit" value="Add to Cart" />
                </form>
            </div>
			
		</div>
	</div>

</body>
</html>