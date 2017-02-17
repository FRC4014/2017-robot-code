package org.usfirst.frc.team4014.steamworks.drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class HalfSpeed extends Command{

	private final DriveTrain driveTrain;
	
	
	
	public HalfSpeed(DriveTrain driveTrain) {
		this.driveTrain = driveTrain;
	}

	protected void initialize() {
		driveTrain.changeSpeedModefier(0.5);
	}
	
	protected void end() {
		driveTrain.changeSpeedModefier(1);
	}
	
	@Override
	protected boolean isFinished() {
		//Always return false because the button controls this function
		return false;
	}

}
