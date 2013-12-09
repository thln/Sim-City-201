package application.gui.animation.agentGui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Timer;

import transportation.BusAgent;
import application.Phonebook;

public class BusGuiVertical extends CityGui {

	private BusAgent agent = null;
	private boolean isPresent = true;
	
	private final int stopTopY = (int) Phonebook.getPhonebook().getBusStops().get(0).getY()+8;
	private final int stopBottomY = (int) Phonebook.getPhonebook().getBusStops().get(2).getY()+8;
	private final int stopLeftX = 168;
	private final int stopRightX = 386;

	private final int waitTime = 1000;
	
	private int xPos = stopLeftX, yPos = 325;//default bus position
	private int yDestination = stopBottomY;//Stop 1

	private enum Command {noCommand, stop1, stop2, stop3, stop4};
	private Command command = Command.stop1;
	private int lastStop = 4;

	private enum BusState {stopped, enroute, inIntersection1, inIntersection2, inIntersection3, inIntersection4, inCrosswalk1, inCrosswalk2, inCrosswalk6, inCrosswalk7, inCrosswalk11, inCrosswalk12};
	BusState state = BusState.stopped;

	private Timer busStop = new Timer();

	public BusGuiVertical(BusAgent bus){
		agent = bus;
	}

	public void updatePosition() {
		
		if (inBusyIntersection() || inBusyCrosswalk()) {
			return;
		}

		if (yPos < yDestination)
			yPos++;
		else if (yPos > yDestination)
			yPos--;
		
		inAnIntersection();
		inACrosswalk();
		leftAnIntersection();
		leftACrosswalk();
		
		
		if (yPos == 325 || yPos == -25) {
			changeRoads();
		}

		if (yPos == yDestination) {
			System.err.println("Msging vertical bus anget)");
			if (command == Command.stop1) {
				agent.msgAtBusStop(1);
				lastStop = 1;
			}
			else if (command == Command.stop2) {
				agent.msgAtBusStop(2);
				lastStop = 2;
			}
			else if (command == Command.stop3) {
				agent.msgAtBusStop(3);
				lastStop = 3;
			}
			else if (command == Command.stop4) {
				agent.msgAtBusStop(4);
				lastStop = 4;
			}
		}
		command = Command.noCommand;
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillOval(xPos, yPos, 25, 25);
		g.setColor(Color.WHITE);
		g.drawString("B", xPos + 9, yPos + 18);
		
		if(xPos == stopLeftX && yPos == stopTopY) {
			g.fillRect(xPos-4, yPos+4, 5, 20);
		}
		else if(xPos == stopRightX && yPos == stopTopY) {
			g.fillRect(xPos+26, yPos+4, 5, 20);
		}

		else if(xPos == stopLeftX && yPos == stopBottomY) {
			g.fillRect(xPos-4, yPos+4, 5, 20);
		}
		else if(xPos == stopRightX && yPos == stopBottomY) {
			g.fillRect(xPos+26, yPos+4, 5, 20);
		}
	}

	//Actions
	public void goToNextBusStop() {
		if (lastStop == 1) {
			goToStop2();
			return;
		}
		if (lastStop == 2) {
			goToEndOfLeftRoad();
			return;
		}
		if (lastStop == 3) {
			goToStop4();
			return;
		}
		if (lastStop == 4) {
			goToEndOfRightRoad();
			return;
		}
	}
	
	public void goToStop1() {
		command = Command.stop1;
		yDestination = stopBottomY;
	}

	public void goToStop2() {
		command = Command.stop2;
		yDestination = stopTopY;
		
	}

	public void goToStop3() {
		command = Command.stop3;
		yDestination = stopTopY;
	}

	public void goToStop4() {
		command = Command.stop4;
		yDestination = stopBottomY;
	}

	public void goToEndOfRightRoad() {
		yDestination = 325;
	}

	public void goToEndOfLeftRoad() {
		yDestination = -25;
	}

	public void changeRoads() {
		if (yDestination == 325) {
			xPos = stopLeftX;
			goToStop1();
		}
		else {
			xPos = stopRightX;
			goToStop3();
		}
	}
	
	synchronized public boolean inBusyIntersection() {

		Rectangle me = new Rectangle(xPos, yPos-1, 25, 25);
		if (Phonebook.getPhonebook().intersection1.getIntersection().intersects(me)) {
			if (Phonebook.getPhonebook().intersection1.isIntersectionBusy() == true &&
					!(state == BusState.inIntersection1)) {
				return  true;
			}
			return false;
		}
		
		me.setLocation(xPos, yPos+1);
		if (Phonebook.getPhonebook().intersection2.getIntersection().intersects(me)) {
			if (Phonebook.getPhonebook().intersection2.isIntersectionBusy() == true &&
					!(state == BusState.inIntersection2)) {
				return  true;
			}
			return false;
		}
		
		me.setLocation(xPos, yPos-1);
		if (Phonebook.getPhonebook().intersection3.getIntersection().intersects(me)) {
			if (Phonebook.getPhonebook().intersection3.isIntersectionBusy() == true &&
					!(state == BusState.inIntersection3)) {
				return  true;
			}
			return false;
		}
		
		me.setLocation(xPos, yPos+1);
		if (Phonebook.getPhonebook().intersection4.getIntersection().intersects(me)) {
			if (Phonebook.getPhonebook().intersection4.isIntersectionBusy() == true &&
					!(state == BusState.inIntersection4)) {
				return true;
			}
			return false;
		}
		else {
			return false;
		}
	}
	
	synchronized public boolean inBusyCrosswalk() {

		Rectangle me = new Rectangle(xPos, yPos-1, 25, 25);
		if (Phonebook.getPhonebook().crosswalk1.getCrosswalk().intersects(me)) {
			if (Phonebook.getPhonebook().crosswalk1.isCrosswalkBusy() == true &&
					!(state == BusState.inCrosswalk1)) {
				return true;
			}
			return false;
		}
		
		me.setLocation(xPos, yPos+1);
		if (Phonebook.getPhonebook().crosswalk2.getCrosswalk().intersects(me)) {
			if (Phonebook.getPhonebook().crosswalk2.isCrosswalkBusy() == true &&
					!(state == BusState.inCrosswalk2)) {
				return true;
			}
			return false;
		}
		
		me.setLocation(xPos, yPos-1);
		if (Phonebook.getPhonebook().crosswalk6.getCrosswalk().intersects(me)) {
			if (Phonebook.getPhonebook().crosswalk6.isCrosswalkBusy() == true &&
					!(state == BusState.inCrosswalk6)) {
				return  true;
			}
			return false;
		}
		
		me.setLocation(xPos, yPos+1);
		if (Phonebook.getPhonebook().crosswalk7.getCrosswalk().intersects(me)) {
			if (Phonebook.getPhonebook().crosswalk7.isCrosswalkBusy() == true &&
					!(state == BusState.inCrosswalk7)) {
				return  true;
			}
			return false;
		}
		
		me.setLocation(xPos, yPos-1);
		if (Phonebook.getPhonebook().crosswalk11.getCrosswalk().intersects(me)) {
			if (Phonebook.getPhonebook().crosswalk11.isCrosswalkBusy() == true &&
					!(state == BusState.inCrosswalk11)) {
				return  true;
			}
			return false;
		}
		
		me.setLocation(xPos, yPos+1);
		if (Phonebook.getPhonebook().crosswalk12.getCrosswalk().intersects(me)) {
			if (Phonebook.getPhonebook().crosswalk12.isCrosswalkBusy() == true &&
					!(state == BusState.inCrosswalk12)) {
				return  true;
			}
			return false;
		}
		else {
			return false;
		}
	}
	
	public void inAnIntersection() {
		Rectangle me = new Rectangle(xPos, yPos, 25, 25);
		
		if (Phonebook.getPhonebook().intersection1.getIntersection().intersects(me) &&
				!(state == BusState.inIntersection1)) {
			Phonebook.getPhonebook().intersection1.setIntersectionBusy(true);	
			state = BusState.inIntersection1;
		}
		else if (Phonebook.getPhonebook().intersection2.getIntersection().intersects(me) &&
				!(state == BusState.inIntersection2)) {
			Phonebook.getPhonebook().intersection2.setIntersectionBusy(true);	
			state = BusState.inIntersection2;
		}
		else if (Phonebook.getPhonebook().intersection3.getIntersection().intersects(me) &&
				!(state == BusState.inIntersection3)) {
			Phonebook.getPhonebook().intersection3.setIntersectionBusy(true);	
			state = BusState.inIntersection3;
		}
		else if (Phonebook.getPhonebook().intersection4.getIntersection().intersects(me) &&
				!(state == BusState.inIntersection4)) {
			Phonebook.getPhonebook().intersection4.setIntersectionBusy(true);	
			state = BusState.inIntersection4;			
		}
	}	
	
	public void inACrosswalk() {
		Rectangle me = new Rectangle(xPos, yPos, 25, 25);
		
		if (Phonebook.getPhonebook().crosswalk1.getCrosswalk().intersects(me) &&
				!(state == BusState.inCrosswalk1)) {
			Phonebook.getPhonebook().crosswalk1.setCrosswalkBusy(true);	
			state = BusState.inCrosswalk1;
		}
		else if (Phonebook.getPhonebook().crosswalk2.getCrosswalk().intersects(me) &&
				!(state == BusState.inCrosswalk2)) {
			Phonebook.getPhonebook().crosswalk2.setCrosswalkBusy(true);	
			state = BusState.inCrosswalk2;
		}
		else if (Phonebook.getPhonebook().crosswalk6.getCrosswalk().intersects(me) &&
				!(state == BusState.inCrosswalk6)) {
			Phonebook.getPhonebook().crosswalk6.setCrosswalkBusy(true);	
			state = BusState.inCrosswalk6;
		}
		else if (Phonebook.getPhonebook().crosswalk7.getCrosswalk().intersects(me) &&
				!(state == BusState.inCrosswalk7)) {
			Phonebook.getPhonebook().crosswalk7.setCrosswalkBusy(true);	
			state = BusState.inCrosswalk7;
		}
		else if (Phonebook.getPhonebook().crosswalk11.getCrosswalk().intersects(me) &&
				!(state == BusState.inCrosswalk11)) {
			Phonebook.getPhonebook().crosswalk11.setCrosswalkBusy(true);	
			state = BusState.inCrosswalk11;
		}
		else if (Phonebook.getPhonebook().crosswalk12.getCrosswalk().intersects(me) &&
				!(state == BusState.inCrosswalk12)) {
			Phonebook.getPhonebook().crosswalk12.setCrosswalkBusy(true);	
			state = BusState.inCrosswalk12;
		}
	}
	
	public void leftAnIntersection() {
		Rectangle me = new Rectangle(xPos, yPos, 25, 25);
		
		if (!Phonebook.getPhonebook().intersection1.getIntersection().intersects(me)
				&& (state == BusState.inIntersection1)) {
			Phonebook.getPhonebook().intersection1.setIntersectionBusy(false);	
			state = BusState.enroute;	
		}
		else if (!Phonebook.getPhonebook().intersection2.getIntersection().intersects(me)
				&& (state == BusState.inIntersection2)) {
			Phonebook.getPhonebook().intersection2.setIntersectionBusy(false);	
			state = BusState.enroute;	
		}
		else if (!Phonebook.getPhonebook().intersection3.getIntersection().intersects(me) 
				&& (state == BusState.inIntersection3)) {
			Phonebook.getPhonebook().intersection3.setIntersectionBusy(false);	
			state = BusState.enroute;	
		}
		else if (!Phonebook.getPhonebook().intersection4.getIntersection().intersects(me) 
				&& (state == BusState.inIntersection4)) {
			Phonebook.getPhonebook().intersection4.setIntersectionBusy(false);	
			state = BusState.enroute;	
		}
	}
	
	public void leftACrosswalk() {
		Rectangle me = new Rectangle(xPos, yPos, 25, 25);
		
		if (!Phonebook.getPhonebook().crosswalk1.getCrosswalk().intersects(me)
				&& (state == BusState.inCrosswalk1)) {
			Phonebook.getPhonebook().crosswalk1.setCrosswalkBusy(false);	
			state = BusState.enroute;	
		}
		else if (!Phonebook.getPhonebook().crosswalk2.getCrosswalk().intersects(me)
				&& (state == BusState.inCrosswalk2)) {
			Phonebook.getPhonebook().crosswalk2.setCrosswalkBusy(false);	
			state = BusState.enroute;	
		}
		else if (!Phonebook.getPhonebook().crosswalk6.getCrosswalk().intersects(me)
				&& (state == BusState.inCrosswalk6)) {
			Phonebook.getPhonebook().crosswalk6.setCrosswalkBusy(false);	
			state = BusState.enroute;	
		}
		else if (!Phonebook.getPhonebook().crosswalk7.getCrosswalk().intersects(me)
				&& (state == BusState.inCrosswalk7)) {
			Phonebook.getPhonebook().crosswalk7.setCrosswalkBusy(false);	
			state = BusState.enroute;	
		}
		else if (!Phonebook.getPhonebook().crosswalk11.getCrosswalk().intersects(me)
				&& (state == BusState.inCrosswalk11)) {
			Phonebook.getPhonebook().crosswalk11.setCrosswalkBusy(false);	
			state = BusState.enroute;	
		}
		else if (!Phonebook.getPhonebook().crosswalk12.getCrosswalk().intersects(me)
				&& (state == BusState.inCrosswalk12)) {
			Phonebook.getPhonebook().crosswalk12.setCrosswalkBusy(false);	
			state = BusState.enroute;	
		}
	}
}
