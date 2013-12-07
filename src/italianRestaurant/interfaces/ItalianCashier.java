package italianRestaurant.interfaces;

/**
 * A sample Cashier interface built to unit test a CashierAgent.
 *
 *
 */
public interface ItalianCashier {
	
	 //Sent by the waiter prompting the cashier to compute the bill
	
	public abstract void msgComputeBill(ItalianWaiter w, ItalianCustomer c, String choice);
	
	public abstract void msgHereIsMoney(ItalianCustomer c, Double payment);
	
	public abstract void msgRestockingBill(ItalianMarket market, String foodname, Double billtotal);

}