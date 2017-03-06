package org.usfirst.frc.team4014.steamworks.gear;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OpenStovepipeFlap extends Command {

	private final Gear gear;

	public OpenStovepipeFlap(Gear gear){
		this.gear = gear;
	}

	protected void initalize(){
		gear.openStovePipeFlap();
		SmartDashboard.putString("Stovepipe Flap", "Open");
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
