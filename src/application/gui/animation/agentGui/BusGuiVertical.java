package application.gui.animation.agentGui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

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

	private enum BusState {stopped, enroute};
	BusState state = BusState.stopped;

	private Timer busStop = new Timer();

	public BusGuiVertical(){
	}

	public void updatePosition() {
//		if (xPos < xDestination)
//			xPos++;
//		else if (xPos > xDestination)
//			xPos--;

		if (yPos < yDestination)
			yPos++;
		else if (yPos > yDestination)
			yPos--;

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

}
