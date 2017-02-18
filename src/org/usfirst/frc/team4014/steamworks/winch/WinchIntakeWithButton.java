package org.usfirst.frc.team4014.steamworks.winch;

import org.usfirst.frc.team4014.steamworks.OI;
import org.usfirst.frc.team4014.steamworks.gear.Gear;

import edu.wpi.first.wpilibj.command.Command;

public class WinchIntakeWithButton extends Command {
	
	private final Winch winch;
	private final Gear gear;

	
	public WinchIntakeWithButton(Winch winch, Gear gear) {
		this.winch = winch;
		this.gear = gear;
		requires(winch);
	}

	protected void initialize() {
		
	}
	
	protected void execute() {
		winch.start();
		gear.superClose();
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
