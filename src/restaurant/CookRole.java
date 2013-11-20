package restaurant;

//import agent.Agent;
//import restaurant.RestaurantCustomer.AgentState;
//import restaurant.MarketAgent.Stock;

import java.util.*;
import java.util.concurrent.Semaphore;

import person.Person;
import person.Role;

/**
 * Restaurant Cook Role
 * Currently not printing
 * No current Cook Gui
 */

public class CookRole extends Role {

	private String name;
	private Semaphore atDestination = new Semaphore(0,true);
	protected String roleName = "Cook";

	//public CookGui cookGui = null;

	Timer timer = new Timer();
	private int cookTime;
	private RevolvingStand theRevolvingStand;

	int inventoryChecker = 0;

	private List<Order> myOrders = Collections.synchronizedList(new ArrayList<Order>());
	private List<myMarket> markets = Collections.synchronizedList(new ArrayList<myMarket>());
	private List<Stock> stockFulfillment = Collections.synchronizedList(new ArrayList<Stock>());

	private Map<String, Food> foodMap = new HashMap<String, Food>(); {
		foodMap.put("Chicken", new Food("Chicken"));
		foodMap.put("Steak", new Food("Steak"));
		foodMap.put("Pizza", new Food("Pizza"));
		foodMap.put("Salad", new Food("Salad"));
	}

	public CookRole(Person p1, String pName, String rName) {
		super(p1, pName, rName);
	}

	public CookRole(String roleName) {
		super(roleName);
	}

	public String getMaitreDName() {
		return name;
	}

	public String getName() {
		return name;
	}



	/**
	 * Messages
	 */
	public void msgHeresAnOrder(int table, String choice, WaiterRole waiterRole) {
		synchronized(myOrders){

			print("Order received for table " + table);
			Order order = new Order(table, choice, waiterRole);
			myOrders.add(order);
			stateChanged();
		}
	}

	public void msgOrderDone(Order order) {
		order.setDone();
		stateChanged();
	}

	public void msgCantFulfill(String choice, int amount, int orderedAmount, MarketAgent market) {
		synchronized(stockFulfillment){
			//print("Market cannot fulfill order for " + choice + "-- Amount: " + amount + " Ordered: " + orderedAmount);
			stockFulfillment.add(new Stock(choice, amount, orderedAmount, market));
			stateChanged();
		}
	}

	public void msgOrderFulfillment(String choice, int amount, int orderedAmount, MarketAgent market) {
		synchronized(stockFulfillment){
			//print("Got fullfillment for " + choice + "-- Amount: " + amount + " Ordered: " + orderedAmount);
			stockFulfillment.add(new Stock(choice, amount, orderedAmount, market));
			stateChanged();
		}
	}

	public void msgAtDestination() {//from animation
		atDestination.release();// = true;
		stateChanged();
	}



	/**
	 * Scheduler
	 */
	protected boolean pickAndExecuteAnAction() {
		/* Think of this next rule as:
            Does there exist a table and customer,
            so that table is unoccupied and customer is waiting.
            If so seat him at the table.
		 */

		inventoryChecker++;

		if (inventoryChecker == 500) {
			checkInventory();
			return true;
		}


		if(!theRevolvingStand.isStandEmpty())
		{
			myOrders.add(theRevolvingStand.takeOrder());
			return true;
		}
		
		synchronized(myOrders){
			if (!myOrders.isEmpty()) {
				for (Order order : myOrders) {
					if (order.isOpen()) {
						cookOrder(order);
						return true;//return true to the abstract agent to re-invoke the scheduler.
					}

					if (order.isDone()) {
						doneCooking(order);
						return true;
					}
				}
			}
		}

		synchronized(stockFulfillment){
			if (!stockFulfillment.isEmpty()) {
				updateStock();
				return true;
			}
		}


		return false;
		//we have tried all our rules and found
		//nothing to do. So return false to main loop of abstract agent
		//and wait.
	}



	/**
	 * Actions
	 */
	private void cookOrder(final Order o) {

		if(!isInStock(o.choice)) {
			checkInventory(o.choice);
			myOrders.remove(o);
			o.waiterRole.msgOrderIsNotAvailable(o.choice, o.tableNumber);
			return;
		}

		/*
		cookGui.DoGetIngredients();
		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
		cookGui.DoGoToGrill();
		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
		cookGui.DoGoToHomePosition();
		*/
		
		foodMap.get(o.choice).quantity--;
		checkInventory(o.choice);

		if (o.choice == "Chicken")
			cookTime = foodMap.get("Chicken").cookTime;
		else if (o.choice == "Steak")
			cookTime =foodMap.get("Steak").cookTime;
		else if (o.choice == "Pizza")
			cookTime = foodMap.get("Pizza").cookTime;
		else //if (o.choice == "Salad")
			cookTime = foodMap.get("Salad").cookTime;

		o.setCooking();
		
		timer.schedule(new TimerTask() {
			public void run() {
				msgOrderDone(o);
			}
		},
		cookTime);

	}

	private void doneCooking(Order o) {
		//print("Done cooking order for table " + o.tableNumber);

		/* GUI stuff
		cookGui.DoPickUpFood();
		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();

		}

		cookGui.DoGoToPlatingArea(o.choice);
		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
		o.waiter.msgOrderIsReady(o.tableNumber, o.choice);
		myOrders.remove(o);
		cookGui.DoGoToHomePosition();
		*/
	}

	public void checkInventory() {
		inventoryChecker = 0;
		checkInventory("Steak");
		checkInventory("Chicken");
		checkInventory("Pizza");
		checkInventory("Salad");
	}

	private void checkInventory(String choice) {
		if (foodMap.get(choice).quantity < foodMap.get(choice).threshold) {
			myMarket myMark = null;
			for(myMarket MM: markets) {
				if (MM.availableChoices.get(choice) == true) {
					myMark = MM;
					break;
				}
			}

			if (myMark == null) {
				//print("Out of markets to order from for " + choice);
				return;
			}

			int stockOnHand;
			stockOnHand = foodMap.get(choice).amountOrdered + foodMap.get(choice).quantity;

			//print("Current stock on hand for " + choice + ": " + stockOnHand);

			if (stockOnHand < foodMap.get(choice).threshold) {
				int orderAmount;
				orderAmount = foodMap.get(choice).capacity - stockOnHand;
				foodMap.get(choice).amountOrdered = orderAmount;

				//print("Requesting " + myMark.market.getName() + " for " + orderAmount + " " + choice + "(s)");
				//myMark.market.msgOutofItems(choice, orderAmount);
				//CHEF AND MARKET
			}
		}
	}

	private void updateStock() {
		//No stock is fulfilled
		if (stockFulfillment.get(0).quantity == 0) {
			for (myMarket MM: markets) {
				if (MM.market.equals(stockFulfillment.get(0).market)) {
					foodMap.get(stockFulfillment.get(0).choice).amountOrdered -= stockFulfillment.get(0).orderedAmount;

					//Setting order availability for the choice at market to false
					MM.availableChoices.put(stockFulfillment.get(0).choice, false);
					//print(MM.market.getName() + " is out of " + stockFulfillment.get(0).choice);
				}
			}
			//Check inventory to re-order
			//print("Re-ordering from different market for " + stockFulfillment.get(0).choice);
			checkInventory(stockFulfillment.get(0).choice);
			stockFulfillment.remove(0);
		}
		else {
			//Updating stock
			foodMap.get(stockFulfillment.get(0).choice).quantity += stockFulfillment.get(0).quantity;
			foodMap.get(stockFulfillment.get(0).choice).amountOrdered -= stockFulfillment.get(0).quantity;

			//If only part of the order was fulfilled
			if (stockFulfillment.get(0).quantity < stockFulfillment.get(0).orderedAmount) {
				//Finding market
				for (myMarket MM: markets) {
					if (MM.market.equals(stockFulfillment.get(0).market)) {
						//Setting order availability for the choice at market to false
						MM.availableChoices.put(stockFulfillment.get(0).choice, false);
						//print(MM.market.getName() + " is out of " + stockFulfillment.get(0).choice);
					}
				}
				
				//Order only partially fulfilled, ordering from a new market
				//print("Order partially fulfilled, re-ordering from different market for " + stockFulfillment.get(0).choice);
				
				myMarket myMark = null;
				for(myMarket MM: markets) {
					if (MM.availableChoices.get(stockFulfillment.get(0).choice) == true) {
						myMark = MM;
						break;
					}
				}

				if (myMark == null) {
					//print("Out of markets to order from for " + stockFulfillment.get(0).choice);
					stockFulfillment.remove(0);
					return;
				}
				
				int newOrderAmount;
				newOrderAmount = stockFulfillment.get(0).orderedAmount - stockFulfillment.get(0).quantity;
				//print("Requesting " + myMark.market.getName() + " for " + newOrderAmount + " " + stockFulfillment.get(0).choice + "(s)");
				//CHEF AND MARKET
				//myMark.market.msgOutofItems(stockFulfillment.get(0).choice, newOrderAmount);
			}
			
			stockFulfillment.remove(0);
		}
	}


	//Utilities

	/* GUI STUFF
	public void setGui(CookGui gui) {
		cookGui = gui;
	}

	public CookGui getGui() {
		return cookGui;
	}
	*/
	
	private boolean isInStock(String choice) {
		if (foodMap.get(choice).quantity > 0)
			return true;
		else
			return false;
	}

	public void addMarket(MarketAgent market) {
		markets.add(new myMarket(market));
	}

	public void deleteInventory() {
		foodMap.get("Chicken").quantity = 0;
		foodMap.get("Steak").quantity = 0;
		foodMap.get("Salad").quantity = 0;
		foodMap.get("Pizza").quantity = 0;
	//	print("Deleted all food inventory");
	}

	public void setRevolvingStand(RevolvingStand rs)
	{
		theRevolvingStand = rs;
	}
	
	//Food Class
	public class Food {
		String foodType;
		int	cookTime;
		int quantity = 10;
		int capacity = 10;
		int threshold = 2;
		int amountOrdered = 0;

		Food(String choice) {
			foodType = choice;

			if (choice == "Chicken")
				cookTime = 5000;
			else if (choice == "Steak")
				cookTime = 5000;
			else if (choice == "Pizza")
				cookTime = 5000;
			else if (choice == "Salad")
				cookTime = 5000;
		}
	}

	public class Stock {
		String choice;
		int quantity;
		int orderedAmount;
		MarketAgent market;

		Stock(String choice, int quantity, int orderedAmount, MarketAgent market) {
			this.choice = choice;
			this.quantity = quantity;
			this.market = market;
			this.orderedAmount = orderedAmount;
		}
	}

	public class myMarket {
		MarketAgent market;
		Map<String, Boolean> availableChoices = new HashMap<String, Boolean>(); {
			availableChoices.put("Chicken", true);
			availableChoices.put("Steak", true);
			availableChoices.put("Pizza", true);
			availableChoices.put("Salad", true);
		}

		myMarket(MarketAgent market) {
			this.market = market;
		}
	}
}

