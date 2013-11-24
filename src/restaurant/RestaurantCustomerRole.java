package restaurant;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import java.util.concurrent.Semaphore;

import person.Person;
import person.Role;

/**
 * Restaurant customer agent.
 */
public class RestaurantCustomerRole extends Role {
	private int hungerLevel = 5;        // determines length of meal
	Timer timer = new Timer();
	protected String RoleName = "Restaurant Customer";
	//private CustomerGui customerGui;
	int xHome, yHome;

	private Menu menu;

	//Agent Correspondents
	private HostRole hostRole;
	private WaiterRole waiterRole;
	private CashierRole cashierRole;


	// private boolean isHungry = false; //hack for gui
	public enum AgentState
	{DoingNothing, TablesFull, DecidingToStayInRestaurant, WaitingInRestaurant, BeingSeated, DecidingChoice, DoneDeciding, Ordered, Eating, DoneEating, AskedForCheck, PayingCheck, PayedCheck, inJail, Leaving};
	private AgentState state = AgentState.DoingNothing;//The start state

	public enum AgentEvent 
	{none, gotHungry, followHost, seated, askedToOrder, gotFood, doneEating, gotCheck, atCashier, gotChange, sentToJail, doneLeaving};
	AgentEvent event = AgentEvent.none;

	private int tableNumber;
	private double check;
	private String choice;
	private double money;

	/**
	 * Constructor for CustomerAgent class
	 *
	 * @param name name of the customer
	 * @param gui  reference to the customergui so the customer can send it messages
	 */
	public RestaurantCustomerRole(Person p1, String pName, String rName) {
		super(p1, pName, rName);
	/*
		if (name.equals("Broke 1")) {
			money = 0;
		}
		else if (name.equals("Broke 2")) {
			money = 6;
		}
		else if (name.equals("Broke 3")) {
			money = 100;
		}
		else {
			money = 50;
		}
		
		these need to just pass in money, or become dishonest if instanceof crook
		*/
		state = AgentState.DoingNothing; 
		event = AgentEvent.gotHungry;
	}

	/**
	 * hack to establish connection to Host agent.
	 */
	public void setHost(HostRole hostRole) {
		this.hostRole = hostRole;
	}

	public String getCustomerName() {
		return personName;
	}



	/**
	 * Messages
	 */
	public void gotHungry(int xHome, int yHome) {//from animation
		System.out.println("I'm hungry");
		this.xHome = xHome;
		this.yHome = yHome;
		event = AgentEvent.gotHungry;
		stateChanged();
	}

	public void msgTablesAreFull() {
		System.out.println("Got message that all tables are full");
		state = AgentState.TablesFull;
		stateChanged();
	}
	
	public void msgPleaseFollowMe(int tableNumber, Menu menu, WaiterRole waiterRole) {
		this.tableNumber = tableNumber;
		this.menu = menu;
		this.waiterRole = waiterRole; //to establish connection to waiter
		System.out.println("Received msgPleaseFollowMe from " + waiterRole.getName());
		event = AgentEvent.followHost;
		stateChanged();
	}

	public void msgAnimationFinishedGoToSeat() { //from animation
		event = AgentEvent.seated;
		stateChanged();
	}

	public void msgWhatWouldYouLike() {
		event = AgentEvent.askedToOrder;
		stateChanged();
	}

	public void msgPleaseReorder(Menu newMenu) {
		this.menu = newMenu;
		event = AgentEvent.seated;
		state = AgentState.BeingSeated;
		stateChanged();
	}

	public void msgHeresYourOrder (String choice) {
		this.choice = choice;
		event = AgentEvent.gotFood;
		stateChanged();
	}

	public void msgHeresYourCheck(double check) {
		this.check = check;
		event = AgentEvent.gotCheck;
		stateChanged();
	}

	public void msgAnimationFinishedGoToCashier() { //from animation
		event = AgentEvent.atCashier;
		stateChanged();
	}

	public void msgHeresYourChange(double change) {
		event = AgentEvent.gotChange;
		money = change;
		System.out.println("Recieved my change of $" + change + ". I have now $" + money);
		stateChanged();
	}
	
	public void msgGoToJail() {
		event = AgentEvent.sentToJail;
		stateChanged();
	}

	public void msgAnimationFinishedLeaveRestaurant() { //from animation
		event = AgentEvent.doneLeaving;
		stateChanged();
	}



	/**
	 * Scheduler
	 */
	protected boolean pickAndExecuteAnAction() {
		//	CustomerAgent is a finite state machine

		if (state == AgentState.DoingNothing && event == AgentEvent.gotHungry ){
			goToRestaurant();
			return true;
		}
		if (state == AgentState.TablesFull && event == AgentEvent.gotHungry) {
			DecidingToStay();
			return true;
		}
		if (state == AgentState.WaitingInRestaurant && event == AgentEvent.followHost ) {
			SitDown();
			return true;
		}
		if (state == AgentState.BeingSeated && event == AgentEvent.seated) {
			MakeChoice();
			return true;
		}
		if (state == AgentState.DoneDeciding && event == AgentEvent.askedToOrder) {
			PlaceOrder();
			return true;
		}
		if (state == AgentState.Ordered && event == AgentEvent.gotFood) {
			EatFood();
			return true;
		}		
		if (state == AgentState.Eating && event == AgentEvent.doneEating) {
			AskForCheck();
			return true;
		}
		if (state == AgentState.AskedForCheck && event == AgentEvent.gotCheck) {
			PayingCheck();
			return true;
		}
		if (state == AgentState.PayingCheck && event == AgentEvent.atCashier) {
			PayCheck();
			return true;
		}
		if (state == AgentState.PayedCheck && event == AgentEvent.gotChange) {
			LeaveRestaurant();
			return true;
		}
		if (state == AgentState.PayedCheck && event == AgentEvent.sentToJail) {
			GoToJail();
			return true;
		}
		if (state == AgentState.Leaving && event == AgentEvent.doneLeaving) {
			ResetState();
			return true;
		}
		
		return false;
	}



	/**
	 * Actions
	 */
	private void goToRestaurant() {
		state = AgentState.WaitingInRestaurant;
		print("Going to restaurant");
		hostRole.msgIWantFood(this, xHome, yHome);
	}
	
	private void DecidingToStay() {
		state = AgentState.DecidingToStayInRestaurant;

		Random rand = new Random();
		int myRandomChoice;

		myRandomChoice = rand.nextInt(10);
		myRandomChoice %= 2; // 1/2 of the time the customer leaves

		if (myRandomChoice == 0) {
			state = AgentState.Leaving;
			System.out.println("I don't want to wait, bailing from this stupid restaurant");
			//customerGui.DoExitRestaurant();
			stateChanged();
		}
		else {
			state = AgentState.WaitingInRestaurant;
			System.out.println("Decided to stay and eat in restaurant");
			hostRole.msgStaying(this, xHome, yHome);
			stateChanged();
		}
	}

	private void SitDown() {
		state = AgentState.BeingSeated;
		System.out.println("Being seated. Going to table");
		//customerGui.DoGoToSeat(tableNumber);
	}

	private void MakeChoice() {
		state = AgentState.DecidingChoice;
		
		System.out.println("Deciding what I want...");
		timer.schedule(new TimerTask() {
			public void run() {
				
				if (money < menu.lowestPricedItem) {
					System.out.println("I can't afford anything on the menu");
					LeaveRestaurant();
					ResetState();
					return;
				}
				
				Random rand = new Random();
				int myRandomChoice;

				do {
					myRandomChoice = rand.nextInt(10);
					myRandomChoice %= 4;
				} while (!menu.menuMap.containsKey(myRandomChoice) || (money < menu.menuMap.get(myRandomChoice).price));

				choice = menu.menuMap.get(myRandomChoice).choice;
				
				state = AgentState.DoneDeciding;
				AskToOrder();
				//customerGui.DoReadyToOrder();
				stateChanged();
			}
		},
		5000);
	}

	private void AskToOrder() {
		waiterRole.msgReadyToOrder(this);
	}

	private void PlaceOrder() {
		//customerGui.DoPlaceOrder(choice); //GUI call
		state = AgentState.Ordered;
		waiterRole.msgHeresMyOrder(this, choice);
	}

	private void EatFood() {
		state = AgentState.Eating;
		//customerGui.DoEatFood(choice);
		System.out.println("Eating Food");
		//This next complicated line creates and starts a timer thread.
		//We schedule a deadline of getHungerLevel()*1000 milliseconds.
		//When that time elapses, it will call back to the run routine
		//located in the anonymous class created right there inline:
		//TimerTask is an interface that we implement right there inline.
		//Since Java does not all us to pass functions, only objects.
		//So, we use Java syntactic mechanism to create an
		//anonymous inner class that has the public method run() in it.
		timer.schedule(new TimerTask() {
			//Object cookie = 1;
			public void run() {
				//print("Done eating " + choice);
				event = AgentEvent.doneEating;
				//isHungry = false;
				stateChanged();
			}
		},
		10000);//getHungerLevel() * 1000);//how long to wait before running task
	}

	private void AskForCheck() {
		//customerGui.DoAskForCheck();
		state = AgentState.AskedForCheck;
		System.out.println("Asking for my check");
		waiterRole.msgIWantMyCheck(this);
	}

	private void PayingCheck() {
		state = AgentState.PayingCheck;
		System.out.println("Going to the cashier");
		//customerGui.DoGoToCashier();
	}

	private void PayCheck() {
		
		if (personName.equals("Broke 3")) {
			money = 0;
		}
		

		System.out.println("Paying my check of: " + check);
		System.out.println("I have $" + money);

		state = AgentState.PayedCheck;
		cashierRole.msgPayment(choice, money, this);
		money = 0;
	}

	private void LeaveRestaurant() {
		state = AgentState.Leaving;

		System.out.println("Leaving.");
		waiterRole.msgLeavingTable(this);
		//customerGui.DoExitRestaurant();
	}
	
	private void GoToJail() {
		state = AgentState.inJail;
		waiterRole.msgLeavingTable(this);
		//customerGui.DoGoToJail();
		
		timer.schedule(new TimerTask() {
			public void run() {
				walkOfShame();
			}
		},
		5000);
	}
	
	private void walkOfShame() {
		state = AgentState.Leaving;
		//customerGui.DoExitRestaurant();
		stateChanged();
	}

	private void ResetState() {
		state = AgentState.DoingNothing;
	}



	//Utilities

	public int getHungerLevel() {
		return hungerLevel;
	}

	public void setHungerLevel(int hungerLevel) {
		this.hungerLevel = hungerLevel;
		//could be a state change. Maybe you don't
		//need to eat until hunger lever is > 5?
	}

	public String toString() {
		return "customer " + getName();
	}

	/*
	public void setGui(CustomerGui g) {
		customerGui = g;
	}*/

	public void setCashier(CashierRole cashierRole) {
		this.cashierRole = cashierRole;
	}

	/*
	public CustomerGui getGui() {
		return customerGui;
	}*/
	
	public double getMoney() {
		return money;
	}
}

