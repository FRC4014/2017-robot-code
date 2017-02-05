package org.usfirst.frc.team4014.steamworks.gear;

import org.usfirst.frc.team4014.steamworks.OI;

import edu.wpi.first.wpilibj.command.Command;

public class MoveLeftServo extends Command{
	
	private final OI oi;
	private final Servos servos;
	
	

	public MoveLeftServo(OI oi, Servos servos) {
		this.oi = oi;
		this.servos = servos;
	}


	protected void execute(){
		
	}
	

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}


}
