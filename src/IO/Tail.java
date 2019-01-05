package IO;
import java.io.*;

/**
 * A program that prints out the last 10 lines of each file specified on the command line
 */
public class Tail {
    public static void main(String[] args) {
        if (args.length != 1) {         // Check command-line arguments
            System.err.println("Usage: java Head <filename>");
            System.exit(0);
        }
        // Call the tail() method
        try {
            tail(args[0]);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void tail(String filename) {
        File f;
        BufferedReader br = null;

        try {
            f = new File(filename);
            br = new BufferedReader(new FileReader(f));

            while(line <= 10) {
                System.out.println(line + " " + br.readLine());
                line++;
            }
        }
        catch(IOException e) { System.err.println(e.getMessage()); }
        finally {
            if(br != null){
                try { br.close(); }
                catch(IOException e1) {}
            }
        }
    }
}
