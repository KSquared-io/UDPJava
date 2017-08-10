package backend;

import java.net.*;
import java.io.*;


public class PacketReceiveDemo 
{
    public static String consoleOutput="";
    public static String nl = "\n";
    public static char readChar;

    public static String receivePacket(){
        try
            {
                String bind = "Binding to local port 2000";
                System.out.println (bind);
                consoleOutput+=bind+nl;

                // Create a datagram socket, bound to the specific port 2000
                DatagramSocket socket = new DatagramSocket(2000);

                String bound = "Bound to local port " + socket.getLocalPort();
                System.out.println (bound);
                consoleOutput+=bound+nl;

                // Create a datagram packet, containing a maximum buffer of 256 bytes
                DatagramPacket packet = new DatagramPacket( new byte[256], 256 );

                // Receive a packet - remember by default this is a blocking operation
                socket.receive(packet);

                String received = "Packet received!";
                System.out.println (received);
                consoleOutput+=received+nl;

                // Display packet information
                InetAddress remote_addr = packet.getAddress();
                String sentBy = "Sent by  : " + remote_addr.getHostAddress();
                System.out.println (sentBy);
                consoleOutput+=sentBy+nl;
                String sentFrom = "Send from: " + packet.getPort();
                System.out.println (sentFrom);
                consoleOutput+=sentFrom+nl;

                // Display packet contents, by reading from byte array
                ByteArrayInputStream bin = new ByteArrayInputStream (packet.getData());

                // Display only up to the length of the original UDP packet
                for (int i=0; i < packet.getLength(); i++)
                {
                        int data = bin.read();
                        if (data == -1)
                                break;
                        else{
                            readChar=(char) data;
                            System.out.print (readChar);
                            consoleOutput+=readChar;                           
                        }
                                
                }
                consoleOutput+=nl;
                socket.close();
                String socketClosed = "socket closed";
                System.out.println(socketClosed);
                consoleOutput+=socketClosed+nl;
                
                return consoleOutput;
            }
            catch (IOException ioe)
            {
                String ioeError = "Error - " + ioe;
                System.err.println (ioeError);
                consoleOutput+=ioeError+nl;

                return consoleOutput;
            }
    }
    public static void main (String args[])
    {
            receivePacket();

    }
}