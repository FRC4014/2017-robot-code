 package org.usfirst.frc.team4014.steamworks.gear;

import org.usfirst.frc.team4014.steamworks.DPIO;
import org.usfirst.frc.team4014.steamworks.OI;
import org.usfirst.frc.team4014.steamworks.PWM;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Gear extends Subsystem {

	private static final Servo 
		stovepipeFlapServo = new Servo(PWM.STOVEPIPE_FLAP_SERVO),
		leftServo = new Servo(PWM.GEAR_DOOR_LEFT_SERVO),
		rightServo = new Servo(PWM.GEAR_DOOR_RIGHT_SERVO);
	private static final DigitalInput limit = new DigitalInput(DPIO.GEAR_PEG_LIMIT);
	
	private final OI oi;
	private Preferences prefs;
	
	public Gear (OI oi) {
		this.oi = oi;

		JoystickButton b = new JoystickButton(oi.getMateJoystick(), 10);
		b.toggleWhenPressed(new ToggleGearClamp(this));
		SmartDashboard.putString("Gear Control Status", "Super Closed");

		prefs = Preferences.getInstance();
		
		JoystickButton p = new JoystickButton(oi.getMateJoystick(), 8);
		
		JoystickButton stoveFlapButton = new JoystickButton(oi.getMateJoystick(), 2);
		stoveFlapButton.toggleWhenPressed(new ToggleStovepipeFlap(this));
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
		return limit.get();
	}
	
	public void openStovePipeFlap() {
		stovepipeFlapServo.setAngle(prefs.getDouble("gear.Gear.openStovePipeFlap.angle", 0));
	}

	public void closeStovePipeFlap() {
		stovepipeFlapServo.setAngle(prefs.getDouble("gear.Gear.closeStovePipeFlap.angle", 180));
	}
}
