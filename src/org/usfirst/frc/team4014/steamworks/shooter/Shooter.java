package org.usfirst.frc.team4014.steamworks.shooter;

import org.usfirst.frc.team4014.steamworks.OI;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem {
	//((0.03 * (joystick.getZ())) + 0.96)
	private final CANTalon shooterMotorOne = new CANTalon(7), shooterMotorTwo = new CANTalon(8);
	
	private final OI oi;

	//private StringBuilder builderOfStrings;
	
	public Shooter(OI oi) {
		this.oi = oi;
		///builderOfStrings = new StringBuilder();
		SmartDashboard.putNumber("Z-Axis", 11);
		shooterMotorOne.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		shooterMotorOne.configEncoderCodesPerRev(2048);
		
		shooterMotorOne.configNominalOutputVoltage(+0.0f, -0.0f);
		shooterMotorOne.configPeakOutputVoltage(+12.0f, -12.0f);
		
		shooterMotorOne.setProfile(0);
		shooterMotorOne.setF(0);
		shooterMotorOne.setP(0);
		shooterMotorOne.setI(0);
		shooterMotorOne.setD(0);
		//shooterMotorOne.changeControlMode(CANTalon.TalonControlMode.Position);
	}
	
	/*public StringBuilder getBuilderOfStrings() {
		return builderOfStrings;
	}*/
	
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
		SmartDashboard.putNumber("Z-Axis", shooterMotorOne.getSpeed());
		shooterMotorOne.set(oi.getMateJoystick().getY() * 5000);
	}
	
	public void stop() {
		shooterMotorOne.set(0);
	}
	
}
