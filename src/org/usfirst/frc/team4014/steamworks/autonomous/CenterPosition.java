package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;
import org.usfirst.frc.team4014.steamworks.gear.CloseGearClamp;
import org.usfirst.frc.team4014.steamworks.gear.Gear;
import org.usfirst.frc.team4014.steamworks.gear.OpenGearClamp;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class CenterPosition extends CommandGroup {
	
	// Dashboard preferences keys
	private static final String AUTO_CENTER_DRIVE_SPEED = "auto.center.drive.speed";
	private static final String AUTO_CENTER_DRIVE_DISTANCE = "auto.center.drive.distance";

	// Default values
	private static final int DRIVE_DISTANCE = 93;
	private static final double DRIVE_SPEED = 0.8;

	public static void initPreferences() {
		Preferences prefs = Preferences.getInstance();
		prefs.putInt(AUTO_CENTER_DRIVE_DISTANCE, DRIVE_DISTANCE);
		prefs.getDouble(AUTO_CENTER_DRIVE_SPEED, DRIVE_SPEED);
	}

	public CenterPosition(DriveTrain driveTrain, Gear gear, Gyro gyro){
		Preferences prefs = Preferences.getInstance();
		
		int distance = prefs.getInt(AUTO_CENTER_DRIVE_DISTANCE, DRIVE_DISTANCE);
		double speed = prefs.getDouble(AUTO_CENTER_DRIVE_SPEED, DRIVE_SPEED);
		addSequential(new Drive(driveTrain, distance, speed));
		
		double speed2 = prefs.getDouble("auto.center.SlowGearApproach.speed2", 0.5);
		
		
		addSequential(new SlowGearApproach(driveTrain, speed2, gear));
		addSequential(new OpenGearClamp(gear));
		addSequential(new Drive(driveTrain, -12, -0.25));
		addSequential(new CloseGearClamp(gear));
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
	}
	
	
}
