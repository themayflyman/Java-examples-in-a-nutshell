package IO;
import java.io.*;

/**
 * This class is a static method delete() and a standalone program that
 * deletes a specified file or directory.
 */
public class Delete {
    /**
     * This is the main() method of the standalone program. After checking
     * its arguments, it invokes the Delete.delete() method to do the deletion.
     */
    public static void main(String[] args) {
        if(args.length != 1) {       // Check command-line arguments
            System.err.println("Usage: java Delete <file or directory>");
            System.exit(0);
        }
        // Call the delete() and display any error messages it throws
        try { delete(args[0]); }
        catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * The static method that does the deletion. Invoked by main(), and
     * designed for use by other programs as well. It first makes sure that
     * the specified file or directory is deleteable before attempting to
     * delete it. If there is a problem, it throws an
     * IllegalArgumentException.
     */
    public static void delete(String filename) {
        // Create a file object to represent the filename
        File f = new File(filename);

        // Make sure the file or directory exists and isn't write protected
        if(!f.exists()) fail("Delete: no such file or directory: " + filename);
        if(!f.canWrite()) fail("Delete: write protected: " + filename);

        // If it is a directory, make sure it is empty
        if(f.isDirectory()) {
            String[] files = f.list();
            if(files.length > 0)
                fail("Delete: directory not empty: " + filename);
        }

        // If we passed all the tests, then attempt to delete it
        boolean success = f.delete();

        // And throw an exception if it didn't work for some (unknown) reason.
        // For example, because of a bug with Java 1.1.1 on Linux.
        // directory deletion always fails
        if(!success) fail("Delete: deletion failed");
    }

    /* A convenience method to throw an exception */
    protected static void fail(String msg) throws IllegalArgumentException {
        throw new IllegalArgumentException(msg);
    }
}