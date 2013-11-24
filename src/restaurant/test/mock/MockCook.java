package restaurant.test.mock;

import market.Market;
import restaurant.Order;
import restaurant.WaiterRole;
import restaurant.interfaces.Cook;
import testing.Mock;

	/**
	 * This is a mock cook to serve for testing purposes. 
	 * @author Tam Henry Le Nguyen
	 *
	 */

public class MockCook extends Mock implements Cook
{
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
		// TODO Auto-generated method stub
		
	}
}
