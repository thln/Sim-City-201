package housing;

import person.*;
import application.gui.animation.agentGui.*;

public class ApartmentResidentRole extends Role{
	
	//DATA
	private ApartmentResidentGui gui;
	public enum ResidentState {moving, none};
	public ResidentState state;
	int apartment;

	public ApartmentResidentRole (Person p1, String pName, String rName) {
		super(p1, pName, rName);
		state = ResidentState.moving;
	}
	
	//Scheduler

	public boolean pickAndExecuteAnAction () {
		if(state == ResidentState.moving) {
			GoToUnit(apartment);
		}
		return false;
	}
		
	//ACTIONS
	public void GoToUnit(int unit) {
		gui.DoDoToUnit(unit);
	}
	
	//utilities
	public void setGui (ApartmentResidentGui gui) {
		this.gui = gui;
	}
	
	public ApartmentResidentGui getGui() {
		return gui;
	}
	
	public void setUnit(int apartment) {
		this.apartment = apartment;
	}
}
