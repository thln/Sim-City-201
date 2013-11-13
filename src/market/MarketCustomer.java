package market;

import java.util.HashMap;

import person.Person;
import person.Role;

public class MarketCustomer extends Role {
<<<<<<< HEAD

	protected MarketCustomer(Person person) {
		super(person);
	}

	public void msgHereAreYourThings(String item, double orderCost) {
		
	}

	protected boolean pickAndExecuteAnAction() {
		return false;
	}

=======
	public MarketCustomer() {
		super();
	}
	
	protected boolean pickAndExecuteAnAction() {
		return false;
	}
>>>>>>> 22c324ebdc08c6de01990a208a16a1ff66d3c517
}
