package org.usfirst.frc.team4014.steamworks.winch;

import org.usfirst.frc.team4014.steamworks.OI;

import edu.wpi.first.wpilibj.command.Command;

public class WinchIntakeWithButton extends Command {
	
	private final Winch winch;

	
	public WinchIntakeWithButton(Winch winch) {
		this.winch = winch;
		requires(winch);
		
	}

	protected void initialize() {
		
	}
	
	protected void execute() {
		winch.start();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected void end() {
		winch.stop();
	}

	protected void interrupted() {
		end();
	}
}
