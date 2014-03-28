package configmodule;
import com.phidgets.*;

// TODO depending on the commonalities between the sensors, we can either make a polymorphic structure of sensors, make all separate classes for each
// 		type and ignore a parent class, or include them all in this class.  I don't care, but I dont know enough at this time to choose for us

public class Sensor {
	
	private int orientationDegree; // where is the sensor facing? 0 = right, 90 = front...
	private sensorType type;
	private InterfaceKitPhidget phidget;
	private int threshold;
	private int port;
	
	private static final double sonar2cm = 1.296;
			
			
	Sensor(sensorType type, int orientationDegree, int serial, int threshold, int port) throws PhidgetException
	{
		this.type = type;
		this.orientationDegree = orientationDegree;
		this.port = port;
		this.phidget = new InterfaceKitPhidget();
		this.phidget.open(serial);
		phidget.waitForAttachment();
	}
	
	/*****************************************************
	 * Description:	Polls the sensor for its value
	 * 
	 * Returns:	The distance in ____ to nearest object // TODO choose distance measurement unit
	 * @throws PhidgetException 
	 ****************************************************/
	public double pollSensor() throws PhidgetException
	{
		if(this.type == sensorType.sonar) // sonar sensor
		{
			double distance = phidget.getSensorValue(port)*sonar2cm;
			if(distance < threshold)
				return -1;
			
			return distance;
			
		}
		else if(this.type == sensorType.reflective) // reflective sensor
		{
			
		}
		else // compass sensor
		{
			
		}
		
		return 0;
	}
	
}
