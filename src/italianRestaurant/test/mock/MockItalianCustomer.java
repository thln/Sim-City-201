package italianRestaurant.test.mock;

import italianRestaurant.interfaces.*;

/**
 * A sample MockCustomer built to unit test a CashierAgent.
 *
 * @author Carmen Tan
 *
 */
public class MockItalianCustomer extends Mock implements ItalianCustomer {
	
	private String name;
	
	public void msgSitAtTable(int table) {
		
	}
	
	public void msgdoneOrdering() {
		
	}
	
	public void msgdoneWaitingForFood() {
		
	}
	
	public void msgFoodOutReorder(String OutChoice) {
		
	}
	/**
	 * Reference to the Cashier under test that can be set by the unit test.
	 */
	public ItalianCashier cashier;
	 

	public EventLog log = new EventLog();
	
	public MockItalianCustomer(String name) {
		super(name);
		this.name = name;

	}

	@Override
	public void msgHereIsBill(Double total) {
		log.add(new LoggedEvent("Received HereIsYourTotal from cashier. Total = "+ total));

		if(this.name.toLowerCase().contains("thief")){
			//test the non-normative scenario where the customer has no money if their name contains the string "theif"
	//		cashier.IAmShort(this, 0);

		}else if (this.name.toLowerCase().contains("rich")){
			//test the non-normative scenario where the customer overpays if their name contains the string "rich"
	//		cashier.HereIsMyPayment(this, Math.ceil(total));

		}else{
			//test the normative scenario
			cashier.msgHereIsMoney(this, total);
		}
	}

	@Override
	public void msgChangeAndReceipt(String receipt, Double change) {
		log.add(new LoggedEvent("Received HereIsYourChange from cashier. Change = "+ change));
	}

	@Override
	public void YouOweUs(Double remaining_cost) {
		log.add(new LoggedEvent("Received YouOweUs from cashier. Debt = "+ remaining_cost));
	}
	
	public String toString() {
		return "customer " + name;
	}
}
