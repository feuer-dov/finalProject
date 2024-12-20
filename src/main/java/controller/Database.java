package controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import model.Account;
import model.Item;
import model.Product;
import model.Sale;

public class Database implements DatabaseInterface{
	private ServletContext context;
	String addUserSQL = "INSERT INTO ACCOUNTS (Full_Name, Credit_Card, Shipping_Address, Username, Password, Privilege, Billing_Address) VALUES(?, ?, ?, ?, ?, ?, ?);";
	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch(ClassNotFoundException e) {
		}
	}
	
	public Database(ServletContext context) {
		this.context = context;
	}
	
	
	public Connection getConnection() throws SQLException{
		String path = context.getRealPath("4413Database.db");
		return DriverManager.getConnection("jdbc:sqlite:" + path);
	}
	
	
	public void closeConnection(Connection con) {
		if(con == null)
			return;
		else {
			try {
				con.close();
			}catch(SQLException e) {
			}
		}
	}
	
	//**************************ACCOUNTS*********************************//
	
	/*
	 * Creates an general account and stores it in the Database
	 * Return Values:
	 * 		1: Successfully Created Account
	 * 		2: Username already in Use
	 * 		3: Error has occurred
	 */
	public int createAccount(String username, String password, String shippingAddress, String creditcard, String name, String bill) {
		//Check if username already exists. Return 2 if it does
		String uSQL = "SELECT * FROM ACCOUNTS;";
		Connection con = null;
		
		try {
			con = getConnection();
			PreparedStatement statement = con.prepareStatement(uSQL);
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				//Username already exists
				System.out.println("Name 1: " + results.getString(4));
				if (results.getString(4).equals(username)) {
					statement.close();
					closeConnection(con);
					return 2;
				}
			}
			
			PreparedStatement stmt = con.prepareStatement(addUserSQL);
			stmt.setString(1, name);
			stmt.setString(2, creditcard);
			stmt.setString(3, shippingAddress);
			stmt.setString(4, username);
			stmt.setString(5, password);
			stmt.setString(6, "1");
			stmt.setString(7, bill);
			stmt.executeUpdate();
			
			stmt.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return 3;
		} finally {
			closeConnection(con);
		}

		
		return 1;
	}
	
	public int adminCreateAccount(String username, String password, String shippingAddress, String creditcard, String name, String bill, int p) {
		//Check if username already exists. Return 2 if it does
		String uSQL = "SELECT * FROM ACCOUNTS;";
		Connection con = null;
		
		try {
			con = getConnection();
			PreparedStatement statement = con.prepareStatement(uSQL);
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				//Username already exists
				System.out.println("Name 1: " + results.getString(4));
				if (results.getString(4).equals(username)) {
					statement.close();
					closeConnection(con);
					return 2;
				}
			}
			
			PreparedStatement stmt = con.prepareStatement(addUserSQL);
			stmt.setString(1, name);
			stmt.setString(2, creditcard);
			stmt.setString(3, shippingAddress);
			stmt.setString(4, username);
			stmt.setString(5, password);
			stmt.setInt(6, p);
			stmt.setString(7, bill);
			stmt.executeUpdate();
			
			stmt.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return 3;
		} finally {
			closeConnection(con);
		}

		
		return 1;
	}
	/*
	 * Takes a username and removes them from the database
	 * Returns: True if successfully removed and false otherwise
	 */
	public boolean deleteAccount(String username) {
		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = getConnection();
			String sql = "DELETE FROM ACCOUNTS WHERE username=?";
			statement = con.prepareStatement(sql);
			statement.setString(1, username);
			statement.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeConnection(con);
		}
		
		return true;
	}
	
	/*
	 * Takes the old username of the account and all the new information stored in
	 * a Account class and updates the account info in the database
	 * returns true if successful and false otherwise
	 */
	public boolean updateAccount(String oldUsername, Account acc) {
		
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "UPDATE ACCOUNTS SET Full_name = ?, Credit_Card=?, Shipping_Address=?, Username=?, Password=?, Privilege=?, Billing_Address=? WHERE username=?";
		
		try {
			con = getConnection();
			statement = con.prepareStatement(sql);
	
			statement.setString(1, acc.getName());
			statement.setString(2, acc.getCreditCard());
			statement.setString(3, acc.getShipAddress());
			statement.setString(4, acc.getUsername());
			statement.setString(5, acc.getPassword());
			statement.setInt(6, acc.getPriv());
			statement.setString(7, acc.getBillingAddress());
			statement.setString(8, oldUsername);
			
			statement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeConnection(con);
		}
		
		return true;
	}
	
	/*
	 * Takes an username and password and returns true if login info is correct and false otherwise
	 */
	public boolean verifyLogin(String username, String password) {
		
		Connection con = null;
		PreparedStatement statement = null;
		String u;
		String p;
		try {
			con = getConnection();
			String sql = "SELECT * FROM ACCOUNTS;";
			statement = con.prepareStatement(sql);
			ResultSet r = statement.executeQuery();
			
			while(r.next()) {
				u = r.getString(4);
				p = r.getString(5);
				if(u.equals(username) && p.equals(password)) {
					return true;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeConnection(con);
		}
		
		return false;
	}
	
	/*
	 * Given a Username, Populates the Account class instance and returns it
	 */
	public Account getAccount(String username) {
		Connection con = null;
		PreparedStatement statement = null;
		Account user = null;
		try {
			con = getConnection();
			String sql = "SELECT * FROM ACCOUNTS;";
			statement = con.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			
			while(results.next()) {
				if(results.getString(4).equals(username)) {
					user = new Account(results.getString(3), results.getString(4), results.getString(5), results.getString(2), results.getString(1), results.getInt(6), results.getString(7));
					return user;
				}
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try{
				statement.close();
				con.close();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return user;
	}
	
	public List<Account> getAllAccounts(){
		List<Account> accounts = new ArrayList<Account>();
		
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "SELECT * FROM ACCOUNTS;";
		Account account = null;
		try {
			con = getConnection();
			statement = con.prepareStatement(sql);
			ResultSet r = statement.executeQuery();
			
			while(r.next()) {
				account = new Account(r.getString(3), r.getString(4), r.getString(5), r.getString(2), r.getString(1), r.getInt(6), r.getString(7));
				accounts.add(account);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeConnection(con);
		}
		
		return accounts;
	}
	
	
	
	//**************************ITEMS*********************************//
	
	/*
	 * Takes an item and stores it in the database
	 * returns true if successful and false otherwise
	 */
	public boolean saveItem(Item item) {
		
		Connection con = null;
		PreparedStatement statement = null;
		PreparedStatement stmt = null;
		String check = "SELECT * FROM ITEMS;";
		String sql = "INSERT INTO ITEMS() VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try {			
			con = getConnection();
			
			stmt = con.prepareStatement(check);
			ResultSet r = stmt.executeQuery();
			
			while(r.next()) {
				if(r.getInt(2) == item.getId()) {
					return false;
				}
			}
			
			statement = con.prepareStatement(sql);
			statement.setString(1, item.getName());
			statement.setInt(2, item.getId());
			statement.setString(3, item.getDescription());
			statement.setString(4, item.getCategoryName());
			statement.setString(5, item.getBrand());
			statement.setDouble(6, item.getPrice());
			statement.setInt(7, item.getStock());
			
			statement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeConnection(con);
		}
		
		return true;
	}
	
	/*
	 * Takes an Id and returns the Item from the Database. Returns null otherwise
	 */
	public Item getItem(int id) {
		Item item = null;
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = getConnection();
			String sql = "SELECT * FROM ITEMS;";
			statement = con.prepareStatement(sql);
			ResultSet r = statement.executeQuery();
			while(r.next()) {
				if(r.getInt(2) == id) {
					item = new Item(r.getString(1), r.getInt(2), r.getString(3), r.getString(4), r.getString(5), r.getDouble(6), r.getInt(7));
					break;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeConnection(con);
		}
		return item;
	}
	
	/*
	 * Takes an Items id and removes it from the database
	 * Returns true if successful and false otherwise
	 */
	public boolean deleteItem(int id) {
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "DELETE FROM ITEMS WHERE id=?";
		
		try {
			con = getConnection();
			statement = con.prepareStatement(sql);
			statement.setInt(1, id);
			
			statement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeConnection(con);
		}
		
		return true;
	}

	public void updateItemQty(int id, int newStock) {
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "UPDATE ITEMS SET Stock=? WHERE Id=?";
		
		try {
			con = getConnection();
			statement = con.prepareStatement(sql);
			statement.setInt(1, newStock);
			statement.setInt(2, id);
			
			statement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeConnection(con);
		}
	}
	
	/*
	 * Takes Item id as input
	 * return qty of item (id) and -1 otherwise
	 */
	public int getItemQty(int id) {
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "SELECT * FROM ITEMS;";
		int qty = -1;
		
		try {
			con = getConnection();
			statement = con.prepareStatement(sql);
			ResultSet r = statement.executeQuery();
			
			while(r.next()) {
				if(r.getInt(2) == id) {
					qty = r.getInt(7);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeConnection(con);
		}
		
		
		return qty;
	}
	
	// returns all items sorted by name
	public List<Item> getAllItems(){
		List<Item> items = new ArrayList<Item>();
		Item item = null;
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "SELECT * FROM ITEMS ORDER BY Name;";
		
		try {
			con = getConnection();
			statement = con.prepareStatement(sql);
			ResultSet r = statement.executeQuery();
			
			while(r.next()) {
				item = new Item(r.getString(1), r.getInt(2), r.getString(3), r.getString(4), r.getString(5), r.getDouble(6), r.getInt(7));
				items.add(item);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeConnection(con);
		}
		
		return items;
	}
	
	
	// returns list of items sorted by price
	public List<Item> getAllItemsPriceSorted(){
		List<Item> items = new ArrayList<Item>();
		Item item = null;
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "SELECT * FROM ITEMS ORDER BY Price;";
		
		try {
			con = getConnection();
			statement = con.prepareStatement(sql);
			ResultSet r = statement.executeQuery();
			
			while(r.next()) {
				item = new Item(r.getString(1), r.getInt(2), r.getString(3), r.getString(4), r.getString(5), r.getDouble(6), r.getInt(7));
				items.add(item);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeConnection(con);
		}
		
		return items;
	}
	
	// returns list of items sorted by price
public List<Item> getAllItemsPriceSortedDesc(){
		List<Item> items = new ArrayList<Item>();
		Item item = null;
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "SELECT * FROM ITEMS ORDER BY Price DESC;";
		
		try {
			con = getConnection();
			statement = con.prepareStatement(sql);
			ResultSet r = statement.executeQuery();
			
			while(r.next()) {
				item = new Item(r.getString(1), r.getInt(2), r.getString(3), r.getString(4), r.getString(5), r.getDouble(6), r.getInt(7));
				items.add(item);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeConnection(con);
		}
		
		return items;
	}
	
	/*
	 * Takes category name as parameter and returns an ArrayList of Items with category = parameter name
	 */
	public ArrayList<Item> getItemsByName(String name){
		ArrayList<Item> items = new ArrayList<Item>();
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "SELECT * FROM ITEMS;";
		
		try {
			con = getConnection();
			statement = con.prepareStatement(sql);
			ResultSet r = statement.executeQuery();
			
			while(r.next()) {
				
				String catName = r.getString(1);
				
				if(catName.equals(name)) {
					Item i = new Item(r.getString(1), r.getInt(2), r.getString(3), r.getString(4), r.getString(5), r.getDouble(6), r.getInt(7));
					items.add(i);
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeConnection(con);
		}
		
		return items;
	}
	
	
	/*
	 * When called, gathers all unique category names from the database and
	 * returns them in an ArrayList
	 */
	public ArrayList<String> getAllCatNames(){
		ArrayList<String> catNames = new ArrayList<String>();
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "SELECT * FROM ITEMS ORDER BY Category_Name;";
		
		try {
			con = getConnection();
			statement = con.prepareStatement(sql);
			ResultSet r = statement.executeQuery();
			
			while(r.next()) {
				if(!(catNames.contains(r.getString(4)))) {
					catNames.add(r.getString(4));
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			closeConnection(con);
		}
		
		return catNames;
	}
	
	
	/*
	 * When called, provides an ArrayList of all unique Brands in the Database
	 */
	public ArrayList<String> getAllBrandNames(){
		ArrayList<String> brandNames = new ArrayList<String>();
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "SELECT * FROM ITEMS ORDER BY Brand";
		
		try {
			con = getConnection();
			statement = con.prepareStatement(sql);
			ResultSet r = statement.executeQuery();
			
			while(r.next()){
				if(!(brandNames.contains(r.getString(5)))) {
					brandNames.add(r.getString(5));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeConnection(con);
		}
		
		return brandNames;
	}
	
	
	/*
	 * Takes a catName as parameter and returns an ArrayList of Items with category = catName
	 * returns an empty ArrayList if there is nothing matching catName
	 * returns null if error occurs
	 */
	public ArrayList<Item> getAllItemsByCat(String catName){
		ArrayList<Item> items = new ArrayList<Item>();
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "SELECT * FROM ITEMS;";
		
		try {
			con = getConnection();
			statement = con.prepareStatement(sql);
			ResultSet r = statement.executeQuery();
			
			while(r.next()) {
				if(catName.equals(r.getString(4))) {
					Item i = new Item(r.getString(1), r.getInt(2), r.getString(3), r.getString(4), r.getString(5), r.getDouble(6), r.getInt(7));
					items.add(i);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeConnection(con);
		}
		return items;
	}
	
	
	/*
	 * Takes brandName as parameter and returns an ArrayList of all Items with
	 * brandName = Items brandName
	 */
	public ArrayList<Item> getItemsByBrand(String brandName){
		ArrayList<Item> items = new ArrayList<Item>();
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "SELECT * FROM ITEMS;";
		
		try {
			con = getConnection();
			statement = con.prepareStatement(sql);
			ResultSet r = statement.executeQuery();
			
			while(r.next()) {
				if(brandName.equals(r.getString(5))) {
					Item i = new Item(r.getString(1), r.getInt(2), r.getString(3), r.getString(4), r.getString(5), r.getDouble(6), r.getInt(7));
					items.add(i);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeConnection(con);
		}
		
		return items;
	}
	
	//**************************SALES*********************************//
	
	
	/*
	 * Takes a transactionID and returns the matching Sale object
	 */
	public Sale getSale(int transactionId) {
		Sale sale = null;
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "SELECT * FROM PURCHASE_HISTORY;";
		
		try {
			con = getConnection();
			statement = con.prepareStatement(sql);
			ResultSet r = statement.executeQuery();
			
			while(r.next()) {
				if(r.getInt(5) == transactionId) {
					ArrayList<Integer> itemIds = new ArrayList<Integer>();
					ArrayList<Integer> itemQtys = new ArrayList<Integer>();
					
					try {
						byte[] dataId = r.getBytes(2);
						byte[] dataQty = r.getBytes(3);
						
						ByteArrayInputStream inputStream1 = new ByteArrayInputStream(dataId);
						ByteArrayInputStream inputStream2 = new ByteArrayInputStream(dataQty);
						
						ObjectInputStream objectStream1 = new ObjectInputStream(inputStream1);
						ObjectInputStream objectStream2 = new ObjectInputStream(inputStream2);
						itemIds = (ArrayList<Integer>) objectStream1.readObject();
						itemQtys = (ArrayList<Integer>) objectStream2.readObject();
						sale = new Sale(r.getString(1), r.getDouble(5), itemIds, itemQtys, r.getInt(4), r.getString(6), r.getString(7));
						break;
					}catch(Exception e) {
						e.printStackTrace();
						System.out.println("ERROR!!!");
						return null;
					}
					
					
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeConnection(con);
		}
		
		
		return sale;
	}

	/*
	 * Takes a Sale Object s and stores it in the database
	 * Returns True if successful and false otherwise
	 */
	public boolean finalizeSale(Sale s) {
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "INSERT INTO PURCHASE_HISTORY(Cust_Username, Item_Id, Quantity, Transaction_Id, Total, Address, CCNum) VALUES (?, ?, ?, ?, ?, ?, ?);";
		
		try {
			con = getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(1, s.getCustName());
			//statement.setInt(2, s.getItemId());
			ByteArrayOutputStream idByte = new ByteArrayOutputStream();
			ByteArrayOutputStream qtyByte = new ByteArrayOutputStream();
			ObjectOutputStream outputStream1 = null;
			ObjectOutputStream outputStream2 = null;
			byte[] idData = null;
			byte[] qtyData = null;
			try {
				outputStream1 = new ObjectOutputStream(idByte);
				outputStream2 = new ObjectOutputStream(qtyByte);
				outputStream1.writeObject(s.getItemId());
				outputStream2.writeObject(s.getQuantity());
				idData = idByte.toByteArray();
				qtyData = qtyByte.toByteArray();
				
				statement.setObject(2, idData);
				statement.setObject(3, qtyData);
				statement.setInt(4, s.getTransactionId());
				statement.setDouble(5, s.getTotal());
				statement.setString(6, s.getShippingAddress());
				statement.setString(7, s.getCCNumber());
				
				statement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeConnection(con);
		}
		
		return true;
	}
	
	public List<Sale> getAllSales(){
		List<Sale> sales = new ArrayList<Sale>();
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "SELECT * FROM PURCHASE_HISTORY;";
		Sale sale = null;
		try {
			con = getConnection();
			statement = con.prepareStatement(sql);
			ResultSet r = statement.executeQuery();
			
			while(r.next()) {
				
				
				
				try {
					byte[] itemData = r.getBytes(2);
					byte[] qtyData = r.getBytes(3);
					
					ByteArrayInputStream inputStream1 = new ByteArrayInputStream (itemData);
					ByteArrayInputStream inputStream2 = new ByteArrayInputStream (qtyData);
					
					ObjectInputStream objectStream1 = new ObjectInputStream(inputStream1);
					ObjectInputStream objectStream2 = new ObjectInputStream(inputStream2);
					
					ArrayList<Integer> itemIds = (ArrayList<Integer>) objectStream1.readObject();
					ArrayList<Integer> itemQtys = (ArrayList<Integer>) objectStream2.readObject();
					
					sale = new Sale(r.getString(1), r.getDouble(5), itemIds, itemQtys, r.getInt(4), r.getString(6), r.getString(7));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				sales.add(sale);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeConnection(con);
		}
		
		return sales;
	}
	
	public List<Sale> getAllSalesByUsername(String username){
		Connection con = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM PURCHASE_HISTORY;";
		
		List<Sale> result = new ArrayList<>();
		
		try {
			con = getConnection();
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString(1).equals(username)){
					byte[] itemData = rs.getBytes(2);
					byte[] qtyData = rs.getBytes(3);
					
					ByteArrayInputStream inputStream1 = new ByteArrayInputStream (itemData);
					ByteArrayInputStream inputStream2 = new ByteArrayInputStream (qtyData);
					
					ObjectInputStream objectStream1 = new ObjectInputStream(inputStream1);
					ObjectInputStream objectStream2 = new ObjectInputStream(inputStream2);
					
					ArrayList<Integer> itemIds = (ArrayList<Integer>) objectStream1.readObject();
					ArrayList<Integer> itemQtys = (ArrayList<Integer>) objectStream2.readObject();
					
					Sale sale = new Sale(rs.getString(1), rs.getDouble(5), itemIds, itemQtys, rs.getInt(4), rs.getString(6), rs.getString(7));
					result.add(sale);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeConnection(con);
		}
		
		return result;
	}
	
	public List<Item> getItemsBySearchQuery(String query){
		Connection con = null;
		PreparedStatement stmt = null;
		List<Item> result = new ArrayList<Item>();
		
		String sql = "SELECT DISTINCT * FROM ITEMS WHERE Name LIKE ? OR Brand LIKE ? OR Category_Name LIKE ? OR Description LIKE ?";
        try {
        	con = getConnection();
        	stmt = con.prepareStatement(sql);
        	String searchTerm = "%" + query + "%";
        	
        	for (int i = 1; i <= 4; i++) {
        	    stmt.setString(i, searchTerm);
        	}
            
            ResultSet r = stmt.executeQuery();
            while (r.next()) {
            	Item i = new Item(r.getString(1), r.getInt(2), r.getString(3), r.getString(4), r.getString(5), r.getDouble(6), r.getInt(7));
				result.add(i);
               }
            }catch (Exception e) {
            e.printStackTrace();
            result = null;
        }
        
        return result;
	}

}
