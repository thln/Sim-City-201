package application.gui.animation;

import java.awt.geom.Rectangle2D;

import javax.swing.*;

public class Building extends JButton {
	//Previously extends JLabel
	
	BuildingPanel myBuildingPanel;
	ImageIcon myImage;
	private String name;
	
	public Building(String name) {
		super(name);
	}
	
	public void displayBuilding() {
		myBuildingPanel.displayBuildingPanel();
	}
	
	public BuildingPanel getBuildingPanel() {
		return myBuildingPanel;
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
