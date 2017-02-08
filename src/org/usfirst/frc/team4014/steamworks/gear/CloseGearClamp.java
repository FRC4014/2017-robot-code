package org.usfirst.frc.team4014.steamworks.gear;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CloseGearClamp extends Command{

	private final Gear gear;
	
	public CloseGearClamp(Gear gear){
		this.gear = gear;
	}
	protected void initalize(){
		gear.open();
		SmartDashboard.putString("Gear Control Status", "Closed");
	}
	@Override
	protected boolean isFinished() {
		return true;
	}
}
