package italianRestaurant;

import italianRestaurant.interfaces.*;

public class ItalianRestaurantOrder {

	/*Order class stores the different customer orders
	 * the waiter gives to the cook, and its state
	 * of cooking on the cook's "grill".
	 */
	enum OrderState {pending, cooking, done, finished};
	ItalianWaiter w;
	ItalianFood food;
	String choice;
	int table;
	OrderState s;
	
	ItalianRestaurantOrder(ItalianWaiter waiter, String f, int t)  {
		w = waiter;
		choice = f;
		table = t;
	}
		
	ItalianRestaurantOrder(ItalianWaiter waiter, ItalianFood f, int t) {
		w = waiter;
		food = f;
		table = t;
		s = OrderState.pending;
	}
		
	public String toString() {
		return "Order " + food;
	}
}
