package org.usfirst.frc.team4014.steamworks.gear;

import org.usfirst.frc.team4014.steamworks.OI;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Gear extends Subsystem {

	private static final Servo 
		leftServo = new Servo(1),
		rightServo = new Servo(2);
	private final DigitalInput limit;
	
	private final OI oi;
	
	public Gear (OI oi) {
		this.oi = oi;
		limit = new DigitalInput(0);
		JoystickButton b = new JoystickButton(oi.mateJoystick, 10);
		b.toggleWhenPressed(new ToggleGearClamp(this));
		SmartDashboard.putString("Gear Control Status", "Closed");
	}
	
	@Override
	protected void initDefaultCommand() {

	}

	public void open() {
		leftServo.setAngle(90);
		rightServo.setAngle(90);
		SmartDashboard.putString("Gear Control Status", "Open");
	}

	public void close() {
		leftServo.setAngle(15);
		rightServo.setAngle(165);
		SmartDashboard.putString("Gear Control Status", "Closed");
	}
	
	public boolean limitSwitch() {
		return limit.get();
	}
	
}
