package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 * drives forward distance (in inches)
 * at speed (range from -1 to 1)
 */
public class Drive extends Command {
	private final DriveTrain driveTrain;
	private double speed;
	private double distance;
	private double tolerance;
	private Preferences prefs;
	
	public Drive(DriveTrain driveTrain, double distance, double speed) {
		this.driveTrain = driveTrain;
		this.distance = distance;
		this.speed = speed;
		requires(driveTrain);
		prefs = Preferences.getInstance();
	}

	protected void initialize (){
		driveTrain.resetEncoders();
		tolerance = prefs.getDouble("auto.Drive.distance.tolerance", 0.2);
	}
	
	protected void execute() {
		double[] s = driveTrain.speedsAdjustedForEncoders(speed);
		driveTrain.drive(s[0], s[1]);
	}
	
	@Override
	protected boolean isFinished() {
		boolean finished = isFinishedJustLeftEncoder();
//		boolean finished = isFinishedJustRightEncoder();
//		boolean finished = isFinishedBothEncoders();
		if (finished) {
			driveTrain.stop();
		}
		return finished;
	}

	private boolean isFinishedBothEncoders() {
		double leftDistance  = Math.abs(driveTrain.getLeftEncoder().getDistance());
		double rightDistance = Math.abs(driveTrain.getRightEncoder().getDistance());
		double averageDistance = (leftDistance + rightDistance) / 2;
		boolean inTolerance = (distance - averageDistance) < tolerance;
		return inTolerance  || (averageDistance > distance);
	}
	
	private boolean isFinishedJustLeftEncoder() {
		Encoder leftEncoder = driveTrain.getLeftEncoder();
		double leftDistance  = Math.abs(leftEncoder.getDistance());
		boolean inTolerance = (distance - leftDistance) < tolerance;
		System.out.println("Drive:"
				+ " [target distance="+distance+"]" 
				+ " stopped?="+leftEncoder.getStopped() 
				+ " rate="+leftEncoder.getRate() 
				+ " encodingScale="+leftEncoder.getEncodingScale()
				+ " get="+leftEncoder.get()
				+ " raw="+leftEncoder.getRaw()
				+ " distance=" + leftEncoder.getDistance());
		return inTolerance || (leftDistance > distance + tolerance);
	}
	
	private boolean isFinishedJustRightEncoder() {
		double rightDistance = Math.abs(driveTrain.getRightEncoder().getDistance());
		boolean inTolerance = (distance - rightDistance) < tolerance;
		return inTolerance || (rightDistance > distance);
	}
	
}
