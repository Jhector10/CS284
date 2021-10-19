
public class FactorialExample {
	
	public static int factorial(int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}if (n == 0) {
			return 1;
		} else {
			return n * factorial(n - 1);
		}
	}
	
	public static int factorialAux(int n, int a) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}if (n == 0) {
			return a;
		} else {
			return factorialAux(n-1, n*a);
		}
	}
	
	public int mystery(int a, int b) {
		if (b == 0) {
			return 0;
		} if (b % 2 == 0) {
			return mystery(a+a, new Integer(b/2));
		} else {
			return mystery(a+a, new Integer(b/2)) + a;
		}
	}
	
	public static int multiply(int a, int b) {
		if (b == 0) {
			return 0;
		} else {
			return a + multiply(a, b-1);
		}
	}

	public static void main(String[] args) {
		FactorialExample fe = new FactorialExample();
		
		System.out.println("The result of factorial(4): " + fe.factorial(4));
		System.out.println("The result of factorial(5): " + fe.factorial(5));
		
		System.out.println();

		System.out.println(fe.mystery(4, 4));
		System.out.println(fe.mystery(2, 2));
		System.out.println(fe.mystery(2, 4));
		System.out.println(fe.mystery(3, 5));
		System.out.println(fe.mystery(6, 7));
		System.out.println(fe.mystery(5, 5));
	}

}
