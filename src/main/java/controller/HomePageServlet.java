package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Item;

/**
 * Servlet implementation class HomePageServlet
 */
@WebServlet("/home")
public class HomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomePageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseInterface db = new Database(getServletContext());
		
	
		
//		List<String> categories = db.getAllCatNames();
//		List<String> brands = db.getAllBrandNames();
//
//
//		request.setAttribute("categories", categories); // pass cats to list in jsp sidebar
//		request.setAttribute("brands", brands); // pass brands to list in jsp sidebar
//		
	
		String category = request.getParameter("category");
		String brand = request.getParameter("brand");
		String byPrice = request.getParameter("byPrice");
		String searchQuery = request.getParameter("searchQuery");
		
		List<Item> itemsToDisplay;
		
		if(category != null) {
			itemsToDisplay = db.getAllItemsByCat(category);
		}
		else if(brand != null) {
			itemsToDisplay = db.getItemsByBrand(brand);
		}
		else if(byPrice != null) {

			if(byPrice.equals("desc")) {
				itemsToDisplay = db.getAllItemsPriceSortedDesc();
			}
			else {
				itemsToDisplay = db.getAllItemsPriceSorted();
			}
		}
		else if(searchQuery != null) {
			itemsToDisplay = db.getItemsBySearchQuery(searchQuery);
		}
		else {
			itemsToDisplay = db.getAllItems();
		}
		
		request.setAttribute("itemsToDisplay", itemsToDisplay);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/HomePage.jsp");
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
