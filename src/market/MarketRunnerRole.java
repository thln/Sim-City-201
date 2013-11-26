package market;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import market.interfaces.MarketRunner;
import person.Person;
import person.Role;
import person.Worker;
import testing.EventLog;
import testing.LoggedEvent;
import application.gui.animation.agentGui.MarketRunnerGui;

public class MarketRunnerRole extends Role implements MarketRunner {
	
	MarketRunnerGui marketRunnerGui = (MarketRunnerGui) gui;
	
	protected String roleName = "Market Runner";

	//Data
	String name;
	Market market;
	
	public List<MarketOrder> orders = Collections.synchronizedList(new ArrayList<MarketOrder>());
	
	public EventLog log = new EventLog();

	public MarketRunnerRole(Person person, String pName, String rName, Market market) {
		super(person, pName, rName);
		this.market = market;
	}

	public MarketRunnerRole(String roleName, Market market) {
		super(roleName);
		this.market = market;
	}

	//Messages
	public void msgHeresAnOrder(MarketOrder o) {
		print("Recieved and order to fulfill");
		log.add(new LoggedEvent("Recieved msgHeresAnOrder"));
		orders.add(o);
		stateChanged();
	}

	//Scheduler
	public boolean pickAndExecuteAnAction() {
		if (!orders.isEmpty()) {
			for (MarketOrder o: orders){
				processOrder(o);
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
	public void processOrder(MarketOrder o) {
		
		if (o.customer != null) {
			decreaseInventoryBy(o.item, o.itemAmountOrdered);
			o.itemAmountFulfilled = o.itemAmountOrdered;
			if (o.customer instanceof MarketCustomerRole) {
				print("Fulfilled order for customer: " + ((MarketCustomerRole) o.customer).getName());
			}
			market.getSalesPerson(test).msgOrderFulfilled(o);
			orders.remove(o);
		}
		else { //o.customerType is an instance of business
			decreaseInventoryBy(o.item, o.itemAmountOrdered);
			o.itemAmountFulfilled = o.itemAmountOrdered;
			print("Fulfilled order for restaurant for: " + o.item);
			market.getUPSman(test).msgDeliverOrder(o);
			orders.remove(o);
		}
	}

	public void decreaseInventoryBy(String item, int amount) {
		int newAmount = market.inventory.get(item).amount - amount;
		market.inventory.get(item).setInventory(newAmount);
	}
}
