package market;

import market.interfaces.MarketCustomer;
import application.Phonebook;
import person.Person;
import person.Role;
import testing.LoggedEvent;
import testing.EventLog;

public class MarketCustomerRole extends Role implements MarketCustomer {
	protected String roleName = "Market Customer";

	public EventLog log = new EventLog();
	
	//Data
	public enum MarketCustomerState {waitingForOrders, recievedOrders, payed, disputingBill}
	public MarketCustomerState state = MarketCustomerState.waitingForOrders;
	
	public double bill = 0;
	String item;
	int itemAmount;
	String name;

	public MarketCustomerRole(Person person, String pName, String rName) {
		super(person, pName, rName);
	}

	//Messages
	public void msgHereAreYourThings(String item, int itemAmount, double orderCost) {
		state = MarketCustomerState.recievedOrders;
		this.item = item;
		this.itemAmount = itemAmount;
		bill = orderCost;
		log.add(new LoggedEvent("Recieved msgHereAreYourThings"));
		stateChanged();
	}

	//Scheduler
	@Override
	public boolean pickAndExecuteAnAction() {
		if (state == MarketCustomerState.recievedOrders) {
			payBill();
			return true;
		}
		
		if (state == MarketCustomerState.payed) {
			exitMarket();
			return true;
		}
		
		return false;
	}

	//Actions
	public void payBill(){
		if (bill == Phonebook.getPhonebook().getMarket().inventory.get(item).price * itemAmount) {
			Phonebook.getPhonebook().getMarket().getSalesPerson(test).msgPayment(this, bill);
			person.money -= bill;
			state = MarketCustomerState.payed;
		}
		else {
			//message market that bill was wrong
			state = MarketCustomerState.payed;
		}
	}
	
	public void setItem(String item) {
		this.item = item;
	}
	
	public void exitMarket() {
		state = MarketCustomerState.waitingForOrders;
		this.setRoleInactive();
	}
}
