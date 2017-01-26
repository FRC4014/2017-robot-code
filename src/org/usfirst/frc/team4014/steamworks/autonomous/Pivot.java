package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
/**
 * Pivots the robot. 
 * Set speed as positive to pivot left
 * or negative to pivot right
 */
public class Pivot extends Command {

	private final DriveTrain driveTrain;
	private double speed;
	
	public Pivot(DriveTrain driveTrain) {
		this.driveTrain = driveTrain;
	}
	
	protected void execute(){
		driveTrain.drive(-speed, speed);
	}

	@Override
	protected boolean isFinished() {
		// TODO return false until the encoder return the right value, then return true
		return true;
	}

}
