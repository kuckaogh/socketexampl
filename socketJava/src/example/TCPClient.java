package example;

//TCPClient.java

import java.io.*;
import java.net.*;

class TCPClient {
	
	
	public static void main(String argv[]) throws Exception {

		initialize();
	}

	public static void initialize() throws Exception {
	
		Socket clientSocket = new Socket("localhost", 5000);
	
		PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
	
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	
	    //String msg_handShake = "T\n"+Misc.version+"\n@\n";
	    //String msg = "BEGIN";
	    
		while (true) {
			
			if (Misc.handShake(outToServer, inFromServer, Misc.msg_handShake)){
			
				System.out.println("OK");
				
				String msg_filePath = "@\nC:\\gurobi501\\win64\\bin\\a.lp\n$\n";
				
				Misc.sendData(outToServer, msg_filePath);
				
				
				
				// call sub to receive data
				// receive filepath for config file and lp file
				// inData = Misc.readAll(inFromServer);
			}
			
		}
	}
}

//clientSocket.close();

//BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

