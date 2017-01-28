package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterPosition extends CommandGroup {

	public CenterPosition(DriveTrain driveTrain){
		addSequential( new Drive(driveTrain, 93, 0.25));
		//TODO: let go of gear
		addSequential(new Drive(driveTrain, -12, -0.25));
	}
	
}
