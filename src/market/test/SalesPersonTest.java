package market.test;

import junit.framework.TestCase;
import market.Market;
import market.SalesPersonRole;
import market.test.mock.MockMarketCustomer;
import market.test.mock.MockMarketRunner;
import person.Worker;

public class SalesPersonTest extends TestCase {

	Worker worker;
	SalesPersonRole salesPerson;
	Market market;
	MockMarketCustomer marketCustomer;
	MockMarketRunner marketRunner;
	
	public void setUp() throws Exception {
		super.setUp();
		market = new Market("Market");
		salesPerson = new SalesPersonRole(worker, "SalesPerson", "MarketSalesPerson", market);
		marketCustomer = new MockMarketCustomer("Mock Customer");
		marketRunner = new MockMarketRunner("Mock Market Runner");
	}


	public void testOneMarketCustomerPaymentNormativeScenerio() {
		try {
			setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Checking pre-conditions
		assertEquals("Sales Person shouldn't have any orders in it", salesPerson.orders.size(), 0);
		
		assertEquals("Sales Person should have an empty event log before the SalesPerson's msgIWantProducts is called. Instead, the SalesPerson's event log reads: "
				+ salesPerson.log.toString(), 0, salesPerson.log.size());
		
		//Step 1
		salesPerson.msgIWantProducts(marketCustomer, "Car", 1);
		
		//Checking post conditions for step 1
		assertEquals("Sales Person should have 1 order in it", salesPerson.orders.size(), 1);
		
	}
	
	public void testTwoBusinessPaymentNormativeScenerio() {
		try {
			setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testThreeMarketCustomerAndBusinessPaymentScenerio() {
		try {
			setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testFourBusinessAndMarketCustomerPaymentScenerio() {
		try {
			setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
