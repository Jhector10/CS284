/*<listing chapter="CS7" number="1">*/
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.Scanner;

/** Class to check for balanced parentheses.
 *  @author Koffman and Wolfgang
 **/
public class ParenChecker {

    // Constants
    /** Set of opening parenthesis characters. */
    private static final String OPEN = "([{";
    /** Set of closing parenthesis characters, matches OPEN. */
    private static final String CLOSE = ")]}";

    /**
     * Test the input string to see that it contains balanced
     * parentheses. This method tests an input string to see
     * that each type of parenthesis is balanced. '(' is matched
     * with ')', '[' is matched with ']', and
     * '{' is matched with '}'.
     * @param expression A String containing the expression to
     *        be examined
     * @return true if all the parentheses match
     */
    public static boolean isBalanced(String expression) {
        // Create an empty stack.
        Deque<Character> s = new ArrayDeque<>();
        boolean balanced = true;
        try {
            int index = 0;
            while (balanced && index < expression.length()) {
                char nextCh = expression.charAt(index);
                if (isOpen(nextCh)) {
                    s.push(nextCh);
                } else if (isClose(nextCh)) {
                    char topCh = s.pop();
                    balanced =
                        OPEN.indexOf(topCh) == CLOSE.indexOf(nextCh);
                }
                index++;
            }
        } catch (NoSuchElementException ex) {
            balanced = false;
        }
        return (balanced && s.isEmpty());
    }

    /**
     * Method to determine whether a character is one of the
     * opening parentheses.
     * @param ch Character to be tested
     * @return true if ch is one of the opening parentheses
     */
    private static boolean isOpen(char ch) {
        return OPEN.indexOf(ch) > -1;
    }

    /**
     * Method to determine whether a character is one of the
     * closing parentheses.
     * @param ch Character to be tested
     * @return true if ch is one of the closing parentheses
     */
    private static boolean isClose(char ch) {
        return CLOSE.indexOf(ch) > -1;
    }

    /**
     * main method. Ask the user for a string and
     * call the ParenChecker to see whether the parentheses
     * are balanced.
     * @param args Not used
     */
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter an expression containing parentheses");
        String expression = in.nextLine();
        if (ParenChecker.isBalanced(expression)) {
            System.out.println(expression
                    + " is balanced");
        } else {
            System.out.println(expression
                    + " is not balanced");
        }
    }
}
/*</listing>*/
