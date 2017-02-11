package org.usfirst.frc.team4014.steamworks.gear;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ToggleGearClamp extends Command{
	
	private final Gear gear;
	
	

	public ToggleGearClamp(Gear gear) {
		this.gear = gear;
		requires(gear);
	}

	protected void initialize(){
		gear.open();
		SmartDashboard.putString("Gear Control Status", "Open");
	}
	
	@Override
	protected void end() {
		gear.close();
		SmartDashboard.putString("Gear Control Status", "Closed");
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
}
