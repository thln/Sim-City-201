package americanRestaurant.interfaces;

import americanRestaurant.AmericanRestaurantWaiterRole;

public interface AmericanRestaurantHost {

	void msgIWantToEat (CustomerAgent C1);

	public void msgWantToBreak(AmericanRestaurantWaiterRole w);

	public void msgOffBreak(AmericanRestaurantWaiterRole w1);
	void msgTableIsClear (Table t1);

	void msgWillWait (CustomerAgent c1);
	void msgWontWait (CustomerAgent c1) ;
	void msgWatchThisCust (AmericanRestaurantCustomer c1) ;

	void msgDebtPaid (AmericanRestaurantCustomer c1) ;
}
