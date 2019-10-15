import java.util.*;

//----------------------------Abstract Customer class----------------------------

//This is our Observer
public class Customer {
	
	public String name;
	//Casual, regular, or business
	public String type;
	//True if they have space to make an additional rental
	public boolean canRent;
	public List<Rental> rentals = new ArrayList<>();
	public HardwareStore store;
	public RentAlgorithm rentAlgorithm;
	
	public Customer(String name, String type, RentAlgorithm rentAlgorithm) {
		this.name = name;
		this.type = type;
		this.rentAlgorithm = rentAlgorithm;
	}
	
	//Helper method for doing a rental
	public Rental rent() {
		Rental newRental = this.rentAlgorithm.rent(this.store);
		return newRental;
	}
	
	//Returns a rental's tools (baseTools) to the store's inventory and updates the store's completedRental list. Removes the rental from the store's activeRentals list TODO
	public void completeRental(Rental rental) {
		this.rentals.remove(rental);
		this.store.completeRental(rental);
	}
	
	//Observer update method. Returns a new rental object or null if it can't rent or if it isn't randomly chosen TODO
	public Rental update(int newState) {
		//Decrement current rentals' daysRemaining; If any rental has 0 days left -> this.completeRental(rental)
		//If this.canRent -> randomly decide if this customer will rent more today. If so -> return rentAlgorithm.rent();
		return null; //TODO
	}
	
}

//----------------------------RentAlgorithm Strategy pattern----------------------------

abstract class RentAlgorithm {
	
	//Makes a new rental object and returns it, removing a certain number of tools from the hardwareStore.
	//Adds 0-6 options to each tool. The rental's id is gotten using store.getRentalId(). Uses the
	//store.getRandomTools(int) helper function
	public abstract Rental rent(HardwareStore store);
	
}

class CasualRentAlgorithm extends RentAlgorithm {
	
	public Rental rent(HardwareStore store) {
		//1-2 tools for 1-2 nights
		//TODO
		return null;
	}
	
}

class BusinessRentAlgorithm extends RentAlgorithm {
	
	public Rental rent(HardwareStore store) {
		//Rent 3 tools for 7 days
		//TODO
		return null;
	}
	
}

class RegularRentAlgorithm extends RentAlgorithm {
	
	public Rental rent(HardwareStore store) {
		//1-3 tools for 3-5 nights
		//TODO
		return null;
	}
	
}


//----------------------------Rental class----------------------------

class Rental {
	//Save the base tool types
	private List<Tool> baseTools;
	//The list of tools once options are added on
	public List<Tool> tools;
	private int days;
	public int remainingDays;
	private int cost;
	private String customerName;
	public int id;
	
	public Rental(List<Tool> baseTools, List<Tool> tools, int days, String customerName/*,int id*/) {
		this.baseTools = baseTools;
		this.tools = tools;
		this.days = days;
		//Set the cost of the rental
		int cost = 0;
		for (int i = 0; i < this.tools.size(); i++) {
			cost += tools.get(i).cost();
		}
		this.cost = cost;
		this.customerName = customerName;
		//this.id = id;
	}
	
	//Prints tools + options (tools) for which customer, for how many days, and at what cost
	public void printRental() 
	{
		for(Tool t: tools)
		{
			System.out.println(t.getDescription);
		}
	}
	
	public int getCost() {
		return this.cost;
	}
	
}




