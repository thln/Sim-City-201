package market;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import person.Person;
import person.Role;

public class MarketRunner extends Role {

	//Data
	//Correspondents
	Market market;
	SalesPerson salesPerson;
	UPSman UPSMan;
	
	private List<MarketOrder> orders = Collections.synchronizedList(new ArrayList<MarketOrder>());

	MarketRunner(Person person, Market market, SalesPerson salesPerson) {
		this.market = market;
		this.salesPerson = salesPerson;
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
			salesPerson.msgOrderFulfilled(o);
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
