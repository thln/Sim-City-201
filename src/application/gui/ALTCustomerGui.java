package application.gui;

import restaurant.*;

import java.awt.*;
import java.io.*;

import javax.imageio.*;

import java.awt.image.BufferedImage;;

public class CustomerGui implements Gui{
	
	private RestaurantCustomerRole role = null;
	private boolean isPresent = false;
	private boolean isHungry = false;
	
	ControlPanel gui;
	BufferedImage qmark = null;
	BufferedImage steak = null;
	BufferedImage chicken = null;
	BufferedImage salad = null;
	BufferedImage pizza = null;
	BufferedImage currImg = null;
	
    private int xPos = -40, yPos = -40;//default customer position
    private int xDestination = -40, yDestination = -40;//default start position
    public int home;
    
	private enum Command {noCommand, GoToRestaurant, GoToSeat, LeaveRestaurant, atCashier};
	private Command command=Command.noCommand;
    
    static final int NTABLES = 5;

	public CustomerGui(RestaurantCustomerRole c, ControlPanel gui){ //HostAgent m) {
		role = c;
		
		//maitreD = m;
		this.gui = gui;
		
		try {
            qmark = ImageIO.read(new File("qmark.png"));
        	} catch (IOException e) {
        	}
		try {
            chicken = ImageIO.read(new File("chicken.png"));
        	} catch (IOException e) {
        	}
		try {
            steak = ImageIO.read(new File("steak.png"));
        	} catch (IOException e) {
        	}
		try {
            salad = ImageIO.read(new File("salad.png"));
        	} catch (IOException e) {
        	}
		try {
            pizza = ImageIO.read(new File("pizza.png"));
        	} catch (IOException e) {
        	}
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
			if (command==Command.GoToRestaurant)
				role.msgAnimationFinishedGoToRest();
			if (command==Command.GoToSeat) 
				role.msgAnimationFinishedGoToSeat();
			else if (command==Command.LeaveRestaurant) {
				role.msgAnimationFinishedLeaveRestaurant();
				System.out.println("about to call gui.setCustomerEnabled(role);");
				isHungry = false;
				gui.setCustomerEnabled(role);
			}
			else if (command==Command.atCashier)
				role.msgAtCashier();
			command=Command.noCommand;
		}
		/*
		if (xPos == xDestination && yPos == yDestination
				& (xDestination == 20) & (yDestination == 170) && ) {
			role.msgAtCashier();
			command=Command.noCommand;
		}*/
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillRect(xPos, yPos, 20, 20);
		g.drawImage(currImg, xDestination, yDestination, null);
	}

	public boolean isPresent() {
		return isPresent;
	}
	public void setHungry() {
		isHungry = true;
		role.gotHungry();
		setPresent(true);
	}
	public boolean isHungry() {
		return isHungry;
	}

	public void setPresent(boolean p) {
		isPresent = p;
	}
	
	public void SetHome(int startPos) {
		home = startPos;
	}
	
	public void DoGoToRestaurant() {
		xDestination = 30*home + 20;
		yDestination = 20;
		command = Command.GoToRestaurant;
	}

	public void DoGoToSeat(int seatnumber) {//later you will map seatnumber to table coordinates.
		/*
		for (int ix = 1; ix <= NTABLES; ix++) {
			xDestination = 50*ix;
			yDestination = 50*ix;
		}
		*/
		
		//if(seatnumber < 10) {
			xDestination = 50*seatnumber;
			yDestination = 50;
		//	gui.removeStart(this.role);
		/*}
		else {
			int rows = seatnumber/10;
			int cols = 10;
			int xremainder = seatnumber - cols*rows;
		
			xDestination = 50*xremainder;
			yDestination = 50*rows;
		}*/
		command = Command.GoToSeat;
		 
	}
	
	public void DoGotoCashier(){
		xDestination = 20;
    	yDestination = 170;
    	currImg = null;
    	command=Command.atCashier;
	}
	
	public void DoGoToJail(){
		xDestination = 400;
    	yDestination = 400;
    	System.out.println("about to call gui.setCustomerEnabled(role);");
		isHungry = false;
		//gui.setCustomerEnabled(role);
		command=Command.noCommand;
	}

	public void DoExitRestaurant() {
		//xDestination = 40;
		//yDestination = 20;
		
		xDestination = -40;
		yDestination = -40;
		
		command = Command.LeaveRestaurant;
	}
	
	public void showOrder(String choice) {
		switch(choice){
			case "Steak": currImg = steak;
				break;
			case "Chicken": currImg = chicken;
				break;
			case "Salad": currImg = salad;
				break;
			case "Pizza": currImg = pizza;
				break;
			default: currImg = qmark;
				break;
		}
	}
}
