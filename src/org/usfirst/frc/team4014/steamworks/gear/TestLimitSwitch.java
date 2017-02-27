package org.usfirst.frc.team4014.steamworks.gear;

import edu.wpi.first.wpilibj.command.Command;

public class TestLimitSwitch extends Command{
	
	private final Gear gear;
	
	public TestLimitSwitch (Gear gear) {
		this.gear = gear;
	}
	
	protected void execute() {
		gear.writeLimitSwitchStatus();
	}
	
	@Override
	protected boolean isFinished() {
		// we want this to run forever
		return false;
	}

}
