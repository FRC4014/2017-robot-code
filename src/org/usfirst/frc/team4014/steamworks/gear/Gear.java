 package org.usfirst.frc.team4014.steamworks.gear;

import org.usfirst.frc.team4014.steamworks.OI;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Gear extends Subsystem {

	private static final Servo 
		leftServo = new Servo(1),
		rightServo = new Servo(2);
	private static final DigitalInput limit = new DigitalInput(3);
	
	private final OI oi;
	private Preferences prefs;
	
	public Gear (OI oi) {
		this.oi = oi;
		JoystickButton b = new JoystickButton(oi.getMateJoystick(), 10);
		b.toggleWhenPressed(new ToggleGearClamp(this));
		SmartDashboard.putString("Gear Control Status", "Super Closed");
		prefs = Preferences.getInstance();
		JoystickButton p = new JoystickButton(oi.getMateJoystick(), 8);
		p.toggleWhenPressed(new TestLimitSwitch(this));
//		new TestLimitSwitch(this).start();
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}

	public void open() {
		double left = prefs.getDouble("gear.Gear.open.leftServo", 15);
		double right = prefs.getDouble("gear.Gear.open.rightServo", 15);
		leftServo.setAngle(left);
		rightServo.setAngle(right); // Formerly 165
		SmartDashboard.putString("Gear Control Status", "Open");
	}

	public void close() {
		double left = prefs.getDouble("gear.Gear.close.leftServo", 90);
		double right = prefs.getDouble("gear.Gear.close.rightServo", 90);
		leftServo.setAngle(left);
		rightServo.setAngle(right);
		SmartDashboard.putString("Gear Control Status", "Closed");
	}
	
	public void superClose() {
		double left = prefs.getDouble("gear.Gear.superClose.leftServo", 0);
		double right = prefs.getDouble("gear.Gear.superClose.rightServo", 180);
		leftServo.setAngle(left);
		rightServo.setAngle(right);
		SmartDashboard.putString("Gear Control Status", "Super Closed");
	}
	
	public boolean isPegInGear() {
		// TODO: This is returning true just until we get the DIO error figured out.
//		return true;
		if (limit.get() == false){
			return true;
		} else {
			return false;
		}
//		return limit.get();
	}
	
	public boolean writeLimitSwitchStatus() {
		return limit.get();
	}
	
}
