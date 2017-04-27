package org.usfirst.frc.team4014.steamworks.vision;

import org.usfirst.frc.team4014.steamworks.CAN;
import org.usfirst.frc.team4014.steamworks.OI;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LEDs extends Subsystem{

	Solenoid frontLED = new Solenoid(CAN.PNEUMATIC_CONTROL_MODULE, 2);
	Solenoid backLED = new Solenoid(CAN.PNEUMATIC_CONTROL_MODULE, 3);
	
	private final OI oi;
	
	public LEDs(OI oi){
		this.oi = oi;
		JoystickButton f = new JoystickButton(oi.getDriverJoystick(), 3);
		JoystickButton a = new JoystickButton(oi.getDriverJoystick(), 4);
		f.toggleWhenPressed(new FrontLEDOnOff(this));
		a.toggleWhenPressed(new BackLEDOnOff(this));
	}
	
	@Override
	protected void initDefaultCommand() {

	}
	
	public void frontOn() {
		frontLED.set(true);
		SmartDashboard.putBoolean("front LED On", true);
	}
	public void backOn() {
		backLED.set(true);
		SmartDashboard.putBoolean("Back LED On", true);
	}
	public void frontOff() {
		frontLED.set(false);
		SmartDashboard.putBoolean("front LED On", false);
	}
	public void backOff() {
		backLED.set(false);
		SmartDashboard.putBoolean("Back LED On", false);
	}

}
