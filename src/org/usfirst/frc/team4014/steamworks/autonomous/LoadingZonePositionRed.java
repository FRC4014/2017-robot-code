package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;
import org.usfirst.frc.team4014.steamworks.gear.CloseGearClamp;
import org.usfirst.frc.team4014.steamworks.gear.Gear;
import org.usfirst.frc.team4014.steamworks.gear.OpenGearClamp;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class LoadingZonePositionRed extends CommandGroup {

	public LoadingZonePositionRed(DriveTrain driveTrain, Gear gear, Gyro gyro) {
		Preferences prefs = Preferences.getInstance();

		int drive1Distance = prefs.getInt("auto.LoadingZonePositionRed.drive1.distance", 94);
		double drive1Speed = prefs.getDouble("auto.LoadingZonePositionRed.drive1.speed", 0.5);
		addSequential(new Drive(driveTrain, drive1Distance, drive1Speed));

		double pivotSpeed = prefs.getDouble("auto.LoadingZonePositionRed.pivot.speed", 0.5);
		int pivotAngle = prefs.getInt("auto.LoadingZonePositionRed.pivot.angle", -60);
		addSequential(new PIDPivotByGyro(driveTrain, gyro, pivotSpeed, pivotAngle));

		//TODO: adjust using vision

		double gearApproachSpeed = prefs.getDouble("auto.CenterNoEncoders.gearApproachSpeed", -0.45);
		addSequential(new SlowGearApproach(driveTrain, gearApproachSpeed, gear),8);
		
		addSequential(new OpenGearClamp(gear), 1);
		
		double driveSpeed = prefs.getDouble("auto.CenterNoEncoders.driveSpeed", 0.6);
		int driveTime = prefs.getInt("auto.CenterNoEncoders.driveTime", 2);
		int driveTimeout = prefs.getInt("auto.CenterNoEncoders.driveTimeout", 5);
		addSequential(new DriveByTime(driveTrain, driveSpeed, driveTime), driveTimeout);
	}
}
