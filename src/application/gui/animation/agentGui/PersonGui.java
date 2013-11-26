package application.gui.animation.agentGui;

import person.*;

import java.awt.*;
import java.util.Random;

import javax.swing.JLabel;

public class PersonGui extends CityGui{

	private Person agent = null;
	private boolean isHungry = false;

	public boolean raveMode = false;
	
	Random rand = new Random();
	int red = rand.nextInt(255);
	int blue  = rand.nextInt(255);
	int green  = rand.nextInt(255);
	Color myColor = new Color(red, blue, green);

	private int xRestaurant1Location = 300 + 15;
	private int yRestaurant1Location = 20 + 10;
	//This is going to be used for future restaurants
	//	private int xRestaurant2Location;
	//	private int yRestaurant2Location;
	//	private int xRestaurant3Location;
	//	private int yRestaurant3Location;
	//	private int xRestaurant4Location;
	//	private int yRestaurant4Location;
	//	private int xRestaurant5Location;
	//	private int yRestaurant5Location;
	private int xMarketLocation = 500 + 25;
	private int yMarketLocation = 100 + 25;
	private int xBankLocation = 300 + 30;
	private int yBankLocation = 230 + 30;


	private int xPos, yPos;//default person position
	private int xDestination, yDestination;//default start position
	private int xHome, yHome;
	private enum Command {noCommand, GoToRestaurant, GoToMarket, GoToBank, GoToBusStop, GoOnBus, GoHome};
	private Command command = Command.noCommand;

	private enum PersonState {nothing};
	PersonState state = PersonState.nothing;

	private String choice;
	public PersonGui() {
	}

	public PersonGui(Person p/*, RestaurantGui gui*/){ //HostAgent m) {
		this.agent = p;
		if(p instanceof Worker) {
			setxPos(0);
			setyPos(25);
			setxHome(0);
			yHome = 25;
			setxDestination(25);
			yDestination = 25;
		}
		if(p instanceof Wealthy) {
			setxPos(0);
			setyPos(125);
			setxHome(0);
			yHome = 125;
			setxDestination(25);
			yDestination = 125;
		}
		if(p instanceof Crook) {
			setxPos(280);
			setyPos(250);
			setxHome(280);
			yHome = 250;
			setxDestination(300);
			yDestination = 250;
		}
		if(p instanceof Deadbeat) {
			setxPos(280);
			setyPos(250);
			setxHome(280);
			yHome = 250;
			setxDestination(300);
			yDestination = 250;
		}
		//this.gui = gui;
	}

	public void updatePosition() {
		if (getxPos() < getxDestination())
			setxPos(getxPos() + 1);
		else if (getxPos() > getxDestination())
			setxPos(getxPos() - 1);

		if (getyPos() < yDestination)
			setyPos(getyPos() + 1);
		else if (getyPos() > yDestination)
			setyPos(getyPos() - 1);

		if (getxPos() == getxDestination() && getyPos() == yDestination) {
			//System.out.println(command + "  " + agent.getName() + "has semaphore permits = " + agent.getAtDestination().availablePermits());

			if(agent != null) {
				if (command == Command.GoToRestaurant && getxPos() == xRestaurant1Location && getyPos() == yRestaurant1Location) {
					agent.msgAtDestination();
				}
				if (command == Command.GoToMarket && getxPos() == xMarketLocation && getyPos() == yMarketLocation) {
					agent.msgAtDestination();
				}
				if (command == Command.GoToBank && getxPos() == xBankLocation && getyPos() == yBankLocation) {
					agent.msgAtDestination();
				}
				if (command == Command.GoHome && getxPos() == getxHome() && getyPos() == yHome) {
					agent.msgAtDestination();
				}
				command = Command.noCommand;
			}

		}
	}

	public void draw(Graphics2D g) {
		g.setColor(myColor);
		
		if (raveMode) {
			Random rand = new Random();
			int red = rand.nextInt(255);
			int blue  = rand.nextInt(255);
			int green  = rand.nextInt(255);
			g.setColor(new Color (red, blue, green));
		}
		
		g.fillRect(getxPos(), getyPos(), 20, 20);
		g.setColor(Color.WHITE);
		if(agent != null) {
			g.drawString(agent.getName(), getxPos(), getyPos());
		}
	}

	public void setHungry() {
		isHungry = true;
		setPresent(true);
		setxDestination(getxHome());
		yDestination = yHome;
	}

	public boolean isHungry() {
		return isHungry;
	}

	//Actions
	public void DoGoToRestaurant() {//later you will map building to map coordinates.
		setxDestination(xRestaurant1Location);
		yDestination = yRestaurant1Location;
		command = Command.GoToRestaurant;
	}

	public void DoGoToMarket() {//later you will map building to map coordinates.
		setxDestination(xMarketLocation);
		yDestination = yMarketLocation;
		command = Command.GoToMarket;
	}

	public void DoGoToBank() {//later you will map building to map coordinates.		
		setxDestination(xBankLocation);
		yDestination = yBankLocation;
		command = Command.GoToBank;
	}

	public void DoGoToBusStop(int stopNum) {//later you will map stop number to map coordinates.
		setxDestination(100);
		yDestination = 100;
	}

	public void DoGoHome() { //the person's assigned home number. Maybe use coordinates instead?
		setxDestination(getxHome());
		yDestination = yHome;
		command = Command.GoHome;
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
}
