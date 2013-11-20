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
	
	String name;
	
	private List<MarketOrder> orders = Collections.synchronizedList(new ArrayList<MarketOrder>());

	MarketRunnerRole(Person person, String pName, String rName) {
		super(person, pName, rName);
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
			Phonebook.getPhonebook().getMarket().salesPersonRole.msgOrderFulfilled(o);
			orders.remove(o);
		}
		else { //o.customerType is an instance of business
			decreaseInventoryBy(o.item, o.totalItems);
			Phonebook.getPhonebook().getMarket().UPSmanRole.msgDeliverOrder(o);
			orders.remove(o);
		}
	}

	public void decreaseInventoryBy(String item, int amount) {
		int newAmount = Phonebook.getPhonebook().getMarket().inventory.get(item) - amount;
		Phonebook.getPhonebook().getMarket().inventory.put(item, newAmount);
	}
}
