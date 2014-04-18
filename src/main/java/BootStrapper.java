package configmodule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import com.phidgets.PhidgetException;
import robotinterpreter.RobotInterpreter;
import jssc.SerialPortException;
import org.tempuri.Service;
import org.tempuri.IService;

public class BootStrapper {

	public static void main(String args[]) throws InterruptedException, SerialPortException, IOException, PhidgetException {
		System.out.println("Beginning Execution");
		Service robotService = new Service();
		IService robotServiceInterface = robotService.getBasicHttpBindingIService();

		robotServiceInterface.setStatus("001");
		System.out.println(robotServiceInterface.getStatus());

		ConfigurationModule config = new ConfigurationModule();
		RobotInterpreter r = new RobotInterpreter();
		
		r.addRobotListener(config);
		String code = "";

		while(code.length() == 0) {
			code = robotServiceInterface.getCode();
		}

		System.out.println(code);
		r.load(code);
		r.execute();
		Thread.sleep(10000);
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

