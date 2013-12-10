package application.gui.animation.agentGui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import application.Phonebook;

public class CopGui extends VehicleHorizontalGui {

	ImageIcon carLeft = new ImageIcon("res/CopCarLeft.png");
	ImageIcon carRight = new ImageIcon("res/CopCarRight.png");

	private final int TopRoadY = 75;
	private final int BottomRoadY = 195;

	public CopGui() {
		xPos = 600;
		yPos = BottomRoadY;
		xDestination = -25;
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
		if (xPos%20 == 0 || xPos%20 == 1 || xPos%20 == 2 ||
				xPos%20 == 3 || xPos%20 == 4 || xPos%20 == 5 ||
				xPos%20 == 6 || xPos%20 == 7 || xPos%20 == 8 ||
				xPos%20 == 9 || xPos%20 == 10) {
			g.setColor(Color.RED);
			g.fillRect(xPos+18, yPos-3, 10, 5);
			g.setColor(Color.BLUE);
			g.fillRect(xPos+28, yPos-3, 10, 5);
		}
		else {
			g.setColor(Color.BLUE);
			g.fillRect(xPos+18, yPos-3, 10, 5);
			g.setColor(Color.RED);
			g.fillRect(xPos+28, yPos-3, 10, 5);
		}
			
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
			xDestination = -25;
		}
		else {
			yPos = TopRoadY;
			xDestination = 600;
		}
	}
	
	synchronized public boolean inBusyIntersection() {
		Rectangle me = new Rectangle(xPos+1, yPos, 25, 25);
		
		if (Phonebook.getPhonebook().intersection1.getIntersection().intersects(me)) {
			if (Phonebook.getPhonebook().intersection1.isIntersectionBusy() == true &&
					!(state == VehicleState.inIntersection1)) {
				return  true;
			}
			return false;
		}
		
		me.setLocation(xPos+1, yPos);
		if (Phonebook.getPhonebook().intersection2.getIntersection().intersects(me)) {
			if (Phonebook.getPhonebook().intersection2.isIntersectionBusy() == true &&
					!(state == VehicleState.inIntersection2)) {
				return  true;
			}
			return false;
		}
		
		me.setLocation(xPos-1, yPos);
		if (Phonebook.getPhonebook().intersection3.getIntersection().intersects(me)) {
			if (Phonebook.getPhonebook().intersection3.isIntersectionBusy() == true &&
					!(state == VehicleState.inIntersection3)) {
				return  true;
			}
			return false;
		}
		
		me.setLocation(xPos-1, yPos);
		if (Phonebook.getPhonebook().intersection4.getIntersection().intersects(me)) {
			if (Phonebook.getPhonebook().intersection4.isIntersectionBusy() == true &&
					!(state == VehicleState.inIntersection4)) {
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
					!(state == VehicleState.inCrosswalk3)) {
				return true;
			}
			return false;
		}
		
		me.setLocation(xPos+1, yPos);
		if (Phonebook.getPhonebook().crosswalk4.getCrosswalk().intersects(me)) {
			if (Phonebook.getPhonebook().crosswalk4.isCrosswalkBusy() == true &&
					!(state == VehicleState.inCrosswalk4)) {
				return true;
			}
			return false;
		}
		
		me.setLocation(xPos+1, yPos);
		if (Phonebook.getPhonebook().crosswalk5.getCrosswalk().intersects(me)) {
			if (Phonebook.getPhonebook().crosswalk5.isCrosswalkBusy() == true &&
					!(state == VehicleState.inCrosswalk5)) {
				return true;
			}
			return false;
		}
		
		me.setLocation(xPos-1, yPos);
		if (Phonebook.getPhonebook().crosswalk8.getCrosswalk().intersects(me)) {
			if (Phonebook.getPhonebook().crosswalk8.isCrosswalkBusy() == true &&
					!(state == VehicleState.inCrosswalk8)) {
				return true;
			}
			return false;
		}
		
		me.setLocation(xPos-1, yPos);
		if (Phonebook.getPhonebook().crosswalk9.getCrosswalk().intersects(me)) {
			if (Phonebook.getPhonebook().crosswalk9.isCrosswalkBusy() == true &&
					!(state == VehicleState.inCrosswalk9)) {
				return true;
			}
			return false;
		}
		
		me.setLocation(xPos-1, yPos);
		if (Phonebook.getPhonebook().crosswalk10.getCrosswalk().intersects(me)) {
			if (Phonebook.getPhonebook().crosswalk10.isCrosswalkBusy() == true &&
					!(state == VehicleState.inCrosswalk10)) {
				return true;
			}
			return false;
		}
		else {
			return false;
		}
	}
}
