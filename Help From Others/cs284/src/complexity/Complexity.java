package complexity;
//creator: Simrun Heir
//Assigment: CS 284 hw 2
//Honor Code: "I pledge my Honor that I have abided by the Stevens Honor System."
public class Complexity{
	
	/**implements an operation that has a time complexity of O(n)
	 * 
	 * @param n
	 */
	public void method0(int n) {
		int counter = 0;
		for(int i=0; i<n; i++) {
			System.out.println("Operation "+counter);
			counter++;
		}
	}
	/**method with time complexity O(n^2)
	 * 
	 * @param n
	 */
	public static void method1(int n) {
		int counter = 0;
		//loop with time complexity O(n^2)
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.println("Operation "+counter);
				counter++;
			}
		}
		System.out.println(counter);
	}
	/**method with time complexity O(n^3)
	 * 
	 * @param n
	 */
	public static void method2(int n) {
		int counter = 0;
		//loop with time complexity O(n^3)
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				for(int k=0; k<n; k++) {
					System.out.println("Operation "+counter);
					counter++;	
				}
			}
		}
		System.out.println(counter);
	}
	/**method with time complexity O(log n)
	 * 
	 * @param n
	 */
	public static void method3(int n) {
		int counter = 0;
		//log by default (in math) is base 10
		for(int i=1; i<n; i *= 10) {
			System.out.println("Operation "+counter);
			counter++;
		}
		System.out.println(counter);
	}
	/**method with time complexity O(n log n)
	 * 
	 * @param n
	 */
	public static void method4(int n) {
		int counter = 0;
		for(int i=1; i<n; i++) {
			for(int j=1; j<n; j *= 10) {
				System.out.println("Operation "+counter);
				counter++;
			}
		}
		System.out.println(counter);
	}
	/**method with time complexity O(log log n)
	 * 
	 * @param n
	 */
	public static void method5(int n) {
		int counter = 0;
		for(int i=1; i<n; i *= 10) {
			for(int j=1; j<n; j *= 10) {
				System.out.println("Operation "+counter);
				counter++;
			}
		}
		System.out.println(counter);
	}
	/**Extra Credit: method with time complexity O(2^n)
	 * use recursion for method
	 * @param n
	 */
	public static int method6(int n) {	
		int counter = 0;
		//recursive method with time complexity O(2^n)
		if (n == 0) {
			return counter;
		}else if(n == 1) {
			counter = 1;
			return counter;
		}else {
			return method6((n-2)) + method6((n-1));
		}
	}
	
	
	public static void main(String args[]) {
		int h = 10;
		int f = 5;
		int g = 3;
		
		System.out.println("method1 test. O(n^2), n="+g);
		method1(g);
		System.out.println("method2 test. O(n^3), n="+g);
		method2(g);
		System.out.println("method3 test. O(log n), n="+h);
		method3(h);
		System.out.println("method4 test. O(n log n), n="+h);
		method4(h);
		System.out.println("method5 test. O(log log n), n="+h);
		method5(h);
		System.out.println("method6 test. O(2^n), n="+g);
		System.out.println(method6(g));
	}
}