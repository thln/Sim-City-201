package italianRestaurant.interfaces;


/**
 * A sample Waiter interface built to unit test a CashierAgent.
 *
 * @author Carmen Tan
 *
 */
public interface ItalianWaiter {

	
	public abstract void msgHereIsCheck(ItalianCustomer c, Double total);
	public abstract void msgOrderDone(String choice, int table);
	public abstract void msgFoodOut(String choice, int table);
	
	
}