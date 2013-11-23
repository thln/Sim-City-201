package application.gui.animation.agentGui;

import transportation.*;

import java.awt.*;

import javax.swing.JLabel;

public class CarGui implements Gui{

	private Car agent = null;
	private boolean isPresent = false;

	//RestaurantGui gui;

	private int xPos, yPos;
	private int xDestination, yDestination;
	private int xHome, yHome;
	private enum Command {noCommand};
	private Command command = Command.noCommand;

	private enum CarState {nothing};
	CarState state = CarState.nothing;

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
		g.setColor(Color.BLUE);
		g.fillRect(xPos, yPos, 20, 20);
	}

	public boolean isPresent() {
		return isPresent;
	}

	public void setPresent(boolean p) {
		isPresent = p;
	}
}
