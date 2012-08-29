package wrimsv2.solver.Gurobi;

import gurobi.GRB;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.IOUtils;


public class Misc {
	
	// @ means begin communication
	// $ means end
	
	public static final String version = "v1.01";
	public static final String msg_handShake = "@\n"+Misc.version+"\n$\n";

	
	public static int readInteger(DataInputStream br) throws IOException{
		
		
		int ret = br.readInt();
		
		
		return ret;
	}

	public static double[] readDoubles(DataInputStream br) throws IOException{
		
		int length = br.readInt();
		
		System.out.println("length is: "+length);
		
		double[] ret = new double[length];
		
		for (int i=0;i<length;i++){
			
			ret[i] = br.readDouble();
		}

		return ret;
	}
	
	public static String[] readStrings(DataInputStream br) throws IOException{
		
		int length = br.readInt();
		
		System.out.println("length is: "+length);
		
		String[] ret = new String[length];
		
		for (int i=0;i<length;i++){
			
			ret[i] = br.readUTF();
		}

		return ret;
	}
	
	public static String readAllString(BufferedReader br) throws IOException{
		
		String line="";
		String section="";
		
		while (true) {
			
			line = br.readLine()+"\n";

			//System.out.println("feed:" + line);
			section = section + line;

			if (line.startsWith("$")) break;
			
		}

		return section;
	}

	public static String receiveData(BufferedReader in) throws IOException {
		
		String raw = Misc.readAllString(in);
		
		String data = raw.substring(2, raw.length()-3); // remove @ $ and newline
		return data;

	}

	
	public static void sendData(PrintWriter out, String msg) throws IOException {
		
		out.print(msg);
		
		out.flush();

	}

	
	public static LpResult readLpResult(DataInputStream br) throws IOException{
		
		LpResult result = new LpResult();
		
		result.status = br.readInt();
		
		if (result.status == GRB.Status.OPTIMAL){

			result.varNames  = readStrings(br);
			result.varValues = readDoubles(br);
		}

		return result;
	}
	
	public static void sendLpResult(DataOutputStream out, LpResult result) throws IOException {
		
		out.writeInt(result.status);
		
		if (result.status == GRB.Status.OPTIMAL){
			sendStrings(out,result.varNames);
			sendDoubles(out,result.varValues);
		}
		
	}
	
	public static void sendIntegers(DataOutputStream out, int[] dataArray) throws IOException {
		
		out.writeInt(dataArray.length);
		
		for (int i=0;i<dataArray.length;i++){
			out.writeInt(dataArray[i]);
		}
		
		out.flush();
	}

	public static void sendStrings(DataOutputStream out, String[] dataArray) throws IOException {
		
		out.writeInt(dataArray.length);
		
		for (int i=0;i<dataArray.length;i++){
			out.writeUTF(dataArray[i]);
			//out.writeDouble(dataArray[i]);
		}
		
		out.flush();
	}
	
	public static void sendDoubles(DataOutputStream out, double[] dataArray) throws IOException {
		
		out.writeInt(dataArray.length);
		
		for (int i=0;i<dataArray.length;i++){
			out.writeDouble(dataArray[i]);
		}
		
		out.flush();
	}
	
	public static boolean sendData(PrintWriter out, BufferedReader in, String msg) throws IOException {
		
		out.print(msg);
		
		out.flush();

		String inData = Misc.readAllString(in);
		
		// check if the other side gets it

		return false;
	}
	
	public static boolean handShake(PrintWriter out, BufferedReader in, String msg) throws IOException {
		
		out.print(msg);
		
		out.flush();

		String inData = Misc.readAllString(in);
		
		if (inData.equals(msg)) {
			//System.out.println("OK");
			return true;
		}

		return false;
	}	
	
	public static String readStringFromFile(String filePath) throws IOException{
		String fromFile ="";

	    FileInputStream inputStream = new FileInputStream(filePath);
	    
	    try {
	        fromFile = IOUtils.toString(inputStream);
	    } finally {
	        inputStream.close();
	    }
		return fromFile;
	}

}
