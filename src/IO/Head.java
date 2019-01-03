package IO;
import java.io.*;

/**
 * A program that prints out the first 10 lines of each file specified on the command line
 */
public class Head {
    /**
     * This is main() method of the standalone program. After checking
     * its arguments, it invokes the Head.head() to prints out the first 10 lines of the file
     */
    public static void main(String[] args) {
        if(args.length != 1) {         // Check command-line arguments
            System.err.println("Usage: java Head <filename>");
            System.exit(0);
        }
        // Call the head() method
        try { head(args[0]); }
        catch(IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void head(String filename) {
        File f;
        BufferedReader br = null;

        try {
            f = new File(filename);
            br = new BufferedReader(new FileReader(f));
            String str = null;
            int line = 1;

            // breaks when null is read or the first 10 lines have been printed
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
