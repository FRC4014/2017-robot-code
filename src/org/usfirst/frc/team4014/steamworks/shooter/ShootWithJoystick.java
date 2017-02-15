package org.usfirst.frc.team4014.steamworks.shooter;

import org.usfirst.frc.team4014.steamworks.OI;

import edu.wpi.first.wpilibj.command.Command;



public class ShootWithJoystick extends Command {

	private final Shooter shooter;
	private final OI oi;
	
	public ShootWithJoystick(OI oi, Shooter shooter) {
		this.oi = oi;
		this.shooter = shooter;
		requires(shooter);
	}
	
	protected void initialize() {
		
	}
	
	protected void execute() {
		shooter.shoot(oi.getMateJoystick());
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected void end() {
		shooter.stop();
	}

	protected void interrupted() {
		end();
	}
}
