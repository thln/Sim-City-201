package housing;

import person.*;
import application.gui.animation.agentGui.*;

public class HousingResidentRole extends Role{
	
	//DATA
	private HousingResidentGui gui;

	public HousingResidentRole (Person p1, String pName, String rName) {
		super(p1, pName, rName);
	}
	
	//Scheduler

	public boolean pickAndExecuteAnAction () {
		return false;
	}
		
	//ACTIONS
	
	//utilities
	public void setGui (HousingResidentGui gui) {
		this.gui = gui;
	}
	
	public HousingResidentGui getGui() {
		return gui;
	}
}
