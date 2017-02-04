package org.usfirst.frc.team4014.steamworks.autonomous;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Command;

public class turnServo extends Command{

	Servo servo;
	private double angle;
	
	public turnServo (Servo servo, double angle){
		this.servo = servo;
		this.angle = angle;
	}
	
	protected void execute(){
		servo.setAngle(angle);
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}

}
