package market;

import person.Person;
import person.Role;

public class MarketCustomerRole extends Role {

//	enum CustomerDesire {none, buyCar, buyFood};
	
	protected String RoleName = "Market Customer";
	CustomerDesire desire = CustomerDesire.none;
	
	public MarketCustomerRole(Person person) {
		super(person);
	}

	public void msgHereAreYourThings(String item, double orderCost) {
		
	}

	protected boolean pickAndExecuteAnAction() {
		return false;
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
