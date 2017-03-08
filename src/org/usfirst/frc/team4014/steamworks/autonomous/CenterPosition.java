package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;
import org.usfirst.frc.team4014.steamworks.gear.CloseGearClamp;
import org.usfirst.frc.team4014.steamworks.gear.Gear;
import org.usfirst.frc.team4014.steamworks.gear.OpenGearClamp;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class CenterPosition extends CommandGroup {
	
	public CenterPosition(DriveTrain driveTrain, Gear gear, Gyro gyro) {
		Preferences prefs = Preferences.getInstance();
		
		int drive1Distance = prefs.getInt("auto.CenterPosition.drive1.distance", 40);
		double drive1Speed = prefs.getDouble("auto.CenterPosition.drive1.speed", 0.8);
		System.out.println("CenterPosition: drive1Distance = " + drive1Distance); 
		System.out.println("CenterPosition: drive1Speed = " + drive1Speed); 
		addSequential(new Drive(driveTrain, drive1Distance, drive1Speed));
		
//		double gearApproachSpeed = prefs.getDouble("auto.CenterPosition.slowApproach.speed", 0.5);
//		addSequential(new SlowGearApproach(driveTrain, gearApproachSpeed, gear));
//
//		addSequential(new OpenGearClamp(gear));
//
//		int drive2Distance = prefs.getInt("auto.CenterPosition.drive2.distance", -12);
//		double drive2Speed = prefs.getDouble("auto.CenterPosition.drive2.speed", -0.8);
//		addSequential(new Drive(driveTrain, drive2Distance, drive2Speed));

//		addSequential(new CloseGearClamp(gear));
	}
	
}
