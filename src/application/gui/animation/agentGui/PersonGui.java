package application.gui.animation.agentGui;

//import person.\*;

import java.awt.*;

import javax.swing.JLabel;

public class PersonGui implements Gui{

	//private Person agent = null;
	private boolean isPresent = true;
	private boolean isHungry = false;

	//RestaurantGui gui;

	private int xPos = -20, yPos = 50;//default bus position
	private int xDestination = 100, yDestination = 50;//default start position
	private int xHome, yHome;
	private enum Command {noCommand, GoToBuilding, GoToBusStop, GoOnBus, LeaveBuilding};
	private Command command = Command.noCommand;

	private enum PersonState {nothing};
	PersonState state = PersonState.nothing;

	private String choice;

	public PersonGui(/*Person p, RestaurantGui gui*/){ //HostAgent m) {
		//this.agent = p;
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
			command = Command.noCommand;
		}
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillRect(xPos, yPos, 20, 20);
	}

	public boolean isPresent() {
		return isPresent;
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

	public void setPresent(boolean p) {
		isPresent = p;
	}
	
	public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
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
