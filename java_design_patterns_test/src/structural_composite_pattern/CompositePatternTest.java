package structural_composite_pattern;

public class CompositePatternTest {

	public static void main(String[] args) {
		
		Shape tri = new Triangle();
		Shape tri1 = new Triangle();
		Shape cir = new Circle();
		
		DrawingComposite drawing = new DrawingComposite();
		drawing.add(tri1);
		drawing.add(tri1);
		drawing.add(cir);
		
		drawing.draw("Red");
		
		drawing.clear();
		
		drawing.add(tri);
		drawing.add(cir);
		drawing.draw("Green");
	}
	
}
