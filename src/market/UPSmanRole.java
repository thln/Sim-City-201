package market;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

import chineseRestaurant.ChineseRestaurant;
import market.interfaces.UPSman;
import person.Person;
import person.Role;
import person.Worker;
import testing.EventLog;
import testing.LoggedEvent;
import application.gui.animation.agentGui.*;

public class UPSmanRole extends Role implements UPSman {

	//Data
	public List<MarketOrder> orders = Collections.synchronizedList(new ArrayList<MarketOrder>());
	private Semaphore atDestination = new Semaphore(0, true);
	Market market;
	private MarketUPSmanGui UPSmanGui;

	public EventLog log = new EventLog();
	
	public UPSmanRole (Person p, String pName, String rName, Market market) {
		super(p, pName, rName);
		this.market = market;
	}

	public UPSmanRole(String roleName, Market market) {
		super(roleName);
		this.market = market;
	}

	//Messages
	public void msgDeliverOrder(MarketOrder o) {
		print("Recieved an order to deliver");
		log.add(new LoggedEvent("Recieved msgDeliverOrder"));
		orders.add(o);
		stateChanged();
	}
	
	public void msgAtDestination() {
		atDestination.release();
	}

	//Scheduler
	public boolean pickAndExecuteAnAction() {
		if (!orders.isEmpty()) {
			for (MarketOrder o: orders) {
				deliverOrder(o);
				return true;
			}
		}
		
		if (leaveRole && orders.isEmpty()) {
			market.goingOffWork(person);
			leaveRole = false;
			return true;
		}
		
		return false;
	}

	//Actions
	public void deliverOrder(MarketOrder o) {
		print("Delivered order ot restaurant");
		o.restaurant.getCook(test).msgOrderFulfillment(o.item, o.itemAmountFulfilled, o.itemAmountOrdered);
		market.getSalesPerson(test).msgOrderDelivered(o);
		orders.remove(o);
	}
	
	public void setGui(MarketUPSmanGui gui) {
		UPSmanGui = gui;
	}
	
	public MarketUPSmanGui getGui() {
		return UPSmanGui;
	}
}
