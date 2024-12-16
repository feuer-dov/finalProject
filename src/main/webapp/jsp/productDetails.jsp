<%@ page import="model.Item" %>
<%
    Item item = (Item) request.getAttribute("item");
    if (item == null) {
        // Redirect to catalog if no item is set
        response.sendRedirect("catalog?action=list");
        return;
    }
%>
<html>
<head>
    <title><%= item.getName() %> Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
        }

        .item-details {
            max-width: 600px;
            margin: 0 auto;
        }

        .item-details h1 {
            margin-top: 0;
        }

        .item-details p {
            line-height: 1.5;
        }

        .item-details form {
            margin-top: 20px;
        }

        .item-details input[type="number"] {
            width: 50px;
            margin-right: 10px;
        }

        .item-details input[type="submit"] {
            padding: 5px 10px;
        }

        .back-link {
            display: inline-block;
            margin-bottom: 20px;
        }

        .back-link a {
            text-decoration: none;
            color: #333;
        }

        .back-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="item-details">
        <div class="back-link"><a href="catalog?action=list">&laquo; Back to Catalog</a></div>
        <h1><%= item.getName() %></h1>
        <p><strong>Price:</strong> $<%= item.getPrice() %></p>
        <p><strong>Category:</strong> <%= item.getCategoryName() %></p>
        <p><strong>Brand:</strong> <%= item.getBrand() %></p>
        <p><strong>Stock:</strong> <%= item.getStock() %></p>
        <p><%= item.getDescription() %></p>

        <!-- Form to add item with specified quantity to cart -->
        <form action="catalog" method="get">
            <input type="hidden" name="action" value="addToCart">
            <input type="hidden" name="productId" value="<%= item.getId() %>" />
            <label for="qty">Quantity:</label>
            <input type="number" id="qty" name="quantity" value="1" min="1" max="<%= item.getStock() %>">
            <input type="submit" value="Add to Cart">
        </form>
    </div>
</body>
</html>
