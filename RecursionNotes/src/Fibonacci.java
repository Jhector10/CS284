
public class Fibonacci {
	
	private static int ffib(int prevFibo, int currentFibo, int n) { 
		if (n == 0) {
			return currentFibo;
		} else {
			return ffib(currentFibo, prevFibo + currentFibo, n - 1);
		}
	}
	
	public static int ffibonacciStart(int n) {
		return ffib(0, 1, n);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
