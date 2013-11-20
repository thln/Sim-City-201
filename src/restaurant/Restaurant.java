package restaurant;

import application.WatchTime;
import person.Person;
import person.Role;
import person.Worker;

public class Restaurant {

	//Open and closing times
	public WatchTime openTime = new WatchTime(11);
	public WatchTime closeTime = new WatchTime(21);

	//Roles
	public HostRole hostRole = new HostRole("Host");
	public CookRole cookRole = new CookRole("Cook");
	public CashierRole cashierRole = new CashierRole("Cashier");

	public Role arrivedAtWork(Person person, String title) {
		if (title == "host") {
			//Setting previous bank guard role to inactive
			if (hostRole.getPerson() != null) {
				Worker worker = (Worker) hostRole.getPerson();
				worker.goOffWork();
			}
			//Setting bank guard role to new role
			hostRole.setPerson(person);
			return hostRole;
		}
		else if (title == "cook") {
			//Setting previous bank guard role to inactive
			if (cookRole.getPerson() != null) {
				Worker worker = (Worker) cookRole.getPerson();
				worker.goOffWork();
			}
			//Setting bank guard role to new role
			cookRole.setPerson(person);
			return cookRole;
		}
		else if (title == "cashier") {
			//Setting previous bank guard role to inactive
			if (cashierRole.getPerson() != null) {
				Worker worker = (Worker) cashierRole.getPerson();
				worker.goOffWork();
			}
			//Setting bank guard role to new role
			cashierRole.setPerson(person);
			return cashierRole;
		}
		else
			return null;
	}

	public void msgIWantFood(RestaurantCustomerRole cust, int xHome, int yHome) {
		hostRole.msgIWantFood(cust, xHome, yHome);
	}

	public void goingOffWork(Person person) {
		Worker worker = (Worker) person;
		
		if (worker.getWorkerRole().equals(hostRole)) {
			hostRole = null;
		}
	}

}
