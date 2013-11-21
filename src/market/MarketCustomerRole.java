package market;

import application.Phonebook;
import person.Person;
import person.Role;

public class MarketCustomerRole extends Role {
	protected String roleName = "Market Customer";

	//Data
	boolean recievedItems = false;
	double money;
	double bill;
	String item;
	String name;

	public MarketCustomerRole(Person person, String pName, String rName) {
		super(person, pName, rName);
	}

	//Messages
	public void msgHereAreYouThings(String item, double orderCost) {
		recievedItems = true;
		bill = orderCost;
	}

	//Scheduler
	@Override
	protected boolean pickAndExecuteAnAction() {
		if (recievedItems == true) {
			payBill();
			return true;
		}
		return false;
	}

	//Actions
	public void payBill(){
		Phonebook.getPhonebook().getMarket().salesPersonRole.msgPayment(this, bill);
		money -= bill;
		recievedItems = false;
	}

	//	public void setDesire(String string) {
	//		if (string == "buyCar") {
	//			desire = CustomerDesire.buyCar;
	//		}
	//		if (string == "buyFood") {
	//			desire = CustomerDesire.buyCar;
	//		}
	//	}
}
