package restaurant;

public class Restaurant {

	HostRole hostRole;
	
	public void msgIWantFood(RestaurantCustomerRole cust, int xHome, int yHome) {
		hostRole.msgIWantFood(cust, xHome, yHome);
	}
	
}
