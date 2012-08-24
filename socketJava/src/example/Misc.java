package example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.IOUtils;

public class Misc {
	
	// @ means begin communication
	// $ means end
	
	public static final String version = "v1.01";
	public static final String msg_handShake = "@\n"+Misc.version+"\n$\n";
	
	public static String readAll(BufferedReader br) throws IOException{
		
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
		
		String raw = Misc.readAll(in);
		
		String data = raw.substring(2, raw.length()-3); // remove @ $ and newline
		return data;

	}
	
	public static void sendData(PrintWriter out, String msg) throws IOException {
		
		out.print(msg);
		
		out.flush();

	}
	
	public static boolean sendData(PrintWriter out, BufferedReader in, String msg) throws IOException {
		
		out.print(msg);
		
		out.flush();

		String inData = Misc.readAll(in);
		
		// check if the other side gets it

		return false;
	}
	
	public static boolean handShake(PrintWriter out, BufferedReader in, String msg) throws IOException {
		
		out.print(msg);
		
		out.flush();

		String inData = Misc.readAll(in);
		
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
