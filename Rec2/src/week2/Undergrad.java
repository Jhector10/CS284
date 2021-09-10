package week2;

public class Undergrad extends Student{
	
	private int year;
	
	public Undergrad(int year, String first, String family, int age, int GPA) {
		super(first, family, age, GPA);
		this.year = year;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int newYear) {
		this.year = newYear;
	}
	
	public String toString() {
		return super.toString() + " Graduation year: " + year;
	}

	public static void main(String[] args) {
		Undergrad u = new Undergrad(2024, "Billy", "Lastname", 21, 3);
		System.out.println(u.toString());
	}
}
