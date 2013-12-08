package americanRestaurant.interfaces;

import americanRestaurant.AmericanRestaurantCookRole.Order;

public interface AmericanRestaurantWaiter {
	
	void msgAtCook(AmericanRestaurantCustomer c1);

	void msgDoneAndPaying(AmericanRestaurantCustomer c1);

	void msgHereIsCheck(AmericanRestaurantCustomer c1, int check1);

	void msgReadyForCheck(AmericanRestaurantCustomer c1);

	void msgOutOfFood(Order order1);

	void msgOrderIsReady(Table tab1, String choice);

	void msgHereIsMyChoice(AmericanRestaurantCustomer c1, String choice1);

	void msgReadyToOrder(AmericanRestaurantCustomer c);

	void msgSeatAtTable(AmericanRestaurantCustomer c1, Table t1);

	void msgFoodHere(AmericanRestaurantCustomer c1);

	void msgAtDoor();

	void msgAtTable();

	void msgCanBreak();

	void msgNoBreak();

	Object getGui();
}
