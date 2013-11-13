package market;

import java.util.HashMap;

import person.Person;
import person.Role;

public class MarketCustomer extends Role {


	public MarketCustomer(Person person) {
		super(person);
	}

	public void msgHereAreYourThings(String item, double orderCost) {
		
	}


	public MarketCustomer() {
		super();
	}
	
	protected boolean pickAndExecuteAnAction() {
		return false;
	}
}
