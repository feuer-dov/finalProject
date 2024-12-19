package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
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

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    HttpSession session = request.getSession(true);
	    Account acc = (Account) session.getAttribute("account");
	    
	    boolean displayCreditFail = false;
	    session.setAttribute("displayCreditFail", displayCreditFail);
	    
	    if (acc != null) {
	    	
			Database db = new Database(request.getServletContext());
			String billing = acc.getBillingAddress();
		    session.setAttribute("def_billing", billing);
		    String shipping = acc.getShipAddress();
		    session.setAttribute("def_shipping", shipping);
		    String creditCard = acc.getCreditCard();
		    session.setAttribute("def_creditCard", creditCard);
		    
		    
		    Cart cart = (Cart) session.getAttribute("cart");
		    ArrayList<Integer> itemIds = new ArrayList<>();
		    ArrayList<Integer> quantity = new ArrayList<>();
		    for (Item item : cart.getItems()) {
		    	itemIds.add(item.getId());
		    	quantity.add(item.getQtyOrdered());
		    }
		    
		    int transactionId = db.getAllSales().size();
		    double total = cart.getTotal();
		    
		    Sale sale = new Sale(acc.getUsername(), total, itemIds, quantity, transactionId);
		    session.setAttribute("sale", sale);
		    
		    String target = "/jsp/CheckoutView.jsp";
		    request.getRequestDispatcher(target).forward(request, response);
		    
		} else {

			session.setAttribute("sendToCheckout", true);
			String target = "/jsp/LoginPage.jsp";
		  
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