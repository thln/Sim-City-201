package market;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import application.Phonebook;
import person.Person;
import person.Role;

public class MarketRunnerRole extends Role {
	
	protected String roleName = "Market Runner";

	//Data
	//Correspondents
	Market market;
	SalesPersonRole salesPerson;
	UPSmanRole UPSMan;
	String name;
	
	private List<MarketOrder> orders = Collections.synchronizedList(new ArrayList<MarketOrder>());

	MarketRunnerRole(Person person, String pName, String rName) {
		super(person, pName, rName);
		market = Phonebook.getPhonebook().getMarket();
		salesPerson = Phonebook.getPhonebook().getMarket().salesPersonRole;
	}

	public MarketRunnerRole(String roleName) {
		super(roleName);
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
