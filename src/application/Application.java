package application;

import housing.Housing;

import javax.swing.*;

import market.*;
import bank.*;
import person.*;
import restaurant.Restaurant;

import java.util.*;

import application.gui.appView.listPanel.ListPanel.Profile;


public class Application extends JPanel {
	
	private ArrayList<Person> population = new ArrayList<Person>();
	private static List<Housing> allHousing = Collections.synchronizedList(new ArrayList<Housing>());


	public Bank bank;
	public Market market;
	public Restaurant restaurant;

	//public static Phonebook phonebook = new Phonebook(bank, market, restaurant, allHousing);

	
	public Application() {

		
		Phonebook.getPhonebook().setHousingList(allHousing);
		
		//String name, int money, String jobTitle, String jobPlace, int startT, int lunchT, int endT
		
		/*
		Worker m = new Worker("Matthew", 300, "maintenance", "", 800, 1400, 1700);
		//allHousing.add(new Housing(m, allHousing.size(), "Mansion"));
		//m.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(m, allHousing.size(), "Apartment"));
		m.setHome(allHousing.get(allHousing.size() - 1));
		//allHousing.add(new Housing(m, allHousing.size(), "Park"));
		//m.setHome(allHousing.get(allHousing.size() - 1));
		population.add(m);
		m.startThread();
		m.updateTime(800);
		*/
		
		//Bank Workers
		//SHIFT 1
		Worker bank1a = new Worker("Alex", 100, "bankTeller", "bank", 0, 6, 12);
		Worker bank1b = new Worker("Ben", 100, "loanOfficer", "bank", 0, 6, 12);
		Worker bank1c = new Worker("Caitlyn", 100, "bankGuard", "bank", 0, 6, 12);
		
		//SHIFT 2
		Worker bank2a = new Worker("Andy", 100, "bankTeller", "bank", 12, 18, 24);
		Worker bank2b = new Worker("Billy", 100, "loanOfficer", "bank", 12, 18, 24);
		Worker bank2c = new Worker("Courtney", 100, "bankGuard", "bank", 12, 18, 24);

		//Market Workers
		//SHIFT 1
		Worker market1d = new Worker("Derrick", 10, "marketRunner", "market", 1000, 1400, 2000);
		Worker market1e = new Worker("Erin", 1000, "marketSales", "market", 1000, 1400, 2000);
		Worker market1f = new Worker("Fred", 100, "UPSman", "market", 1000, 1400, 2000);	
		//SHIFT 2
		Worker market2d = new Worker("Derrick", 100, "marketRunner", "market", 2000, 400, 1000);
		Worker market2e = new Worker("Erin", 200, "marketSales", "market", 2000, 400, 1000);
		Worker market2f = new Worker("Fred", 100, "UPSman", "market", 2000, 400, 1000);	
		
		//Restaurant Workers
		//SHIFT 1
		Worker rest1h = new Worker("Henry", 100, "host", "restaurant", 0, 1800, 1200);
		Worker rest1g = new Worker("Greg", 100, "cashier", "restaurant", 0, 1800, 1200);	
		Worker rest1i = new Worker("Iris", 100, "cook", "restaurant", 0, 1800, 1200);
		Worker rest1j = new Worker("Josh", 100, "waiter", "restaurant", 0, 1800, 1200);
		Worker rest1k = new Worker("Kristi", 100, "altWaiter", "restaurant", 0, 1800, 1200);
		//SHIFT 2
		Worker rest2g = new Worker("Greg", 100, "cashier", "restaurant", 2400, 600, 1200);
		Worker rest2h = new Worker("Henry", 100, "host", "restaurant", 2400, 600, 1200);
		Worker rest2i = new Worker("Iris", 100, "cook", "restaurant", 2400, 600, 1200);
		Worker rest2j = new Worker("Josh", 100, "waiter", "restaurant", 2400, 600, 1200);
		Worker rest2k = new Worker("Kristi", 100, "altWaiter", "restaurant", 2400, 600, 1200);
		
		

		//!!!!Important -- Need to initialize setters 
		//ex. waiter.setHost, waiter.setCook, waiter.setHost, 
		//Do this when person walks in for work***

		//Standard Wealthy Person
		Wealthy t = new Wealthy("Tony", 100);

		//Standard Crook
		//Crook v = new Crook("Vinny", 250);

		//Standard Deadbeat
		//Deadbeat w = new Deadbeat("Walter", 0);

		//Setting Gui for everyone
		
		//Adding housing
		allHousing.add(new Housing(bank1a, allHousing.size(), "Apartment"));
		bank1a.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(bank1b, allHousing.size(), "Apartment"));
		bank1b.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(bank1c, allHousing.size(), "Apartment"));
		bank1c.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(market1d, allHousing.size(), "Apartment"));
		market1d.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(market1e, allHousing.size(), "Apartment"));
		market1e.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(market1f, allHousing.size(), "Apartment"));
		market1f.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(rest1g, allHousing.size(), "Apartment"));
		rest1g.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(rest1h, allHousing.size(), "Apartment"));
		rest1h.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(rest1i, allHousing.size(), "Apartment"));
		rest1i.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(rest1j, allHousing.size(), "Apartment"));
		rest1j.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(rest1k, allHousing.size(), "Apartment"));
		rest1k.setHome(allHousing.get(allHousing.size() - 1));
//		allHousing.add(new Housing(l, allHousing.size(), "Apartment"));
//		l.setHome(allHousing.get(allHousing.size() - 1));
//		allHousing.add(new Housing(m, allHousing.size(), "Apartment"));
//		m.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(t, allHousing.size(), "Mansion"));
		t.setHome(allHousing.get(allHousing.size() - 1));

		allHousing.add(new Housing(bank2a, allHousing.size(), "Apartment"));
		bank2a.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(bank2b, allHousing.size(), "Apartment"));
		bank2b.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(bank2c, allHousing.size(), "Apartment"));
		bank2c.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(market2d, allHousing.size(), "Apartment"));
		market2d.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(market2e, allHousing.size(), "Apartment"));
		market2e.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(market2f, allHousing.size(), "Apartment"));
		market2f.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(rest2g, allHousing.size(), "Apartment"));
		rest2g.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(rest2h, allHousing.size(), "Apartment"));
		rest2h.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(rest2i, allHousing.size(), "Apartment"));
		rest2i.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(rest2j, allHousing.size(), "Apartment"));
		rest2j.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(rest2k, allHousing.size(), "Apartment"));
		rest2k.setHome(allHousing.get(allHousing.size() - 1));
		

		
		//Adding to Vector
		population.add(bank1a);
		population.add(bank1b);
		population.add(bank1c);
		population.add(market1d);
		population.add(market1e);
		population.add(market1f);
		population.add(rest1g);
		population.add(rest1h);
		population.add(rest1i);
		population.add(rest1j);
		population.add(rest1k);
		population.add(t);
		
		population.add(bank2a);
		population.add(bank2b);
		population.add(bank2c);
		population.add(market2d);
		population.add(market2e);
		population.add(market2f);
		population.add(rest2g);
		population.add(rest2h);
		population.add(rest2i);
		population.add(rest2j);
		population.add(rest2k);
		
		//Starting Threads
		bank1a.startThread();
		bank1b.startThread();
		bank1c.startThread();
		market1d.startThread();
		market1e.startThread();
//		market1f.startThread();
		rest1h.startThread();
		rest1g.startThread();	
		rest1i.startThread();
		rest1j.startThread();
//		rest1k.startThread();
		t.startThread();
		
//		bank2a.startThread();
//		bank2b.startThread();
//		bank2c.startThread();
//		market2d.startThread();
//		market2e.startThread();
//		market2f.startThread();
//		rest2g.startThread();
//		rest2h.startThread();
//		rest2i.startThread();
//		rest2j.startThread();
//		rest2k.startThread();
	}

	public void addPerson (String name ,int money, String type,
			String jobTitle, String jobLocation, int startT, int lunchT, int endT) {
		//last 4 parameters specifically for worker. make empty/0 for all other types
		//add any special parameters if new things needed for other types
		if (type.equals("Wealthy")) 
		{
			//min money req?
			Wealthy newP = new Wealthy(name, money);
			allHousing.add(new Housing(newP, allHousing.size(), "Mansion"));
			newP.setHome(allHousing.get(allHousing.size() - 1));
			population.add(newP);
			newP.startThread();
		}
		else if (type.equals("Crook")) 
		{
			Crook newP = new Crook(name, money);
			allHousing.add(new Housing(newP, allHousing.size(), "Apartment"));
			newP.setHome(allHousing.get(allHousing.size() - 1));
			population.add(newP);
			newP.startThread();
			System.out.println("ayyy");
		}
		else if (type.equals("Worker")) 
		{
			Worker newP = new Worker(name, money, jobTitle, jobLocation, startT, lunchT, endT);
			allHousing.add(new Housing(newP, allHousing.size(), "Apartment"));
			newP.setHome(allHousing.get(allHousing.size() - 1));
			population.add(newP);
			newP.startThread();	
		}
		else if (type.equals("Deadbeat")) 
		{
			Deadbeat newP = new Deadbeat(name, money);
			allHousing.add(new Housing(newP, allHousing.size(), "Park"));
			newP.setHome(allHousing.get(allHousing.size() - 1));
			population.add(newP);
			newP.startThread();
		}
	}
	
	public void editPerson(int index, String name, int money){
		population.get(index).setName(name);
		population.get(index).setMoney(money);
	}
	
	//function used to test gui functionality
	public void printLastPop(){
		System.out.println(population.get(population.size()-1).getName());
	}
	
	public int getPopulationSize() {
		return population.size();
	}
	
	public Person getPerson(int personIndex){
		return population.get(personIndex);
	}
}
