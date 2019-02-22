package Networking;
import java.io.*;
import java.net.*;
import java.util.Date;

/**
 * Using the URLConnection techniques demonstrated in Example 5-2, write a
 * program that prints the modification date of a specified URL.
 */
public class GetURLModificationDate {
    /* Use the URLConnection techniques to prints the modification date */
    public static void printModificationDate(URL url) throws IOException {
        URLConnection c = url.openConnection();
        c.connect();

        System.out.println("  Last Modified: " + new Date(c.getLastModified()));
        System.out.println("  Modified Since: " +
                           new Date(c.getIfModifiedSince()));
    }

    public static void main(String[] args) {
        try { printModificationDate(new URL(args[0])); }
        catch (IOException e) {
            System.err.println(e);
            System.err.println("Usage: java GetURLModificationDate <url>");
        }
    }
}
