package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.Cart;
import model.Item;
import model.Sale;
import controller.Database;

/**
 * Servlet implementation class OrderConfirmation
 */
@WebServlet("/OrderConfirmation")
public class OrderConfirmationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderConfirmationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		
	    HttpSession session = request.getSession(true);
	    
	    //used when cart has more than stock
	    boolean errorCart = false;
	    
	    //credit card authorization algorithm
	    //just randomly fails half the cards
	    
	    String shipping = request.getParameter("shippingAddress");
    	String creditCard = request.getParameter("creditCardNumber");
    	
	    Random r = new Random();
	    if (r.nextBoolean() && r.nextBoolean()) {
	    	request.setAttribute("cardFailed", 1);
    		String target = "CheckoutServlet";
    		request.getRequestDispatcher(target).forward(request, response);
	    }
	    else {
	    	
	    	
	  	    Database db = new Database(request.getServletContext());
	  	    Cart cart = (Cart) session.getAttribute("cart");
	  	    
	  	    int transactionId = db.getAllSales().size();
		    double total = cart.getTotal();
		    
		    ArrayList<Integer> itemIds = new ArrayList<>();
		    ArrayList<Integer> quantity = new ArrayList<>();
		    for (Item item : cart.getItems()) {
		    	itemIds.add(item.getId());
		    	quantity.add(item.getQtyOrdered());
		    }
		    
		    Account acc = (Account) session.getAttribute("account");
		    Sale sale = new Sale(acc.getUsername(), total, itemIds, quantity, transactionId, shipping, creditCard);

		    ArrayList<Integer> newStock = new ArrayList<>();
	  		
	  		int j = 0;
	  		for (int i : sale.getItemId()) {
	  			int current = db.getItemQty(i) - quantity.get(j);
	  			
	  			//check to see the stock for any items went negative
	  			//update the cart to the new max amount if they did
	  			if (current < 0 ) {
	  	    		errorCart = true;
	  	    		cart.updateItem(i, db.getItemQty(i));
	  			} else
	  				newStock.add(current);
	  		}
	  		
	  		if (errorCart) {
	  			String target = "/final/ShoppingCart";
	  			session.setAttribute("cartError", 1);
	  			request.getRequestDispatcher(target).forward(request, response);
	  		}
	  		
	  		//update stock in db
	  		j=0;
	  	    for (int i : sale.getItemId()) {
	  	    	db.updateItemQty(i, newStock.get(j));
	  	    	j++;
	  	    }    
	  		
	  	    //add the sale to the db
	  	    db.finalizeSale(sale);	    
	  		
	  		
	  		request.setAttribute("total", total);
	  		//clear cart
	  		session.setAttribute("cart", null);
	  		
	  		
	  		//forward to JSP
	  		String target = "jsp/OrderConfirmationView.jsp";
	  		request.getRequestDispatcher(target).forward(request, response);
	    	
	    }
	    
	  	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}