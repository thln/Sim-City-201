package application.gui.animation.agentGui;

//import bank.\*;

import java.awt.*;
import javax.swing.*;

public class BankGuardGui implements Gui{

	//private LoanOfficerRole agent = null;
	private boolean isPresent = true;

	//RestaurantGui gui;

    private int xPos = 260, yPos = 260;//default BankGuard position
    private int xDestination = 260, yDestination = 260;//default start position
	
	private enum Command {noCommand};
	private Command command = Command.noCommand;

	private enum CustomerState {nothing};
	CustomerState state = CustomerState.nothing;
	
	public BankGuardGui(/*LoanOfficerRole c, RestaurantGui gui*/){
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
		g.setColor(Color.ORANGE);
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
    public void GoToTellers() {
    	xDestination = 450;
    	yDestination = 250;
    }
    
    public void BacktoPosition() {
    	xDestination = 260;
    	yDestination = 260;
    }
    
    public void DoCatchRobber() {
    	xDestination = 120;
    	yDestination = 50;
    }
    
    public void BringToJail() {
    	xDestination = 300;
    	yDestination = -20;
    }
    
    public void DoExit() {
    	xDestination = 300;
    	yDestination = 300;
    }
}