
public class Simulation {
	
	public static void main(String[] args) {
		Tool t = new PaintingTool("tool 1");
		System.out.println(t.getDescription() + " " + t.cost());
		ToolFactory f = new ExtensionCordFactory();
		t = f.addOption(t);
		System.out.println(t.getDescription() + " " + t.cost());
	}
	
}
