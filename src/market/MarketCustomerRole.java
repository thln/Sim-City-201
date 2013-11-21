package market;

import market.MarketOrder.orderState;
import application.Phonebook;
import person.Person;
import person.Role;

public class MarketCustomerRole extends Role {
	protected String roleName = "Market Customer";

	//Data
	
	enum MarketCustomerState {waitingForOrders, recievedOrders, payed, disputingBill}
	MarketCustomerState state;

	double money;
	double bill;
	String item;
	int itemAmount;
	String name;

	public MarketCustomerRole(Person person, String pName, String rName) {
		super(person, pName, rName);
	}

	//Messages
	public void msgHereAreYourThings(String item, int itemAmount, double orderCost) {
		state = MarketCustomerState.recievedOrders;
		this.itemAmount = itemAmount;
		bill = orderCost;
	}

	//Scheduler
	@Override
	protected boolean pickAndExecuteAnAction() {
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
	private void payBill(){
		if (bill == Phonebook.getPhonebook().getMarket().marketItemsForSale.get(item).price * itemAmount) {
			Phonebook.getPhonebook().getMarket().salesPersonRole.msgPayment(this, bill);
			money -= bill;
			state = MarketCustomerState.payed;
		}
		else {
			//message market that bill was wrong
			state = MarketCustomerState.disputingBill;
		}
	}
	
	private void exitMarket() {
		this.setRoleActive();
	}
}
