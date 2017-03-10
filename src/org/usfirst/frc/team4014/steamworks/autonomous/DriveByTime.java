package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveByTime extends Command{

	private final DriveTrain driveTrain;
	private double speed;
	private double time;
	private double timeCounter;
	
	public DriveByTime(DriveTrain driveTrain,double speed,double time){
		this.driveTrain = driveTrain;
		this.speed = speed;
		this.time = time;
		this.timeCounter = 0;
	}
	
	protected void execute() {
		double[] s = driveTrain.speedsAdjustedForEncoders(speed);
		driveTrain.drive(s[0], s[1]);
		timeCounter = timeCounter + 0.02; //TODO there's probably a way to make this more accurate
	}
	
	
	@Override
	protected boolean isFinished() {
		if (timeCounter >= time){
			return true;
		} else {
			return false;
		}
	}

}
