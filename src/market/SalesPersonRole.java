package market;

import java.util.*;
import java.util.concurrent.Semaphore;

import chineseRestaurant.ChineseRestaurant;
import application.Phonebook;
import application.Restaurant;
import application.gui.animation.agentGui.*;
import market.MarketOrder.orderState;
import market.interfaces.MarketCustomer;
import market.interfaces.SalesPerson;
import person.Person;
import person.Role;
import person.Worker;
import seafoodRestaurant.SeafoodRestaurant;
import testing.EventLog;
import testing.LoggedEvent;

public class SalesPersonRole extends Role implements SalesPerson {

	Market market;

	public EventLog log = new EventLog();

	//Data
	public List<MarketOrder> orders = Collections.synchronizedList(new ArrayList<MarketOrder>());
	private Semaphore atDestination = new Semaphore(0, true);
	MarketSalesPersonGui salesPersonGui;

	//Constructors
	public SalesPersonRole(Person person, String pName, String rName, Market market) {
		super(person, pName, rName);
		this.market = market;
	}

	public SalesPersonRole(String roleName, Market market) {
		super(roleName);
		this.market = market;
	}



	//Messages
	public void msgIWantProducts(MarketCustomer customer, String item, int numWanted) {
		if (customer instanceof MarketCustomerRole) {
			print(((MarketCustomerRole) customer).person.getName() + " asked for " + numWanted + " " + item + "(s)");
		}
		log.add(new LoggedEvent("Recieved msgIWantProducts"));
		orders.add(new MarketOrder(customer, item, numWanted));
		stateChanged();
	}


	
	public void msgIWantProducts(Restaurant restaurant, String item, int numWanted) {
		print("Restaurant asked for " + numWanted + " " + item + "(s)");
		log.add(new LoggedEvent("Recieved msgIWantProducts"));
		orders.add(new MarketOrder(restaurant, item, numWanted));
		stateChanged();
	}

	public void msgOrderFulfilled(MarketOrder o) {
		if (o.customer instanceof MarketCustomerRole) {
			print("An order has been fulfilled for: " + ((MarketCustomerRole) o.customer).person.getName());
		}

		for (MarketOrder MO : orders) {
			if (MO.equals(o)) {
				MO.state = orderState.itemsFound;
				stateChanged();
				return;
			}
		}
	}


	public void msgOrderDelivered(MarketOrder o) {

		print("An order has been delivered for the restaurant");

		for (MarketOrder MO : orders) {
			if (MO.equals(o)) {
				MO.state = orderState.itemsDelivered;
				stateChanged();
				return;
			}
		}
	}
	
	public void msgAtDestination() {
		atDestination.release();
	}


	public void msgPayment(MarketCustomer customer, double payment) {
		if (customer instanceof MarketCustomerRole) {
			print("Recieved payment of $" + payment + " from " + ((MarketCustomerRole) customer).person.getName());
		}

		log.add(new LoggedEvent("Recieved msgPayment"));
		market.money += payment;
		for (MarketOrder o : orders) {
			if (customer.equals(o.customer)) {
				orders.remove(o);
				return;
			}
		}
	}


	public void msgPayment(ChineseRestaurant chineseRestaurant, double payment) {

		print("Recieved payment of $" + payment + " from restaurant");

		market.money += payment;
		for (MarketOrder o : orders) {
			if (o.restaurant.equals(chineseRestaurant)) {
				orders.remove(o);
				return;
			}
		}
	}

	public void msgPayment(SeafoodRestaurant seafoodRestaurant, double payment) 
	{
		print("Recieved payment of $" + payment + " from restaurant");

		market.money += payment;
		for (MarketOrder o : orders) 
		{
			if (o.restaurant.equals(seafoodRestaurant)) 
			{
				orders.remove(o);
				return;
			}
		}
	}
	
	//Scheduler
	public boolean pickAndExecuteAnAction() {
		if (!orders.isEmpty()) {
			synchronized(orders) {
				for (MarketOrder o : orders) {
					if (o.state == orderState.open) {
						findItems(o);
						return true;
					}
					if (o.state == orderState.itemsFound) {
						giveCustomerItems(o);
						return true;
					}
					if (o.state == orderState.itemsDelivered) {
						askForPayment(o);
						return true;
					}
				}
			}
		}

		if (leaveRole) {
			market.goingOffWork(person);
			leaveRole = false;
			return true;
		}

		return false;
	}

	//Actions
	public void findItems(MarketOrder o) {
		o.state = orderState.processing;

		if (market.inventory.get(o.item).amount == 0) {
			((ChineseRestaurant) o.restaurant).getCook(test).msgCantFulfill(o.item, 0, o.itemAmountOrdered);
			orders.remove(o);
			stateChanged();
			return;
		}
		salesPersonGui.DoGotoRunner();
		try {
			this.atDestination.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		print("Gave Market Runner an order to find");
		market.getMarketRunner(test).msgHeresAnOrder(o);
		stateChanged();
	}

	public void giveCustomerItems(MarketOrder o) {
		o.state = orderState.gaveToCustomer;
		o.orderCost = market.inventory.get(o.item).price  * o.itemAmountFulfilled;

		if (o.customer instanceof MarketCustomerRole) {
			print("Gave order to: " + ((MarketCustomerRole) o.customer).person.getName());
		}

		o.customer.msgHereAreYourThings(o.item, o.itemAmountFulfilled, o.orderCost);
		stateChanged();
	}

	public void askForPayment(MarketOrder o) {
		o.state = orderState.gaveToCustomer;
		print("Asking for payment from the restaurant");
		((ChineseRestaurant) o.restaurant).getCashier(true).msgPleasePayForItems(o.item, o.itemAmountFulfilled, o.orderCost, this);
		stateChanged();
	}

	public void msgMarketOpen() {
		print("Opening market");
		if (!orders.isEmpty()) {
			for (MarketOrder o: orders) {
				if (o.customer != null) {
					o.customer.msgComeIn();
				}
			}
		}
	}
	
	public void setGui(MarketSalesPersonGui gui) {
		salesPersonGui = gui;
	}
	
	public MarketSalesPersonGui getGui() {
		return salesPersonGui;
	}
}
