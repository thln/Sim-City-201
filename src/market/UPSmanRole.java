package market;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import person.Role;

public class UPSmanRole extends Role {

	//Data
	private List<MarketOrder> orders = Collections.synchronizedList(new ArrayList<MarketOrder>());
	protected String RoleName = "UPS man";
	
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
		//o.cook.HereIsOrder(o.item, o.totalItems);
		orders.remove(o);
	}

}
