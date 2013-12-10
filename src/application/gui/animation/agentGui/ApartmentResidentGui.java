package application.gui.animation.agentGui;

import housing.*;

import java.awt.Color;
import java.awt.Graphics2D;

public class ApartmentResidentGui extends HousingGui{
    
    private ApartmentResidentRole agent = null;
    private boolean isPresent = true;
    
    //RestaurantGui gui;

    private int xPos = 300, yPos = 300;//default HouseRenter position
    private int xDestination = 300, yDestination = 200;//default start position
    
    private enum Command {noCommand};
	private Command command = Command.noCommand;

	private enum CustomerState {nothing};
	CustomerState state = CustomerState.nothing;
	
	public ApartmentResidentGui() {
	}
	
    public ApartmentResidentGui(ApartmentResidentRole agent) {
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
    }
	
	public void DoDoToUnit(int unit) {
    	CheckLessThan10();
    	xDestination = 50;
    	yDestination = 50;
    }
    
    public void CheckLessThan10() {
    	
    }
}
