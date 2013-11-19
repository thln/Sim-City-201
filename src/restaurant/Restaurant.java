package restaurant;

import person.Person;

public class Restaurant {

	public HostRole hostRole;
	
	public void msgIWantFood(RestaurantCustomerRole cust, int xHome, int yHome) {
		hostRole.msgIWantFood(cust, xHome, yHome);
	}

	public void goingOffWork(Person person) {
		if (person.getWorkerRole().equals(hostRole)) {
			hostRole = null;
		}
	}
	
}
