package Networking;
import java.io.*;
import java.net.*;
import java.util.Properties;

/**
 * For this exercise, write a client program named Checkmail that uses the POP
 * protocol to check a user's mail. It should output the number of messages that
 * are waiting to be retrieved and display the From line of each message. This
 * client should not use the POP dele command to delete messages from
 * the server; it should simple display a summary of the messages waiting to be
 * retrieved. In order to read mail messages, this client has to know the host-
 * name of POP server and has to send a username and password to the
 * server. Your program may obtain the hostname, username, and password
 * from the command line or by prompting the user, but should ideally get this
 * information by reading a configuration file. Consider a java.util.Properties
 * object to implement such a configuration file.
 */
public class Checkmail {
    public static void main(String[] args) {
        try {
            // Check the arguments
            if (args.length != 4) {
                throw new IllegalArgumentException("Wrong number of arguments");
            }

            String host = args[0];
            int port = Integer.parseInt(args[1]);
            String usr = args[2];
            String pwd = args[3];

            // Connect to the mail server
            Socket s = new Socket(host, port);

            // Set up streams for reading from and writing to the sever.
            // The from_server stream is final for use in the inner class below
            final Reader from_server = new InputStreamReader(s.getInputStream());
            PrintWriter to_server = new PrintWriter(s.getOutputStream());

            // Set up streams for reading from and writing to the console
            // The to_user stream is final for use in the anonymous class below
            BufferedReader from_user =
                    new BufferedReader(new InputStreamReader(System.in));
            // Pass true for auto-flush on println()
            final PrintWriter to_user = new PrintWriter(System.out, true);

            // Tell the user that we've connected
            to_user.println("Connected to" + s.getInetAddress() +
                    ":" + s.getPort());

            // Authorization
            to_server.println("USER " + usr);
            to_server.println("PASS " + pwd);
            to_server.println("LIST");
            to_server.flush();


            // Create a thread that gets output from the server and displays
            // it to the user. We use a separate thread for this so that we
            // can receive asynchronous output
            Thread t = new Thread() {
                public void run() {
                    char[] buffer = new char[1024];
                    int chars_read;
                    try {
                        // Read characters until the stream closes
                        while((chars_read = from_server.read(buffer)) != -1) {
                            // Loop through the array of characters, and
                            // print them out, converting all \n characters
                            // to the local platform's line terminator.
                            // This could be more efficient, but it is probably
                            // faster than the network is, which is good enough
                            for(int i=0; i<chars_read; i++) {
                                if (buffer[i] == '\n') to_user.println();
                                else to_user.print(buffer[i]);
                            }
                            to_user.flush();
                        }
                    }
                    catch (IOException e) { to_user.println(e); }

                    // When the server closes the connection, the loop above
                    // will end. Tell the user what happened, and call
                    // System.exit(), causing the main thread to exit along
                    // with this one.
                    to_user.println("Connection closed by server.");
                    System.exit(0);
                }
            };

            // We set the priority of the server-to-user thread above to be
            // one level higher than the main thread. We shouldn't have to do
            // this, but on some operating systems, output sent to the console
            // doesn't appear when a thread at the same priority level is
            // blocked waiting for input from the console.
            t.setPriority(Thread.currentThread().getPriority() + 1);

            // Now start the server-to-user thread
            t.start();

            String line;
            while((line = from_user.readLine()) != null) {
                if (line.equals("QUIT") || line.equals("quit")) {
                    to_server.println(line + "\n");
                }
                to_server.println("RETR" + line + "\n");
                to_server.flush();
            }


            // If the user types a Ctrl-D (Unix) or Ctrl-Z (Windows) to end
            // their input, we'll get an EOF, and the loop above will exit.
            // When this happens, we stop the server-to-user thread and close
            // the socket.

            s.close();
            to_user.println("Connection closed by client.");
        }
        catch (Exception e) {
            System.err.println(e);
            System.err.println("Usage: java Checkmail <MailServerURL> <port>" +
                                " <usr> <pwd>");
        }
    }
}
