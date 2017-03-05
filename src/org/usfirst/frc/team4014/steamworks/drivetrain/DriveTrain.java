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
	private boolean isReversed;
	private double speedMultiplier = 1.0;

	private boolean brakeMode;

	private final Encoder leftEncoder;
	private final Encoder rightEncoder;
	
    public DriveTrain(OI oi) {
		this.oi = oi;
		robotDrive = new RobotDrive(leftMotor1, leftMotor2, rightMotor1, rightMotor2);
		isReversed = false;
		
		JoystickButton t = new JoystickButton(oi.getDriverJoystick(), 11);
		t.toggleWhenPressed(new ToggleDriveDirection(this, oi));
		
		JoystickButton h = new JoystickButton(oi.getDriverJoystick(), 12);
		h.whileHeld(new HalfSpeed(this));
		
		leftEncoder = makeEncoder(0, 1, false);
		rightEncoder = makeEncoder(2, 3, false);
	}

    /**
     * Makes a standard encoder for drive train.
     * 
     * @param a DIO for 'A' channel.
     * @param b DIO for 'B' channel.
     * @param reverseDirection Set to true if the encoder direction should be reversed.
     * @return Standard Encoder for DriveTrain.
     */
	private Encoder makeEncoder(int a, int b, boolean reverseDirection) {
		Encoder encoder = new Encoder(a, b, false, Encoder.EncodingType.k4X);
		encoder.setMaxPeriod(.1);
		encoder.setMinRate(10);
		encoder.setDistancePerPulse(18.8495559); //wheel diameter * pi
		encoder.setReverseDirection(reverseDirection);
		encoder.setSamplesToAverage(7);
		return encoder;
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
    	if (isReversed) {
            robotDrive.tankDrive(-left, -right);
    	} 
    	else {
            robotDrive.tankDrive(left, right);
    	}
    }
    
    /**
     * Arcade drive using the joystick.  Basically, y axis controls the speed,
     * and x axis controls the degree of the turn.
     * 
     * @param joystick a joystick device to control the robot (must have x and y
     * attenuator)
     */
    public void drive(Joystick joystick) {
    	if (isReversed) {
    		robotDrive.arcadeDrive(joystick.getY(), -joystick.getTwist() * speedMultiplier, true);
    	} 
    	else {
    		robotDrive.arcadeDrive(-joystick.getY(), -joystick.getTwist() * speedMultiplier, true);
    	}
    }
	
    /**
     * Stops the DriveTrain motors.  Does not engage the brake.
     */
	public void stop() {
		drive(0,0);	
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
		SmartDashboard.putString("Brake Mode", brakeMode ? "On" : "Off");
	}

	public boolean isBrakeModeEnabled() {
		return brakeMode;
	}

	public Encoder getLeftEncoder() {
		return leftEncoder;
	}

	public Encoder getRightEncoder() {
		return rightEncoder;
	}
	
	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}
}
