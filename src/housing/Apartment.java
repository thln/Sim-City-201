package housing;

import java.awt.*;
import java.util.List;
import java.util.*;

import application.gui.animation.*;
import application.gui.animation.agentGui.*;


public class Apartment {
	
	private ApartmentPanel apartmentPanel;
	String name;
	List<Housing> aptUnits = new ArrayList<Housing>();
	
	public Apartment(String name) {
		this.name = name;
	}
	
	public boolean arrived(ApartmentResidentRole ARR) {
		if(ARR instanceof ApartmentResidentRole) {
			ARR.setApartment(this);
			aptUnits = apartmentPanel.getAptUnits();
			for(int i=0;i<aptUnits.size();i++) {
				if(aptUnits.get(i).getOccupantName().equals(ARR.getName())) {
					ARR.setUnit(i, aptUnits.get(i));
				}
			}
			ApartmentResidentGui ARG = new ApartmentResidentGui(ARR);
			ARR.setGui(ARG);
			apartmentPanel.addGui(ARG);
			return true;
		}
		return false;
	}
	
	
	public void enterHouse(ApartmentResidentRole ARR) {
		
	}
	
	public void setBuildingPanel(BuildingPanel myBuildingPanel) {
		apartmentPanel = (ApartmentPanel)myBuildingPanel;
	}
	
	public ApartmentPanel getPanel() {
		return apartmentPanel;
	}
}
