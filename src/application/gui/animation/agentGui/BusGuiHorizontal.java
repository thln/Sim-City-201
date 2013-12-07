package application.gui.animation.agentGui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

import application.Phonebook;

public class BusGuiHorizontal extends CityGui {

	//	private Bus agent = null;
	private boolean isPresent = true;
	
	private final int stopLeftX = (int) Phonebook.getPhonebook().getBusStops().get(0).getX();
	private final int stopRightX = (int) Phonebook.getPhonebook().getBusStops().get(1).getX();;
	private final int stopTopY = 75;
	private final int stopBottomY = 195;
	
	private final int waitTime = 1000;

	private int xPos = -25, yPos = stopTopY;//default bus position
	private int xDestination = stopLeftX;//Stop 1

	private enum Command {noCommand, stop1, stop2, stop3, stop4};
	private Command command = Command.stop1;

	private enum BusState {stopped, enroute};
	BusState state = BusState.stopped;

	private Timer busStop = new Timer();
	private Semaphore wait = new Semaphore(0, true);

	public BusGuiHorizontal(){
	}

	public void updatePosition() {
		if (xPos < xDestination)
			xPos++;
		else if (xPos > xDestination)
			xPos--;

		//		if (yPos < yDestination)
		//			yPos++;
		//		else if (yPos > yDestination)
		//			yPos--;

		if (xPos == 600 || xPos == -25) {
			changeRoads();
		}

		if (xPos == xDestination) {
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
						goToEndOfTopRoad();
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
						goToEndOfBottomRoad();
					}
				},
				waitTime);
			}
		}
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillOval(xPos, yPos, 25, 25); //keeping it uniform for now
	}

	//Actions
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

}
