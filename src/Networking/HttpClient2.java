package Networking;
import java.io.*;
import java.net.*;

/**
 * Modify the HttpClient program of Example 5-4 so that it uses a newer ver-
 * sion of the HTTP protocol. To do this, you have to send a somewhat more
 * complicated GET request to the web server. Use the HttpMirror program of
 * Example 5-5 to find what form this request should take. HTTP Versions 1.0
 * and later add a version number to the GET request and follow the GET line
 * with a number of header lines followed by a blank line that serves to termi-
 * nate the request.
 * For this exercise, the only header you need to include is the User-Agent line,
 * which should identify the web client that you are using. Since you are writing
 * your own web client, you can give it any name you like! Be sure to follow
 * your GET request and User-Agent header with a blank line, or the web server
 * will keep waiting for more headers and will never respond to your request.
 * When you get this program working, you should notice that web servers
 * respond differently to requests from it than they did to requests from the
 * original HttpClient program. When a client requests data using HTTP 1.0 or
 * 1.1, the server sends a version number, a status code, and a number of
 * response header lines before it sends the actual requested file.
 */
public class HttpClient2 {
    public static void main(String[] args) {
        try {
            // Check the arguments
            if ((args.length != 1) && (args.length != 2)) {
                throw new IllegalArgumentException("Wrong number of arguments");
            }

            // Get an output stream to write URL connects to
            OutputStream to_file;
            if (args.length == 2) {
                to_file = new FileOutputStream(args[1]);
            }
            else {
                to_file = System.out;
            }

            // Now use the URL class to parse the user-specified URL into
            // its various parts.
            URL url = new URL(args[0]);
            String protocol = url.getProtocol();
            if (!protocol.equals("http")) {
                throw new IllegalArgumentException("Must use 'http:' protocol");
            }
            String host = url.getHost();
            int port = url.getPort();
            if (port == -1) port = 80;  // if no port, use the default HTTP port
            String filename = url.getFile();

            // Open a network socket connection to the specified host and port
            Socket socket = new Socket(host, port);

            // Get input and output streams for the socket
            InputStream from_server = socket.getInputStream();
            PrintWriter to_server = new PrintWriter(socket.getOutputStream());

            // Send the HTTP GET command to the Web server, specifying the file
            // Updated to support HTTP 1.1
            to_server.print("GET / HTTP/1.1" + filename + "\n\n" +
                            "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_3) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.0.3 Safari/605.1.15\n");
            to_server.flush();  // Send it right now

            // Now read the server's response, and write it to the file
            byte[] buffer = new byte[4096];
            int bytes_read;
            while((bytes_read = from_server.read(buffer)) != -1) {
                to_file.write(buffer, 0, bytes_read);
            }

            // When the server closes the connection, we close our stuff
            socket.close();
            to_file.close();
        }
        catch (Exception e) {
            System.err.println(e);
            System.err.println("Usage: java HttpClient2 <URL> [<filename>]");
        }
    }
}
