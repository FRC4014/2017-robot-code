package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;
import org.usfirst.frc.team4014.steamworks.gear.CloseGearClamp;
import org.usfirst.frc.team4014.steamworks.gear.Gear;
import org.usfirst.frc.team4014.steamworks.gear.OpenGearClamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LoadingZonePositionRed extends CommandGroup{
	public LoadingZonePositionRed(DriveTrain driveTrain, Gear gear){
		addSequential(new Drive(driveTrain, 102, 0.8));
		addSequential(new PivotByGyro(driveTrain, 0.5, -45));
		//TODO: adjust using vision
		//TODO: figure out how far forward we need to go
		addSequential(new SlowGearAproach(driveTrain, 0.5, gear));
		addSequential(new OpenGearClamp(gear));
		addSequential(new Drive(driveTrain, -12, -0.25));
		addSequential(new CloseGearClamp(gear));
	}
}
