package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class PivotTest extends CommandGroup{

	Servo leftServo = new Servo(3);
	Servo rightServo = new Servo(4);

	public PivotTest(DriveTrain driveTrain, AnalogGyro GYRO) {
//		addSequential(new PidPivotByGyro(driveTrain,GYRO, 45));
//		addSequential(new FakeAutonomousCommand(driveTrain));
		addSequential(new PivotByGyro(driveTrain, GYRO, -180));
		addSequential(new PivotByGyro(driveTrain, GYRO, 180));
		gyro = new AnalogGyro(1);
//		addSequential(new PidPivotByGyro(gyro, driveTrain, 45));
//		addSequential(new PivotByGyro(driveTrain, gyro, 90));
//		new WaitCommand(1000);
//		addSequential(new PidPivotByGyro(driveTrain, -45));
//		new WaitCommand(1000);
//		addSequential(new PidPivotByGyro(driveTrain, 90));
//		new WaitCommand(1000);
//		addSequential(new PidPivotByGyro(driveTrain, -90));
//		new WaitCommand(1000);
//		addSequential(new PidPivotByGyro(driveTrain, 360));
//		addSequential(new PidPivotByGyro(gyro, driveTrain, 360));
		
		addParallel(new turnServo(leftServo, 90));
		addSequential(new turnServo(rightServo, 90));
	}

}
