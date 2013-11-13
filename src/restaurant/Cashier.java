package restaurant;

import agent.Agent;

import java.util.*;

import restaurant.interfaces.Cashier;
import restaurant.interfaces.Customer;
import restaurant.interfaces.Market;
import restaurant.interfaces.Waiter;
import restaurant.test.mock.EventLog;
import restaurant.test.mock.LoggedEvent;

/**
 * Restaurant Cashier Agent
 */

public class Cashier extends Person {

	public EventLog log = new EventLog();
	
	private String name;
	//private Semaphore atTable = new Semaphore(0,true);

	//Keeps a list of checks
	public List<Check> Checks = Collections.synchronizedList(new ArrayList<Check>());
	public List<Payment> Payments = Collections.synchronizedList(new ArrayList<Payment>());
	public List<Order> OrdersToPay = Collections.synchronizedList(new ArrayList<Order>());

	//Map of food prices
	private Map<String, Double> foodPrices = new HashMap<String, Double>(); {
		foodPrices.put("Chicken", 10.99);
		foodPrices.put("Steak", 15.99);
		foodPrices.put("Pizza", 8.99);
		foodPrices.put("Salad", 5.99);
	}

	public Cashier(String name) {
		super();

		this.name = name;

	}

	public String getName() {
		return name;
	}



	/**
	 * Messages
	 */
	public void msgComputeBill(String choice, int tableNumber, Waiter waiter) {
		synchronized(Checks){
			print("Calculating bill for table " + tableNumber);
			log.add(new LoggedEvent("Calculating bill for table"));
			Checks.add(new Check(choice, tableNumber, waiter));
			stateChanged();
		}
	}

	public void msgPayment(String choice, double amount, Customer customer) {
		synchronized(Payments){
			print("Received payment from " + customer.getCustomerName());
			log.add(new LoggedEvent("Received payment from " + customer.getCustomerName()));
			Payments.add(new Payment(choice, amount, customer));
			stateChanged();
		}
	}

	public void msgOrderFulfilled(String choice, int amount, Market market) {
		synchronized(OrdersToPay){
			OrdersToPay.add(new Order(choice, amount, market));
			log.add(new LoggedEvent("Received msgOrderFulfilled from " + market.getName()));
			stateChanged();
		}
	}



	/**
	 * Scheduler
	 */
	public boolean pickAndExecuteAnAction() {
		/* Think of this next rule as:
            Does there exist a table and customer,
            so that table is unoccupied and customer is waiting.
            If so seat him at the table.
		 */
		synchronized(Checks){
			if (!Checks.isEmpty()) {
				ComputeBill();
				return true;
			}
		}

		synchronized(Payments){
			if (!Payments.isEmpty()) {
				GiveChange();
				return true;
			}
		}

		synchronized(OrdersToPay){
			if (!OrdersToPay.isEmpty()) {
				PayMarket();
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

	public void ComputeBill() {
		double checkAmount = foodPrices.get(Checks.get(0).choice);
		Checks.get(0).waiter.msgHereIsCheck(Checks.get(0).tableNumber, checkAmount);
		Checks.remove(0);
	}

	private void GiveChange() {
		if (Payments.get(0).payment < foodPrices.get(Payments.get(0).choice)) {
			Payments.get(0).customer.msgGoToJail();
			Payments.remove(0);
			return;
		}
		
		double change;
		change = Payments.get(0).payment - foodPrices.get(Payments.get(0).choice);
		change = Math.round(change * 100.0) / 100.0;
		Payments.get(0).customer.msgHeresYourChange(change);
		print("Gave change to customer " + Payments.get(0).customer.getName());
		Payments.remove(0);
	}
	
	private void PayMarket() {
		double payment;
		payment = OrdersToPay.get(0).amountOrdered * foodPrices.get(OrdersToPay.get(0).choice);
		payment = Math.round(payment * 100.0) / 100.0;
		print("Giving Market " + OrdersToPay.get(0).market.getName() + " for " + OrdersToPay.get(0).amountOrdered + " " + OrdersToPay.get(0).choice + "(s) x $" + foodPrices.get(OrdersToPay.get(0).choice) + " = $" + payment);
		OrdersToPay.get(0).market.msgPayment(payment, this);
		OrdersToPay.remove(0);
	}

	
	
	//Check Class
	public class Check {
		String choice;
		int tableNumber;
		Waiter waiter;

		Check(String choice, int tableNumber, Waiter waiter) {
			this.choice = choice;
			this.tableNumber = tableNumber;
			this.waiter = waiter;
		}
	}

	//Payment Class
	public class Payment {
		public String choice;
		public double payment;
		public Customer customer;

		Payment(String choice, double payment, Customer customer) {
			this.choice = choice;
			this.payment = payment;
			this.customer = customer;
		}
	}
	
	//Order Class
	public class Order {
		String choice;
		int amountOrdered;
		Market market;

		Order(String choice, int amountOrdered, Market market) {
			this.choice = choice;
			this.amountOrdered = amountOrdered;
			this.market = market;
		}
	}
}