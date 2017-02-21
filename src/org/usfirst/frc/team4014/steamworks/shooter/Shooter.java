package org.usfirst.frc.team4014.steamworks.shooter;

import org.usfirst.frc.team4014.steamworks.CAN;
import org.usfirst.frc.team4014.steamworks.OI;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
	//((0.03 * (joystick.getZ())) + 0.96)
	public static final CANTalon 
		shooterMotorOne = new CANTalon(CAN.SHOOTER_MOTOR_1), 
		shooterMotorTwo = new CANTalon(CAN.SHOOTER_MOTOR_2);
	
//	private static final Servo
//		leftGate = new Servo(3),
//		rightGate = new Servo(4);
	
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
		if(joystick.getRawButton(3)){
			shooterMotorOne.set(-1);
		}
		else{
			shooterMotorOne.set(0);
		}
	}
	
	public void stop() {
		shooterMotorOne.set(0);
	}
	
	// TODO: set the angles once the cad/placement of the servos is done
	public void leftGateOpen() {
//		leftGate.setAngle();
	}
	public void leftGateClose() {
//		leftGate.setAngle();
	}
	public void rightGateOpen() {
//		rightGate.setAngle();
	}
	public void rightGateClose() {
//		rightGate.setAngle();
	}
}
