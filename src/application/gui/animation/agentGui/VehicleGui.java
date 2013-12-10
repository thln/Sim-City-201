package application.gui.animation.agentGui;

import java.awt.Rectangle;

import agent.Agent;
import application.Phonebook;

public abstract class VehicleGui extends CityGui {

	protected Agent agent = null;
	protected boolean isPresent = true;

	protected int xPos, yPos;
	protected int xDestination, yDestination;

	protected enum Command {noCommand,
		block1, block2, block3, block4, block5, block6, block7, block8, block9, block10};
	protected Command command = Command.noCommand;

	protected enum VehicleState {stopped, enroute,
		inIntersection1, inIntersection2, inIntersection3, inIntersection4,
		inCrosswalk1, inCrosswalk2, inCrosswalk3, inCrosswalk4, inCrosswalk5,inCrosswalk6, inCrosswalk7, inCrosswalk8, inCrosswalk9, inCrosswalk10, inCrosswalk11, inCrosswalk12,
		inBusParking1H,inBusParking2H,inBusParking3H,inBusParking4H,inBusParking1V,inBusParking2V,inBusParking3V, inBusParking4V};
	VehicleState state = VehicleState.stopped;


	public VehicleGui(){

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


	synchronized public void inAnIntersection() {
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

	synchronized public void leftAnIntersection() {
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
	
	
	public void inBusParking() {
		System.err.println(this+"I'm in bus parking");
		Rectangle me = new Rectangle(xPos, yPos, 25, 25);

		if (Phonebook.getPhonebook().busParking1H.getBusParking().intersects(me) &&
				!(state == VehicleState.inBusParking1H)) {
			Phonebook.getPhonebook().busParking1H.setBusParkingBusy(true);	
			state = VehicleState.inBusParking1H;
		}
		else if (Phonebook.getPhonebook().busParking2H.getBusParking().intersects(me) &&
				!(state == VehicleState.inBusParking2H)) {
			Phonebook.getPhonebook().busParking2H.setBusParkingBusy(true);	
			state = VehicleState.inBusParking2H;
		}	
		else if (Phonebook.getPhonebook().busParking3H.getBusParking().intersects(me) &&
				!(state == VehicleState.inBusParking3H)) {
			Phonebook.getPhonebook().busParking3H.setBusParkingBusy(true);	
			state = VehicleState.inBusParking3H;
		}
		else if (Phonebook.getPhonebook().busParking4H.getBusParking().intersects(me) &&
				!(state == VehicleState.inBusParking4H)) {
			Phonebook.getPhonebook().busParking4H.setBusParkingBusy(true);	
			state = VehicleState.inBusParking4H;
		}
		else if (Phonebook.getPhonebook().busParking1V.getBusParking().intersects(me) &&
				!(state == VehicleState.inBusParking1V)) {
			Phonebook.getPhonebook().busParking1V.setBusParkingBusy(true);	
			state = VehicleState.inBusParking1V;
		}
		else if (Phonebook.getPhonebook().busParking2V.getBusParking().intersects(me) &&
				!(state == VehicleState.inBusParking2V)) {
			Phonebook.getPhonebook().busParking2V.setBusParkingBusy(true);	
			state = VehicleState.inBusParking2V;
		}	
		else if (Phonebook.getPhonebook().busParking3V.getBusParking().intersects(me) &&
				!(state == VehicleState.inBusParking3V)) {
			Phonebook.getPhonebook().busParking3V.setBusParkingBusy(true);	
			state = VehicleState.inBusParking3V;
		}
		else if (Phonebook.getPhonebook().busParking4V.getBusParking().intersects(me) &&
				!(state == VehicleState.inBusParking4V)) {
			Phonebook.getPhonebook().busParking4V.setBusParkingBusy(true);	
			state = VehicleState.inBusParking4V;
		}
	}

	public void leftBusParking() {
		System.err.println(this+ "I'm leaving bus parking");
		Rectangle me = new Rectangle(xPos, yPos, 25, 25);

		if (!Phonebook.getPhonebook().busParking1H.getBusParking().intersects(me)
				&& (state == VehicleState.inBusParking1H)) {
			Phonebook.getPhonebook().busParking1H.setBusParkingBusy(false);	
			state = VehicleState.enroute;	
		}
		else if (!Phonebook.getPhonebook().busParking2H.getBusParking().intersects(me)
				&& (state == VehicleState.inBusParking2H)) {
			Phonebook.getPhonebook().busParking2H.setBusParkingBusy(false);	
			state = VehicleState.enroute;	
		}
		else if (!Phonebook.getPhonebook().busParking3H.getBusParking().intersects(me)
				&& (state == VehicleState.inBusParking3H)) {
			Phonebook.getPhonebook().busParking3H.setBusParkingBusy(false);	
			state = VehicleState.enroute;
		}
		else if (!Phonebook.getPhonebook().busParking4H.getBusParking().intersects(me)
				&& (state == VehicleState.inBusParking4H)) {
			Phonebook.getPhonebook().busParking4H.setBusParkingBusy(false);	
			state = VehicleState.enroute;	
		}
		else if (!Phonebook.getPhonebook().busParking1V.getBusParking().intersects(me)
				&& (state == VehicleState.inBusParking1V)) {
			Phonebook.getPhonebook().busParking1V.setBusParkingBusy(false);	
			state = VehicleState.enroute;	
		}
		else if (!Phonebook.getPhonebook().busParking2V.getBusParking().intersects(me)
				&& (state == VehicleState.inBusParking2V)) {
			Phonebook.getPhonebook().busParking2V.setBusParkingBusy(false);	
			state = VehicleState.enroute;	
		}
		else if (!Phonebook.getPhonebook().busParking3V.getBusParking().intersects(me)
				&& (state == VehicleState.inBusParking3V)) {
			Phonebook.getPhonebook().busParking3V.setBusParkingBusy(false);	
			state = VehicleState.enroute;
		}
		else if (!Phonebook.getPhonebook().busParking4V.getBusParking().intersects(me)
				&& (state == VehicleState.inBusParking4V)) {
			Phonebook.getPhonebook().busParking4H.setBusParkingBusy(false);	
			state = VehicleState.enroute;	
		}
	}
}
