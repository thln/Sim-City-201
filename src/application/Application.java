package application;

import javax.swing.*;

import person.Crook;
import person.Person;
import person.Worker;
import person.Wealthy;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class Application extends JPanel {
	
	private ArrayList<Person> Population = new ArrayList<Person>();
	
	public Application() 
	{
		//Worker(String name, int money, String jobTitle, int startT, int lunchT, int endT);
		//Standard Workers
		Worker a = new Worker("Alex", 500, "bankTeller", 800, 1200, 1800);
		Worker b = new Worker("Ben", 500, "loanOfficer", 800, 1200, 1800);
		Worker c = new Worker("Caitlyn", 500, "bankGuard", 800, 1200, 1800);
		Worker d = new Worker("Derrick", 500, "marketRunner", 800, 1300, 1900);
		Worker e = new Worker("Erin", 500, "marketSales", 800, 1300, 1900);
		Worker f = new Worker("Fred", 500, "UPSman", 800, 1300, 1900);
		Worker g = new Worker("Greg", 500, "cashier", 800, 1100, 1700);
		Worker h = new Worker("Henry", 500, "host", 800, 1100, 1700);
		Worker i = new Worker("Iris", 500, "cook", 800, 1100, 1700);
		Worker j = new Worker("Jackie", 500, "waiter", 800, 1100, 1700);
		Worker k = new Worker("Kate", 500, "altWaiter", 800, 1100, 1700);
		
		//!!!!Important -- Need to initialize setters 
		//ex. waiter.setHost, waiter.setCook, waiter.setHost, 
		//Do this when person walks in for work***
		
		//Standard Wealthy Person
		Wealthy t = new Wealthy("Tony", 2000);
		
		//Standard Crook
		Crook v = new Crook("Vinny", 250);
		
		//Standard Deadbeat
		//DeadBeat w = new DeadBeat("Walter");
		
		//Setting Gui for everyone
		
		//Adding to Vector
		Population.add(a);
		Population.add(b);
		Population.add(c);
		Population.add(d);
		Population.add(e);
		Population.add(f);
		Population.add(g);
		Population.add(h);
		Population.add(i);
		Population.add(j);
		Population.add(k);
		Population.add(t);
		Population.add(v);
		//Population.add(w);
		
		//Starting Thread
		a.startThread();
		b.startThread();
		c.startThread();
		d.startThread();
		e.startThread();
		f.startThread();
		g.startThread();
		h.startThread();
		i.startThread();
		j.startThread();
		k.startThread();
		t.startThread();
		v.startThread();
		//w.startThread();
	}
	
	public void addPerson(String name, int money, String type,
			String jobTitle, int startT, int lunchT, int endT)
	{
		//last 4 parameters specifically for worker. make empty/0 for all other types
		//add any special parameters if new things needed for other types
		//need to add Deadbeat
		if(type.equals("Wealthy"))
		{
			//min money req?
			Wealthy newP = new Wealthy(name, money);
			Population.add(newP);
			newP.startThread();
		}
		else if(type.equals("Crook"))
		{
			Crook newP = new Crook(name, money);
			Population.add(newP);
			newP.startThread();
		}
		else if(type.equals("Worker"))
		{
			Worker newP = new Worker(name, money, jobTitle, startT, lunchT, endT);
			Population.add(newP);
			newP.startThread();
		}

	}
	
}
