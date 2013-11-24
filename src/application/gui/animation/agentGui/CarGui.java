package application.gui.animation.agentGui;

//import transportation.\*;

import java.awt.*;

import javax.swing.JLabel;

public class CarGui implements Gui{

	//private Car agent = null;
	private boolean isPresent = true;

	//RestaurantGui gui;

	private int xPos = 200, yPos = 100;//default bus position
	private int xDestination = 200, yDestination = 100;//default start position
	
	private enum Command {noCommand};
	private Command command = Command.noCommand;

	private enum CarState {nothing};
	CarState state = CarState.nothing;

	private String choice;

	public CarGui(/*Car c, RestaurantGui gui*/){ //HostAgent m) {
		//agent = c;
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

	public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }
    
    //Actions
}
