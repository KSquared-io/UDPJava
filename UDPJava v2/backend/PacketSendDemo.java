package backend;

import java.net.*;
import java.io.*;
// This will create a text and send it to 
// the hostname using port 2000

public class PacketSendDemo
{
    public static String consoleOutput="";
    public static String nl = "\n";
    /**
     *
     * @param hostname
     * @return 
     */
    public static String sendPacketTo(String hostname){	
	try
	{
            String bind = "Binding to a local port";
            System.out.println (bind);
            consoleOutput += bind+nl;
            
            // Create a datagram socket, bound to any available local port
            DatagramSocket socket = new DatagramSocket();
            String bound = "Bound to local port " + socket.getLocalPort();
            System.out.println (bound);
            consoleOutput += bound+nl;


            // Create a message to send using UDP packet
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            PrintStream pout = new PrintStream (bout);
            pout.print ( "Greetings from this client!!");

            // Get the contents of the message as an array of bytes
            byte[] barray = bout.toByteArray();

            DatagramPacket packet = new DatagramPacket(barray, barray.length);

            String lookup = "Looking up the hostname " + hostname;
            System.out.println (lookup);
            consoleOutput += lookup+nl;

            // Lookup the hostname and resolve the IP address 
            InetAddress remote_addr = InetAddress.getByName(hostname);

            String hostIP = "Hostname IP is " + remote_addr.getHostAddress();
            System.out.println (hostIP);
            consoleOutput += hostIP+nl;

            packet.setAddress (remote_addr);

            packet.setPort (2000);

            // Send packet
            socket.send(packet);

            String UDPsent = "Packet sent";
            System.out.println (UDPsent);
            consoleOutput += UDPsent+nl;
            
            socket.close();
            
            return consoleOutput;
	}
	catch (UnknownHostException uhe)
	{
            String cantfindhost = "Can't find the host " + hostname;
            System.err.println (cantfindhost);
            consoleOutput = cantfindhost;
            return consoleOutput;
	}
	catch (IOException ioe)
	{	
            String ioeError = " Error - " + ioe;
            System.err.println (ioeError);	
            consoleOutput = ioeError;
            return consoleOutput;
        }
   }
    
   public static void main (String args[])
   {
	int argc = args.length;
	
	if (argc != 1)
	{  
		System.out.println ("Syntax :");
		System.out.println ("java PacketSendDemo hostname");
		return;
	}

	String hostname = args[0];
	sendPacketTo(hostname);
  }
}