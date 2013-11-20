package person;

import bank.BankCustomerRole;
import application.Phonebook;
import application.TimeManager.Time;

public class Crook extends Person {
	//These people become dishonest customers in the restaurant
	
	String name;

    public Crook(String name,  int money) {
		super(name);
		this.money = money;
		this.name = name;
    }
    
    private void robBank(Role r) {
		//GUI call to go to business
//		try {
//			atDestination.acquire();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//
//		}
		//Once semaphore is released from GUI

		setRoleActive(r);
		BankCustomerRole cust1 = (BankCustomerRole) r;
		cust1.setDesire("robBank");
		Phonebook.getPhonebook().getBank().bankGuardRole.msgRobbingBank(cust1);
		stateChanged();
	}
}

