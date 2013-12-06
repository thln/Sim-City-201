package application.gui.animation.agentGui;

import transportation.*;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class BusHorizontalGui extends CityGui {

	private Bus agent = null;
	private boolean isPresent = true;
	
	private final int Road1 = 75;
	private final int Road2 = 195;

	private int xPos = 0, yPos = 75;//default bus position
	private int xDestination = 130, yDestination = Road1;//Stop 1
	
	private enum Command {noCommand, stop1, stop2, stop3, stop4};
	private Command command = Command.noCommand;

	private enum BusState {stopped, enroute};
	BusState state = BusState.stopped;
	
	private Timer busStop = new Timer();
	
	public BusHorizontalGui(){
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

		if (xPos == xDestination && yPos == yDestination) {
			if (command == Command.stop1) {
				busStop.schedule(new TimerTask() {
					public void run() {
						GoToStop2();
					}
				},
				2000);
				return;
			}
			if (command == Command.stop2) {
				busStop.schedule(new TimerTask() {
					public void run() {
						GoToStop3();
					}
				},
				2000);
				return;
			}
			if (command == Command.stop3) {
				busStop.schedule(new TimerTask() {
					public void run() {
						GoToStop4();
					}
				},
				2000);
				return;
			}
			if (command == Command.stop4) {
				busStop.schedule(new TimerTask() {
					public void run() {
						GoToStop1();
					}
				},
				2000);
				return;
			}
			command = Command.noCommand;
		}
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillOval(xPos, yPos, 20, 20); //keeping it uniform for now
	}

    //Actions
    public void GoToStop1() {
    	xDestination = 130;
    	yDestination = Road1;
    	command = Command.stop1;
    }
    
    public void GoToStop2() {
    	xDestination = 425;
    	yDestination = Road1;
    	command = Command.stop2;
    }
    
    public void GoToStop3() {
    	xDestination = 425;
    	yDestination = Road2;
    	command = Command.stop3;
    }
    
    public void GoToStop4() {
    	xDestination = 430;
    	yDestination = Road2;
    	command = Command.stop4;
    }
}
