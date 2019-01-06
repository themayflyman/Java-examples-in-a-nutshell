package IO;
import java.io.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FileLister2 {
    public static void main(String[] args) {
        if(args.length != 2 && args.length != 1) {         // Check command-line arguments
            System.err.println("Usage: java FileLister2 <directory>");
            System.exit(0);
        }
        // Call the display() method
        try {
            if(args.length == 2)
                display(args[0], args[1]);
            else
                display(args[0], null);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void display(String directory, String option) {
        Map<String, Long> filesInfo = fileInfo(directory);
        for(Map.Entry<String, Long> f: filesInfo.entrySet())
            System.out.println(f.getKey() + ": " + f.getValue() + " bytes");
    }

    public static Map<String, Long> fileInfo(String directory) {
        Map<String, Long> fileInfo = new HashMap<String, Long>();
        File dir = new File(directory);
        if(!dir.exists())
            System.err.println("FileLister2: no such directory");
        else if(!dir.isDirectory())
            System.err.println("FileLister2: " + dir.getName() +
                               ": is not a directory");
        File[] files = dir.listFiles();
        if((files != null) && files.length>0) {
            for (File file : files) {
                String filename = file.getName();
                if (!file.exists())
                    System.err.println("FileLister2: " + filename +
                            ": no such file or directory");
                // if it's a directory, call dirSize() which will recursively adds up
                // all the size of subdirectories or the files it contents
                else if (file.isDirectory())
                    fileInfo.put(filename, dirSize(file));
                // or if it's a file, prints out its name and size
                else
                    fileInfo.put(filename, file.length());
            }
        }
        return fileInfo;
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
