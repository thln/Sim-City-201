package application.gui.animation.agentGui;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import application.Phonebook;

public class VehicleVerticalGui extends VehicleGui {

	ImageIcon carUp = new ImageIcon("res/CarUp.png");
	ImageIcon carDown = new ImageIcon("res/CarDown.png");

	private final int leftRoadX = 168;
	private final int rightRoadX = 386;

	public VehicleVerticalGui() {
		xPos = leftRoadX;
		yPos = 325;
		yDestination = -25;
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
	}

	public void draw(Graphics2D g) {
		if (xPos == leftRoadX) {
			g.drawImage(carUp.getImage(), xPos, yPos, null);
		}
		else if (xPos == rightRoadX) {
			g.drawImage(carDown.getImage(), xPos, yPos, null);
		}
	}

	public void changeRoads() {
		if (yDestination == 325) {
			xPos = leftRoadX;
			yDestination = -25;
		}
		else {
			xPos = rightRoadX;
			yDestination = 325;
		}
	}
	
	synchronized public boolean inBusyIntersection() {

		Rectangle me = new Rectangle(xPos, yPos-1, 25, 25);
		if (Phonebook.getPhonebook().intersection1.getIntersection().intersects(me)) {
			if (Phonebook.getPhonebook().intersection1.isIntersectionBusy() == true &&
					!(state == VehicleState.inIntersection1)) {
				return  true;
			}
			return false;
		}
		
		me.setLocation(xPos, yPos+1);
		if (Phonebook.getPhonebook().intersection2.getIntersection().intersects(me)) {
			if (Phonebook.getPhonebook().intersection2.isIntersectionBusy() == true &&
					!(state == VehicleState.inIntersection2)) {
				return  true;
			}
			return false;
		}
		
		me.setLocation(xPos, yPos-1);
		if (Phonebook.getPhonebook().intersection3.getIntersection().intersects(me)) {
			if (Phonebook.getPhonebook().intersection3.isIntersectionBusy() == true &&
					!(state == VehicleState.inIntersection3)) {
				return  true;
			}
			return false;
		}
		
		me.setLocation(xPos, yPos+1);
		if (Phonebook.getPhonebook().intersection4.getIntersection().intersects(me)) {
			if (Phonebook.getPhonebook().intersection4.isIntersectionBusy() == true &&
					!(state == VehicleState.inIntersection4)) {
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
					!(state == VehicleState.inCrosswalk1)) {
				return true;
			}
			return false;
		}
		
		me.setLocation(xPos, yPos+1);
		if (Phonebook.getPhonebook().crosswalk2.getCrosswalk().intersects(me)) {
			if (Phonebook.getPhonebook().crosswalk2.isCrosswalkBusy() == true &&
					!(state == VehicleState.inCrosswalk2)) {
				return true;
			}
			return false;
		}
		
		me.setLocation(xPos, yPos-1);
		if (Phonebook.getPhonebook().crosswalk6.getCrosswalk().intersects(me)) {
			if (Phonebook.getPhonebook().crosswalk6.isCrosswalkBusy() == true &&
					!(state == VehicleState.inCrosswalk6)) {
				return  true;
			}
			return false;
		}
		
		me.setLocation(xPos, yPos+1);
		if (Phonebook.getPhonebook().crosswalk7.getCrosswalk().intersects(me)) {
			if (Phonebook.getPhonebook().crosswalk7.isCrosswalkBusy() == true &&
					!(state == VehicleState.inCrosswalk7)) {
				return  true;
			}
			return false;
		}
		
		me.setLocation(xPos, yPos-1);
		if (Phonebook.getPhonebook().crosswalk11.getCrosswalk().intersects(me)) {
			if (Phonebook.getPhonebook().crosswalk11.isCrosswalkBusy() == true &&
					!(state == VehicleState.inCrosswalk11)) {
				return  true;
			}
			return false;
		}
		
		me.setLocation(xPos, yPos+1);
		if (Phonebook.getPhonebook().crosswalk12.getCrosswalk().intersects(me)) {
			if (Phonebook.getPhonebook().crosswalk12.isCrosswalkBusy() == true &&
					!(state == VehicleState.inCrosswalk12)) {
				return  true;
			}
			return false;
		}
		else {
			return false;
		}
	}
}
