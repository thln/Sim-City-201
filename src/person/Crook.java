package person;

import application.TimeManager.Time;

public class Crook extends Person {
	//These people become dishonest customers in the restaurant
	
	String name;

    public Crook(String name,  int money) {
		super(name);
		this.money = money;
		this.name = name;
    }
}

