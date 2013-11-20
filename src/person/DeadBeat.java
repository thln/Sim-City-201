package person;

public class Deadbeat extends Person {
    int wanderTime = 8;
    int parkTime = 20;
    int eatTime = 13;
    
	String name;

    public Deadbeat(String name,  int money) {
		super(name);
		this.money = money;
		this.name = name;
    }

	
	public void msgWelfareCheck() {
	    money += 50;
	}
}
