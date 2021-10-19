/*<listing chapter="4" number="2">*/
import java.util.ArrayDeque;
import java.util.Deque;

/** Class with methods to check whether a string is a palindrome.
 *  @author Koffman &amp; Wolfgang
 **/
public class PalindromeFinder {

    /**
     * Private constructor, since this class only contains static methods.
     */
    private PalindromeFinder() {}

    /** Method to fill a stack of characters from an input string. */
    private static Deque<Character> fillStack(String inputString) {
        Deque<Character> charStack = new ArrayDeque<>();
        for (int i = 0; i < inputString.length(); i++) {
            charStack.push(inputString.charAt(i));
        }
        return charStack;
    }

    /**
     * Method to build the reverse of a string by pushing them onto a stack
     * and then building a string containing the characters in a stack.
     * post: The stack is empty.
     * @return The string containing the words in the stack
     */
    private static String buildReverse(String str) {
        Deque<Character> charStack = fillStack(str);
        StringBuilder result = new StringBuilder();
        while (!charStack.isEmpty()) {
            // Remove top item from stack and append it to result.
            result.append(charStack.pop());
        }
        return result.toString();
    }

    public static boolean isPalindrome(String str) {
        return str.equalsIgnoreCase(buildReverse(str));
    }
    
    /*<exercise chapter="4" type="programming-project" number="1">*/
    /**
     * Method to determine if a string is a palindrome based only on
     * letters in the string.
     * @param str The string to evaluate
     * @return true if the input is a palindrome
     */
    public static boolean isPalindromeLettersOnly(String str) {
        Deque<Character> charStack = new ArrayDeque<>();
        Deque<Character> charQueue = new ArrayDeque<>();
        // Insert only the letters into both the stack and queue
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isLetter(c)) {
                charStack.push(c);
                charQueue.offer(c);
            }
        }
        // If this is a palindrome, characters popped from the stack should
        // be in the same order as characters removed from the queue.
        for (int i = 0; i < charStack.size(); i++) {
            char stackChar = charStack.pop();
            char queueChar = charQueue.remove();
            if (stackChar != queueChar)
                return false;
        }
        // All characters matched
        return true;
    }
	/*</exercise>*/
    
    /*<exercise chapter="4" type="programming-project" number="6">*/
    public static boolean isPalindromeAlternate(String str) {
        Deque<Character> stack1 = fillStack(str);
        Deque<Character> stack2 = new ArrayDeque<>();
        int numChars = stack1.size();
        for (int i = 0; i < numChars/2; i++) {
            stack2.push(stack1.pop());
        }
        if (numChars % 2 == 1) {
            stack1.pop();
        }
        return equalStackIgnoreCase(stack1, stack2);
    }
    
    public static boolean equalStackIgnoreCase(Deque<Character> s1, Deque<Character> s2) {
        if (s1.size() != s2.size()) {
            return false;
        }
        while (!s1.isEmpty()) {
            if (Character.toLowerCase(s1.pop()) != Character.toLowerCase(s2.pop())) {
                return false;
            }
        }
        return true;
    }
    /*</exercise>*/
}
/*</listing>*/
