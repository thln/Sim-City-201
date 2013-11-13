package market;

import java.util.*;

import person.Person;
import person.Role;
import restaurant.Cook;

public class SalesPerson extends Role {

	SalesPerson(Person person) {
		super(person);
	}

	//Data
	//Correspondents
	//MarketRunner marketRunner;
	
	private List<Order> orders;
	public double money;
	public enum orderState {open, processing, itemsFound, gaveToCustomer};
	public enum CustomerType {marketCustomer, business};
	public HashMap<String, Item> inventoryPrices = new HashMap<String, Item>(); {
		//For people
		inventoryPrices.put("Car", new Item("Car", 1000));
		inventoryPrices.put("Pasta", new Item("Pasta", 1.99));
		inventoryPrices.put("Ice Cream", new Item("Ice Cream", 5.99));
		inventoryPrices.put("Chips", new Item("Chips", 2.99));
		inventoryPrices.put("Milk", new Item("Milk", 2.50));
		inventoryPrices.put("Eggs", new Item("Eggs", 1.50));
		inventoryPrices.put("Lobster", new Item("Lobster", 12.99));
		inventoryPrices.put("Cheese", new Item("Cheese", 4.99));
		
		//For restaurants
		inventoryPrices.put("Chicken", new Item("Chicken", 10.99));
		inventoryPrices.put("Steak", new Item("Steak", 15.99));
		inventoryPrices.put("Pizza", new Item("Pizza", 8.99));
		inventoryPrices.put("Salad", new Item("Salad", 5.99));
	}

	class Order {
		Order(MarketCustomer customer, String item, int itemsWanted) {
			this.customer = customer;
			this.item = item;
			totalItems = itemsWanted;
		}
		
		Order(Cook customer, String item, int itemsWanted) {
			this.cook = cook;
			this.item = item;
			totalItems = itemsWanted;
		}

		MarketCustomer customer = null;
		Cook cook = null;
		CustomerType custType;
		String item;
		int totalItems;
		double orderCost;
		orderState state = orderState.open;
	}

	//Messages
	public void msgIWantProducts(MarketCustomer customer, String item, int numWanted) {
		orders.add(new Order(customer, item, numWanted));
		stateChanged();
	}

	public void msgIWantProducts(Cook cook, String item, int numWanted, double payment) {
		orders.add(new Order(cook, item, numWanted));
		money += payment;
		stateChanged();
	}

	public void msgPayment(MarketCustomer customer, double payment) {
		money += payment;
		for (Order o : orders) {
			if (o.customer.equals(customer)) {
				orders.remove(o);
				return;
			}
		}
	}

	//Scheduler
	protected boolean pickAndExecuteAnAction() {
		if (!orders.isEmpty()) {
			for (Order o : orders) {
				if (o.state == orderState.open) {
					findItems(o);
					return true;
				}
				if (o.state == orderState.itemsFound) {
					giveCustomerItems(o);
					return true;
				}
			}
		}
		return false;
	}

	//Actions
	private void findItems(Order o) {
		o.state = orderState.processing;
		//marketRunner.msgHeresAnOrder(o);
		stateChanged();
	}

	private void giveCustomerItems(Order o) {
		o.state = orderState.gaveToCustomer;
		o.orderCost = inventoryPrices.get(o.item).price;
		o.customer.msgHereAreYourThings(o.item, o.orderCost);
		stateChanged();
	}
	
	//Item Class
	public class Item {
		String itemName;
		double  price;

		Item(String choice, double price) {
			itemName = choice;
			this.price = price;
		}
	}
}
