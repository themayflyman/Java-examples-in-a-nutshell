package JavaBasic;

/**
 * This program prints out all its command-line arguments.
 **/
public class Echo {
    public static void main(String[] args) {
       int i = 0;                            // Initialize the loop variable
       while(i < args.length) {              // Loop until the end of array
           System.out.print(args[i] + " ");  // Print each argument out
           i++;                              // Increment the loop variable
       }
       System.out.println();                 // Terminate the line
    }
}
