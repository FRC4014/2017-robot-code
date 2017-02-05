package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PidPivotByGyro extends PIDCommand {
	
//	private static final AnalogGyro GYRO = new AnalogGyro(1);
	private static AnalogGyro GYRO;
	
	private final DriveTrain driveTrain;
	
	public PidPivotByGyro(DriveTrain driveTrain, AnalogGyro GYRO, double angle) {
		super(0.7, 0, 0, 20);
        requires(driveTrain);
        this.GYRO = GYRO;
		this.driveTrain = driveTrain;
		setSetpoint(angle);

//		SmartDashboard.putNumber("Gyro PID Output", 0);
//		SmartDashboard.putNumber("Gyro Angle", 0);
//		SmartDashboard.putNumber("Gyro PID Position", 0);
//		SmartDashboard.putBoolean("Gyro PID Is On Target", false);
		
		LiveWindow.addSensor("Gyro", "Gyro", GYRO);
		LiveWindow.addActuator("Drive", "Pivot", getPIDController());
		System.out.println("PidPivotByGyro says hello");
	}
	
	@Override
	protected void initialize() {
		super.initialize();
		System.out.println("PidPivotByGyro.initialize");
		
		setSetpoint(SmartDashboard.getNumber("Gyro PID Setpoint", 45));

		getPIDController().setPID(
				SmartDashboard.getNumber("P", 7),
				SmartDashboard.getNumber("I", 0),
				SmartDashboard.getNumber("D", 0)
				);

		getPIDController().setAbsoluteTolerance(
				SmartDashboard.getNumber("PID Absolute Tolerance", 0.07));

	}

	@Override
	protected double returnPIDInput() {
		double a = GYRO.getAngle();
		System.out.println("PidPivotByGyro.returnPIDInput: gyro angle = " + a);
		SmartDashboard.putNumber("Gyro Angle", a);
		return a;
	}

	@Override
	protected void usePIDOutput(double output) {
		double speedFactor = SmartDashboard.getNumber("Speed Factor", 0.5);

		// limit speedFactor to reasonable values
		speedFactor = Math.min(speedFactor, 1.0);
		speedFactor = Math.max(speedFactor, 0.0);

		double speed = output * speedFactor;

		System.out.println("PidPivotByGyro.usePIDOutput: [output = " + output 
				+ "] [p = " + getPIDController().getP() 
				+ "] [I = " + getPIDController().getI() 
				+ "] [D = " + getPIDController().getD() 
				+ "]");

		driveTrain.drive(speed, -speed);
	}

	@Override
	protected boolean isFinished() {
		boolean onTarget = getPIDController().onTarget();
		SmartDashboard.putBoolean("Gyro PID Is On Target", onTarget);
		return onTarget;
	}

}
