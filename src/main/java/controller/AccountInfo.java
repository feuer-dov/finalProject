package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;

/**
 * Servlet implementation class AccountInfo
 */
@WebServlet("/AccountInfo")
public class AccountInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		DatabaseInterface db = new Database(request.getServletContext());
		HttpSession session = request.getSession();
		
		if(action.equals("updateInfo")) {
			String username = request.getParameter("username");
			String name = request.getParameter("name");
			String billingAddress = request.getParameter("billingAddress");
			String shipAddress = request.getParameter("shipAddress");
			String creditCard = request.getParameter("creditCard");
			
			Account a1 = db.getAccount(username);
			Account account = new Account(shipAddress, username, a1.getPassword(), creditCard, name, 1, billingAddress);
			db.updateAccount(username, account);
			session.setAttribute("account", account);
		}
		else if(action.equals("logOut")) {
			session.setAttribute("account", null);
			request.getRequestDispatcher("home").forward(request, response);;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("jsp/accountInfo.jsp");
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
