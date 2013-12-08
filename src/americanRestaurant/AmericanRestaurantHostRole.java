
package americanRestaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

import agent.Agent;
import americanRestaurant.interfaces.AmericanRestaurantCustomer;
import americanRestaurant.interfaces.AmericanRestaurantHost;

/**
 * Restaurant AmericanRestaurantHost Agent
 */
//We only have 2 types of agents in this prototype. A customer and an agent that
//does all the rest. Rather than calling the other agent a waiter, we called him
//the AmericanRestaurantCookRole. A AmericanRestaurantHost is the manager of a restaurant who sees that all
//is proceeded as he wishes.


public class AmericanRestaurantHostRole extends Agent implements AmericanRestaurantHost {
	static final int NTABLES = 4;//a global for the number of americanRestaurantTables.
	static final int NWaiters = 3;
	//Notice that we implement waitingCustomers using ArrayList, but type it
	//with List semantics.
	private Table[] tableList;
	public List<AmericanRestaurantCustomerRole> waitingCustomers;
	private List<AmericanRestaurantCustomerRole> leavingCustomers;
	private List<AmericanRestaurantCustomerRole> dishonestCustomers;
	private List<AmericanRestaurantWaiterRole> Waiters;
	boolean tableAvailable = true;
	String name = "Billy";
	boolean wantToBreak = false;

	public AmericanRestaurantHostRole (){
		super();
		waitingCustomers = Collections.synchronizedList(new ArrayList<AmericanRestaurantCustomerRole>());
		leavingCustomers = Collections.synchronizedList(new ArrayList<AmericanRestaurantCustomerRole>());
		dishonestCustomers = Collections.synchronizedList(new ArrayList<AmericanRestaurantCustomerRole>());
		tableList = new Table[NTABLES];
		for (int i = 0; i < NTABLES; i++) {
			tableList[i] = new Table();
			tableList[i].setSeatNum(i);
		}
		Waiters = Collections.synchronizedList(new ArrayList<AmericanRestaurantWaiterRole>());
	}
	//copy constructor
	public AmericanRestaurantHostRole (AmericanRestaurantHostRole H1) {
		this.waitingCustomers = H1.waitingCustomers;
		this.tableList = H1.tableList;
		this.Waiters = H1.Waiters;
	}

	private Semaphore atTable = new Semaphore(0,true);        //Way to coordinate which agents are doing what

	public List getWaitingCustomers() {
		return waitingCustomers;
	}

	public Table[] getTables() {
		return tableList;
	}

	// MESSAGES

	public void msgAddWaiter (AmericanRestaurantWaiterRole W1, String name) {
		Waiters.add(W1);
		stateChanged();
	}

	public void msgIWantToEat (AmericanRestaurantCustomerRole C1){   
		synchronized(dishonestCustomers){
			if (dishonestCustomers.size() > 0) {
				for (AmericanRestaurantCustomerRole cust1: dishonestCustomers) {
					if (cust1.equals(C1)){
						cust1.msgPayOffDebt();
						return;
					}
				}
			}
		}
		
		Do("Finding AmericanRestaurantWaiter");
		waitingCustomers.add(C1);         
		C1.getGui().setHomePosition(waitingCustomers.size()-1);
		stateChanged();
	}

	public void msgWantToBreak(AmericanRestaurantWaiterRole w) {        
		AmericanRestaurantWaiterRole correct = FindWaiter(w);
		correct.setWantToBreak(true);
		wantToBreak = true;
		stateChanged();
	}

	public void msgOffBreak(AmericanRestaurantWaiterRole w1) {
		Waiters.add(w1);
		stateChanged();
	}

	public void msgTableIsClear (Table t1){
		RemoveTable(t1);
		stateChanged();
	}

	public void msgWillWait (AmericanRestaurantCustomerRole c1) {

	}

	public void msgWontWait (AmericanRestaurantCustomerRole c1) {
		leavingCustomers.add(c1);
	}

	public void msgWatchThisCust (AmericanRestaurantCustomer c1) {
		Do("Putting " + c1.getName() + " on the blacklist.");
		dishonestCustomers.add((AmericanRestaurantCustomerRole) c1);
	}

	public void msgDebtPaid (AmericanRestaurantCustomer c1) {
		Do("Taking " + c1.getName() + " off the blacklist.");
		dishonestCustomers.remove(c1);
		waitingCustomers.add((AmericanRestaurantCustomerRole) c1);
		stateChanged();
	}

	/**
	 * Scheduler.  Determine what action is called for, and do it.
	 */
	protected boolean pickAndExecuteAnAction() {

		int i = 0;
		while (i < NTABLES){                        //Search to see if all americanRestaurantTables are occupied
			if (!tableList[i].isOccupied())        
				break;
			else i++;
		}

		if (i == NTABLES) {                                        //If all are occupied, set boolean for customer to wait
			tableAvailable = false;   
			synchronized(waitingCustomers){
				for (AmericanRestaurantCustomerRole c1: waitingCustomers) {
					if (!c1.waiting) {
						Do("Restaurant full.");
						c1.msgRestaurantFull();
					}
				}
			}
		}
		
		synchronized(waitingCustomers){
			if (Waiters.size() != 0 && tableAvailable){
				if (!waitingCustomers.isEmpty()){
					for (AmericanRestaurantCustomerRole cust1 : waitingCustomers) 
					{
						OccupyTable(cust1);
						return false;
					}
				}
			}
		}

		synchronized(leavingCustomers){
			if (leavingCustomers.size() > 0) {
				AmericanRestaurantCustomerRole leaver;        
				for (AmericanRestaurantCustomerRole c1: leavingCustomers) {
					leaver = c1;
					leavingCustomers.remove(c1);

					synchronized(waitingCustomers){
						for (AmericanRestaurantCustomerRole cust1: waitingCustomers) {
							if (cust1 == leaver){
								waitingCustomers.remove(leaver);
								break;
							}                        
						}
					}
					return false;
				}
			}
		}

		synchronized(Waiters){
			if (wantToBreak) {                                //If someone wants to break
				for (AmericanRestaurantWaiterRole w: Waiters) {                //Find the waiter who wants to break
					if (w.getWantToBreak()) {
						if (Waiters.size() >= 2) {                //If there are enough available waiters
							BreakWaiter(w);                                //Allow waiter to break
							return false;
						}
						else
							DenyWaiter(w);                                //Otherwise, deny break
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

	public AmericanRestaurantWaiterRole FindWaiter (AmericanRestaurantWaiterRole wait) {
		synchronized(Waiters) {
			for (AmericanRestaurantWaiterRole w: Waiters) {
				if (w == wait)
					return w;
			}
			return null;
		}
	}

	void OccupyTable (AmericanRestaurantCustomerRole c1){

		int seatNumber = 0;
		for (Table table1 : tableList) {                //Find an available table
			if (!table1.isOccupied()){
				c1.setSeatNumber(seatNumber);                                        //give the customer their seat number
				AmericanRestaurantWaiterRole w = findAvailableWaiter();                        //find an available waiter
				Do("Seating " +c1 + " with AmericanRestaurantWaiter " + w.getName());
				w.msgSeatAtTable(c1, table1);                                //message the waiter to seat the customer
				table1.setOccupant(c1);                                        //set the table as occupied
				waitingCustomers.remove(c1);								//remove customer
				return;
			}
			seatNumber++;                                //If table is occupied, try the next table and increase seatnumber
		}		
	}

	void BreakWaiter (AmericanRestaurantWaiterRole w) {
		w.msgCanBreak();
		wantToBreak = false;
		Do ("AmericanRestaurantWaiter " + w.getName() + " go on break");
		w.getGui().getGui().setWaiterEnabled(w, true);
		Waiters.remove(w);        
	}

	void DenyWaiter (AmericanRestaurantWaiterRole w) {
		wantToBreak = false;
		Do ("Break not allowed");
		w.msgNoBreak();
		w.getGui().getGui().setWaiterEnabled(w, false);
	}

	void RemoveTable (Table t1) {
		for (Table table1 : tableList) {
			if (table1 == t1) 
				table1.setUnoccupied();
		}
		//If a customer was waiting, the table should be cleared
		tableAvailable = true;
		stateChanged();
	}

	AmericanRestaurantWaiterRole findAvailableWaiter(){
		synchronized(Waiters) {
			for (AmericanRestaurantWaiterRole w: Waiters)
			{
				if (w.getCustomers().size() == 0)                 //If waiter has no customers, send the new customer to that waiter        
					return w;
			}

			//Otherwise find the waiter with the least customers
			int min = 0;        
			for (int i = 1; i < Waiters.size(); i++)
			{
				if (Waiters.get(min).getCustomers().size() < Waiters.get(i).getCustomers().size())        
					min = i;
			}
			return Waiters.get(min);
		}
	}
	
	public void SlideCustomers() {
		synchronized(waitingCustomers) {						//slide customer guis over to front 
			for (int i = 0; i < waitingCustomers.size(); i++) {
				waitingCustomers.get(i).getGui().setHomePosition(i);
			}
		}
	}

}