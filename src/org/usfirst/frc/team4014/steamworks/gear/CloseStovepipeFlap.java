package org.usfirst.frc.team4014.steamworks.gear;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CloseStovepipeFlap extends Command {

	private final Gear gear;

	public CloseStovepipeFlap(Gear gear){
		this.gear = gear;
	}

	protected void initalize(){
		gear.closeStovePipeFlap();
		SmartDashboard.putString("Stovepipe Flap", "Closed");
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
