package org.usfirst.frc.team4014.steamworks.shooter;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleShooterFlaps extends Command{

	private final Shooter shooter;
	
	public ToggleShooterFlaps(Shooter shooter) {
		this.shooter = shooter;
		requires(shooter);
	}

	protected void initialize(){
		shooter.leftGateOpen();
		shooter.rightGateOpen();
	}
	protected void end(){
		shooter.leftGateClose();
		shooter.rightGateClose();
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
