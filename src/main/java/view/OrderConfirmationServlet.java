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

import model.Cart;
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
	    Random r = new Random();
	    if (r.nextBoolean()) {
	    	request.setAttribute("cardFailed", "1");
    		String target = "/final/CheckoutServlet";
    		request.getRequestDispatcher(target).forward(request, response);
	    }
	    
	    Sale sale = (Sale) session.getAttribute("sale");
	    Database db = new Database(request.getServletContext());
	    Cart cart = (Cart) session.getAttribute("cart");
	    
		ArrayList<Integer> quantity = sale.getQuantity();
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
		
		//clear cart
		String total = "$" + cart.getTotal();
		request.setAttribute("total", total);
		
		//forward to JSP
		String target = "/jsp/OrderConfirmationView.jsp";
		request.getRequestDispatcher(target).forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}