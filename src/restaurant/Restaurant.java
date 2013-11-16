package restaurant;

public class Restaurant {

	Host host;
	
	public void msgIWantFood(RestaurantCustomer cust, int xHome, int yHome) {
		host.msgIWantFood(cust, xHome, yHome);
	}
	
}
