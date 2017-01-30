package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PidPivotByGyro extends PIDCommand {
	
	private final AnalogGyro gyro;
	private final DriveTrain driveTrain;
	
	public PidPivotByGyro(AnalogGyro gyro, DriveTrain driveTrain, double angle) {
		super(.7, 0, 0, 20);
		this.gyro = gyro;
		this.driveTrain = driveTrain;
		setSetpoint(angle);
		getPIDController().setAbsoluteTolerance(.75);
		getPIDController().setContinuous();
		gyro.reset();
		gyro.setSensitivity(0.007);
		SmartDashboard.putNumber("Gyro PID Output", 0);
		SmartDashboard.putNumber("Gyro Angle", 0);
		SmartDashboard.putNumber("Gyro PID Position", 0);
		SmartDashboard.putNumber("Gyro PID Setpoint", angle);
		SmartDashboard.putBoolean("Gyro PID Is On Target", false);
	}

	@Override
	protected double returnPIDInput() {
		double a = gyro.getAngle();
		SmartDashboard.putNumber("Gyro Angle", a);
		return a;
	}

	@Override
	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber("Gyro PID Output", output);
		SmartDashboard.putNumber("Gyro PID Position", getPosition());
		SmartDashboard.putNumber("Gyro PID Setpoint", getSetpoint());
		if(getSetpoint() > gyro.getAngle()) {
			driveTrain.drive(output, -output);
		} else {
			driveTrain.drive(-output, output);
		}
	}

	@Override
	protected boolean isFinished() {
		boolean onTarget = getPIDController().onTarget();
		SmartDashboard.putBoolean("Gyro PID Is On Target", onTarget);
		return onTarget;
	}

}
