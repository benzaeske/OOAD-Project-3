//not github
import java.util.*;

//----------------------------Basic Tool Objects----------------------------
public interface Tool {
	public int cost();
	public String getDescription();
}

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
		return this.type;
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
		return this.type;
	}
}

class PlumbingTool implements Tool {
	public String name;
	public String type;
	protected int cost;
	public PlumbingTool(String name) {
		this.name = name;
		this.type = "Concrete Tool";
		this.cost = 15;
	}
	public int cost() {
		return this.cost;
	}
	public String getDescription() {
		return this.type;
	}
}

class WoodworkTool implements Tool {
	public String name;
	public String type;
	protected int cost;
	public WoodworkTool(String name) {
		this.name = name;
		this.type = "Concrete Tool";
		this.cost = 15;
	}
	public int cost() {
		return this.cost;
	}
	public String getDescription() {
		return this.type;
	}
}

class YardworkTool implements Tool {
	public String name;
	public String type;
	protected int cost;
	public YardworkTool(String name) {
		this.name = name;
		this.type = "Concrete Tool";
		this.cost = 10;
	}
	public int cost() {
		return this.cost;
	}
	public String getDescription() {
		return this.type;
	}
}

//----------------------------Basic Tool Objects----------------------------
abstract class ToolDecorator implements Tool {
	protected Tool tool;
	public ToolDecorator(Tool tool) {
		this.tool = tool;
	}
}

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

//----------------------------Factories----------------------------

abstract class ToolFactory {
	public abstract Tool addOption(Tool tool);
}

class ExtensionCordFactory extends ToolFactory {
	public Tool addOption(Tool tool) {
		Tool wrappedTool = new ExtensionCord(tool);
		return wrappedTool;
	}
}

class AccessoryKitFactory extends ToolFactory {
	public Tool addOption(Tool tool) {
		Tool wrappedTool = new AccessoryKit(tool);
		return wrappedTool;
	}
}

class ProtectiveGearFactory extends ToolFactory {
	public Tool addOption(Tool tool) {
		Tool wrappedTool = new ProtectiveGearPackage(tool);
		return wrappedTool;
	}
}










