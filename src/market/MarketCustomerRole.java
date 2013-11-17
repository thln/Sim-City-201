package market;

import person.Person;
import person.Role;

public class MarketCustomerRole extends Role {
	protected String RoleName = "Market Customer";

	//Data
	boolean recievedItems = false;
	double money;
	double bill;
	String item;

	//Agent correspondents
	SalesPersonRole salesPerson;

	//	enum CustomerDesire {none, buyCar, buyFood};
	//	CustomerDesire desire = CustomerDesire.none;

	public MarketCustomerRole(Person person) {
		super(person);
	}

	//Messages
	public void msgHereAreYouThings(String item, double orderCost, SalesPersonRole salesPerson) {
		recievedItems = true;
		bill = orderCost;
		this.salesPerson = salesPerson;
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
		salesPerson.msgPayment(this, bill);
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
