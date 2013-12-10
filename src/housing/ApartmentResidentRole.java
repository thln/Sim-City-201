package housing;

import java.util.concurrent.Semaphore;

import person.*;
import application.gui.animation.agentGui.*;

public class ApartmentResidentRole extends HousingResidentRole{
	
	//DATA
	private Apartment apartment;
	private ApartmentResidentGui gui;
	public enum ResidentState {moving, none};
	public ResidentState state = ResidentState.none;
	private Semaphore atDestination = new Semaphore(0, true);
	private Housing myHome;
	int aptUnit;

	public ApartmentResidentRole (Person p1, String pName, String rName) {
		super(p1, pName, rName);
	}
	
	//Messages
	public void msgMoveToUnit() {
		state = ResidentState.moving;
		stateChanged();
	}
	
	public void msgAtDestination() {
		atDestination.release();
	}
	
	//Scheduler

	public boolean pickAndExecuteAnAction () {
		if(state == ResidentState.moving) {
			GoToUnit(aptUnit);
			return true;
		}
		return false;
	}
		
	//ACTIONS
	public void GoToUnit(int unit) {
		gui.DoDoToUnit(unit);
		try {
			this.atDestination.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HousingResidentGui HRR = new HousingResidentGui(this);
		myHome.getPanel().addGui(HRR);
		state = ResidentState.none;
	}
	
	//utilities
	public void setGui (ApartmentResidentGui gui) {
		this.gui = gui;
	}
	
	public ApartmentResidentGui getGui() {
		return gui;
	}
	
	public void setUnit(int aptUnit, Housing home) {
		this.aptUnit = aptUnit;
		this.myHome = home;
	}
	
	public Housing getHome() {
		return this.myHome;
	}
	
	public void setApartment(Apartment apt) {
		this.apartment = apt;
	}
}
