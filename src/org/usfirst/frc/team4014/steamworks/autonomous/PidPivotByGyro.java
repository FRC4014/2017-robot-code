package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PidPivotByGyro extends PIDCommand {
	
	private final AnalogGyro gyro;
	private final DriveTrain driveTrain;
	
	public PidPivotByGyro(AnalogGyro gyro, DriveTrain driveTrain, double angle) {
		super(0.5,0,0);
        requires(driveTrain);

		this.gyro = gyro;
		this.driveTrain = driveTrain;
		setSetpoint(angle);

		SmartDashboard.putNumber("Gyro PID Output", 0);
		SmartDashboard.putNumber("Gyro Angle", 0);
		SmartDashboard.putNumber("Gyro PID Position", 0);
		SmartDashboard.putNumber("Gyro PID Setpoint", getSetpoint());
		SmartDashboard.putBoolean("Gyro PID Is On Target", false);
		SmartDashboard.putNumber("P", 1);
		SmartDashboard.putNumber("I", 0);
		SmartDashboard.putNumber("D", 0.3);
		SmartDashboard.putNumber("F", 0.3);

		LiveWindow.addSensor("Gyro", "Gyro", gyro);
		LiveWindow.addActuator("Drive", "Pivot", getPIDController());
		System.out.println("PidPivotByGyro says hello");
	}
	
	@Override
	protected void initialize() {
		super.initialize();
		System.out.println("PidPivotByGyro.initialize");
		
//		getPIDController().setPID(.5, 0, .15, .15);
		getPIDController().setAbsoluteTolerance(.1);
//		getPIDController().setContinuous();
		gyro.reset();
//		gyro.setSensitivity(0.007);
	}

	@Override
	protected double returnPIDInput() {
		double a = gyro.getAngle();
		System.out.println("PidPivotByGyro.returnPIDInput: gyro angle = " + a);
		SmartDashboard.putNumber("Gyro Angle", a);
		return a;
	}

	@Override
	protected void usePIDOutput(double output) {
		System.out.println("PidPivotByGyro.usePIDOutput: output = " + output);
		
		SmartDashboard.putNumber("Gyro PID Output", output);
		SmartDashboard.putNumber("Gyro PID Position", getPosition());
		SmartDashboard.putNumber("Gyro PID Setpoint", getSetpoint());
		driveTrain.drive(output, -output);
	}

	@Override
	protected boolean isFinished() {
		boolean onTarget = getPIDController().onTarget();
		SmartDashboard.putBoolean("Gyro PID Is On Target", onTarget);
		return onTarget;
	}

}
