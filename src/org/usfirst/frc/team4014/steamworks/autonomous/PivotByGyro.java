package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Gyro;
/**
 * Pivots the robot. 
 * Set speed as positive to pivot left
 * or negative to pivot right
 */
public class PivotByGyro extends Command {

	private static final double SPEED = 0.25;
	
	private final DriveTrain driveTrain;
	private double angle;
	private Gyro gyro;
	private final double left;
	private boolean done = true;
	private double usedLeft;
	
	public PivotByGyro(DriveTrain driveTrain, Gyro gyro, double angle) {
		this.driveTrain = driveTrain;
		this.angle = angle;
		this.gyro = gyro;
		if (angle > 0){
			left = -SPEED;
		} else {
			left = SPEED;
		}
	}
	
	protected void initialize(){
		gyro.reset();
		done = false;
	}
	
	protected void execute(){
		while (!done && 
			   Math.abs(gyro.getAngle()) < Math.abs(angle)) {
			usedLeft = ((-(1-SPEED)/Math.abs(angle))*Math.abs(gyro.getAngle())) + 1;
			driveTrain.drive(usedLeft, -usedLeft);
//			try {
//				Thread.sleep(2);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		driveTrain.drive(0, 0);
		done = true;
	}

	@Override
	protected boolean isFinished() {
		return done;
	}

}
