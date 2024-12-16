package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	ArrayList<Item> cart;
	
	public Cart() {
		cart = new ArrayList<Item>();
	}
	
	public void addItem(Item item) {
		// Check if item id is found and increase quantity by 1]
		
		boolean itemFound = false;
		for(Item i: cart) {
			if(i.getId() == item.getId()) {
				i.setQtyOrdered(i.getQtyOrdered() + item.getQtyOrdered());
				itemFound = true;
				break;
			}
		}
		
		if(!itemFound) {
			cart.add(item);
		}
		
	}
	
	public void removeItem(int id) {
		for(Item item: cart) {
			if(item.getId() == id) {
				cart.remove(item);
				break;
			}
		}
	}
	
	public void update(int id, int newQty) {
		for(Item item: cart) {
			if(item.getId() == id) {
				item.setQtyOrdered(newQty);;
				break;
			}
		}
		
	}
	
	public List<Item> getItems() {
		return cart;
	}
	
	public void printCart() {
		for(Item item: cart) {
			System.out.print(item.getName() + ", ");

		}
	}
	public void testCart() {
		System.out.println("Cart test");
	}
	

}

//
//package model;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class Cart {
//    private Map<Integer, Integer> items = new HashMap<>();
//
//    public void addProduct(int productId) {
//        items.put(productId, items.getOrDefault(productId, 0) + 1);
//    }
//
//    public Map<Integer, Integer> getItems() {
//        return items;
//    }
//}
