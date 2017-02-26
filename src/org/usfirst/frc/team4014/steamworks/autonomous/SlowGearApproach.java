package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;
import org.usfirst.frc.team4014.steamworks.gear.Gear;

import edu.wpi.first.wpilibj.command.Command;

public class SlowGearApproach extends Command{

	
	private final DriveTrain driveTrain;
	private final Gear gear;
	private final double speed;
	
	public SlowGearApproach(DriveTrain driveTrain, double speed, Gear gear) {
		this.driveTrain = driveTrain;
		this.speed = speed;
		this.gear = gear;
	}
	
	protected void execute(){
		driveTrain.drive(speed, speed);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO: This is returning true just until we get the DIO error figured out.
//		return true;
		return gear.isPegInGear();
	}

}
