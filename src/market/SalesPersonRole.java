package market;

import java.util.*;

import application.Phonebook;
import market.MarketOrder.orderState;
import market.interfaces.SalesPerson;
import person.Person;
import person.Role;
import person.Worker;
import restaurant.CookRole;
import restaurant.Restaurant;

public class SalesPersonRole extends Role implements SalesPerson {
	
	protected String roleName = "Sales Person";
	String name;
	Market market;

	//Data
	private List<MarketOrder> orders = Collections.synchronizedList(new ArrayList<MarketOrder>());

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
	/* (non-Javadoc)
	 * @see market.SalesPerson#msgIWantProducts(market.MarketCustomerRole, java.lang.String, int)
	 */
	@Override
	public void msgIWantProducts(MarketCustomerRole customer, String item, int numWanted) {
		orders.add(new MarketOrder(customer, item, numWanted));
		stateChanged();
	}

	/* (non-Javadoc)
	 * @see market.SalesPerson#msgIWantProducts(restaurant.Restaurant, java.lang.String, int)
	 */
	@Override
	public void msgIWantProducts(Restaurant restaurant, String item, int numWanted) {
		orders.add(new MarketOrder(restaurant, item, numWanted));
		stateChanged();
	}
	
	/* (non-Javadoc)
	 * @see market.SalesPerson#msgOrderFulfilled(market.MarketOrder)
	 */
	@Override
	public void msgOrderFulfilled(MarketOrder o) {
		for (MarketOrder MO : orders) {
			if (MO.equals(o)) {
				MO.state = orderState.itemsFound;
				stateChanged();
				return;
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see market.SalesPerson#msgOrderDelivered(market.MarketOrder)
	 */
	@Override
	public void msgOrderDelivered(MarketOrder o) {
		for (MarketOrder MO : orders) {
			if (MO.equals(o)) {
				MO.state = orderState.itemsDelivered;
				stateChanged();
				return;
			}
		}
	}

	/* (non-Javadoc)
	 * @see market.SalesPerson#msgPayment(market.MarketCustomerRole, double)
	 */
	@Override
	public void msgPayment(MarketCustomerRole customer, double payment) {
		market.money += payment;
		for (MarketOrder o : orders) {
			if (o.customer.equals(customer)) {
				orders.remove(o);
				return;
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see market.SalesPerson#msgPayment(restaurant.Restaurant, double)
	 */
	@Override
	public void msgPayment(Restaurant restaurant, double payment) {
		market.money += payment;
		for (MarketOrder o : orders) {
			if (o.customer.equals(restaurant)) {
				orders.remove(o);
				return;
			}
		}
	}

	//Scheduler
	protected boolean pickAndExecuteAnAction() {
		if (!orders.isEmpty()) {
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
		
		if (leaveRole){
			((Worker) person).roleFinishedWork();
			leaveRole = false;
			return true;
		}
		
		return false;
	}

	//Actions
	private void findItems(MarketOrder o) {
		o.state = orderState.processing;
		
		if (market.inventory.get(o.item).amount == 0) {
			o.restaurant.cookRole.msgCantFulfill(o.item, 0, o.itemAmountOrdered, market);
			orders.remove(o);
			stateChanged();
			return;
		}
		
		market.marketRunnerRole.msgHeresAnOrder(o);
		stateChanged();
	}

	private void giveCustomerItems(MarketOrder o) {
		o.state = orderState.gaveToCustomer;
		o.orderCost = market.inventory.get(o.item).price  * o.itemAmountFulfilled;
		o.customer.msgHereAreYourThings(o.item, o.itemAmountFulfilled, o.orderCost);
		stateChanged();
	}

	private void askForPayment(MarketOrder o) {
		o.state = orderState.gaveToCustomer;
		o.orderCost = market.inventory.get(o.item).price * o.itemAmountFulfilled;
		o.restaurant.cashierRole.msgPleasePayForItems(o.item, o.itemAmountFulfilled, o.orderCost, this);
		stateChanged();
	}
}
