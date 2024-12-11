package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
	    HttpSession session = request.getSession(true);
	    
	    Sale sale = (Sale) session.getAttribute("sale");
	    Database db = new Database(request.getServletContext());
	    db.finalizeSale(sale);
	    
	    out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<h1>Thank you for Ordering. </h1>");
		out.println("<body>");
		out.println("<b>" + request.getParameter("billing") + "</b>");
		out.println("<b>" + request.getParameter("shipping") + "</b>");
		
		out.println("</body></html>");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}