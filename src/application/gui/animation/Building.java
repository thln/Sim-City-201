package application.gui.animation;

import javax.swing.*;

public class Building extends JLabel {
	BuildingPanel myBuildingPanel;
	private String name;
	
	public Building() {
		
	}
	
	public void displayBuilding() {
		myBuildingPanel.displayBuildingPanel();
	}
	
	public void setBuildingPanel( BuildingPanel bp ) {
		myBuildingPanel = bp;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
}
