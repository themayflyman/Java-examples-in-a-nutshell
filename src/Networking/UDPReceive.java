package Networking;
import java.net.*;

/**
 * This program waits to receive datagrams sent the specified port.
 * When it receives one, it displays the sending host and prints the
 * contents of the datagram as a string. Then it loops and waits again.
 */
public class UDPReceive {
    public static final String usage = "Usage: java UDPReceive <port>";
    public static void main(String[] args) {
        try {
            if (args.length != 1)
                throw new IllegalArgumentException("Wrong number of args");

            // Get the port from the command line
            int port = Integer.parseInt(args[0]);

            // Create a socket to listen on the port
            DatagramSocket dsocket = new DatagramSocket(port);

            // Create a buffer to read datagrams info. If anyone sends us a
            // packet containing more than will fit into this buffer, the
            // excess will simple be discarded!
            byte[] buffer = new byte[2048];

            // Create a packet to receive data into the buffer
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            // Now loop forever, waiting to receive packets and printing them
            for(;;) {
                // Wait to receive a datagram
                dsocket.receive(packet);

                // Convert the contents to a string, and display them
                String msg = new String(buffer, 0, packet.getLength());
                System.out.println(packet.getAddress().getHostName() +
                                   ":" + msg);

                // Reset the length of the packet before reusing it.
                // Prior to Java 1.1, we'd just create a new packet each time.
                packet.setLength(buffer.length);
            }
        }
        catch (Exception e) {
            System.err.println(e);
            System.err.println(usage);
        }
    }
}
