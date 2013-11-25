package housing;

import person.Person;

public class Housing 
{
	public enum differentHousingTypes {Apartment, Park, Mansion};
	public static differentHousingTypes housingStructure;
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
		
		if(type.equals("Apartment"))
		{
			housingStructure = differentHousingTypes.Apartment;
			//some X and Y coordinate stuff
		}
		else if(type.equals("Park"))
		{
			housingStructure = differentHousingTypes.Park;
			//some X and Y Coordinate stuff
		}
		else if(type.equals("Mansion"))
		{
			housingStructure = differentHousingTypes.Mansion;
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
