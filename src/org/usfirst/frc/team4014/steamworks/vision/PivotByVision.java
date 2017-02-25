package org.usfirst.frc.team4014.steamworks.vision;

import org.usfirst.frc.team4014.steamworks.autonomous.PIDPivotByGyro;
import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Servo;

public class PivotByVision extends Command {
	
	private static final double PIVOT_SPEED = 0.6;
	private static final double DEFAULT_TURN_ANGLE = 50;
	private final VisionTracker vision;
	private final DriveTrain driveTrain;
	private final Gyro gyro;
	private PIDPivotByGyro pivot;
	private long timeInit;
	private VisionState visionstate;
	private Preferences prefs;
	
	public static void initPreferences() {
		Preferences prefs = Preferences.getInstance();
		prefs.putDouble("vision.pivotbyvision.pivot.speed", PIVOT_SPEED);
		prefs.putDouble("vision.pivotbyvision.pivot.defaultangle", DEFAULT_TURN_ANGLE );
	}

	public PivotByVision(VisionTracker vision, DriveTrain driveTrain, Gyro gyro) {
		this.vision = vision;
		this.driveTrain = driveTrain;
		this.gyro = gyro;

	}
	
	@Override
	protected void initialize() {
		prefs = Preferences.getInstance();
	}

	@Override
	protected void execute() {
		super.execute();
		visionstate  =  vision.getState();
		double pivotSpeed = prefs.getDouble("vision.pivotbyvision.pivot.speed", PIVOT_SPEED);
		double defaultTurnAngle = prefs.getDouble("vision.pivotbyvision.pivot.defaultangle", DEFAULT_TURN_ANGLE );
		if (visionstate.contourCount == 2 ) {
			pivot = new PIDPivotByGyro(driveTrain, gyro, pivotSpeed, visionstate.horizontalDeltaAngle);
			pivot.start();
		}
		else if(visionstate.contourCount == 1) {
			pivot = new PIDPivotByGyro(driveTrain, gyro, pivotSpeed, visionstate.horizontalDeltaAngle);
			pivot.start();
		}
		else {
			//TODO find angle needed if no contours
			pivot = new PIDPivotByGyro(driveTrain, gyro, pivotSpeed, defaultTurnAngle);
			pivot.start();
		}
		
	}

	@Override
	protected boolean isFinished() {
		boolean done = visionstate.xCentered;
//		boolean done = pivot != null && !pivot.isRunning();
		if (done) SmartDashboard.putNumber("Vis Piv Time:", System.currentTimeMillis() - timeInit);
		return done;
	}

}
