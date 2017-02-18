package org.usfirst.frc.team4014.steamworks.vision;

public final class VisionState {
	final double deltaAngle;
	final boolean centered;
	final int contourCount;
	
	public VisionState(double deltaAngle, boolean centered, int contourcount) {
		super();
		this.deltaAngle = deltaAngle;
		this.centered = centered;
		this.contourCount = contourcount;
		
	}
	
	
}
