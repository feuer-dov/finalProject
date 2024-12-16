package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.Item;

@WebServlet("/catalog")
public class CatalogServlet extends HttpServlet {
    private Database database;

    @Override
    public void init() throws ServletException {
        try {
            // Initialize the Database instance with ServletContext
            this.database = new Database(getServletContext());
            System.out.println("Database initialized successfully.");
        } catch (Exception e) {
            System.err.println("Error initializing database: " + e.getMessage());
            e.printStackTrace();
            throw new ServletException("Database initialization failed.", e);
        }
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Determine the action to perform
        String action = request.getParameter("action");

        if (action == null || action.equals("list")) {
            // Default action: list items
            listItems(request, response);
        } else if (action.equals("details")) {
            // Show item details
            showItemDetails(request, response);
        } else if (action.equals("addToCart")) {
            // Add item to cart
            addToCart(request, response);
        } else {
            // Unknown action, redirect to catalog
            response.sendRedirect("catalog?action=list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle form submissions via POST
        doGet(request, response);
    }

    private void listItems(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchQuery = request.getParameter("searchQuery");
        String categoryName = request.getParameter("categoryName");
        String brandName = request.getParameter("brandName");

        List<Item> items;

        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            // If there's a search query, filter items by the query
            // In the original code, search was by product name.
            // We assume database.getItemsByName(...) does a similar function.
            items = database.getItemsByName(searchQuery);
            if (items == null) {
                items = new ArrayList<>();
            }
        } else if (categoryName != null && !categoryName.trim().isEmpty()) {
            // Filter by category
            items = database.getAllItemsByCat(categoryName);
            if (items == null) {
                items = new ArrayList<>();
            }
        } else if (brandName != null && !brandName.trim().isEmpty()) {
            // Filter by brand
            items = database.getItemsByBrand(brandName);
            if (items == null) {
                items = new ArrayList<>();
            }
        } else {
            // Otherwise, get all items
            items = database.getAllItems();
            if (items == null) {
                items = new ArrayList<>();
            }
        }

        // Get all categories and brands
        List<String> categories = database.getAllCatNames();
        if (categories == null) {
            categories = new ArrayList<>();
        }
        List<String> brands = database.getAllBrandNames();
        if (brands == null) {
            brands = new ArrayList<>();
        }

        request.setAttribute("items", items);
        request.setAttribute("categories", categories);
        request.setAttribute("brands", brands);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/catalog.jsp");
        dispatcher.forward(request, response);
    }

    private void showItemDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productIdStr = request.getParameter("productId");

        if (productIdStr == null) {
            response.sendRedirect("catalog?action=list");
            return;
        }

        try {
            int productId = Integer.parseInt(productIdStr);
            Item item = database.getItem(productId);

            if (item == null) {
                response.sendRedirect("catalog?action=list");
                return;
            }

            request.setAttribute("item", item);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/productDetails.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            throw new ServletException("Error retrieving item details", e);
        }
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response)
    	    throws IOException, ServletException {
    	    String productIdStr = request.getParameter("productId");
    	    String quantityStr = request.getParameter("quantity");

    	    if (productIdStr == null) {
    	        response.sendRedirect("catalog?action=list");
    	        return;
    	    }

    	    try {
    	        int productId = Integer.parseInt(productIdStr);
    	        int quantity = 1;
    	        if (quantityStr != null && !quantityStr.isEmpty()) {
    	            quantity = Integer.parseInt(quantityStr);
    	        }

    	        Item item = database.getItem(productId);
    	        if (item == null) {
    	            response.sendRedirect("catalog?action=list");
    	            return;
    	        }

    	        // Set the ordered quantity
    	        item.setQtyOrdered(quantity);

    	        HttpSession session = request.getSession();
    	        Cart cart = (Cart) session.getAttribute("cart");
    	        if (cart == null) {
    	            cart = new Cart();
    	            session.setAttribute("cart", cart);
    	        }

    	        // Add item to cart
    	        cart.addItem(item);

    	        // Redirect to cart page
    	        response.sendRedirect("/jsp/cart.jsp");
    	    } catch (NumberFormatException e) {
    	        throw new ServletException("Invalid product or quantity", e);
    	    }
    	}

    }

