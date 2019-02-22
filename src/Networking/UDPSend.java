package Networking;
import java.io.*;
import java.net.*;

/**
 * This class sends the specified text or file as a datagram to the
 * specified port of the specified host.
 */
public class UDPSend {
    public static final String usage =
            "Usage: java UDPSend <hostname> <port> <msg> .. \n" +
            "   or: java UDPSend <hostname> <port> -f <file>";

    public static void main(String[] args) {
        try {
            // Check the number of arguments
            if (args.length < 3)
                throw new IllegalArgumentException("Wrong number of args");
            // Parse the arguments
            String host = args[0];
            int port = Integer.parseInt(args[1]);

            // Figure out the message to send
            // If the third argument is -f, then send the contents of the file
            // specified as the fourth argument. Otherwise, concatenate the
            // third and all remaining arguments and send that.
            byte[] message;
            if (args[2].equals("-f")) {
                File f = new File(args[3]);
                int len = (int)f.length();    // figure out how big the file is
                message = new byte[len];      // create a buffer big enough
                FileInputStream in = new FileInputStream(f);
                int bytes_read = 0, n;
                do {
                    n = in.read(message, bytes_read, len-bytes_read);
                    bytes_read += n;
                } while((bytes_read < len) && (n != -1));
            }
            else {
                String msg = args[2];
                for (int i=3; i<args.length; i++) {
                    msg += " " + args[i];
                }
                message = msg.getBytes();
            }

            // Get the internet address of the specified host
            InetAddress address = InetAddress.getByName(host);

            // Initialize a datagram packet with data and address
            DatagramPacket packet = new DatagramPacket(message, message.length,
                                                       address, port);

            // Create a datagram socket, send the packet through it, close it.
            DatagramSocket dsocket = new DatagramSocket();
            dsocket.send(packet);
            dsocket.close();
        }
        catch (Exception e) {
            System.err.println(e);
            System.err.println(usage);
        }
    }
}
