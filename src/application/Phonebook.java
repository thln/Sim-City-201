package application;

import housing.Housing;
import housing.HousingMaintenanceCompany;
import italianRestaurant.ItalianRestaurant;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import market.Market;
import seafoodRestaurant.SeafoodRestaurant;
import transportation.BusStop;
import americanRestaurant.AmericanRestaurant;
import bank.Bank;
import chineseRestaurant.ChineseRestaurant;


public class Phonebook{

	//Banks
	static private Bank eastBank;
	static private Bank westBank;

	//Markets
	static private Market eastMarket;
	static private Market westMarket;


	//Restaurants
	static private ChineseRestaurant chineseRestaurant;
	static private SeafoodRestaurant seafoodRestaurant;
	//ItalianRestaurant italianRestaurant;
	static private AmericanRestaurant americanRestaurant;
	static private ItalianRestaurant italianRestaurant;
	//MexicanRestaurant mexicanRestaurant;



	private HousingMaintenanceCompany housingMaintenanceCompany;


	private List<Housing> publicAllHousing; //= Collections.synchronizedList(new ArrayList<Housing>());
	private List<Housing> testHousing;// = Collections.synchronizedList(new ArrayList<Housing>());


	//the following 4 lists are for dynamic building and business making in v2

	public class Block {
		int blockNumber;
		List <Integer> neighborBlocks;

		public int doIWalk(int destinationBlock){
			for (int i: neighborBlocks){
				if (destinationBlock == i)
					return i;
			}
			for (int n: neighborBlocks) {
				for (int x: blocks.get(n).neighborBlocks) {
					{
						if (destinationBlock == x)
							return n;
					}
				}
			}
			return 0;
		}
		
		Block (int blockNum, List<Integer> busBlocks){
			blockNumber = blockNum;
			neighborBlocks = busBlocks;
		}
	}

	//Add list of neighboring blocks
	public HashMap <Integer, Block> blocks = new HashMap <Integer, Block>();
	{
		List<Integer> block1 = new ArrayList<Integer> ();
		block1.add(2);
		block1.add(4);
		blocks.put(1, new Block(1, block1));
		
		List<Integer> block2 = new ArrayList<Integer> ();
		block2.add(1);
		block2.add(3);
		block2.add(5);
		blocks.put(2, new Block(2, block2));
		
		List<Integer> block3 = new ArrayList<Integer> ();
		block3.add(2);
		block3.add(6);
		blocks.put(3, new Block(3, block3));
		
		List<Integer> block4 = new ArrayList<Integer> ();
		block4.add(1);
		block4.add(5);	
		block4.add(7);
		blocks.put(4, new Block(4, block4));
		
		List<Integer> block5 = new ArrayList<Integer> ();
		block5.add(2);
		block5.add(4);
		block5.add(6);
		block5.add(8);
		blocks.put(5, new Block(5, block5));
		
		List<Integer> block6 = new ArrayList<Integer> ();
		block6.add(2);
		block6.add(3);
		block6.add(4);
		block6.add(5);	
		block6.add(8);
		blocks.put(6, new Block(6, block6));
		
		List<Integer> block7 = new ArrayList<Integer> ();	
		block7.add(4);
		block7.add(8);
		blocks.put(7, new Block(7, block7));
		
		List<Integer> block8 = new ArrayList<Integer> ();
		block8.add(5);	
		block8.add(7);
		block8.add(9);
		blocks.put(8, new Block(8, block8));
		
		List<Integer> block9 = new ArrayList<Integer> ();	
		block9.add(6);
		block9.add(8);
		blocks.put(9, new Block(9, block9));
		
	}
	

	//List of bus stops
	public List<Point> busStops = new ArrayList<>();
	public List<BusStop> busStopsList = new ArrayList<BusStop>();

	
	
	//Intersections	
	public Intersection intersection1 = new Intersection(170, 72, 30, 38, 1);
	public Intersection intersection2 = new Intersection(390, 72, 30, 38, 2);
	public Intersection intersection3 = new Intersection(170, 192, 30, 38, 3);
	public Intersection intersection4 = new Intersection(390, 192, 30, 38, 4);

	public Crosswalk crosswalk1 = new Crosswalk(160, 10, 39, 25, 1);
	public Crosswalk crosswalk2 = new Crosswalk(380, 10, 39, 25, 1);

	public Crosswalk crosswalk3 = new Crosswalk(70, 70, 25, 39, 3);
	public Crosswalk crosswalk4 = new Crosswalk(280, 70, 25, 39, 4);
	public Crosswalk crosswalk5 = new Crosswalk(490, 70, 25, 39, 5);

	public Crosswalk crosswalk6 = new Crosswalk(160, 137, 39, 25, 6);
	public Crosswalk crosswalk7 = new Crosswalk(380, 137, 39, 25, 7);

	public Crosswalk crosswalk8 = new Crosswalk(70, 190, 25, 39, 8);
	public Crosswalk crosswalk9 = new Crosswalk(280, 190, 25, 39, 9);
	public Crosswalk crosswalk10 = new Crosswalk(490, 190, 25, 39, 10);

	public Crosswalk crosswalk11 = new Crosswalk(160, 270, 39, 25, 11);
	public Crosswalk crosswalk12 = new Crosswalk(380, 270, 39, 25, 12);

	private static Phonebook phonebook;


	private Phonebook() {

		//Set bus stops
		busStops.add(new Point(127, 28));		//Top left bus stop = busStop(0)
		busStops.add(new Point(420, 28));		//Top right bus stop = busStop(1)
		busStops.add(new Point(420, 230));		//Bottom right bus stop = busStop(2)
		busStops.add(new Point(127, 230));		//Bottom left bus stop = busStop(3)
		

		eastBank = new Bank("East Bank");
		eastBank.setClosestStop(busStops.get(2));		//setting building's closest bus stop

		busStopsList.add(new BusStop(0, 0, 0)); 			//empty busStop
		busStopsList.add(new BusStop(1, 127, 28));			//Top left bus Stop
		busStopsList.add(new BusStop(2, 420, 28));			// Top Right Bus Stop
		busStopsList.add(new BusStop(3, 420, 230));			// Bottom Right Bus Stop
		busStopsList.add(new BusStop(4, 127, 230));			// Bottom Left Bus Stop
		
		eastBank = new Bank("East Bank");
		eastBank.setClosestStop(busStops.get(3)); 		//setting building's closest bus stop
		eastBank.setClosestBusStopNumber(3);			//setting building's closest bus stop
		
		westBank = new Bank("West Bank");
		westBank.setClosestStop(busStops.get(1));		//setting building's closest bus stop
		westBank.setClosestBusStopNumber(1);			//setting building's closest bus stop
		
		eastMarket = new Market("East Market");
		eastMarket.setClosestStop(busStops.get(2));		//setting building's closest bus stop
		eastMarket.setClosestBusStopNumber(2);			//setting building's closest bus stop
		
		westMarket = new Market("West Market");
		eastMarket.setClosestStop(busStops.get(3));		//setting building's closest bus stop

		westMarket.setClosestStop(busStops.get(1));		//setting building's closest bus stop
		westMarket.setClosestBusStopNumber(1);			//setting building's closest bus stop
		

		seafoodRestaurant = new SeafoodRestaurant("Seafood Restaurant");
//		seafoodRestaurant.setClosestSop(busStops.get(2));
		
		chineseRestaurant = new ChineseRestaurant("Chinese Restaurant");
		chineseRestaurant.setClosestStop(busStops.get(2));		//setting building's closest bus stop
		chineseRestaurant.setClosestBusStopNumber(2);			//setting building's closest bus stop
		
		italianRestaurant = new ItalianRestaurant("Italian Restaurant");
		italianRestaurant.setClosestStop(busStops.get(1));		//setting building's closest bus stop
		
		americanRestaurant = new AmericanRestaurant("American Restaurant");
		
		italianRestaurant.setClosestStop(busStops.get(2));		//setting building's closest bus stop
		chineseRestaurant.setClosestBusStopNumber(2);			//setting building's closest bus stop

		/* For future use after all the restaurants have been integrated
		 * 
		mexicanRestaurant = new MexicanRestaurant("Mexican Restaurant");
		 */
		
		housingMaintenanceCompany = new HousingMaintenanceCompany("Housing maintenance company");
		getBusStops().add(new Point(172, 28));
		getBusStops().add(new Point(420, 28));
		getBusStops().add(new Point(420, 230));
		getBusStops().add(new Point(172, 230));
	}

	public static Phonebook getPhonebook() {
		if (phonebook == null) {
			phonebook = new Phonebook();
			return phonebook;
		}
		else {
			return phonebook;
		}
	}


	public Bank getEastBank() {
		return eastBank; 
	}

	public Bank getWestBank() {
		return westBank; 
	}

	public Market getEastMarket() {
		return eastMarket;
	}

	public Market getWestMarket() 
	{
		return westMarket;
	}

	public ChineseRestaurant getChineseRestaurant() 
	{
		return chineseRestaurant; //temporary stub. make more dynamic later
	}

	public SeafoodRestaurant getSeafoodRestaurant() {
		return seafoodRestaurant;
	}

	public ItalianRestaurant getItalianRestaurant() 
	{
		return italianRestaurant; //temporary stub. make more dynamic later
	}
	
	public AmericanRestaurant getAmericanRestaurant() 
	{
		return americanRestaurant; //temporary stub. make more dynamic later
	}

	public List<Housing> getAllHousing(boolean test)
	{
		if(test)
		{
			return testHousing;
		}
		else
		{
			return publicAllHousing;
		}
	}

	public void setHousingList(List<Housing> ApplicationHousing) 
	{
		this.publicAllHousing = ApplicationHousing;
	}

	public void setTestHousingList(List<Housing> testingHMC)
	{
		this.testHousing = testingHMC;
	}

	public HousingMaintenanceCompany getHousingMaintenanceCompany()
	{
		return housingMaintenanceCompany;
	}

	public void setHousingMaintenanceCompany(HousingMaintenanceCompany HMC)
	{
		this.housingMaintenanceCompany = HMC;
	}

	public List<Point> getBusStops() {
		return busStops;
	}
	
	public List<BusStop> getAllBusStops()
	{
		return busStopsList;
	}

	public void setBusStops(List<Point> busStops) {
		this.busStops = busStops;
	}

	public void closeBuilding(String type){
		System.out.println("Closing " + type);
		if (type == "eastBank")
			eastBank.closeBuilding();
		if (type == "westBank")
			westBank.closeBuilding();
		if (type == "eastMarket")
			eastMarket.closeBuilding();
		if (type == "westMarket")
			westMarket.closeBuilding();
		if (type == "chineseRestaurant")
			chineseRestaurant.closeBuilding();
	}

	public void openBuilding(String type){
		System.out.println("Opening " + type);
		if (type == "eastBank")
			eastBank.userClosed = false;
		if (type == "westBank")
			westBank.userClosed = false;
		if (type == "eastMarket")
			eastMarket.userClosed = false;
		if (type == "westMarket")
			westMarket.userClosed = false;
		if (type == "chineseRestaurant")
			chineseRestaurant.userClosed = false;
	}

	public class Intersection {
		public Rectangle intersection;
		public int number;

		public boolean intersectionBusy;

		public Intersection(int x, int y, int width, int height, int n) {
			intersection = new Rectangle(x, y, width, height);
			intersectionBusy = false;
			number = n;
		}

		public Rectangle getIntersection() {
			return intersection;
		}

		public void setIntersection(Rectangle intersection) {
			this.intersection = intersection;
		}

		public boolean isIntersectionBusy() {
			return intersectionBusy;
		}

		public void setIntersectionBusy(boolean intersectionBusy) {
			this.intersectionBusy = intersectionBusy;
		}
	}

	public class Crosswalk {
		public Rectangle crosswalk;
		public int number;

		public boolean crosswalkBusy;

		public Crosswalk(int x, int y, int width, int height, int n) {
			crosswalk = new Rectangle(x, y, width, height);
			crosswalkBusy = false;
			number = n;
		}

		public Rectangle getCrosswalk() {
			return crosswalk;
		}

		public void setCrosswalk(Rectangle crosswalk) {
			this.crosswalk = crosswalk;
		}

		public boolean isCrosswalkBusy() {
			return crosswalkBusy;
		}

		public void setCrosswalkBusy(boolean crosswalkBusy) {
			this.crosswalkBusy = crosswalkBusy;
		}
	}
}
