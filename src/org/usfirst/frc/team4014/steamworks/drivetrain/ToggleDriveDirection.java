package org.usfirst.frc.team4014.steamworks.drivetrain;

import org.usfirst.frc.team4014.steamworks.OI;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleDriveDirection extends Command{

	private final DriveTrain driveTrain;
	private final OI oi;
	
	public ToggleDriveDirection(DriveTrain driveTrain, OI oi) {
		this.driveTrain = driveTrain;
		this.oi = oi;
	}
	
	protected void initialize() {
		driveTrain.reverseDriveDirection();
	}
	
	protected void end() {
		driveTrain.standardDriveDirection();
	}

	
	@Override
	protected boolean isFinished() {
		// always return false because this command is toggled
		return false;
	}

}
