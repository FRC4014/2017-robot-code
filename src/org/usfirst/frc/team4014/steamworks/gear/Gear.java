package org.usfirst.frc.team4014.steamworks.gear;

import org.usfirst.frc.team4014.steamworks.OI;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Gear extends Subsystem{

	public final Servo 
		leftServo = new Servo(1),
		rightServo = new Servo(2);
	
	public final OI oi;
	
	public Gear (OI oi){
		this.oi = oi;
		JoystickButton b = new JoystickButton(oi.mateJoystick, 10);
		b.toggleWhenPressed(new ToggleGearClamp(this));
		SmartDashboard.putString("Gear Control Status", "Closed");
	}
	
	@Override
	protected void initDefaultCommand() {

	}

	public void open(){
		leftServo.setAngle(90);
		rightServo.setAngle(90);
	}
	public void close(){
		leftServo.setAngle(0);
		rightServo.setAngle(0);
	}
	
}
