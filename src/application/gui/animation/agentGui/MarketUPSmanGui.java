package application.gui.animation.agentGui;

import market.*;
import java.awt.*;
import javax.swing.*;

public class MarketUPSmanGui implements Gui{

	private UPSmanRole agent = null;
	private boolean isPresent = true;

	//RestaurantGui gui;

    private int xPos = 500, yPos = 200;//default MarketRunner position
    private int xDestination = 480, yDestination = 200;//default start position
	
	private enum Command {noCommand};
	private Command command = Command.noCommand;

	private enum CustomerState {nothing};
	CustomerState state = CustomerState.nothing;
	
	public MarketUPSmanGui() {
	}
	
	public MarketUPSmanGui(UPSmanRole c/*, RestaurantGui gui*/){
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
		g.setColor(new Color(102, 51, 0));
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
	public void DoDeliverOrder() {
		xDestination = 570;
		yDestination = 200;
	}
	
	public void DoGoToSalesPerson() {
    	xDestination = 200;
    	yDestination = 150;
	}
}