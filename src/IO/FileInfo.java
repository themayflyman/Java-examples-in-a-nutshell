package IO;
import java.io.*;
import java.lang.Character;

/**
 * A program that counts and reports the number of lines, words and
 * characters in a specified file. Use static methods of the java.lang.Character
 * class to determine whether a given character is a space (and therefore the
 * boundary between two words).
 */
public class FileInfo {
    public static void main(String[] args) {
        if (args.length != 1) {         // Check command-line arguments
            System.err.println("Usage: java FileInfo <filename>");
            System.exit(0);
        }
        // Call the info() method
        try {
            info(args[0]);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void info(String filename) {
        File f;
        BufferedReader br = null;

        try {
            f = new File(filename);
            br = new BufferedReader(new FileReader(f));
            String str;
            char tmp = 0;
            long lines = 0;
            long words = 0;
            long cs = 0;
            while((str = br.readLine()) != null) {
                for(char s : str.toCharArray()) {
                    // multiple whitespaces are counted as one whitespace
                    if(Character.isWhitespace(s) && !Character.isWhitespace(tmp))
                        words++;
                    cs++;
                    tmp = s;
                }
                lines++;
            }
            System.out.println("lines: " + lines);
            System.out.println("words: " + words);
            System.out.println("characters: " + cs);
        }
        catch(IOException e) { System.err.println(e.getMessage()); }
        finally {
            if(br != null) {
                try { br.close(); }
                catch (IOException e1) { System.err.println(e1.getMessage()); }
            }
        }
    }
}
