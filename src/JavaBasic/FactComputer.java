package JavaBasic;

/**
 * This program computes and displays the factorial of a number specified
 * on the command line. It handles possible user input errors with try/catch.
 */
public class FactComputer {
    public static void main(String[] args) {
        // Try to compute a factorial
        // If something goes wrong, handle it in the catch clause below
        try {
            int x = Integer.parseInt(args[0]);
            System.out.println(x + "! = " + Factorial4.factorial(x));
        }
        // The user forgot to specify an argument
        // Throw if args[0] if undefined
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You must specify an argument");
            System.out.println("Usage: java JavaBasic.FactComputer <number>");
        }
        // The argument is not an number
        // Thrown by Integer.parseInt()
        catch (NumberFormatException e) {
            System.out.println("The argument you specify must be an integer");
        }
        // The argument is < 0.
        // Thrown by JavaBasic.Factorial4.factorial()
        catch (IllegalArgumentException e) {
            // Display the message sent by the factorial() method
            System.out.println("Bad argument: " + e.getMessage());
        }
    }
}
