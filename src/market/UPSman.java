package market;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import person.Role;
import restaurant.Order;

public class UPSman extends Role {

	//Data
	private List<Order> orders = Collections.synchronizedList(new ArrayList<Order>());

	//Messages
	public void msgDeliverOrder(Order o) {
		orders.add(o);
		stateChanged();
	}
	
	//Scheduler
	protected boolean pickAndExecuteAnAction() {
		if (!orders.isEmpty()) {
			for (Order o: orders) {
				deliverOrder(o);
				return true;
			}
		}
		return false;
	}
	
	//Actions
	public void deliverOrder(Order o) {
		o.customer.HereIsOrder(o);
		orders.remove(o);
	}

}
