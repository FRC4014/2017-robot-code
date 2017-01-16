package org.usfirst.frc.team4014.steamworks.drivetrain;

import org.usfirst.frc.team4014.steamworks.OI;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

    public final CANTalon 
    	leftMotor1 = new CANTalon(2),
    	leftMotor2 = new CANTalon(3),
    	rightMotor1 = new CANTalon(4),
    	rightMotor2 = new CANTalon(5);
    
    public final RobotDrive robotDrive = new RobotDrive(
        leftMotor1, leftMotor2, rightMotor1, rightMotor2);

	private final OI oi;
	
    public DriveTrain(OI oi) {
		this.oi = oi;
	}
  
    /**
     * Sets the default command to DriveWithJoystick, so that the Robot will
     * be available for driving when it is not doing any other commands.
     */
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick(oi, this));
	}

    /**
     * Passes left and right into the RobotDrive.tankDrive method.  Basically
     * they represent the left and right sides of the robot.
     * 
     * @param left the speed of the left wheels (between -1 and 1)
     * @param right the speed of the right wheels (between -1 and 1)
     */
    public void drive(double left, double right) {
        robotDrive.tankDrive(left, right);
    }
    
    /**
     * Arcade drive using the joystick.  Basically, y axis controls the speed,
     * and x axis controls the degree of the turn.
     * 
     * @param joystick a joystick device to control the robot (must have x and y
     * attenuator)
     */
    public void drive(Joystick joystick) {
        robotDrive.arcadeDrive(-joystick.getY(), -joystick.getTwist(), true);
    }
	
    /**
     * Stops the DriveTrain motors.  Does not engage the brake.
     */
	public void stop() {
		drive(0,0);	
	}
}
