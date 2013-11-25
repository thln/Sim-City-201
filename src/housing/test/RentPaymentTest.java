package housing.test;

import application.Phonebook;
import application.TimeManager;
import application.TimeManager.Day;
import person.Crook;
import person.Wealthy;
import person.Worker;
import junit.framework.TestCase;

public class RentPaymentTest extends TestCase
{
	Worker worker;
	Crook crook;
	Wealthy landlord;

	public void SetUp() throws Exception
	{
		super.setUp();
		worker = new Worker("testworker", 100, "waiter", "restaurant", 800, 1200, 1700);
		crook = new Crook("testcrook", 100);
		landlord = new Wealthy("testlandlord", 500);
	}
	
	//Test that people deposit money
	
	public void testNormativeScenarioOne()
	{
		try 
		{
			SetUp();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	  	  /******************************/
		 /***Testing preconditions******/
		/******************************/
		
		assertEquals("There should be no money in the mailbox. There is", 0, Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.getCurrentPaymentAmount());
		assertEquals("The cost of renting an apartment should be $50. It isn't.", 50, Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.getApartmentRentCost());
		assertEquals("Test Worker should have enough money to pay rent. It doesn't.", 100.0, worker.money);
		assertEquals("Test Crook should have enough money to pay rent. It doesn't.", 100.0, crook.money);
		assertEquals("Today should be Sunday. It isn't.", Day.Sunday, TimeManager.getTimeManager().getTime().day);
		assertFalse("Test Worker's checkedMailbox should return false. it doesn't.", worker.checkedMailbox);
		assertFalse("Test Crook's checkedMailbox should return false. it doesn't.", crook.checkedMailbox);

		  /**************************/
		 /*****Paying rent *********/
		/**************************/
		
		assertTrue("Worker's pickandexecute action should return true to pay rent. It doesn't.", worker.pickAndExecuteAnAction());
		
		assertEquals("There should be $50 in the mailbox. There isn't.", 50, Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.getCurrentPaymentAmount());
		assertEquals("Test Worker should have $50 less. It doesn't.", 50.0, worker.money);
		assertTrue("Test Worker's checkedMailbox should return true. it doesn't.", worker.checkedMailbox);
		assertEquals("Today should be Sunday. It isn't.", Day.Sunday, TimeManager.getTimeManager().getTime().day);
		assertEquals("Test Crook should have enough money to pay rent. It doesn't.", 100.0, crook.money);
		assertFalse("Test Crook's checkedMailbox should return false. it doesn't.", crook.checkedMailbox);
	}
	
	//Test that two people can deposit money
	public void testNormativeScenarioTwo()
	{
		try 
		{
			SetUp();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	  	  /******************************/
		 /***Testing preconditions******/
		/******************************/
		
		assertEquals("There should be $50 money in the mailbox. There isn't", 50, Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.getCurrentPaymentAmount());
		assertEquals("The cost of renting an apartment should be $50. It isn't.", 50, Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.getApartmentRentCost());
		assertEquals("Test Worker should have enough money to pay rent. It doesn't.", 100.0, worker.money);
		assertEquals("Test Crook should have enough money to pay rent. It doesn't.", 100.0, crook.money);
		assertEquals("Today should be Sunday. It isn't.", Day.Sunday, TimeManager.getTimeManager().getTime().day);
		assertFalse("Test Worker's checkedMailbox should return false. it doesn't.", worker.checkedMailbox);
		assertFalse("Test Crook's checkedMailbox should return false. it doesn't.", crook.checkedMailbox);

		  /**************************/
		 /*****Paying rent *********/
		/**************************/
		
		assertTrue("Worker's pickandexecute action should return true to pay rent. It doesn't.", worker.pickAndExecuteAnAction());
		
		assertEquals("There should be $100 in the mailbox. There isn't.", 100, Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.getCurrentPaymentAmount());
		assertEquals("Test Worker should have $50 less. It doesn't.", 50.0, worker.money);
		assertTrue("Test Worker's checkedMailbox should return true. it doesn't.", worker.checkedMailbox);
		assertEquals("Today should be Sunday. It isn't.", Day.Sunday, TimeManager.getTimeManager().getTime().day);
		assertEquals("Test Crook should have enough money to pay rent. It doesn't.", 100.0, crook.money);
		assertFalse("Test Crook's checkedMailbox should return false. it doesn't.", crook.checkedMailbox);
		
		  /**************************/
		 /*****Paying more rent ****/
		/**************************/
		
		assertTrue("Crook's pickandexecute action should return true to pay rent. It doesn't.", crook.pickAndExecuteAnAction());
		
		assertEquals("There should be $150 in the mailbox. There isn't.", 150, Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.getCurrentPaymentAmount());
		assertEquals("Test Worker should have $50 less. It doesn't.", 50.0, worker.money);
		assertTrue("Test Worker's checkedMailbox should return true. it doesn't.", worker.checkedMailbox);
		assertEquals("Today should be Sunday. It isn't.", Day.Sunday, TimeManager.getTimeManager().getTime().day);
		assertEquals("Test Crook should have $50 less. It doesn't.", 50.0, crook.money);
		assertTrue("Test Crook's checkedMailbox should return true. it doesn't.", crook.checkedMailbox);
	}
	
	//Test that wealthy person gets money
	public void testNormativeScenarioThree()
	{
		try 
		{
			SetUp();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		

	  	  /******************************/
		 /***Testing preconditions******/
		/******************************/
		
		assertEquals("There should be $150 money in the mailbox. There isn't", 150, Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.getCurrentPaymentAmount());
		assertEquals("The cost of renting an apartment should be $50. It isn't.", 50, Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.getApartmentRentCost());
		assertEquals("Test Worker should have enough money to pay rent. It doesn't.", 100.0, worker.money);
		assertEquals("Test Crook should have enough money to pay rent. It doesn't.", 100.0, crook.money);
		assertEquals("Today should be Sunday. It isn't.", Day.Sunday, TimeManager.getTimeManager().getTime().day);
		assertFalse("Test Worker's checkedMailbox should return false. it doesn't.", worker.checkedMailbox);
		assertFalse("Test Crook's checkedMailbox should return false. it doesn't.", crook.checkedMailbox);

		  /**************************/
		 /*****Paying rent *********/
		/**************************/
		
		assertTrue("Worker's pickandexecute action should return true to pay rent. It doesn't.", worker.pickAndExecuteAnAction());
		
		assertEquals("There should be $200 in the mailbox. There isn't.", 200, Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.getCurrentPaymentAmount());
		assertEquals("Test Worker should have $50 less. It doesn't.", 50.0, worker.money);
		assertTrue("Test Worker's checkedMailbox should return true. it doesn't.", worker.checkedMailbox);
		assertEquals("Today should be Sunday. It isn't.", Day.Sunday, TimeManager.getTimeManager().getTime().day);
		assertEquals("Test Crook should have enough money to pay rent. It doesn't.", 100.0, crook.money);
		assertFalse("Test Crook's checkedMailbox should return false. it doesn't.", crook.checkedMailbox);
		
		  /**************************/
		 /*****Paying more rent ****/
		/**************************/
		
		assertTrue("Crook's pickandexecute action should return true to pay rent. It doesn't.", crook.pickAndExecuteAnAction());
		
		assertEquals("There should be $250 in the mailbox. There isn't.", 250, Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.getCurrentPaymentAmount());
		assertEquals("Test Worker should have $50 less. It doesn't.", 50.0, worker.money);
		assertTrue("Test Worker's checkedMailbox should return true. it doesn't.", worker.checkedMailbox);
		assertEquals("Today should be Sunday. It isn't.", Day.Sunday, TimeManager.getTimeManager().getTime().day);
		assertEquals("Test Crook should have $50 less. It doesn't.", 50.0, crook.money);
		assertTrue("Test Crook's checkedMailbox should return true. it doesn't.", crook.checkedMailbox);
		

		try 
		{
			Thread.sleep(720);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}

		//How to make Monday ?????????
		assertEquals("Today should be Monday. It isn't.", Day.Monday, TimeManager.getTimeManager().getTime().day);
		assertEquals("There should be $250 in the mailbox. There isn't.", 250, Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.getCurrentPaymentAmount());
		assertFalse("Landlord's checkedMailbox should return false. it doesn't.", landlord.checkedMailbox);
		assertEquals("Landlord should have $500. It doesn't.", 500.0, landlord.money);

		assertTrue("Landlord's pickandexecute action should return true to pay rent. It doesn't.", landlord.pickAndExecuteAnAction());

		assertEquals("There should be 0 in the mailbox. There isn't.", 0, Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.getCurrentPaymentAmount());
		assertTrue("Landlord's checkedMailbox should return true. it doesn't.", landlord.checkedMailbox);
		assertEquals("Landlord should have $500. It doesn't.", 550.0, landlord.money);
	}
}
