package factory_pattern;

public class FactoryPatternTest {

	public static void main(String[] args) {
		
		final Computer pc = ComputerFactory.getComputer("PC","2GB","500GB","2.4GHz");
		final Computer server = ComputerFactory.getComputer("SERVER","16GB","1TB","2.9GHz");
		
		System.out.println("Factory PC Config :: " + pc);
		
		System.out.println("Factory Server Config :: " + server);
		
	}
	
}
