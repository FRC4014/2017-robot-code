package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDPivotByGyro extends PIDCommand{

	private final DriveTrain driveTrain;
	private final Gyro gyro;
	private double speedFactor;
	private double angle;
	private long timeInit;
	
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
		System.out.println("PIDPivotByGyro.initialize: called.");
		long before = System.currentTimeMillis();
		gyro.reset();
		long after = System.currentTimeMillis();
		System.out.println("Gyro reset delay: " + (after - before));

//		setSetpointRelative(angle);

		getPIDController().setPID(0.7, 0, 0);
		getPIDController().setAbsoluteTolerance(1);
	}
	
	@Override
	protected double returnPIDInput() {
		double angle2 = gyro.getAngle();
		System.out.println("PIDPivotByGyro: gyro angle = " + angle2);
		return angle2;
	}

	@Override
	protected void usePIDOutput(double output) {
		double speed = output * speedFactor;
		driveTrain.drive(speed, -speed);
	}

	@Override
	protected boolean isFinished() {
		boolean done = getPIDController().onTarget();
		if (done) SmartDashboard.putNumber("PID Piv Time:", System.currentTimeMillis() - timeInit);
		return done;
	}

}
