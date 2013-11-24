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
	public HashMap<String, Product> marketItemsForSale = new HashMap<String, Product>(); 
	{
		//For people
		marketItemsForSale.put("Car", new Product("Car", 1000.00));
		marketItemsForSale.put("Pasta", new Product("Pasta", 1.99));
		marketItemsForSale.put("Ice Cream", new Product("Ice Cream", 4.99));
		marketItemsForSale.put("Chips", new Product("Chips", 2.99));
		marketItemsForSale.put("Milk", new Product("Milk", 2.50));
		marketItemsForSale.put("Eggs", new Product("Eggs", 1.50));
		marketItemsForSale.put("Lobster", new Product("Lobster", 12.99));
		marketItemsForSale.put("Cheese", new Product("Cheese", 3.99));
	}

	public HashMap<String, Item> inventory = new HashMap<String, Item>(); {
		//For people
		inventory.put("Car", new Item("Car", 1000.00, 1000));
		inventory.put("Pasta", new Item("Pasta", 1.99, 1000));
		inventory.put("Ice Cream", new Item("Ice Cream", 4.99, 1000));
		inventory.put("Chips", new Item("Chips", 2.99, 1000));
		inventory.put("Milk", new Item("Milk", 2.50, 1000));
		inventory.put("Eggs", new Item("Eggs", 1.50, 1000));
		inventory.put("Lobster", new Item("Lobster", 12.99, 1000));
		inventory.put("Cheese", new Item("Cheese", 3.99, 1000));
		
		//For restaurants
		inventory.put("Chicken", new Item("Chicken", 10.99, 1000));
		inventory.put("Steak", new Item("Steak", 15.99, 1000));
		inventory.put("Pizza", new Item("Pizza", 8.99, 1000));
		inventory.put("Salad", new Item("Salad", 5.99, 1000));
	}

	//Constructor
	public Market(String name) 
	{
		this.name = name;
	}


	//Methods
	public Role arrivedAtWork(Person person, String title) 
	{
		if (title == "salesPerson") 
		{
			//Setting previous bank guard role to inactive
			if (salesPersonRole.getPerson() != null) 
			{
				Worker worker = (Worker) salesPersonRole.getPerson();
				worker.roleFinishedWork();
			}
			//Setting bank guard role to new role
			salesPersonRole.setPerson(person);
			return salesPersonRole;
		}
		else if (title == "marketRunner") 
		{
			//Setting previous bank guard role to inactive
			if (marketRunnerRole.getPerson() != null) 
			{
				Worker worker = (Worker) marketRunnerRole.getPerson();
				worker.roleFinishedWork();
			}
			//Setting bank guard role to new role
			marketRunnerRole.setPerson(person);
			return marketRunnerRole;
		}
		else if (title == "UPSman") {
			//Setting previous bank guard role to inactive
			if (UPSmanRole.getPerson() != null) {
				Worker worker = (Worker) UPSmanRole.getPerson();
				worker.roleFinishedWork();
			}
			//Setting bank guard role to new role
			UPSmanRole.setPerson(person);
			return UPSmanRole;
		}
		else
			return null;
	}


	public class Item 
	{
		public String itemName;
		public double  price;
		public int amount;

		Item(String choice, double price, int amount) 
		{
			itemName = choice;
			this.price = price;
			this.amount = amount;
		}
		
		public void setInventory(int newAmount) 
		{
			this.amount = newAmount;
		}
	}
	
	public class Product 
	{
		public String itemName;
		public double  price;

		public Product (String choice, double price) 
		{
			itemName = choice;
			this.price = price;
		}
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
}
