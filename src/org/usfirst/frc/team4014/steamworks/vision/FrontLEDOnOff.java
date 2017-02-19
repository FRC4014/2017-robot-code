package org.usfirst.frc.team4014.steamworks.vision;

import edu.wpi.first.wpilibj.command.Command;

public class FrontLEDOnOff extends Command{

	private final LEDs leds;
	public FrontLEDOnOff(LEDs leds) {
		this.leds = leds;
	}
	
	protected void initialize() {
		leds.frontOn();
	}
	

	protected void end() {
		leds.frontOff();
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
