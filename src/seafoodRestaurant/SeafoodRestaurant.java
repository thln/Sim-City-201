package seafoodRestaurant;
import java.util.Vector;

import person.Person;
import person.Role;
import person.Worker;
import application.WatchTime;
import application.gui.animation.BuildingPanel;

public class SeafoodRestaurant {

	//Data
	String name;
	public boolean userClosed = false;


	//List of Waiters
	private Vector<SeafoodRestaurantWaiterRole> waiters = new Vector<SeafoodRestaurantWaiterRole>();

	//Open and closing times
	public WatchTime openTime = new WatchTime(11);
	public WatchTime closeTime = new WatchTime(21);

	//Roles
	public SeafoodRestaurantHostRole seafoodRestaurantHostRole = new SeafoodRestaurantHostRole("Host");
	public SeafoodRestaurantCookRole seafoodRestaurantCookRole = new SeafoodRestaurantCookRole("Cook", this);
	//public RestaurantCookGui cookGui = new RestaurantCookGui(seafoodRestaurantCookRole);

	public SeafoodRestaurantCashierRole seafoodRestaurantCashierRole = new SeafoodRestaurantCashierRole("Cashier", this);
	public SeafoodRestaurantRevolvingStand theRevolvingStand = new SeafoodRestaurantRevolvingStand();
	private BuildingPanel restPanel;

	//Mocks
	//public SeafoodRestaurantMockCook seafoodRestaurantMockCook = new SeafoodRestaurantMockCook("MockCook");
	//public SeafoodRestaurantMockCashier seafoodRestaurantMockCashier = new SeafoodRestaurantMockCashier("MockCashier");

	public SeafoodRestaurant(String name) {
		this.name = name;
		//seafoodRestaurantCookRole.setGui(cookGui);
	}

	//Methods
	public Role arrivedAtWork(Person person, String title)  {

		if (title == "host") {
			//Setting previous bank guard role to inactive
			if (seafoodRestaurantHostRole.getPerson() != null) {
				Worker worker = (Worker) seafoodRestaurantHostRole.getPerson();
				worker.roleFinishedWork();
			}
			//Setting bank guard role to new role
			seafoodRestaurantHostRole.setPerson(person);
			if (isOpen()) {
				seafoodRestaurantHostRole.msgRestaurantOpen();
			}
			return seafoodRestaurantHostRole;
		}
		else if (title == "cook") {
			//Setting previous bank guard role to inactive
			if (seafoodRestaurantCookRole.getPerson() != null) {
				Worker worker = (Worker) seafoodRestaurantCookRole.getPerson();
				worker.roleFinishedWork();
			}
			//Setting cook role to new role
			seafoodRestaurantCookRole.setPerson(person);
			if (isOpen()) {
				seafoodRestaurantHostRole.msgRestaurantOpen();
			}
			//restPanel.addGui(cookGui);
			return seafoodRestaurantCookRole;
		}
		else if (title.contains("cashier")) {
			//Setting previous bank guard role to inactive
			if (seafoodRestaurantCashierRole.getPerson() != null) {
				Worker worker = (Worker) seafoodRestaurantCashierRole.getPerson();
				worker.roleFinishedWork();
			}
			//Setting cashier role to new role
			seafoodRestaurantCashierRole.setPerson(person);
			if (isOpen()) {
				seafoodRestaurantHostRole.msgRestaurantOpen();
			}
			return seafoodRestaurantCashierRole;
		}
		else if (title == "waiter") {	
			SeafoodRestaurantWaiterRole waiter = new SeafoodRestaurantWaiterRole(person, person.getName(), title);
//			if (waiters.size() <= 12) {
//				//RestaurantWaiterGui g = new RestaurantWaiterGui(waiter);
//				//restPanel.addGui(g);
//				//waiter.setGui(g);
//				//g.setHomePosition(5, (55 + (22 * waiters.size())));
//			}
//			else if (waiters.size() <= 24) {
//				RestaurantWaiterGui g = new RestaurantWaiterGui(waiter);
//				restPanel.addGui(g);
//				waiter.setGui(g);
//				g.setHomePosition(27, (55 + (22 * (waiters.size()-12))));
//			}
//
//			waiters.add(waiter);
			seafoodRestaurantHostRole.newWaiter(waiter);
			if (isOpen()) {
				seafoodRestaurantHostRole.msgRestaurantOpen();
			}
			return waiter;
		}
		else if (title == "altWaiter") {
			SeafoodRestaurantAltWaiterRole altWaiter = new SeafoodRestaurantAltWaiterRole(person, person.getName(), title);
//			if (waiters.size() <= 12) {
//				RestaurantWaiterGui g = new RestaurantWaiterGui(altWaiter);
//				restPanel.addGui(g);
//				altWaiter.setGui(g);
//				g.setHomePosition(5, (55 + (22 * waiters.size())));
//			}
//			else if (waiters.size() <= 24) {
//				RestaurantWaiterGui g = new RestaurantWaiterGui(altWaiter);
//				restPanel.addGui(g);
//				altWaiter.setGui(g);
//				g.setHomePosition(27, (55 + (22 * (waiters.size()-12))));
//			}

			waiters.add(altWaiter);
			seafoodRestaurantHostRole.newWaiter(altWaiter);
			if (isOpen()) {
				seafoodRestaurantHostRole.msgRestaurantOpen();
			}
			return altWaiter;
		}
		//for waiter and alternative waiters, you message the host
		return null;
	}

//	public boolean arrived(SeafoodRestaurantCustomerRole rCR) {
//		if (customers.size() <= 12) {
//			RestaurantCustomerGui rCG = (RestaurantCustomerGui) rCR.gui;
//			rCG.setHomePosition((22 * customers.size()), 10);
//			restPanel.addGui(rCG);
//			customers.add(rCR);
//			rCR.gotHungry((22 * customers.size()), 10);
//			return true;
//		}
//		else if (customers.size() <= 24) {
//			RestaurantCustomerGui rCG = (RestaurantCustomerGui) rCR.gui;
//			rCG.setHomePosition((22 * (customers.size() - 12)), 32);
//			restPanel.addGui(rCG);
//			customers.add(rCR);
//			rCR.gotHungry((22 * (customers.size() - 12)), 32);
//			return true;
//		}
//		return false;
//	}

	public void goingOffWork(Person person) {
		Worker worker = (Worker) person;

		if (worker.getWorkerRole().equals(seafoodRestaurantHostRole)) {
			seafoodRestaurantHostRole = null;
			//restPanel.removeGui(worker.getWorkerRole().gui);
		}
		if (worker.getWorkerRole().equals(seafoodRestaurantCashierRole)) {
			seafoodRestaurantCashierRole = null;
		}
		if (worker.getWorkerRole().equals(seafoodRestaurantCookRole)) {
			seafoodRestaurantCookRole = null;
			//restPanel.removeGui(cookGui);
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

	public SeafoodRestaurantRevolvingStand getRevolvingStand() {
		return theRevolvingStand;
	}

	public void setPanel(BuildingPanel panel) {
		restPanel = panel;
	}

	public SeafoodRestaurantCookRole getCook(boolean test) {
//		if (test) {
//			return seafoodRestaurantMockCook;
//		}
		return seafoodRestaurantCookRole;
	}

	public SeafoodRestaurantCashierRole getCashier(boolean test) {
//		if (test) {
//			return seafoodRestaurantMockCashier;
//		}
		return seafoodRestaurantCashierRole;
	}

	public boolean isOpen() {
		if (seafoodRestaurantHostRole.getPerson() != null && seafoodRestaurantHostRole.MyWaiters.size() != 0 && seafoodRestaurantCookRole.getPerson() != null && seafoodRestaurantCashierRole != null && !userClosed)
			return true;
		else 
			return false;
	}

	public void setBuildingPanel (BuildingPanel buildingPanel) {
		restPanel = buildingPanel;
	}

	public void removeWaiter(SeafoodRestaurantWaiterRole seafoodRestaurantWaiterRole) {
		//waiters.remove(seafoodRestaurantWaiterRole);
		//restPanel.removeGui(seafoodRestaurantWaiterRole.gui);
	}

	public void removeCustomer(SeafoodRestaurantCustomerRole customerRole) {
		//customers.remove(customerRole);
		//restPanel.removeGui(customerRole.gui);
	}

	public void closeBuilding(){
		userClosed = true;
		seafoodRestaurantHostRole.msgLeaveRole();
		for (SeafoodRestaurantWaiterRole w1: waiters) {
			w1.msgLeaveRole();
			restPanel.removeGui(w1.gui);
		}
		seafoodRestaurantCookRole.msgLeaveRole();
		//restPanel.removeGui(cookGui);

		seafoodRestaurantCashierRole.msgLeaveRole();
	}
}
