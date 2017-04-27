package org.usfirst.frc.team4014.steamworks.shooter;

import org.usfirst.frc.team4014.steamworks.CAN;
import org.usfirst.frc.team4014.steamworks.OI;
import org.usfirst.frc.team4014.steamworks.PWM;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
	//((0.03 * (joystick.getZ())) + 0.96)
	public static final CANTalon 
		shooterMotorOne = new CANTalon(CAN.SHOOTER_MOTOR_1), 
		shooterMotorTwo = new CANTalon(CAN.SHOOTER_MOTOR_2);
	
	private static final Servo
		leftShooterDoor = new Servo(PWM.SHOOTER_SERVO_LEFT),
		rightShooterDoor = new Servo(PWM.SHOOTER_SERVO_RIGHT);
	
	private final OI oi;
	
	
	public Shooter(OI oi) {
		this.oi = oi;
		Button shooterButton = new JoystickButton(this.oi.getDriverJoystick(),6);
		shooterButton.toggleWhenPressed(new ShootWithJoystick(this.oi, this));
		
		Button doorButton = new JoystickButton(this.oi.getDriverJoystick(), 8);
		doorButton.toggleWhenPressed(new ToggleShooterFlaps(this));
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
		shooterMotorOne.set((0.25 * (joystick.getZ()) + 0.75) * 12);
		shooterMotorTwo.set(-(0.25 * (joystick.getZ()) + 0.75) * 12);

	}
	
	public void stop() {
		shooterMotorOne.set(0);
		shooterMotorTwo.set(0);
	}
	
	// TODO: set the angles once the cad/placement of the servos is done
	public void leftGateOpen() {
		leftShooterDoor.setAngle(180);
	}
	public void leftGateClose() {
		leftShooterDoor.setAngle(155);
	}
	public void rightGateOpen() {
		rightShooterDoor.setAngle(25);
	}
	public void rightGateClose() {
		rightShooterDoor.setAngle(0);
	}
}
