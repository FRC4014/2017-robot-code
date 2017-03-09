package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;
import org.usfirst.frc.team4014.steamworks.gear.Gear;
import org.usfirst.frc.team4014.steamworks.gear.OpenGearClamp;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterNoEncoders extends CommandGroup{
	
	public CenterNoEncoders(DriveTrain driveTrain,Gear gear){
		Preferences prefs = Preferences.getInstance();

		double gearApproachSpeed = prefs.getDouble("auto.CenterNoEncoders.gearApproachSpeed", -0.45);
		addSequential(new SlowGearApproach(driveTrain, gearApproachSpeed, gear));
		
		addSequential(new OpenGearClamp(gear), 1);
		
		double driveSpeed = prefs.getDouble("auto.CenterNoEncoders.driveSpeed", 0.6);
		int driveTime = prefs.getInt("auto.CenterNoEncoders.driveTime", 2);
		int driveTimeout = prefs.getInt("auto.CenterNoEncoders.driveTimeout", 5);
		addSequential(new DriveByTime(driveTrain, driveSpeed, driveTime), driveTimeout);
	}
	
}
