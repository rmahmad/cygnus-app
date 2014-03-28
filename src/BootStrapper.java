package configmodule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.phidgets.PhidgetException;

import robotinterpreter.RobotInterpreter;
import jssc.SerialPortException;

public class BootStrapper {

	public static void main(String args[]) throws InterruptedException, SerialPortException, IOException, PhidgetException {
		ConfigurationModule config = new ConfigurationModule();
		RobotInterpreter r = new RobotInterpreter();
		
		r.addRobotListener(config);
		String code = readFile("/home/mayngo/workspace/Cygnus/Java/Config Module/src/configmodule/testing.txt");
		System.out.println(code);
		r.load(code);
		r.execute();
		Thread.sleep(5000);
		
	}
	
	static String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
	
}

