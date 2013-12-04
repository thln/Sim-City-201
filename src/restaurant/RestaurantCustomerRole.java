package restaurant;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import person.Person;
import person.Person.HungerLevel;
import person.Role;
import restaurant.interfaces.Host;
import restaurant.interfaces.RestaurantCustomer;
import restaurant.interfaces.Waiter;
import application.Phonebook;
import application.gui.animation.agentGui.RestaurantCustomerGui;

/**
 * Restaurant customer agent.
 */
public class RestaurantCustomerRole extends Role implements RestaurantCustomer {
	private int hungerLevel = 5;        // determines length of meal
	Timer timer = new Timer();
	protected String RoleName = "Restaurant Customer";

	int xHome, yHome;
	
	private Menu menu;

	private WaiterRole waiterRole;


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
	 * Constructor for RestaurantCustomer class
	 *
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
		this.money = p1.money;
		gui = new RestaurantCustomerGui(this);
	}

	public String getCustomerName() {
		return personName;
	}



	/**
	 * Messages
	 */
	public void msgComeIn() {
		stateChanged();
	}
	
	public void gotHungry(int xHome, int yHome) {//from animation
		print("I'm hungry");
		this.xHome = xHome;
		this.yHome = yHome;
		event = AgentEvent.gotHungry;
		stateChanged();
	}

	public void msgTablesAreFull() {
		print("Got message that all tables are full");
		state = AgentState.TablesFull;
		stateChanged();
	}
	
	public void msgPleaseFollowMe(int tableNumber, Menu menu, WaiterRole waiterRole) {
		this.tableNumber = tableNumber;
		this.menu = menu;
		this.waiterRole = waiterRole; //to establish connection to waiter
		print("Received msgPleaseFollowMe from " + waiterRole.getName());
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
		print("Recieved my change of $" + change + ". I have now $" + money);
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

		if (state == AgentState.DoingNothing && event == AgentEvent.gotHungry ) {
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
		if (state == AgentState.Ordered && event == AgentEvent.gotFood)
		{
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
		Phonebook.getPhonebook().getRestaurant().hostRole.msgIWantFood(this, xHome, yHome);
	}
	
	private void DecidingToStay() {
		RestaurantCustomerGui customerGui = (RestaurantCustomerGui) gui;
		state = AgentState.DecidingToStayInRestaurant;

		Random rand = new Random();
		int myRandomChoice;

		myRandomChoice = rand.nextInt(10);
		myRandomChoice %= 2; // 1/2 of the time the customer leaves

		if (myRandomChoice == 0) {
			state = AgentState.Leaving;
			print("I don't want to wait, bailing from this stupid restaurant");
			customerGui.DoExitRestaurant();
			stateChanged();
		}
		else {
			state = AgentState.WaitingInRestaurant;
			print("Decided to stay and eat in restaurant");
			Phonebook.getPhonebook().getRestaurant().hostRole.msgStaying(this, xHome, yHome);
			stateChanged();
		}
	}

	private void SitDown() {
		RestaurantCustomerGui customerGui = (RestaurantCustomerGui) gui;
		state = AgentState.BeingSeated;
		print("Being seated. Going to table");
		customerGui.DoGoToSeat(tableNumber);
		event = AgentEvent.seated;			//to delete
	}

	private void MakeChoice() {
		RestaurantCustomerGui customerGui = (RestaurantCustomerGui) gui;
		state = AgentState.DecidingChoice;
		
		print("Deciding what I want...");
		timer.schedule(new TimerTask() {
			public void run() 
			{
				RestaurantCustomerGui customerGui = (RestaurantCustomerGui) gui;
				
				if (money < menu.lowestPricedItem) {
					print("I can't afford anything on the menu");
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
				customerGui.DoReadyToOrder();
				stateChanged();
			}
		},
		5000);
	}

	private void AskToOrder() {
		waiterRole.msgReadyToOrder(this);
	}

	private void PlaceOrder() {
		RestaurantCustomerGui customerGui = (RestaurantCustomerGui) gui;
		customerGui.DoPlaceOrder(choice); //GUI call
		state = AgentState.Ordered;
		waiterRole.msgHeresMyOrder(this, choice);
	}

	private void EatFood() {
		RestaurantCustomerGui customerGui = (RestaurantCustomerGui) gui;
		state = AgentState.Eating;
		customerGui.DoEatFood(choice);
		print("Eating Food");
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
				print("Done eating " + choice);
				event = AgentEvent.doneEating;
				//isHungry = false;
				stateChanged();
			}
		},
		10000);//getHungerLevel() * 1000);//how long to wait before running task
	}

	private void AskForCheck() {
		RestaurantCustomerGui customerGui = (RestaurantCustomerGui) gui;
		customerGui.DoAskForCheck();
		state = AgentState.AskedForCheck;
		print("Asking for my check");
		waiterRole.msgIWantMyCheck(this);
	}

	private void PayingCheck() {
		RestaurantCustomerGui customerGui = (RestaurantCustomerGui) gui;
		state = AgentState.PayingCheck;
		print("Going to the cashier");
		
		//This stateChanged() needs to be deleted because gui isn't working
		event = AgentEvent.atCashier;
		stateChanged();
		customerGui.DoGoToCashier();
	}

	private void PayCheck() {
		
		if (personName.equals("Broke 3")) {
			money = 0;
		}
		

		print("Paying my check of: " + check);
		print("I have $" + money);

		state = AgentState.PayedCheck;
		Phonebook.getPhonebook().getRestaurant().cashierRole.msgPayment(choice, money, this);
		money = 0;
	}

	private void LeaveRestaurant() {
		RestaurantCustomerGui customerGui = (RestaurantCustomerGui) gui;
		state = AgentState.Leaving;

		print("Leaving.");
		waiterRole.msgLeavingTable(this);
		
		//This event and state changed should be removed when gui is working
		event = AgentEvent.doneLeaving;
		stateChanged();
		customerGui.DoExitRestaurant();
	}
	
	private void GoToJail() {
		RestaurantCustomerGui customerGui = (RestaurantCustomerGui) gui;
		state = AgentState.inJail;
		waiterRole.msgLeavingTable(this);
		customerGui.DoGoToJail();
		
		timer.schedule(new TimerTask() {
			public void run() {
				walkOfShame();
			}
		},
		5000);
	}
	
	private void walkOfShame() {
		RestaurantCustomerGui customerGui = (RestaurantCustomerGui) gui;
		state = AgentState.Leaving;
		customerGui.DoExitRestaurant();
		stateChanged();
	}

	private void ResetState() {
		state = AgentState.DoingNothing;
		person.setHunger(HungerLevel.full);
		this.setRoleInactive();
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
	
	public double getMoney() {
		return money;
	}

	@Override
	public void setHost(Host host) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgPleaseFollowMe(int tableNumber, Menu menu, Waiter waiter) {
		// TODO Auto-generated method stub
		
	}
}

