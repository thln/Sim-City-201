package americanRestaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

import person.Role;
import americanRestaurant.interfaces.AmericanRestaurantCashier;

/**
 * Restaurant Cook Agent
 */

public class AmericanRestaurantCookRole extends Role {

	//DATA

	//CookGui cookGui;
	List<Order> PendingOrders;
	List<Order> FinishedOrders;
	//List<MarketAgent> markets;
	HashMap<String,Food> foodList;
	boolean needToOrder;

	public Semaphore inProcess;

	public static class Food {
		String choice;
		int cookingTime;
		int amount;
		int capacity;
		int amountOrdered;
		int price;
		Timer gatherFood;

		Food (String c, int time, int amt, int amtOrd, int cap, int price) {
			choice = c;
			cookingTime = time;
			amount = amt;
			capacity = cap;
			amountOrdered = amtOrd;
			gatherFood = new Timer();
			this.price = price;
		}

		void setAmount  (int amt) {
			this.amount = amt;
		}

		String getChoice (){
			return choice;
		}
	}


	//Constructor

	public AmericanRestaurantCookRole (){
		super();
		PendingOrders = Collections.synchronizedList(new ArrayList<Order>());
		FinishedOrders = Collections.synchronizedList(new ArrayList<Order>());	
		foodList = new HashMap<>();
		foodList.put("Steak", new Food("Steak", 5000, 1, 3, 5, 5));
		foodList.put("Chicken", new Food("Chicken", 4000, 3, 3, 5, 4));
		foodList.put("Salad", new Food("Salad", 3000, 0, 3, 5, 2));
		foodList.put("Pizza", new Food("Pizza", 3500, 4, 3, 5, 3));

		inProcess = new  Semaphore(0, true);

		//Constructing market agents
		markets = Collections.synchronizedList (new ArrayList<MarketAgent>());
		AddMarket();
		AddMarket();
		AddMarket();
		needToOrder = true;

		for (MarketAgent m1: markets)
			m1.startThread();
	}

	//Copy Constructor
	public AmericanRestaurantCookRole (AmericanRestaurantCookRole c){
		this.PendingOrders = c.PendingOrders;
		this.FinishedOrders = c.FinishedOrders;
	}

	static public class Order {
		String choice;
		AmericanRestaurantWaiterRole waiter1;
		Timer t1;
		AmericanRestaurantTable tab;
		int finishTime = 0;

		Order(){
			t1 = new Timer();
		}

		Order (String c1, AmericanRestaurantWaiterRole wait1, AmericanRestaurantTable tab1)
		{
			this.choice = c1;
			this.waiter1 = wait1;
			if (choice == "Steak")
				this.finishTime = 5500;
			if (choice == "Chicken")
				this.finishTime = 5000;
			if (choice == "Salad")
				this.finishTime = 3500;
			if (choice == "Pizza")
				this.finishTime = 4500;	

			t1 = new Timer();
			this.tab = tab1;
		}
	}

	// GUI MESSAGES

	public void msgAtCookingArea () {
		inProcess.release();
	}

	public void msgAtPlatingArea () {
		inProcess.release();
	}

	// MESSAGES

	public void msgHereIsAnOrder (Order order1){	
		PendingOrders.add(order1);
		stateChanged();
	}

	public void msgHereIsYourOrder(Food f1) {
		String choice = f1.choice;
		foodList.put(choice, f1);
		AmericanRestaurantWaiterRole.AddItem(f1.choice);
		Do("Bought more " + f1.choice);
	}

	public void msgHereIsPartOfYourOrder(Food f1) {
		String choice = f1.choice;
		foodList.put(choice, f1);
		AmericanRestaurantWaiterRole.AddItem(f1.choice);
		Do("Bought more " + f1.choice);
	}

	//SCHEDULER

	protected boolean pickAndExecuteAnAction() {

		synchronized(FinishedOrders) {
			for (Order order1: FinishedOrders) {
				FinishOrder(order1);
				return false;
			}
		}

		synchronized(PendingOrders) {
			for (Order order1: PendingOrders) {
				CookOrder(order1);
				return false;
			}
		}

		synchronized(markets) {
			if (needToOrder) {
				for (String key: foodList.keySet()) {
					if (foodList.get(key).amount <= 1) {
						ContactMarket(foodList.get(key));
					}	
				}
				needToOrder = false;
			}
		}
		return false;
	}

	// ACTIONS

	void CookOrder (final Order order1){

		PendingOrders.remove(order1);
		Food f = foodList.get(order1.choice);
		if (f.amount <= 1) {
			needToOrder = true;	
			stateChanged();
		}

		if (f.amount == 0) {
			Do("I'm out of food " + f.choice);
			AmericanRestaurantWaiterRole.RemoveItem(f.choice);
			order1.waiter1.msgOutOfFood(order1);		
			return;
		}

		f.amount--;
		Do("Cooking " + order1.choice);

		if (!(cookGui.getXPos() == cookGui.xFridgeArea && cookGui.getXPos() == cookGui.yFridgeArea)){
			cookGui.DoGoToFridge();
			try {
				inProcess.acquire();			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		order1.t1.schedule(new TimerTask() {		//Start timer
			public void run() {	
				FinishedOrders.add(order1);
				stateChanged();
			}
		}, order1.finishTime);
	}

	void FinishOrder (Order order1){
		Do(order1.choice + " is finished");
		order1.waiter1.msgOrderIsReady(order1.tab, order1.choice);
		if (!(cookGui.getXPos() == cookGui.xPlatingArea && cookGui.getXPos() == cookGui.yPlatingArea)){
			cookGui.DoGoPlate();
			try {
				inProcess.acquire();			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		FinishedOrders.remove(order1);
	}

	void AddMarket () {
		markets.add(new MarketAgent());
		int i = markets.size()-1;
		//set inventory of market agent
		if (i == 0) {
			markets.get(i).setInventory("Steak", 1);
			markets.get(i).setInventory("Chicken", 1);
			markets.get(i).setInventory("Salad", 5);
			markets.get(i).setInventory("Pizza", 0);
		}

		if (i == 1) {
			markets.add(new MarketAgent());
			markets.get(i).setInventory("Steak", 2);
			markets.get(i).setInventory("Chicken", 3);
			markets.get(i).setInventory("Salad", 0);
			markets.get(i).setInventory("Pizza", 4);
		}

		if (i == 2){
			markets.add(new MarketAgent());
			markets.get(i).setInventory("Steak", 5);
			markets.get(i).setInventory("Chicken", 0);
			markets.get(i).setInventory("Salad", 6);
			markets.get(i).setInventory("Pizza", 2);
		}
	}

	void ContactMarket(Food f1) {
		for (MarketAgent m1: markets) {
			int marketAmount = m1.getInventory().get(f1.choice).amount;
			if (marketAmount >= f1.amountOrdered) {
				Do("Ordering " + f1.choice + " from market.");
				m1.msgHereIsAnOrder(f1, this);					//Order food and move on
				break;
			}
			else if (marketAmount > 0) {	//If this market has more than one of the desired item
				Do("Ordering partial " + f1.choice + "  from market.");
				f1.amountOrdered -= marketAmount;
				m1.msgHereIsAnOrder(f1, this);					//Order food and ask another market 
			}
			else {
				Do("Market empty, trying another");					//Otherwise, continue looping
			}
		}

		return;
	}

	public void setGui(CookGui g) {
		cookGui = g;
	}

	public void setCashier(AmericanRestaurantCashier myCashier) {
		for (MarketAgent m1: markets) {
			m1.setCashier(myCashier);
		}
		return;
	}
}

