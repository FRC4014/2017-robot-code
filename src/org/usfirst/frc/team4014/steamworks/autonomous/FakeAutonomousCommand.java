package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class FakeAutonomousCommand extends Command {

	DriveTrain driveTrain;
	
	public FakeAutonomousCommand(DriveTrain driveTrain){
		this.driveTrain = driveTrain;
	}
	
	protected void initialize(){
		
	}
	
	protected void execute(){
		driveTrain.drive(-0.5, 0.5);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
