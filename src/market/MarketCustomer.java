package market;

import person.Role;

public class MarketCustomer extends Role {
	public MarketCustomer() {
		super();
	}
	
	protected boolean pickAndExecuteAnAction() {
		return false;
	}
}
