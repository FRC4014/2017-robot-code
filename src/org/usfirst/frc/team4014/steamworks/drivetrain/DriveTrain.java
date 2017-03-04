package org.usfirst.frc.team4014.steamworks.drivetrain;

import org.usfirst.frc.team4014.steamworks.CAN;
import org.usfirst.frc.team4014.steamworks.OI;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

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
//	private static final Encoder ENCODER = new Encoder(0,1,false, Encoder.EncodingType.k4X);
	private boolean isReversed;
	private double speedMultiplier = 1.0;

	private boolean brakeMode;
	
    public DriveTrain(OI oi) {
		this.oi = oi;
		robotDrive = new RobotDrive(leftMotor1, leftMotor2, rightMotor1, rightMotor2);
		isReversed = false;
		//ENCODER.setDistancePerPulse(18.8495559); //wheel diameter * pi
		//ENCODER.setMaxPeriod(.1);
		//ENCODER.setMinRate(10);
		leftMotor2.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightMotor1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
//		leftMotor2.configEncoderCodesPerRev(2048);
//		rightMotor1.configEncoderCodesPerRev(2048); //TODO figure out what this number needs to be
		leftMotor2.reverseSensor(false);
		rightMotor1.reverseSensor(false);
//		leftMotor2.enc
		//TODO maybe there's more setup we need for the encoders?
		
		
		JoystickButton t = new JoystickButton(oi.getDriverJoystick(), 11);
		t.toggleWhenPressed(new ToggleDriveDirection(this, oi));
		
		JoystickButton h = new JoystickButton(oi.getDriverJoystick(), 12);
		h.whileHeld(new HalfSpeed(this));
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
    		robotDrive.arcadeDrive(-joystick.getY(), -joystick.getTwist() * speedMultiplier, true);
    	} 
    	else {
    		robotDrive.arcadeDrive(joystick.getY(), -joystick.getTwist() * speedMultiplier, true);
    	}
    }
	
    /**
     * Stops the DriveTrain motors.  Does not engage the brake.
     */
	public void stop() {
		drive(0,0);	
	}

	public boolean isDestinationReached(double distance){
		double targetNum = distance * 38603.8905273;
		SmartDashboard.putNumber("Autonomous target distance inches", distance);
		SmartDashboard.putNumber("Autonomous target Distance", targetNum);
		SmartDashboard.putNumber("encoder Position", rightMotor1.getEncPosition());
		if (rightMotor1.getEncPosition() >= targetNum){
			return true;
		} else {
			return false;
		}
	}

	public void encoderReset(){
		rightMotor1.setPosition(0);//the manual said to use setPosition, but there is a setEncPosition function if this doesn't work
		leftMotor2.setPosition(0);
	}

	public void reverseDriveDirection(){
		isReversed = true;
		SmartDashboard.putBoolean("Reversed Joystick", isReversed);
	}

	public void standardDriveDirection(){
		isReversed = false;
		SmartDashboard.putBoolean("Reversed Joystick", isReversed);
	}

	public void setSpeedMultiplier (double multiplier) {
		speedMultiplier = multiplier;
	}
	
	public void enableBrakeMode(boolean brake) {
		brakeMode = brake;
		rightMotor1.enableBrakeMode(brake);
		rightMotor2.enableBrakeMode(brake);
		leftMotor1.enableBrakeMode(brake);
		leftMotor2.enableBrakeMode(brake);
	}

	public boolean isBrakeModeEnabled() {
		return brakeMode;
	}
}
