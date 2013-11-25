package restaurant;

import application.WatchTime;
import person.Person;
import person.Role;
import person.Worker;

import application.gui.animation.*;

import restaurant.interfaces.Cashier;
import restaurant.interfaces.Cook;
import restaurant.test.mock.MockCashier;
import restaurant.test.mock.MockCook;

public class Restaurant {

	//Data
	String name;

	//Open and closing times
	public WatchTime openTime = new WatchTime(11);
	public WatchTime closeTime = new WatchTime(21);

	//Roles
	public HostRole hostRole = new HostRole("Host");
	public CookRole cookRole = new CookRole("Cook", this);
	public CashierRole cashierRole = new CashierRole("Cashier", this);
	public RevolvingStand theRevolvingStand = new RevolvingStand();
	private BuildingPanel restPanel;

	//Mocks
	public MockCook mockCook = new MockCook("MockCook");
	public MockCashier mockCashier = new MockCashier("MockCashier");

	public Restaurant(String name) {
		this.name = name;
	}

	//Methods
	public Role arrivedAtWork(Person person, String title)  {

		if (title == "host") {
			//Setting previous bank guard role to inactive
			if (hostRole.getPerson() != null) {
				Worker worker = (Worker) hostRole.getPerson();
				worker.roleFinishedWork();
			}
			//Setting bank guard role to new role
			hostRole.setPerson(person);
			return hostRole;
		}
		else if (title == "cook") 
		{
			//Setting previous bank guard role to inactive
			if (cookRole.getPerson() != null) 
			{
				Worker worker = (Worker) cookRole.getPerson();
				worker.roleFinishedWork();
			}
			//Setting bank guard role to new role
			cookRole.setPerson(person);
			return cookRole;
		}
		else if (title == "cashier") 
		{
			//Setting previous bank guard role to inactive
			if (cashierRole.getPerson() != null) 
			{
				Worker worker = (Worker) cashierRole.getPerson();
				worker.roleFinishedWork();
			}
			//Setting bank guard role to new role
			cashierRole.setPerson(person);
			return cashierRole;
		}
		else if (title == "waiter")
		{	
			WaiterRole waiter = new WaiterRole(person, person.getName(), title);
			hostRole.addWaiter(waiter);
			return waiter;
		}
		else if (title == "alternative waiter")
		{
			AltWaiterRole altWaiter = new AltWaiterRole(person, person.getName(), title);
			hostRole.addWaiter(altWaiter);
			return altWaiter;
		}
		//for waiter and alternative waiters, you message the host
		return null;
	}

	public void msgIWantFood(RestaurantCustomerRole cust, int xHome, int yHome) 
	{
		hostRole.msgIWantFood(cust, xHome, yHome);
	}

	public void goingOffWork(Person person) 
	{
		Worker worker = (Worker) person;

		if (worker.getWorkerRole().equals(hostRole)) 
		{
			hostRole = null;
		}
		if (worker.getWorkerRole().equals(cashierRole))
		{
			cashierRole = null;
		}
		if (worker.getWorkerRole().equals(cookRole))
		{
			cookRole = null;
		}
		//WAITERS AND ALT WAITERS
		//finish the "leave work" in Role.java 
		//make function in host to delete waiter
		//waiters have to finish duties before finishing waiter & no assignments
		//look at onBreak code to follow
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public RevolvingStand getRevolvingStand()
	{
		return theRevolvingStand;
	}

	public void setPanel(BuildingPanel panel) {
		restPanel = panel;
	}

	public Cook getCook(boolean test) {
		if (test) {
			return mockCook;
		}
		return cookRole;
	}

	public Cashier getCashier(boolean test) {
		if (test) {
			return mockCashier;
		}
		return cashierRole;

	}
}
