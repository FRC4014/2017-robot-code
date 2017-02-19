package org.usfirst.frc.team4014.steamworks.vision;

public final class VisionState {
	final double deltaAngle;
	final boolean xCentered;
	final int contourCount;
	final boolean yCentered;
	final double verticalDeltaAngle;
	
	public VisionState(double horizontalDeltaAngle, boolean centered, int contourcount, boolean yCentered, double verticalDeltaAngle) {
		super();
		this.deltaAngle = horizontalDeltaAngle;
		this.xCentered = centered;
		this.contourCount = contourcount;
		this.yCentered = yCentered;
		this.verticalDeltaAngle = verticalDeltaAngle;
		
	}
	
	
}
