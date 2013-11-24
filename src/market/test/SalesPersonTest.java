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
