package org.usfirst.frc.team4014.steamworks.shooter;

import org.usfirst.frc.team4014.steamworks.OI;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem {
	//((0.03 * (joystick.getZ())) + 0.96)
	private static final double SCALE = 0.076923;
	private static final double ENCODER_TICKS = 60000;
	private static final double TARGET_RPM_VALUE = SCALE * ENCODER_TICKS;
	
	private final CANTalon shooterMotorOne = new CANTalon(7), shooterMotorTwo = new CANTalon(8);
	
	private final OI oi;
	
	public Shooter(OI oi) {
		this.oi = oi;
		SmartDashboard.putNumber("Z-Axis", 11);
		/*
		shooterMotorOne.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		shooterMotorOne.configEncoderCodesPerRev(2048);
		
		shooterMotorOne.configNominalOutputVoltage(+0.0f, -0.0f);
		shooterMotorOne.configPeakOutputVoltage(+12.0f, -12.0f);
		
		shooterMotorOne.setProfile(0);
		shooterMotorOne.setF(0.7);
		shooterMotorOne.setP(0);
		shooterMotorOne.setI(0);
		shooterMotorOne.setD(0);
		*/
		
		shooterMotorOne.changeControlMode(CANTalon.TalonControlMode.Speed);
	}
	
	public CANTalon getTalon() {
		return shooterMotorOne;
	}
	
	public int getEncSpeed() {
		return shooterMotorOne.getEncVelocity();
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new ShootWithJoystick(oi, this));
	}
	
	public void shoot(Joystick joystick) {
		SmartDashboard.putNumber("Z-Axis", shooterMotorOne.getEncVelocity());
		//if (Math.abs(joystick.getY()) <= 0.1){
			//shooterMotorOne.changeControlMode(CANTalon.TalonControlMode.Speed);
			//shooterMotorOne.set(0);
		//}
		//else
		//{
			shooterMotorOne.set(joystick.getY() * 100);
		//}
		
	}
	
	public void stop() {
		shooterMotorOne.set(0);
	}
	
}
