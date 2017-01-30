package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PidPivotByGyro extends PIDCommand {
	
	private final Gyro gyro;
	private final DriveTrain driveTrain;
	
	public PidPivotByGyro(Gyro gyro, DriveTrain driveTrain, double angle) {
		super(.7, 0, 0);
		this.gyro = gyro;
		this.driveTrain = driveTrain;
		setSetpoint(angle);
		getPIDController().setAbsoluteTolerance(.75);
	}

	@Override
	protected double returnPIDInput() {
		return gyro.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber("Gyro PID Output", output);
		if(getSetpoint() > gyro.getAngle()) {
			driveTrain.drive(output, -output);
		} else {
			driveTrain.drive(-output, output);
		}
	}

	@Override
	protected boolean isFinished() {
		return getPIDController().onTarget();
	}

}
