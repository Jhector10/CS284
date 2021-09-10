package week1;

public class Rectangle {
	// private means it is only accessible in this class
	private double width;
	private double height;
	private static int numberOfRectangles = 0; 
	
	// methods
	public Rectangle(double x, double y) {
		width = x;
		height = y;
		numberOfRectangles++; // increment number by 1 when called
	}
	
	public double area() {
		return width*height;
	}
	
	// static indicates that it is a class method
	public static int getNumberOfRectangles() {
		return numberOfRectangles;
	}
	
	Rectangle rect1 = new Rectangle(3.5, 2.6);
	Rectangle rect2 = new Rectangle(7.2, 8.4);
	
	double ar; {
	ar = rect1.area();
	ar = rect2.area();
	}
}
