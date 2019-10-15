import java.util.*;

public class HardwareStore {
	
	private List<Tool> inventory;
	private List<Customer> customers;
	private List<Rental> completedRentals;
	private List<Rental> activeRentals;
	private int currentDay;
	private int revenue;
	private int casualRentCount;
	private int businessRentCount;
	private int regularRentCount;
	private int rentCount;
	
	public HardwareStore(List<Tool> inventory) {
		this.inventory = inventory;
		this.customers = new ArrayList<>();
		this.completedRentals = new ArrayList<>();
		this.activeRentals = new ArrayList<>();
		this.currentDay = 34;
		this.revenue = 0;
		this.casualRentCount = 0;
		this.businessRentCount = 0;
		this.regularRentCount = 0;
		this.rentCount = 0;
	}

//----------------Simulation methods----------------	
	
	//Run for loop for each day TODO
	public void runSimulation() {
			
	}
		
	//Simulate one day TODO
	public void doDay() {
		int dayRevenue = 0;
		//1. print day number
		//2. notify observers (customers) of day change; This returns a rent object or a null object. If not null 
		//	-> Increase the rent count based on the type of customer (casual, regular, or business), 
		//	and increase the dayRevenue (dayRevenue += newRental.getCost()), add the new rental to this.activeRentals
		//3. print completed rentals (this.printCompletedRentals())
		//4. print active rentals (this.printActiveRentals())
		//4. print count and list of tools left in inventory (this.printInventory())
		//5. print the current day's revenue (dayRevenue) and add dayRevenue to this.revenue
		//If currentDay is 0 -> print completed rentals overall and by customer, total money the store made (this.revenue)
	}
	
	public void completeRental(Rental rental) {
		//Returns a rental's tools (baseTools) to the store's inventory, and moves the rental from active to completed
	}
	
	//Returns a given 'number' of random tools from the store's inventory.
	public List<Tool> getRandomTools(int number) {
		return null; //TODO
	}
	
	//Gets a new rentId and increases rentCount
	/*public int getRentalId() {
		int id = this.rentCount;
		this.rentCount += 1;
		return id;
	}*/
	
//----------------Observer pattern methods----------------
	
	//Add a customer (observer) TODO
	public void addCustomer() {
		
	}
	
	//Notifies all registered customers of the day change. Returns the amount of money the store made.
	public int notifyCustomers() {
		//check number of tools and don't notify customers if their type can't perform a rental
		//keep track of the day's revenue (returned by each customer.update() method)
		int dayRevenue = 0;
		return dayRevenue;
	}
		
//----------------Helper print methods----------------
	
	//helper method that prints completed rentals TODO
	public void printCompletedRentals() {
		System.out.println("");
	}
	
	//helper method that prints active rentals TODO
	public void printActiveRentals() {
		System.out.println("");
	}
	
	//helper method that prints count and list of all tools in the inventory TODO
	public void printInventory() {
		System.out.println("");
	}
	
	
}
