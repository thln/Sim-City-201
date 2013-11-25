package application.gui.animation.agentGui;

import person.*;

import java.awt.*;

import javax.swing.JLabel;

public class PersonGui extends CityGui{

	private Person agent = null;
	private boolean isHungry = false;

	//RestaurantGui gui;

	private int xRestaurant1Location = 400;
	private int yRestaurant1Location = 50;
	//This is going to be used for future restaurants
	//	private int xRestaurant2Location;
	//	private int yRestaurant2Location;
	//	private int xRestaurant3Location;
	//	private int yRestaurant3Location;
	//	private int xRestaurant4Location;
	//	private int yRestaurant4Location;
	//	private int xRestaurant5Location;
	//	private int yRestaurant5Location;
	private int xMarketLocation = 400;
	private int yMarketLocation = 100;
	private int xBankLocation = 400;
	private int yBankLocation = 170;


	private int xPos, yPos;//default person position
	private int xDestination, yDestination;//default start position
	private int xHome, yHome;
	private enum Command {noCommand, GoToBuilding, GoToBusStop, GoOnBus, GoHome};
	private Command command = Command.noCommand;

	private enum PersonState {nothing};
	PersonState state = PersonState.nothing;

	private String choice;
	public PersonGui() {
	}

	public PersonGui(Person p/*, RestaurantGui gui*/){ //HostAgent m) {
		this.agent = p;
		if(p instanceof Worker) {
			xPos = 0;
			yPos = 25;
			xHome = 0;
			yHome = 25;
			xDestination = 25;
			yDestination = 25;
		}
		if(p instanceof Wealthy) {
			xPos = 0;
			yPos = 125;
			xHome = 0;
			yHome = 125;
			xDestination = 25;
			yDestination = 125;
		}
		if(p instanceof Crook) {
			xPos = 280;
			yPos = 250;
			xHome = 280;
			yHome = 250;
			xDestination = 300;
			yDestination = 250;
		}
		if(p instanceof Deadbeat) {
			xPos = 280;
			yPos = 250;
			xHome = 280;
			yHome = 250;
			xDestination = 300;
			yDestination = 250;
		}
		//this.gui = gui;
	}

	public void updatePosition() {
		if (xPos < xDestination)
			xPos++;
		else if (xPos > xDestination)
			xPos--;

		if (yPos < yDestination)
			yPos++;
		else if (yPos > yDestination)
			yPos--;

		if (xPos == xDestination && yPos == yDestination) {
			if(agent != null) {
				if(command == Command.GoToBuilding) {
					agent.msgAtDestination();
				}
				if(command == Command.GoHome) {
					agent.msgAtDestination();
				}
			}
			command = Command.noCommand;
		}
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillRect(xPos, yPos, 20, 20);
		g.setColor(Color.RED);
		if(agent != null) {
			g.drawString(agent.getName(), xPos, yPos);
		}
	}

	public void setHungry() {
		isHungry = true;
		setPresent(true);
		xDestination = xHome;
		yDestination = yHome;
	}

	public boolean isHungry() {
		return isHungry;
	}

	//Actions
	public void DoGoToRestaurant() {//later you will map building to map coordinates.
		xDestination = xRestaurant1Location;
		yDestination = yRestaurant1Location;
		command = Command.GoToBuilding;
	}

	public void DoGoToMarket() {//later you will map building to map coordinates.
		xDestination = xMarketLocation;
		yDestination = yMarketLocation;
		command = Command.GoToBuilding;
	}

	public void DoGoToBank() {//later you will map building to map coordinates.
		xDestination = xBankLocation;
		yDestination = yBankLocation;
		command = Command.GoToBuilding;
	}

	public void DoGoToBusStop(int stopNum) {//later you will map stop number to map coordinates.
		xDestination = 100;
		yDestination = 100;
	}

	public void DoGoHome() { //the person's assigned home number. Maybe use coordinates instead?
		xDestination = xHome;
		yDestination = yHome;
		command = Command.GoHome;
	}

	public void setHomeLocation(int x, int y) {
		xHome = x;
		yHome = y;
	}

	public String toString() {
		return "Person Gui";
	}
}
