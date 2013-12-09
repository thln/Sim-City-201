package americanRestaurant;

import java.awt.Point;
import java.util.Vector;

import person.Person;
import person.Role;
import person.Worker;
import americanRestaurant.interfaces.AmericanRestaurantCashier;
import application.WatchTime;
import application.gui.animation.BuildingPanel;
import application.gui.animation.agentGui.RestaurantCustomerGui;
import application.gui.animation.agentGui.RestaurantWaiterGui;

public class AmericanRestaurant {
	//Data
		String name;
		public boolean userClosed = false;
		public Point location; 
		private Point closestStop;

		//Open and closing times
		public WatchTime openTime = new WatchTime(11);
		public WatchTime closeTime = new WatchTime(21);

		//Roles
		public AmericanRestaurantHostRole americanHost = new AmericanRestaurantHostRole("American Host");
		public AmericanRestaurantCookRole americanCook = new AmericanRestaurantCookRole("American Cook");		
		public AmericanRestaurantCashierRole americanCashier = new AmericanRestaurantCashierRole("American Cashier");
		//public AmericanRestaurantRevolvingStand theRevolvingStand = new AmericanRestaurantRevolvingStand();
		private BuildingPanel restPanel;

		//Mocks
	//	public AmericanRestaurantMockCook AmericanRestaurantMockCook = new AmericanRestaurantMockCook("AmericanMockCook");
	//	public AmericanRestaurantMockCashier AmericanRestaurantMockCashier = new AmericanRestaurantMockCashier("AmericanMockCashier");

		public AmericanRestaurant(String name) {
			location = new Point(220, 250);
			this.name = name;
		}

		//Methods
		public Role arrivedAtWork(Person person, String title)  {

			if (title == "host") {
				//Setting previous bank guard role to inactive
//				if (AmericanRestaurantCookRole.getPerson() != null) {
//					Worker worker = (Worker) AmericanRestaurantCookRole.getPerson();
//					worker.roleFinishedWork();
//				}
				//Setting bank guard role to new role
				americanHost.setPerson(person);
				if (isOpen()) {
					americanHost.msgRestaurantOpen();
				}
				return americanHost;
			}
			else if (title == "cook") {
//				//Setting previous bank guard role to inactive
//				if (AmericanRestaurantCookRole.getPerson() != null) {
//					Worker worker = (Worker) AmericanRestaurantCookRole.getPerson();
//					worker.roleFinishedWork();
//				}
				//Setting cook role to new role
				americanHost.setPerson(person);
				if (isOpen()) {
					americanHost.msgRestaurantOpen();
				}
		//		AmericanRestaurantCookRole.setGui(cookGui);
		//		restPanel.addGui(cookGui);
				return americanHost;
			}
			else if (title.contains("cashier")) {
				//Setting previous bank guard role to inactive
//				if (AmericanRestaurantCashierRole.getPerson() != null) {
//					Worker worker = (Worker) AmericanRestaurantCashierRole.getPerson();
//					worker.roleFinishedWork();
//				}
				//Setting americanRestaurantCashier role to new role
				americanCashier.setPerson(person);
				if (isOpen()) {
					americanHost.msgRestaurantOpen();
				}
				return americanCashier;
			}
			else if (title == "waiter") {	
				AmericanRestaurantWaiterRole waiter = new AmericanRestaurantWaiterRole(person, person.getName(), title, this);
				if (americanHost.Waiters.size() <= 12) {
					//RestaurantWaiterGui g = new RestaurantWaiterGui(waiter);
					//restPanel.addGui(g);
					//waiter.setGui(g);
					//g.setHomePosition(5, (55 + (22 * waiters.size())));
				}
				else if (americanHost.Waiters.size() <= 24) {
					//RestaurantWaiterGui g = new RestaurantWaiterGui(waiter);
					//restPanel.addGui(g);
					//waiter.setGui(g);
					//g.setHomePosition(27, (55 + (22 * (waiters.size()-12))));
				}

				americanHost.msgAddWaiter(waiter);
//				if (isOpen()) {
//					AmericanRestaurantCookRole.msgRestaurantOpen();
//				}
				return waiter;
			}
			
//			else if (title == "altWaiter") {
//				AmericanRestaurantAltWaiterRole altWaiter = new AmericanRestaurantAltWaiterRole(person, person.getName(), title);
//				if (waiters.size() <= 12) {
//					RestaurantWaiterGui g = new RestaurantWaiterGui(altWaiter);
//					restPanel.addGui(g);
//					altWaiter.setGui(g);
//					g.setHomePosition(5, (55 + (22 * waiters.size())));
//				}
//				else if (waiters.size() <= 24) {
//					RestaurantWaiterGui g = new RestaurantWaiterGui(altWaiter);
//					restPanel.addGui(g);
//					altWaiter.setGui(g);
//					g.setHomePosition(27, (55 + (22 * (waiters.size()-12))));
//				}
//				
//				waiters.add(altWaiter);
//				AmericanRestaurantCookRole.addWaiter(altWaiter);
//				if (isOpen()) {
//					AmericanRestaurantCookRole.msgRestaurantOpen();
//				}
//				return altWaiter;
//			}
			//for waiter and alternative waiters, you message the host
			return null;
		}

		public boolean customerArrived(AmericanRestaurantCustomerRole rCR) {
			if (americanHost.waitingCustomers.size() <= 12) {
				//RestaurantCustomerGui rCG = (RestaurantCustomerGui) rCR.getGui();
				//rCG.setHomePosition((22 * customers.size()), 10);
				//restPanel.addGui(rCG);
				americanHost.msgIWantToEat(rCR);
				//rCR.gotHungry((22 * customers.size()), 10);
				return true;
			}
			else if (americanHost.waitingCustomers.size() <= 24) {
				//RestaurantCustomerGui rCG = (RestaurantCustomerGui) rCR.getGui();
				//rCG.setHomePosition((22 * (customers.size() - 12)), 32);
				//restPanel.addGui(rCG);
				americanHost.msgIWantToEat(rCR);
				//rCR.gotHungry((22 * (customers.size() - 12)), 32);
				return true;
			}
			return false;
		}

		public void goingOffWork(Person person) {
			Worker worker = (Worker) person;

			if (worker.getWorkerRole().equals(americanHost)) {
				americanHost = null;
				//restPanel.removeGui(AmericanRestaurantCookRole.getGui());
			}
			if (worker.getWorkerRole().equals(americanCashier)) {
				americanCashier = null;
			}
			if (worker.getWorkerRole().equals(americanHost)) {
				americanHost = null;
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

//		public AmericanRestaurantRevolvingStand getRevolvingStand() {
//			return theRevolvingStand;
//		}
//
//		public void setPanel(BuildingPanel panel) {
//			restPanel = panel;
//		}
//
//		public AmericanRestaurantCook getCook(boolean test) {
//			if (test) {
//				return AmericanRestaurantMockCook;
//			}
//			return AmericanRestaurantCookRole;
//		}
//
//		public AmericanRestaurantCashier getCashier(boolean test) {
//			if (test) {
//				return AmericanRestaurantMockCashier;
//			}
//			return AmericanRestaurantCashierRole;
//		}
//
		public boolean isOpen() {
			if (americanHost.getPerson() != null && americanHost.Waiters.size() != 0 && americanHost.getPerson() != null && americanCashier.person != null && !userClosed)
				return true;
			else 
				return false;
		}
//		
//		public void setBuildingPanel (BuildingPanel rp) {
//			restPanel = rp;
//		}
//
//		public void removeWaiter(AmericanRestaurantWaiterRole AmericanRestaurantWaiterRole) {
//			waiters.remove(AmericanRestaurantWaiterRole);
//			restPanel.removeGui(AmericanRestaurantWaiterRole.getGui());
//		}
//		
//		public void removeCustomer(AmericanRestaurantCustomerRole customerRole) {
//			customers.remove(customerRole);
//			restPanel.removeGui(customerRole.getGui());
//		}
//		
//		public void closeBuilding(){
//			userClosed = true;
//			AmericanRestaurantCookRole.msgLeaveRole();
//			for (AmericanRestaurantWaiterRole w1: waiters) {
//				w1.msgLeaveRole();
//				restPanel.removeGui(w1.getGui());
//			}
//			AmericanRestaurantCookRole.msgLeaveRole();
//			restPanel.removeGui(cookGui);
//			
//			AmericanRestaurantCashierRole.msgLeaveRole();
//		}

		public void setClosestStop(Point point) {
			closestStop = point;
		}
		
		public Point getClosestStop() {
			return closestStop;
		}
}
