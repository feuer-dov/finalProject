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
		for (int i = 0; i < cart.size(); i++) {
			// if item already exists, change it's values to the new item
			Item cartItem = cart.get(i);
			if (cartItem.getId() == item.getId()) {
				cart.set(i, item);
				itemFound = true;
				break;
			}
		}

		if (!itemFound) {
			cart.add(item);
		}

	}

	public void removeItem(int id) {
		for (Item item : cart) {
			if (item.getId() == id) {
				cart.remove(item);
				break;
			}
		}
	}

	public void updateItem(int id, int newQty) {
		for (Item item : cart) {
			if (item.getId() == id) {
				item.setQtyOrdered(newQty);
				;
				break;
			}
		}

	}

	public void increaseItemQty(int id, int increaseAmount) {
		for (Item item : cart) {
			if (item.getId() == id) {
				if (item.getQtyOrdered() + increaseAmount <= item.getStock()) {
					item.setQtyOrdered(item.getQtyOrdered() + increaseAmount);
				}
				break;
			}
		}
	}

	public void decreaseItemQty(int id, int decreaseAmount) {
		for (Item item : cart) {
			if (item.getId() == id) {
				item.setQtyOrdered(item.getQtyOrdered() - decreaseAmount);
				if (item.getQtyOrdered() <= 0) { // if qty is below 0, remove
					cart.remove(item);
				}
				break;
			}
		}
	}

	public List<Item> getItems() {
		return cart;
	}

	public void printCart() {
		for (Item item : cart) {
			System.out.print(item.getName() + ", ");

		}
	}

	public boolean isEmpty() {
		boolean result = false;
		if (cart.size() == 0) {
			result = true;
		}

		return result;
	}

	public double getTotal() {
		double total = 0;
		for (Item item : cart) {
			total += item.getPrice() * item.getQtyOrdered();
		}

		return total;
	}
	
	public void setItemQty(int id, int qty) {
		
		for (Item item: cart) {
			if (item.getId() == id) {
				if(qty >= 0 && qty <= item.getStock()) {
					item.setQtyOrdered(qty);
				}
				break;
			}
		}
		
		
	}
	
	private Item getItemById(int id){
		Item result = null;
		
		for (Item item: cart) {
			if (item.getId() == id) {
				result = item;
				break;
			}
		}
		
		return result;
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
