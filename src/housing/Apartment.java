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
	
	public boolean arrived(HousingResidentRole HRR) {
		HousingResidentGui HRG = new HousingResidentGui(HRR);
		HRR.setGui(HRG);
		apartmentPanel.addGui(HRG);
		return false;
	}
	
	public void House(HousingResidentRole HRR) {
		HousingResidentGui HRG = new HousingResidentGui(HRR);
		aptUnits = apartmentPanel.getAptUnits();
		for(Housing house : aptUnits) {
			if(house.getOccupantName().equals(HRR.getName())) {
				house.getPanel().addGui(HRG);
			}
		}
	}
	
	public void setBuildingPanel(BuildingPanel myBuildingPanel) {
		apartmentPanel = (ApartmentPanel)myBuildingPanel;
	}
	
	public ApartmentPanel getPanel() {
		return apartmentPanel;
	}
}
