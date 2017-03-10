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
		speed = prefs.getDouble("auto.PivotByEncoders.speed", 0.7);
		degreesPerPulse = prefs.getDouble("auto.PivotByEncoders.degreesPerPulse", 0.032);
		tolerance = prefs.getDouble("auto.PivotByEncoders.tolerance", 0.005);
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
	protected void end() {
		super.end();
		driveTrain.drive(0, 0);
	}

	@Override
	protected boolean isFinished() {
		// Use absolute values so calculation works both ways.
		double absLeftPulses = Math.abs(driveTrain.getLeftEncoder().get());
		double absRightPulses = Math.abs(driveTrain.getRightEncoder().get());
		double absAngle = Math.abs(angle);

		double degreesPivoted = absLeftPulses * degreesPerPulse;
		System.out.println("PivotByEncoders: angle= " + angle
				+ "  tolerance= " + tolerance
				+ "  degreesPerPulse= " + degreesPerPulse
				+ "  degreesPivoted= " + degreesPivoted
				+ "  absLeftPulses= " + absLeftPulses
				+ "  absRightPulses= " + absRightPulses
				);
		return degreesPivoted + tolerance > absAngle;
	}

}
