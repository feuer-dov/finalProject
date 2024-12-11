package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	ArrayList<Item> cart;
	
	public Cart() {
		cart = new ArrayList<Item>();
	}
	
	public void addItem(int id) {
		// Check if item id is found and increase quantity by 1]
		boolean itemFound = false;
		for(Item item: cart) {
			if(item.getId() == id) {
//				int oldQty = item.getQuantity();
//				item.setOrderedQty(oldQty + 1);
				itemFound = true;
				break;
			}
		}
		
		if(!itemFound) {
			
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
		//TODO
		
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
