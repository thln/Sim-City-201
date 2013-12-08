package application.gui.animation.agentGui;

import person.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;

import application.Phonebook;

public class PersonGui extends CityGui {
	
	private final int WINDOWX = 600;
	private final int WINDOWY = 325;
	
	private Person agent = null;
	private boolean isHungry = false;
	public boolean walk = true;

	public boolean raveMode = false;

	Random rand = new Random();
	int red = rand.nextInt(255);
	int blue  = rand.nextInt(255);
	int green  = rand.nextInt(255);
	Color myColor = new Color(red, blue, green);
	Color transColor = new Color(0,0,0,1);
	Color currColor;

	private int xChineseLocation = WINDOWX/2 + 15;
	private int yChineseLocation = 20 + 10;
	//This is going to be used for future restaurants
	private int xItalianLocation = (WINDOWX-100) + 15;
	private int yItalianLocation = 20+10;
	private int xMexicanLocation = (WINDOWX/2-75) + 15;
	private int yMexicanLocation = (WINDOWY - 75) + 10;
	private int xAmericanLocation = (WINDOWX - 100) + 15;
	private int yAmericanLocation = (WINDOWY - 75) + 10;
	private int xSeafoodLocation = (WINDOWX-100) + 15;
	private int ySeafoodLocation = 150 + 10; //?
	
	private int xMarketLocation = 500 + 25;
	private int yMarketLocation = 100 + 25;
	
	//Bus stops
	private int startStopX;
	private int startStopY;
	private int endStopX;
	private int endStopY;

	private int xPos, yPos;//default person position
	private int xDestination, yDestination;//default start position
	private int xHome, yHome;
	private enum Command {noCommand, GoToRestaurant, GoToMarket, GoToBank, GoToBusStop, GoOnBus, GoHome};
	private Command command = Command.noCommand;

	private enum PersonState {nothing};
	PersonState state = PersonState.nothing;

	private String choice;
	public PersonGui() {
		setxPos(0);
		setyPos(125);
		setxHome(0);
		yHome = 125;
		setxDestination(25);
		setyDestination(125);
		setDefaultColor();
	}

	public PersonGui(Person p) {
		this.agent = p;
		if(p instanceof Worker) {
			setxPos(0);
			setyPos(25);
			setxHome(0);
			yHome = 25;
			setxDestination(25);
			setyDestination(25);
		}
		if(p instanceof Wealthy) {
			setxPos(0);
			setyPos(125);
			setxHome(0);
			yHome = 125;
			setxDestination(25);
			setyDestination(125);
		}
		if(p instanceof Crook) {
			setxPos(280);
			setyPos(250);
			setxHome(280);
			yHome = 250;
			setxDestination(300);
			setyDestination(250);
		}
		if(p instanceof Deadbeat) {
			setxPos(280);
			setyPos(250);
			setxHome(280);
			yHome = 250;
			setxDestination(300);
			setyDestination(250);
		}
		setDefaultColor();
		//this.gui = gui;
	}

	public void updatePosition() {
		if (xPos < getxDestination())
			setxPos(xPos + 1);
		else if (xPos > getxDestination())
			setxPos(xPos - 1);

		if (yPos < getyDestination())
			setyPos(yPos + 1);
		else if (yPos > getyDestination())
			setyPos(yPos - 1);

		if (xPos == getxDestination() && yPos == getyDestination() && command != Command.noCommand) {
		//	System.out.println(command + "  " + agent.getName() + "has semaphore permits = " + agent.getAtDestination().availablePermits());

			if(agent != null) {
				if (command == Command.GoToRestaurant) {
					agent.msgAtDestination();
					currColor = transColor;
				}
				if (command == Command.GoToMarket && xPos == xMarketLocation && yPos == yMarketLocation) {
					agent.msgAtDestination();
					currColor = transColor;
				}
				
				if (command == Command.GoToBank && xPos == Phonebook.getPhonebook().getEastBank().location.getX() 
						&& yPos == Phonebook.getPhonebook().getEastBank().location.getY()) {
					agent.msgAtDestination();
					currColor = transColor;
				}
				if (command == Command.GoHome && xPos == getxHome() && yPos == yHome) {
					agent.msgAtDestination();
					currColor = transColor;
				}
				if (command == Command.GoToBusStop) {
					agent.msgAtDestination();
					System.out.println("Reached bus stop");
					currColor = transColor;
				}
				command = Command.noCommand;
			}

		}
	}

	public void draw(Graphics2D g) {

		if (raveMode){
			Random rand = new Random();
			int red = rand.nextInt(255);
			int blue  = rand.nextInt(255);
			int green  = rand.nextInt(255);
			Color myColor = new Color(red, blue, green);
			g.setColor(myColor);
		}
		else if (!raveMode)
			g.setColor(currColor);

		g.fillRect(xPos, yPos, 20, 20);
		if(currColor != transColor)
			g.setColor(Color.WHITE);
		if(agent != null) {
			g.drawString(agent.getName(), xPos, yPos);
		}
		else
			g.drawString("testGui", xPos, yPos);
	}

	public void setHungry() {
		isHungry = true;
		setPresent(true);
		setxDestination(getxHome());
		setyDestination(yHome);
	}

	public boolean isHungry() {
		return isHungry;
	}

	//Actions
	public void DoGoToRestaurant(String restaurantType) {//later you will map building to map coordinates.
		switch(restaurantType.toLowerCase()) {
			case "chinese" : {
				setxDestination(xChineseLocation);
				setyDestination(yChineseLocation);
			}
			break;
			case "italian" : {
				setxDestination(xItalianLocation);
				setyDestination(yItalianLocation);
			}
			break;
			case "mexican" : {
				setxDestination(xMexicanLocation);
				setyDestination(xMexicanLocation);
			}
			break;
			case "american" : {
				setxDestination(xAmericanLocation);
				setyDestination(yAmericanLocation);
			}
			break;
			case "seafood" : {
				setxDestination(xSeafoodLocation);
				setyDestination(ySeafoodLocation);
			}
			break;
			default:
			break;
		}
		setDefaultColor();
		command = Command.GoToRestaurant;
	}

	public void DoGoToMarket() {//later you will map building to map coordinates.
		setxDestination(xMarketLocation);
		setyDestination(yMarketLocation);
		setDefaultColor();
		command = Command.GoToMarket;
	}

	public void DoGoToBank(String location) {
		
		if (location.equals("East")){
	//		System.out.println("x, y is " + Phonebook.getPhonebook().getEastBank().location.getX() + " , " + Phonebook.getPhonebook().getEastBank().location.getY());
		setxDestination((int) Phonebook.getPhonebook().getEastBank().location.getX());
		setyDestination((int) Phonebook.getPhonebook().getEastBank().location.getY());
	//	System.out.println("x, y is " + xDestination + " , " + yDestination);
		}
		else {
			setxDestination((int) Phonebook.getPhonebook().getWestBank().location.getX());
			setyDestination((int) Phonebook.getPhonebook().getWestBank().location.getY());
		}
		setDefaultColor();
		command = Command.GoToBank;
	}

	public void DoGoToBusStop(int stopNum) {//later you will map stop number to map coordinates.
		setxDestination(100);
		setyDestination(100);
	}

	public void DoGoHome() { //the person's assigned home number. Maybe use coordinates instead?
		setxDestination(getxHome());
		setyDestination(yHome);
		setDefaultColor();
		command = Command.GoHome;
	}

	public void doGoToBus(double endX, double endY) {
		System.out.println("Going to bus stop");
		endStopX = (int) endX;
		endStopY = (int) endY;
		findStartStop();
		xDestination = startStopX;
		yDestination = startStopY;
		setDefaultColor();
		command = Command.GoToBusStop;
	}

	public boolean decideForBus(String location) {
		int xDest = 0, yDest = 0;
		
		if (location.equals("East Bank")) {
			xDest = (int) Phonebook.getPhonebook().getEastBank().location.getX();
			yDest = (int) Phonebook.getPhonebook().getEastBank().location.getY();
		}
		if (location.equals("West Bank")) {
			xDest = (int) Phonebook.getPhonebook().getWestBank().location.getX();
			yDest = (int) Phonebook.getPhonebook().getWestBank().location. getY();
		}
		if (location.equals("East Market")) {
			xDest = (int) Phonebook.getPhonebook().getWestBank().location.getX();
			yDest = (int) Phonebook.getPhonebook().getWestBank().location. getY();
		}
		if (location.equals("Chinese Restaurant")) {
			xDest = (int) Phonebook.getPhonebook().getWestBank().location.getX();
			yDest = (int) Phonebook.getPhonebook().getWestBank().location. getY();
		}
		
		if ((xDest - xPos >= 50) || (yDest - yPos >= 50)){
			return false;
		}
			return true;
	}

	public void setHomeLocation(int x, int y) {
		setxHome(x);
		yHome = y;
	}

	public String toString() {
		return "Person Gui";
	}

	public int getxDestination() {
		return xDestination;
	}

	public void setxDestination(int xDestination) {
		this.xDestination = xDestination;
	}

	public int getxHome() {
		return xHome;
	}

	public void setxHome(int xHome) {
		this.xHome = xHome;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public int getyHome() {
		return yHome;
	}

	public void setRaveMode() {
		if (raveMode) {
			raveMode = false;
		}
		else
			raveMode = true;
	}
	
	public void findStartStop() {
		if (xPos <= 300 && yPos <= 162.5){
			startStopX = (int) Phonebook.getPhonebook().busStops.get(0).getX();
			startStopY = (int) Phonebook.getPhonebook().busStops.get(0).getY();
		}
		if (xPos >= 300 && yPos <= 162.5){
			startStopX = (int) Phonebook.getPhonebook().busStops.get(0).getX();
			startStopY = (int) Phonebook.getPhonebook().busStops.get(0).getY();
		}
		if (xPos <= 300 && yPos >= 162.5){
			startStopX = (int) Phonebook.getPhonebook().busStops.get(0).getX();
			startStopY = (int) Phonebook.getPhonebook().busStops.get(0).getY();
		}
		else {
			startStopX = (int) Phonebook.getPhonebook().busStops.get(0).getX();
			startStopY = (int) Phonebook.getPhonebook().busStops.get(0).getY();
		}
	}

	public void setDefaultColor() {
		if (raveMode) {
			Random rand = new Random();
			int red = rand.nextInt(255);
			int blue  = rand.nextInt(255);
			int green  = rand.nextInt(255);
			currColor = new Color (red, blue, green);
		}
		else
			currColor = myColor;
	}

	public int getyDestination() {
		return yDestination;
	}

	public void setyDestination(int yDestination) {
		this.yDestination = yDestination;
	}
}





