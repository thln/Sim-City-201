package application.gui.animation.agentGui;


import restaurant.CustomerAgent;
import restaurant.HostAgent;

import java.awt.*;

public class HostGui implements Gui {

    private HostAgent agent = null;

    private int xPos = 20, yPos = 400;//default HOST position
    private int xDestination = 20, yDestination = 350;//default start position
    
    static final int NTABLES = 5;
    int tables;

    public HostGui(HostAgent agent) {
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
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.MAGENTA);
        g.fillRect(xPos, yPos, 20, 20);
    }

    public boolean isPresent() {
        return true;
    }


    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }
}
