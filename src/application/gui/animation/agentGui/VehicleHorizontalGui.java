package application.gui.animation.agentGui;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import application.Phonebook;

public class VehicleHorizontalGui extends VehicleGui {

	ImageIcon carLeft = new ImageIcon("res/CarLeft.png");
	ImageIcon carRight = new ImageIcon("res/CarRight.png");

	private final int TopRoadY = 75;
	private final int BottomRoadY = 195;
	
	public VehicleHorizontalGui() {
		xPos = 1000;
		yPos = TopRoadY;
		xDestination = 600;
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
			if (command == Command.block1) {
				//Do something
			}
		}
	}
	
	public void draw(Graphics2D g) {
		if (yPos == TopRoadY) {
			g.drawImage(carRight.getImage(), xPos, yPos, null);
		}
		else if (yPos == BottomRoadY) {
			g.drawImage(carLeft.getImage(), xPos, yPos, null);
		}
	}
	
	public void changeRoads() {
		if (xDestination == 600) {
			yPos = BottomRoadY;
			xDestination = 0;
		}
		else {
			yPos = TopRoadY;
			xDestination = 600;
		}
	}
}
