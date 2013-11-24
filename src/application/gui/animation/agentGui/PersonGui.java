package application.gui.animation.agentGui;

import person.*;

import java.awt.*;

import javax.swing.JLabel;

public class PersonGui implements Gui{

	private Person agent = null;
	private boolean isPresent = true;
	private boolean isHungry = false;

	//RestaurantGui gui;

	private int xPos, yPos;
	private int xDestination, yDestination;
	private int xHome, yHome;
	private enum Command {noCommand, GoToSeat, GoToCashier, LeaveRestaurant, GoToRestaurant};
	private Command command = Command.noCommand;

	private enum PersonState {nothing, readyToOrder, ordered, gotFood, askForCheck};
	PersonState state = PersonState.nothing;

	private String choice;

	public PersonGui(Person p/*, RestaurantGui gui*/){ //HostAgent m) {
		agent = p;
		xPos = -20;
		yPos = -20;
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
		command = Command.GoToRestaurant;
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

	public void DoGoToBuilding(int location) {//later you will map seatnumber to table coordinates.
		command = Command.GoToSeat;
	}
}
