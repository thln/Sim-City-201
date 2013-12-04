package chineseRestaurant.test.mock;

import chineseRestaurant.Order;
import chineseRestaurant.WaiterRole;
import chineseRestaurant.interfaces.Cook;
import market.Market;
import testing.EventLog;
import testing.LoggedEvent;
import testing.Mock;

	/**
	 * This is a mock cook to serve for testing purposes. 
	 * @author Tam Henry Le Nguyen
	 *
	 */

public class MockCook extends Mock implements Cook {
	
	public EventLog log = new EventLog();
	
	public MockCook(String name)
	{
		super(name);
	}
	
	public void msgHeresAnOrder(int table, String choice, WaiterRole waiterRole)
	{
		
	}

	public void msgOrderDone(Order order)
	{
		
	}

	public void msgAtDestination()
	{
		
	}

	@Override
	public void msgCantFulfill(String choice, int amount, int orderedAmount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgOrderFulfillment(String choice, int amount, int orderedAmount) {
		log.add(new LoggedEvent("Recieved msgOrderFulfillment"));
	}
}
