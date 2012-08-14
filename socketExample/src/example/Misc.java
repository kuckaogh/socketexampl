package example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.IOUtils;

public class Misc {
	
	
	public static String readAll(BufferedReader br) throws IOException{
		
		String line="";
		String section="";
		
		while (true) {
			
			line = br.readLine()+"\n";

			//System.out.println("feed:" + line);
			section = section + line;

			if (line.startsWith("@")) break;
			
		}

		return section;
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
