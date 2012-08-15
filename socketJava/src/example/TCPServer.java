package example;

//TCPServer.java

import java.io.*;
import java.net.*;

class TCPServer {
	
	public static final String version = "v1.01";
	
	public static void main(String argv[]) throws Exception {
	    
		
	    String msg = "BEGIN VERSION\n"+version+"\n@\n";
	    
		ServerSocket Server = new ServerSocket(5000);

		System.out.println("TCPServer Waiting for client on port 5000");

		while (true) {
			Socket connected = Server.accept();
			System.out.println(" THE CLIENT" + " " + connected.getInetAddress() + ":" + connected.getPort()
					+ " IS CONNECTED ");
			

			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connected.getInputStream()));

			PrintWriter outToClient = new PrintWriter(connected.getOutputStream(), true);

			
			if (Misc.handShake(outToClient, inFromClient, msg)){
				
				System.out.println("OK");
				// call sub to send data
				// inData = Misc.readAll(inFromClient);
			}


		}
	}
}


//BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
//connected.close();
