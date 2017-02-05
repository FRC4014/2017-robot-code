package org.usfirst.frc.team4014.steamworks.gear;

import org.usfirst.frc.team4014.steamworks.OI;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Servos extends Subsystem{

	public final Servo 
		leftServo = new Servo(1),
		rightServo = new Servo(2);
	
	public final OI oi;
	
	public Servos (OI oi){
		this.oi = oi;
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new MoveLeftServo(oi, this));
	}

	public void open(){
		leftServo.setAngle(90);
		rightServo.setAngle(90);
	}
	
}
