package week1;

public class Main {
	public static void main(String[] args) {
		System.out.println(isPrime(2));
	}
	
	public static boolean isPrime(int num) {
		// Only have to check up to the square root
		// Use modulus to check if a number is a factor
		// Use a for loop to iterate over factors (Edge cases are 1 and 0)
		// No negative numbers
		if(num <= 1) {
			return false;
		}
		
		for (int i = 2; i < num; i++) {
			if(num % i == 0) {
				return false;
			}
		}
		return true;
	}

}
