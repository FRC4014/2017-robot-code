package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class PivotTest extends CommandGroup{

	public PivotTest(DriveTrain driveTrain, Gyro gyro){
		Preferences prefs = Preferences.getInstance();

		double driveSpeed = prefs.getDouble("auto.PivotTest.driveSpeed", 0.6);
		int driveTime = prefs.getInt("auto.PivotTest.driveTime", 1);
		int driveTimeout = prefs.getInt("auto.PivotTest.driveTimeout", 10);
		addSequential(new DriveByTime(driveTrain, driveSpeed, driveTime), driveTimeout);
	}
}
