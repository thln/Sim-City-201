package market;

import java.util.*;

import market.MarketOrder.orderState;
import person.Person;
import person.Role;
import restaurant.CookRole;

public class SalesPersonRole extends Role {
	
	protected String RoleName = "Sales Person";

	public SalesPersonRole(Person person) {
		super(person);
	}

	//Data

	//Correspondents
	//MarketRunner marketRunner;

	private List<MarketOrder> orders = Collections.synchronizedList(new ArrayList<MarketOrder>());
	public double money;

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



	//Messages
	public void msgIWantProducts(MarketCustomerRole customer, String item, int numWanted) {
		orders.add(new MarketOrder(customer, item, numWanted));
		stateChanged();
	}

	public void msgIWantProducts(CookRole cookRole, String item, int numWanted, double payment) {
		orders.add(new MarketOrder(cookRole, item, numWanted));
		money += payment;
		stateChanged();
	}
	
	public void msgOrderFulfilled(MarketOrder o) {
		for (MarketOrder MO : orders) {
			if (MO.equals(o)) {
				MO.state = orderState.itemsFound;
				stateChanged();
				return;
			}
		}
	}

	public void msgPayment(MarketCustomerRole customer, double payment) {
		money += payment;
		for (MarketOrder o : orders) {
			if (o.customer.equals(customer)) {
				orders.remove(o);
				return;
			}
		}
	}

	//Scheduler
	protected boolean pickAndExecuteAnAction() {
		if (!orders.isEmpty()) {
			for (MarketOrder o : orders) {
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
	private void findItems(MarketOrder o) {
		o.state = orderState.processing;
		//marketRunner.msgHeresAnOrder(o);
		stateChanged();
	}

	private void giveCustomerItems(MarketOrder o) {
		o.state = orderState.gaveToCustomer;
		o.orderCost = inventoryPrices.get(o.item).price;
		//o.customer.msgHereAreYourThings(o.item, o.orderCost);		KRISTI: THIS LINE IS GIVING AN ERROR: PLZ FIX
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
