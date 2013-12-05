package italianRestaurant;

import person.*;
import application.gui.animation.agentGui.*;
import italianRestaurant.interfaces.*;

import java.util.*;
import java.util.concurrent.Semaphore;

/**
 * Restaurant Cook Agent
 */
//We only have 2 types of agents in this prototype. A customer and an agent that
//does all the rest. Rather than calling the other agent a waiter, we called him
//the CookAgent. A Cook is the manager of a restaurant who sees that all
//is proceeded as he wishes.
public class ItalianCookRole extends Role implements ItalianCook{
	static final int NTABLES = 5;//a global for the number of tables.
	//Notice that we implement waitingCustomers using ArrayList, but type it
	//with List semantics.
	Random rn = new Random();
	boolean AllowedtoOrder = true;
	private Timer cookingtimer = new Timer();
	private Semaphore atMarket = new Semaphore(0, true);
	private Semaphore atFridge = new Semaphore(0, true);
	private List<Order> Orders = Collections.synchronizedList(new ArrayList<Order>());
	public List<ItalianMarket> Markets = Collections.synchronizedList(new ArrayList<ItalianMarket>());
	public List<ItalianMarket> visitedMarkets = Collections.synchronizedList(new ArrayList<ItalianMarket>());
	private List<Food> Foods = Collections.synchronizedList(new ArrayList<Food>());
	
	private ItalianRestaurant restaurant = null;
	//note that tables is typed with Collection semantics.
	//Later we will see how it is implemented
	 
	//private Semaphore atTable = new Semaphore(0,true);
	
	public ItalianCookGui cookGui = null;

	public ItalianCookRole(String name, ItalianRestaurant restaurant) {
		super(name);
		this.restaurant = restaurant;
		//initializing food inventories when application starts
		/*
		Foods.add(new Food("Steak", 5, 4, 3)); //name, cooktime, inventory, low
		Foods.add(new Food("Chicken", 2, 3, 2)); 
		Foods.add(new Food("Salad", 3, 2, 1));
		Foods.add(new Food("Pizza", 4, 3, 2));
		*/
		
		
		Foods.add(new Food("Steak", 5, 12, 3)); //name, cooktime, inventory, low
		Foods.add(new Food("Chicken", 2, 10, 2)); 
		Foods.add(new Food("Salad", 3, 10, 1));
		Foods.add(new Food("Pizza", 4, 12, 2));
		
	}

	// Messages
	public void msgHereIsCustOrder(ItalianWaiter w, String choice, int table) {
		synchronized(Foods) {
		for(int i=0; i<Foods.size();i++) {
			if(Foods.get(i).type.equals(choice)) {
				Orders.add(new Order(w, Foods.get(i), table));
				print("Received order from " + w + " for " + Foods.get(i));
				stateChanged();
			}
		}
		}
	}
	
	public void FoodDone(Order o) {
		o.s = OrderState.done;
		print(o.food + " is Done!");
		System.out.print(o.food.inventory + "\n");
		stateChanged();
		
	}
	
	//message from market saying food is restocked and delivered to cook
	public void msgOrderDone(ItalianMarket market, String foodname, int foodamt){
		
		synchronized(Foods) {
		for(int i=0; i<Foods.size();i++) {
			if(Foods.get(i).type.equals(foodname)){
				Foods.get(i).inventory+= foodamt;
				//Foods.get(i).inventory++;
				//Foods.get(i).inventory = Foods.get(i).capacity;
				
				if(Foods.get(i).inventory == Foods.get(i).capacity) {
					print("Restocked " + foodname + " to capacity from the ItalianMarket!");
					//if any markets were visited, refresh the list for reuse to restock other foods
					for(int n=0; n<visitedMarkets.size();n++) {
						Markets.add(visitedMarkets.get(n));
					}
					visitedMarkets.clear();
					
					Foods.get(i).fs = FoodState.inStock;
					System.out.print(Foods.get(i).inventory);
					stateChanged();
				}
				else {
					print("Order partially filled. Restocking the rest from another market");
					Markets.remove(market);
					Foods.get(i).fs = FoodState.isLow;
					stateChanged();
				}
			}
		}
		}
	}
	
	/*message from market saying they are out of stock
	 * of the certain food item and that cook has to go to
	 * a different market for restocking
	 */
	public void msgOutofChoice(ItalianMarket market, String foodname){
		
		print("I received no " + foodname);
		
		Markets.remove(market);
		synchronized(Foods){
		for(int i=0; i<Foods.size();i++) {
			if(Foods.get(i).type.equals(foodname)){
				Foods.get(i).fs = FoodState.isLow;
				stateChanged();
			}
			}
		}
	}
	
	public void msgatMarket() {
		//System.out.print("REELEASEED!");
		atMarket.release();
		stateChanged();
	}
	public void msgatFridge() {
		atFridge.release();
		stateChanged();
	}
	
	public void setAllowedOrder(boolean allowed) {
		AllowedtoOrder = allowed;
	}
	
	
	/**
	 * Scheduler.  Determine what action is called for, and do it.
	 */
	protected boolean pickAndExecuteAnAction() {
		/* Think of this next rule as:
            Does there exist a table and customer,
            so that table is unoccupied and customer is waiting.
            If so seat him at the table.
		 */
		
		if(Orders != null) {
			synchronized(Orders) {
			for(int i=0; i<Orders.size();i++){
				if(Orders.get(i).s == OrderState.done) {
					TryPlateIt(Orders.get(i));
					return true;
				}
				else if(Orders.get(i).s == OrderState.pending) {
						TryCookIt(Orders.get(i));
						return true;				
				}
			}
			}
		}
		
		//checks whether cook is allowed to order (determined by cashier)
		if(AllowedtoOrder == true) {
			
			//goes to market to restock as soon as inventory levels have hit a low level
			synchronized(Foods) {
				for(int n=0; n<Foods.size();n++){
					if((Foods.get(n).inventory == Foods.get(n).low && Foods.get(n).fs == FoodState.inStock) || Foods.get(n).fs == FoodState.isLow) {
						OrderFoodThatLow(Foods.get(n));
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

	// Actions
	
	
	private void TryCookIt(final Order o) {
		if(o.food.inventory == 0) {
			print("OUT of " + o.food);
			o.w.msgFoodOut(o.food.type, o.table);
			o.s = OrderState.finished;
		}
		else {
			print("Cooking " + o);
			System.out.print(o.food.inventory + "\n");
			cookGui.GoToFridge();
			try {
				atFridge.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cookGui.DoCooking(o.food.type, o.table);
			o.s = OrderState.cooking;
			o.food.inventory--;
		
			cookingtimer.schedule(new TimerTask() {
				public void run() {
					FoodDone(o);
				}
			},
			o.food.cookingTime*1000);
		}
		
	}
	
	private void TryPlateIt(Order o) {
			print("Plating Food");
			cookGui.DoPlateIt(o.food.type, o.table);
			o.w.msgOrderDone(o.food.type, o.table);
		o.s = OrderState.finished;
	}
	
	private void OrderFoodThatLow(Food f){
		if(Markets.size()>0) {
			if(f.fs == FoodState.inStock)
				print("Ordering " + f + " that is low");
			else if(f.fs == FoodState.isLow) {
				//print("Ordering " + f + " that is low");
			}
		cookGui.GotoMarket();
	
		try {
			atMarket.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Markets.get(0).msgOrderforMarket(this, f.type, f.capacity-f.inventory);
		visitedMarkets.add(Markets.get(0));
		f.fs = FoodState.ordered;
		}
		else {
			print("NO markets left to restock from");
			f.fs = FoodState.ordered;
		}
	}

	//utilities
	public String toString() {
		return "Cook " + getName();
	}
	
	public void setGui(ItalianCookGui gui) {
		cookGui = gui;
	}

	public ItalianCookGui getGui() {
		return cookGui;
	}
	
	public void addMarket(ItalianCashier cashier) {
		int i = rn.nextInt(100);
		String randName = Integer.toString(i);
		ItalianMarketRole market = new ItalianMarketRole(randName);
		market.setCashier(cashier);
		Markets.add(market);
		//market.startThread();
		print("added ItalianMarket \"" + randName + "\"");
	}
	
	/*Order class stores the different customer orders
	 * the waiter gives to the cook, and its state
	 * of cooking on the cook's "grill".
	 */
	
	private enum OrderState {pending, cooking, done, finished};
	
	private class Order {
		ItalianWaiter w;
		Food food;
		int table;
		OrderState s;
		Order(ItalianWaiter waiter, Food f, int t) {
			w = waiter;
			food = f;
			table = t;
			s = OrderState.pending;
		}
		
		public String toString() {
			return "Order " + food;
		}
	}
	
	private enum FoodState {inStock, isLow, ordered, orderingAgain, delivered};
	private class Food {
		String type;
		int cookingTime;
		int low;
		int inventory;
		int capacity;
		FoodState fs;
		
		Food(String choice, int time, int invent, int lownum) {
			type = choice;
			cookingTime = time;
			inventory = invent;
			low = lownum;
			capacity = 20;
			fs = FoodState.inStock;
		}
		
		public String toString() {
			return type;
		}
	}

}

