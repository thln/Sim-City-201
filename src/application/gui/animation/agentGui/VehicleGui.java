package application.gui.animation.agentGui;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import agent.Agent;
import application.Phonebook;

public abstract class VehicleGui {

	private Agent agent = null;
	private boolean isPresent = true;

	private int xPos, yPos;//default bus position
	private int xDestination, yDestination;

	private enum Command {noCommand,
		block1, block2, block3, block4, block5, block6, block7, block8, block9, block10};
	private Command command = Command.noCommand;

	private enum VehicleState {stopped, enroute,
		inIntersection1, inIntersection2, inIntersection3, inIntersection4,
		inCrosswalk1, inCrosswalk2, inCrosswalk3, inCrosswalk4, inCrosswalk5,inCrosswalk6, inCrosswalk7, inCrosswalk8, inCrosswalk9, inCrosswalk10, inCrosswalk11, inCrosswalk12};
	VehicleState state = VehicleState.stopped;


	public VehicleGui(){

	}

	public void updatePosition() {
	}

	public void draw(Graphics2D g) {
	}

	//Actions
	public void goToEndOfTopRoad() {
		xDestination = 600;
	}

	public void goToEndOfBottomRoad() {
		xDestination = -25;
	}
	
	public void goToEndOfLeftRoad() {
		yDestination = -25;
	}

	public void goToEndOfRightRoad() {
		yDestination = 325;
	}

	public void changeRoads() {
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

	public void inAnIntersection() {
		Rectangle me = new Rectangle(xPos, yPos, 25, 25);

		if (Phonebook.getPhonebook().intersection1.getIntersection().intersects(me) &&
				!(state == VehicleState.inIntersection1)) {
			Phonebook.getPhonebook().intersection1.setIntersectionBusy(true);	
			state = VehicleState.inIntersection1;
		}
		else if (Phonebook.getPhonebook().intersection2.getIntersection().intersects(me) &&
				!(state == VehicleState.inIntersection2)) {
			Phonebook.getPhonebook().intersection2.setIntersectionBusy(true);	
			state = VehicleState.inIntersection2;
		}
		else if (Phonebook.getPhonebook().intersection3.getIntersection().intersects(me) &&
				!(state == VehicleState.inIntersection3)) {
			Phonebook.getPhonebook().intersection3.setIntersectionBusy(true);	
			state = VehicleState.inIntersection3;
		}
		else if (Phonebook.getPhonebook().intersection4.getIntersection().intersects(me) &&
				!(state == VehicleState.inIntersection4)) {
			Phonebook.getPhonebook().intersection4.setIntersectionBusy(true);	
			state = VehicleState.inIntersection4;			
		}
	}

	public void leftAnIntersection() {
		Rectangle me = new Rectangle(xPos, yPos, 25, 25);

		if (!Phonebook.getPhonebook().intersection1.getIntersection().intersects(me)
				&& (state == VehicleState.inIntersection1)) {
			Phonebook.getPhonebook().intersection1.setIntersectionBusy(false);	
			state = VehicleState.enroute;	
		}
		else if (!Phonebook.getPhonebook().intersection2.getIntersection().intersects(me)
				&& (state == VehicleState.inIntersection2)) {
			Phonebook.getPhonebook().intersection2.setIntersectionBusy(false);	
			state = VehicleState.enroute;	
		}
		else if (!Phonebook.getPhonebook().intersection3.getIntersection().intersects(me) 
				&& (state == VehicleState.inIntersection3)) {
			Phonebook.getPhonebook().intersection3.setIntersectionBusy(false);	
			state = VehicleState.enroute;	
		}
		else if (!Phonebook.getPhonebook().intersection4.getIntersection().intersects(me)
				&& (state == VehicleState.inIntersection4)) {
			Phonebook.getPhonebook().intersection4.setIntersectionBusy(false);	
			state = VehicleState.enroute;	
		}
	}

	synchronized public boolean inBusyCrosswalk() {
		//Horizontal Crosswalks
		Rectangle meLeft = new Rectangle(xPos+1, yPos, 25, 25);
		Rectangle meRight = new Rectangle(xPos-1, yPos, 25, 25);

		if (Phonebook.getPhonebook().crosswalk1.getCrosswalk().intersects(meLeft) ||
				Phonebook.getPhonebook().crosswalk1.getCrosswalk().intersects(meRight)) {
			if (Phonebook.getPhonebook().crosswalk1.isCrosswalkBusy() == true &&
					!(state == VehicleState.inCrosswalk1)) {
				return true;
			}
			return false;
		}

		if (Phonebook.getPhonebook().crosswalk2.getCrosswalk().intersects(meLeft) ||
				Phonebook.getPhonebook().crosswalk2.getCrosswalk().intersects(meRight)) {
			if (Phonebook.getPhonebook().crosswalk2.isCrosswalkBusy() == true &&
					!(state == VehicleState.inCrosswalk2)) {
				return true;
			}
			return false;
		}
		if (Phonebook.getPhonebook().crosswalk6.getCrosswalk().intersects(meLeft) ||
				Phonebook.getPhonebook().crosswalk6.getCrosswalk().intersects(meRight)) {
			if (Phonebook.getPhonebook().crosswalk6.isCrosswalkBusy() == true &&
					!(state == VehicleState.inCrosswalk6)) {
				return  true;
			}
			return false;
		}

		if (Phonebook.getPhonebook().crosswalk7.getCrosswalk().intersects(meLeft) ||
				Phonebook.getPhonebook().crosswalk7.getCrosswalk().intersects(meRight)) {
			if (Phonebook.getPhonebook().crosswalk7.isCrosswalkBusy() == true &&
					!(state == VehicleState.inCrosswalk7)) {
				return  true;
			}
			return false;
		}

		if (Phonebook.getPhonebook().crosswalk11.getCrosswalk().intersects(meLeft) ||
				Phonebook.getPhonebook().crosswalk11.getCrosswalk().intersects(meRight)) {
			if (Phonebook.getPhonebook().crosswalk11.isCrosswalkBusy() == true &&
					!(state == VehicleState.inCrosswalk11)) {
				return  true;
			}
			return false;
		}

		if (Phonebook.getPhonebook().crosswalk12.getCrosswalk().intersects(meLeft) ||
				Phonebook.getPhonebook().crosswalk12.getCrosswalk().intersects(meRight)) {
			if (Phonebook.getPhonebook().crosswalk12.isCrosswalkBusy() == true &&
					!(state == VehicleState.inCrosswalk12)) {
				return  true;
			}
			return false;
		}

		//Vertical Crosswalks
		Rectangle meUp = new Rectangle(xPos, yPos-1, 25, 25);
		Rectangle meDown = new Rectangle(xPos, yPos+1, 25, 25);
		if (Phonebook.getPhonebook().crosswalk3.getCrosswalk().intersects(meUp) ||
				Phonebook.getPhonebook().crosswalk3.getCrosswalk().intersects(meDown)) {
			if (Phonebook.getPhonebook().crosswalk3.isCrosswalkBusy() == true &&
					!(state == VehicleState.inCrosswalk3)) {
				return true;
			}
			return false;
		}

		if (Phonebook.getPhonebook().crosswalk4.getCrosswalk().intersects(meUp) ||
				Phonebook.getPhonebook().crosswalk4.getCrosswalk().intersects(meDown)) {
			if (Phonebook.getPhonebook().crosswalk4.isCrosswalkBusy() == true &&
					!(state == VehicleState.inCrosswalk4)) {
				return true;
			}
			return false;
		}

		if (Phonebook.getPhonebook().crosswalk5.getCrosswalk().intersects(meUp) ||
				Phonebook.getPhonebook().crosswalk5.getCrosswalk().intersects(meDown)) {
			if (Phonebook.getPhonebook().crosswalk5.isCrosswalkBusy() == true &&
					!(state == VehicleState.inCrosswalk5)) {
				return true;
			}
			return false;
		}

		if (Phonebook.getPhonebook().crosswalk8.getCrosswalk().intersects(meUp) ||
				Phonebook.getPhonebook().crosswalk8.getCrosswalk().intersects(meDown)) {
			if (Phonebook.getPhonebook().crosswalk8.isCrosswalkBusy() == true &&
					!(state == VehicleState.inCrosswalk8)) {
				return true;
			}
			return false;
		}

		if (Phonebook.getPhonebook().crosswalk9.getCrosswalk().intersects(meUp) ||
				Phonebook.getPhonebook().crosswalk9.getCrosswalk().intersects(meDown)) {
			if (Phonebook.getPhonebook().crosswalk9.isCrosswalkBusy() == true &&
					!(state == VehicleState.inCrosswalk9)) {
				return true;
			}
			return false;
		}

		if (Phonebook.getPhonebook().crosswalk10.getCrosswalk().intersects(meUp) ||
				Phonebook.getPhonebook().crosswalk10.getCrosswalk().intersects(meDown)) {
			if (Phonebook.getPhonebook().crosswalk10.isCrosswalkBusy() == true &&
					!(state == VehicleState.inCrosswalk10)) {
				return true;
			}
			return false;
		}
		return false;
	}

	public void inACrosswalk() {
		Rectangle me = new Rectangle(xPos, yPos, 25, 25);

		if (Phonebook.getPhonebook().crosswalk1.getCrosswalk().intersects(me) &&
				!(state == VehicleState.inCrosswalk1)) {
			Phonebook.getPhonebook().crosswalk1.setCrosswalkBusy(true);	
			state = VehicleState.inCrosswalk1;
		}
		else if (Phonebook.getPhonebook().crosswalk2.getCrosswalk().intersects(me) &&
				!(state == VehicleState.inCrosswalk2)) {
			Phonebook.getPhonebook().crosswalk2.setCrosswalkBusy(true);	
			state = VehicleState.inCrosswalk2;
		}	
		else if (Phonebook.getPhonebook().crosswalk3.getCrosswalk().intersects(me) &&
				!(state == VehicleState.inCrosswalk3)) {
			Phonebook.getPhonebook().crosswalk3.setCrosswalkBusy(true);	
			state = VehicleState.inCrosswalk3;
		}
		else if (Phonebook.getPhonebook().crosswalk4.getCrosswalk().intersects(me) &&
				!(state == VehicleState.inCrosswalk4)) {
			Phonebook.getPhonebook().crosswalk4.setCrosswalkBusy(true);	
			state = VehicleState.inCrosswalk4;
		}
		else if (Phonebook.getPhonebook().crosswalk5.getCrosswalk().intersects(me) &&
				!(state == VehicleState.inCrosswalk5)) {
			Phonebook.getPhonebook().crosswalk5.setCrosswalkBusy(true);	
			state = VehicleState.inCrosswalk5;
		}
		else if (Phonebook.getPhonebook().crosswalk6.getCrosswalk().intersects(me) &&
				!(state == VehicleState.inCrosswalk6)) {
			Phonebook.getPhonebook().crosswalk6.setCrosswalkBusy(true);	
			state = VehicleState.inCrosswalk6;
		}
		else if (Phonebook.getPhonebook().crosswalk7.getCrosswalk().intersects(me) &&
				!(state == VehicleState.inCrosswalk7)) {
			Phonebook.getPhonebook().crosswalk7.setCrosswalkBusy(true);	
			state = VehicleState.inCrosswalk7;
		}
		else if (Phonebook.getPhonebook().crosswalk8.getCrosswalk().intersects(me) &&
				!(state == VehicleState.inCrosswalk8)) {
			Phonebook.getPhonebook().crosswalk8.setCrosswalkBusy(true);	
			state = VehicleState.inCrosswalk8;
		}
		else if (Phonebook.getPhonebook().crosswalk9.getCrosswalk().intersects(me) &&
				!(state == VehicleState.inCrosswalk9)) {
			Phonebook.getPhonebook().crosswalk9.setCrosswalkBusy(true);	
			state = VehicleState.inCrosswalk9;
		}
		else if (Phonebook.getPhonebook().crosswalk10.getCrosswalk().intersects(me) &&
				!(state == VehicleState.inCrosswalk10)) {
			Phonebook.getPhonebook().crosswalk10.setCrosswalkBusy(true);	
			state = VehicleState.inCrosswalk10;
		}
		else if (Phonebook.getPhonebook().crosswalk11.getCrosswalk().intersects(me) &&
				!(state == VehicleState.inCrosswalk11)) {
			Phonebook.getPhonebook().crosswalk11.setCrosswalkBusy(true);	
			state = VehicleState.inCrosswalk11;
		}
		else if (Phonebook.getPhonebook().crosswalk12.getCrosswalk().intersects(me) &&
				!(state == VehicleState.inCrosswalk12)) {
			Phonebook.getPhonebook().crosswalk12.setCrosswalkBusy(true);	
			state = VehicleState.inCrosswalk12;
		}
	}

	public void leftACrosswalk() {
		Rectangle me = new Rectangle(xPos, yPos, 25, 25);

		if (!Phonebook.getPhonebook().crosswalk1.getCrosswalk().intersects(me)
				&& (state == VehicleState.inCrosswalk1)) {
			Phonebook.getPhonebook().crosswalk1.setCrosswalkBusy(false);	
			state = VehicleState.enroute;	
		}
		else if (!Phonebook.getPhonebook().crosswalk2.getCrosswalk().intersects(me)
				&& (state == VehicleState.inCrosswalk2)) {
			Phonebook.getPhonebook().crosswalk2.setCrosswalkBusy(false);	
			state = VehicleState.enroute;	
		}
		else if (!Phonebook.getPhonebook().crosswalk3.getCrosswalk().intersects(me)
				&& (state == VehicleState.inCrosswalk3)) {
			Phonebook.getPhonebook().crosswalk3.setCrosswalkBusy(false);	
			state = VehicleState.enroute;
		}
		else if (!Phonebook.getPhonebook().crosswalk4.getCrosswalk().intersects(me)
				&& (state == VehicleState.inCrosswalk4)) {
			Phonebook.getPhonebook().crosswalk4.setCrosswalkBusy(false);	
			state = VehicleState.enroute;	
		}
		else if (!Phonebook.getPhonebook().crosswalk5.getCrosswalk().intersects(me)
				&& (state == VehicleState.inCrosswalk5)) {
			Phonebook.getPhonebook().crosswalk5.setCrosswalkBusy(false);	
			state = VehicleState.enroute;	
		}
		else if (!Phonebook.getPhonebook().crosswalk6.getCrosswalk().intersects(me)
				&& (state == VehicleState.inCrosswalk6)) {
			Phonebook.getPhonebook().crosswalk6.setCrosswalkBusy(false);	
			state = VehicleState.enroute;	
		}
		else if (!Phonebook.getPhonebook().crosswalk7.getCrosswalk().intersects(me)
				&& (state == VehicleState.inCrosswalk7)) {
			Phonebook.getPhonebook().crosswalk7.setCrosswalkBusy(false);	
			state = VehicleState.enroute;	
		}
		else if (!Phonebook.getPhonebook().crosswalk8.getCrosswalk().intersects(me)
				&& (state == VehicleState.inCrosswalk8)) {
			Phonebook.getPhonebook().crosswalk8.setCrosswalkBusy(false);	
			state = VehicleState.enroute;	
		}
		else if (!Phonebook.getPhonebook().crosswalk9.getCrosswalk().intersects(me)
				&& (state == VehicleState.inCrosswalk9)) {
			Phonebook.getPhonebook().crosswalk9.setCrosswalkBusy(false);	
			state = VehicleState.enroute;	
		}
		else if (!Phonebook.getPhonebook().crosswalk10.getCrosswalk().intersects(me)
				&& (state == VehicleState.inCrosswalk10)) {
			Phonebook.getPhonebook().crosswalk10.setCrosswalkBusy(false);	
			state = VehicleState.enroute;	
		}
		else if (!Phonebook.getPhonebook().crosswalk11.getCrosswalk().intersects(me)
				&& (state == VehicleState.inCrosswalk11)) {
			Phonebook.getPhonebook().crosswalk11.setCrosswalkBusy(false);	
			state = VehicleState.enroute;	
		}
		else if (!Phonebook.getPhonebook().crosswalk12.getCrosswalk().intersects(me)
				&& (state == VehicleState.inCrosswalk12)) {
			Phonebook.getPhonebook().crosswalk12.setCrosswalkBusy(false);	
			state = VehicleState.enroute;	
		}
	}

}
