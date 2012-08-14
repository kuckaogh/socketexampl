package example;

//TCPClient.java

import java.io.*;
import java.net.*;

class TCPClient {
	
	public static final String version = "v1.01";
	
	public static void main(String argv[]) throws Exception {

		Socket clientSocket = new Socket("localhost", 5000);

		PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);

		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

	    //String msg = "BEGIN VERSION\n"+version+"\n@\n";
	    String msg = "BEGIN";
	    
		while (true) {
			
			if (Misc.handShake(outToServer, inFromServer, msg)){
			
				System.out.println("OK");
				// call sub to receive data
				// receive filepath for config file and lp file
				// inData = Misc.readAll(inFromServer);
			}
			
		}
	}
}

//clientSocket.close();

//BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

