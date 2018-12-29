package JavaBasic;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

/**
 * A program that takes two numbers and a string as command-line arguments
 * and prints out the substring of the string specified by the two numbers.
 * For example:
 *     % java JavaBasic.Substring JavaBasic.Hello 1 3
 * prints out:
 *     ell
 */
public class Substring {
    public static void main(String[] args) {
        try {
            String s = args[0];
            int from = Integer.parseInt(args[1]);
            int to = Integer.parseInt(args[2]);

            if((from < 0) || (from > s.length()))
                throw new StringIndexOutOfBoundsException("You must specify the index " +
                                                          "within the range of the string");
            if((to < 0) || (to > s.length()))
                throw new StringIndexOutOfBoundsException("You must specify the index " +
                                                          "within the range of the string");
            for(int i=from; i<=to; i++)
                System.out.print(s.charAt(i));

            System.out.println();       // Terminate the line
        }
        // The user forgot to specify enough arguments,
        // thrown if args[0], args[1], args[2] is undefined
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You must specify three arguments and ");
            System.out.println("Usage: java JavaBasic.Substring <String> <Integer> <Integer>");
        }
        // The second number or third number is greater than the length of the string or less than 0
        catch (StringIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        // thrown when the second or the third number isn't a integer
        catch (NumberFormatException e) {
            System.out.println("The last two arguments you specify must be integer");
        }
    }
}
