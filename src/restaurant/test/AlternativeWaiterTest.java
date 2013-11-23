package restaurant.test;

import restaurant.test.mock.MockCustomer;
import junit.framework.TestCase;
/**
 * Alternative Waiter Test
 * @author Tam Henry Le Nguyen
 *
 *The point of this test is to see if the waiter can use the revolving stand or not.
 */

public class AlternativeWaiterTest extends TestCase
{
    MockCustomer customer;
    //MockCook cook;

    /**
     * This method is run before each test. You can use it to instantiate the class variables
     * for your agent and mocks, etc.
     */
	public void setUp() throws Exception
	{
		super.setUp();
        customer = new MockCustomer("mockcustomer");                
        //cook = new MockCook("mockcook");
	}
	
    /**
     * This tests the alternative waiter under the following terms: 
     * if the waiter has access to the revolving stand
     * if the chef has access to the revolving stand
     * what happens if the chef checks an empty revolving stand
     * if the waiter can pull an order on the revolving stand
     * if the chef can take the order from the revolving stand
     * if the revolving stand is empty again
     */
	public void testNormativeScenarioOne()
	{
		
	}
	
	/**
	 * This tests the alternative waiter under the following terms:
	 * if there are two orders placed on the revolving stand the chef will take
	 * the orders in the correct sort and remove them accordingly 
	 */
	public void testNormativeScenarioTwo()
	{
		
	}
}
