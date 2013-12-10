package application.gui.animation.agentGui;

import housing.*;

import java.awt.*;

import javax.swing.*;

public class HousingResidentGui extends HousingGui {

    private HousingResidentRole agent = null;
    private boolean isPresent = true;
    
    //RestaurantGui gui;

    private int xPos = 300, yPos = 300;//default HouseRenter position
    private int xDestination = 300, yDestination = 260;//default start position
    
    private enum Command {noCommand};
	private Command command = Command.noCommand;

	private enum CustomerState {nothing};
	CustomerState state = CustomerState.nothing;
    
	public HousingResidentGui() {
	}
	
    public HousingResidentGui(HousingResidentRole agent) {
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
    			
    		}
        //}
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.fillRect(xPos, yPos, 20, 20);
        g.setColor(Color.BLACK);
        if(agent != null)
        	g.drawString(agent.getName(), xPos, yPos);
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
    public void DoGoToKitchen() {
    	xDestination = 510;
    	yDestination = 85;
    }
    
    public void DoGoToFridge() {
    	xDestination = 450;
    	yDestination = 50;
    }
    
    public void DoCooking() {
    	xDestination = 510;
    	yDestination = 75;
    }
    
    public void DoGoToBed() {
    	xDestination = 510;
    	yDestination = 200;
    }
    
    public void DoGoToBedRoom() {
    	xDestination = 450;
    	yDestination = 200;
    }
    
    public void DoGoToBathroom() {
    	xDestination = 100;
    	yDestination = 50;
    }
    
    public void DoGoToLivingRoom() {
    	xDestination = 100;
    	yDestination = 200;
    }
    
    public void DoExit() {
    	xDestination = 300;
    	yDestination = 350;
    }
}
