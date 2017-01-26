package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

public class Drive extends Command {
	
	//TODO: figure out how to set these variables in the cumulative Autonomous class
	
	private final DriveTrain driveTrain;
	private double speed;
	private double distance;
	private Encoder enc;
	
	public Drive(DriveTrain driveTrain) {
		this.driveTrain = driveTrain;
	}

	protected void initialize(){
		enc = new Encoder(0,1,false, Encoder.EncodingType.k4X);
		enc.setDistancePerPulse(18.8495559); //wheel diameter * pi
		enc.setMaxPeriod(.1);
		enc.setMinRate(10);
		enc.setReverseDirection(false);
	}
	
		//Calls repeatedly
	protected void execute() {
		driveTrain.drive(speed, speed);
	}
	
	@Override
	protected boolean isFinished() {
		if(enc.getDistance() >= distance){
			enc.reset();
			return true;
		}
		else
		{
			return false;
		}
		// TODO: Return false until encoder reaches the right value; then return true.
		
	}
	
}
