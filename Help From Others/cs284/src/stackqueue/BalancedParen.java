package stackqueue;

public class BalancedParen {
	private String inputStr;
	private MyStack<Character> charStack;
	
	BalancedParen(String input){
		inputStr = input;
		charStack = new MyStack<>();
		fillStack(input);
	}
	
	private void fillStack(String str) {
		//fill stack with all the letters from str
		//s.charAt(i) is i-th character in the string s
		for (int i=0; i<str.length(); i++) {
			if (str.charAt(i)!=' ') {
				charStack.push(str.charAt(i));
			}
		}
	}
	
	public static boolean isBalanced(String s) {//change this so it works as .isBalanced with no string input
		MyStack<Character> leftChars = new MyStack<>();
		if (s.length()==0) {
			throw new IllegalStateException("isBalanced: input string is empty");
		}
		for(int i=0; i< s.length(); i++) {
			if (s.charAt(i)=='(' || s.charAt(i)=='{' || s.charAt(i)=='[') {//push left brackets
				leftChars.push(s.charAt(i));
			} else {
				if(leftChars.isEmpty()) {//unmatched right char
					return false;
				}
			}
			if (s.charAt(i)==')'&&leftChars.top()!='(' || 
				s.charAt(i)=='}'&&leftChars.top()!='{' || 
				s.charAt(i)==']'&&leftChars.top()!='[' ) {//make sure there is a match on right and left
				return false;
			}
			leftChars.pop(); //good match then pop
		}
		return leftChars.isEmpty(); //if stack is empty, return true, means balanced
	}

}