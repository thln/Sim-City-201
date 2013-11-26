package application;

import housing.Housing;

import javax.swing.*;

import market.*;
import bank.*;
import person.*;
import restaurant.Restaurant;

import java.util.*;
import java.util.Timer;

import application.gui.animation.*;
import application.gui.animation.agentGui.*;
import application.gui.appView.listPanel.*;


public class Application extends JPanel {
	
	private ArrayList<Person> population = new ArrayList<Person>();
	private static List<Housing> allHousing = Collections.synchronizedList(new ArrayList<Housing>());

	public AnimationPanel animPanel;
	public Bank bank;
	public Market market;
	public Restaurant restaurant;
	public Timer updateTimer = new Timer();

	//public static Phonebook phonebook = new Phonebook(bank, market, restaurant, allHousing);

	
	public Application(AnimationPanel ap) {

		animPanel = ap;
		
		Phonebook.getPhonebook().setHousingList(allHousing);
		
		//the following line is for dynamic building and business making in v2
		Phonebook.getPhonebook().getBusinessFromGui(animPanel);
		
		//String name, int money, String jobTitle, String jobPlace, int startT, int lunchT, int endT
		
		
		//Bank Workers
        //SHIFT 1
        Worker bank1a = new Worker("Alex", 100, "bankTeller", "bank", 1, 6, 14);
        Worker bank1b = new Worker("Ben", 100, "loanOfficer", "bank", 1, 6, 14);
        Worker bank1c = new Worker("Caitlyn", 100, "bankGuard", "bank", 0, 6, 13);
        
        //SHIFT 2
        Worker bank2a = new Worker("Andy", 100, "bankTeller", "bank", 12, 18, 1);
        Worker bank2b = new Worker("Billy", 100, "loanOfficer", "bank", 13, 18, 2);
        Worker bank2c = new Worker("Courtney", 100, "bankGuard", "bank", 12, 18, 1);

        //Market Workers
        //SHIFT 1
        Worker market1d = new Worker("Derrick", 10, "marketRunner", "market", 6, 6, 19);
        Worker market1e = new Worker("Erin", 1000, "salesPerson", "market", 5, 6, 18);
        Worker market1f = new Worker("Fred", 10, "UPSman", "market", 7, 6, 20);        

        //SHIFT 2
        Worker market2d = new Worker("Daniel", 100, "marketRunner", "market", 18, 400, 7);
        Worker market2e = new Worker("Elle", 200, "salesPerson", "market", 17, 400, 6);
        Worker market2f = new Worker("Frenchy", 100, "UPSman", "market", 19, 400, 8);        
        
        //Restaurant Workers
        //SHIFT 1
        Worker rest1h = new Worker("Henry", 100, "host", "restaurant", 1, 1800, 14);
        Worker rest1g = new Worker("Greg", 100, "cashier", "restaurant", 4, 1800, 15);        
        Worker rest1i = new Worker("Iris", 100, "cook", "restaurant", 2, 1800, 15);
        Worker rest1j = new Worker("Josh", 100, "waiter", "restaurant", 1, 1800, 14);
        Worker rest1k = new Worker("Kristi", 100, "altWaiter", "restaurant", 3, 1800, 17);
        //SHIFT 2
        Worker rest2g = new Worker("Gil", 100, "cashier", "restaurant", 13, 600, 2);
        Worker rest2h = new Worker("Hannah", 100, "host", "restaurant", 14, 600, 5);
        Worker rest2i = new Worker("Isaac", 100, "cook", "restaurant", 14, 600, 5);
        Worker rest2j = new Worker("Jacob", 100, "waiter", "restaurant", 13, 600, 2);
        Worker rest2k = new Worker("Ken", 100, "altWaiter", "restaurant", 16, 600, 4);
        

		//!!!!Important -- Need to initialize setters 
		//ex. waiter.setHost, waiter.setCook, waiter.setHost, 
		//Do this when person walks in for work***

		//Standard Wealthy Person
		//Wealthy t = new Wealthy("Tam Henry", 700);

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
//		allHousing.add(new Housing(t, allHousing.size(), "Mansion"));
//		t.setHome(allHousing.get(allHousing.size() - 1));

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
		getPopulation().add(bank1a);
		getPopulation().add(bank1b);
		getPopulation().add(bank1c);
		getPopulation().add(market1d);
		getPopulation().add(market1e);
		getPopulation().add(market1f);
		getPopulation().add(rest1g);
		getPopulation().add(rest1h);
		getPopulation().add(rest1i);
		getPopulation().add(rest1j);
		population.add(rest1k);
		//population.add(t);
		
		getPopulation().add(bank2a);
		getPopulation().add(bank2b);
		getPopulation().add(bank2c);
		getPopulation().add(market2d);
		getPopulation().add(market2e);
		getPopulation().add(market2f);
		getPopulation().add(rest2g);
		getPopulation().add(rest2h);
		getPopulation().add(rest2i);
		getPopulation().add(rest2j);
		population.add(rest2k);
		
		for(Person person : getPopulation()) {
			person.setPanel(animPanel);
			PersonGui pg = new PersonGui(person);
			person.setGui(pg);
			animPanel.addGui(pg);
		}
		
		//Starting Threads
		bank1a.startThread();
		bank1b.startThread();	
		bank1c.startThread();
		market1d.startThread();
		market1e.startThread();
		market1f.startThread();
		rest1g.startThread();
		rest1h.startThread();
		rest1i.startThread();
		rest1j.startThread();
		rest1k.startThread();
	//	t.startThread();
		
		bank2a.startThread();
		bank2b.startThread();
		bank2c.startThread();
		market2d.startThread();
		market2e.startThread();
		market2f.startThread();
		rest2g.startThread();
		rest2h.startThread();
		rest2i.startThread();
		rest2j.startThread();
		rest2k.startThread();
		
		updatePeopleTime();
	}
	
	public void updatePeopleTime(){
		int timeConversion = 60 * TimeManager.getSpeedOfTime();
		
		updateTimer.schedule(new TimerTask() {
			public void run() {        
				for (Person p: population){
					p.stateChanged();      
				}
				updatePeopleTime();
			}
		},
		timeConversion);
		
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
			getPopulation().add(newP);
			newP.startThread();
		}
		else if (type.equals("Crook")) 
		{
			Crook newP = new Crook(name, money);
			allHousing.add(new Housing(newP, allHousing.size(), "Apartment"));
			newP.setHome(allHousing.get(allHousing.size() - 1));
			getPopulation().add(newP);
			newP.startThread();
		}
		else if (type.equals("Worker")) 
		{
			Worker newP = new Worker(name, money, jobTitle, jobLocation, startT, lunchT, endT);
			allHousing.add(new Housing(newP, allHousing.size(), "Apartment"));
			newP.setHome(allHousing.get(allHousing.size() - 1));
			getPopulation().add(newP);
			newP.startThread();	
		}
		else if (type.equals("Deadbeat")) 
		{
			Deadbeat newP = new Deadbeat(name, money);
			allHousing.add(new Housing(newP, allHousing.size(), "Park"));
			newP.setHome(allHousing.get(allHousing.size() - 1));
			getPopulation().add(newP);
			newP.startThread();
		}
	}
	
	public void editPerson(int index, String name, int money){
		getPopulation().get(index).setMoney(money);
	}
	
	//function used to test gui functionality
	public void printLastPop(){
		System.out.println(getPopulation().get(getPopulation().size()-1).getName());
	}
	
	public int getPopulationSize() {
		return getPopulation().size();
	}
	
	public int getNumberHomes(){
		return allHousing.size();
	}
	public Person getPerson(int personIndex){
		return getPopulation().get(personIndex);
	}

	public ArrayList<Person> getPopulation() {
		return population;
	}

	public void setPopulation(ArrayList<Person> population) {
		this.population = population;
	}
}
