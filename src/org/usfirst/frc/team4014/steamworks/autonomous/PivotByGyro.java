package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Gyro;
/**
 * Pivots the robot. 
 * Set speed as positive to pivot left
 * or negative to pivot right
 */
public class PivotByGyro extends Command {

	private final DriveTrain driveTrain;
	private double speed;
	private double angle;
	private Gyro gyro;
	
	public PivotByGyro(DriveTrain driveTrain, double speed, double angle) {
		this.driveTrain = driveTrain;
		this.speed = speed;
		this.angle = angle;
	}
	
	protected void initialize(){
		gyro.reset();
	}
	
	protected void execute(){
		driveTrain.drive(-speed, speed);
	}

	@Override
	protected boolean isFinished() {
		if(gyro.getAngle() >= angle){
			return true;
		}
		else {
			return false;
		}
	}

}
