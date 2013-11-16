package restaurant;

import java.util.ArrayList;
import java.util.List;

public class RevolvingStand 
{
	List<Order> AllOrders = new ArrayList<Order>();
	
	public void newOrder(Order o)
	{
		AllOrders.add(o);
	}
	
	public Order takeOrder()
	{
		Order o = AllOrders.get(0);
		AllOrders.remove(0);
		return o;
	}
	
	public boolean isStandEmpty()
	{
		return AllOrders.isEmpty();
	}

}
