package housing;

import person.Person;

public class Housing 
{
	//public enum differentHousingTypes {Apartment, Park, Mansion};
	//public static differentHousingTypes housingStructure;
	public String structure;
	public enum housingState {UrgentWorkOrder, CheckUpNeeded, RecentlyChecked, Checking};
	public housingState state;
	public Person occupant;
	int housingNumber;
	int xCordinate;
	int yCordinate;
	
	public Housing()
	{
		state = housingState.CheckUpNeeded;
	}
	
	public Housing(Person newP, int Address, String type)
	{
		occupant = newP;
		housingNumber = Address;
		state = housingState.CheckUpNeeded;
		
		if(type.toLowerCase().contains(("apartment")))
		{
			//housingStructure = differentHousingTypes.Apartment;
			structure = "Apartment";
			//some X and Y coordinate stuff
		}
		else if(type.toLowerCase().contains(("park")))
		{
			//housingStructure = differentHousingTypes.Park;
			structure = "Park";
			//some X and Y Coordinate stuff
		}
		else if(type.toLowerCase().contains(("mansion")))
		{
			//housingStructure = differentHousingTypes.Mansion;
			structure = "Mansion";
			//some X and Y Coordinate stuff
		}
		
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
}
