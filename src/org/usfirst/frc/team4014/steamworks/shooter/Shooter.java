package org.usfirst.frc.team4014.steamworks.shooter;

import org.usfirst.frc.team4014.steamworks.CAN;
import org.usfirst.frc.team4014.steamworks.OI;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
	//((0.03 * (joystick.getZ())) + 0.96)
	public static final CANTalon 
		shooterMotorOne = new CANTalon(CAN.SHOOTER_MOTOR_1), 
		shooterMotorTwo = new CANTalon(CAN.SHOOTER_MOTOR_2);
	
	private final OI oi;
	
	public Shooter(OI oi) {
		this.oi = oi;
		Button shooterButton = new JoystickButton(this.oi.getMateJoystick(), 3);
		shooterButton.toggleWhenPressed(new ShootWithJoystick(this.oi, this));
	}
	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
	
	public void shoot(Joystick joystick) {
			shooterMotorOne.changeControlMode(TalonControlMode.Voltage);
			shooterMotorTwo.changeControlMode(TalonControlMode.Voltage);
			shooterMotorOne.setVoltageCompensationRampRate(12.0);
			shooterMotorTwo.setVoltageCompensationRampRate(12.0);
			shooterMotorOne.set(-(0.25 * (joystick.getZ()) + 0.75) * 12);
			shooterMotorTwo.set((0.25 * (joystick.getZ()) + 0.75) * 12);
	}
	
	public void stop() {
		shooterMotorOne.set(0);
		shooterMotorTwo.set(0);
	}
	
}
