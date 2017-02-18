package org.usfirst.frc.team4014.steamworks.vision;

import org.usfirst.frc.team4014.steamworks.autonomous.PIDPivotByGyro;
import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PivotByVision extends Command {
	
	private final VisionTracker vision;
	private final DriveTrain driveTrain;
	private final Gyro gyro;
	private PIDPivotByGyro pivot;
	private long timeInit;
	private VisionState visionstate;

	public PivotByVision(VisionTracker vision, DriveTrain driveTrain, Gyro gyro) {
		this.vision = vision;
		this.driveTrain = driveTrain;
		this.gyro = gyro;
	}

	@Override
	protected void initialize() {
		timeInit = System.currentTimeMillis();
		double speedFactor = SmartDashboard.getNumber("Pivot Speed Factor:", 0.5);
		System.out.println("PivotByVision.initialize: speedFactor = " + speedFactor);
	}
	
	@Override
	protected void execute() {
		super.execute();
		visionstate  =  vision.getState();
		if (visionstate.contourCount == 2 ) {
			pivot = new PIDPivotByGyro(driveTrain, gyro, 0.6, visionstate.deltaAngle);
			pivot.start();
		}
		else if(visionstate.contourCount == 1) {
			pivot = new PIDPivotByGyro(driveTrain, gyro, 0.6, visionstate.deltaAngle);
			pivot.start();
		}
		else {
			pivot = new PIDPivotByGyro(driveTrain, gyro, 0.6, 50);
			pivot.start();
		}
		
	}

	@Override
	protected boolean isFinished() {
		boolean done = visionstate.centered;
//		boolean done = pivot != null && !pivot.isRunning();
		if (done) SmartDashboard.putNumber("Vis Piv Time:", System.currentTimeMillis() - timeInit);
		return done;
	}

}
