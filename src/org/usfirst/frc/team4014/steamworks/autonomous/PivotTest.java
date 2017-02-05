package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class PivotTest extends CommandGroup{
	
	public PivotTest(DriveTrain driveTrain, AnalogGyro GYRO) {
//		addSequential(new PidPivotByGyro(driveTrain,GYRO, 45));
//		addSequential(new FakeAutonomousCommand(driveTrain));
		addSequential(new PivotByGyro(driveTrain, GYRO, -180));
		addSequential(new PivotByGyro(driveTrain, GYRO, 180));
//		new WaitCommand(1000);
//		addSequential(new PidPivotByGyro(driveTrain, -45));
//		new WaitCommand(1000);
//		addSequential(new PidPivotByGyro(driveTrain, 90));
//		new WaitCommand(1000);
//		addSequential(new PidPivotByGyro(driveTrain, -90));
//		new WaitCommand(1000);
//		addSequential(new PidPivotByGyro(driveTrain, 360));
	}
}
