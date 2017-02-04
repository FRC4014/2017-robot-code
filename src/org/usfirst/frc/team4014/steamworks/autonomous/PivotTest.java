package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class PivotTest extends CommandGroup{
	private AnalogGyro gyro;
	
	double P;
	double I;
	double D;
	
	public PivotTest(DriveTrain driveTrain) {
		P = prefs.getDouble("P", 0.7);
		I = prefs.getDouble("I", 0);
		D = prefs.getDouble("D", 0);
		gyro = new AnalogGyro(1);
		addSequential(new PidPivotByGyro(gyro, driveTrain, 45));
//		addSequential(new PivotByGyro(driveTrain, gyro, 90));
//		new WaitCommand(1000);
//		addSequential(new PidPivotByGyro(gyro, driveTrain, -45));
//		new WaitCommand(1000);
//		addSequential(new PidPivotByGyro(gyro, driveTrain, 90));
//		new WaitCommand(1000);
//		addSequential(new PidPivotByGyro(gyro, driveTrain, -90));
//		new WaitCommand(1000);
//		addSequential(new PidPivotByGyro(gyro, driveTrain, 360));
	}
}
