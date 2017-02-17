package org.usfirst.frc.team4014.steamworks.drivetrain;

import org.usfirst.frc.team4014.steamworks.CAN;
import org.usfirst.frc.team4014.steamworks.OI;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem {

    public final CANTalon 
    	leftMotor1 = new CANTalon(CAN.DRIVE_TRAIN_LEFT_MOTOR_1),
    	leftMotor2 = new CANTalon(CAN.DRIVE_TRAIN_LEFT_MOTOR_2),
    	rightMotor1 = new CANTalon(CAN.DRIVE_TRAIN_RIGHT_MOTOR_1),
    	rightMotor2 = new CANTalon(CAN.DRIVE_TRAIN_RIGHT_MOTOR_2);
    
    public final RobotDrive robotDrive;
	private final OI oi;
	private static final Encoder ENCODER = new Encoder(0,1,false, Encoder.EncodingType.k4X);
	private boolean isReversed;
	
    public DriveTrain(OI oi) {
		this.oi = oi;
		robotDrive = new RobotDrive(leftMotor1, leftMotor2, rightMotor1, rightMotor2);
		
		ENCODER.setDistancePerPulse(18.8495559); //wheel diameter * pi
		ENCODER.setMaxPeriod(.1);
		ENCODER.setMinRate(10);
		
		JoystickButton t = new JoystickButton(oi.getDriverJoystick(), 11);
		t.toggleWhenPressed(new ToggleDriveDirection(this, oi));
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
    	if (isReversed == false){
    		robotDrive.arcadeDrive(-joystick.getY(), -joystick.getTwist(), true);
    	} else {
    		robotDrive.arcadeDrive(joystick.getY(), -joystick.getTwist(), true);
    	}
    }
	
    /**
     * Stops the DriveTrain motors.  Does not engage the brake.
     */
	public void stop() {
		drive(0,0);	
	}
	public double encoderDistance(){
		return ENCODER.getDistance();
	}
	public void encoderReset(){
		ENCODER.reset();
	}
	public void reverseDriveDirection(){
		isReversed = true;
		SmartDashboard.putBoolean("Reversed Joystick", true);
	}
	public void standardDriveDirection(){
		isReversed = false;
		SmartDashboard.putBoolean("Reversed Joystick", false);
	}
}
