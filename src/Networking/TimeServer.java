package Networking;
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Write a simple server that reports the current time (in textual form) to any
 * client that connects. Use Example 5-5 HttpMirror, as a framework for your
 * server. This server should simple output the current time and close the con-
 * nection, without reading anything from the client. You need to choose a port
 * number that your service listens on. Use the GenericClient program of
 * Example 5-8 to connect to this port and test your program. To do this,
 * encode the appropriate port number into the URL. HttpClient sends an
 * extraneous GET request to the time server, but it still displays the server's
 * response.
 */
public class TimeServer {
    public static void main(String[] args) {
        try {
            // Get the port to listen on
            int port = Integer.parseInt(args[0]);
            // Create a ServerSocket to listen on that port
            ServerSocket ss = new ServerSocket(port);
            // Now enter an infinite loop, waiting for & handling connections
            for(;;) {

                // Wait for a client to connect. This method will block;
                // when it returns the socket will be connected to the client
                Socket client = ss.accept();

                // Get input and output streams to talk to the client
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(client.getOutputStream());

                // Start sending out reply, using the HTTP 1.0 protocol
                out.print("HTTP/1.0 200 \n");           // Version & status code
                out.print("Content-Type: text/plain\n"); // The type of data
                out.print("\n");                         // End of the header

                // Output current time
                out.print(LocalDateTime.now() + "\n");

                // Close socket, breaking the connection to the client, and
                // closing the input and output streams
                out.close();       // Flush and close the output stream
                in.close();        // Close the input stream
                client.close();    // CLose the socket itself
            }  // Now loop again, waiting for the next connection
        }
        // If anything goes wrong, print an error message
        catch (Exception e) {
            System.err.println(e);
            System.err.println("Usage: java HttpMirror <port>");
        }
    }
}
