
public class PlayingWithShapes {
	
	public static void main(String[] args) {
		Rectangle r1 = new Rectangle(1.7, 2.7);
		Circle c1 = new Circle(3.4, "Blue");
		
		System.out.println(r1.toString());
		System.out.println(c1.toString());
		
		System.out.println(r1.area());
		System.out.println(c1.area());
	}

}
