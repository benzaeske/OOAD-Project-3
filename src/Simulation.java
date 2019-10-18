import java.util.*;
import java.io.*;

public class Simulation {
	
	public static void main(String[] args) throws FileNotFoundException {
		//Change output to 'simulation.out': https://www.geeksforgeeks.org/redirecting-system-out-println-output-to-a-file-in-java/
		PrintStream o = new PrintStream(new File("simulation.out"));   
		//Save console
        PrintStream console = System.out; 
        System.setOut(o); 
        
		//Generate the inventory
		List<Tool> inventory = generateInventory();
		//Instantiate the HardwareStore
		HardwareStore store = new HardwareStore(inventory);
		//Make customers and add them to the hardwareStore
		generateCustomers(store);
		//Run the simulation:
		store.runSimulation();
	}
	
	//Helper function for making tools
	public static List<Tool> generateInventory() {
		List<Tool> inventory = new ArrayList<Tool>();
		ToolFactory paintingTools = new PaintingToolFactory();
		ToolFactory concreteTools = new ConcreteToolFactory();
		ToolFactory woodworkTools = new WoodworkToolFactory();
		ToolFactory yardworkTools = new YardworkToolFactory();
		ToolFactory plumbingTools = new PlumbingToolFactory();
		for (int i = 0; i < 5; i++) {
			inventory.add(paintingTools.getInstance());
			inventory.add(concreteTools.getInstance());
			inventory.add(woodworkTools.getInstance());
			inventory.add(yardworkTools.getInstance());
			if (i < 4) {
				inventory.add(plumbingTools.getInstance());
			}
		}
		return inventory;
	}
	
	//Helper function for generating customers
	public static void generateCustomers(HardwareStore store) {
		CustomerFactory businessFactory = new BusinessCustomerFactory();
		CustomerFactory casualFactory = new CasualCustomerFactory();
		CustomerFactory regularFactory = new RegularCustomerFactory();
		for (int i = 0; i < 5; i++) {
			if (i < 2) {
				store.addCustomer(businessFactory.getInstance());
			}
			store.addCustomer(casualFactory.getInstance());
			store.addCustomer(regularFactory.getInstance());
		}
	}
	
}
