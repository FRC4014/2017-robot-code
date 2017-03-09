package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class PivotByEncoders extends Command {
	
	private final DriveTrain driveTrain;
	private final double angle;
	private final Preferences prefs;

	private double speed;
	private double degreesPerPulse;
	private double tolerance;

	public PivotByEncoders(DriveTrain driveTrain, double angle) {
		this.driveTrain = driveTrain;
		this.angle = angle;
		prefs = Preferences.getInstance();
	}

	@Override
	protected void initialize() {
		driveTrain.resetEncoders();
		speed = prefs.getDouble("auto.PivotByEncoders.speed", 0.5);
		degreesPerPulse = prefs.getDouble("auto.PivotByEncoders.degreesPerPulse", 1.0);
		tolerance = prefs.getDouble("auto.PivotByEncoders.tolerance", 2.0);
	}

	@Override
	protected void execute() {
		if (angle < 0) {
			driveTrain.drive(-speed, speed);
		}
		else {
			driveTrain.drive(speed, -speed);
		}
	}

	@Override
	protected boolean isFinished() {
		double leftDistance = driveTrain.getLeftEncoder().getDistance();
		double degreesDistance = leftDistance * degreesPerPulse;
		return degreesDistance + tolerance > angle;
	}
}
