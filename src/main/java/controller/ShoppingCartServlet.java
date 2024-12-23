package controller;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.Item;


/**
 * Servlet implementation class ShoppingCartServlet
 */
@WebServlet("/ShoppingCart")
public class ShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		Cart cart;
   	      synchronized (session) {  // synchronized to prevent concurrent updates
   	         // Retrieve the shopping cart for this session, if any. Otherwise, create one.
   	         cart = (Cart) session.getAttribute("cart");
   	         if (cart == null) {  // No cart, create one.
   	            cart = new Cart();
   	        
   	           
   	            session.setAttribute("cart", cart);  // Save it into session
   	         }
   	      }
	    
   	    Database db = new Database(getServletContext());
   	    
		try {
			String action = request.getParameter("action");
			String itemIDString = request.getParameter("itemId");
			int itemID = Integer.parseInt(itemIDString);
			
	   	  
			if(action.equals("add")) {
				int qty = Integer.parseInt(request.getParameter("qty"));
				Item item = db.getItem(itemID);
				item.setQtyOrdered(qty);
				cart.addItem(item);
			}
			if(action.equals("updateQty")) {
				int qty = Integer.parseInt(request.getParameter("qty"));
				cart.setItemQty(itemID, qty);
			}
			
			if(action.equals("remove")) {
				System.out.println("Remove item!!!!!");
				// Entire item removed from cart instance
				cart.removeItem(itemID);
			}
		}
		catch(Exception e) {
			System.out.println("Exception caught in Shopping Cart");
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/shoppingCartView.jsp");
		rd.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
