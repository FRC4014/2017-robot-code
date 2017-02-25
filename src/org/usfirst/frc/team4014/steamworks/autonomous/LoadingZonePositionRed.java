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

		int drive1Distance = prefs.getInt("auto.LoadingZonePositionRed.drive1.distance", 102);
		double drive1Speed = prefs.getDouble("auto.LoadingZonePositionRed.drive1.speed", 0.8);
		addSequential(new Drive(driveTrain, drive1Distance, drive1Speed));

		double pivotSpeed = prefs.getDouble("auto.LoadingZonePositionRed.pivot.speed", 0.5);
		int pivotAngle = prefs.getInt("auto.LoadingZonePositionRed.pivot.angle", -45);
		addSequential(new PivotByGyro(driveTrain, pivotSpeed, pivotAngle, gyro));

		//TODO: adjust using vision

		int drive2Distance = prefs.getInt("auto.LoadingZonePositionRed.drive2.distance", 38);
		double drive2Speed = prefs.getDouble("auto.LoadingZonePositionRed.drive2.speed", 0.8);
		addSequential(new Drive(driveTrain, drive2Distance, drive2Speed));

		double gearApproachSpeed = prefs.getDouble("auto.LoadingZonePositionRed.gearApproach.speed", 0.5);
		addSequential(new SlowGearApproach(driveTrain, gearApproachSpeed, gear));

		addSequential(new OpenGearClamp(gear));

		int drive3Distance = prefs.getInt("auto.LoadingZonePositionRed.drive3.distance", -12);
		double drive3Speed = prefs.getDouble("auto.LoadingZonePositionRed.drive3.speed", -0.8);
		addSequential(new Drive(driveTrain, drive3Distance, drive3Speed));

		addSequential(new CloseGearClamp(gear));
	}
}
