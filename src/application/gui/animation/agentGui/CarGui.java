package application.gui.animation.agentGui;

import transportation.*;

import java.awt.*;

import javax.swing.JLabel;

public class CarGui implements Gui{

	private Car agent = null;
	private boolean isPresent = false;
	private boolean isHungry = false;

	//RestaurantGui gui;

	private int xPos, yPos;
	private int xDestination, yDestination;
	private int xHome, yHome;
	private enum Command {noCommand, GoToSeat, GoToCashier, LeaveRestaurant, GoToRestaurant};
	private Command command = Command.noCommand;

	public static final int xTable = 200;
	public static final int yTable = 250;

	private enum CustomerState {nothing, readyToOrder, ordered, gotFood, askForCheck};
	CustomerState state = CustomerState.nothing;

	private String choice;

	public CarGui(Car c/*, RestaurantGui gui*/){ //HostAgent m) {
		agent = c;
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

	public void setPresent(boolean p) {
		isPresent = p;
	}
}
