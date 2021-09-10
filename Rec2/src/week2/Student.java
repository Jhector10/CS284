package week2;

public class Student extends Person {
	
	//instance variable
	private int GPA;
	
	public Student(String first, String family, int age, int GPA) {
		super(first, family, age);
		this.GPA = GPA;
	}
	
	public int getGPA() {
		return GPA;
	}
	
	public void setGPA(int GPA) {
		this.GPA = GPA;
	}
	
	public String toString() {
		return super.toString() + " GPA: " + GPA;
	}
	
	public static void main(String[] args) {
		Student s = new Student ("Jacob", "Ashkenas", 19, 100);
		System.out.println(s.toString());
	}

}
