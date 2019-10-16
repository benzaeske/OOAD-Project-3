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
	

	//Do we need this? Renting handled by update()
	//Helper method for doing a rental
	// public Rental rent() {
	// 	Rental newRental = this.rentAlgorithm.rent(this.store);
	// 	return newRental;
	// }

	//Returns a rental's tools (baseTools) to the store's inventory and updates the store's completedRental list. Removes the rental from the store's activeRentals list TODO
	public void completeRental(Rental rental) {
		this.rentals.remove(rental);
		this.store.completeRental(rental);
	}
	
	//Returns max number of tools customer can rent
	public int checkMaxTools()
	{
		int maxTools;
		if(this.type == "casual")
		{
			maxTools = 2;
		}
		else //Regular and business customers can rent up to 3 tools
		{
			maxTools = 3;
		}

		//Check how much space customer has left
		int remainingSpace = 3;
		for(Rental rental : this.rentals)
		{
			remainingSpace -= this.rental.tools.size();
		}

		//If customer has more space to rent tools
		if(remainingSpace > 0)
		{
			//maxTools changed to available space
			if(remainingSpace <= maxTools)
			{
				maxTools = remainingSpace;
			}
		}
		else
		{
			maxTools = 0;
		}

		return maxTools;
	}

	public bool getRentalStatus(int maxTools)
	{
		//If there's enough items in the store and the customer has room for more tools, they canRent = true
		if(this.store.getInventorySize() >= maxTools && maxTools!=0)
		{
			return true;
		}

		return false;
	}

	//Observer update method. Returns a new rental object or null if it can't rent or if it isn't randomly chosen TODO
	public Rental update() {
		//Update canRent
		//If this.canRent -> randomly decide if this customer will rent more today. If so -> return rentAlgorithm.rent();
		
		//If canRent 
			//determine max tools ==> pass into rent

			//RNG: 0 or 1 if rent
		
		//Decreasing remainingDays, returning any rentals if remainingDays == 0		
		for(Rental currentRentals : rentals)
		{
			currentRentals.remainingDays -= 1;

			if(currentRentals.remainingDays == 0)
			{
				this.completeRental(currentRentals);
			}
		}

		//helper functions to handle renting logic
		int maxTools = checkMaxTools();
		this.canRent = getRentalStatus(maxTools);

		Random rand = new Random();
		int willRent = rand.nextInt(2);

		if(willRent && canRent)
		{
			Rental newRental =  this.rentAlgorithm.rent(this.store, maxTools);
			return newRental;
		}
		
		return null; //TODO
	}
	
}

//----------------------------RentAlgorithm Strategy pattern----------------------------

abstract class RentAlgorithm {
	
	//Makes a new rental object and returns it, removing 1-maxTools of tools from the hardwareStore.
	//Adds 0-6 options to each tool. 
	//returns completed rental
	public abstract Rental rent(HardwareStore store, int maxTools);
	
}

class CasualRentAlgorithm extends RentAlgorithm {
	
	public Rental rent(HardwareStore store, int maxTools) {
		//1-2 tools for 1-2 nights
		
		Random rand = new Random();
		int numTools = rand.nextInt(maxTools) + 1;

		List<Tool> tools = new ArrayList<Tool>();
		List<Tool> baseTools = new ArrayList<Tool>();

		//Casual customer will rent 1-2 nights
		//nextInt(2) = [0,1]
		//+1 = [1,2]
		int numDays = rand.nextInt(2)+1;

		for(int i = 0; i < numTools; i++)
		{
			Tool temp = store.getRandomTool();

			//add tool without extras to baseTools
			baseTools.add(temp);

			//Adding random number of options and random options
			int numOptions = rand.nextInt(6);
			for(int j = 0; j < numOptions; j++)
			{
				int optionType = rand.nextInt(3);
				switch(optionType)
				{
					case 0:
						temp = new ExtensionCordFactory().addOption(temp);
						break;
					case 1:
						temp = new AccessoryKitFactory().addOption(temp);
						break;
					case 2:
						temp = new ProtectiveGearFactory().addOption(temp);
						break;
				}
			}

			//add single tool with option info to tools
			tools.add(temp);

		}
		Rental newRental = new Rental(baseTools, tools, numDays);
		return newRental;
	}
	
}

class BusinessRentAlgorithm extends RentAlgorithm {
	
	public Rental rent(HardwareStore store, int maxTools) {
		//Rent 3 tools for 7 days
		Random rand = new Random();
		int numTools = 3;
		int numDays = 7;

		List<Tool> tools = new ArrayList<Tool>();
		List<Tool> baseTools = new ArrayList<Tool>();

		for(int i = 0; i < numTools; i++)
		{
			Tool temp = store.getRandomTool();

			//add tool without extras to baseTools
			baseTools.add(temp);

			//Adding random number of options and random options
			int numOptions = rand.nextInt(6);
			for(int j = 0; j < numOptions; j++)
			{
				int optionType = rand.nextInt(3);
				switch(optionType)
				{
					case 0:
						temp = new ExtensionCordFactory().addOption(temp);
						break;
					case 1:
						temp = new AccessoryKitFactory().addOption(temp);
						break;
					case 2:
						temp = new ProtectiveGearFactory().addOption(temp);
						break;
				}
			}

			//add single tool with option info to tools
			tools.add(temp);

		}
		Rental newRental = new Rental(baseTools, tools, numDays);
		return newRental;
	}
	
}

class RegularRentAlgorithm extends RentAlgorithm {
	
	public Rental rent(HardwareStore store, int maxTools) {
		//1-3 tools for 3-5 nights
		//TODO
		Random rand = new Random();
		int numTools = rand.nextInt(maxTools) + 1;

		List<Tool> tools = new ArrayList<Tool>();
		List<Tool> baseTools = new ArrayList<Tool>();

		//Regular customer will rent 3-5 nights
		//nextInt((5-3)+1) = [0,1,2]
		//+3 = [3,4,5]
		int numDays = rand.nextInt((5-3)+1)+3;

		for(int i = 0; i < numTools; i++)
		{
			Tool temp = store.getRandomTool();

			//add tool without extras to baseTools
			baseTools.add(temp);

			//Adding random number of options and random options
			int numOptions = rand.nextInt(6);
			for(int j = 0; j < numOptions; j++)
			{
				int optionType = rand.nextInt(3);
				switch(optionType)
				{
					case 0:
						temp = new ExtensionCordFactory().addOption(temp);
						break;
					case 1:
						temp = new AccessoryKitFactory().addOption(temp);
						break;
					case 2:
						temp = new ProtectiveGearFactory().addOption(temp);
						break;
				}
			}

			//add single tool with option info to tools
			tools.add(temp);

		}
		Rental newRental = new Rental(baseTools, tools, numDays);
		return newRental;
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
	
	//a customer has a list of rentals, so a rental doesn't need to keep track of the customer it belongs to
	public Rental(List<Tool> baseTools, List<Tool> tools, int days /*String customerName,int id*/) {
		//tools without options
		this.baseTools = baseTools;
		this.tools = tools;
		this.remainingDays = days;
		this.days = days;
		//Set the cost of the rental
		int cost = 0;
		for (int i = 0; i < this.tools.size(); i++) {
			cost += tools.get(i).cost();
		}
		this.cost = cost;
		//this.customerName = customerName;
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




