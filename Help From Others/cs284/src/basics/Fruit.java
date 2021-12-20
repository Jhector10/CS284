package basics;

public class Fruit implements Colorable {
	// data field
	private String color;
	private int acitidy;
	public Fruit(String color, int acitidy) {
		super();
		this.color = color;
		this.acitidy = acitidy;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getAcitidy() {
		return acitidy;
	}
	public void setAcitidy(int acitidy) {
		this.acitidy = acitidy;
	}
	
	
}
