package example;

//TCPServer.java

import java.io.*;
import java.net.*;

class TCPServer {
	
	
	public static void main(String argv[]){
	    
		try {
			initialize();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void initialize() throws Exception {
	    
		
	    //String msg_handShake = "T\n"+Misc.version+"\n@\n";
	    
		ServerSocket Server = new ServerSocket(5000);
	
		System.out.println("TCPServer Waiting for client on port 5000");
	
		while (true) {
			Socket connected = Server.accept();
			System.out.println(" THE CLIENT" + " " + connected.getInetAddress() + ":" + connected.getPort()
					+ " IS CONNECTED ");
			
	
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connected.getInputStream()));
	
			PrintWriter outToClient = new PrintWriter(connected.getOutputStream(), true);
	
			
			if (Misc.handShake(outToClient, inFromClient, Misc.msg_handShake)){
				
				System.out.println("OK");
				
				String filePath = Misc.receiveData(inFromClient);
				
				System.out.println(filePath);
				
				// call sub to send data
				// inData = Misc.readAll(inFromClient);

			}
	
		}
		

	}
}


//BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
//connected.close();
