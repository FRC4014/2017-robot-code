package org.usfirst.frc.team4014.steamworks.gear;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OpenGearClamp extends Command{

	private final Gear gear;
	
	public OpenGearClamp(Gear gear){
		this.gear = gear;
		requires(gear);
	}
	protected void initalize(){
		gear.open();
		SmartDashboard.putString("Gear Control Status", "Open");
	}
	@Override
	protected boolean isFinished() {
		return false;
	}

}
