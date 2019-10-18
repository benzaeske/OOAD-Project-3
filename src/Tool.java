//not github
import java.util.*;

//----------------------------Basic Tool Objects----------------------------
//Tool interface. Has methods to get the cost and description of a Tool
public interface Tool {
	public int cost();
	public String getDescription();
}

//-------Concrete Tool implementations below--------
class PaintingTool implements Tool {
	public String name;
	public String type;
	protected int cost;
	public PaintingTool(String name) {
		this.name = name;
		this.type = "Painting Tool";
		this.cost = 5;
	}
	public int cost() {
		return this.cost;
	}
	public String getDescription() {
		return this.name;
	}
}

class ConcreteTool implements Tool {
	public String name;
	public String type;
	protected int cost;
	public ConcreteTool(String name) {
		this.name = name;
		this.type = "Concrete Tool";
		this.cost = 20;
	}
	public int cost() {
		return this.cost;
	}
	public String getDescription() {
		return this.name;
	}
}

class PlumbingTool implements Tool {
	public String name;
	public String type;
	protected int cost;
	public PlumbingTool(String name) {
		this.name = name;
		this.type = "Plumbing Tool";
		this.cost = 15;
	}
	public int cost() {
		return this.cost;
	}
	public String getDescription() {
		return this.name;
	}
}

class WoodworkTool implements Tool {
	public String name;
	public String type;
	protected int cost;
	public WoodworkTool(String name) {
		this.name = name;
		this.type = "Woodwork Tool";
		this.cost = 15;
	}
	public int cost() {
		return this.cost;
	}
	public String getDescription() {
		return this.name;
	}
}

class YardworkTool implements Tool {
	public String name;
	public String type;
	protected int cost;
	public YardworkTool(String name) {
		this.name = name;
		this.type = "Yardwork Tool";
		this.cost = 10;
	}
	public int cost() {
		return this.cost;
	}
	public String getDescription() {
		return this.name;
	}
}

//----------------------------Tool Decorator----------------------------
//The following website was referenced:
//https://www.journaldev.com/1540/decorator-design-pattern-in-java-example
	
//Abstract ToolDecorator class.
abstract class ToolDecorator implements Tool {
	protected Tool tool;
	public ToolDecorator(Tool tool) {
		this.tool = tool;
	}
}

//------Concrete Tool Decorators below------

class ExtensionCord extends ToolDecorator {
	public ExtensionCord(Tool tool)
	{
		super(tool);
	}

	public String getDescription()
	{
		return tool.getDescription() + " + Extension Cord";
	}

	public int cost()
	{
		return 1 + tool.cost();
	}
}

class AccessoryKit extends ToolDecorator{
	public AccessoryKit(Tool tool)
	{
		super(tool);
	}

	public String getDescription()
	{
		return tool.getDescription() + " + Accessory Kit";
	}

	public int cost()
	{
		return 2 + tool.cost();
	}
}

class ProtectiveGearPackage extends ToolDecorator{
	public ProtectiveGearPackage(Tool tool)
	{
		super(tool);
	}

	public String getDescription()
	{
		return tool.getDescription() + " + Protective Gear";
	}

	public int cost()
	{
		return 3 + tool.cost();
	}
}

//----------------------------Option Factories----------------------------
//Option factories are used to add one of the three options onto a tool

abstract class OptionFactory {
	public abstract Tool addOption(Tool tool);
}

class ExtensionCordFactory extends OptionFactory {
	public Tool addOption(Tool tool) {
		Tool wrappedTool = new ExtensionCord(tool);
		return wrappedTool;
	}
}

class AccessoryKitFactory extends OptionFactory {
	public Tool addOption(Tool tool) {
		Tool wrappedTool = new AccessoryKit(tool);
		return wrappedTool;
	}
}

class ProtectiveGearFactory extends OptionFactory {
	public Tool addOption(Tool tool) {
		Tool wrappedTool = new ProtectiveGearPackage(tool);
		return wrappedTool;
	}
}

//----------------------------Tool Factories----------------------------
/*Tool factories are used to generate tools. They are used in the initialization 
 * phase of the simulation to generate the 24 tools in the store's inventory
 */

abstract class ToolFactory {
	int toolsMade;
	public ToolFactory() {
		this.toolsMade = 0;
	}
	public abstract Tool getInstance();
}

class PaintingToolFactory extends ToolFactory {
	@Override
	public Tool getInstance() {
		this.toolsMade += 1;
		return new PaintingTool("Painting Tool " + this.toolsMade);
	}
}

class ConcreteToolFactory extends ToolFactory {
	@Override
	public Tool getInstance() {
		this.toolsMade += 1;
		return new ConcreteTool("Concrete Tool " + this.toolsMade);
	}
}

class PlumbingToolFactory extends ToolFactory {
	@Override
	public Tool getInstance() {
		this.toolsMade += 1;
		return new PlumbingTool("Plumbing Tool " + this.toolsMade);
	}
}

class WoodworkToolFactory extends ToolFactory {
	@Override
	public Tool getInstance() {
		this.toolsMade += 1;
		return new WoodworkTool("Woodwork Tool " + this.toolsMade);
	}
}

class YardworkToolFactory extends ToolFactory {
	@Override
	public Tool getInstance() {
		this.toolsMade += 1;
		return new YardworkTool("Yardwork Tool " + this.toolsMade);
	}
}









