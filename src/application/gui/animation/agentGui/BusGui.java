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
	private int currWidth = 20, currHeight = 20; //default bus size
	private int newWidth = 20, newHeight = 40; //default start size
	
	private enum Command {noCommand, beHorizontal, beVertical};
	private Command command = Command.noCommand;

	private enum BusState {nothing};
	BusState state = BusState.nothing;
	
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
			if(command == Command.beHorizontal) {
				newWidth = 40;
				newHeight = 20;
			}
			if(command == Command.beVertical) {
				newWidth = 20;
				newHeight = 40;
			}
			command = Command.noCommand;
		}
		
		if (currWidth < newWidth)
			currWidth++;
		else if (currWidth > newWidth)
			currWidth--;

		if (currHeight < newHeight)
			currHeight++;
		else if (currHeight > newHeight)
			currHeight--;
		if (currWidth == newWidth && currHeight == newHeight) {

		}
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.YELLOW);
		g.fillRect(xPos, yPos, currWidth, currHeight);
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
    public void GoToStopA() {
    	xDestination = 100;
    	yDestination = 250;
    	command = Command.beHorizontal;
    }
    
    public void GoToStopB() {
    	xDestination = 400;
    	yDestination = 250;
    	command = Command.beVertical;
    }
}
