package org.usfirst.frc.team4014.steamworks.gear;

import edu.wpi.first.wpilibj.command.Command;

public class ReleaseGear extends Command{
	
	private final Gear gear;
	
	

	public ReleaseGear(Gear gear) {
		this.gear = gear;
	}

	protected void initialize(){
		gear.open();
	}
	
	@Override
	protected void end() {
		gear.close();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
	
}
