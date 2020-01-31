package builder_pattern;

public class BuilderPatternTest {

	public static void main(String[] args) {
		
		Computer comp = new Computer.ComputerBuilder("500GB", "2GB")
				.setBluetoothEnabled(true)
				.setGraphicsCardEnabled(true)
				.build();
		
		
		System.out.println(comp.getHDD() + " :: " + comp.getRAM());	
	}	
}
