package org.usfirst.frc.team4014.steamworks;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;
import org.usfirst.frc.team4014.steamworks.fuelintake.FuelIntake;
import org.usfirst.frc.team4014.steamworks.gear.Gear;
import org.usfirst.frc.team4014.steamworks.shooter.Shooter;
import org.usfirst.frc.team4014.steamworks.winch.Winch;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class TestCANCommand extends Command {

	private DriveTrain driveTrain;
	private int id;
	private double start;

	public TestCANCommand(DriveTrain driveTrain) {
		this.driveTrain = driveTrain;
	}

	@Override
	protected void initialize() {
		id = Preferences.getInstance().getInt("TestCANCommand.id", CAN.WINCH_MOTOR);
		start = Timer.getFPGATimestamp();
		System.out.println("TestCANCommand.id "+id+" for "+toName(id));
	}

	private String toName(int id) {
		String name;
		switch (id) {
		case CAN.DRIVE_TRAIN_LEFT_MOTOR_1: name = "Drive Train Left 1"; break;
		case CAN.DRIVE_TRAIN_LEFT_MOTOR_2: name = "Drive Train Left 2"; break;
		case CAN.DRIVE_TRAIN_RIGHT_MOTOR_1: name = "Drive Train Right 1"; break;
		case CAN.DRIVE_TRAIN_RIGHT_MOTOR_2: name = "Drive Train Right 2"; break;
		case CAN.WINCH_MOTOR: name = "Winch"; break;
		case CAN.SHOOTER_MOTOR_1: name = "Shooter 1"; break;
		case CAN.SHOOTER_MOTOR_2: name = "Shooter 2"; break;
		case CAN.FUEL_INTAKE_MOTOR: name = "Fuel Intake"; break;
		default: throw new IllegalArgumentException("Unknown id: " + id);
		}
		return name;
	}

	@Override
	protected void execute() {
		switch (id) {
		case CAN.DRIVE_TRAIN_LEFT_MOTOR_1: driveTrain.leftMotor1.set(0.5); break;
		case CAN.DRIVE_TRAIN_LEFT_MOTOR_2: driveTrain.leftMotor2.set(0.5); break;
		case CAN.DRIVE_TRAIN_RIGHT_MOTOR_1: driveTrain.rightMotor1.set(0.5); break;
		case CAN.DRIVE_TRAIN_RIGHT_MOTOR_2: driveTrain.rightMotor2.set(0.5); break;
		case CAN.WINCH_MOTOR: Winch.winchmotor.set(0.5); break;
		case CAN.SHOOTER_MOTOR_1: Shooter.shooterMotorOne.set(0.5); break;
		case CAN.SHOOTER_MOTOR_2: Shooter.shooterMotorTwo.set(0.5); break;
		case CAN.FUEL_INTAKE_MOTOR: FuelIntake.motor.set(0.5); break;
		default: throw new IllegalArgumentException("Unknown id: " + id);
}
	}

	@Override
	protected boolean isFinished() {
		return Timer.getFPGATimestamp() - start > 2;
	}

}
