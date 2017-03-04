package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;
import org.usfirst.frc.team4014.steamworks.gear.Gear;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class SlowGearApproach extends Command{

	
	private final DriveTrain driveTrain;
	private final Gear gear;
	private final double speed;
	private final Gyro gyro;
	private double initialGyro;
	
	public SlowGearApproach(DriveTrain driveTrain, double speed, Gear gear, Gyro gyro) {
		this.driveTrain = driveTrain;
		this.speed = speed;
		this.gear = gear;
		this.gyro = gyro;
		requires(driveTrain);
		requires(gear);
	}
	
	protected void initialize(){
		initialGyro = gyro.getAngle();
	}
	
	protected void execute(){
		driveTrain.driveStraight(speed, initialGyro);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO: This is returning true just until we get the DIO error figured out.
//		return true;
		return gear.isPegInGear();
	}

}
