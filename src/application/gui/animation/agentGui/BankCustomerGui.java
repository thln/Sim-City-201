package application.gui.animation.agentGui;

import bank.*;

import java.awt.*;

import javax.swing.*;

public class BankCustomerGui extends BankGui {

    private BankCustomerRole agent = null;
    private boolean isPresent = true;
    
    //RestaurantGui gui;

    private int xPos = 300, yPos = 300;//default Customer position
    private int xDestination = 300, yDestination = 260;//default start position
    
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
    	 
    		if (xPos < xDestination)
    			xPos++;
    		else if (xPos > xDestination)
    			xPos--;

    		if (yPos < yDestination)
    			yPos++;
    		else if (yPos > yDestination)
    			yPos--;

    		if (xPos == xDestination && yPos == yDestination) {
    			if(agent != null) {
    				agent.msgAtDestination();
    			}
    		}
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.fillRect(xPos, yPos, 20, 20);
        if(agent != null) {
        	g.setColor(Color.BLACK);
        	g.drawString(agent.getName(), xPos, yPos);
        }
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
    public void WaitTellerLine(int pos) {
    	xDestination = 380;
    	yDestination = 50+30*(pos-1);
    }
    
    public void DoGoToTeller(int window) {
    	xDestination = 450;
    	yDestination = 20*window+30*(window-1); //accounts for space created by window separations
    }
    
    public void DoGoToLoanOfficer() {
    	xDestination = 120;
    	yDestination = 200;
    }
    
    public void DoRobBank() {
    	xDestination = 100;
    	yDestination = 50;
    }
    
    public void DoGoToJail() {
    	xDestination = 300;
    	yDestination = -40;
    }
    
    public void WaitLoanLine(int pos) {
    	xDestination = 30*pos;
    	yDestination = 230;
    }
    
    public void DoExit() {
    	xDestination = 300;
    	yDestination = 350;
    }
}
