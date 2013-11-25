package market;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import market.interfaces.UPSman;
import person.Person;
import person.Role;
import person.Worker;
import testing.EventLog;
import testing.LoggedEvent;

public class UPSmanRole extends Role implements UPSman {

	//Data
	public List<MarketOrder> orders = Collections.synchronizedList(new ArrayList<MarketOrder>());
	protected String roleName = "UPS man";
	String name;
	Market market;

	public EventLog log = new EventLog();
	
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
		log.add(new LoggedEvent("Recieved msgDeliverOrder"));
		orders.add(o);
		stateChanged();
	}

	//Scheduler
	public boolean pickAndExecuteAnAction() {
		if (!orders.isEmpty()) {
			for (MarketOrder o: orders) {
				deliverOrder(o);
				return true;
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
	public void deliverOrder(MarketOrder o) {
		o.restaurant.getCook(test).msgOrderFulfillment(o.item, o.itemAmountFulfilled, o.itemAmountOrdered);
		market.getSalesPerson(test).msgOrderDelivered(o);
		orders.remove(o);
	}

}
