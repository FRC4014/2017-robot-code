package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

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
		driveTrain.drive(speed, speed);
	}
	
	@Override
	protected boolean isFinished() {
		double leftDistance  = driveTrain.getLeftEncoder().getDistance();
		double rightDistance = driveTrain.getRightEncoder().getDistance();
		double averageDistance = (leftDistance + rightDistance) / 2;
		boolean inTolerance = (distance - averageDistance) < tolerance;
		return inTolerance;
	}
	
}
