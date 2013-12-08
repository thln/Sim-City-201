package americanRestaurant;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import person.Role;
import americanRestaurant.AmericanRestaurantWaiterRole.Menu;
import americanRestaurant.interfaces.AmericanRestaurantCashier;
import americanRestaurant.interfaces.AmericanRestaurantCustomer;
import americanRestaurant.interfaces.AmericanRestaurantWaiter;
import application.gui.animation.RestaurantPanel;

/**
 * Restaurant customer agent.
 */
public class AmericanRestaurantCustomerRole extends Role implements AmericanRestaurantCustomer {

	//data
	static AmericanRestaurantHostRole myHost;
	RestaurantPanel panel1;
	static AmericanRestaurantCashier myCashier;
	static final int decidingTime = 2000;
	static final int maxCash = 300;
	static final int eatingTime = 3000;
	private AmericanRestaurantWaiter myWaiter;
	boolean waiting = false;
	private boolean dishonest = false;

	private int cash, check, debt;
	String choice;
	private int seatNumber = 0;
	Menu myMenu;

	Timer timer;
	Timer deciding;
	Timer eating;

	private String name;
	private int hungerLevel = 5;        // determines length of meal

	//private CustomerGui customerGui;

	// agent correspondents

	public enum AgentState
	{DoingNothing, WaitingInRestaurant, BeingSeated, Seated, ReadyToOrder, Ordered, WaitingForFood,
		OrderReady, Eating, PayingBill, DoneEating, Leaving};
		private AgentState state = AgentState.DoingNothing;//The start state

		public enum AgentEvent 
		{none, gotHungry, followHost, seated, ordering, reOrder, eating, readyForCheck, checkReady, 
			billPaid, doneEating, doneLeaving, noMoney};

			AgentEvent event = AgentEvent.none;

			/**
			 * Constructor for AmericanRestaurantCustomerRole class
			 *
			 * @param name name of the customer
			 * @param gui  reference to the customergui so the customer can send it messages
			 */

			public AmericanRestaurantCustomerRole(){
				super();
			}

			public AmericanRestaurantCustomerRole(String name, AmericanRestaurantHostRole H1, int seatNum, AmericanRestaurantCashierRole C1, RestaurantPanel p1){
				super();
				this.name = name;
				panel1 = p1;
				myHost  = H1;
				myCashier = C1;
				timer = new Timer ();
				deciding = new Timer();
				eating = new Timer();
				seatNumber = seatNum;
				debt = 0;	
			}

			public void setWaiter(AmericanRestaurantWaiterRole waiter) {
				this.myWaiter = waiter;
			}

			public int getSeatNumber(){
				return seatNumber;
			}

			public void setSeatNumber (int x){
				seatNumber = x;
			}

			public String getCustomerName() {
				return name;
			}

			// MESSAGES

			public void gotHungry() {//from animation
				print("I'm hungry");
				if (isDishonest())
					cash = 0;
				if (!isDishonest())
					cash = new Random().nextInt(maxCash); 
				Do("I have " + cash + " bucks.");
				event = AgentEvent.gotHungry;
				stateChanged();
			}

			public void msgRestaurantFull () {
				int decision;
				decision = new Random().nextInt(2);
				if (decision == 0) {
					Do("I won't wait.");
					myHost.msgWontWait(this);
					customerGui.DoExitRestaurant();
				}
				if (decision == 1) {
					Do("I will wait.");
					waiting = true;
					myHost.msgWillWait(this);
				}
			}

			public void msgSitAtTable(AmericanRestaurantWaiterRole w, Menu m) {
				Do("Received msgSitAtTable");
				this.myWaiter = w;
				event = AgentEvent.followHost;
				myMenu = m;			
				stateChanged();
			}

			public void msgAnimationFinishedGoToSeat() {
				//from animation
				event = AgentEvent.seated;
				stateChanged();
			}

			public void msgReOrder(Menu m) {
				myMenu = m;
				event = AgentEvent.reOrder;
				stateChanged();
			}

			public void msgWhatIsYourChoice(){
				event = AgentEvent.ordering;
				stateChanged();
			}

			public void msgHereIsYourFood(String choice){
				event = AgentEvent.eating;
				stateChanged();
			}

			public void msgPleasePayBill () {
				event = AgentEvent.checkReady;
				stateChanged();
			}

			public void msgHereIsChange (int change) {
				Do("Received change");
				this.setCash(change);
				event = AgentEvent.billPaid;
				stateChanged();
			}

			public void msgAnimationFinishedLeaveRestaurant() {
				//from animation
				event = AgentEvent.doneLeaving;
				stateChanged();
			}

			public void msgYouAreInDebt(int debt) {
				this.debt = debt;
			}

			public void msgPayOffDebt (){
				myCashier.msgPayDebt(this, debt, cash);
				Do("Paying my debt.");
			}

			//SCHEDULER
			/**
			 * Scheduler.  Determine what action is called for, and do it.
			 */
			protected boolean pickAndExecuteAnAction() {
				//	AmericanRestaurantCustomerRole is a finite state machine

				if (getState() == AgentState.DoingNothing && event == AgentEvent.gotHungry ){
					state = AgentState.WaitingInRestaurant;
					goToRestaurant();
					return false;
				}
				if (getState() == AgentState.WaitingInRestaurant && event == AgentEvent.followHost ){
					state = AgentState.BeingSeated;
					SitDown();
					return false;
				}
				if (getState() == AgentState.BeingSeated && event == AgentEvent.seated){
					state = AgentState.ReadyToOrder;
					DecideFood();
					return false;
				}

				if (getState() == AgentState.ReadyToOrder && event == AgentEvent.ordering){
					state = AgentState.Ordered;
					OrderFood();
					return false;
				}

				if (getState() == AgentState.ReadyToOrder && event == AgentEvent.noMoney){
					state = AgentState.DoingNothing;
					return false;
				}

				if (getState() == AgentState.Ordered && event == AgentEvent.reOrder) {
					state = AgentState.ReadyToOrder;
					DecideFood();
					return false;
				}

				if (getState() == AgentState.Ordered && event == AgentEvent.eating){
					state = AgentState.Eating;
					EatFood();
					return false;
				}

				if (getState() == AgentState.Eating && event == AgentEvent.checkReady){
					state = AgentState.PayingBill;
					PayBill();
					return false;
				}

				if (getState() == AgentState.PayingBill && event == AgentEvent.billPaid){
					state = AgentState.DoingNothing;
					//no action
					return false;
				}
				return false;
			}

			// Actions

			private void goToRestaurant() {
				Do("Going to restaurant");	
				customerGui.DoGoToEntrance();
				myHost.msgIWantToEat(this);//send our instance, so he can respond to us
			}

			private void SitDown() {
				Do("Being seated. Going to table");
				customerGui.DoGoToSeat(seatNumber);
			}

			private void DecideFood () {

				Do ("Deciding");	
				int size = Menu.items.size();
				int i = 0;

				for (i = 0; i < size; i++){
					int item = new Random().nextInt(size);		//randomly select from a choice in the menu			
					choice = Menu.items.get(item).choice;
					if (isDishonest()) 						//If dishonest, don't check to see if enough cash			
						break;		
					if (Menu.items.get(item).price <= cash){
						break;
					}
					Do("Can't afford");				
				}

				if (i == size) {
					Do("Not enough money for anything.");
					myWaiter.msgDoneAndPaying(this);
					customerGui.DoExitRestaurant();
					event = AgentEvent.noMoney;
					stateChanged();
					return;
				}

				deciding.schedule(new TimerTask() {
					public void run() {			
						BeckonWaiter();
						stateChanged();
					}
				}, decidingTime);		//Time to decide food from menu		

			}

			private void BeckonWaiter() {
				Do ("Beckoning AmericanRestaurantWaiter");
				myWaiter.msgReadyToOrder(this);
			}

			private void OrderFood() {
				myWaiter.msgHereIsMyChoice(this,choice);
			}

			private void EatFood() {
				Do("Eating " + choice);
				final AmericanRestaurantCustomer me = this;
				//This next complicated line creates and starts a timer thread.
				//We schedule a deadline of getHungerLevel()*1000 milliseconds.
				//When that time elapses, it will call back to the run routine
				//located in the anonymous class created right there inline:
				//TimerTask is an interface that we implement right there inline.
				//Since Java does not all us to pass functions, only objects.
				//So, we use Java syntactic mechanism to create an
				//anonymous inner class that has the public method run() in it.
				timer.schedule(new TimerTask() {
					Object cookie = 1;
					public void run() {
						print("Done eating " + choice);
						panel1.deleteFoodIcon(myWaiter);
						event = AgentEvent.readyForCheck;
						myWaiter.msgReadyForCheck(me);
						stateChanged();
					}
				},
				eatingTime);
			}

			private void PayBill() {
				Do("Going to americanRestaurantCashier.");
				myWaiter.msgDoneAndPaying(this);
				customerGui.DoExitRestaurant();
				if (isDishonest()) {
					myCashier.msgNotEnoughMoney(this, check, cash);
					Do("Woops...not enough money.");
					setDishonest(false);
					state = AgentState.DoingNothing;
					return;
				}
				if (!isDishonest())
					myCashier.msgHereIsMyPayment(this, check, cash);
			}

			// Accessors, etc.

			public String getName() {
				return name;
			}

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

			public void setGui(CustomerGui g) {
				customerGui = g;
			}

			public CustomerGui getGui() {
				return customerGui;
			}

			public AgentState getState() {
				return state;
			}

			public void setState(AgentState state) {
				this.state = state;
			}

			public int getCheck() {
				return check;
			}

			public void setCheck(int check) {
				this.check = check;
			}

			public int getCash() {
				return cash;
			}

			public void setCash(int cash) {
				this.cash = cash;
			}

			public void setDebt (int debt) {
				this.debt = debt;
			}

			public int getDebt () {
				return debt;
			}

			public boolean isDishonest() {
				return dishonest;
			}

			public void setDishonest(boolean dishonest) {
				this.dishonest = dishonest;
			}

}

