package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BoilerPosition extends CommandGroup{
	public BoilerPosition(DriveTrain driveTrain){
		addSequential(new Drive(driveTrain, 108, 0.25));
		addSequential(new PivotByGyro(driveTrain, 0.25, 45)); //TODO: change the 45 to a negative depending on the side of the field we are on
		//TODO: use vision sensor to see if we're  in position
//		addSequential(new Drive(driveTrain, distance, speed));
	}
}
