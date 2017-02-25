package org.usfirst.frc.team4014.steamworks.vision;

import org.usfirst.frc.team4014.steamworks.OI;
import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;
import org.usfirst.frc.team4014.steamworks.winch.WinchIntakeWithButton;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

@Deprecated
/**
 * Basic experimental test to test vision and cameraServo commands.
 * @author Frank Boensch
 *
 */
public class Camera extends Subsystem{
	
	 final OI oi;
	private VisionTracker vision;
	private DriveTrain driveTrain;
	private Object gyro;
	private JoystickButton visionPivotButton;
	private JoystickButton cameraServoInclineButton;
	
	public Camera(OI oi, VisionTracker vision, DriveTrain driveTrain, Gyro gyro) {
		this.oi = oi;
		this.vision = vision;
		this.driveTrain = driveTrain;
		this.gyro = gyro;
		visionPivotButton = new JoystickButton(oi.getDriverJoystick(),9);
		visionPivotButton.whenPressed(new PivotByVision(vision, driveTrain, gyro));
		cameraServoInclineButton = new JoystickButton(oi.getDriverJoystick(),10);
		cameraServoInclineButton.toggleWhenPressed(new InclineCamera(vision, true));
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void start() {
		
	}

}
