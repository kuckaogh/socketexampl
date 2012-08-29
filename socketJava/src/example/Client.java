package example;

//TCPClient.java

import gurobi.GRB;

import java.io.*;
import java.net.*;

import wrimsv2.solver.Gurobi.LpResult;
import wrimsv2.solver.Gurobi.Misc;

class Client {
	
	
	public static void main(String argv[]) throws Exception {

		try {
			initialize();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("exception catched");
			
		}
	}

	public static void initialize() throws Exception {
	
		Socket clientSocket = new Socket("localhost", 5000);
	
		PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
	
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		DataInputStream dataInFromServer = new DataInputStream(clientSocket.getInputStream());	
		
	    //String msg_handShake = "T\n"+Misc.version+"\n@\n";
	    //String msg = "BEGIN";
	    
		while (true) {
			
			if (Misc.handShake(outToServer, inFromServer, Misc.msg_handShake)){
			
				System.out.println("OK");
				
				String msg_filePath = "@\nC:\\gurobi501\\win64\\bin\\simple.lp\n$\n";
				
				
				Misc.sendData(outToServer, msg_filePath);
				

				LpResult result = Misc.readLpResult(dataInFromServer);
				
				
				System.out.println("status: "+result.status);
				
				
			    if (result.status == GRB.Status.OPTIMAL) {
				
			    	for ( int i=0;i<result.varValues.length;i++){
					
			    		System.out.println(result.varNames[i]+":"+result.varValues[i]);
			    	}
			    }
				
				BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
				// call sub to receive data
				// receive filepath for config file and lp file
				// inData = Misc.readAll(inFromServer);
			}
			
		}
	}
}

//clientSocket.close();

//BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

