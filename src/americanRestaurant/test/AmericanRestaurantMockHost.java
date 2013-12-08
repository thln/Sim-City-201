package americanRestaurant.test;

import americanRestaurant.AmericanRestaurantWaiterRole;
import americanRestaurant.interfaces.AmericanRestaurantHost;

public class AmericanRestaurantMockHost extends Mock implements AmericanRestaurantHost {

	public AmericanRestaurantMockHost(String name) {
		super(name);
	}

	@Override
	public void msgIWantToEat(CustomerAgent C1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgWantToBreak(AmericanRestaurantWaiterRole w) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgOffBreak(AmericanRestaurantWaiterRole w1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgTableIsClear(Table t1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgWillWait(CustomerAgent c1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgWontWait(CustomerAgent c1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgWatchThisCust(Customer c1) {
		 log.add(new LoggedEvent("Watch this customer: "+ c1.getName()));
	}

	@Override
	public void msgDebtPaid(Customer c1) {
		// TODO Auto-generated method stub
		
	}

}
