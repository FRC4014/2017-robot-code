package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;
import org.usfirst.frc.team4014.steamworks.gear.Gear;
import org.usfirst.frc.team4014.steamworks.gear.OpenGearClamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterNoEncoders extends CommandGroup{
	
	public CenterNoEncoders(DriveTrain driveTrain,Gear gear){
		addSequential(new SlowGearApproach(driveTrain, -0.45, gear));
		addSequential(new OpenGearClamp(gear),0.5);
		addSequential(new DriveByTime(driveTrain, 0.6, 1), 2);
	}
	
}
