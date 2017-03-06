package org.usfirst.frc.team4014.steamworks.gear;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ToggleStovepipeFlap extends Command {

	private Gear gear;

	public ToggleStovepipeFlap(Gear gear) {
		this.gear = gear;
		requires(gear);
	}

	protected void initialize(){
		gear.openStovePipeFlap();
		SmartDashboard.putString("Stovepipe Flap", "Open");
	}

	@Override
	protected void end() {
		gear.closeStovePipeFlap();
		SmartDashboard.putString("Stovepipe Flap", "Closed");
	}

	@Override
	protected boolean isFinished() {
		// always return false because this command is toggled
		return false;
	}

}
