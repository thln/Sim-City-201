package restaurant.test;

import application.Phonebook;
import person.Role.RoleState;
import person.Worker;
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
	Worker person1;
	Worker person2;
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
        person1 = new Worker("testAlternativeWaiter", 200, "alternative waiter", "restaurant", 800, 1200, 1500);
        person2 =  new Worker("testCook", 200, "cook", "restaurant", 800, 1200, 1500);
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
		try 
		{
			setUp();
		}	
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		assertEquals("Person 1's job Title should be Alternative Waiter. It isn't.", person1.getJob().title, "alternative waiter");
		assertEquals("Person 2's job Title should be Cook. It isn't.", person2.getJob().title, "cook");
		assertEquals("Person 1's job location should be restaurant. It isn't.", person1.getJob().jobPlace, "restaurant");
		assertEquals("Person 2's job location should be restaurant. It isn't.", person2.getJob().jobPlace, "restaurant");
		
		//Should not be in worker role yet
		assertEquals("Person 1's worker Role should not be active. It is.", person1.getWorkerRole(), null);
		assertEquals("Person 2's worker Role should not be active. It is.", person2.getWorkerRole(), null);

		assertTrue("The waiter on the host's list should be the same as person 1. It isn't.",Phonebook.getPhonebook().getRestaurant().hostRole.waiters.isEmpty());
		assertEquals("The cook in the restaurant should be the same as person 2. It isn't.",Phonebook.getPhonebook().getRestaurant().cookRole.getPerson(), null);
		
		person1.prepareForWork();
		person2.prepareForWork();
		
		assertEquals("Person 1's worker Role should be active. It isn't.", person1.getWorkerRole().getRoleState(), RoleState.active);
		assertEquals("Person 2's worker Role should be active. It isn't.", person2.getWorkerRole().getRoleState(), RoleState.active);
		
		assertEquals("The waiter on the host's list should be the same as person 1. It isn't.",Phonebook.getPhonebook().getRestaurant().hostRole.waiters.get(0), person1);
		assertEquals("The cook in the restaurant should be the same as person 2. It isn't.",Phonebook.getPhonebook().getRestaurant().cookRole.getPerson(), person2);

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
