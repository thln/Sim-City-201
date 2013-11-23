package restaurant.test.mock;

import restaurant.interfaces.Cook;
import testing.Mock;

	/**
	 * This is a mock cook to serve for testing purposes. 
	 * @author Tam Henry Le Nguyen
	 *
	 */

public class MockCook extends Mock implements Cook
{
	public MockCook(String name)
	{
		super(name);
	}
}
