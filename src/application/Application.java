package application;

import housing.Housing;

import javax.management.relation.Role;
import javax.swing.*;

import market.Market;
import bank.Bank;
import bank.LoanOfficerRole;
import bank.BankGuardRole;
import person.Crook;
import person.Person;
import person.Worker;
import person.Wealthy;
import person.DeadBeat;
import restaurant.Restaurant;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class Application extends JPanel {
	
	private ArrayList<Person> population = new ArrayList<Person>();
	private static ArrayList<Housing> allHousing = new ArrayList<Housing>();

	public static Bank bank = new Bank();
	public static Market market = new Market();
	public static Restaurant restaurant = new Restaurant();
	public static Phonebook phonebook = new Phonebook(bank, market, restaurant, allHousing);

	public Application() {

		//Worker(String name, int money, String jobTitle, int startT, int lunchT, int endT);
		//Standard Workers

		//add all important workers to phonebook
		Worker b = new Worker("Ben", 500, "loanOfficer", 800, 1200, 1600);
		Worker c = new Worker("Caitlyn", 500, "bankGuard", 800, 1200, 1600);
			Phonebook.bank.bankGuardRole = (BankGuardRole) c.getWorkerRole();
		Worker a = new Worker("Alex", 500, "bankTeller", 800, 1200, 1600);
		//	Worker d = new Worker("Derrick", 500, "marketRunner", 1100, 1300, 1900);
		Worker e = new Worker("Erin", 0, "marketSales", 1100, 1300, 1900);
		Worker f = new Worker("Fred", 500, "UPSman", 1100, 1300, 1900);
		Worker g = new Worker("Greg", 10, "cashier", 1000, 1100, 1700);
		Worker h = new Worker("Henry", 500, "host", 1000, 1100, 1700);
		Worker i = new Worker("Iris", 50, "cook", 1000, 1100, 1700);
		Worker j = new Worker("Josh", 500, "waiter", 1000, 1100, 1700);
		Worker k = new Worker("Kristi", 20, "altWaiter", 1000, 1100, 1700);
		//	Worker l = new Worker("Lauren", 100, "waiter", 1000, 1100, 1700);
		Worker m = new Worker("Matthew", 300, "maintenance", 800, 1400, 1700);

		//!!!!Important -- Need to initialize setters 
		//ex. waiter.setHost, waiter.setCook, waiter.setHost, 
		//Do this when person walks in for work***

		//Standard Wealthy Person
		Wealthy t = new Wealthy("Tony", 2000);

		//Standard Crook
		Crook v = new Crook("Vinny", 250);

		//Standard Deadbeat
		DeadBeat w = new DeadBeat("Walter", 0);

		//Setting Gui for everyone
		
		//Adding housing
		allHousing.add(new Housing(a, allHousing.size(), "Apartment"));
		a.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(b, allHousing.size(), "Apartment"));
		b.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(c, allHousing.size(), "Apartment"));
		c.setHome(allHousing.get(allHousing.size() - 1));
		//allHousing.add(new Housing(d, allHousing.size(), "Apartment"));
		//d.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(e, allHousing.size(), "Apartment"));
		e.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(f, allHousing.size(), "Apartment"));
		f.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(g, allHousing.size(), "Apartment"));
		g.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(h, allHousing.size(), "Apartment"));
		h.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(i, allHousing.size(), "Apartment"));
		i.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(j, allHousing.size(), "Apartment"));
		j.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(k, allHousing.size(), "Apartment"));
		k.setHome(allHousing.get(allHousing.size() - 1));
		//allHousing.add(new Housing(l, allHousing.size(), "Apartment"));
		//l.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(m, allHousing.size(), "Apartment"));
		m.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(t, allHousing.size(), "Mansion"));
		t.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(v, allHousing.size(), "Apartment"));
		v.setHome(allHousing.get(allHousing.size() - 1));
		allHousing.add(new Housing(w, allHousing.size(), "Park"));
		w.setHome(allHousing.get(allHousing.size() - 1));
		
		//Adding to Vector
		population.add(a);
		population.add(b);
		population.add(c);
	//	Population.add(d);
		population.add(e);
		population.add(f);
		population.add(g);
		population.add(h);
		population.add(i);
		population.add(j);
		population.add(k);
	//	Population.add(l);
		population.add(m);
		population.add(t);
		population.add(v);
		population.add(w);
		
		//Starting Thread
		a.startThread();
		b.startThread();
		c.startThread();
		//	d.startThread();
		e.startThread();
		f.startThread();
		g.startThread();
		h.startThread();
		i.startThread();
		j.startThread();
		k.startThread();
		//	l.startThread();
		m.startThread();
		t.startThread();
		v.startThread();
		w.startThread();

		b.updateTime(800);
		c.updateTime(800);
		a.updateTime(800);
		m.updateTime(900);
	}

	public void addPerson(String name, int money, String type,
			String jobTitle, int startT, int lunchT, int endT)
	{
		//last 4 parameters specifically for worker. make empty/0 for all other types
		//add any special parameters if new things needed for other types
		if(type.equals("Wealthy"))
		{
			//min money req?
			Wealthy newP = new Wealthy(name, money);
			allHousing.add(new Housing(newP, allHousing.size(), "Mansion"));
			newP.setHome(allHousing.get(allHousing.size() - 1));
			population.add(newP);
			newP.startThread();
		}
		else if(type.equals("Crook"))
		{
			Crook newP = new Crook(name, money);
			allHousing.add(new Housing(newP, allHousing.size(), "Apartment"));
			newP.setHome(allHousing.get(allHousing.size() - 1));
			population.add(newP);
			newP.startThread();
		}
		else if(type.equals("Worker"))
		{
			Worker newP = new Worker(name, money, jobTitle, startT, lunchT, endT);
			allHousing.add(new Housing(newP, allHousing.size(), "Apartment"));
			newP.setHome(allHousing.get(allHousing.size() - 1));
			population.add(newP);
			newP.startThread();
		}
		else if(type.equals("DeadBeat"))
		{
			DeadBeat newP = new DeadBeat(name, money);
			allHousing.add(new Housing(newP, allHousing.size(), "Park"));
			newP.setHome(allHousing.get(allHousing.size() - 1));
			population.add(newP);
			newP.startThread();
		}
	}

}
