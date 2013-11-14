package market;

public class Market {

	double money;
	
	SalesPerson salesPersonRole;
	
	public void msgIWantProducts(MarketCustomer customer, String item, int numWanted) {
		salesPersonRole.msgIWantProducts(customer, item, numWanted);
	}
}
