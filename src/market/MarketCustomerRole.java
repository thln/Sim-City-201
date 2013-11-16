package market;

import person.Person;
import person.Role;

public class MarketCustomerRole extends Role {

	protected String RoleName = "Market Customer";
	
	public MarketCustomerRole(Person person) {
		super(person);
	}

	public void msgHereAreYourThings(String item, double orderCost) {
		
	}

	protected boolean pickAndExecuteAnAction() {
		return false;
	}
}
