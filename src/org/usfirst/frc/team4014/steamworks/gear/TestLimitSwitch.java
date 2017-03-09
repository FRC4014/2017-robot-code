package org.usfirst.frc.team4014.steamworks.gear;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TestLimitSwitch extends Command{
	
	private final Gear gear;
	private final DriveTrain driveTrain;
	
	public TestLimitSwitch (DriveTrain driveTrain, Gear gear) {
		this.driveTrain = driveTrain;
		this.gear = gear;
	}
	
	@Override
	protected void initialize() {
		System.out.println("TestLimitSwitch.initialize: called");
	}

	protected void execute() {
		boolean pegInGear = gear.isPegInGear();
		driveTrain.blinkLights(pegInGear);
		SmartDashboard.putBoolean("Limit Switch Status", pegInGear);
	}
	
	@Override
	public synchronized void cancel() {
		super.cancel();
		driveTrain.blinkLights(false);
		System.out.println("TestLimitSwitch.cancel: called");
	}

	@Override
	protected boolean isFinished() {
		// we want this to run forever
		return false;
	}

}
