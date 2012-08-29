package wrimsv2.solver.Gurobi;

//TCPServer.java

import gurobi.GRB;

import java.io.*;
import java.net.*;


class Server {
	
	
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
	
			DataOutputStream dataOutToClient=new DataOutputStream(connected.getOutputStream());
			
			
			if (Misc.handShake(outToClient, inFromClient, Misc.msg_handShake)){
				
				System.out.println("OK");
				
				String LpFilePath = Misc.receiveData(inFromClient);
				
				System.out.println(LpFilePath);
				
				LpResult result = LpTest.solveLP(LpFilePath);
				
				Misc.sendLpResult(dataOutToClient, result);
				
//				if (result.status == GRB.Status.OPTIMAL){
//				
//					Misc.sendStrings(dataOutToClient, result.varNames);
//				
//					Misc.sendDoubles(dataOutToClient, result.varValues);
				
					
				
				// call sub to send data
				// inData = Misc.readAll(inFromClient);

			}
	
		}
		

	}
}


//BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
//connected.close();
