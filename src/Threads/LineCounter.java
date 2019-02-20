package Threads;
import java.io.*;

/**
 * This program takes a list of filename on the command line and then print out the number of the lines of
 * each file. The program should create one thread for each file and use these threads to count the lines in
 * the file at the same time. Use java.io.LineNumberReader to help you count the lines. You'll probably want
 * to define define a LineCounter class that extends Thread or implements Runnable. Now write a variant of your
 * program that uses LineCounter to read the lines sequentially, rather than at the same time. Compare performances
 * of multi-threaded and single-threaded program, using System.currentTimeMills() to determine elapsed time. Compare
 * the performance of two program for two, five and ten files.
 */
public class LineCounter extends Thread {
    private File file;

    public void run() {
        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fr != null) {
            LineNumberReader lnr = new LineNumberReader(fr);
            System.out.println(file.getName() + ": " + lnr.getLineNumber() + " lines.");
        }
    }

    public void setFile(File file) {
        this.file = file;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // Multi-Threaded
        LineCounter lc = new LineCounter();
        for(String filename: args) {
            File file = new File(filename);
            lc.setFile(file);
            // start() will start a new thread and call run() method
            lc.start();
        }

        long pause = System.currentTimeMillis();

        // Single-Threaded
        LineCounter lc2 = new LineCounter();
        for(String filename: args) {
            File file2 = new File(filename);
            lc2.setFile(file2);
            lc2.run();
        }
        long end = System.currentTimeMillis();
        System.out.println("multi-threaded performance: " + (pause - start) + " milliseconds");
        System.out.println("single-threaded performance: " + (end - pause) + " milliseconds");
    }
}
