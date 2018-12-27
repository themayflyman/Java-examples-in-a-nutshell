import java.io.*;

/**
 * This program reads lines of text from the user, encodes them using the
 * trivial "Rot13" substitution cipher, and then prints out the encoded lines.
 */
public class Rot13Input {
    public static void main(String[] args) throws IOException {
        // Get set up to read lines of text from the user
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        for(;;) {
            System.out.print("> ");
            String line = in.readLine();
            if ((line == null) || line.equals("quit"))
                break;
            StringBuffer buf = new StringBuffer(line);
            for(int i=0; i<buf.length(); i++) {
                buf.setCharAt(i, rot13(buf.charAt(i)));
            }
            System.out.println(buf);
        }
    }

    /**
     * This method preforms the Rot13 substitution cipher. It "rotates"
     * each letter 13 places through the alphabet. Since the Latin alphabet
     * has 26 letters, this method both encodes and decodes.
     */
    public static char rot13(char c) {
        if ((c >= 'A') && (c <= 'Z')) {
            c += 13;
            if (c > 'Z') c -= 26;
        }
        if ((c >= 'a') && (c <= 'z')) {
            c += 13;
            if (c > 'z') c -= 26;
        }
        return c;
    }
}
