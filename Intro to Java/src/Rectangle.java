
public class Rectangle extends Shape{
	// private means it is only accessible in this class
	private double width;
	private double height;
	private static int numberOfRectangles = 0; 
	
	// methods
	public Rectangle(double width, double height, String color) {
		super(color);
		this.width = width;
		this.height = height;
		numberOfRectangles++; // increment number by 1 when called
	}
	
	public Rectangle(double width, double height) {
		super("White");
		this.width = width;
		this.height = height;
	}
	
	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double area() {
		return width*height;
	}
	
	public String toString() {
		return "I am a Rectangle. My width is " +width+". my height is "+height+", and my color is "+this.getColor();
	}
}
