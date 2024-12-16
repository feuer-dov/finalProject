<%@ page import="java.util.List" %>
<%@ page import="model.Item" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.*" %>
<%
    // Since we refactored from Product/Category/Brand to Item with category & brand as strings,
    // we assume the request attributes have changed accordingly. 
    // Adjust your controller to set attributes "items", "categories", and "brands" as Lists of Strings.

    List<Item> items = (List<Item>) request.getAttribute("items");
    List<String> categories = (List<String>) request.getAttribute("categories");
    List<String> brands = (List<String>) request.getAttribute("brands");
%>
<html>
<head>
    <title>Product Catalog</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #333;
            padding: 10px;
            color: #fff;
        }

        header a {
            color: #fff;
            text-decoration: none;
            margin-left: 10px;
        }

        header a:hover {
            text-decoration: underline;
        }

        .container {
            display: flex; /* Enable flexbox layout */
            flex-direction: row;
            margin: 0;
            padding: 0;
        }

        .sidebar {
            width: 200px; /* Adjust as needed */
            background-color: #f2f2f2;
            padding: 10px;
            box-sizing: border-box;
        }

        .sidebar h2 {
            margin-top: 0;
        }

        .sidebar ul {
            list-style-type: none;
            padding-left: 0;
        }

        .sidebar ul li {
            margin-bottom: 5px;
        }

        .content {
            flex: 1; /* Take the remaining space */
            padding: 10px;
            box-sizing: border-box;
        }

        h1 {
            text-align: center;
        }

        .content h2 {
            margin-top: 0;
        }

        .content ul {
            list-style-type: none;
            padding-left: 0;
        }

        .content ul li {
            margin-bottom: 10px;
        }

        .content ul li a {
            text-decoration: none;
            color: #333;
        }

        .content ul li a:hover {
            text-decoration: underline;
        }

        /* Sidebar links styling */
        .sidebar a {
            text-decoration: none;
            color: #333;
        }

        .sidebar a:hover {
            text-decoration: underline;
        }

        /* Search bar styling */
        .search-bar {
            margin-bottom: 20px;
            width: 100%;
            box-sizing: border-box;
        }

        .search-bar form {
            display: flex;
            width: 100%;
            gap: 10px; /* space between input and button */
            box-sizing: border-box;
        }

        .search-bar input[type="text"] {
            flex: 1; /* Take all remaining horizontal space */
            padding: 5px;
            box-sizing: border-box;
        }

        .search-bar input[type="submit"] {
            padding: 5px 10px;
            box-sizing: border-box;
            white-space: nowrap;
        }
    </style>
</head>
<body>
    <header>
        <div>
            <h1 style="margin:0; font-size:1.5em;">Product Catalog</h1>
        </div>
        <div>
            <!-- Add a login link or button here -->
            <a href="AttemptLogin">Login</a>
        </div>
    </header>
    
    <div class="container">
        <div class="sidebar">
            <h2>Categories</h2>
            <ul>
                <li><a href="catalog?action=list">All Products</a></li>
                <% if (categories != null) {
                     for (String cat : categories) { %>
                    <li><a href="catalog?action=list&categoryName=<%= cat %>"><%= cat %></a></li>
                <% }} %>
            </ul>

            <h2>Brands</h2>
            <ul>
                <% if (brands != null) {
                     for (String br : brands) { %>
                    <li><a href="catalog?action=list&brandName=<%= br %>"><%= br %></a></li>
                <% }} %>
            </ul>
        </div>
        <div class="content">
            <div class="search-bar">
                <form action="catalog" method="get">
                    <input type="hidden" name="action" value="list">
                    <input type="text" name="searchQuery" placeholder="Search products..." />
                    <input type="submit" value="Search" />
                </form>
            </div>

            <h2>Products</h2>
            <ul>
                <% if (items != null && !items.isEmpty()) {
                     for (Item item : items) { %>
                        <li>
                            <a href="catalog?action=details&productId=<%= item.getId() %>">
                                <%= item.getName() %>
                            </a> - $<%= item.getPrice() %>
                        </li>
                    <% }
                   } else { %>
                     <li>No products found.</li>
                <% } %>
            </ul>
        </div>
    </div>
</body>
</html>
