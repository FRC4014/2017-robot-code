package org.usfirst.frc.team4014.steamworks.vision;

import edu.wpi.first.wpilibj.command.Command;

public class BackLEDOnOff extends Command{

	private final LEDs leds;
	
	public BackLEDOnOff(LEDs leds) {
		this.leds = leds;
	}
	

	protected void initialize() {
		leds.backOn();
	}
	
	protected void end() {
		leds.backOff();
	}
	
	protected boolean isFinished() {
		return false;
	}

}
