import java.io.*;

/**
 * A program that interactively reads lines of input from the user and prints
 * them back out, reversed. The program exits if the user types "tiuq"
 */
public class InputReverser {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        for(;;) {
            System.out.print("<InputReverser> ");
            String line = in.readLine();

            if(line.equals("ituq")) break;

            for(int i=line.length()-1; i>=0; i--){
                System.out.print(line.charAt(i));
            }
            System.out.println();
        }
    }
}
