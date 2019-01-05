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
        // A buffered reader to count the total lines of the file
        BufferedReader br = null;
        // A buffered reader to read or print the file
        BufferedReader br2 = null;

        try {
            f = new File(filename);
            br = new BufferedReader(new FileReader(f));
            br2 = new BufferedReader(new FileReader(f));
            String str = null;
            long len = 0;
            while((str = br.readLine()) != null)
                len++;
            if(len < 10) {
                for(int i=0; i<len; i++)
                    System.out.println(i + " " + br2.readLine());
            }
            else {
                for(long line=1;line<len; line++)
                    if(line > len - 10)
                        System.out.println(line + " " + br2.readLine());
                    else
                        br2.readLine();
            }
        }
        catch(IOException e) { System.err.println(e.getMessage()); }
        finally {
            if(br != null){
                try { br.close(); }
                catch(IOException e1) { System.err.println(e1.getMessage()); }
            }
            if(br2 != null){
                try { br2.close(); }
                catch(IOException e2) { System.err.println(e2.getMessage()); }
            }
        }
    }
}
