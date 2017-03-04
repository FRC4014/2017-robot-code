package org.usfirst.frc.team4014.steamworks.vision;

import org.usfirst.frc.team4014.steamworks.autonomous.PIDPivotByGyro;
import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Servo;

public class PivotByVision extends Command {
	
	private final VisionTracker vision;
	private final DriveTrain driveTrain;
	private final Gyro gyro;
	private PIDPivotByGyro pivot;
	private long timeInit;
	private VisionState visionstate;
	private Preferences prefs;
	private boolean forcePivot;
	
	public PivotByVision(VisionTracker vision, DriveTrain driveTrain, Gyro gyro) {
		this.vision = vision;
		this.driveTrain = driveTrain;
		this.gyro = gyro;

	}
	
	@Override
	protected void initialize() {
		prefs = Preferences.getInstance();
		forcePivot = true;
	}

	@Override
	protected void execute() {
		super.execute();
		visionstate  =  vision.getState();
		double pivotSpeed = prefs.getDouble("vision.PivotByVision.pivotSpeed", 0.7);
		double defaultTurnAngle = prefs.getDouble("vision.PivotByVision.defaultTurnAngle", 50);
		if (forcePivot){
			if (visionstate.contourCount == 2 ) {
				pivot = new PIDPivotByGyro(driveTrain, gyro, pivotSpeed, (0.45*visionstate.horizontalDeltaAngle));
				pivot.start();
				System.out.println("2 contour vision pivot");
			}
			else if(visionstate.contourCount == 1) {
				pivot = new PIDPivotByGyro(driveTrain, gyro, pivotSpeed, (0.45*visionstate.horizontalDeltaAngle));
			
				pivot.start();
				System.out.println("1 contour vision pivot");
			}
			else {
				//TODO find angle needed if no contours
				pivot = new PIDPivotByGyro(driveTrain, gyro, pivotSpeed, defaultTurnAngle);
				pivot.start();
				System.out.println("0 contour vision pivot");
			}
		}
		if ((Math.abs(visionstate.horizontalDeltaAngle) > 1.5) && (pivot.isOnTarget())){
			forcePivot = true;
			System.out.println("EXECUTING ANOTHER PIVOT");
		}
		else{
			forcePivot = false;
			System.out.println(pivot.isOnTarget());
		}
			
	}

	@Override
	protected boolean isFinished() {
		//boolean done = (visionstate.xCentered && pivot.isOnTarget());
//		boolean done = pivot != null && !pivot.isRunning();
		//if (done) SmartDashboard.putNumber("Vis Piv Time:", System.currentTimeMillis() - timeInit);
		//if (done) System.out.println("COMMAND FINISHED_PivotByVision");
		return true;
	}

}
