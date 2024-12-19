<%@ page import="java.util.List" %>
<%@ page import="model.Cart" %>
<%@ page import="model.Item" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<%
    // Retrieve the cart from session
    Cart cart = (Cart) session.getAttribute("cart");
    if (cart == null || cart.getItems().isEmpty()) {
%>
    <p>Your cart is empty.</p>
    <a href="catalog?action=list">Continue Shopping</a>
<%
    } else {
        List<Item> items = cart.getItems();
        double total = 0; // Initialize total cost
%>
    <h1>Your Shopping Cart</h1>
    <table border="1">
        <tr>
            <th>Product</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Subtotal</th>
        </tr>
    <%
        // Iterate through the list of items in the cart
        for (Item item : items) {
            int quantity = item.getQtyOrdered();
            double price = item.getPrice();
            double subtotal = price * quantity;
            total += subtotal; // Add to total
    %>
        <tr>
            <td><%= item.getName() %></td>
            <td><%= quantity %></td>
            <td>$<%= price %></td>
            <td>$<%= String.format("%.2f", subtotal) %></td>
        </tr>
    <%
        }
    %>
        <tr>
            <td colspan="3" style="text-align:right;"><strong>Total</strong></td>
            <td><strong>$<%= String.format("%.2f", total) %></strong></td>
        </tr>
	<table>
    <a href="/final/catalog?action=list">Continue Shopping</a>
	<%
    }
	%>
