package org.usfirst.frc.team4014.steamworks.shooter;

import org.usfirst.frc.team4014.steamworks.OI;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
	//((0.03 * (joystick.getZ())) + 0.96)
	public static final CANTalon shooterMotorOne = new CANTalon(7), shooterMotorTwo = new CANTalon(8);
	
	private final OI oi;
	
	public Shooter(OI oi) {
		this.oi = oi;
	}
	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new ShootWithJoystick(oi, this));
	}
	
	public void shoot(Joystick joystick) {
		shooterMotorOne.set((0.095 * (joystick.getZ())) + 0.895);
	}
	
	public void stop() {
		shooterMotorOne.set(0);
	}
	
}