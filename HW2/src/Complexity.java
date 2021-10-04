
/*
 * Code done by Joshua Hector
 * I pledge my honor that I have abided by the Stevens Honor System
 */

import java.lang.*;

public class Complexity {
	
	public void method0(int n) {
		int counter =0;
		for (int i = 0; i < n; i++) {
			System.out.println(" Operation "+ counter );
			counter++;
			}
		}
	
	public static void method1(int n) {
		// a method that has time complexity O(n^2)
		int counter = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.println("Operation: " + counter);
				counter++;
			}
		}
	}
	
	public static void method2(int n) {
		// a method that has time complexity O(n^3)
		int counter = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int x = 0; x < n; x++) {
					System.out.println("Operation: " + counter);
					counter++;
				}
			}
		}
	}
	
	public static void method3(int n) {
		// a method that has time complexity O(log n)
		int counter = 0;
		for (int i = 1; i < n; i *= 2) {
			System.out.println("Operation: " + counter);
			counter++;
		}
	}
	
	public static void method4(int n) {
		// a method that has time complexity O(n*log n)
		int counter = 0;
		for (int i = 1; i < n; i *= 2) {
			for (int j = 0; j < n; j++) {
				System.out.println("Operation: " + counter);
				counter++;
			}
		}
	}
	
	public static void method5(int n) {
		// a method that has time complexity O(log (log n))
		int counter = 0;
		for (int i = n; i > 2; i /= Math.sqrt(i)) {
			System.out.println("Operation: " + counter);
			counter++;
		}
	}

	public static void main(String[] args) {
		// Testing the Complexity's
		
		Complexity c = new Complexity();
		c.method0(2);
		System.out.println();
		c.method1(2);
		System.out.println();
		c.method2(2);
		System.out.println();
		c.method3(5);
		System.out.println();
		c.method4(5);
		System.out.println();
		c.method5(16); 
		
		
	}

}
