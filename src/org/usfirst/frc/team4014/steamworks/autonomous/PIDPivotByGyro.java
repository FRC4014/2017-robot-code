package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class PIDPivotByGyro extends PIDCommand{

	private final DriveTrain driveTrain;
	private final Gyro gyro;
	private double speedFactor;
	private double angle;
	
	public PIDPivotByGyro(DriveTrain driveTrain, Gyro gyro, double speedFactor, double angle) {
		super(0.7, 0, 0);
		this.speedFactor = speedFactor;
		this.angle = angle;
		requires(driveTrain);
		this.driveTrain = driveTrain;
		this.gyro = gyro;
		
		setSetpoint(angle);
	}

	protected void initialize(){
		long before = System.currentTimeMillis();
		gyro.reset();
		long after = System.currentTimeMillis();
		System.out.println("Gyro before reset: " + before);
		System.out.println("Gyro after reset: " + after);
		System.out.println("Gyro reset delay: " + (after - before));

//		setSetpointRelative(angle);

		getPIDController().setPID(0.7, 0, 0);
		getPIDController().setAbsoluteTolerance(2);
	}
	
	@Override
	protected double returnPIDInput() {
		return gyro.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		double speed = output * speedFactor;
		driveTrain.drive(speed, -speed);
	}

	@Override
	protected boolean isFinished() {
		return getPIDController().onTarget();
	}

}