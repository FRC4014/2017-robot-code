package org.usfirst.frc.team4014.steamworks.autonomous;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Command;

public class TurnServo extends Command{

	public final Servo 
	leftServo = new Servo(1),
	rightServo = new Servo(2);
	private double angle;
	
	public TurnServo (double angle){
		this.angle = angle;
	}
	
	protected void execute(){
		leftServo.setAngle(angle);
		rightServo.setAngle(angle);
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}

}

