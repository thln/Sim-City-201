package restaurant.interfaces;

import restaurant.Cashier;
import restaurant.Cook;
import restaurant.RestaurantCustomer;
import restaurant.Host;

public interface Waiter {

	public String getName();
	
	
	/**
	 * Messages
	 */
	public void msgPleaseSeatCustomer(int tableNumber, Customer customer, int xHome, int yHome);

	public void msgAtDestination();

	public void msgIsInLobby();

	public void msgReadyToOrder(Customer customer);

	public void msgHeresMyOrder(Customer customer, String choice);
	
	public void msgOrderIsNotAvailable(String choice, int tableNumber);

	public void msgOrderIsReady(int table, String choice);
	
	public void msgIWantMyCheck(Customer cust);

	public void msgLeavingTable(Customer cust);
	
	public void msgWantToGoOnBreak();

	public void msgPermissionToBreak(boolean reply);
	
	public void msgGoOffBreak();
	
	public void msgHereIsCheck(int tableNumber, double checkAmount);
	
	
	//Utilities
	//public void setGui(WaiterGui gui);

	//public WaiterGui getGui();

	public void setHost(Host host);

	public void setCook(Cook cook);
	
	public void setCashier(Cashier cashier);
	
	public boolean isOnBreak();
}
