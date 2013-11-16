package market;

import person.Person;
import person.Role;

public class MarketCustomer extends Role {

	public MarketCustomer(Person person) {
		super(person);
	}

	public void msgHereAreYourThings(String item, double orderCost) {
		
	}

	protected boolean pickAndExecuteAnAction() {
		return false;
	}
}
