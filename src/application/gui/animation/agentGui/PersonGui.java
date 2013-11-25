package application.gui.animation.agentGui;

import person.*;

import java.awt.*;

import javax.swing.JLabel;

public class PersonGui extends CityGui{

	private Person agent = null;
	private boolean isHungry = false;

	//RestaurantGui gui;

	private int xPos, yPos;//default person position
	private int xDestination, yDestination;//default start position
	private int xHome, yHome;
	private enum Command {noCommand, GoToBuilding, GoToBusStop, GoOnBus, LeaveBuilding};
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
			xDestination = 25;
			yDestination = 25;
		}
		if(p instanceof Wealthy) {
			xPos = 0;
			yPos = 125;
			xDestination = 25;
			yDestination = 125;
		}
		if(p instanceof Crook) {
			xPos = 280;
			yPos = 250;
			xDestination = 300;
			yDestination = 250;
		}
		if(p instanceof Deadbeat) {
			xPos = 280;
			yPos = 250;
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

	public void DoGoToBuilding(int bLocX, int bLocY) {//later you will map building to map coordinates.
		xDestination = bLocX;
		yDestination = bLocY;
		command = Command.GoToBuilding;
	}
	
	public void DoGoToBusStop(int stopNum) {//later you will map stop number to map coordinates.
		xDestination = 100;
		yDestination = 100;
	}
	
	public void DoGoHome(int home) { //the person's assigned home number. Maybe use coordinates instead?
		
	}
}
