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
		requires(driveTrain);
		requires(gear);
	}
	
	protected void execute(){
		double[] s = driveTrain.speedsAdjustedForEncoders(speed);
		driveTrain.drive(s[0], s[1]);
	}
	
	@Override
	protected boolean isFinished() {
		return gear.isPegInGear();
	}

}
