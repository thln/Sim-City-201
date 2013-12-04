package chineseRestaurant.interfaces;

import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

import chineseRestaurant.Order;
import chineseRestaurant.WaiterRole;
import chineseRestaurant.CookRole.Stock;
import market.Market;
import person.Worker;

public interface Cook 
{
	public abstract void msgHeresAnOrder(int table, String choice, WaiterRole waiterRole);

	public abstract void msgOrderDone(Order order);

	public abstract void msgCantFulfill(String choice, int amount, int orderedAmount);

	public abstract void msgOrderFulfillment(String choice, int amount, int orderedAmount);

	public abstract void msgAtDestination();
}
