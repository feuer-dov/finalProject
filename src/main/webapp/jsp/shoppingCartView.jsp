<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping Cart</title>

</head>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Shopping Cart</title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/shopping-cart.css">
</head>
<body>
	<div class="outer-container">
		<jsp:include page="header.jsp" />
	    <h1>Shopping Cart</h1>
	
		<c:choose>
			<c:when test="${requestScope.cartError == 1}">
				<p class="error-message">Credit Card Authorization Failed, Please Try Again</p>
			</c:when>
		</c:choose>
	
	    <c:if test="${cart != null and cart.isEmpty() == false}">
	        <div class="cart-items-container">
	            <c:forEach items="${cart.items}" var="item">
	                <div class='cart-item-view'>
	                    <div class="item-image">
	                        <img src="https://via.placeholder.com/300" alt="${item.name}" />
	                    </div>
	                    <div class="item-details">
	                        <h2>${item.name}</h2>
	                        <p>${item.description}</p>
	                        <p>Brand: ${item.brand}</p>
	                        <p>Price: $<fmt:formatNumber value="${item.price}" pattern="#0.00" /></p>
	                    </div>
	
	                    <form method="GET">
	                        <select name="qty" id="quantity" onchange="this.form.submit()">
	                            <c:forEach var="i" begin="1" end="${item.stock}">
	                                <option value="${i}" <c:if test="${item.qtyOrdered == i }">selected</c:if>>${i}</option>
	                            </c:forEach>
	                        </select>
	                        <input type="hidden" name="itemId" value="${item.id}" />
	                        <input type="hidden" name="action" value="updateQty" />
	                    </form>
	
	             
	                    <form method="GET">
	                        <input type="hidden" name="itemId" value="${item.id}" />
	                        <input type="hidden" name="action" value="remove" />
	                        <input type="submit" value="Remove" />
	                    </form>
	                </div>
	            </c:forEach>
	        </div>
			<div class='summary-container'>
			
	        <div class="total-price">
	            <p>Total Price: $<fmt:formatNumber value="${cart.total}" pattern="#0.00" /></p>
	        </div>
	        
	    	<form action="CheckoutServlet" method="GET">
	        	<input type="submit" value="Go to Checkout" />
	    	</form>
	    	
	    	</div>
	    </c:if>
	
	    <c:if test="${cart == null or cart.isEmpty()}">
	        <h2>Your cart is empty!</h2>
	    </c:if>
	
	   
	
	    <br>
	    <a href="/final/home">Keep Shopping</a>
	    
    </div>
</body>
</html>