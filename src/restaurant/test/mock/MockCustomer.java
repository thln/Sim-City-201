package restaurant.test.mock;


import restaurant.HostRole;
import restaurant.Menu;
import restaurant.WaiterRole;
import restaurant.interfaces.Cashier;
import restaurant.interfaces.Customer;

/**
 * A sample MockCustomer built to unit test a CashierAgent.
 *
 * @author Kristi Hupka
 *
 */
public class MockCustomer extends Mock implements Customer {

	public Cashier cashier;

	/**
	 * Reference to the Cashier under test that can be set by the unit test.
	 */
	//public Cashier cashier;

	public MockCustomer(String name) {
		super(name);
	}

//	@Override
//	public void setHost(Host host) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public String getCustomerName() {
//		// TODO Auto-generated method stub
//		return "MockCustomer";
//	}
//
//	@Override
//	public void gotHungry(int xHome, int yHome) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void msgTablesAreFull() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void msgPleaseFollowMe(int tableNumber, Menu menu, Waiter waiter) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void msgAnimationFinishedGoToSeat() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void msgWhatWouldYouLike() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void msgPleaseReorder(Menu newMenu) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void msgHeresYourOrder(String choice) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void msgHeresYourCheck(double check) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void msgAnimationFinishedGoToCashier() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void msgHeresYourChange(double change) {
//		// TODO Auto-generated method stub
//		log.add(new LoggedEvent("Received msgHeresYourChange from cashier. Change: " + change));	
//	}
//
//	@Override
//	public void msgGoToJail() {
//		// TODO Auto-generated method stub
//		log.add(new LoggedEvent("Received msgGoToJail from cashier."));	
//	}
//
//	@Override
//	public void msgAnimationFinishedLeaveRestaurant() {
//		// TODO Auto-generated method stub
//		
//	}
}
