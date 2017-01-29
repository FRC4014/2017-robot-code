package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class PivotTest extends CommandGroup{
	private Gyro gyro;
	
	public PivotTest(DriveTrain driveTrain) {
		gyro = new AnalogGyro(1);
		addSequential(new PivotByGyro(driveTrain, gyro, 90));
//		addSequential(new PivotByGyro(driveTrain, gyro, -90));
//		addSequential(new PivotByGyro(driveTrain, gyro, 90));
//		addSequential(new PivotByGyro(driveTrain, gyro, -90));
//		addSequential(new PivotByGyro(driveTrain, gyro, 360));
	}
}
