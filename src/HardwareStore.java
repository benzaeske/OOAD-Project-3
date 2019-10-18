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
		this.customers = new ArrayList<Customer>();
		this.completedRentals = new ArrayList<Rental>();
		this.activeRentals = new ArrayList<Rental>();
		this.currentDay = 34;
		this.revenue = 0;
		this.casualRentCount = 0;
		this.businessRentCount = 0;
		this.regularRentCount = 0;
		this.rentCount = 0;
	}

//----------------Simulation methods----------------	
//Methods related to running the hardware store 35-day simulation
		
	public void runSimulation() {
		//Run a loop and call doDay each time.
		while (this.currentDay >= 0) {
			this.doDay();
			this.currentDay -= 1;
		}
		//Do final prints
	}
		
	//Simulate one day
	public void doDay() {
		int dayRevenue = 0;
		//1. print day number
		int day = 35 - this.currentDay;
		System.out.println("\n----(Day number: " + day + ")----\n");
		//2. notify each observer (customers) of day change; 
		dayRevenue += this.notifyCustomers();
		//3. print completed rentals
		this.printCompletedRentals();
		//4. print active rentals
		this.printActiveRentals();
		//5. print count and list of tools left in inventory
		this.printInventory();
		//6. print the current day's revenue (dayRevenue) and add dayRevenue to this.revenue
		System.out.println("\n----(Revenue)----");
		this.revenue += dayRevenue;
		System.out.println("Store profit today: $" + dayRevenue);
		System.out.println("Total store profit after " + day + " days : $" + this.revenue);
		//If currentDay is 0 -> print completed rentals overall and by customer, total money the store made
		if (this.currentDay == 0) {
			System.out.println("\n----(End of Simulation)----");
			System.out.println("Total revenue earned over 35 days: $" + this.revenue);
			System.out.println("Total Rentals: " + this.rentCount);
			System.out.println("Rentals by Customer Type");
			System.out.println("Business Customers: " + this.businessRentCount);
			System.out.println("Casual Customers: " + this.casualRentCount);
			System.out.println("Regular Customers: " + this.regularRentCount);
		}
	}
	
	//Returns a rental's tools (baseTools) to the store's inventory, and moves the rental from active to completed
	public void completeRental(Rental rental) {
		for (Tool tool : rental.baseTools) {
			this.inventory.add(tool);
		}
		this.activeRentals.remove(rental);
		this.completedRentals.add(rental);
	}
	
//----------------Observer pattern methods----------------
	
	//Add a customer. Our equivalent of adding an observer
	public void addCustomer(Customer customer) {
		this.customers.add(customer);
		customer.store = this;
	}
	
	//Our equivalent of notifyObservers()
	//customer.update() for all customers. They will handle if they can rent or not and will handle returning their rentals. 
	//Add the returned rental to activeRentals if not null. keep track of the day's revenue along the way (new rentals)
	public int notifyCustomers() {
		int dayRevenue = 0;
		for (Customer customer : this.customers) {
			//Update each customer
			Rental newRental = customer.update();
			//Update active rentals and rental counts if the customer makes a new rental
			if (newRental != null) {
				this.activeRentals.add(newRental);
				this.rentCount += 1;
				switch(customer.type) {
					case "business":
						this.businessRentCount += 1;
					case "regular":
						this.regularRentCount += 1;
					case "casual":
						this.casualRentCount += 1;
				}
				//Update the day's revenue
				dayRevenue += newRental.getCost();
			}
		}
		return dayRevenue;
	}
		
//----------------Helper methods----------------
//Methods which hold logic that may need to be repeated multiple times.
	
	//Because each tool needs a unique number/combination of extras, each tool needs to be retrieved one at a time
	//This should take no parameter because it should only return 1 tool
	//Should handle removing tool from store inventory
	public Tool getRandomTool() {
		Random rand = new Random();
		int randIndex = rand.nextInt(this.inventory.size());
		Tool randTool = this.inventory.get(randIndex);
		this.inventory.remove(randIndex);
		return randTool;
	}
	
	//helper method that prints completed rentals
	public void printCompletedRentals() {
		System.out.println("\n----(Completed Rentals)----");
		if (this.completedRentals.size() == 0) {
			System.out.println("No completed rentals.");
			return;
		}
		System.out.println("Total completed: " + this.completedRentals.size());
		for (Rental rental : this.completedRentals) {
			rental.printRental();
		}
	}
	
	//helper method that prints active rentals
	public void printActiveRentals() {
		System.out.println("\n----(Active Rentals)----");
		if (this.activeRentals.size() == 0) {
			System.out.println("No active rentals.");
			return;
		}
		System.out.println("Total active: " + this.activeRentals.size());
		for (Rental rental : this.activeRentals) {
			rental.printRental();
		}
	}
	
	//helper method that prints count and list of all tools in the inventory
	public void printInventory() {
		System.out.println("\n----(Store Inventory)----");
		System.out.println("Total tools left in inventory: " + this.inventory.size());
		for (Tool tool : this.inventory) {
			System.out.println(tool.getDescription());
		}
	}
	
	public int getInventorySize()
	{
		return this.inventory.size();
	}
	
}
