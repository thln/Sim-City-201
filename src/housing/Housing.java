package housing;

import application.gui.animation.*;
import person.Person;

public class Housing 
{
	//public enum differentHousingTypes {Apartment, Park, Mansion};
	//public static differentHousingTypes housingStructure;
	public String type;
	public enum housingState {UrgentWorkOrder, CheckUpNeeded, RecentlyChecked, Checking};
	public housingState state;
	public Person occupant;
	int housingNumber;
	int xCordinate;
	int yCordinate;
	private HousingPanel housingPanel;
	
	public Housing()
	{
		state = housingState.CheckUpNeeded;
	}
	
	public Housing(Person newP, int Address, String type)
	{
		occupant = newP;
		housingNumber = Address;
		state = housingState.CheckUpNeeded;
		this.type = type;
		/*
		if(type.toLowerCase().contains(("apartment")))
		{
			//housingStructure = differentHousingTypes.Apartment;
			type = "Apartment";
			//some X and Y coordinate stuff
		}
		else if(type.toLowerCase().contains(("park")))
		{
			//housingStructure = differentHousingTypes.Park;
			type = "Park";
			//some X and Y Coordinate stuff
		}
		else if(type.toLowerCase().contains(("mansion")))
		{
			//housingStructure = differentHousingTypes.Mansion;
			type = "Mansion";
			//some X and Y Coordinate stuff
		}
		*/
		
	}
	
	public int getHousingNumber()
	{
		return housingNumber;
	}
	
	public int getXCordinate()
	{
		return xCordinate;
	}
	
	public int getYCordinate()
	{
		return yCordinate;
	}
	
	public String getOccupantName()
	{
		return occupant.getName();
	}
	
	public void setBuildingPanel(HousingPanel myBuildingPanel) {
		housingPanel = myBuildingPanel;
	}
	
	public HousingPanel getPanel() {
		return housingPanel;
	}
}
