package application.gui;


import restaurant.CustomerAgent;
import restaurant.WaiterAgent;

import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

public class WaiterGui implements Gui {

    private WaiterAgent agent = null;

    private int xPos = 300, yPos = 140;//default waiter position
    private int xDestination = 300, yDestination = 120;//default start position
    int homePos;
    private enum Command {noCommand, goToWaiting, GoToSeat, LeaveTable, LeaveTableAgain, atCook, atCashier};
	private Command command=Command.noCommand;
    
    static final int NTABLES = 5;
    int tables;

    public WaiterGui(WaiterAgent agent) {
        this.agent = agent;
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
    			if (command==Command.goToWaiting) {
					agent.msgAtCust();
    			}
    			if (command==Command.GoToSeat) {
    				agent.msgAtTable();
    			}
    			else if (command==Command.LeaveTable) {
    				agent.msgBackAtTable();
   				}
    			else if (command==Command.LeaveTableAgain) {
    				agent.msgAtTableAgain();
    			}
    			else if(command==Command.atCook) {
        			agent.msgAtCook();
   				}
   				else if(command==Command.atCashier) {
   	    			agent.msgAtCashier();
    			}
    			command=Command.noCommand;
    		}
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.YELLOW);
        g.fillRect(xPos, yPos, 20, 20);
    }

    public boolean isPresent() {
        return true;
    }
    
    public void StartAt(int home) {
    	homePos = home;
    	xDestination = 300 - 30*homePos;
    	yDestination = 120;
    }
    
    public void GoToWaiting(int Pos) {
    	xDestination = 30*Pos + 40;
    	yDestination = 0;
    	
    	command = Command.goToWaiting;
    }
    
    public void DoBringToTable(int table) {
    	xDestination = 50*table + 20;
    	yDestination = 50 - 20;
    	
    	command = Command.GoToSeat;
    }

    public void GoToHome() {
    	xDestination = 300 - 30*homePos;
    	yDestination = 120;
        command = Command.noCommand;
    }
    
    public void DoGotoCook() {
        xDestination = 350;
        yDestination = 250;
        command = Command.atCook;
    }
    
    public void DoGoToTable(int table) {
    	xDestination = 50*table + 20;
		yDestination = 50 - 20;
		command = Command.LeaveTable;
    }
    
    public void DoGoToTableAgain(int table) {
    	xDestination = 50*table + 20;
		yDestination = 50 - 20;
		command = Command.LeaveTableAgain;
    }
    
    public void GotoBreak() {
    	xDestination = 400;
    	yDestination = 20;
    }
    
    public void GoToCashier() {
    	xDestination = 20;
    	yDestination = 170;
    	command = Command.atCashier;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }
}
