package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.Robot;
import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.PIDCommand;

public class PIDPivotByGyro extends PIDCommand {
	
	private final double setpoint;
	public final AnalogGyro gyro;
	private final DriveTrain driveTrain;

	public PIDPivotByGyro(double setpoint) {
		super(0.7, 0, 0);
		requires(Robot.DRIVE_TRAIN);

		this.gyro = Robot.GYRO;
		this.driveTrain = Robot.DRIVE_TRAIN;
		this.setpoint = setpoint;
		
	}
	
	@Override
	protected void initialize() {
		getPIDController().setAbsoluteTolerance(0.05);
		gyro.reset();
		try {
			wait(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected double returnPIDInput() {
		return gyro.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		double speed = output * 0.5;
		
		System.out.println("PIDPivotByGyro.usePIDOutput: "
				+ "[speed:" + speed
				+ "]");
		
		driveTrain.drive(speed, -speed);
	}

	@Override
	protected boolean isFinished() {
		return getPIDController().onTarget();
	}

}
