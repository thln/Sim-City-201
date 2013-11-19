package market;

import java.util.*;

import market.SalesPersonRole.Item;
import person.Person;

public class Market {

	double money;
	HashMap<String, Integer> inventory = new HashMap<String, Integer>(); {
		//For people
		inventory.put("Car", 100);
		inventory.put("Pasta", 100);
		inventory.put("Ice Cream", 100);
		inventory.put("Chips", 100);
		inventory.put("Milk", 100);
		inventory.put("Eggs", 100);
		inventory.put("Lobster", 100);
		inventory.put("Cheese", 100);

		//For restaurants
		inventory.put("Chicken", 100);
		inventory.put("Steak", 100);
		inventory.put("Pizza", 100);
		inventory.put("Salad", 100);
	}
	
	public HashMap<Integer, Item> marketItemsForSale = new HashMap<Integer, Item>(); {
		//For people shopping
		marketItemsForSale.put(0, new Item("Car", 1000));
		marketItemsForSale.put(1, new Item("Pasta", 1.99));
		marketItemsForSale.put(2, new Item("Ice Cream", 5.99));
		marketItemsForSale.put(3, new Item("Chips", 2.99));
		marketItemsForSale.put(4, new Item("Milk", 2.50));
		marketItemsForSale.put(5, new Item("Eggs", 1.50));
		marketItemsForSale.put(6, new Item("Lobster", 12.99));
		marketItemsForSale.put(7, new Item("Cheese", 4.99));
	}
	
	public SalesPersonRole salesPersonRole;
	public UPSmanRole	UPSmanRole;
	
	
	public SalesPersonRole arrivedAtWorkSalesPerson(Person person) {
		return salesPersonRole;
	}
	
	public UPSmanRole arrivedAtWorkUPSman(Person person) {
		return UPSmanRole;
	}
	
	public setSalesPersonRole(Person person) {
		//salesPersonRole = ?
	}
	
	public class Item {
		public String itemName;
		public double  price;

		Item(String choice, double price) {
			itemName = choice;
			this.price = price;
		}
	}
}
