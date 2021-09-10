
public abstract class Shape { // want the shapes to be a specific type, use "abstract"
	//Data fields
	private String color;
	
	//Constructor
	public Shape(String color) {
		this.color = color;
	}
	
	//Methods
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String toString() {
		return "I am a Shape. My color is " + color + ".";
	}
}
