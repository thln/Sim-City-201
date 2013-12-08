package application.gui.animation.agentGui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import application.Phonebook;
public class BusGuiVertical extends CityGui {

	//	private Bus agent = null;
	private boolean isPresent = true;
	
	private final int stopTopY = 40;
	private final int stopBottomY = 230;
	private final int stopLeftX = 168;
	private final int stopRightX = 388;

	private final int waitTime = 1000;
	
	private int xPos = stopLeftX, yPos = 325;//default bus position
	private int yDestination = stopBottomY;//Stop 1

	private enum Command {noCommand, stop1, stop2, stop3, stop4};
	private Command command = Command.stop1;

	private enum BusState {stopped, enroute, inIntersection1, inIntersection2, inIntersection3, inIntersection4};
	BusState state = BusState.stopped;

	private Timer busStop = new Timer();

	public BusGuiVertical(){
	}

	public void updatePosition() {
		
		if (inBusyIntersection()) {
			return;
		}

		if (yPos < yDestination)
			yPos++;
		else if (yPos > yDestination)
			yPos--;
		
		inAnIntersection();
		leftAnIntersection();
		
		
		if (yPos == 325 || yPos == -25) {
			changeRoads();
		}

		if (yPos == yDestination) {
			if (command == Command.stop1) {
				busStop.schedule(new TimerTask() {
					public void run() {
						goToStop2();
					}
				},
				waitTime);
			}
			else if (command == Command.stop2) {
				busStop.schedule(new TimerTask() {
					public void run() {
						goToEndOfLeftRoad();
					}
				},
				waitTime);
			}
			else if (command == Command.stop3) {
				busStop.schedule(new TimerTask() {
					public void run() {
						goToStop4();
					}
				},
				waitTime);
			}
			else if (command == Command.stop4) {
				busStop.schedule(new TimerTask() {
					public void run() {
						goToEndOfRightRoad();
					}
				},
				waitTime);
			}
		}
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillOval(xPos, yPos, 25, 25); //keeping it uniform for now
	}

	//Actions
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
			System.err.println("Intersection 3 busy with bus " + this);
		}
		else if (Phonebook.getPhonebook().intersection4.getIntersection().intersects(me) &&
				!(state == BusState.inIntersection4)) {
			Phonebook.getPhonebook().intersection4.setIntersectionBusy(true);	
			state = BusState.inIntersection4;			
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
			System.err.println(this + " left Intersection 3");
		}
		else if (!Phonebook.getPhonebook().intersection4.getIntersection().intersects(me) 
				&& (state == BusState.inIntersection4)) {
			Phonebook.getPhonebook().intersection4.setIntersectionBusy(false);	
			state = BusState.enroute;	
		}
	}
}
