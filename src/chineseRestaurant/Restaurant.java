package chineseRestaurant;

import java.util.Vector;

import chineseRestaurant.interfaces.Cashier;
import chineseRestaurant.interfaces.Cook;
import chineseRestaurant.test.mock.MockCashier;
import chineseRestaurant.test.mock.MockCook;
import person.Person;
import person.Role;
import person.Worker;
import application.WatchTime;
import application.gui.animation.BuildingPanel;
import application.gui.animation.agentGui.RestaurantCookGui;
import application.gui.animation.agentGui.RestaurantCustomerGui;
import application.gui.animation.agentGui.RestaurantWaiterGui;

public class Restaurant{

	//Data
	String name;
	public boolean userClosed = false;

	//List of Customers
	private Vector<RestaurantCustomerRole> customers = new Vector<RestaurantCustomerRole>();

	//List of Waiters
	private Vector<WaiterRole> waiters = new Vector<WaiterRole>();

	//Open and closing times
	public WatchTime openTime = new WatchTime(11);
	public WatchTime closeTime = new WatchTime(21);

	//Roles
	public HostRole hostRole = new HostRole("Host");
	public CookRole cookRole = new CookRole("Cook", this);
	public RestaurantCookGui cookGui = new RestaurantCookGui(cookRole);
	
	public CashierRole cashierRole = new CashierRole("Cashier", this);
	public RevolvingStand theRevolvingStand = new RevolvingStand();
	private BuildingPanel restPanel;

	//Mocks
	public MockCook mockCook = new MockCook("MockCook");
	public MockCashier mockCashier = new MockCashier("MockCashier");

	public Restaurant(String name) {
		this.name = name;
		cookRole.setGui(cookGui);
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
			if (isOpen()) {
				hostRole.msgRestaurantOpen();
			}
			return hostRole;
		}
		else if (title == "cook") {
			//Setting previous bank guard role to inactive
			if (cookRole.getPerson() != null) {
				Worker worker = (Worker) cookRole.getPerson();
				worker.roleFinishedWork();
			}
			//Setting cook role to new role
			cookRole.setPerson(person);
			if (isOpen()) {
				hostRole.msgRestaurantOpen();
			}
			restPanel.addGui(cookGui);
			return cookRole;
		}
		else if (title.contains("cashier")) {
			//Setting previous bank guard role to inactive
			if (cashierRole.getPerson() != null) {
				Worker worker = (Worker) cashierRole.getPerson();
				worker.roleFinishedWork();
			}
			//Setting cashier role to new role
			cashierRole.setPerson(person);
			if (isOpen()) {
				hostRole.msgRestaurantOpen();
			}
			return cashierRole;
		}
		else if (title == "waiter") {	
			WaiterRole waiter = new WaiterRole(person, person.getName(), title);
			if (waiters.size() <= 12) {
				RestaurantWaiterGui g = new RestaurantWaiterGui(waiter);
				restPanel.addGui(g);
				waiter.setGui(g);
				g.setHomePosition(5, (55 + (22 * waiters.size())));
			}
			else if (waiters.size() <= 24) {
				RestaurantWaiterGui g = new RestaurantWaiterGui(waiter);
				restPanel.addGui(g);
				waiter.setGui(g);
				g.setHomePosition(27, (55 + (22 * (waiters.size()-12))));
			}
			
			waiters.add(waiter);
			hostRole.addWaiter(waiter);
			if (isOpen()) {
				hostRole.msgRestaurantOpen();
			}
			return waiter;
		}
		else if (title == "altWaiter") {
			AltWaiterRole altWaiter = new AltWaiterRole(person, person.getName(), title);
			if (waiters.size() <= 12) {
				RestaurantWaiterGui g = new RestaurantWaiterGui(altWaiter);
				restPanel.addGui(g);
				altWaiter.setGui(g);
				g.setHomePosition(5, (55 + (22 * waiters.size())));
			}
			else if (waiters.size() <= 24) {
				RestaurantWaiterGui g = new RestaurantWaiterGui(altWaiter);
				restPanel.addGui(g);
				altWaiter.setGui(g);
				g.setHomePosition(27, (55 + (22 * (waiters.size()-12))));
			}
			
			waiters.add(altWaiter);
			hostRole.addWaiter(altWaiter);
			if (isOpen()) {
				hostRole.msgRestaurantOpen();
			}
			return altWaiter;
		}
		//for waiter and alternative waiters, you message the host
		return null;
	}

	public boolean arrived(RestaurantCustomerRole rCR) {
		if (customers.size() <= 12) {
			RestaurantCustomerGui rCG = (RestaurantCustomerGui) rCR.gui;
			rCG.setHomePosition((22 * customers.size()), 10);
			restPanel.addGui(rCG);
			customers.add(rCR);
			rCR.gotHungry((22 * customers.size()), 10);
			return true;
		}
		else if (customers.size() <= 24) {
			RestaurantCustomerGui rCG = (RestaurantCustomerGui) rCR.gui;
			rCG.setHomePosition((22 * (customers.size() - 12)), 32);
			restPanel.addGui(rCG);
			customers.add(rCR);
			rCR.gotHungry((22 * (customers.size() - 12)), 32);
			return true;
		}
		return false;
	}

	public void goingOffWork(Person person) {
		Worker worker = (Worker) person;

		if (worker.getWorkerRole().equals(hostRole)) {
			hostRole = null;
		}
		if (worker.getWorkerRole().equals(cashierRole)) {
			cashierRole = null;
		}
		if (worker.getWorkerRole().equals(cookRole)) {
			cookRole = null;
			restPanel.removeGui(cookGui);
		}
		//WAITERS AND ALT WAITERS
		//finish the "leave work" in Role.java 
		//make function in host to delete waiter
		//waiters have to finish duties before finishing waiter & no assignments
		//look at onBreak code to follow
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RevolvingStand getRevolvingStand() {
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

	public boolean isOpen() {
		if (hostRole.getPerson() != null && hostRole.waiters.size() != 0 && cookRole.getPerson() != null && cashierRole != null && !userClosed)
			return true;
		else 
			return false;
	}
	
	public void setBuildingPanel (BuildingPanel buildingPanel) {
		restPanel = buildingPanel;
	}

	public void removeWaiter(WaiterRole waiterRole) {
		waiters.remove(waiterRole);
		restPanel.removeGui(waiterRole.gui);
	}
	
	public void removeCustomer(RestaurantCustomerRole customerRole) {
		customers.remove(customerRole);
		restPanel.removeGui(customerRole.gui);
	}
	
	public void closeBuilding(){
		userClosed = true;
		hostRole.msgLeaveRole();
		for (WaiterRole w1: waiters) {
			w1.msgLeaveRole();
		}
		cookRole.msgLeaveRole();
		cashierRole.msgLeaveRole();
	}
}
