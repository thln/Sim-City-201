package market;

import java.util.*;

import application.WatchTime;
import person.Person;
import person.Role;
import person.Worker;

public class Market {

	//Data
	String name;

	//Open and closing times
	public WatchTime openTime = new WatchTime(9);
	public WatchTime closeTime = new WatchTime(18);

	//Roles
	public SalesPersonRole salesPersonRole = new SalesPersonRole("Sales Person", this);
	public MarketRunnerRole marketRunnerRole = new MarketRunnerRole("Market Runner", this);
	public UPSmanRole UPSmanRole = new UPSmanRole("UPS Man", this);

	double money;
	HashMap<String, Double> marketItemsForSale = new HashMap<String, Double>(); {
		//For people
		marketItemsForSale.put("Car", 1000.00);
		marketItemsForSale.put("Pasta", 1.99);
		marketItemsForSale.put("Ice Cream", 4.99);
		marketItemsForSale.put("Chips", 2.99);
		marketItemsForSale.put("Milk", 2.50);
		marketItemsForSale.put("Eggs", 1.50);
		marketItemsForSale.put("Lobster", 12.99);
		marketItemsForSale.put("Cheese", 3.99);
	}

	public HashMap<Integer, Item> inventory = new HashMap<Integer, Item>(); {
		//For people
		inventory.put(0, new Item("Car", 1000.00, 1000));
		inventory.put(1, new Item("Pasta", 1.99, 1000));
		inventory.put(2, new Item("Ice Cream", 4.99, 1000));
		inventory.put(3, new Item("Chips", 2.99, 1000));
		inventory.put(4, new Item("Milk", 2.50, 1000));
		inventory.put(5, new Item("Eggs", 1.50, 1000));
		inventory.put(6, new Item("Lobster", 12.99, 1000));
		inventory.put(7, new Item("Cheese", 3.99, 1000));
		
		//For restaurants
		inventory.put(8, new Item("Chicken", 10.99, 1000));
		inventory.put(9, new Item("Steak", 15.99, 1000));
		inventory.put(10, new Item("Pizza", 8.99, 1000));
		inventory.put(11, new Item("Salad", 5.99, 1000));
	}

	//Constructor
	public Market(String name) {
		this.name = name;
	}


	//Methods
	public Role arrivedAtWork(Person person, String title) {
		if (title == "salesPerson") {
			//Setting previous bank guard role to inactive
			if (salesPersonRole.getPerson() != null) {
				Worker worker = (Worker) salesPersonRole.getPerson();
				worker.goOffWork();
			}
			//Setting bank guard role to new role
			salesPersonRole.setPerson(person);
			return salesPersonRole;
		}
		else if (title == "marketRunner") {
			//Setting previous bank guard role to inactive
			if (marketRunnerRole.getPerson() != null) {
				Worker worker = (Worker) marketRunnerRole.getPerson();
				worker.goOffWork();
			}
			//Setting bank guard role to new role
			marketRunnerRole.setPerson(person);
			return marketRunnerRole;
		}
		else if (title == "UPSman") {
			//Setting previous bank guard role to inactive
			if (UPSmanRole.getPerson() != null) {
				Worker worker = (Worker) UPSmanRole.getPerson();
				worker.goOffWork();
			}
			//Setting bank guard role to new role
			UPSmanRole.setPerson(person);
			return UPSmanRole;
		}
		else
			return null;
	}


	public class Item {
		public String itemName;
		public double  price;
		public int amount;

		Item(String choice, double price, int amount) {
			itemName = choice;
			this.price = price;
			this.amount = amount;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
