package Systems;

import Systems.JoyDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDeviceStatus;
import edu.wpi.first.wpilibj.Timer;

public class CANDriveAssembly {
	
    private static final int talon_arm_id = 2;
    
    private static CANTalon talon_arm;
    
    private static double kp;
    private static double error, posnow;
    private static double goalangle,x;
	
	public static void init(){
        talon_arm = new CANTalon(talon_arm_id);
        talon_arm.changeControlMode(CANTalon.TalonControlMode.Voltage);
        talon_arm.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
        SmartDashboard.putNumber("goalangle", 0);
        SmartDashboard.putNumber("kp", -0.2);
        SmartDashboard.putNumber("nowangle", 0);
        talon_arm.setEncPosition(0);
        
        kp = -0.2;
        JoyDrive.init();
	}
	
	//Called in teleopMode
	public static void teleopPreiodic(){
		double tempangle;
    	goalangle = SmartDashboard.getNumber("goalangle");
    	if(JoyDrive.joy_Start){
			talon_arm.setEncPosition(0);
		}
		JoyDrive.Joystickvalue();
    	if(JoyDrive.joy_LB){
    		goalangle = goalangle+1;
        	SmartDashboard.putNumber("goalangle", goalangle);
    	}
    	else if(JoyDrive.joy_RB){
    		goalangle = goalangle-1;
        	SmartDashboard.putNumber("goalangle", goalangle);
    	}
    	SmartDashboard.putNumber("arm_value", talon_arm.get());
    	kp = SmartDashboard.getNumber("kp");
    	
    	posnow = talon_arm.getEncPosition();
    	
    	error = goalangle - posnow * 0.088;
		tempangle=posnow*0.088;
    	
        SmartDashboard.putNumber("posnow", posnow );
        SmartDashboard.putNumber("nowangle", tempangle );
        SmartDashboard.putNumber("error", error);
        
        
        
        if(error > 0){
        	if(error > 2){
        		
        		x = -error * kp ;
        	}
        	else{
        		x = 0;
        	}
        }
        else{
        	if(error < -2){
        		x = -error * kp;
        	}
        	else{
        		x = 0;
        	}
        }
        
        
        
        
        talon_arm.set(x);
        SmartDashboard.putNumber("x", x);
        
    }
    
    /**
     * This function is called periodically during test mode
     */
	public static void joystick(){
		
	}
}
