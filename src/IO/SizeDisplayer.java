package IO;
import java.io.*;

/**
 * A program that adds up and reports the size of all files in a specified
 * directory. It should recursively scan any subdirectories, summing and report-
 * ing the size of the files that they contain, and incorporate those directory
 * sizes into its final output.
 */
public class SizeDisplayer {
    public static void main(String[] args) {
        if (args.length != 1) {         // Check command-line arguments
            System.err.println("Usage: java SizeDisplayer <directory>");
            System.exit(0);
        }
        // Call the size() method
        try {
            display(args[0]);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void display(String directory) {
        // Convert the string to a File object, and check that the dir exists
        File dir = new File(directory);
        if(!dir.exists())
            System.err.println("FileSize: no such directory");

        File[] files = dir.listFiles();
        // Check if files is null, if it's true, the second operand won't be evaluated
        // which avoid NullPointerException
        if(files != null && files.length > 0) {
            java.util.Arrays.sort(files);
            for (File file : files) {
                String filename = file.getName();
                if (!file.exists())
                    System.err.println("SizeDisplayer: " + filename +
                            ": no such file or directory");
                // if it's a directory, call dirSize() which will recursively adds up
                // all the size of subdirectories or the files it contents
                else if (file.isDirectory())
                    System.out.println(filename + ": " +
                            dirSize(file) + " bytes");
                else
                    System.out.println(filename + ": " + file.length() + " bytes");
            }
        }
    }

    private static long dirSize(File dir) {
        long size = 0;
        File[] files = dir.listFiles();
        if(files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory())
                    size += dirSize(file);
                else
                    size += file.length();
            }
        }
        return size;
    }
}
