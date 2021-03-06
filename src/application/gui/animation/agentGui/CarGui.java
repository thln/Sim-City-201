package application.gui.animation.agentGui;

//import transportation.\*;

import java.awt.*;

import javax.swing.JLabel;

import transportation.*;

public class CarGui extends CityGui{

	private Car agent = null;
	private boolean isPresent = true;

	//RestaurantGui gui;

	private int xPos = 200, yPos = 100;//default bus position
	private int xDestination = 200, yDestination = 100;//default start position
	
	private enum Command {noCommand};
	private Command command = Command.noCommand;

	private enum CarState {nothing};
	CarState state = CarState.nothing;
	
	public CarGui() {
	}
	
	public CarGui(Car c/*, RestaurantGui gui*/){ //AmericanRestaurantHostRole m) {
		agent = c;
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
		g.fillOval(xPos, yPos, 20, 20);
	}
    
    //Actions
    public void GoToParkingGarage(int garage) {
    	xDestination = 200;
    	yDestination = 200;
    }
    
    public void GoToHomeGarage(int garage) {
    	xDestination = 100;
    	yDestination = 200;
    }
}
