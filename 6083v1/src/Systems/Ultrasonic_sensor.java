package Systems;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Ultrasonic_sensor {
	
	private static Ultrasonic ultra;
	private static double distance;
	private static final int ping_channel = 0;
	private static final int echo_channel = 1;
	
	public static void init(){
		ultra = new Ultrasonic(ping_channel, echo_channel);
	    ultra.setAutomaticMode(true);
	    ultra.setEnabled(true);
	    SmartDashboard.putNumber("distance(cm)", 0);
	    }
	
	public static void teleop(){
		distance=ultra.getRangeMM()/10;
		SmartDashboard.putNumber("distance(cm)", distance);
	}
	}
