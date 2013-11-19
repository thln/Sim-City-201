package application.gui;


import restaurant.*;

import java.awt.*;

public class HostGui implements Gui {

    private HostRole agent = null;

    private int xPos = -20, yPos = -20;//default waiter position
    private int xDestination = -20, yDestination = -20;//default start position

    public static int xTable = 200;
    public static int yTable = 250;

    public HostGui(HostRole agent) {
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

        if (xPos == xDestination && yPos == yDestination
        		& (xDestination == xTable + 20) & (yDestination == yTable - 20)) {
           //agent.msgAtTable();
        }
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.MAGENTA);
        g.fillRect(xPos, yPos, 20, 20);
    }

    public boolean isPresent() {
        return true;
    }

    public void DoBringToTable(RestaurantCustomerRole customer, int table) {

    	int xTableDim;
    	int yTableDim;

    	if (table == 1) {
    		xTableDim = 200;
    		yTableDim = 250;
    	}
    	else if (table == 2) {
    		xTableDim = 50;
    		yTableDim = 200;
    	}
    	else {
    		xTableDim = 200;
    		yTableDim = 50;
    	}

    	xDestination = xTableDim + 20;
    	yDestination = yTableDim - 20;
    	xTable = xTableDim;
    	yTable = yTableDim;
    }

    public void DoLeaveCustomer() {
        xDestination = -20;
        yDestination = -20;
        xTable = -40;
        yTable = 0;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }
}
