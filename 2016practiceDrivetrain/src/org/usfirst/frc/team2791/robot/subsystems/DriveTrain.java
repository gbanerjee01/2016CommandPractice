
package org.usfirst.frc.team2791.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
	private Talon CIM0, CIM1, CIM2, CIM3;
	private RobotDrive drive;
	private Encoder Encoder01, Encoder23;
	//23 is the left, 01 is the right
	private ShakerGyro gyro;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public DriveTrain(){
		CIM0 = new Talon(0);
		CIM1 = new Talon(1);
		CIM2 = new Talon(2);
		CIM3 = new Talon(3);
		
		drive = new RobotDrive(CIM2,CIM3,CIM0,CIM0);
		
		Encoder01 = new Encoder(Constants.Encoder01PortA,Constants.Encoder01PortB);
		Encoder23 = new Encoder(Constants.Encoder23PortA,Constants.Encoder23PortB);
		
		Encoder01.reset();
		Encoder23.reset();
		Encoder01.setDistancePerPulse(Util.tickToFeet(driveEncoderTicks, Constants.WHEEL_DIAMETER));
		Encoder23.setDistancePerPulse(Util.tickToFeet(driveEncoderTicks, Constants.WHEEL_DIAMETER));
	
		gyro = new ShakerGyro();
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoystick());
    	// Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void tankDrive(Driver driver){
    	drive.tankDrive(driver.getGTADriveLeft(),driver.getGTADriveRight());
    }
    /*public void tankDrive(double leftAxis, double rightAxis){
    	tankDrive()
    }*/
    public void stop(){
    	drive.tankDrive(0,0);
    }
    public Encoder getLeftEncoder() {
		return Encoder23;
	}

	/**
	 * @return The encoder getting the distance and speed of right side of the drivetrain.
	 */
	public Encoder getRightEncoder() {
		return Encoder01;
	}
	public double getAngle() {
		return gyro.getAngle();
	}
}

