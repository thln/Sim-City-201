package restaurant;

//import agent.Agent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;

import person.Person;
import person.Role;
import person.Worker;
import restaurant.myCustomer.customerState;
import restaurant.interfaces.RestaurantCustomer;
import restaurant.interfaces.Waiter;
import application.Phonebook;
import application.gui.animation.agentGui.RestaurantWaiterGui;

/**
 * Restaurant Waiter Role
 */

public class WaiterRole extends Role implements Waiter {

	//Keeps  a list of customers
	public List<myCustomer> myCustomers = Collections.synchronizedList(new ArrayList<myCustomer>());
	public List<Order> readyOrders = Collections.synchronizedList(new ArrayList<Order>());

	protected String name;
	protected String RoleName = "Waiter";
	RestaurantWaiterGui waiterGui = (RestaurantWaiterGui) gui;

	public Menu menu = new Menu();

	Timer breakTimer = new Timer();

	protected boolean isInLobby = true;
	protected enum breakStatus{working, askedToGoOnBreak, waitingForReply, receivedReply, onBreak, goOffBreak};
	breakStatus state = breakStatus.working;

	protected boolean PermissionToBreak = false;

	public WaiterRole(Person p1, String pName, String rName) {
		super(p1, pName, rName);
	}

	public String getMaitreDName() {
		return name;
	}

	/**
	 * Messages
	 */
	public void msgPleaseSeatCustomer(int tableNumber, RestaurantCustomerRole customer, int xHome, int yHome) {
		myCustomers.add(new myCustomer(customer, tableNumber, xHome, yHome));
		print(customer.getCustomerName() + " was added to myCustomers list");
		stateChanged();
	}

	public void msgAtDestination() {//from animation
		atDestination.release();// = true;
		stateChanged();
	}

	public void msgIsInLobby() {
		isInLobby = true;
		stateChanged();
	}

	public void msgReadyToOrder(RestaurantCustomerRole customer) {
		for (myCustomer myCust : myCustomers) 
		{
			if (myCust.customer == customer) 
			{
				myCust.setReadyToOrder();
				stateChanged();
			}
		}
	}

	public void msgHeresMyOrder(RestaurantCustomer customer, String choice) {
		for (myCustomer myCust : myCustomers) 
		{
			if (myCust.customer == customer) 
			{
				myCust.setChoice(choice);
				myCust.setOrdered();
				stateChanged();
			}
		}
	}

	public void msgOrderIsNotAvailable(String choice, int tableNumber) {
		for (myCustomer myCust : myCustomers) {
			if (myCust.tableNumber == tableNumber) {
				myCust.setReorder();
				stateChanged();
			}
		}
	}

	public void msgOrderIsReady(int table, String choice) {
		Order order = null;
		for (myCustomer MC: myCustomers) {
			if (MC.tableNumber == table)
				order = new Order(table, choice, MC.customer);
		}

		readyOrders.add(order);
		stateChanged();
	}

	public void msgIWantMyCheck(RestaurantCustomerRole cust) {
		for (myCustomer myCust : myCustomers) {
			if (myCust.customer == cust) {
				myCust.setWantCheck();
				stateChanged();
			}
		}
	}

	public void msgLeavingTable(RestaurantCustomerRole cust)  {
		for (myCustomer myCust : myCustomers) 
		{
			if (myCust.customer == cust) 
			{
				myCust.setFinished();
				stateChanged();
			}
		}
	}

	public void msgWantToGoOnBreak() {
		state = breakStatus.askedToGoOnBreak;
		print("Got message to go on break");
		stateChanged();
	}

	public void msgPermissionToBreak(boolean reply) {
		PermissionToBreak = reply;
		print("Recieved reply and it is: " + reply);
		state = breakStatus.receivedReply;
		stateChanged();
	}

	public void msgGoOffBreak() {
		state = breakStatus.goOffBreak;
		print("Got message to go off break");
		stateChanged();
	}

	public void msgHereIsCheck(int tableNumber, double checkAmount) {
		for (myCustomer myCust : myCustomers) {
			if (myCust.tableNumber == tableNumber) {
				myCust.CheckAmount = checkAmount;
				print("Recieved " + myCust.customer.getCustomerName() + "'s check of $" + myCust.CheckAmount);
				stateChanged();
			}
		}
	}


	/**
	 * Scheduler
	 */
	public boolean pickAndExecuteAnAction() {

		//if an order is ready, deliver it
		if (leaveRole) {
			AskHostToLeave();
		}

		if (state == breakStatus.askedToGoOnBreak) {
			print("Scheduled to ask for break");
			askToGoOnBreak();
			return true;
		}

		if ((PermissionToBreak == false) && (state == breakStatus.receivedReply)) 
		{
			breakDenied();
			return true;
		}

		if ((myCustomers.isEmpty()) && (PermissionToBreak == true) && (state == breakStatus.receivedReply)) 
		{
			goOnBreak();
			return true;
		}

		if (state == breakStatus.goOffBreak) 
		{
			goOffBreak();
			return true;
		}

		synchronized(readyOrders) 
		{
			while (!readyOrders.isEmpty()) 
			{
				deliverOrder();
				return true;
			}
		}

		synchronized(myCustomers){
			for (myCustomer myCust : myCustomers) 
			{
				if (myCust.isFinished()) 
				{
					clearTable(myCust);
					return true;
				}

				//if a customer is waiting
				if (myCust.isWaiting()) 
				{
					if (isInLobby == true) 
					{	
						seatCustomer(myCust);
						return true;
					}
					return true;
				} //return true to the abstract agent to re-invoke the scheduler.

				if (myCust.isReadyToOrder()) 
				{
					takeOrder(myCust);
					return true;
				}

				if (myCust.isOrdered()) 
				{
					placeOrder(myCust);
					return true;
				}

				if (myCust.isReorder()) 
				{
					retakeOrder(myCust);
					return true;
				}

				if (myCust.isWantCheck()) 
				{
					giveCheck(myCust);
					return true;
				}
			}
		}


		//		catch (ConcurrentModificationException e) 
		//		{
		//			e.printStackTrace();
		//			stateChanged();
		//			return false;
		//		}

		return false;
		//we have tried all our rules and found
		//nothing to do. So return false to main loop of abstract agent
		//and wait.
	}



	/**
	 * Actions
	 */
	protected void seatCustomer(myCustomer MC) {
		RestaurantWaiterGui waiterGui = (RestaurantWaiterGui) gui;
		waiterGui.DoPickUpCustomer(MC.xHome, MC.yHome);
		try {
			atDestination.acquire();
		} catch (InterruptedException e) 
		{
			e.printStackTrace();

		}
		MC.customer.msgPleaseFollowMe(MC.tableNumber, menu, this);
		isInLobby = false;
		DoSeatCustomer(MC.customer, MC.tableNumber);

		try {
			atDestination.acquire();
			atDestination.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
		waiterGui.DoLeaveCustomer();
		print("Finished seating " + MC.customer.getCustomerName());
		MC.setSeated();
	}

	// The animation DoXYZ() routines
	protected void DoSeatCustomer(RestaurantCustomerRole customer, int table) {
		RestaurantWaiterGui waiterGui = (RestaurantWaiterGui) gui;
		isInLobby = false;
		print("Seating " + customer + " at " + table);
		waiterGui.DoBringToTable(customer, table);
	}

	protected void takeOrder(myCustomer MC) {
		RestaurantWaiterGui waiterGui = (RestaurantWaiterGui) gui;
		isInLobby = false;
		print(MC.customer.getCustomerName() + " wants to order");

		for (myCustomer myCust : myCustomers) 
		{
			if (myCust.customer == MC.customer)
			{
				myCust.setRequestedToOrder();
			}
		}

		waiterGui.DoTakeOrder(MC.tableNumber);
		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
		waiterGui.DoLeaveCustomer();

		MC.customer.msgWhatWouldYouLike();
	}

	protected void placeOrder(myCustomer MC) {
		RestaurantWaiterGui waiterGui = (RestaurantWaiterGui) gui;
		isInLobby = false;
		print("Placing " + MC.customer.getCustomerName() + "'s order");

		for (myCustomer myCust : myCustomers) {
			if (myCust.customer == MC.customer) {
				myCust.setWaitingForFood();
			}
		}

		waiterGui.DoGoToKitchen();
		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
		waiterGui.DoLeaveCustomer();

		Phonebook.getPhonebook().getRestaurant().cookRole.msgHeresAnOrder(MC.tableNumber, MC.choice, this);

	}

	protected void retakeOrder(myCustomer MC) {
		RestaurantWaiterGui waiterGui = (RestaurantWaiterGui) gui;
		isInLobby = false;

		waiterGui.DoTakeOrder(MC.tableNumber);
		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
		waiterGui.DoLeaveCustomer();
		MC.setSeated();

		print("Asking " + MC.customer.getCustomerName() + " for a re-order");
		MC.customer.msgPleaseReorder(new Menu().remove(MC.choice));

	}

	protected void deliverOrder() {
		RestaurantWaiterGui waiterGui = (RestaurantWaiterGui) gui;
		isInLobby = false;
		waiterGui.DoGoToPlatingArea();

		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
		waiterGui.DoDeliverOrder(readyOrders.get(0).tableNumber, readyOrders.get(0).choice);
		print("waiter called msgGotOrder");
		Phonebook.getPhonebook().getRestaurant().cookGui.msgGotOrder(readyOrders.get(0).choice);
		try {
			atDestination.acquire();
			atDestination.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
		waiterGui.DoLeaveCustomer();
		readyOrders.get(0).customer.msgHeresYourOrder(readyOrders.get(0).choice);
		Phonebook.getPhonebook().getRestaurant().cashierRole.msgComputeBill(readyOrders.get(0).choice, readyOrders.get(0).tableNumber, this);

		//Changing customer state to "Got Food"
		for (myCustomer MC : myCustomers) {
			if (MC.customer.equals(readyOrders.get(0).customer))
				MC.setGotFood();	
		}

		readyOrders.remove(0);
	}

	protected void giveCheck(myCustomer MC) {
		RestaurantWaiterGui waiterGui = (RestaurantWaiterGui) gui;
		isInLobby = false;

		waiterGui.DoTakeOrder(MC.tableNumber); //Is going to table
		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
		waiterGui.DoLeaveCustomer();

		MC.customer.msgHeresYourCheck(MC.CheckAmount);
		MC.state = customerState.GaveCheck;
	}

	protected void clearTable(myCustomer MC) {
		print(MC.customer.getCustomerName() + " is leaving " + MC.tableNumber);
		Phonebook.getPhonebook().getRestaurant().hostRole.msgLeavingTable(MC.customer, this);
		Phonebook.getPhonebook().getRestaurant().removeCustomer(MC.customer);

		myCustomers.remove(MC);
	}

	protected void askToGoOnBreak() 
	{
		print("Asking host for break");
		Phonebook.getPhonebook().getRestaurant().hostRole.msgMayIGoOnBreak(this);
		state = breakStatus.waitingForReply;
	}

	protected void goOnBreak() 
	{
		isInLobby = false;
		state = breakStatus.onBreak;
		PermissionToBreak = false;
		//	waiterGui.DoGoOnBreak();
		stateChanged();
	}

	protected void goOffBreak() {
		isInLobby = false;
		//		waiterGui.DoLeaveCustomer();
		Phonebook.getPhonebook().getRestaurant().hostRole.msgOffBreak(this);
		state = breakStatus.working;
		//		waiterGui.denyBreak();
		stateChanged();
	}

	protected void breakDenied() {
		state = breakStatus.working;
		//	waiterGui.denyBreak();
		stateChanged();
	}

	protected void AskHostToLeave()	{
		Phonebook.getPhonebook().getRestaurant().hostRole.msgIAmLeavingSoon(this);
		if(myCustomers.isEmpty())
		{
			Phonebook.getPhonebook().getRestaurant().hostRole.msgIAmLeavingWork(this);
			((Worker) person).roleFinishedWork();
			leaveRole = false;
		}
	}

	/**
	 * Utilities
	 */

	public boolean isOnBreak() {
		if (state == breakStatus.onBreak)
			return true;
		return false;
	}
}


