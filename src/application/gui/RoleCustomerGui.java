package application.gui;

import bank.*;
import application.gui.controlPanel.*;
import java.awt.*;
import java.io.*;

import javax.imageio.*;

import java.awt.image.BufferedImage;;

public class RoleCustomerGui implements Gui{
	
	private BankCustomerRole role = null;
	private boolean isPresent = false;
	private boolean isHungry = false;
	
	ControlPanel gui;
	
    private int xPos = -40, yPos = -40;//default customer position
    private int xDestination = -40, yDestination = -40;//default start position
    public int home;
    
	private enum Command {noCommand, GoToRestaurant, GoToSeat, LeaveRestaurant, atCashier};
	private Command command=Command.noCommand;
    
    static final int NTABLES = 5;

	public RoleCustomerGui(BankCustomerRole c, ControlPanel gui){ //HostAgent m) {
		role = c;
		
		this.gui = gui;
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
			if (command==Command.GoToRestaurant) {
				//role.msgAnimationFinishedGoToRest();
			}
			if (command==Command.GoToSeat) {
				//role.msgAnimationFinishedGoToSeat();
			}
			else if (command==Command.LeaveRestaurant) {
				//role.msgAnimationFinishedLeaveRestaurant();
				System.out.println("about to call gui.setCustomerEnabled(role);");
				isHungry = false;
				//gui.setCustomerEnabled(role);
			}
			else if (command==Command.atCashier) {
				//role.msgAtCashier();
			}
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
	}

	public boolean isPresent() {
		return isPresent;
	}
	public void setHungry() {
		isHungry = true;
	//	role.gotHungry();
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

	public void DoGoToSeat(int seatnumber) {
			xDestination = 50*seatnumber;
			yDestination = 50;
		//	gui.removeStart(this.role);
		command = Command.GoToSeat;
		 
	}
	
	public void DoGotoCashier(){
		xDestination = 20;
    	yDestination = 170;
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
		xDestination = -40;
		yDestination = -40;
		
		command = Command.LeaveRestaurant;
	}
}
