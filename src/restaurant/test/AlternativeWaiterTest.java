package restaurant.test;

import bank.BankGuardRole;
import application.Phonebook;
import person.Role.RoleState;
import person.Worker;
import restaurant.AltWaiterRole;
import restaurant.CookRole;
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
	Worker person3;
	AltWaiterRole altWaiter;
	CookRole cook;
    MockCustomer customer;
    //MockCook cook;

    /**
     * This method is run before each test. You can use it to instantiate the class variables
     * for your agent and mocks, etc.
     */
	public void setUp() throws Exception
	{
		super.setUp();
		//Phonebook();
        customer = new MockCustomer("mockcustomer"); 
        person1 = new Worker("testAlternativeWaiter", 200, "alternative waiter", "restaurant", 800, 1200, 1500);
        person2 = new Worker("testCook", 200, "cook", "restaurant", 800, 1200, 1500);
		altWaiter = new AltWaiterRole ( person1, person1.getName(), "Alternative Waiter");
		cook = new CookRole(person2, person2.getName(), "cook", Phonebook.getPhonebook().getRestaurant());
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
		//person3.prepareForWork();
		
		assertEquals("Person 1's job Title should be Alternative Waiter. It isn't.", person1.getJob().title, "alternative waiter");
		assertEquals("Person 2's job Title should be Cook. It isn't.", person2.getJob().title, "cook");
		assertEquals("Person 1's job location should be restaurant. It isn't.", person1.getJob().jobPlace, "restaurant");
		assertEquals("Person 2's job location should be restaurant. It isn't.", person2.getJob().jobPlace, "restaurant");
		
		//Should not be in worker role yet
		assertEquals("Person 1's worker Role should not be active. It is.", person1.getWorkerRole(), null);
		assertEquals("Person 2's worker Role should not be active. It is.", person2.getWorkerRole(), null);

		assertTrue("There should be no waiters in the host's list of waiters. There are.",Phonebook.getPhonebook().getRestaurant().hostRole.waiters.isEmpty());
		assertEquals("There should be no cook in the restaurant. There is.",Phonebook.getPhonebook().getRestaurant().cookRole.getPerson(), null);
		
		person1.prepareForWork();
		person2.prepareForWork();
		
		assertEquals("Person 1's worker Role should be active. It isn't.", person1.getWorkerRole().getRoleState(), RoleState.active);
		assertEquals("Person 2's worker Role should be active. It isn't.", person2.getWorkerRole().getRoleState(), RoleState.active);
		
		assertEquals("The waiter on the host's list should be the same as person 1. It isn't.",Phonebook.getPhonebook().getRestaurant().hostRole.waiters.get(0).waiterRole.getPerson(), person1);
		assertEquals("The cook in the restaurant should be the same as person 2. It isn't.",Phonebook.getPhonebook().getRestaurant().cookRole.getPerson(), person2);
		assertEquals("The waiter on the host's list should an alternative waiter. It isn't.",Phonebook.getPhonebook().getRestaurant().hostRole.waiters.get(0).waiterRole.getRoleName(), "Alternative Waiter");

		
		assertTrue("The waiter's list of orders should be empty. It isn't.", Phonebook.getPhonebook().getRestaurant().hostRole.waiters.get(0).waiterRole.readyOrders.isEmpty());
		//assertTrue("The waiter's list of customers should be empty. It isn't.", Phonebook.getPhonebook().getRestaurant().hostRole.waiters.get(0).waiterRole.myCustomers.isEmpty());
		assertTrue("The cook's list of orders should be empty. It isn't.", Phonebook.getPhonebook().getRestaurant().cookRole.myOrders.isEmpty());
	
		//altWaiter = Phonebook.getPhonebook().getRestaurant().hostRole.waiters.get(0).waiterRole;
		
		Phonebook.getPhonebook().getRestaurant().hostRole.waiters.get(0).waiterRole.msgHeresMyOrder(customer, "Steak");
		
		assertEquals("The waiter's list of orders should be 1. It isn't.", Phonebook.getPhonebook().getRestaurant().hostRole.waiters.get(0).waiterRole.readyOrders.size(), 1);
		assertEquals("The first order on the waiter's list should be steak. It isn't.", Phonebook.getPhonebook().getRestaurant().hostRole.waiters.get(0).waiterRole.readyOrders.get(1).choice, "Steak");
		assertTrue("The cook's list of orders should be empty. It isn't.", Phonebook.getPhonebook().getRestaurant().cookRole.myOrders.isEmpty());

		
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
