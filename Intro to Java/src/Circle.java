
public class Circle extends Shape{

	// data fields
	private double radius;
	// private static final double PI = 3.14
	
	// Constructor
	public Circle() {
		super("White");
		radius = 0;
	}
	
	public Circle(double radius, String color) {
		super(color);
		this.radius = radius;
	}
	
	// Methods
	public double getRadius() {
		return radius;
	}
	
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public double area() {
		return Math.PI * radius * radius;
	}
	
	public String toString() {
		return "I am a Circle. My radius is " + radius + " and my color is " + this.getColor();
	}

}
