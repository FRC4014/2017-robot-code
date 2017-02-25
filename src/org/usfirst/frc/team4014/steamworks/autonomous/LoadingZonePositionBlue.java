package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;
import org.usfirst.frc.team4014.steamworks.gear.CloseGearClamp;
import org.usfirst.frc.team4014.steamworks.gear.Gear;
import org.usfirst.frc.team4014.steamworks.gear.OpenGearClamp;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class LoadingZonePositionBlue extends CommandGroup{
	public LoadingZonePositionBlue(DriveTrain driveTrain, Gear gear, Gyro gyro){
		addSequential(new Drive(driveTrain, 102, 0.8));
		addSequential(new PivotByGyro(driveTrain, 0.5, 45, gyro));
		//TODO: adjust using vision
		addSequential(new Drive(driveTrain, 38, 0.8));
		addSequential(new SlowGearApproach(driveTrain, 0.5, gear));
		addSequential(new OpenGearClamp(gear));
		addSequential(new Drive(driveTrain, -12, -0.25));
		addSequential(new CloseGearClamp(gear));
	}
}
