package restaurant.interfaces;

import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

import market.Market;
import person.Worker;
import restaurant.Order;
import restaurant.WaiterRole;
import restaurant.CookRole.Stock;
import restaurant.CookRole.myMarket;

public interface Cook 
{
	public abstract void msgHeresAnOrder(int table, String choice, WaiterRole waiterRole);

	public abstract void msgOrderDone(Order order);

	public abstract void msgCantFulfill(String choice, int amount, int orderedAmount, Market market);

	public abstract void msgOrderFulfillment(String choice, int amount, int orderedAmount, Market market);

	public abstract void msgAtDestination();
}
