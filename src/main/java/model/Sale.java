package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Sale implements Serializable{
	private String custName;
	private ArrayList<Integer> itemId;
	private ArrayList<Integer> quantity;
	private int transactionId;
	private double total;
	private String address;
	private String ccNumber;
	
	public Sale(String cname, double total, ArrayList<Integer> id, ArrayList<Integer> qty, int tId, String shippingAddress, String ccNumber) {
		this.custName = cname;
		this.itemId = id;
		this.quantity = qty;
		this.transactionId = tId;
		this.total = total;
		this.address = shippingAddress;
		this.ccNumber = ccNumber;
	}
	
	public String getCCNumber() {
		return this.getCCNumber();
	}
	
	public void setCCNumber(String newCC) {
		this.ccNumber = newCC;
	}
	
	public String getShippingAddress() {
		return this.address;
	}
	
	public void setShippingAddress(String newAddress) {
		this.address = newAddress;
	}
	
	public String getCustName() {
		return this.custName;
	}
	
	
	public ArrayList<Integer> getItemId() {
		return this.itemId;
	}
	
	public ArrayList<Integer> getQuantity() {
		return this.quantity;
	}
	
	public int getTransactionId() {
		return this.transactionId;
	}



	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	
}
