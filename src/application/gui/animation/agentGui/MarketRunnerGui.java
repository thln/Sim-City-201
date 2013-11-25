package application.gui.animation.agentGui;

import market.*;
import java.awt.*;

import javax.swing.*;

public class MarketRunnerGui extends MarketGui{

	private MarketRunnerRole agent = null;
	private boolean isPresent = true;

	//RestaurantGui gui;

    private int xPos = 340, yPos = 100;//default MarketRunner position
    private int xDestination = 300, yDestination = 100;//default start position
	
	private enum Command {noCommand};
	private Command command = Command.noCommand;

	private enum CustomerState {nothing};
	CustomerState state = CustomerState.nothing;
	
	public MarketRunnerGui() {
	}
	
	public MarketRunnerGui(MarketRunnerRole c/*, RestaurantGui gui*/){
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
		g.setColor(Color.GREEN);
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
    public void DoGoToInventory() {
    	xDestination = 450;
    	yDestination = 60;
    }
    
    public void DoGoToSalesPerson() {
    	xDestination = 200;
    	yDestination = 100;
    }
    
    public void DoExit() {
    	xDestination = 300;
    	yDestination = 300;
    }
}