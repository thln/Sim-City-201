package application.gui.animation.agentGui;

import bank.*;

import java.awt.*;

import javax.swing.*;

public class BankCustomerGui implements Gui {

    private BankCustomerRole agent = null;
    private boolean isPresent = true;
    
    //RestaurantGui gui;

    private int xPos = 300, yPos = 365;//default Customer position
    private int xDestination = 300, yDestination = 320;//default start position
    
    private enum Command {noCommand};
	private Command command = Command.noCommand;

	private enum CustomerState {nothing};
	CustomerState state = CustomerState.nothing;
	
    public BankCustomerGui() {
    }
    
    public BankCustomerGui(BankCustomerRole agent/*, RestaurantGui gui*/) {
        this.agent = agent;
      //this.gui = gui;
    }

    public void updatePosition() {
    	//for (int ix = 1; ix <= NTABLES; ix++) {
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
        //}
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
    public void DoGoToSalesPerson() {
    	xDestination = 150;
    	yDestination = 150;
    }
    
    public void DoExit() {
    	xDestination = 300;
    	yDestination = 360;
    }
}
