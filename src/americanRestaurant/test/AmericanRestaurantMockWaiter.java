package americanRestaurant.test;

import americanRestaurant.AmericanRestaurantCookRole.Order;
import americanRestaurant.interfaces.AmericanRestaurantWaiter;

public class AmericanRestaurantMockWaiter extends Mock implements AmericanRestaurantWaiter{
	
	public String name;

	public AmericanRestaurantMockWaiter(String name1) {	
		super(name1);
	}

	@Override
	public void msgAtCook(Customer c1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgDoneAndPaying(Customer c1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgHereIsCheck(Customer c1, int check1) {
		log.add(new LoggedEvent("Received HereIsCheck from americanRestaurantCashier. Check = "+ 20));
	}

	@Override
	public void msgReadyForCheck(Customer c1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgOutOfFood(Order order1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgOrderIsReady(Table tab1, String choice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgHereIsMyChoice(Customer c1, String choice1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgReadyToOrder(Customer c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgSeatAtTable(Customer c1, Table t1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgFoodHere(Customer c1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgAtDoor() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgAtTable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgCanBreak() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgNoBreak() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getGui() {
		// TODO Auto-generated method stub
		return null;
	}

}
