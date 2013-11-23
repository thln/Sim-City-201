package application.gui.animation.agentGui;

import market.*;

import java.awt.*;
import javax.swing.*;

public class MarketRunnerGui implements Gui{

	private MarketRunnerRole agent = null;
	private boolean isPresent = false;

	//RestaurantGui gui;

	private int xPos, yPos;
	private int xDestination, yDestination;
	
	private enum Command {noCommand};
	private Command command = Command.noCommand;

	private enum CustomerState {nothing};
	CustomerState state = CustomerState.nothing;

	public MarketRunnerGui(MarketRunnerRole c/*, RestaurantGui gui*/){
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
	
	//Actions
	
}