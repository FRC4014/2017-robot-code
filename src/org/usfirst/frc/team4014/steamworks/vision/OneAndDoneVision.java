package org.usfirst.frc.team4014.steamworks.vision;

public class OneAndDoneVision extends InclineCamera{

	public OneAndDoneVision(VisionTracker vision, boolean boilerMode) {
		super(vision, boilerMode);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
	
}
