package week2;

class Bird {
	public void Sing() {
		System.out.println("tweet");
	}
	public void Wings() {
		System.out.println("flap");
	}
}

class Robin {
	public void Sing() {
		System.out.println("tweet");
	}
	public void Wings() {
		System.out.println("flap");
	}
}

public class polymorphism {
	
	public static void main(String[] args) {
		Bird a = new Bird();
		a.Sing();
		a.Wings();
		System.out.print("\n");
		Robin c = new Robin();
		c.Sing();
		c.Wings();
	}

}
