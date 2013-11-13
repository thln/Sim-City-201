package restaurant;

import agent.Agent;
import restaurant.myCustomer.customerState;

import java.util.*;
import java.util.concurrent.Semaphore;

/**
 * Restaurant Waiter Agent
 */

public class Waiter extends Person {

	//Keeps  a list of customers
	public List<myCustomer> myCustomers = Collections.synchronizedList(new ArrayList<myCustomer>());
	public List<Order> readyOrders = Collections.synchronizedList(new ArrayList<Order>());

	private String name;
	private Semaphore atDestination = new Semaphore(0,true);

	public WaiterGui waiterGui = null;
	private CookGui cookGui = null;

	public Menu menu = new Menu();

	Timer breakTimer = new Timer();

	//Agent Correspondents
	private Host host;
	private Cook cook;
	private Cashier cashier;

	private boolean isInLobby = true;
	private enum breakStatus{working, askedToGoOnBreak, waitingForReply, receivedReply, onBreak, goOffBreak};
	breakStatus state = breakStatus.working;

	private boolean PermissionToBreak = false;

	public Waiter(String name) {
		super();

		this.name = name;
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
	public void msgPleaseSeatCustomer(int tableNumber, RestaurantCustomer customer, int xHome, int yHome) {
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

	public void msgReadyToOrder(RestaurantCustomer customer) {
		for (myCustomer myCust : myCustomers) {
			if (myCust.customer == customer) {
				myCust.setReadyToOrder();
				stateChanged();
			}
		}
	}

	public void msgHeresMyOrder(RestaurantCustomer customer, String choice) {
		for (myCustomer myCust : myCustomers) {
			if (myCust.customer == customer) {
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

	public void msgIWantMyCheck(RestaurantCustomer cust) {
		for (myCustomer myCust : myCustomers) {
			if (myCust.customer == cust) {
				myCust.setWantCheck();
				stateChanged();
			}
		}
	}

	public void msgLeavingTable(RestaurantCustomer cust) {
		for (myCustomer myCust : myCustomers) {
			if (myCust.customer == cust) {
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
	protected boolean pickAndExecuteAnAction() {

		//if an order is ready, deliver it

		if (state == breakStatus.askedToGoOnBreak) {
			print("Scheduled to ask for break");
			askToGoOnBreak();
			return true;
		}

		if ((PermissionToBreak == false) && (state == breakStatus.receivedReply)) {
			breakDenied();
			return true;
		}

		if ((myCustomers.isEmpty()) && (PermissionToBreak == true) && (state == breakStatus.receivedReply)) {
			goOnBreak();
			return true;
		}

		if (state == breakStatus.goOffBreak) {
			goOffBreak();
			return true;
		}

		synchronized(readyOrders) {
			while (!readyOrders.isEmpty()) {
				deliverOrder();
				return true;
			}
		}

		try {
			for (myCustomer myCust : myCustomers) {
				if (myCust.isFinished()) {
					clearTable(myCust);
					return true;
				}

				//if a customer is waiting
				if (myCust.isWaiting()) {
					if (isInLobby == true) {
						seatCustomer(myCust);
						return true;
					}
					return true;
				} //return true to the abstract agent to re-invoke the scheduler.

				if (myCust.isReadyToOrder()) {
					takeOrder(myCust);
					return true;
				}

				if (myCust.isOrdered()) {
					placeOrder(myCust);
					return true;
				}

				if (myCust.isReorder()) {
					retakeOrder(myCust);
					return true;
				}

				if (myCust.isWantCheck()) {
					giveCheck(myCust);
					return true;
				}
			}
		}
		catch (ConcurrentModificationException e) {
			e.printStackTrace();
			stateChanged();
			return false;
		}
		
		return false;
		//we have tried all our rules and found
		//nothing to do. So return false to main loop of abstract agent
		//and wait.
	}



	/**
	 * Actions
	 */
	private void seatCustomer(myCustomer MC) {
		waiterGui.DoPickUpCustomer(MC.xHome, MC.yHome);
		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		MC.customer.msgPleaseFollowMe(MC.tableNumber, menu, this);
		isInLobby = false;
		DoSeatCustomer(MC.customer, MC.tableNumber);
		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		waiterGui.DoLeaveCustomer();
		print("Finished seating " + MC.customer.getCustomerName());
		MC.setSeated();
	}

	// The animation DoXYZ() routines
	private void DoSeatCustomer(RestaurantCustomer customer, int table) {
		//Notice how we print "customer" directly. It's toString method will do it.
		//Same with "table"
		isInLobby = false;
		print("Seating " + customer + " at " + table);
		waiterGui.DoBringToTable(customer, table);
	}

	private void takeOrder(myCustomer MC) {
		isInLobby = false;
		print(MC.customer.getCustomerName() + " wants to order");

		for (myCustomer myCust : myCustomers) {
			if (myCust.customer == MC.customer) {
				myCust.setRequestedToOrder();
			}
		}

		waiterGui.DoTakeOrder(MC.tableNumber);
		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		waiterGui.DoLeaveCustomer();

		MC.customer.msgWhatWouldYouLike();
	}

	private void placeOrder(myCustomer MC) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		waiterGui.DoLeaveCustomer();

		cook.msgHeresAnOrder(MC.tableNumber, MC.choice, this);

	}

	private void retakeOrder(myCustomer MC) {
		isInLobby = false;

		waiterGui.DoTakeOrder(MC.tableNumber);
		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		waiterGui.DoLeaveCustomer();
		MC.setSeated();

		print("Asking " + MC.customer.getCustomerName() + " for a re-order");
		MC.customer.msgPleaseReorder(new Menu().remove(MC.choice));

	}

	private void deliverOrder() {
		isInLobby = false;
		waiterGui.DoGoToPlatingArea();

		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		waiterGui.DoDeliverOrder(readyOrders.get(0).tableNumber, readyOrders.get(0).choice);
		System.err.println("waiter called msgGotOrder");
		cookGui.msgGotOrder(readyOrders.get(0).choice);
		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		waiterGui.DoLeaveCustomer();
		readyOrders.get(0).customer.msgHeresYourOrder(readyOrders.get(0).choice);
		cashier.msgComputeBill(readyOrders.get(0).choice, readyOrders.get(0).tableNumber, this);

		//Changing customer state to "Got Food"
		for (myCustomer MC : myCustomers) {
			if (MC.customer.equals(readyOrders.get(0).customer))
				MC.setGotFood();	
		}

		readyOrders.remove(0);
	}

	private void giveCheck(myCustomer MC) {
		isInLobby = false;

		waiterGui.DoTakeOrder(MC.tableNumber); //Is going to table
		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		waiterGui.DoLeaveCustomer();

		MC.customer.msgHeresYourCheck(MC.CheckAmount);
		MC.state = customerState.GaveCheck;
	}

	private void clearTable(myCustomer MC){
		print(MC.customer.getCustomerName() + " is leaving " + MC.tableNumber);
		host.msgLeavingTable(MC.customer, this);
		myCustomers.remove(MC);
	}

	private void askToGoOnBreak() {
		print("Asking host for break");
		host.msgMayIGoOnBreak(this);
		state = breakStatus.waitingForReply;
	}

	private void goOnBreak() {
		isInLobby = false;
		state = breakStatus.onBreak;
		PermissionToBreak = false;
		waiterGui.DoGoOnBreak();
		stateChanged();
	}

	private void goOffBreak() {
		isInLobby = false;
		waiterGui.DoLeaveCustomer();
		host.msgOffBreak(this);
		state = breakStatus.working;
		waiterGui.denyBreak();
		stateChanged();
	}

	private void breakDenied() {
		state = breakStatus.working;
		waiterGui.denyBreak();
		stateChanged();
	}



	/**
	 * Utilities
	 */
	public void setGui(WaiterGui gui) {
		waiterGui = gui;
	}

	public WaiterGui getGui() {
		return waiterGui;
	}

	public void setHost(Host host) {
		this.host = host;
	}

	public void setCook(Cook cook) {
		this.cook = cook;
	}
	
	public void setCookGui(CookGui cookGui) {
		this.cookGui = cookGui;
	}

	public void setCashier(Cashier cashier) {
		this.cashier = cashier;
	}

	public boolean isOnBreak() {
		if (state == breakStatus.onBreak)
			return true;
		return false;
	}

	//public String toString() {
	//return "table"+ tableNumber;
	//}
}


