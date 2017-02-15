package org.usfirst.frc.team4014.steamworks.winch;

import org.usfirst.frc.team4014.steamworks.CAN;
import org.usfirst.frc.team4014.steamworks.OI;
import org.usfirst.frc.team4014.steamworks.shooter.ShootWithJoystick;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Winch extends Subsystem {
	
	public static final CANTalon winchmotor = new CANTalon(CAN.WINCH_MOTOR);
	private final OI oi;
	private static final double WINCH_SPEED = 1;
	private final JoystickButton winchbutton;
 
	//TODO find real winch speed
		
	public Winch(OI oi) {
		super("Winch");
		this.oi = oi;
		winchbutton = new JoystickButton(oi.getMateJoystick(), 1);
		winchbutton.toggleWhenPressed(new WinchIntakeWithButton(this));
	}

	@Override
	protected void initDefaultCommand() {
		//We don't anything to run by default in this subsystem
	}

	public void start() {
		winchmotor.set(WINCH_SPEED);
	}
	
	public void stop() {
		winchmotor.set(0);
	}
	
}

