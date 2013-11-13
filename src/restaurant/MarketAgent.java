package restaurant;

import agent.Agent;
import restaurant.interfaces.Market;
import java.util.*;

/**
 * Restaurant Cook Agent
 */

public class MarketAgent extends Agent implements Market{

	private String name;
	//private Semaphore atTable = new Semaphore(0,true);

	private CookAgent cook;
	private CashierAgent cashier;

	enum StockState {open, processing, fulfilled};
	
	Timer timer = new Timer();
	
	//Keeps a list of orders
	private List<Stock> myOrders = Collections.synchronizedList(new ArrayList<Stock>());

	private Map<String, Integer> Inventory = new HashMap<String, Integer>(); {
		Inventory.put("Chicken", 2);
		Inventory.put("Steak", 2);
		Inventory.put("Pizza", 2);
		Inventory.put("Salad", 2);
	}

	public MarketAgent(String name, CookAgent cook, CashierAgent cashier) {
		super();

		this.name = name;
		this.cook = cook;
		this.cashier = cashier;
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
	public void msgOutofItems(String choice, int orderAmount) {
		synchronized(myOrders){
			print("Received an order for " + orderAmount + " " + choice + "(s)");
			myOrders.add(new Stock(choice, orderAmount));
			stateChanged();
		}
	}
	
	public void msgPayment(double payment, CashierAgent cashier) {
		print("Received a payment of $" + payment + " from " + cashier.getName());
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
		synchronized(myOrders){
			if (!myOrders.isEmpty()) {
				for (Stock order: myOrders) {
					if (order.state == StockState.open) {
						checkInventory(order);
						return true;
					}
					else if (order.state == StockState.fulfilled) {
						deliverOrder(order);
						return true;
					}

				}
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
	private void checkInventory(final Stock order) {					
		order.state = StockState.processing;
		
		if (Inventory.get(order.choice) == 0) {
			print("Cannot fulfill order of " + order.orderedAmount + " " + order.choice + "(s)");
			cook.msgCantFulfill(order.choice, 0, order.orderedAmount, this);
			myOrders.remove(order);
			return;
		}
		
		timer.schedule(new TimerTask() {
			public void run() {
				order.state = StockState.fulfilled;
				stateChanged();
			}
		},
		10000);
	}

	private void deliverOrder(Stock order) {
		print("Delivering order to cook for " + order.choice);
		if (Inventory.get(order.choice) >= order.orderedAmount) {
			//Sending cook full amount
			cook.msgOrderFulfillment(order.choice, order.orderedAmount, order.orderedAmount, this);
			cashier.msgOrderFulfilled(order.choice, Inventory.get(order.choice), this);

			//Updating Inventory
			int newAmount = Inventory.get(order.choice) - order.orderedAmount;
			Inventory.put(order.choice, newAmount);
			myOrders.remove(order);
		}
		else //if (Inventory.get(order.choice) < order.quantity)
		{
			//Sending cook what is left in inventory
			cook.msgOrderFulfillment(order.choice, Inventory.get(order.choice), order.orderedAmount, this);
			cashier.msgOrderFulfilled(order.choice, Inventory.get(order.choice), this);
			Inventory.put(order.choice, 0);
			myOrders.remove(order);
		}	
	}

	//Utilities
	public int getSteak() {
		return Inventory.get("Steak");
	}
	
	public int getChicken() {
		return Inventory.get("Chicken");
	}
	
	public int getSalad() {
		return Inventory.get("Salad");
	}
	
	public int getPizza() {
		return Inventory.get("Pizza");
	}

	public class Stock {
		String choice;
		int orderedAmount;
		StockState state = StockState.open;
		
		Stock(String choice, int orderedAmount) {
			this.choice = choice;
			this.orderedAmount = orderedAmount;
		}
	}

}

