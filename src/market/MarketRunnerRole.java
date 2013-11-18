package market;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import person.Person;
import person.Role;

public class MarketRunnerRole extends Role {
	
	protected String RoleName = "Market Runner";

	//Data
	//Correspondents
	Market market;
	SalesPersonRole salesPersonRole;
	UPSmanRole UPSMan;
	String name;
	
	private List<MarketOrder> orders = Collections.synchronizedList(new ArrayList<MarketOrder>());

	MarketRunnerRole(Person person, Market market, SalesPersonRole salesPersonRole, String name) {
		this.market = market;
		this.salesPersonRole = salesPersonRole;
		this.name = name;
	}

	//Messages
	public void msgHeresAnOrder(MarketOrder o) {
		orders.add(o);
		stateChanged();
	}

	//Scheduler
	protected boolean pickAndExecuteAnAction() {
		if (!orders.isEmpty()) {
			for (MarketOrder o: orders){
				processOrder(o);
				return true;
			}
		}
		return false;
	}

	//Actions
	public void processOrder(MarketOrder o) {
		if (o.customer != null) {
			decreaseInventoryBy(o.item, o.totalItems);
			salesPersonRole.msgOrderFulfilled(o);
			orders.remove(o);
		}
		else { //o.customerType is an instance of business
			decreaseInventoryBy(o.item, o.totalItems);
			UPSMan.msgDeliverOrder(o);
			orders.remove(o);
		}
	}

	public void decreaseInventoryBy(String item, int amount) {
		int newAmount = market.inventory.get(item) - amount;
		market.inventory.put(item, newAmount);
	}
}
