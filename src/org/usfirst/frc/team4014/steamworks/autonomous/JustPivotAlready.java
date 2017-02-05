package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.Robot;
import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class JustPivotAlready extends Command {

	private final DriveTrain driveTrain;
	private final long duration;
	
	private long startTime;

	/**
	 * @param duration in milliseconds
	 */
	public JustPivotAlready(long duration) {
		driveTrain = Robot.DRIVE_TRAIN;
		requires(driveTrain);
		this.duration = duration;
		startTime = 0;
	}
	
	@Override
	protected void execute() {
		if (startTime == 0) {
			startTime = System.currentTimeMillis();
		}
		double speed = 0.7;
		driveTrain.drive(speed, -speed);
	}
	
	

	@Override
	protected boolean isFinished() {
		long now = System.currentTimeMillis();
		boolean finished = now > startTime + duration;
		System.out.println("JustPivotAlready.isFinished: " 
				+ "[startTime:" + startTime
				+ "][duration:" + duration
				+ "][now:" + now
				+ "][finished:"+finished);
		if (finished) driveTrain.drive(0, 0);//move this line to cancel()
		return finished;
	}

}
