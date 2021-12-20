package stackqueue;

public class PalindromeChecker{
	//data fields
	private String inputStr;
	private MyStack<Character> chStack;
	
	PalindromeChecker(String input) {
		inputStr = input;
		chStack = new MyStack<>();
		fillStack(input);
	}
	
	private void fillStack(String str) {
		//fill stack with all the letters from str
		//s.charAt(i) is i-th character in the string s
		for (int i=0; i<str.length(); i++) {
			if (str.charAt(i)!=' ') {
				chStack.push(str.charAt(i));
			}
		}
	}
	
	private String reverse() {
		StringBuilder s = new StringBuilder();
		
		while(!chStack.isEmpty()) {
			s.append(chStack.pop());
		}
		return s.toString();
	}
	
	public boolean isPalindrome() {
		return inputStr.replaceAll("\\s", "").equalsIgnoreCase(reverse());
	}
	
}