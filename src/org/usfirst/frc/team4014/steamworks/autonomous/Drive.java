package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

/**
 * drives forward distance (in inches)
 * at speed (range from -1 to 1)
 */
public class Drive extends Command {
	//TODO: Find out if driving backwards work. if it doesn't, make it work
	private final DriveTrain driveTrain;
	private double speed;
	private double distance;
	private Encoder enc;
	
	public Drive(DriveTrain driveTrain, double distance, double speed) {
		this.driveTrain = driveTrain;
		this.distance = distance;
		this.speed = speed;
	}

	protected void initialize (){
		enc = new Encoder(0,1,false, Encoder.EncodingType.k4X);
		enc.setDistancePerPulse(18.8495559); //wheel diameter * pi
		enc.setMaxPeriod(.1);
		enc.setMinRate(10);
		if(distance < 0){
			enc.setReverseDirection(true);
			distance = distance * -1;
		} else {
			enc.setReverseDirection(false);
		}
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
		else {
			return false;
		}
		// TODO: Return false until encoder reaches the right value; then return true.
		
	}
	
}
