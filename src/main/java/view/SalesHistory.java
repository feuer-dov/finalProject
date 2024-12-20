package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Sale;
import controller.Database;

/**
 * Servlet implementation class SalesHistory
 */
@WebServlet("/SalesHistory")
public class SalesHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalesHistory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Database db = new Database(request.getServletContext());
		List<Sale> sales = db.getAllSales();
		
		if(request.getParameter("filterName") != null && !request.getParameter("filterName").isEmpty()) {
			String username = (String) request.getAttribute("filterName");
			sales = db.getAllSalesByUsername(username);
			System.out.println("Filtering Sale");
		}
		
		request.setAttribute("sales", sales);
		
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/jsp/SalesHistory.jsp");
		dispatch.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
