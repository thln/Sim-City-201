package application.gui.animation.agentGui;

//import transportation.\*;

import java.awt.*;

import javax.swing.JLabel;

public class BusGui implements Gui{

	//private Bus agent = null;
	private boolean isPresent = true;

	//RestaurantGui gui;

	private int xPos = 100, yPos = 100;//default bus position
	private int xDestination = 100, yDestination = 100;//default start position
	
	private enum Command {noCommand};
	private Command command = Command.noCommand;

	private enum BusState {nothing};
	BusState state = BusState.nothing;

	private String choice;

	public BusGui(/*Bus b, RestaurantGui gui*/){ //HostAgent m) {
		//agent = b;
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
			
		}
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.YELLOW);
		g.fillRect(xPos, yPos, 20, 40);
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
