package restaurant;

import java.util.*;
//import java.util.concurrent.Semaphore;

import person.Person;
import person.Role;

/**
 * Restaurant Host Role
 */
//We only have 2 types of agents in this prototype. A customer and an agent that
//does all the rest. Rather than calling the other agent a waiter, we called him
//the HostAgent. A Host is the manager of a restaurant who sees that all
//is proceeded as he wishes.
public class HostRole extends Role {
	static final int NTABLES = 4;//a global for the number of tables.
	//Notice that we implement waitingCustomers using ArrayList, but type it
	//with List semantics.
	public List<myCustomer> newCustomers = Collections.synchronizedList(new ArrayList<myCustomer>());
	public List<myCustomer> waitingCustomers = Collections.synchronizedList(new ArrayList<myCustomer>());
	public List<myWaiter> waiters = Collections.synchronizedList(new ArrayList<myWaiter>());


	public Collection<Table> tables;
	protected String roleName = "Host";
	//note that tables is typed with Collection semantics.
	//Later we will see how it is implemented

	private String name;
	//private Semaphore atTable = new Semaphore(0,true);

	//GUI stuff
	//public HostGui hostGui = null;

	public HostRole(Person p1, String pName, String rName) {
		super(p1, pName, rName);

		// make some tables
		tables = new ArrayList<Table>(NTABLES);
		for (int ix = 1; ix <= NTABLES; ix++) {
			tables.add(new Table(ix));//how you add to a collections
		}
	}

	public HostRole(String roleName) {
		super(roleName);

		// make some tables
		tables = new ArrayList<Table>(NTABLES);
		for (int ix = 1; ix <= NTABLES; ix++) {
			tables.add(new Table(ix));//how you add to a collections
		}
	}

	public String getMaitreDName() {
		return name;
	}

	public String getName() {
		return name;
	}

	public List getWaitingCustomers() {
		return waitingCustomers;
	}

	public Collection getTables() {
		return tables;
	}



	/**
	 *	Messages
	 */
	public void msgIWantFood(RestaurantCustomerRole cust, int xHome, int yHome) {
		synchronized(newCustomers){
			newCustomers.add(new myCustomer(cust, xHome, yHome));
			stateChanged();
		}
	}

	public void msgStaying(RestaurantCustomerRole cust, int xHome, int yHome) {
		synchronized(waitingCustomers){
			waitingCustomers.add(new myCustomer(cust, xHome, yHome));
			stateChanged();
		}
	}

	public void msgLeavingTable(RestaurantCustomerRole cust, WaiterRole waiterRole) {
		for (Table table : tables) {
			if (table.getOccupant() == cust) {
				//print(cust.getName() + " is leaving table " + table);
				table.setUnoccupied();
			}
		}

		for (myWaiter MW: waiters) {
			if (MW.waiterRole == waiterRole) {
				MW.totalCustomers--;
			}
		}

		stateChanged();
	}

	public void msgMayIGoOnBreak(WaiterRole waiterRole) {
		//print(waiter.getName() + " asked to go on break");
		for (myWaiter MW: waiters) {
			if (MW.waiterRole == waiterRole) {
				MW.askedToGoOnBreak = true;
				stateChanged();
			}
		}
	}

	public void msgOffBreak(WaiterRole waiterRole) {
		for (myWaiter MW: waiters) {
			if (MW.waiterRole.equals(waiterRole)) {
				MW.onBreak = false;
				stateChanged();
			}
		}
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
		synchronized(newCustomers){
			synchronized(waitingCustomers){
				if (!newCustomers.isEmpty()) {
					greetCustomer();
					return true;
				}
			}
		}

		synchronized(waiters){
			synchronized(waitingCustomers){
				for (Table table : tables) {
					if (!table.isOccupied()) {
						if (!waiters.isEmpty()) {
							if (!waitingCustomers.isEmpty()) {
								assignCustomer(waitingCustomers.get(0), table, findWaiterWithLeastCustomers());//the action
								return true;//return true to the abstract agent to re-invoke the scheduler.
							}
						}
					}
				}
			}
		}

		synchronized(waiters){
			if (!waiters.isEmpty()) {
				for (myWaiter MW : waiters) {
					if (MW.askedToGoOnBreak == true) {
						replyToBreakRequest(MW);
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



	/** Actions
	 *
	 */
	private void greetCustomer() {
		int fullTableIterator = 0;

		for (Table table : tables) {
			if (table.isOccupied()) {
				fullTableIterator++;
			}
		}

		if (fullTableIterator == NTABLES) {
			informCustomerRestaurantFull(newCustomers.get(0).customer);
		}
		else {
			waitingCustomers.add(newCustomers.get(0));
			newCustomers.remove(0);
		}
	}

	private void assignCustomer(myCustomer MC, Table table, WaiterRole waiterRole) {
		//print("Assigning customer " + MC.customer.getCustomerName() + " to waiter");
		addCustomerToWaiter(waiterRole);
		waiterRole.msgPleaseSeatCustomer(table.tableNumber, MC.customer, MC.xHome, MC.yHome);
		table.setOccupant(MC.customer);
		for (myCustomer MyCust : waitingCustomers) {
			if (MyCust.equals(MC)) {
				waitingCustomers.remove(MC);
				return;
			}
		}
	}

	private void replyToBreakRequest(myWaiter MW) {

		if (waiters.size() == 1) {
			MW.askedToGoOnBreak = false;
			MW.waiterRole.msgPermissionToBreak(false);
			//print("Telling " + MW.waiter.getName() + " he/she cannot go on break");
			return;
		}

		int workingWaiterCount = 0;

		//Determining how many working waiters there are
		for (myWaiter wait: waiters) {
			if (wait.onBreak == false)
				workingWaiterCount++;
		}

		if (workingWaiterCount > 1) {
			MW.askedToGoOnBreak = false;
			//print("Allowing " + MW.waiter.getName() + " to go on break");
			MW.waiterRole.msgPermissionToBreak(true);
			MW.onBreak = true;
		}
		else {
			MW.askedToGoOnBreak = false;
			//print("Deny " + MW.waiter.getName() + " to go on break");
			MW.waiterRole.msgPermissionToBreak(false);
		}
	}

	private void informCustomerRestaurantFull(RestaurantCustomerRole customer) {
		customer.msgTablesAreFull();
		for(myCustomer MC : newCustomers) {
			if (MC.customer.equals(customer)) {
				newCustomers.remove(MC);
				return;
			}
		}
		stateChanged();
	}


	//utilities

	/* GUI STUFF
	public void setGui(HostGui gui) {
		hostGui = gui;
	}

	public HostGui getGui() {
		return hostGui;
	}*/

	public void addWaiter(WaiterRole waiterRole) {
		waiters.add(new myWaiter(waiterRole));
		//print("Hired new waiter, " + waiter.getName());
		stateChanged();
	}

	public void addCustomerToWaiter(WaiterRole waiterRole) {
		for (myWaiter MW: waiters) {
			if (MW.waiterRole == waiterRole)
				MW.totalCustomers++;
		}
	}

	private WaiterRole findWaiterWithLeastCustomers() {
		//Finding first waiter that is not on break
		myWaiter lowestWaiter = null;
		for (myWaiter lowWaiter: waiters) {
			if (lowWaiter.onBreak == false) {
				lowestWaiter = lowWaiter;
				break;
			}
		}

		//Spreading customers equally
		for (int i = 1; i < waiters.size(); i++) {
			if ((lowestWaiter.totalCustomers > waiters.get(i).totalCustomers) && (waiters.get(i).onBreak == false))
				lowestWaiter = waiters.get(i);
		}

		return lowestWaiter.waiterRole;
	}

	private class Table {
		RestaurantCustomerRole occupiedBy;
		int tableNumber;

		Table(int tableNumber) {
			this.tableNumber = tableNumber;
		}


		void setOccupant(RestaurantCustomerRole cust) {
			occupiedBy = cust;
		}

		void setUnoccupied() {
			occupiedBy = null;
		}

		RestaurantCustomerRole getOccupant() {
			return occupiedBy;
		}

		boolean isOccupied() {
			return occupiedBy != null;
		}

		public String toString() {
			return "table " + tableNumber;
		}

	}

	private class myWaiter {
		public WaiterRole waiterRole;
		public int totalCustomers;
		boolean askedToGoOnBreak = false;
		boolean onBreak = false;

		myWaiter(WaiterRole waiterRole) {
			this.waiterRole = waiterRole;
			totalCustomers = 0;
		}
	}
	
	private class myCustomer {
		RestaurantCustomerRole customer;
		int xHome;
		int yHome;
		
		myCustomer(RestaurantCustomerRole customer, int xHome, int yHome) {
			this.customer = customer;
			this.xHome = xHome;
			this.yHome = yHome;
		}
	}
}

