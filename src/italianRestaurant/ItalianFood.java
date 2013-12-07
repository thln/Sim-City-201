package italianRestaurant;


public class ItalianFood {
	String type;
	int cookingTime;
	int low;
	int inventory;
	int capacity;
	FoodState fs;
	enum FoodState {inStock, isLow, ordered, orderingAgain, delivered};
	
	ItalianFood(String choice, int time, int invent, int lownum) {
		type = choice;
		cookingTime = time;
		inventory = invent;
		low = lownum;
		capacity = 20;
		fs = FoodState.inStock;
	}
	
	public String toString() {
		return type;
	}
}