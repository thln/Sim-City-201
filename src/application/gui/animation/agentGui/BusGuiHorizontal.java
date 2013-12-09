package application.gui.animation.agentGui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

import transportation.BusAgent;
import application.Phonebook;

public class BusGuiHorizontal extends CityGui {

	private BusAgent agent = null;
	private boolean isPresent = true;
	private boolean checkedStation = false;

	private final int stopLeftX = (int) Phonebook.getPhonebook().getBusStops().get(0).getX();
	private final int stopRightX = (int) Phonebook.getPhonebook().getBusStops().get(1).getX();;
	private final int stopTopY = 75;
	private final int stopBottomY = 195;

	private final int waitTime = 1500;

	private int xPos = 0, yPos = stopTopY;//default bus position
	private int xDestination = stopLeftX;//Stop 1

	private enum Command {noCommand, stop1, stop2, stop3, stop4};
	private Command command = Command.stop1;
	private int lastStop = 4;

	private enum BusState {stopped, enroute, inIntersection1, inIntersection2, inIntersection3, inIntersection4, inCrosswalk3, inCrosswalk4, inCrosswalk5, inCrosswalk8, inCrosswalk9, inCrosswalk10};
	BusState state = BusState.stopped;

	private Timer busStop = new Timer();
	private Semaphore wait = new Semaphore(0, true);

	public BusGuiHorizontal(BusAgent bus){
		agent = bus;
	}

	public void updatePosition() {
		if (inBusyIntersection() || inBusyCrosswalk()) {
			return;
		}

		if (xPos < xDestination)
			xPos++;
		else if (xPos > xDestination)
			xPos--;		

		inAnIntersection();
		inACrosswalk();
		leftAnIntersection();
		leftACrosswalk();

		if (xPos == 600 || xPos == -25) {
			changeRoads();
		}

		if (xPos == xDestination) {
			if (command == Command.stop1) {
				busStop.schedule(new TimerTask() {
					public void run() {
//						if(!checkedStation)
//						{
						agent.msgAtBusStop(1);
						lastStop = 1;
//						checkedStation = true;
//						}
					}
				},
				waitTime);
			}
			else if (command == Command.stop2) {
				busStop.schedule(new TimerTask() {
					public void run() {
//						if(!checkedStation)
//						{
						agent.msgAtBusStop(2);
						lastStop = 2;
//						checkedStation = true;
//						}
					}
				},
				waitTime);
			}
			else if (command == Command.stop3) {
				busStop.schedule(new TimerTask() {
					public void run() {
//						if(!checkedStation)
//						{
						agent.msgAtBusStop(3);
						lastStop = 3;
//						checkedStation = true;
//						}
					}
				},
				waitTime);
			}
			else if (command == Command.stop4) {
				busStop.schedule(new TimerTask() {
					public void run() {
//						if(!checkedStation)
//						{
						agent.msgAtBusStop(4);
						lastStop = 4;
//						checkedStation = true;
//						}
					}
				},
				waitTime);
			}
		}
		else
		{
			if(agent.getCheckedStation())
			{
				agent.msgLeavingStation();
			}
			//checkedStation = false;
		}
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillOval(xPos, yPos, 25, 25);
		g.setColor(Color.WHITE);
		g.drawString("A", xPos + 9, yPos + 18);

		if(xPos == stopLeftX && yPos == stopTopY) {
			g.fillRect(xPos+4, yPos-4, 20, 5);
		}
		else if(xPos == stopRightX && yPos == stopTopY) {
			g.fillRect(xPos+4, yPos-4, 20, 5);
		}

		else if (xPos == stopLeftX && yPos == stopBottomY) {
			g.fillRect(xPos+4, yPos+26, 20, 5);
		}
		else if(xPos == stopRightX && yPos == stopBottomY) {
			g.fillRect(xPos+4, yPos+26, 20, 5);
		}
	}

	//Actions
	public void goToNextBusStop() {
		if (lastStop == 1) {
			goToStop2();
			return;
		}
		if (lastStop == 2) {
			goToEndOfTopRoad();
			return;
		}
		if (lastStop == 3) {
			goToStop4();
			return;
		}
		if (lastStop == 4) {
			goToEndOfBottomRoad();
			return;
		}
	}

	public void goToStop1() {
		command = Command.stop1;
		xDestination = stopLeftX;
	}

	public void goToStop2() {
		command = Command.stop2;
		xDestination = stopRightX;
	}

	public void goToStop3() {
		command = Command.stop3;
		xDestination = stopRightX;
	}

	public void goToStop4() {
		command = Command.stop4;
		xDestination = stopLeftX;
	}

	public void goToEndOfTopRoad() {
		xDestination = 600;
	}

	public void goToEndOfBottomRoad() {
		xDestination = -25;
	}

	public void changeRoads() {
		if (xDestination == 600) {
			yPos = stopBottomY;
			goToStop3();
		}
		else {
			yPos = stopTopY;
			goToStop1();
		}
	}

	synchronized public boolean inBusyIntersection() {
		Rectangle me = new Rectangle(xPos+1, yPos, 25, 25);

		if (Phonebook.getPhonebook().intersection1.getIntersection().intersects(me)) {
			if (Phonebook.getPhonebook().intersection1.isIntersectionBusy() == true &&
					!(state == BusState.inIntersection1)) {
				return  true;
			}
			return false;
		}

		me.setLocation(xPos+1, yPos);
		if (Phonebook.getPhonebook().intersection2.getIntersection().intersects(me)) {
			if (Phonebook.getPhonebook().intersection2.isIntersectionBusy() == true &&
					!(state == BusState.inIntersection2)) {
				return  true;
			}
			return false;
		}

		me.setLocation(xPos-1, yPos);
		if (Phonebook.getPhonebook().intersection3.getIntersection().intersects(me)) {
			if (Phonebook.getPhonebook().intersection3.isIntersectionBusy() == true &&
					!(state == BusState.inIntersection3)) {
				return  true;
			}
			return false;
		}

		me.setLocation(xPos-1, yPos);
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

	synchronized public boolean inBusyCrosswalk() {

		Rectangle me = new Rectangle(xPos+1, yPos, 25, 25);
		if (Phonebook.getPhonebook().crosswalk3.getCrosswalk().intersects(me)) {
			if (Phonebook.getPhonebook().crosswalk3.isCrosswalkBusy() == true &&
					!(state == BusState.inCrosswalk3)) {
				return true;
			}
			return false;
		}

		me.setLocation(xPos+1, yPos);
		if (Phonebook.getPhonebook().crosswalk4.getCrosswalk().intersects(me)) {
			if (Phonebook.getPhonebook().crosswalk4.isCrosswalkBusy() == true &&
					!(state == BusState.inCrosswalk4)) {
				return true;
			}
			return false;
		}

		me.setLocation(xPos+1, yPos);
		if (Phonebook.getPhonebook().crosswalk5.getCrosswalk().intersects(me)) {
			if (Phonebook.getPhonebook().crosswalk5.isCrosswalkBusy() == true &&
					!(state == BusState.inCrosswalk5)) {
				return true;
			}
			return false;
		}

		me.setLocation(xPos-1, yPos);
		if (Phonebook.getPhonebook().crosswalk8.getCrosswalk().intersects(me)) {
			if (Phonebook.getPhonebook().crosswalk8.isCrosswalkBusy() == true &&
					!(state == BusState.inCrosswalk8)) {
				return true;
			}
			return false;
		}

		me.setLocation(xPos-1, yPos);
		if (Phonebook.getPhonebook().crosswalk9.getCrosswalk().intersects(me)) {
			if (Phonebook.getPhonebook().crosswalk9.isCrosswalkBusy() == true &&
					!(state == BusState.inCrosswalk9)) {
				return true;
			}
			return false;
		}

		me.setLocation(xPos-1, yPos);
		if (Phonebook.getPhonebook().crosswalk10.getCrosswalk().intersects(me)) {
			if (Phonebook.getPhonebook().crosswalk10.isCrosswalkBusy() == true &&
					!(state == BusState.inCrosswalk10)) {
				return true;
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

		if (Phonebook.getPhonebook().crosswalk3.getCrosswalk().intersects(me) &&
				!(state == BusState.inCrosswalk3)) {
			Phonebook.getPhonebook().crosswalk3.setCrosswalkBusy(true);	
			state = BusState.inCrosswalk3;
		}
		else if (Phonebook.getPhonebook().crosswalk4.getCrosswalk().intersects(me) &&
				!(state == BusState.inCrosswalk4)) {
			Phonebook.getPhonebook().crosswalk4.setCrosswalkBusy(true);	
			state = BusState.inCrosswalk4;
		}
		else if (Phonebook.getPhonebook().crosswalk5.getCrosswalk().intersects(me) &&
				!(state == BusState.inCrosswalk5)) {
			Phonebook.getPhonebook().crosswalk5.setCrosswalkBusy(true);	
			state = BusState.inCrosswalk5;
		}
		else if (Phonebook.getPhonebook().crosswalk8.getCrosswalk().intersects(me) &&
				!(state == BusState.inCrosswalk8)) {
			Phonebook.getPhonebook().crosswalk8.setCrosswalkBusy(true);	
			state = BusState.inCrosswalk8;
		}
		else if (Phonebook.getPhonebook().crosswalk9.getCrosswalk().intersects(me) &&
				!(state == BusState.inCrosswalk9)) {
			Phonebook.getPhonebook().crosswalk9.setCrosswalkBusy(true);	
			state = BusState.inCrosswalk9;
		}
		else if (Phonebook.getPhonebook().crosswalk10.getCrosswalk().intersects(me) &&
				!(state == BusState.inCrosswalk10)) {
			Phonebook.getPhonebook().crosswalk10.setCrosswalkBusy(true);	
			state = BusState.inCrosswalk10;
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

		if (!Phonebook.getPhonebook().crosswalk3.getCrosswalk().intersects(me)
				&& (state == BusState.inCrosswalk3)) {
			Phonebook.getPhonebook().crosswalk3.setCrosswalkBusy(false);	
			state = BusState.enroute;	
		}
		else if (!Phonebook.getPhonebook().crosswalk4.getCrosswalk().intersects(me)
				&& (state == BusState.inCrosswalk4)) {
			Phonebook.getPhonebook().crosswalk4.setCrosswalkBusy(false);	
			state = BusState.enroute;	
		}
		else if (!Phonebook.getPhonebook().crosswalk5.getCrosswalk().intersects(me)
				&& (state == BusState.inCrosswalk5)) {
			Phonebook.getPhonebook().crosswalk5.setCrosswalkBusy(false);	
			state = BusState.enroute;	
		}
		else if (!Phonebook.getPhonebook().crosswalk8.getCrosswalk().intersects(me)
				&& (state == BusState.inCrosswalk8)) {
			Phonebook.getPhonebook().crosswalk8.setCrosswalkBusy(false);	
			state = BusState.enroute;	
		}
		else if (!Phonebook.getPhonebook().crosswalk9.getCrosswalk().intersects(me)
				&& (state == BusState.inCrosswalk9)) {
			Phonebook.getPhonebook().crosswalk9.setCrosswalkBusy(false);	
			state = BusState.enroute;	
		}
		else if (!Phonebook.getPhonebook().crosswalk10.getCrosswalk().intersects(me)
				&& (state == BusState.inCrosswalk10)) {
			Phonebook.getPhonebook().crosswalk10.setCrosswalkBusy(false);	
			state = BusState.enroute;	
		}
	}
}
