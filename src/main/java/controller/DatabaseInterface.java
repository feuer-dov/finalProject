package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Item;
import model.Sale;

public interface DatabaseInterface {

	Connection getConnection() throws SQLException;
	void closeConnection(Connection con);
	int createAccount(String username, String password, String shippingAddress, String creditCard, String name, String bill);
	int adminCreateAccount(String username, String password, String shippingAddress, String creditcard, String name, String bill, int p);
	boolean deleteAccount(String username);
	boolean updateAccount(String oldUsername, Account acc);
	boolean verifyLogin(String username, String password);
	Account getAccount(String username);
	List<Account> getAllAccounts();
	boolean saveItem(Item item);
	Item getItem(int id);
	boolean deleteItem(int id);
	void updateItemQty(int id, int newStock);
	int getItemQty(int id);
	List<Item> getAllItems();
	ArrayList<Item> getItemsByName(String name);
	ArrayList<String> getAllCatNames();
	ArrayList<String> getAllBrandNames();
	ArrayList<Item> getAllItemsByCat(String catName);
	ArrayList<Item> getItemsByBrand(String brandName);
	Sale getSale(int transactionId);
	boolean finalizeSale(Sale s);
	List<Sale> getAllSales();
}
