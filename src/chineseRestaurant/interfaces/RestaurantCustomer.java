package chineseRestaurant.interfaces;

import chineseRestaurant.Menu;
import chineseRestaurant.interfaces.Host;
import chineseRestaurant.interfaces.Waiter;

/**
 * A sample Customer interface built to unit test a CashierAgent.
 *
 * @author Kristi Hupka
 *
 */
public interface RestaurantCustomer {

	public abstract void setHost(Host host);

	public abstract String getCustomerName();
	
	public abstract String getName();



	/**
	 * Messages
	 */
	public abstract void gotHungry(int xHome, int yHome);

	public abstract void msgTablesAreFull();
	
	public abstract void msgPleaseFollowMe(int tableNumber, Menu menu, Waiter waiter);

	public abstract void msgAnimationFinishedGoToSeat();

	public abstract void msgWhatWouldYouLike();

	public abstract void msgPleaseReorder(Menu newMenu);

	public abstract void msgHeresYourOrder(String choice);

	public abstract void msgHeresYourCheck(double check);
	
	public abstract void msgAnimationFinishedGoToCashier();

	public abstract void msgHeresYourChange(double change);
	
	public abstract void msgGoToJail();

	public abstract void msgAnimationFinishedLeaveRestaurant();

	public abstract void msgComeIn();
}