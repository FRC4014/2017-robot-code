package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class PivotTest extends CommandGroup{

	public PivotTest(DriveTrain driveTrain, Gyro gyro){
		addSequential(new PIDPivotByGyro(driveTrain, gyro, 0.6, 45));
	}
}
