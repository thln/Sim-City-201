package market;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import person.Person;
import person.Role;
import restaurant.Market;

public class UPSmanRole extends Role {

	//Data
	private List<MarketOrder> orders = Collections.synchronizedList(new ArrayList<MarketOrder>());
	protected String roleName = "UPS man";
	String name;
	Market market;

	public UPSmanRole (Person p, String pName, String rName, Market market) {
		super(p, pName, rName);
		this.market = market;
	}

	public UPSmanRole(String roleName, Market market) {
		super(roleName);
		this.market = market;
	}

	//Messages
	public void msgDeliverOrder(MarketOrder o) {
		orders.add(o);
		stateChanged();
	}

	//Scheduler
	protected boolean pickAndExecuteAnAction() {
		if (!orders.isEmpty()) {
			for (MarketOrder o: orders) {
				deliverOrder(o);
				return true;
			}
		}
		return false;
	}

	//Actions
	public void deliverOrder(MarketOrder o) {
		o.cookRole.msgOrderFulfillment(o.item, o.totalItems, o.itemAmountOrdered, market);
		orders.remove(o);
	}

}
