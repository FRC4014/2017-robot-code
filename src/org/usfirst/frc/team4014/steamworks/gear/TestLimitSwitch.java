package org.usfirst.frc.team4014.steamworks.gear;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TestLimitSwitch extends Command{
	
	private final Gear gear;
	
	public TestLimitSwitch (Gear gear) {
		this.gear = gear;
	}
	
	@Override
	protected void initialize() {
		System.out.println("TestLimitSwitch.initialize: called");
	}


	protected void execute() {
		SmartDashboard.putBoolean("Limit Switch Status", gear.writeLimitSwitchStatus());
	}
	
	@Override
	public synchronized void cancel() {
		super.cancel();
		System.out.println("TestLimitSwitch.cancel: called");
	}

	@Override
	protected boolean isFinished() {
		// we want this to run forever
		return false;
	}

}
