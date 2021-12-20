package people;

public class Grad extends Student{
	private int id;
	
	public Grad(String fn, String ln, int age, double gpa, int id) {
		super(fn, ln, age, gpa);
		this.id = id;
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public static void printOut(Grad s) {
		System.out.println(s.getFirstName()+" "+s.getLastName()+": "+s.getAge()+", "+s.getGpa()+", "+s.getId());
	}
	public static void main(String[] args) {
		Student k = new Grad("mike", "mccreesh", 20, 4.0, 75);
		printOut(k);
	}
}
