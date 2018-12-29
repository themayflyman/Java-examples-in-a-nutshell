package JavaBasic;

import java.io.*;

/**
 * This program displays factorials as the user enters values interactively
 */
public class FactQuoter {
    public static void main(String[] args) throws IOException {
        // This is how we set things up to real lines of text
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // Loop forever
        for(;;) {
            // Display a prompt to the user
            System.out.print("<JavaBasic.FactQuoter> ");
            // Read a line from the user
            String line = in.readLine();
            // If we reach the end-of-file,
            // or if the user types "quit", then quit
            if ((line == null) || line.equals("quit")) break;
            // Try to parse the line, and compute and print the factorial
            try {
                int x = Integer.parseInt(line);
                System.out.println(x + "! = " + Factorial4.factorial(x));
            }
            // If anything goes wrong, display a generic error message
            catch (Exception e) {System.out.println("Invalid Input");}
        }
    }
}
