package org.usfirst.frc.team4014.steamworks.winch;

import org.usfirst.frc.team4014.steamworks.CAN;
import org.usfirst.frc.team4014.steamworks.OI;
import org.usfirst.frc.team4014.steamworks.gear.Gear;
import org.usfirst.frc.team4014.steamworks.shooter.ShootWithJoystick;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Winch extends Subsystem {
	
	public static final CANTalon winchmotor = new CANTalon(CAN.WINCH_MOTOR);
	private final OI oi;
	private static final double WINCH_SPEED = 1;
	private final JoystickButton winchbutton;
	private final Gear gear;
 
	//TODO find real winch speed
	
	public Winch(OI oi, Gear gear) {
		super("Winch");
		this.gear = gear;
		this.oi = oi;
		winchbutton = new JoystickButton(oi.getMateJoystick(), 1);
		winchbutton.whileHeld(new WinchIntakeWithButton(this, gear));
	}

	@Override
	protected void initDefaultCommand() {
		//We don't anything to run by default in this subsystem
	}

	public void start() {
		Preferences prefs = Preferences.getInstance();
		double winchSpeed = prefs.getDouble("winch.Winch.winchSpeed", WINCH_SPEED);
		winchmotor.set(-(winchSpeed));
	}
	
	public void stop() {
		winchmotor.set(0);
	}
	
}

